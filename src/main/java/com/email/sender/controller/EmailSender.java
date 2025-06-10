package com.email.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.sender.entity.EmailMessage;
import com.email.sender.service.EmailService;

import jakarta.mail.MessagingException;

 

@RestController
public class EmailSender {

	@Autowired
	EmailService emailService;
	
	

	
	@PostMapping("/email/send")
	public String emailSend(
			@RequestBody EmailMessage emailMessage) {	
		
		String response = "Correo enviado...";
		 try {
			emailService.sendHtmlEmail(emailMessage.getFrom(), emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getBody());
		} catch (MessagingException e) {
			e.printStackTrace();
			response = "Ha habido un error al enviar el correo.";
		}
		
		 return response;
	}
	
	@GetMapping("/email/test")
	public String getEmailSend() {
		try {
			emailService.sendHtmlEmail("jealdana@cerometros.com", "jealdana@gmail.com", "Prueba de correo html", 
					"<h1>Esta es una prueba de email desde Spring</h1>" + "<p>Puede contener <strong>Contenido</strong> HTML.</p>");
		
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Correo enviado";
				//emailService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getBody());
	}
}
