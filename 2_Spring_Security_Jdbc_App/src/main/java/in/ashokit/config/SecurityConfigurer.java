package in.ashokit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.ashokit.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
	@Autowired
	private MyUserDetailsService userDtlsService;
	
	@Autowired
	public void configureUsers(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDtlsService)
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	@Bean
	public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests( (req) -> 
				req.requestMatchers("/admin")
				   .permitAll()
				   .anyRequest()
				   .authenticated()
		).formLogin();
		return http.build();
	}
}
