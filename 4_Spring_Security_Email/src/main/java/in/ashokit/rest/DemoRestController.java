package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.EmailUtils;
import jakarta.mail.MessagingException;


@RestController
public class DemoRestController {

	@Autowired
	private EmailUtils emailUtils;

	@GetMapping("/email")
	public String sendmail() throws Exception {
		//emailUtils.sendEmail();
		emailUtils.sendEmailWithAttachment();
		return "Email Sent Successfully";
	}
}
