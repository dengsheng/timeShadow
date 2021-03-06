package com.snow.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



import com.snow.dao.UserDao;
import com.snow.model.Addf;
import com.snow.model.AddfMapper;
import com.snow.model.Album;
import com.snow.model.AlbumMapper;
import com.snow.model.Comment;
import com.snow.model.CommentMapper;
import com.snow.model.FriendMapper;
import com.snow.model.Image;
import com.snow.model.ImageMapper;
import com.snow.model.Page;
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
		String addsql = "UPDATE album SET apv = apv + 1 WHERE aid = ?";
		int roww = template.update(addsql,new Object[]{image.getAid()});
		if(row>0){
			return true;
		}else{
			return false;
		}
	}
	
	/*计算总照片数量*/
	@Override
	public int countImage(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) from imgs where aid = ?";
		int row = template.queryForObject(sql,new Object[]{id},Integer.class);
		return row;
	}

	/*获取相册照片*/
	@Override
	public List<Image> findImg(Page page,int id){
		// TODO Auto-generated method stub
		String sql = "select * from imgs  where aid = ? limit ?,?";
		RowMapper<Image> imgmap = new ImageMapper();
		List<Image> imglist = template.query(sql,new Object[]{id,page.getBegin(),page.getPageSize()},imgmap);
		return imglist;
	}
	
	
	
	/*获取个人照片并按浏览量排序*/
	@Override
	public List<Image> findUserImg(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * from imgs WHERE aid in(SELECT aid from album WHERE uid=?) ORDER BY tpv DESC";
		RowMapper<Image> imgmap = new ImageMapper();
		List<Image> imglist = template.query(sql,new Object[]{id},imgmap);
		return imglist;
	}
	
	
	
	/*获取相册编号*/
	@Override
	public int getAid(String albumname) {
		// TODO Auto-generated method stub
		String sql = "SELECT aid from album where aname = ?";
		int rs = template.queryForObject(sql, new Object[]{albumname}, Integer.class);
		return rs;
	}
	
	/*获取所有好友请求*/
	@Override
	public List<Addf> getAddf(String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM addf WHERE towho = ?";
		RowMapper<Addf> addfMapper = new AddfMapper();
		List<Addf> addfs = template.query(sql,new Object[]{name},addfMapper);
		return addfs;
	}
	
	/*删除请求*/
	@Override
	public void deleteAdd(String user, String friend) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM addf WHERE who =? and towho =?";
		int row = template.update(sql,new Object[]{friend,user});
	}
	
	/*接受好友请求*/
	@Override
	public void acceptFriend(String user, String friend) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO friends(friend1,friend2) values(?,?)";
		int row = template.update(sql,new Object[]{user,friend});
	}
	
	/*获取所有好友*/
	@Override
	public List<Addf> getFriends(String name) {
		String sql = "SELECT * FROM friends WHERE friend1 = ? or friend2 = ?";
		RowMapper<Addf> addfMapper = new FriendMapper();
		List<Addf> fris = template.query(sql,new Object[]{name,name},addfMapper);
		return fris;
	}
	
	/*获取具体照片*/
	@Override
	public Image findImgOne(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM imgs WHERE tid =?";
		RowMapper<Image> imgmap = new ImageMapper();
		List<Image> img = template.query(sql,new Object[]{id},imgmap);
		return img.get(0);
	}
	
	/*分享照片
	@Override
	public void shareImg(int id, String desc) {
		Date today = new Date();
		// TODO Auto-generated method stub
		String sql = "UPDATE imgs set tdesc=?,status=? ,tuploaddate=? WHERE tid =?";
		int row = template.update(sql, new Object[]{desc,"share",today,id});
	}*/
	
	/*获取所有照片*/
	@Override
	public List<Image> findShareImg() {
		// TODO Auto-generated method stub
		String sql = "select * from imgs";
		RowMapper<Image> imgmap = new ImageMapper();
		List<Image> imglist = template.query(sql,imgmap);
		return imglist;
	}

	/*修改相册*/
	@Override
	public void updateAlbum(String oldname, String albumname, String desc,
			Date date) {
		// TODO Auto-generated method stub
		String sql = "UPDATE album SET aname=?,adescription=?,adate=? WHERE aname=?";
		int row = template.update(sql,new Object[]{albumname,desc,date,oldname});
		
	}

	@Override
	public void deleteAlbum(String name) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM album WHERE aname =?";
		int row = template.update(sql,new Object[]{name});
	}

	/*获取照片评论*/
	@Override
	public List<Comment> getComment(int tid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * from comment WHERE tid=?";
		RowMapper<Comment> commentmap = new CommentMapper();
		List<Comment> commentlist = template.query(sql,new Object[]{tid},commentmap);
		return commentlist;
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO comment(para,tid,name,time) VALUES(?,?,?,?)";
		template.update(sql,new Object[]{comment.getPara(),comment.getTid(),comment.getName(),comment.getTime()});
	}

	
	
	
	
}
