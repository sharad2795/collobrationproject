package com.niit.collobration;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collobration.DAO.CommentsDAO;
import com.niit.collobration.model.Comments;

public class Commentstestcase 
{
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static Comments comments;
	
	@Autowired
	static CommentsDAO commentsDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		comments = (Comments) context.getBean("comments");
		commentsDAO = (CommentsDAO) context.getBean("commentsDAO");
	}
	@Test
	public void createCommentTestCase()
	{
		comments.setId(1);
		comments.setMessage("Nice Post");
		comments.setBlogid(1);
		comments.setUserid("lala");
		long d = System.currentTimeMillis();
		Date today = new Date(d);
		comments.setDateadded(today);
		boolean flag = commentsDAO.saveComment(comments);
		assertEquals("createCommentTestCase", true, flag);
	}

}
