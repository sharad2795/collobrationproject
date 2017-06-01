package com.niit.collobration.model;

import com.niit.collobration.BaseDomain.BaseDomain;

public class Chatmessage extends BaseDomain
{
	private String message;
	
	private int id;
	
	public Chatmessage()
	{
		
	}
	
	public Chatmessage(int id, String message) {
	    this.id = id;
	    this.message = message;
	  }
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	


}
