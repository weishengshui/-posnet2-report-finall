package com.chinarewards.posnet2.report.service.user;

import java.util.List;
import java.util.Map;

import com.chinarewards.posnet2.report.vo.EverydayRecordVo;

public interface ReportService {
	
	public List getTotalStatement(String sDate, String eDate, String activity_id);
	
	public Map<String,EverydayRecordVo> getEverydayStatement(String sDate, String eDate, String activity_id);
	
	public List<String> getExchTypes(String activity_id);
}
