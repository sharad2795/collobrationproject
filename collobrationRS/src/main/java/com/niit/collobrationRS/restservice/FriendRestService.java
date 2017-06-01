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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collobration.DAO.FriendDAO;

import com.niit.collobration.DAOimpl.FriendDAOimpl;

import com.niit.collobration.model.Friend;

@RestController
public class FriendRestService 
{
	private static final Logger log = LoggerFactory.getLogger(FriendDAOimpl.class);
	@Autowired 
	Friend friend;
	@Autowired 
	FriendDAO friendDAO;
	@Autowired
	HttpSession session;
	
	@PostMapping("/savefriend/")
	public ResponseEntity<Friend> createFriend(@RequestBody Friend newFriend)
	{
		String id = (String) session.getAttribute("loggedInUserID");
		log.debug("Starting of savefriend");
		System.out.println(newFriend.getId());
		friend=friendDAO.fetchfriendsbyid(newFriend.getId());
		if(friend==null)
		{
			newFriend.setId(ThreadLocalRandom.current().nextInt(100,1000000+1));
			newFriend.setUserid(id);
			friendDAO.save(newFriend);
			log.debug("friends added to database");
			log.debug("End of method savefriend");
			newFriend.setErrorcode("200");
			newFriend.setErrormessage("You Have added friend Successfully");
			return new ResponseEntity<Friend>(newFriend, HttpStatus.OK);
		}
		else
		{
			newFriend = new Friend();
			newFriend.setErrorcode("404");
			newFriend.setErrormessage("Error SAVING FRIEND");
			log.debug("Error FRIEND already exists");
			log.debug("End of method saveFRIEND");
			return new ResponseEntity<Friend>(newFriend, HttpStatus.OK);
		}
	}
	@GetMapping("/getfriend/{id}")
	public ResponseEntity<Friend> getfriendFromDB(@PathVariable("id")int id)
	{
		log.debug("Start of method getfriendFromDB");
		String uid = (String) session.getAttribute("loggedInUserID");
		Friend retreivedFriend;
		retreivedFriend=friendDAO.fetchfriendsbyid(id);
		if(retreivedFriend==null)
		{
			retreivedFriend=new Friend();
			friend.setErrorcode("404");
			friend.setErrorcode("sorry friendlist is null");
				
		 
		}
		else
		{
			friend.setErrorcode("200");
			friend.setErrorcode("friend found");
			
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletefriend/{id}")
	public ResponseEntity<Friend> deletefriendFromDB(@PathVariable("id")int id)
	{
		log.debug("Start of method deletefriendFromDB");
		String uid = (String) session.getAttribute("loggedInUserID");
		Friend retreivedFriend;
		retreivedFriend=friendDAO.fetchfriendsbyid(id);
		if(retreivedFriend==null)
		{
			retreivedFriend=new Friend();
			friend.setErrorcode("404");
			friend.setErrorcode("sorry friendlist is null");
				
		 
		}
		
		else
		{
			friendDAO.delete(retreivedFriend);
			friend.setErrorcode("200");
			friend.setErrorcode("friend deleted succesfully ");
		
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	@GetMapping("/listfriendbyuserid/")
	public ResponseEntity<List<Friend>> listfriendbyuserid()
	{
		String id = (String) session.getAttribute("loggedInUserID");
		
		List<Friend> friendlist=friendDAO.fetchfriendbyuserid(id);
	
		if (friendlist == null) 
		{
			friend.setErrorcode("404");
			friend.setErrorcode("sorry friendlist is null");
			
		}
		else

		{
			friend.setErrorcode("200");
			friend.setErrormessage("the list of friends are");
		}
		return new ResponseEntity<List<Friend>>(friendlist,HttpStatus.OK);
	}



}
