package com.chinarewards.posnet2.report.service.user;

import java.util.List;

import com.chinarewards.posnet2.report.dao.user.ReportDao;

public class ReportServiceImpl implements ReportService {
	
	private ReportDao reportDao; 
	@Override
	public List getTotalStatement(String sDate, String eDate, String activity_id) {
		
		return reportDao.getTotalStatement(sDate, eDate, activity_id);
	}
	
	public ReportDao getReportDao() {
		return reportDao;
	}
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	

}
