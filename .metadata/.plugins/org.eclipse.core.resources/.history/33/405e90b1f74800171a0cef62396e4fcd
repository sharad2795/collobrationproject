package com.niit.collobration.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.niit.collobration.BaseDomain.BaseDomain;
@Entity
@Component
@Table(name="friend")
public class Friend extends BaseDomain
{
	@Id
	private int id;
	
	private String userid;
	
	private String friend_id;
	
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

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	
}
