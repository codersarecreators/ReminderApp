package com.codersarecreators.reminder;


public class SmsObject {

	private String id = null;
	private String text = null;
	private String date = null;
	private String time = null;
	private String phoneNumber = null;
	
	public SmsObject(String id, String text, String date, String time, String phoneNumber)
	{
		this.id = id;
		this.text = text;
		this.date = date;
		this.time = time;
		this.phoneNumber = phoneNumber;
	}
}
