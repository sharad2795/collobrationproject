package com.niit.collobration.DAO;

import java.util.List;

import com.niit.collobration.model.Blog;

public interface BlogDAO {
		
		public List<Blog> list();
		
		public boolean save(Blog blog);
		
		public boolean update(Blog blog);
		
		public Blog getblogbyid(int id);
		
		public boolean delete(Blog blog);
		
		public List<Blog> listapprovedblogs();
		
		public List<Blog> listpendingblogs();
		
		public List<Blog> listrejectedblogs();

		public List<Blog> listblogbyuserid(String user_id);
		
		
		
		
	}
	
