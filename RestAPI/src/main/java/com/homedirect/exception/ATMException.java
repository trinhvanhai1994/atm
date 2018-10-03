package com.homedirect.exception;

public class ATMException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private int code;
	private Object object;
	
	public ATMException(String message) {
		super(message);
	}
	
	public ATMException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ATMException(int code,String message, Object object) {
		super(message + ": " + object);
		this.code = code;
		this.object = object;
	}

	public int getCode() { return code; }
	
	public void setCode(int code) { this.code = code; }

	public Object getObject() {return object;}

	public void setObject(Object object) {this.object = object;}
	
}
