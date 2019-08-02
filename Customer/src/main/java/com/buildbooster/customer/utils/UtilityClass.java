package com.buildbooster.customer.utils;

import com.buildbooster.customer.model.BusinessDetails;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class UtilityClass {
	
	public static String salt = "abc";
	public static  String urlText="http://localhost:8080/employee/activateUser";
	
	public static String sendMessage(BusinessDetails user, JavaMailSender sender) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(user.getEmail());
			helper.setText(urlText+"?email=" + user.getEmail() + "&password="
					+ user.getPassword());
			helper.setSubject("Registation Activation Link");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}
}
