package com.chinarewards.posnet2.report.vo;

public class DetailExchRecord {
	
	private String shopName;//商户名称
	private String posid;//posid
	private String token;//验证码
	private double amount;//消费金额
	private String time;//交易时间
	
	public DetailExchRecord() {
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	
}
