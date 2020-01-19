package com.ssafy.pms.vo;

public class Phone {
	private String num;
	private String model;
	private String vcode;
	private int price;

	public Phone() {
	}
	public Phone(String num, String model, String vendor) {
		this.num = num;
		this.model = model;
		this.vcode = vendor;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
}