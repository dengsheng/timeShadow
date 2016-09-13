package com.snow.dao;

import com.snow.model.User;

public interface UserDao {
	public User login(User u);

	public boolean register(User u);

}