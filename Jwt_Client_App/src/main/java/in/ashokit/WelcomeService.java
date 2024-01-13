package in.ashokit;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WelcomeService {

	private static final String AUTH_ENDPOINT="http://localhost:8080/auth/login";
	private static final String HI_ENDPOINT="http://localhost:8080/hi";
	
	public String invoksecuredRestApi() {
		String response =null;
		RestTemplate rt=new RestTemplate();
		JwtRequest request=new JwtRequest("parvez@gmail.com","parvez");
		ResponseEntity<JwtResponse> postForEntity = rt.postForEntity(AUTH_ENDPOINT,request,JwtResponse.class);
		JwtResponse body = postForEntity.getBody();
		System.out.println(body);
		String token = body.getToken();
		
		HttpHeaders headers=new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		
		HttpEntity entity=new HttpEntity<>(headers);
		ResponseEntity<String> exchange = rt.exchange(HI_ENDPOINT,HttpMethod.GET,entity, String.class);
		String body2 = exchange.getBody();
		System.out.println(body2);
		return body2;
	}
}
