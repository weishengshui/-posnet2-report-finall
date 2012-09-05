package com.chinarewards.posnet2.report.service.user;

import java.util.List;
import java.util.Map;

import com.chinarewards.posnet2.report.exception.DaoLevelException;
import com.chinarewards.posnet2.report.vo.DetailExchRecord;
import com.chinarewards.posnet2.report.vo.EverydayRecordVo;
import com.chinarewards.posnet2.report.vo.MerchantExRecordVo;

public interface ReportService {
	
	public List getTotalStatement(String sDate, String eDate, String activity_id);
	
	public Map<String,EverydayRecordVo> getEverydayStatement(String sDate, String eDate, String activity_id);
	
	public List<String> getExchTypes(String activity_id);
	
	public Map<String,List<MerchantExRecordVo>> getMerchantTotal(String sDate, String eDate, String activity_id); 
	
	public DetailExchRecord getDetailExchRecordByTokenAndActivityId(String activity_id, String token)throws DaoLevelException;
		
	
}
