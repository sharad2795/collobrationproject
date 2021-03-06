package com.niit.collobration.DAO;

import java.util.List;

import com.niit.collobration.model.Jobapplication;

public interface JobapplicationDAO 
{
	public List<Jobapplication> fetchAllJobApplications();
	
	public List<Jobapplication> fetchAllJobApplicationsByJobID(int jobid);
	
	public Jobapplication fetchJobApplicationsByID(int id);
	
	public List<Jobapplication> fetchAllJobApplicationsByUserID(String userid);
	
	public boolean saveJobApplication(Jobapplication  jobapplication);
	
	public boolean	updateJobApplication(Jobapplication  jobapplication);
	
	public boolean deleteJobApplication(Jobapplication  jobapplication);
	
}
	











