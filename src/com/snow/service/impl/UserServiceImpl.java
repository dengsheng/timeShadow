package com.snow.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.snow.dao.UserDao;

import com.snow.model.Addf;
import com.snow.model.Album;
import com.snow.model.Comment;
import com.snow.model.Image;
import com.snow.model.Page;
import com.snow.model.User;
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
	public boolean amessage(String ousername,String username,String descriptions){
		return userDao.amessage(ousername, username, descriptions);
	}

	@Override
	public List<Album> getAllProblems(int uid) {
		// TODO Auto-generated method stub
		return userDao.getAllAlbums(uid);
	}

	@Override
	public boolean createAlbum(Album album) {
		// TODO Auto-generated method stub
		return userDao.createAlbum(album);
	}

	@Override
	public boolean uploadImg(Image image) {
		// TODO Auto-generated method stub
		return userDao.uploadImg(image);
	}

	@Override
	public int countImage(int id) {
		// TODO Auto-generated method stub
		return userDao.countImage(id);
	}

	@Override
	public List<Image> findImg(Page page,int id) {
		// TODO Auto-generated method stub
		return userDao.findImg(page,id);
	}

	@Override
	public int getAid(String albumname) {
		// TODO Auto-generated method stub
		return userDao.getAid(albumname);
	}

	@Override
	public List<Addf> getAddf(String name) {
		// TODO Auto-generated method stub
		return userDao.getAddf(name);
	}

	@Override
	public void deleteAdd(String user, String friend) {
		// TODO Auto-generated method stub
		userDao.deleteAdd(user, friend);
	}

	@Override
	public void acceptFriend(String user, String friend) {
		// TODO Auto-generated method stub
		userDao.acceptFriend(user, friend);
	}

	@Override
	public List<Addf> getFriends(String name) {
		// TODO Auto-generated method stub
		return userDao.getFriends(name);
	}

	@Override
	public Image findImgOne(int id) {
		// TODO Auto-generated method stub
		return userDao.findImgOne(id);
	}
	
	/*
	@Override
	public void shareImg(int id, String desc) {
		// TODO Auto-generated method stub
		userDao.shareImg(id, desc);
	}*/

	@Override
	public List<Image> findShareImg() {
		// TODO Auto-generated method stub
		return userDao.findShareImg();
	}

	@Override
	public void updateAlbum(String oldname, String albumname, String desc,
			Date date) {
		// TODO Auto-generated method stub
		userDao.updateAlbum(oldname, albumname, desc, date);
	}

	@Override
	public void deleteAlbum(String name) {
		// TODO Auto-generated method stub
		userDao.deleteAlbum(name);
	}

	@Override
	public List<Image> findUserImg(int id) {
		// TODO Auto-generated method stub
		return userDao.findUserImg(id);
	}

	@Override
	public List<Comment> getComment(int tid) {
		// TODO Auto-generated method stub
		return userDao.getComment(tid);
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		userDao.addComment(comment);
	}
	
	

}
