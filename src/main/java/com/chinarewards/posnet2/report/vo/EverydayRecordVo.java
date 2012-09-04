package com.chinarewards.posnet2.report.vo;

import java.util.List;

public class EverydayRecordVo {
	
	private String time;
	private Weekend weekend;
	private String weekday;
	private List<Integer> count;//数量
	private Double amount;//金额
	public String getTime() {
		return time;
	}
	
	public EverydayRecordVo() {
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Weekend getWeekend() {
		return weekend;
	}
	public void setWeekend(Weekend weekend) {
		this.weekend = weekend;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	public List<Integer> getCount() {
		return count;
	}
	public void setCount(List<Integer> count) {
		this.count = count;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	

}
