package com.niit.collobration.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.niit.collobration.BaseDomain.BaseDomain;

@Entity
@Component
@Table(name="comments")
public class Comments extends BaseDomain
{
	@Id
	private int id;
	private String userid;
	private int blogid;
	private int forumid;
	private String message;
	private Date dateadded;
	
	public Date getDateadded() {
		return dateadded;
	}
	public void setDateadded(Date dateadded) {
		this.dateadded = dateadded;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public int getForumid() {
		return forumid;
	}
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
