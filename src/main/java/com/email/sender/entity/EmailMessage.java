package com.email.sender.entity;

import org.springframework.lang.NonNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmailMessage {
	

	private String to;

	private String subject;

	private String body;
	
	public EmailMessage() {
		
	}
	/**
	 * @param to
	 * @param subject
	 * @param body
	 */
	public EmailMessage(String from,String to, String subject, String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	
	}
	


	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "EmailMessage [to=" + to + ", subject=" + subject + ", body=" + body + "]";
	}
	
	
		

}
