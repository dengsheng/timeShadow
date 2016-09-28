package com.snow.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AddfMapper implements RowMapper<Addf>{

	@Override
	public Addf mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Addf addf = new Addf();
		addf.setWho(rs.getString("who"));
		addf.setTo(rs.getString("towho"));
		return addf;
	}

}
