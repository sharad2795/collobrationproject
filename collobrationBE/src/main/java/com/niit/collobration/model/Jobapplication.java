package com.niit.collobration.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.niit.collobration.BaseDomain.BaseDomain;
@Entity
@Component
public class Jobapplication extends BaseDomain
{
private String firstname;

private String lastname;

private String email;

private String contact;

private String qualifications;

@Id
private int id;

private String marks10th;

private String marks12th;

private String userid;

private int jobid;

private String ugmarks;

private String experience;

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getContact() {
	return contact;
}

public void setContact(String contact) {
	this.contact = contact;
}

public String getQualifications() {
	return qualifications;
}

public void setQualifications(String qualifications) {
	this.qualifications = qualifications;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getMarks10th() {
	return marks10th;
}

public void setMarks10th(String marks10th) {
	this.marks10th = marks10th;
}

public String getMarks12th() {
	return marks12th;
}

public void setMarks12th(String marks12th) {
	this.marks12th = marks12th;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public int getJobid() {
	return jobid;
}

public void setJobid(int jobid) {
	this.jobid = jobid;
}

public String getUgmarks() {
	return ugmarks;
}

public void setUgmarks(String ugmarks) {
	this.ugmarks = ugmarks;
}

public String getExperience() {
	return experience;
}

public void setExperience(String experience) {
	this.experience = experience;
}


}