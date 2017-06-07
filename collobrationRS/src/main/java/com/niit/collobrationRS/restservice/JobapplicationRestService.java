package com.niit.collobrationRS.restservice;

import java.sql.Date;
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

import com.niit.collobration.DAO.JobapplicationDAO;
import com.niit.collobration.model.Job;
import com.niit.collobration.model.Jobapplication;

@RestController
public class JobapplicationRestService
{
	@Autowired
	private HttpSession session;
	
	@Autowired
	Jobapplication jobapplication;
	
	@Autowired
	JobapplicationDAO jobapplicationDAO;
	private static final Logger log=LoggerFactory.getLogger(JobapplicationRestService.class);
	@PostMapping("/savejobapplication/")
	public ResponseEntity<Jobapplication> createjobapplication(@RequestBody Jobapplication jobapplication)
	{
		log.debug("starting of create jobapplication method");
		String id = (String) session.getAttribute("loggedInUserID");
		Jobapplication newjob=jobapplicationDAO.fetchJobApplicationsByID(jobapplication.getId());
		
		if(id==null)
		{
			log.debug("user id null in job");
			jobapplication.setErrorcode("400");
			jobapplication.setErrormessage("please login first");
			return new ResponseEntity<Jobapplication>(jobapplication, HttpStatus.OK);
		}
		if(newjob==null)
		{
			jobapplication.setId(ThreadLocalRandom.current().nextInt(100,1000000+1));
			jobapplication.setUserid(id);
			jobapplicationDAO.saveJobApplication(jobapplication);
			log.debug("jobapp added to database");
			log.debug("End of method createjobapp");
			jobapplication.setErrorcode("200");
			jobapplication.setErrormessage("job applied successfully");
			
			return new ResponseEntity<Jobapplication>(jobapplication, HttpStatus.OK);
		}
		else
		{
			jobapplication.setErrorcode("404");
			jobapplication.setErrormessage("job not applied");
			
			log.debug("job already applied");
			log.debug("End of method appliedjob");
			return new ResponseEntity<Jobapplication>(jobapplication, HttpStatus.OK);
		}
	}
	@GetMapping("/JobapplicationById/{id}")
	public ResponseEntity<Jobapplication> showjobapplicationById(@PathVariable("id")int id)
	{
		System.out.println(id);
		log.debug("Satrting of method showjobapplicationById");
		jobapplication = jobapplicationDAO.fetchJobApplicationsByID(id);
		if(jobapplication==null)
		{
			log.debug("Checking whether jobapp List is Null Or Not");
			jobapplication = new Jobapplication();
			jobapplication.setErrorcode("404");
			jobapplication.setErrormessage("No Such jobappli Exists");
			return new ResponseEntity<Jobapplication>(jobapplication, HttpStatus.OK);
		}
		else
		{
			jobapplication.setErrorcode("200");
			jobapplication.setErrormessage("jobapp By Id Fetched Successfully");
			return new ResponseEntity<Jobapplication>(jobapplication,HttpStatus.OK);
		}
	}
	@GetMapping("/listalljobsapplications/")
	public ResponseEntity<List<Jobapplication>> listAlljobapplications()
	{
		List<Jobapplication> joblist=jobapplicationDAO.fetchAllJobApplications();
	
		if (joblist == null) 
		{
			jobapplication.setErrorcode("404");
			jobapplication.setErrorcode("sorry jobapplist is null");
			
		}
		else

		{
			jobapplication.setErrorcode("200");
			jobapplication.setErrormessage("the list of jobapps are");
		}
		return new ResponseEntity<List<Jobapplication>>(joblist,HttpStatus.OK);
	}

	@GetMapping("/listalljobapplicationsbyuserid/")
	public ResponseEntity<List<Jobapplication>> listalljobapplicationsbyuserid()
	{	
		String id = (String) session.getAttribute("loggedInUserID");
		List<Jobapplication> joblist=jobapplicationDAO.fetchAllJobApplicationsByUserID(id);
	
		if (joblist == null) 
		{
			jobapplication.setErrorcode("404");
			jobapplication.setErrorcode("sorry joblist is null");
			
		}
		else

		{
			jobapplication.setErrorcode("200");
			jobapplication.setErrormessage("the list of jobsapp by userid  are");
		}
		return new ResponseEntity<List<Jobapplication>>(joblist,HttpStatus.OK);
	}


	@GetMapping("/listjobapplicationsbyjobid/{jobid}")
	public ResponseEntity<List<Jobapplication>> listjobapplicationsbyjobid(@PathVariable("jobid")int jobid)
	{
		
		List<Jobapplication> joblist=jobapplicationDAO.fetchAllJobApplicationsByJobID(jobid);
	
		if (joblist == null) 
		{
			jobapplication.setErrorcode("404");
			jobapplication.setErrorcode("sorry joblist is null");
			
		}
		else

		{
			jobapplication.setErrorcode("200");
			jobapplication.setErrormessage("the list of jobsapp are");
		}
		return new ResponseEntity<List<Jobapplication>>(joblist,HttpStatus.OK);
	}







}
