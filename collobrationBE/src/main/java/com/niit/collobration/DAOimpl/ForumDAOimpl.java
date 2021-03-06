package com.niit.collobration.DAOimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collobration.DAO.ForumDAO;
import com.niit.collobration.model.Forum;
@Repository("forumDAO")
@EnableTransactionManagement
@Transactional
public class ForumDAOimpl implements ForumDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumDAOimpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public List<Forum> getAllUsersForums() {
		return sessionFactory.getCurrentSession().createQuery("from Forum").list();
	}

	public List<Forum> getAllForumsByUserId(String id) {
		return sessionFactory.getCurrentSession().createQuery("from Forum where userid='"+id+"'").list();
	}

	public Forum getForumById(int id) {
		return (Forum) sessionFactory.getCurrentSession().get(Forum.class, id);
	}

	public boolean saveForum(Forum forum) {
		try 
		{
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateForum(Forum forum) {
		try 
		{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteForum(Forum forum) {
		try 
		{
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}
