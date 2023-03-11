package com.example.service;
import com.example.model.EmailDetails;

public interface EmailService {
	
	String sendSimplemail(EmailDetails details);
	
	String sendMailWithAttachment(EmailDetails details);

}
