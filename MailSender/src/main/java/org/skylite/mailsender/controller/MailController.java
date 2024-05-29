package org.skylite.mailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api")
public class MailController {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@PostMapping("/send-mail")
	public String sendMail(@RequestParam String mailId) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(mailId);
			helper.setSubject("Task assigned");
			helper.setText("Dear Player, You have a new task assigned to you. \nTask Name : Survive 5 hours in the Hell sand.");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javaMailSender.send(message);
		return "Mail has sent to "+mailId;
	}
	
	
}
