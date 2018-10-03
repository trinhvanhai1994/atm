package com.homedirect.request;

public class SearchTransactionRequest {

	private int accountId;
	private String fromDate;
	private String toDate;
	private Byte type;
	private int pageNo;
	private int pageSize;

	public SearchTransactionRequest(int accountId, String fromDate, String toDate, Byte type, int pageNo,
			int pageSize) {
		this.accountId = accountId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.type = type;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
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
