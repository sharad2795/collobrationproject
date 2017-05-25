package com.niit.collobration.DAO;

import java.util.List;

import com.niit.collobration.model.Forum;

public interface ForumDAO 
{

public List<Forum> getAllUsersForums();

public List<Forum> getAllForumsByUserId(String id);

public Forum getForumById(int id);

public boolean saveForum(Forum forum);

public boolean updateForum(Forum forum);

public boolean deleteForum(Forum forum);

}
