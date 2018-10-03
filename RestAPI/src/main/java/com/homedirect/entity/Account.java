package com.homedirect.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String accountNumber;
	private String username;
	private String password;
	private Double amount;
	
	public static class Constant {
		public final static int USERNAME_LENG = 5;
		public final static int PASSWORD_LENG = 6;
		public final static double DEFAULT_AMOUNT = 50000;
	}
	
	public Account() {
	}

	public Account(Integer id, String accountNumber, String username, String password, Double amount) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.username = username;
		this.password = password;
		this.amount = amount;
	}
	
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getUsername() { return username; }
	public void setUsername(String userName) { this.username = userName; }

	public String getAccountNumber() { return accountNumber; }
	public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

	public String getPassword() { return password; }
	public void setPassword(String passWord) { this.password = passWord; }

	public Double getAmount() { return amount; }
	public void setAmount(Double amount) { this.amount = amount; }
	
	@Override
	public String toString() {
		return "Account [Id=" + id + ", accountNumber=" + accountNumber + ", username=" + username + ", password="
				+ password + ", amount=" + amount + "]";
	}
}
