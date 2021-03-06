package com.snow.service;

import java.util.Date;
import java.util.List;

import com.snow.model.Addf;
import com.snow.model.Album;
import com.snow.model.Comment;
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

	public int countImage(int id);

	public List<Image> findImg(Page page,int id);
	public List<Image> findUserImg(int id);
	
	public int getAid(String albumname);
	
	public List<Addf> getAddf(String name);
	
	public void deleteAdd(String user,String friend);

	public void acceptFriend(String user, String friend);
	
	public List<Addf> getFriends(String name);
	
	public Image findImgOne(int id);
	
	//public void shareImg(int id,String desc);

	public List<Image> findShareImg();

	public void updateAlbum(String oldname, String albumname, String desc,
			Date date);
	public void deleteAlbum(String name);
	
	public List<Comment> getComment(int tid);
	
	public void addComment(Comment comment);
	
}