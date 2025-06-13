package com.email.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.email.sender.entity.EmailMessage;
import com.email.sender.service.EmailService;


import jakarta.mail.MessagingException;

 

@RestController
public class EmailSender {

	@Autowired
	EmailService emailService;
	
	

	/*
	 * Aqui recibe un POST con un Json en el cuerpo
	 * 
	 * {
     *  "from": "jealdana@cerometros.com",
     *  "to": "jealdana@gmail.com",
     *  "subject": "Prueba desde app java",
     *   "body": "<h1>Esta es una prueba de email desde Spring</h1> <p>Puede contener <strong>Contenido</strong> HTML.</p>"
     * }
	 */
	
	@PostMapping("/email/send")
	public String emailSend(
			@RequestBody EmailMessage emailMessage) {	
		
		String response = "Correo enviado...";
		 try {
			 
			 // Validar que los datos de entrada existan y sea correctos
			
				 // Validar que cada uno de los datos del objeto sean difernetes de null
				 if( emailMessage.getTo()!= null && emailMessage.getSubject()!= null && emailMessage.getBody()!= null) {
					 // Valida que al menos en la variable to tengamos una cadena de texto que represente una direccion de corro.
					 try {
						emailService.emailCheckSubjectAndBodyNotEmpty(emailMessage.getSubject(), emailMessage.getBody());
					} catch (Exception e) {
						
						response = e.getMessage();
					}
						 
						// Validar si el destinatario es una direccion de correo
						 if (emailService.emailCheckerTo(emailMessage.getTo())  ) {
							 emailService.sendHtmlEmail( emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getBody());
						 }else {
							 response="La dieccion de correo destino no es valida";
						 }
				
					 
				 }else {
					 response = "Los datos para envio del correo son insuficientes";
				 }
				  
			
		
		 } catch (MessagingException e) {
			e.printStackTrace();
			response = "Ha habido un error al enviar el correo.";
		}
		
		 return response;
	}
	
	@PostMapping("/email/form")
	public String emailSendByForm(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("body") String body) {
		
		String response = "Correo enviado.";
		 try {
			emailService.sendHtmlEmail(to, subject, body);
		} catch (MessagingException e) {
			e.printStackTrace();
			response = "Ha habido un error al enviar el correo.";
		}
		
		return response;
		
	}
	

}
