package com.niit.collobrationRS.restservice;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collobration.DAO.FriendDAO;
import com.niit.collobration.DAO.UserDAO;
import com.niit.collobration.model.Friend;
import com.niit.collobration.model.User;

@RestController
public class UserRestService {

	private static Logger log=LoggerFactory.getLogger(UserRestService.class);
	
	
	@Autowired 
	User user;
	@Autowired 
	UserDAO userDAO;
	@Autowired
	HttpSession session;
	@Autowired
	Friend friends;
	@Autowired
	FriendDAO friendsDAO;
	@GetMapping("/hello")
	public String hello()
	{
		System.out.println("in hello");
		return "hello";
	}

	@RequestMapping(value="/getAllUsers",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers()
	{
		List<User> userList=userDAO.list();
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	@RequestMapping(value="/showuserstoaddfriends", method=RequestMethod.GET)
	public ResponseEntity<List<User>> displayUsers()
	{
		List<User> userList=userDAO.list();
		String loggedInUserId = (String) session.getAttribute("loggedInUserID");
		List<Friend> approvedFriends = friendsDAO.fetchAllApprovedFriends(loggedInUserId);
		int size = userList.size();
		int friendsize = approvedFriends.size();
		int j=0;
		System.out.println(friendsize);
		for(int i=0;i<size;i++)
		{
			log.debug("Inside For Loop");
			user = userList.get(i);
			if(j<friendsize)
			{
				log.debug("2nd IF Condition ShowAllUser Inside For");
				friends = approvedFriends.get(j);
				log.debug(user.getId());
				log.debug(friends.getFriend_id());
				if(user.getId().equals(friends.getFriend_id()) || user.getId() == friends.getFriend_id())
				{
					log.debug("3rd IF Condition ShowAllUser Inside For");
					userList.remove(user);
					size=userList.size();
					i=-1;
					j=-1;
				}
				j++;
			}
			if(j>=friendsize)
			{
				log.debug("4rth IF COndition ShowAllUser Inside For");
				j=0;
			}
			if(user.getId().equals(loggedInUserId))
			{
				log.debug("IF COndition ShowAllUser Inside For");
				userList.remove(user);
				size=userList.size();
			}
		}
		List<Friend> pendingList = friendsDAO.fetchAllPendingFriendsByUserid(loggedInUserId);
		int pendingfriendsize = pendingList.size();
		int k = 0;
		for(int i=0;i<size;i++)
		{
			log.debug("Insiden 2nd For Loop");
			user = userList.get(i);
			if(k<pendingfriendsize)
			{
				log.debug("6th IF Condition ShowAllUser Inside For");
				friends = pendingList.get(k);
				log.debug(user.getId());
				log.debug(friends.getFriend_id());
				if(user.getId().equals(friends.getFriend_id()) || user.getId() == friends.getFriend_id())
				{
					log.debug("7th IF Condition ShowAllUser Inside For");
					userList.remove(user);
					size=userList.size();
					i=-1;
					k=-1;
				}
				k++;
			}
			if(k>=pendingfriendsize)
			{
				log.debug("5th IF COndition ShowAllUser Inside For");
				k=0;
			}
		}
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<User> logincredentials(@RequestBody User newUser )
	{
		log.debug("->->->->calling method authenticate");
		newUser = userDAO.validate(newUser.getId(), newUser.getPassword());
		if (newUser == null) {
			newUser = new User(); // Do wee need to create new user?
			newUser.setErrorcode("404");
			newUser.setErrorcode("Invalid Credentials.  Please enter valid credentials");
			
		}
		else

		{
			newUser.setIsonline("Online");
			userDAO.update(newUser);
			newUser.setErrorcode("200");
			newUser.setErrormessage("You have successfully logged in.");
			
			log.debug("->->->->Valid Credentials");
			/*session.setAttribute("loggedInUser", newUser);*/
			session.setAttribute("loggedInUserID", newUser.getId());
			session.setAttribute("loggedInUserRole", newUser.getRole());
			
			log.debug("You are loggin with the role : " +session.getAttribute("loggedInUserRole"));
		}
		return new ResponseEntity<User>(newUser, HttpStatus.OK);


		
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession session) 
	{
		log.debug("->->->->calling method logout");
		String id = (String) session.getAttribute("loggedInUserID");
		user = userDAO.getuserbyid(id);
		user.setIsonline("Offline");
		userDAO.update(user);
		session.invalidate();
		user = new User();
		user.setErrorcode("200");
		user.setErrormessage("You have successfully logged out.");

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/saveuser/")
	public ResponseEntity<User> createUser(@RequestBody User newUser)
	{
		log.debug("Starting of saveUser");
		user=userDAO.getuserbyid(newUser.getId());
		if(user==null)
		{
			if(newUser.getPassword().equals(newUser.getConfirmpassword()))
			{
				newUser.setIsonline("Offline");
				userDAO.save(newUser);
				log.debug("User added to database");
				log.debug("End of method saveUser");
				newUser.setErrorcode("200");
				newUser.setErrormessage("You Have Registered Successfully");
				return new ResponseEntity<User>(newUser, HttpStatus.OK);
			}
			else
			{
				newUser.setErrorcode("410");
				newUser.setErrormessage("Password And Confirm Password Not Matching");
				return new ResponseEntity<User>(newUser, HttpStatus.OK);
			}
		}
		else
		{
			newUser.setErrorcode("404");
			newUser.setErrormessage("Error Registering User");
			log.debug("Error user already exists");
			log.debug("End of method saveUser");
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		}
	}
	
	@PutMapping("/updateuser/")
	public ResponseEntity<User> updateuser(@RequestBody User user)
	{
		String id = (String) session.getAttribute("loggedInUserID");
		if(id==null)
		{
			log.debug("user id null user not loggedin");
			user=new User();
			user.setErrorcode("400");
			user.setErrormessage("please login first");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		log.debug("starting of method updateuser of userrestservice");
		User retreivedUser;
		retreivedUser=userDAO.getuserbyid(user.getId());
		if(retreivedUser==null)
		{
			retreivedUser=new User();
			//retreivedUser.setIsOnline('\u0000');
			user.setErrorcode("404");
			user.setErrorcode("sorry userlist is null");
				
		 
		}
		else
		{
			if(user.getAddress()==null||user.getAddress()=="")
			{
				user.setAddress(retreivedUser.getAddress());
			}
			if(user.getEmail()==null||user.getEmail()=="")
			{
				user.setEmail(retreivedUser.getEmail());
			}
			if(user.getConfirmpassword()==null||user.getConfirmpassword()=="")
			{
				user.setConfirmpassword(retreivedUser.getConfirmpassword());
			}
			if(user.getPassword()==null||user.getPassword()=="")
			{
				user.setPassword(retreivedUser.getPassword());
			}
			if(user.getContact()==null||user.getContact()=="")
			{
				user.setContact(retreivedUser.getContact());
			}
			if(user.getName()==null||user.getName()=="")
			{
				user.setName(retreivedUser.getName());
			}
			user.setRole(retreivedUser.getRole());
			user.setIsonline(retreivedUser.getIsonline());
			userDAO.update(user);
			user.setErrorcode("200");
			user.setErrormessage("userupdatedssuccesfully");
			
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUserFromDB(@PathVariable("id")String id)
	{
		log.debug("Start of method getUserFromDB");
		User retreivedUser;
		retreivedUser=userDAO.getuserbyid(id);
		if(retreivedUser==null)
		{
			retreivedUser=new User();
			//retreivedUser.setIsOnline('\u0000');
			user.setErrorcode("404");
			user.setErrorcode("sorry userlist is null");
				
		 
		}
		else
		{
			user.setErrorcode("200");
			user.setErrorcode("userfound");
			
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<User> deleteUserFromDB(@PathVariable("id")String id)
	{
		log.debug("Start of method deleteUserFromDB");
		User retreivedUser;
		retreivedUser=userDAO.getuserbyid(id);
		if(retreivedUser==null)
		{
			retreivedUser=new User();
			user.setErrorcode("404");
			user.setErrorcode("user is null");
		
		}
		else
		{
			userDAO.delete(retreivedUser);
			user.setErrorcode("200");
			user.setErrorcode("user deleted succesfully ");
		
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}




}
