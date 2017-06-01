package com.niit.collobration.DAO;

import java.util.List;

import com.niit.collobration.model.Friend;

public interface FriendDAO 
{
	public List<Friend> list();
	
	public boolean save(Friend friend);
	
	public boolean delete(Friend friend);
	
	public Friend fetchfriendsbyid(int id);
	
	public List<Friend> fetchfriendbyuserid(String userid);
}
