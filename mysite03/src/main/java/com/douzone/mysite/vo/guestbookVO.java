package com.douzone.mysite.vo;

public class guestbookVO {
	private long no;
	private String name;
	private String password;
	private String message;
	private String regDate;
	
	public long getNo() {
		return no;
	}
	public long setNo(long no) {
		return this.no = no;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "guestbookVO [no=" + no + ", name=" + name + ", password=" + password + ", message=" + message
				+ ", regDate=" + regDate + "]";
	}
	
	
	
}
