package com.niit.collobrationRS.restservice;

import java.util.Date;
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

import com.niit.collobration.DAO.ForumDAO;
import com.niit.collobration.model.Forum;
import com.niit.collobration.model.User;
@RestController
public class ForumRestService 
{
private static final Logger logger = LoggerFactory.getLogger(ForumRestService.class);
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private Forum forum;
	
	@Autowired
	private ForumDAO forumDAO;
	
	@Autowired User user;
	
	@PostMapping("/Forumsave/")
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum)
	{
		logger.debug("Starting of method createForum");
		long d = System.currentTimeMillis();
		Date today = new Date(d);
		String id = (String) session.getAttribute("loggedInUserID");
		if(id==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			forum.setErrorcode("400");
			forum.setErrorcode("User Not Logged In Please Log In First To Create Forum");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		else
		{
			logger.debug("Starting of else of create blog");
			forum.setId(ThreadLocalRandom.current().nextInt(100,1000000+1));
			forum.setDateadded(today);
			forum.setUserid(id);
			forumDAO.saveForum(forum);
			forum.setErrorcode("200");
			forum.setErrorcode("Forum Created Successfully");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
	}
	@DeleteMapping("/Forumdelete/{id}")
	public ResponseEntity<Forum> deleteForum(@PathVariable("id")int fid)
	{
		String id = (String) session.getAttribute("loggedInUserID");
		Forum newForum = forumDAO.getForumById(fid);
		if(id==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			forum.setErrorcode("400");
			forum.setErrormessage("User Not Logged In Please Log In First To Delete Forum");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		if(newForum==null)
		{
			logger.debug("No such Forum found");
			forum.setErrorcode("404");
			forum.setErrormessage("Forum Not Found");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		forum = forumDAO.getForumById(fid);
		if(forum.getUserid().equals(id))
		{
			boolean b = forumDAO.deleteForum(forum);
			if(b)
			{
				logger.debug("Forum Got Deleted");
				forum.setErrorcode("200");
				forum.setErrormessage("Forum Deleted Successfully");
			}
			else
			{
				logger.debug("Error Deleting Forum");
				forum.setErrorcode("405");
				forum.setErrormessage("Error Deleting Forum");
			}
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		else
		{
			logger.debug("You Cannot Delete This Forum Because This Forum Is Created By Another User");
			forum.setErrorcode("406");
			forum.setErrormessage("You Cannot Delete This Forum Because This Forum Is Created By Another User");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
	}
	@PutMapping("/Forumupdate/")
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum)
	{
		String id = (String) session.getAttribute("loggedInUserID");
		Forum newForum = forumDAO.getForumById(forum.getId());
		if(id==null)
		{
			logger.debug("Checking whether User Is Logged In Or Not");
			forum.setErrorcode("400");
			forum.setErrormessage("User Not Logged In Please Log In First To Update Forum");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		if(newForum==null)
		{
			logger.debug("No such Forum found");
			forum.setErrorcode("404");
			forum.setErrormessage("Forum Not Found");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		if(newForum.getUserid().equals(id))
		{
			long d = System.currentTimeMillis();
			Date today = new Date(d);
			forum.setDateadded(today);
			forum.setUserid(newForum.getUserid());
			if (forum.getTopic()==null || forum.getTopic()=="") 
			{
				forum.setTopic(newForum.getTopic());
			}
			boolean b = forumDAO.updateForum(forum);
			if(b)
			{
				logger.debug("Forum Got Updated");
				forum.setErrorcode("200");
				forum.setErrormessage("Forum Updated Successfully");
			}
			else
			{
				logger.debug("Error Updating Forum");
				forum.setErrorcode("405");
				forum.setErrormessage("Error Updating Forum");
			}
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		else
		{
			logger.debug("You Cannot Update This Forum Because This Forum Is Created By Another User");
			forum.setErrorcode("406");
			forum.setErrormessage("You Cannot Update This Forum Because This Forum Is Created By Another User");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
	}
	@GetMapping("/ForumAllList")
	public ResponseEntity<List<Forum>> getAllForums()
	{
		List<Forum> forumList = forumDAO.getAllUsersForums();
		if (forumList == null) 
		{
			user.setErrorcode("404");
			user.setErrorcode("sorry forumlist is null");
			
		}
		else

		{
			user.setErrorcode("200");
			user.setErrormessage("the list of forums are");
		}
		return new ResponseEntity<List<Forum>>(forumList,HttpStatus.OK);
	}
	@GetMapping("/ForumAllListByUserId")
	public ResponseEntity<List<Forum>> getAllForumsByUserId()
	{
		String id = (String) session.getAttribute("loggedInUserID");
		List<Forum> forumList = forumDAO.getAllForumsByUserId(id);
		if (forumList == null) 
		{
			user.setErrorcode("404");
			user.setErrorcode("sorry forumlist is null");
			
		}
		else

		{
			user.setErrorcode("200");
			user.setErrormessage("the list of forums are");
		}
		return new ResponseEntity<List<Forum>>(forumList,HttpStatus.OK);
	}
	@PostMapping("/ForumById/")
	public ResponseEntity<Forum> getForumById(@RequestBody Forum forum)
	{
		forum = forumDAO.getForumById(forum.getId());
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
}
