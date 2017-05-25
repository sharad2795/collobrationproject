package com.niit.collobration.DAO;

import java.util.List;

import com.niit.collobration.model.Job;

public interface JobDAO 
{
	public List<Job> list();
	
	public boolean save(Job job);
	
	public boolean update(Job job);
	
	public Job getjobbyid(String id);
	
	public boolean delete(Job job);
	

	
	
	
}
