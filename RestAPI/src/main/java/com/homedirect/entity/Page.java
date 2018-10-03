package com.homedirect.entity;

import java.util.List;

public class Page<T> {

	private int pageNo;
	private int pageSize;
	private int totalElements;
	private List<T> content;

	public Page(int pageNo, int pageSize, int totalElements, List<T> data) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.content = data;
		this.totalElements = totalElements;
	}

	public Page() {
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
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

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> data) {
		this.content = data;
	}
}
