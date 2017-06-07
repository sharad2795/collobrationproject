package com.niit.collobration.DAO;

import java.util.List;

import com.niit.collobration.model.Friend;

public interface FriendDAO 
{
	public List<Friend> fetchAllFriends();
	
	public List<Friend> fetchAllApprovedFriends(String userid);
	
	public List<Friend> fetchAllPendingFriends(String friend_id);
	
	public List<Friend> fetchAllPendingFriendsByUserid(String userid);
	
	public List<Friend> fetchAllRejectFriends(String userid);
	
	public List<Friend> fetchAllFriendsByUserId(String id);
	
	public Friend getFriendById(int id);
	
	public Boolean saveFriend(Friend friend);
	
	public Boolean updateFriend(Friend friend);
	
	public Boolean deleteFriend(Friend friend);		

}
