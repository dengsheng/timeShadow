package com.snow.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AlbumMapper implements RowMapper<Album>{

	@Override
	public Album mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Album album = new Album();
		album.setId(rs.getInt("aid"));
		album.setAlbumname(rs.getString("aname"));
		album.setDescription(rs.getString("adescription"));
		album.setPv(rs.getInt("apv"));
		album.setUserid(rs.getInt("uid"));
		album.setCreatedate(rs.getDate("adate"));
		return album;
	}

}
