package com.niit.collobration.DAO;

import java.util.List;

import com.niit.collobration.model.User;

public interface UserDAO 
{
	
	public List<User> list();
	
	public boolean save(User user);
	
	public boolean update(User user);
	
	public User getuserbyid(String id);
	
	public User validate(String id, String password);
	
	public boolean delete(User user);
	
	
}
