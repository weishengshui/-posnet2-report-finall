package com.chinarewards.posnet2.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.domain.User;
import com.chinarewards.posnet2.report.service.user.ReportService;
import com.chinarewards.posnet2.report.vo.DetailExchRecord;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 验证码使用情况action
 * @author weishengshui
 *
 */
public class TokenAction extends ActionSupport {

	private static final long serialVersionUID = -1060707528649972974L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ReportService reportService;
	private boolean cmdExists = false;
	private String token;
	private DetailExchRecord detailExchRecord; 
	
	public boolean isCmdExists() {
		return cmdExists;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	public DetailExchRecord getDetailExchRecord() {
		return detailExchRecord;
	}
	public void setDetailExchRecord(DetailExchRecord detailExchRecord) {
		this.detailExchRecord = detailExchRecord;
	}
	@Override
	public String execute() throws Exception {
		logger.debug("call execute()");
		if(!cmdExists()){
			cmdExists=false;
			token = "";
			return SUCCESS;
		}else{
		if(token==null || token.trim().length()==0){
			this.addActionError("请输入验证码！");
			return INPUT;
		}
		cmdExists = true;
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("User"); 
		String activity_id = user.getActivity_id();
		
		detailExchRecord = reportService.getDetailExchRecordByTokenAndActivityId(activity_id, token);
		logger.debug("detailExchRecord="+detailExchRecord);
		return SUCCESS;
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
