package com.chinarewards.posnet2.report.vo;

import java.util.List;

import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.PolymorphismType;
import org.hibernate.annotations.Proxy;

public class MerchantExRecordVo {
	
	private MerchantExRecord merchantExRecord;
	
	private List<PosTypeCountVo> posTypeCounts;
	
	public MerchantExRecordVo() {
	}

	public MerchantExRecord getMerchantExRecord() {
		return merchantExRecord;
	}

	public void setMerchantExRecord(MerchantExRecord merchantExRecord) {
		this.merchantExRecord = merchantExRecord;
	}

	public List<PosTypeCountVo> getPosTypeCounts() {
		return posTypeCounts;
	}

	public void setPosTypeCounts(List<PosTypeCountVo> posTypeCounts) {
		this.posTypeCounts = posTypeCounts;
	}
	
	
	
	
	
	
}
