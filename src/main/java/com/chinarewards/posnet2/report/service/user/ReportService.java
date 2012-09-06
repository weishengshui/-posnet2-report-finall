package com.chinarewards.posnet2.report.service.user;

import java.util.List;
import java.util.Map;

import com.chinarewards.posnet2.report.exception.DaoLevelException;
import com.chinarewards.posnet2.report.exception.ServiceLevelException;
import com.chinarewards.posnet2.report.vo.DetailExchRecord;
import com.chinarewards.posnet2.report.vo.EverydayRecordVo;
import com.chinarewards.posnet2.report.vo.MerchantExRecordVo;
import com.chinarewards.posnet2.report.vo.MerchantVo;

public interface ReportService {
	
	public List getTotalStatement(String sDate, String eDate, String activity_id) throws ServiceLevelException;
	
	public Map<String,EverydayRecordVo> getEverydayStatement(String sDate, String eDate, String activity_id)throws ServiceLevelException;
	
	public List<String> getExchTypes(String activity_id) throws ServiceLevelException;
	
	public Map<String,List<MerchantExRecordVo>> getMerchantTotal(String sDate, String eDate, String activity_id) throws ServiceLevelException; 
	
	public DetailExchRecord getDetailExchRecordByTokenAndActivityId(String activity_id, String token)throws ServiceLevelException;
		
	public List<MerchantVo> getMerchantVos(String activity_id)throws ServiceLevelException;
	
	public List<DetailExchRecord> getDetailExchRecords(String sDate, String eDate, String activity_id, String merchantId, int pageIndex, int pageSize)throws ServiceLevelException;
	
	public int  getDetailExchRecordCount(String sDate, String eDate, String activity_id, String merchantId)throws ServiceLevelException;
	
}
