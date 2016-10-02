package com.snow.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ImageMapper implements RowMapper<Image>{

	@Override
	public Image mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Image image = new Image();
		image.setId(rs.getInt("tid"));
		image.setAid(rs.getInt("aid"));
		image.setName(rs.getString("tname"));
		image.setUrl(rs.getString("turl"));
		image.setCreatedate(rs.getDate("tuploaddate"));
		image.setPv(rs.getInt("tpv"));
		return image;
	}
	
}
