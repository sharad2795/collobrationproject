package com.niit.collobration.DAOimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collobration.DAO.JobDAO;
import com.niit.collobration.model.Job;
@EnableTransactionManagement
@Repository("jobDAO")
public class JobDAOimpl implements JobDAO
{

	private SessionFactory sessionFactory;
	
	public JobDAOimpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		
	}
@Transactional
public List<Job> list() 
{
	
	return sessionFactory.getCurrentSession().createQuery("from Job").list();

}
@Transactional
public boolean save(Job job) 
{
	 try {
		sessionFactory.getCurrentSession().save(job);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	 

}
@Transactional
public boolean update(Job job) 
{
		
	try {
		sessionFactory.getCurrentSession().update(job);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}


}
@Transactional
public Job getjobbyid(String id) 
{
	return (Job) sessionFactory.getCurrentSession().get(Job.class, id);

}

@Transactional
public boolean delete(Job job) 
{
	try {
		sessionFactory.getCurrentSession().delete(job);;
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
}








}
