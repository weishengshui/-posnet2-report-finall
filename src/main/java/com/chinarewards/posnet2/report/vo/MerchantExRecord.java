package com.chinarewards.posnet2.report.vo;

public class MerchantExRecord {
	
	private String id;
	private String shopName;
	private Integer exCount;
	private Double amount = 0.0;
	
	
	public MerchantExRecord() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getExCount() {
		return exCount;
	}
	public void setExCount(Integer exCount) {
		this.exCount = exCount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
	
	
}
