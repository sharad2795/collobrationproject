package com.niit.collobration.DAOimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collobration.DAO.JobDAO;
import com.niit.collobration.DAO.JobapplicationDAO;
import com.niit.collobration.model.Job;
import com.niit.collobration.model.Jobapplication;

@EnableTransactionManagement
@Transactional
@Repository("jobapplicationDAO")
public class JobapplicationDAOimpl implements JobapplicationDAO
{
	private static final Logger log = LoggerFactory.getLogger(JobapplicationDAOimpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	 private JobapplicationDAOimpl(SessionFactory sessionFactory) 
	{
		this.sessionFactory=sessionFactory;
	}

	public List<Jobapplication> fetchAllJobApplications() 
	{	return sessionFactory.getCurrentSession().createQuery("from Jobapplication").list();
	}

	public List<Jobapplication> fetchAllJobApplicationsByJobID(int jobid) 
	{
		return sessionFactory.getCurrentSession().createQuery("from Jobapplication where jobid='"+jobid+"'").list();
	}

	public Jobapplication fetchJobApplicationsByID(int id) 
	{
		return (Jobapplication) sessionFactory.getCurrentSession().get(Jobapplication.class, id);
	}

	public List<Jobapplication> fetchAllJobApplicationsByUserID(String userid) 
	{
		return sessionFactory.getCurrentSession().createQuery("from Jobapplication where userid='"+userid+"'").list();
	}

	public boolean saveJobApplication(Jobapplication jobapplication) {
		try {
			sessionFactory.getCurrentSession().save(jobapplication);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}

	public boolean updateJobApplication(Jobapplication jobapplication)
	{
		try {
			sessionFactory.getCurrentSession().update(jobapplication);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteJobApplication(Jobapplication jobapplication) {
		try {
			sessionFactory.getCurrentSession().delete(jobapplication);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
		
}
