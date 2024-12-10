package com.springtry.entity;

public class Email {

	
	private String from;
	private String to;
	private String submessage;
	private String mail;
	
	
	
	public Email() {}
	@Override
	public String toString() {
		return "email [from="+from+ ", to=" + to + ", submessage=" + submessage +", mail=" + mail+ "]";
	}
	public Email(String from, String to, String submessage,String mail) {
		super();
		this.from=from;
		this.to = to;
		this.submessage = submessage;
		this.mail = mail;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubmessage() {
		return submessage;
	}
	public void setSubmessage(String submessage) {
		this.submessage = submessage;
	}
	public String getFrom() {
		// TODO Auto-generated method stub
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
}
