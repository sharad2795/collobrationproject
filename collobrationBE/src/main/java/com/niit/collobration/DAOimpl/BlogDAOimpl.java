package com.niit.collobration.DAOimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collobration.DAO.BlogDAO;
import com.niit.collobration.model.Blog;

@Repository("blogDAO")
@EnableTransactionManagement
@Transactional
public class BlogDAOimpl implements BlogDAO
{
	private SessionFactory sessionFactory;
	
	public BlogDAOimpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		
	}
@Transactional
public List<Blog> list() 
{
	
	return sessionFactory.getCurrentSession().createQuery("from Blog").list();

}
@Transactional
public boolean save(Blog blog) 
{
	 try {
		sessionFactory.getCurrentSession().save(blog);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	 

}
@Transactional
public boolean update(Blog blog) 
{
		
	try {
		sessionFactory.getCurrentSession().update(blog);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}


}
@Transactional
public Blog getblogbyid(int id) 
{
	return (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);

}

@Transactional
public boolean delete(Blog blog) 
{
	try {
		sessionFactory.getCurrentSession().delete(blog);;
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
}
@Transactional
public List<Blog> listapprovedblogs() 
{
	return sessionFactory.getCurrentSession().createQuery("from Blog where status='approved'").list();
}
@Transactional
public List<Blog> listpendingblogs() 
{
	return sessionFactory.getCurrentSession().createQuery("from Blog where status='pending'").list();
}
@Transactional
public List<Blog> listrejectedblogs()
{
	return sessionFactory.getCurrentSession().createQuery("from Blog where status='rejected'").list();
}
public List<Blog> listblogbyuserid(String user_id) 
{
	return sessionFactory.getCurrentSession().createQuery("from Blog where user_id='"+user_id+"'").list();
}


	
}
