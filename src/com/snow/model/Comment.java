package com.snow.model;

import java.util.Date;


public class Comment {
	private int cid;
	private String para;
	private int tid;
	private int sortid;
	private String name;
	private Date time;
	
	public int getCid(){
		return cid;
	}
	public void setCid(int cid){
		this.cid = cid;
	}
	public String getPara(){
		return para;
	}
	public void setPara(String para){
		this.para = para;
	}
	public int getTid(){
		return tid;
	}
	public void setTid(int tid){
		this.tid = tid;
	}
	public int getSortid(){
		return sortid;
	}
	public void setSortid(int sortid){
		this.sortid = sortid;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public Date getTime(){
		return time;
	}
	public void setTime(Date date){
		this.time = date;
	}
	
}
