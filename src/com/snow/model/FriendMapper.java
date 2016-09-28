package com.snow.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FriendMapper implements RowMapper<Addf>{

	@Override
	public Addf mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Addf fris = new Addf();
		fris.setWho(rs.getString("friend1"));
		fris.setTo(rs.getString("friend2"));
		return fris;
	}

}
