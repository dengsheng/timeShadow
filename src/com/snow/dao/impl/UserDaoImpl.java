package com.snow.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



import com.snow.dao.UserDao;
import com.snow.model.Album;
import com.snow.model.AlbumMapper;
import com.snow.model.Image;
import com.snow.model.User;
import com.snow.model.UserMapper;

@Repository
public class UserDaoImpl implements UserDao{
	@Resource
	private JdbcTemplate template;//注入
	
	/*登录验证*/
	@Override
	public User login(User u) {
		// TODO Auto-generated method stub
		System.out.print("UserDaoImpl");
		String sql = "select * from user where uname=?";
	    Object params[] = {u.getUsername()};
		RowMapper<User> userMapper = new UserMapper();
		
		List<User> user =  template.query(sql, params, userMapper);
		//User user = template.queryForObject(sql,params,userMapper);
	    if(user.size() == 1){
	    	/*判断密码是否相等*/
	    	if(user.get(0).getPassword().equals(u.getPassword())){
	    		return user.get(0);
	    	}else{
	    		User uerror = new User();
	    		uerror.setPassword(" ");
	    		return uerror;
	    	}
	    }else{
	    	return null;
	    }
	}
	
	/*用户注册*/
	public boolean register(User u) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO user(uname,upwd,uemail) VALUES(?,?,?)";
		int row = template.update(sql, new Object[]{u.getUsername(),u.getPassword(),u.getEmail()});
		if(row > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/*修改个人信息*/
	public boolean amessage(String ousername,String username,String descriptions){
		String sql = "UPDATE USER SET uname=?,udescriptions=? WHERE uname=?";
		int row = template.update(sql,new Object[]{username,descriptions,ousername});
		if(row > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/*获取相册*/
	@Override
	public List<Album> getAllAlbums(int uid) {
		// TODO Auto-generated method stub
		String sql = "select * from album where uid=?";
		RowMapper<Album> albumMapper = new AlbumMapper();
		List<Album> albums = template.query(sql, new Object[]{uid}, albumMapper);
		return albums;
	}
	
	/*创建相册*/
	@Override
	public boolean createAlbum(Album album) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO album(aname,adescription,adate,apv,uid) VALUES(?,?,?,?,?)";
		int row = template.update(sql, new Object[]{album.getAlbumname(),album.getDescription(),album.getCreatedate(),album.getPv(),album.getUserid()});
		if(row>0){
			return true;
		}else{
			return false;
		}
	}
	
	/*上传照片*/
	@Override
	public boolean uploadImg(Image image) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO imgs(tname,turl,aid,tuploaddate) VALUES(?,?,?,?)";
		int row = template.update(sql,new Object[]{image.getName(),image.getUrl(),image.getAid(),image.getCreatedate()});
		if(row>0){
			return true;
		}else{
			return false;
		}
	}
}
