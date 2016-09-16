package com.snow.service;

import java.util.List;

import com.snow.model.Album;
import com.snow.model.Image;
import com.snow.model.Page;
import com.snow.model.User;

public interface UserService {
	
	public User login(User u);
	
	public boolean register(User u);
	
	public boolean amessage(String ousername,String username,String descriptions);
	
	public List<Album> getAllProblems(int uid);
	
	public boolean createAlbum(Album album);
	
	public boolean uploadImg(Image image);

	public int countImage();

	public List<Image> findImg(Page page);
	
}