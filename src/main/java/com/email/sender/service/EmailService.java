package com.email.sender.service;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    String sender;
    
    public String sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        
        return "Enviado";
    }
    
    public void sendHtmlEmail( String to, String subject, String body) throws MessagingException {
	    MimeMessage message = mailSender.createMimeMessage();

	    message.setFrom(new InternetAddress(sender));
	    message.setRecipients(MimeMessage.RecipientType.TO, to);
	    message.setSubject(subject);

	    String htmlContent = body;
	    		//"<h1>Esta es una prueba de email desde Spring</h1>" +
	            //             "<p>Puede contener <strong>Contenido</strong> HTML.</p>";
	    message.setContent(htmlContent, "text/html; charset=utf-8");

	    mailSender.send(message);
	}
    
    // Recibe los campos de una pagina de contacto
    // Requiere un TOKEN valido que le daremos a la pagina. Usar Spring auth para generar token
    // Requiere que le asignemos una plantilla HTML
    // Aqui hay un ejemplo.  https://youtu.be/fZQOy5SucXw?si=JUWMEtr00JwTqxC1
    // Requiere que la plantilla se asocie a un eMail destino, Asunto
    public void sendHtmlEmailByForm( String fullName, String emailAddress, String phoneNumber,String contactMessage, String to, String subject, String body) throws MessagingException {
	    MimeMessage message = mailSender.createMimeMessage();

	    message.setFrom(new InternetAddress(sender));
	    message.setRecipients(MimeMessage.RecipientType.TO, to);
	    message.setSubject(subject);

	    String htmlContent = body;
	    		//"<h1>Esta es una prueba de email desde Spring</h1>" +
	            //             "<p>Puede contener <strong>Contenido</strong> HTML.</p>";
	    message.setContent(htmlContent, "text/html; charset=utf-8");

	    mailSender.send(message);
	}
    
    public boolean emailCheckerTo(String to) {
    	
    	
    	String regExp= "^[A-Za-z0-9._%+-]+@"
    				  + "[a-zA-Z0-9.-]+\\.[A-Za-z]{2,}$";
    	Pattern pattern = Pattern.compile(regExp);
    	Matcher matcher = pattern.matcher(to);

    	return matcher.find();
    }
    
    public  void emailCheckSubjectAndBodyNotEmpty(String subject, String body) throws Exception {
    	
    
    	
    	if ( subject.isBlank() && subject.isEmpty())
    		throw new Exception("El asunto del mensaje  es incorrecto o está vacio");
    		
    	
    	if( body.isBlank() && body.isEmpty())
    		throw new Exception("El cuerpo del mensaje  es incorrecto o está vacío");

    	
    }
}