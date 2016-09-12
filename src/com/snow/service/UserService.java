package com.snow.service;

import com.snow.entity.User;

public interface UserService {
	public User login(User u);
	public boolean register(User u);
}