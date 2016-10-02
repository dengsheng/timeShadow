package com.snow.model;

import java.util.Date;

public class Image {
	private int id;
	private String name;
	private String url;
	private int aid;
	private Date createdate;
	private int pv;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public int getAid(){
		return aid;
	}
	public void setAid(int aid){
		this.aid = aid;
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
}
