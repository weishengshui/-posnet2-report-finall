package com.chinarewards.posnet2.report.service.user;

import java.util.List;

public interface ReportService {
	
	public List getTotalStatement(String sDate, String eDate, String activity_id);
}
