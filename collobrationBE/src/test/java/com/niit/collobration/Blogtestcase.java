package com.niit.collobration;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collobration.DAO.BlogDAO;

import com.niit.collobration.model.Blog;

public class Blogtestcase 
{

@Autowired static AnnotationConfigApplicationContext context;
	
	@Autowired  static Blog blog;
	
	@Autowired  static BlogDAO  blogDAO;
	
	@BeforeClass
	public static  void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		blog = (Blog) context.getBean("blog");
		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		 
	}
	
	
	//@Test
		public void createblogTestCase()
		{
			long d= System.currentTimeMillis();
			Date today=new Date(d);
			blog.setId(01);
			blog.setTitle("hello");
			blog.setUser_id("manish");
			blog.setDescription("new blog");
			blog.setStatus("p");
			blog.setDatecreated(today);
			boolean flag =	blogDAO.save(blog);
		       
		       assertEquals("createblogTestCase ",true, flag);
		}
		
	//@Test
	
		public void updateblog()
		{
			long d= System.currentTimeMillis();
			Date today=new Date(d);

			blogDAO.getblogbyid(01);
			blog.setId(01);
			blog.setTitle("hello");
			blog.setUser_id("manish");
			blog.setDescription("new blog");
			blog.setStatus("A");
			blog.setDatecreated(today);
			
			boolean flag= blogDAO.update(blog);
			assertEquals("updateblog", true, flag);
		}
	
	//@Test
	public void deleteblog()
	{
		blogDAO.getblogbyid(01);
		boolean flag= blogDAO.delete(blog);
		assertEquals("deleteblog",true, flag);
	
	}
	
	@Test
	public void listblogs()
	{
		blogDAO.getblogbyid(01);
		boolean flag = blogDAO.list()!=null;
		assertEquals("listblogs", true, flag);
	}
	
	
}


