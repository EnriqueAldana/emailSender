package com.email.sender.service;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    
    public String sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        
        return "Enviado";
    }
    
    public void sendHtmlEmail(String from, String to, String subject, String body) throws MessagingException {
	    MimeMessage message = mailSender.createMimeMessage();

	    message.setFrom(new InternetAddress(from));
	    message.setRecipients(MimeMessage.RecipientType.TO, to);
	    message.setSubject(subject);

	    String htmlContent = body;
	    		//"<h1>Esta es una prueba de email desde Spring</h1>" +
	            //             "<p>Puede contener <strong>Contenido</strong> HTML.</p>";
	    message.setContent(htmlContent, "text/html; charset=utf-8");

	    mailSender.send(message);
	}
}