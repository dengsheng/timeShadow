package com.snow.model;

public class Page {
	private int page = 1;
	private int pagesize = 6;
	
	public int getBegin(){
		int begin = (page-1) * pagesize;
		return begin;
	}
	public int getPage(){
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pagesize;
	}
	public void setPageSize(int pageSize) {
		this.pagesize = pageSize;
	}
}
