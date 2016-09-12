package com.snow.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.snow.entity.UserMapper;

import com.snow.dao.UserDao;
import com.snow.entity.User;

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
}
