package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestCOntroller {

	@GetMapping("/welcome")
	public String welcomeMsg() {
		return "welcome spring boot security";
	}
	
	@GetMapping("/greet")
	public String greetMsg() {
		return "Good Morning";
	}
}
