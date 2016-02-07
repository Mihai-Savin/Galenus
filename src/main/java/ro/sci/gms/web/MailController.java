package ro.sci.gms.web;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sci.gms.service.SmtpMailSender;

@RestController
public class MailController {

	@Autowired
	private SmtpMailSender smtpMailSender;
	
	@RequestMapping("/send-mail")
	public void sendMail() throws MessagingException {
		smtpMailSender.send("petru.hangiu@gmail.com", "Test mail from Spring", "Hello");
	}
}
