package com.homedirect.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String fromAccount;
	private String toAccount;
	private Double transferAmount;
	private Date time;
	private String status;
	private String content;
	private Byte type;

	public class TransactionType {
		public static final byte DEPOSIT = 1;
		public static final byte WITHDRAW = 2;
		public static final byte TRANSFER = 3;
	}
	
	public static class Constant {
		public final static String STATUS_SUCCESS = "Thanh Cong";
		public final static String STATUS_FAIL = "That Bai";
		public final static int FEE_TRANSFER = 999;
		public final static int LIMIT_SHOW_HISTORY = 5;
		public final static Double DEFAULT_BALANCE = 50000d;
		public final static int MAX_AMOUNT_WITHDRAW = 10000000;
		public final static String NULL = "";
		public final static String CONTENT_WITHDRAW = "Rut tien";
		public final static String CONTENT_DEPOSIT = "Gui tien";
		public final static int PAGESIZE = 10;
	}

	public Transaction() {
	}

	public Transaction(String fromAccount, String toAccount, Double transferAmount, Date time, String status,
			String content, Byte type) {
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transferAmount = transferAmount;
		this.time = time;
		this.status = status;
		this.content = content;
		this.type = type;
	}

	public Integer getId() {return Id;}

	public void setId(Integer id) {Id = id;}

	public String getFromAccount() { return fromAccount; }

	public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }

	public String getToAccount() { return toAccount; }

	public void setToAccount(String toAccount) { this.toAccount = toAccount; }

	public Double getTransferAmount() { return transferAmount; }

	public void setTransferAmount(Double transferAmount) { this.transferAmount = transferAmount; }

	public Date getTime() { return time; }

	public void setTime(Date time) { this.time = time; }

	public String getStatus() { return status; }

	public void setStatus(String status) { this.status = status; }

	public String getContent() { return content; }

	public void setContent(String content) { this.content = content; }

	public Byte getType() { return type; }

	public void setType(Byte type) { this.type = type; }
}
