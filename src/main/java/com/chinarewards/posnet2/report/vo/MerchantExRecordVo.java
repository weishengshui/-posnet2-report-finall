package com.chinarewards.posnet2.report.vo;

public class MerchantExRecordVo {
	
	private String shopName;
	private Integer exCount;
	private Double amount;
	private PosTypeCountVo posTypeCount;
	public MerchantExRecordVo() {
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
	public PosTypeCountVo getPosTypeCount() {
		return posTypeCount;
	}
	public void setPosTypeCount(PosTypeCountVo posTypeCount) {
		this.posTypeCount = posTypeCount;
	}
	
	
	
	
}
