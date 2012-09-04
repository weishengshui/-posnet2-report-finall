package com.chinarewards.posnet2.report.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.domain.Activity;
import com.chinarewards.posnet2.report.domain.User;
import com.chinarewards.posnet2.report.service.user.LoginService;
import com.chinarewards.posnet2.report.service.user.ReportService;
import com.opensymphony.xwork2.ActionSupport;

public class ReportTemplateAction extends ActionSupport {

	private static final long serialVersionUID = -7259475053417870072L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String activity_name;
	private String startDate;
	private String endDate;
	private List total;//历史总计
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private LoginService loginService;
	private ReportService reportService;
	
	
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public List getTotal() {
		return total;
	}
	public void setTotal(List total) {
		this.total = total;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public ReportService getReportService() {
		return reportService;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	
	
	@Override
	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("User"); 
		String activity_id = user.getActivity_id();
		Activity activity = loginService.getActivityById(activity_id);
		activity_name = activity.getActivityName();
		logger.debug("activity Name = "+activity_name);
		return SUCCESS;
	}
	
	public String totalStatements() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("User"); 
		String activity_id = user.getActivity_id();
		Activity activity = loginService.getActivityById(activity_id);
		if(!cmdExists()){
			startDate = dateToString(activity.getStartDate());
			endDate = dateToString(activity.getEndDate()) ;
		}else{
		    total =  reportService.getTotalStatement(startDate, getToDate(endDate), activity_id);
		}
		logger.debug("startDate={}, endDate={}",new Object[]{startDate,endDate});
		return SUCCESS;
	}
	
	public void validateTotalStatements() {
		if(cmdExists()){
			logger.debug("validateTotalStatements cmd = 1");
			Date sDate = stringToDate(startDate);
			Date eDate = stringToDate(endDate);
			checkStartDateEndDate(sDate, eDate);
		}
	}
	
	private void checkStartDateEndDate(Date sDate, Date eDate){
		if(sDate==null || eDate==null){
			this.addActionError("请输入正确的日期！");
			return;
		}
		
		Date now = new Date();
		if(sDate.after(eDate)){
			this.addActionError("输入的时间区间不正确！");
			return;
		}
		if(sDate.after(now)){
			this.addActionError("输入的时间区间不正确！");
			return;
		}
		
	}
	
	private String getToDate(String endDate){
		Date now = new Date();
		Date eDate = stringToDate(endDate);
		if(eDate.after(now)){
			return dateToString(now);
		}
		else{
			return endDate;
		}
	}
	
	
	
	//Date to "yyyy/MM/dd"
	private String dateToString(Date date){
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private Date stringToDate(String str){
		try {
			return sdf.parse(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	private boolean cmdExists(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("cmd")!=null && request.getParameter("cmd").equals("1")){
			return true;
		}
		return false;
	}
	
}
