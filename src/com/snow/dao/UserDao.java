package com.snow.dao;

import java.util.List;

import com.snow.model.Album;
import com.snow.model.User;

public interface UserDao {
	public User login(User u);

	public boolean register(User u);
	
	public boolean amessage(String ousername,String username,String descriptions);
	
	public List<Album> getAllAlbums(int uid);

}