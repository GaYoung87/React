package com.test.vo;

public class User {
	
	private String num;
	private String name;
	private String message;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMassage() {
		return message;
	}
	public void setMassage(String massage) {
		this.message = massage;
	}
	@Override
	public String toString() {
		return "User [num=" + num + ", name=" + name + ", message=" + message + "]";
	}
	public User(String num, String name, String message) {
		this.num = num;
		this.name = name;
		this.message = message;
	}

}
