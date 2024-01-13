package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JwtClientAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JwtClientAppApplication.class, args);
		WelcomeService bean = context.getBean(WelcomeService.class);
		bean.invoksecuredRestApi();
		
	}

}
