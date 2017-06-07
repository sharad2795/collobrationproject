package com.niit.collobrationRS.restservice;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collobration.DAO.FriendDAO;
import com.niit.collobration.DAO.UserDAO;
import com.niit.collobration.DAOimpl.FriendDAOimpl;
import com.niit.collobration.model.Friend;

@RestController
public class FriendRestService 
{
	private static final Logger logger = LoggerFactory.getLogger(FriendDAOimpl.class);
	@Autowired 
	Friend friend;
	@Autowired 
	FriendDAO friendDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	HttpSession session;
	
	@PostMapping("/Friendsave/")
	public ResponseEntity<Friend> saveFriend(@RequestBody Friend newFriend)
	{
		logger.debug("Satrting of method saveFriend");
		friend = friendDAO.getFriendById(newFriend.getId());
		String loggedInUserId = (String) session.getAttribute("loggedInUserID");
		if(loggedInUserId==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			newFriend.setErrorcode("400");
			newFriend.setErrormessage("User Not Logged In Please Log In First To Create friends");
			return new ResponseEntity<Friend>(newFriend, HttpStatus.OK);
		}
		if(friend==null)
		{
			logger.debug("Satrting of if(friend==null) method of savefriends");
			newFriend.setId(ThreadLocalRandom.current().nextInt(100,1000000+1));
			newFriend.setUserid(loggedInUserId);
			newFriend.setStatus("Pending");
			friendDAO.saveFriend(newFriend);
			newFriend.setErrorcode("200");
			newFriend.setErrormessage("Friend Successfully Added");
			return new ResponseEntity<Friend>(newFriend, HttpStatus.OK);
		}
		else
		{
			logger.debug("Satrting of else method of savefriends");
			newFriend.setErrorcode("404");
			newFriend.setErrormessage("friends Does Not Posted Successfully Please Try Again");
			return new ResponseEntity<Friend>(newFriend, HttpStatus.OK);
		}
	}
	@DeleteMapping("/Frienddelete/{id}")
	public ResponseEntity<Friend> deletefriends(@PathVariable("id")String id)
	{
		System.out.println(id);
		Friend newFriend = new Friend();
		int bid = Integer.parseInt(id);
		logger.debug("Starting of deletefriends Method");
		friend = friendDAO.getFriendById(bid);
		String loggedInUserId = (String) session.getAttribute("loggedInUserID");
		if(loggedInUserId==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			newFriend.setErrorcode("400");
			newFriend.setErrormessage("User Not Logged In Please Log In First To Delete friends");
			return new ResponseEntity<Friend>(newFriend, HttpStatus.OK);
		}
		if(friend==null)
		{
			logger.debug("Satrting of if(friends==null) method of deletefriends");
			newFriend.setErrorcode("404");
			newFriend.setErrormessage("No Such friends Exists");
			return new ResponseEntity<Friend>(newFriend, HttpStatus.OK);
		}
		else
		{
			logger.debug("Satrting of nested if method of else method of deletefriends");
			friendDAO.deleteFriend(friend);
			newFriend.setErrorcode("200");
			newFriend.setErrormessage("friends Deleted Successfully");
			return new ResponseEntity<Friend>(newFriend, HttpStatus.OK);
		}
	}
	@PostMapping("/FriendsById/{id}")
	public ResponseEntity<Friend> showfriendsById(@PathVariable("id")String id)
	{
		System.out.println(id);
		logger.debug("Satrting of method showfriendsById");
		int bid = Integer.parseInt(id);
		friend = friendDAO.getFriendById(bid);
		if(friend==null)
		{
			logger.debug("Checking whether friends List is Null Or Not");
			friend = new Friend();
			friend.setErrorcode("404");
			friend.setErrormessage("No Such friends Exists");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			friend.setErrorcode("200");
			friend.setErrormessage("friends By Id Fetched Successfully");
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
	}
	@GetMapping("/FriendsByUserId")
	public ResponseEntity<List<Friend>> showfriendsByUserId()
	{
		logger.debug("Satrting of method showfriendsByUserId");
		String loggedInUserId = (String) session.getAttribute("loggedInUserID");
		List<Friend> friendsList = friendDAO.fetchAllFriendsByUserId(loggedInUserId);
		if(loggedInUserId==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			friend.setErrorcode("400");
			friend.setErrormessage("User Not Logged In Please Log In First To Fetch friends");
			return new ResponseEntity<List<Friend>>(friendsList, HttpStatus.OK);
		}
		if(friendsList==null)
		{
			logger.debug("Checking whether friends List is Null Or Not");
			friend.setErrorcode("404");
			friend.setErrormessage("You Have Not Created Any friendss");
			return new ResponseEntity<List<Friend>>(friendsList, HttpStatus.OK);
		}
		else
		{
			logger.debug("Starting of Else Method of friendsByUserId Function");
			friend = new Friend();
			friend.setErrorcode("200");
			friend.setErrormessage("friendss By UserId Fetched Successfully");
			return new ResponseEntity<List<Friend>>(friendsList,HttpStatus.OK);
		}
	}
	@GetMapping("/RejectedFriendsByUserId")
	public ResponseEntity<List<Friend>> showRejectedFriendsByUserId()
	{
		logger.debug("Satrting of method showRejectedFriendsByUserId");
		String loggedInUserId = (String) session.getAttribute("loggedInUserID");
		List<Friend> friendsList = friendDAO.fetchAllRejectFriends(loggedInUserId);
		if(loggedInUserId==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			friend.setErrorcode("400");
			friend.setErrormessage("User Not Logged In Please Log In First To Fetch friends");
			return new ResponseEntity<List<Friend>>(friendsList, HttpStatus.OK);
		}
		if(friendsList==null)
		{
			logger.debug("Checking whether friends List is Null Or Not");
			friend.setErrorcode("404");
			friend.setErrormessage("You Have Not Created Any friendss");
			return new ResponseEntity<List<Friend>>(friendsList, HttpStatus.OK);
		}
		else
		{
			logger.debug("Starting of Else Method of friendsByUserId Function");
			friend = new Friend();
			friend.setErrorcode("200");
			friend.setErrormessage("friendss By UserId Fetched Successfully");
			return new ResponseEntity<List<Friend>>(friendsList,HttpStatus.OK);
		}
	}
	@GetMapping("/PendingFriendsByUserId")
	public ResponseEntity<List<Friend>> showPendingFriendsByUserId()
	{
		logger.debug("Satrting of method showPendingFriendsByUserId");
		String loggedInUserId = (String) session.getAttribute("loggedInUserID");
		List<Friend> friendsList = friendDAO.fetchAllPendingFriends(loggedInUserId);
		if(loggedInUserId==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			friend.setErrorcode("400");
			friend.setErrormessage("User Not Logged In Please Log In First To Fetch friends");
			return new ResponseEntity<List<Friend>>(friendsList, HttpStatus.OK);
		}
		if(friendsList==null)
		{
			logger.debug("Checking whether friends List is Null Or Not");
			friend.setErrorcode("404");
			friend.setErrormessage("You Have Not Created Any friendss");
			return new ResponseEntity<List<Friend>>(friendsList, HttpStatus.OK);
		}
		else
		{
			logger.debug("Starting of Else Method of friendsByUserId Function");
			friend = new Friend();
			friend.setErrorcode("200");
			friend.setErrormessage("friendss By UserId Fetched Successfully");
			return new ResponseEntity<List<Friend>>(friendsList,HttpStatus.OK);
		}
	}
	@GetMapping("/ApprovedFriendsByUserId")
	public ResponseEntity<List<Friend>> showApprovedFriendsByUserId()
	{
		logger.debug("Satrting of method showApprovedFriendsByUserId");
		String loggedInUserId = (String) session.getAttribute("loggedInUserID");
		List<Friend> friendsList = friendDAO.fetchAllApprovedFriends(loggedInUserId);
		if(loggedInUserId==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			friend.setErrorcode("400");
			friend.setErrormessage("User Not Logged In Please Log In First To Fetch friends");
			return new ResponseEntity<List<Friend>>(friendsList, HttpStatus.OK);
		}
		if(friendsList==null)
		{
			logger.debug("Checking whether friends List is Null Or Not");
			friend.setErrorcode("404");
			friend.setErrormessage("You Have Not Created Any friendss");
			return new ResponseEntity<List<Friend>>(friendsList, HttpStatus.OK);
		}
		else
		{
			logger.debug("Starting of Else Method of friendsByUserId Function");
			friend = new Friend();
			friend.setErrorcode("200");
			friend.setErrormessage("friends By UserId Fetched Successfully");
			return new ResponseEntity<List<Friend>>(friendsList,HttpStatus.OK);
		}
	}
	@PutMapping("/FriendupdateApproveStatus/")
	public ResponseEntity<Friend> updateFriendStatusApproved(@RequestBody Friend friend)
	{
		friend = friendDAO.getFriendById(friend.getId());
		String loggedInUserId = (String) session.getAttribute("loggedInUserID");
		List<Friend> pendingFriendsList = friendDAO.fetchAllPendingFriends(loggedInUserId);
		int size=pendingFriendsList.size();
		if(loggedInUserId==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			friend.setErrorcode("400");
			friend.setErrormessage("User Not Logged In Please Log In First To Fetch friends");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		if(friend==null)
		{
			logger.debug("Checking whether friend is Null Or Not");
			friend = new Friend();
			friend.setErrorcode("404");
			friend.setErrormessage("No Such Friend Found");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			for(int i=0;i<size;i++)
			{
				logger.debug("Inside Approve Update For");
				Friend friends = new Friend();
				friends = pendingFriendsList.get(i);
				friends.setId(ThreadLocalRandom.current().nextInt(100,1000000+1));
				String friendid = friend.getUserid();
				friends.setUserid(loggedInUserId);
				friends.setFriend_id(friendid);
				friends.setStatus("Approved");
				friendDAO.saveFriend(friends);
			}
			friend.setStatus("Approved");
			friendDAO.updateFriend(friend);
			friend.setErrorcode("200");
			friend.setErrormessage("Friend Request Approved");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
	}
	@PutMapping("/FriendupdateRejectStatus/")
	public ResponseEntity<Friend> updateFriendStatusRejected(@RequestBody Friend friend)
	{
		friend = friendDAO.getFriendById(friend.getId());
		String loggedInUserId = (String) session.getAttribute("loggedInUserID");
		if(loggedInUserId==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			friend.setErrorcode("400");
			friend.setErrormessage("User Not Logged In Please Log In First To Fetch friends");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		if(friend==null)
		{
			logger.debug("Checking whether friend is Null Or Not");
			friend = new Friend();
			friend.setErrorcode("404");
			friend.setErrormessage("No Such Friend Found");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			friend.setStatus("Rejected");
			friendDAO.updateFriend(friend);
			friend.setErrorcode("200");
			friend.setErrormessage("Friend Request Rejected");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
	}	
	
	

}
