package com.chinarewards.posnet2.report.vo;

import java.util.List;

import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.PolymorphismType;
import org.hibernate.annotations.Proxy;

public class MerchantExRecord {
	
	private String id;
	private String shopName;
	private Integer exCount;
	private Double amount;
	
	
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
