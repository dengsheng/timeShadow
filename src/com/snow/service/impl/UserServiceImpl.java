package com.snow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.snow.dao.UserDao;

import com.snow.entity.User;
import com.snow.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;

	@Override
	public User login(User u) {
		System.out.println("UserserviceImpl");
		User user = userDao.login(u);
		return user;
	}

	@Override
	public boolean register(User u) {
		// TODO Auto-generated method stub
		return userDao.register(u);
	}

}
