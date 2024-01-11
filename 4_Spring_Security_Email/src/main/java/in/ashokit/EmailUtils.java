package in.ashokit;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("parvezahmad9i94@gmail.com");
		//msg.setTo("parvezahmad9i94@gmail.com","parveziahmad09@gmail.com");
		//msg.setCc("parveziahmad09@gmail.com");
		msg.setSubject("Spring boot Email");
		msg.setText("This is test body");
		mailSender.send(msg);
	}
	public void sendEmailWithAttachment() throws Exception{

		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg,true);
		helper.setTo("parvezahmad9i94@gmail.com");
		helper.setSubject("Your Report");
		helper.setText("<h2> Please download your Report</h2",true);
		File f=new File("Report.txt");
		helper.addAttachment("Report",new File("data.xls"));
		mailSender.send(msg);

	}
}
