package org.skylite.mailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class MailController {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${activation.token}")
	private String token;
	@PostMapping("/send-mail")
	public String sendMail(@RequestParam String mailId, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String path = request.getServletPath();
		String link= url.replace(path, "/api/activate?token="+token);
		System.out.println(link);
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(mailId);
			helper.setSubject("Activate Your account");
			helper.setText("Dear Player, You need to activate your account to use the product further.\nlink:"+link);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javaMailSender.send(message);
		return "Mail has sent to "+mailId;
	}
	
	@GetMapping("/activate")
	public String activate(@RequestParam String token) {
		if(token.equals(this.token)) {
			return "Your account has been activated";
		}
		return "Cannot activate account due to invalid token";
	}
	
	
}
