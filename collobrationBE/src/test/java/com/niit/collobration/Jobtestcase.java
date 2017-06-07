package com.niit.collobration;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collobration.DAO.JobDAO;
import com.niit.collobration.model.Job;

public class Jobtestcase {

@Autowired static AnnotationConfigApplicationContext context;
	
	@Autowired  static Job job;
	
	@Autowired  static JobDAO  jobDAO;
	
	@BeforeClass
	public static  void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		job = (Job) context.getBean("job");
		
		jobDAO = (JobDAO) context.getBean("jobDAO");
		 
	}
	
	
	//@Test
		public void createjobTestCase()
		{
			job.setId(01);
			job.setTitle("softwareengineers");
			job.setDescription("expereience 1 year,salary:20k");
			job.setCompanyname("INFOSYS");
			boolean flag =	jobDAO.save(job);
		       
			assertEquals("createjobTestCase ",true, flag);
		}
		
	//@Test
	
		public void updatejob()
		{
			job.setId(01);
			job.setTitle("softwareengineers");
			job.setDescription("expereience 2 year,salary:20k");
			job.setCompanyname("INFOSYS");
			
			boolean flag= jobDAO.update(job);
			assertEquals("updatejob", true, flag);
		}
	
	//@Test
	public void deletejob()
	{
		jobDAO.getjobbyid(01);
		boolean flag= jobDAO.delete(job);
		assertEquals("deletejob",true, flag);
	
	}
	
	@Test
	public void list()
	{
		jobDAO.getjobbyid(01);
		boolean flag = jobDAO.list()!=null;
		assertEquals("list", true, flag);
	}
	


}
