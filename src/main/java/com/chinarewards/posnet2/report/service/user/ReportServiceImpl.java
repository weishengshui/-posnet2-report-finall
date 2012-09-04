package com.chinarewards.posnet2.report.service.user;

import java.util.List;
import java.util.Map;

import com.chinarewards.posnet2.report.dao.user.ReportDao;
import com.chinarewards.posnet2.report.vo.EverydayRecordVo;

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
