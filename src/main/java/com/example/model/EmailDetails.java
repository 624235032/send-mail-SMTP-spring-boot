package com.example.model;


import lombok.Data;


@Data
public class EmailDetails {
	
	private String to;
	private String cc;
	private String msgBody;
	private String subject;
	private String attachment;

}
