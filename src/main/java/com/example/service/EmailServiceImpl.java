package com.example.service;

import java.io.File;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.model.EmailDetails;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String from;

	@Override
	public String sendSimplemail(EmailDetails details) {

		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// setting email sender
			mailMessage.setFrom(from);
			mailMessage.setTo(details.getTo());
			mailMessage.setCc(details.getCc());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());
			
			// send email 
			javaMailSender.send(mailMessage);
			
			return "Mail Sent Successfully";
		} catch (Exception e) {
			return "Error While Sending Mail";
		}
	}

	@Override
	public String sendMailWithAttachment(EmailDetails details) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			// setting email sender
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setTo(details.getTo());
			mimeMessageHelper.setCc(details.getCc());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(details.getSubject());
			
			FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
			
			// attaching file email
			mimeMessageHelper.addAttachment(file.getFilename(), file);
			
			// send email 
			javaMailSender.send(mimeMessage);
			return "Mail Sent Successfully";
		} catch (MessagingException e) {
			return "Error Shile Sending Mail";

		}
	}

}
