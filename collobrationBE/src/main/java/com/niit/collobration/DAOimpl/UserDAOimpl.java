package com.niit.collobration.DAOimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collobration.DAO.UserDAO;
import com.niit.collobration.model.User;
@Repository("userDAO")
@EnableTransactionManagement
public class UserDAOimpl implements UserDAO
{
	private static final Logger log = LoggerFactory.getLogger(UserDAOimpl.class);	
	
	private SessionFactory sessionFactory;
		
		public UserDAOimpl(SessionFactory sessionFactory)
		{
			this.sessionFactory=sessionFactory;
			
		}
	@Transactional
	public List<User> list() 
	{
		
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	
	}
@Transactional
	public boolean save(User user) 
	{
		 try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		 
	
	}
@Transactional
	public boolean update(User user) 
	{
			
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	
	}
@Transactional
	public User getuserbyid(String id) 
	{
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	
	}
@Transactional
	public User validate(String id, String password) 
	{
	log.debug("->->Starting of the method isValidUserDetails");
		Query query =  sessionFactory.getCurrentSession().createQuery("from User where id =? and password = ? ");
		query.setString(0, id);
		query.setString(1, password);
		
		List<User> list = (List<User>) query.list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		
		return null;	

	}

	@Transactional
	public boolean delete(User user) 
	{
		try {
			sessionFactory.getCurrentSession().delete(user);;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
