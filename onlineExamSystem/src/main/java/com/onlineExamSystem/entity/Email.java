package com.onlineExamSystem.entity;

public class Email {

	private String to;
	private String from;
	private String submessage;
	private String mail;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubmessage() {
		return submessage;
	}
	public void setSubmessage(String submessage) {
		this.submessage = submessage;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	protected Email(String to, String from, String submessage, String mail) {
		super();
		this.to = to;
		this.from = from;
		this.submessage = submessage;
		this.mail = mail;
	}
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Email [to=" + to + ", from=" + from + ", submessage=" + submessage + ", mail=" + mail + "]";
	}
	
	
	
}
