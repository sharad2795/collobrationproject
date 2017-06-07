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

import com.niit.collobration.DAO.JobDAO;
import com.niit.collobration.model.Blog;
import com.niit.collobration.model.Job;

@RestController
public class JobRestService 
{
	private static final Logger log=LoggerFactory.getLogger(JobRestService.class);
	
	@Autowired 
	private HttpSession session;
	
	@Autowired
	Job job;
	
	@Autowired
	
	JobDAO jobDAO;
	
	
	@PostMapping("/createjob/")
	public ResponseEntity<Job> createjob(@RequestBody Job job)
	{
		log.debug("starting of create job method");
		String id = (String) session.getAttribute("loggedInUserID");
		Job newjob=jobDAO.getjobbyid(job.getId());	
		
		if(id==null)
		{
			log.debug("user id null in job");
			job.setErrorcode("400");
			job.setErrormessage("please login first");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		if(newjob==null)
		{
			long d= System.currentTimeMillis();
			Date today=new Date(d);
			job.setId(ThreadLocalRandom.current().nextInt(100,1000000+1));
			job.setStatus("available");
			job.setUserid(id);
			jobDAO.save(job);
			log.debug("job added to database");
			log.debug("End of method createjob");
			job.setErrorcode("200");
			job.setErrormessage("job created successfully");
			
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		else
		{
			job.setErrorcode("404");
			job.setErrormessage("job not created");
			
			log.debug("job already exists");
			log.debug("End of method createjob");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
	}
	@PostMapping("/JobById/{id}")
	public ResponseEntity<Job> showjobById(@PathVariable("id")String id)
	{
		System.out.println(id);
		log.debug("Satrting of method showjobById");
		int bid = Integer.parseInt(id);
		job = jobDAO.getjobbyid(bid);
		if(job==null)
		{
			log.debug("Checking whether job List is Null Or Not");
			job = new Job();
			job.setErrorcode("404");
			job.setErrormessage("No Such job Exists");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		else
		{
			job.setErrorcode("200");
			job.setErrormessage("job By Id Fetched Successfully");
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	}
	@PutMapping("/updatejob/")
	public ResponseEntity<Job> updatejob(@RequestBody Job job)
	{
		String id = (String) session.getAttribute("loggedInUserID");
		Job newjob=jobDAO.getjobbyid(job.getId());
		log.debug("starting of method updatejob");
		if(id==null)
		{
			log.debug("starting of method 1st if");
			job.setErrorcode("400");
			job.setErrormessage("please login first");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		if(newjob==null)
		{
			log.debug("starting of method 2ndif");
			job.setErrorcode("404");
			job.setErrormessage("job not found");
		}
		else
		{
			log.debug("starting of method 1stelse");
			if(newjob.getUserid().equals(id))
			{
				log.debug("starting of method 3rdif");
				job.setStatus(newjob.getStatus());
				job.setUserid(newjob.getUserid());
				if(job.getTitle()==null || job.getTitle()=="")
				{
					job.setTitle(newjob.getTitle());
				}
				if(job.getDescription()==null)
				{
					job.setDescription(newjob.getDescription());
				}
				jobDAO.update(job);
				log.debug("after update");
				job.setErrorcode("200");
				job.setErrormessage("userid is matching ");
				job.setErrormessage("job updated succesfully ");
			}
			else
			{
				
				log.debug("starting of method 2ndelse");
				job.setErrorcode("406");
				job.setErrormessage("user is is not matching");
			}
			
			
		
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
		
	@DeleteMapping("/deletejob/{id}")
	public ResponseEntity<Job> deletejob(@PathVariable("id")int bid)
	{
		String id = (String) session.getAttribute("loggedInUserID");
		Job newjob=jobDAO.getjobbyid(bid);
		log.debug("starting of method deletejob");
		if(id==null)
		{
			log.debug("starting of method 1st if");
			job.setErrorcode("400");
			job.setErrormessage("please login first");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		if(newjob==null)
		{
			log.debug("starting of method 2ndif");
			job.setErrorcode("404");
			job.setErrormessage("blog not found");
		}
		else
		{
			log.debug("starting of method 1stelse");
			if(newjob.getUserid().equals(id))
			{
				jobDAO.delete(newjob);
				log.debug("after delete");
				job.setErrorcode("200");
				job.setErrormessage("userid is matching ");
				job.setErrormessage("blog deleted succesfully ");
			}
			
			else
			{
				log.debug("starting of method 2ndelse");
				job.setErrorcode("406");
				job.setErrormessage("user is is not matching");
			}
	
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	@GetMapping("/listalljobs/")
	public ResponseEntity<List<Job>> listAlljobs()
	{
		List<Job> joblist=jobDAO.list();
	
		if (joblist == null) 
		{
			job.setErrorcode("404");
			job.setErrorcode("sorry joblist is null");
			
		}
		else

		{
			job.setErrorcode("200");
			job.setErrormessage("the list of jobs are");
		}
		return new ResponseEntity<List<Job>>(joblist,HttpStatus.OK);
	}

	@GetMapping("/listavailablejobs/")
	public ResponseEntity<List<Job>> listavailablejobs()
	{
		List<Job> joblist=jobDAO.getavailablejobs();
	
		if (joblist == null) 
		{
			job.setErrorcode("404");
			job.setErrorcode("sorry joblist is null");
			
		}
		else

		{
			job.setErrorcode("200");
			job.setErrormessage("the list of available jobs are");
		}
		return new ResponseEntity<List<Job>>(joblist,HttpStatus.OK);
	}


	@GetMapping("/listjobsbyuserid/")
	public ResponseEntity<List<Job>> listjobsbyuserid()
	{
		String id = (String) session.getAttribute("loggedInUserID");
		
		List<Job> joblist=jobDAO.getjobbyuserid(id);
	
		if (joblist == null) 
		{
			job.setErrorcode("404");
			job.setErrorcode("sorry joblist is null");
			
		}
		else

		{
			job.setErrorcode("200");
			job.setErrormessage("the list of jobs are");
		}
		return new ResponseEntity<List<Job>>(joblist,HttpStatus.OK);
	}

	@PutMapping("/updatejobstatus/")
	public ResponseEntity<Job> updatejobstatus(@RequestBody Job job)
	{
		String id = (String) session.getAttribute("loggedInUserID");
		Job newjob=jobDAO.getjobbyid(job.getId());
		log.debug("starting of method updatejob");
		if(id==null)
		{
			log.debug("starting of method 1st if");
			job.setErrorcode("400");
			job.setErrormessage("please login first");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		if(newjob==null)
		{
			log.debug("starting of method 2ndif");
			job.setErrorcode("404");
			job.setErrormessage("job not found");
		}
		else
		{
			log.debug("starting of method 1stelse");
			if(newjob.getUserid().equals(id))
			{
				log.debug("starting of method 3rdif");
				newjob.setStatus("NOT AVAILABLE");
				jobDAO.update(newjob);
				log.debug("after update");
				job.setErrorcode("200");
				job.setErrormessage("userid is matching ");
				job.setErrormessage("job updated succesfully ");
			}
			else
			{
				
				log.debug("starting of method 2ndelse");
				job.setErrorcode("406");
				job.setErrormessage("user is is not matching");
			}
			
			
		
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}


}
