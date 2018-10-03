package com.homedirect.request;

public class SearchAccountRequest {

	private String username;
	private int pageNo;
	private int pageSize;

	public SearchAccountRequest(String username, int pageNo, int pageSize) {
		super();
		this.username = username;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
