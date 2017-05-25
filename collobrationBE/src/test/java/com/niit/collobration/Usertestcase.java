package com.niit.collobration;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collobration.DAO.UserDAO;
import com.niit.collobration.model.User;

public class Usertestcase {

@Autowired static AnnotationConfigApplicationContext context;
	
	@Autowired  static User user;
	
	@Autowired  static UserDAO  userDAO;
	
	@BeforeClass
	public static  void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		user = (User) context.getBean("user");
		
		userDAO = (UserDAO) context.getBean("userDAO");
		 
	}
	
	
	//@Test
		public void createUserTestCase()
		{
			user.setId("manish");
			user.setConfirmpassword("manish@123");
			user.setPassword("manish@123");
			user.setName("manish");
			user.setRole("Admin");
			user.setAddress("Andheri W");
			user.setEmail("manish@gmail.com");
			user.setContact("999999999");
		       boolean flag =	userDAO.save(user);
		       
		       assertEquals("createUserTestCase ",true, flag);
		}
		
	//@Test
	
		public void updateuser()
		{
			userDAO.getuserbyid("manish");
			user.setId("manish");
			user.setConfirmpassword("manish@123");
			user.setPassword("manish@123");
			user.setName("manish");
			user.setRole("Admin");
			user.setEmail("manish@gmail.com");
			user.setContact("999999999");
			user.setAddress("mint st,chennai");
			boolean flag= userDAO.update(user);
			assertEquals("updateuser", true, flag);
		}
	
	//@Test
	public void deleteuser()
	{
		userDAO.getuserbyid("manish");
		boolean flag= userDAO.delete(user);
		assertEquals("deleteuser",true, flag);
	
	}
	
	@Test
	public void validateuser()
	{
		userDAO.getuserbyid("manish");
	User flag=	userDAO.validate("manish","manish@123");
		assertEquals("validateuser", true, flag );
		
		
	}

	//@Test
	public void listusers()
	{
		userDAO.getuserbyid("manish");
		boolean flag = userDAO.list()!=null;
		assertEquals("listusers", true, flag);
	}
	
	
}
