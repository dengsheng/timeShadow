package com.snow.model;

import java.io.Serializable;
import java.util.Date;

public class Album implements Serializable{
	private int id;
	private String albumname;
	private String description;
	private Date createdate;
	private int pv;
	private int userid;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getAlbumname(){
		return albumname;
	}
	public void setAlbumname(String albumname){
		this.albumname = albumname;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public Date getCreatedate(){
		return createdate;
	}
	public void setCreatedate(Date createdate){
		this.createdate = createdate;
	}
	public int getPv(){
		return pv;
	}
	public void setPv(int pv){
		this.pv = pv;
	}
	public int getUserid(){
		return userid;
	}
	public void setUserid(int userid){
		this.userid = userid;
	}
}
		
