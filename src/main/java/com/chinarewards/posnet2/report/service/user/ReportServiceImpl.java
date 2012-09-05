package com.chinarewards.posnet2.report.service.user;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.chinarewards.posnet2.report.dao.user.ReportDao;
import com.chinarewards.posnet2.report.vo.EverydayRecordVo;
import com.chinarewards.posnet2.report.vo.MerchantExRecord;
import com.chinarewards.posnet2.report.vo.MerchantExRecordVo;

public class ReportServiceImpl implements ReportService {
	
	private ReportDao reportDao; 
	@Override
	public List getTotalStatement(String sDate, String eDate, String activity_id) {
		
		return reportDao.getTotalStatement(sDate, eDate, activity_id);
	}
	
	@Override
	public Map<String,EverydayRecordVo> getEverydayStatement(String sDate, String eDate,
			String activity_id) {
		
		return reportDao.getEverydayStatement(sDate, eDate, activity_id);
	}
	
	@Override
	public Map<String, List<MerchantExRecordVo>> getMerchantTotal(String sDate, String eDate, String activity_id) {
		List<String> exTypes = getExchTypes(activity_id);
		if(exTypes==null|| exTypes.size()==0){
			return null;
		}
		Map<String, List<MerchantExRecordVo>> merchantTotalMap = new TreeMap<String, List<MerchantExRecordVo>>();
		for(String exType:exTypes){
			merchantTotalMap.put(exType, reportDao.getMerchantTotalByExType(sDate, eDate, activity_id, exType));
		}
		if(merchantTotalMap.size()==0){
			return null;
		}
		return merchantTotalMap;
	}
	
	@Override
	public List<String> getExchTypes(String activity_id) {
		return reportDao.getExchangeTypes(activity_id);
	}
	
	public ReportDao getReportDao() {
		return reportDao;
	}
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	

}
