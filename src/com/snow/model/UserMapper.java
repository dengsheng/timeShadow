package com.snow.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("uid"));
		user.setUsername(rs.getString("uname"));
		user.setPassword(rs.getString("upwd"));
		user.setEmail(rs.getString("uemail"));
		user.setDescriptions(rs.getString("udescriptions"));
		return user;
	}

}