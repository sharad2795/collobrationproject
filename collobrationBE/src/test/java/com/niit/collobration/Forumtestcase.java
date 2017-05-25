package com.niit.collobration;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collobration.DAO.ForumDAO;
import com.niit.collobration.model.Forum;

public class Forumtestcase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static ForumDAO forumDAO;
	
	@Autowired
	static Forum forum;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.virus");
		context.refresh();
		forum = (Forum) context.getBean("forum");
		forumDAO = (ForumDAO) context.getBean("forumDAO");
	}
	@Test
	public void createForumTestCase()
	{
		long d = System.currentTimeMillis();
		Date today = new Date(d);
		forum.setId(01);
		forum.setTopic("What is java");
		forum.setUserid("mahi");
		forum.setDateadded(today);
		boolean flag = forumDAO.saveForum(forum);
		assertEquals("createForumTestCase",true, flag);
	}


}
