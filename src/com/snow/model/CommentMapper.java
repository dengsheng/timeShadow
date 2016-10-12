package com.snow.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CommentMapper implements RowMapper<Comment>{

	@Override
	public Comment mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Comment comment = new Comment();
		comment.setCid(rs.getInt("cid"));
		comment.setName(rs.getString("name"));
		comment.setPara(rs.getString("para"));
		comment.setTid(rs.getInt("tid"));
		comment.setTime(rs.getDate("time"));
		comment.setSortid(rs.getInt("sortid"));
		return comment;
	}

}
