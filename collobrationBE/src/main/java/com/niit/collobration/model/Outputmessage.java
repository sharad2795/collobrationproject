package com.niit.collobration.model;

import java.util.Date;

public class Outputmessage extends Chatmessage
{
	 private Date time;
	    
	    public Outputmessage(Chatmessage original, Date time) {
	        super(original.getId(), original.getMessage());
	        this.time = time;
	    }
	    
	    public Date getTime() {
	        return time;
	    }
	    
	    public void setTime(Date time) {
	        this.time = time;
	    }
}
