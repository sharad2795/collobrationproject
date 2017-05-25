package com.niit.collobration.DAOimpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collobration.DAO.CommentsDAO;
import com.niit.collobration.model.Comments;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@Repository("commentsDAO")
@EnableTransactionManagement
@Transactional
public class CommentsDAOimpl implements CommentsDAO
{

	private SessionFactory sessionFactory;
	
	public CommentsDAOimpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public List<Comments> getCommentsList() {
		return sessionFactory.getCurrentSession().createQuery("from Comments").list();
	}

	public boolean saveComment(Comments comments) {
		try 
		{
			sessionFactory.getCurrentSession().save(comments);
			return true;
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateComment(Comments comments) {
		try 
		{
			sessionFactory.getCurrentSession().update(comments);
			return true;
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteComment(Comments comments) {
		try 
		{
			sessionFactory.getCurrentSession().delete(comments);
			return true;
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Comments> getCommentsListByBlog(int id) {
		return sessionFactory.getCurrentSession().createQuery("from Comments where blogid='"+id+"'").list();
	}

	public Comments getCommentByID(int id) {
		return (Comments) sessionFactory.getCurrentSession().get(Comments.class, id);
	}

	public List<Comments> getCommentsListByForum(int id) {
		return sessionFactory.getCurrentSession().createQuery("from Comments where forumid='"+id+"'").list();
	}

}
