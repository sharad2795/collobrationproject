package com.niit.collobration.BaseDomain;

import javax.persistence.Transient;

public class BaseDomain 
{

	@Transient
	public String errormessage;
	
	@Transient
	public String errorcode;
	
	public String getErrormessage()
	{
		return errormessage;
	}
	public void setErrormessage(String errormessage) 
	{
		this.errormessage = errormessage;
	}
	public String getErrorcode() 
	{
		return errorcode;
	}
	public void setErrorcode(String errorcode) 
	{
		this.errorcode = errorcode;
	}
	
	








}
