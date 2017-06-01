package com.niit.collobration.DAOimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collobration.model.Friend;
@Transactional
@EnableTransactionManagement
@Repository("friendDAO")
public class FriendDAOimpl implements com.niit.collobration.DAO.FriendDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	private FriendDAOimpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	public List<Friend> list() 
	{
		return sessionFactory.getCurrentSession().createQuery("from Friend").list();
	}

	public boolean save(Friend friend) 
	{
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Friend fetchfriendsbyid(int id)
	{
		return (Friend) sessionFactory.getCurrentSession().get(Friend.class, id);
	}

	public List<Friend> fetchfriendbyuserid(String userid)
	{
		return  sessionFactory.getCurrentSession().createQuery("from Friend where userid='"+userid+"'").list();	

	}
}