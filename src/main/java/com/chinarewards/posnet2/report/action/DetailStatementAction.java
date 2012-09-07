package com.chinarewards.posnet2.report.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.domain.Activity;
import com.chinarewards.posnet2.report.domain.User;
import com.chinarewards.posnet2.report.service.user.LoginService;
import com.chinarewards.posnet2.report.service.user.ReportService;
import com.chinarewards.posnet2.report.vo.DetailExchRecord;
import com.chinarewards.posnet2.report.vo.MerchantVo;
import com.opensymphony.xwork2.ActionSupport;

public class DetailStatementAction extends ActionSupport {

	private static final long serialVersionUID = -5862735638011964524L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ReportService reportService;
	private LoginService loginService;
	private List<MerchantVo> merchantVos;
	private String merchantId;
	private String startDate;
	private String endDate;
	private List<DetailExchRecord> detailExchRecords;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private boolean hasData;
	private boolean hasError=true;
	
	/**
	 * 分页信息
	 * 
	 */
	private int offset=1;//当前页
	private int pageSize;//一页多少条记录
	private int pageCount;//一共多少页
	private int allCount;//所有的记录数
	private int pre;
	private int next;
	

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public List<MerchantVo> getMerchantVos() {
		return merchantVos;
	}

	public void setMerchantVos(List<MerchantVo> merchantVos) {
		this.merchantVos = merchantVos;
	}

	public boolean isHasError() {
		return hasError;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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
	public List<DetailExchRecord> getDetailExchRecords() {
		return detailExchRecords;
	}
	
	public void setDetailExchRecords(List<DetailExchRecord> detailExchRecords) {
		this.detailExchRecords = detailExchRecords;
	}
	public boolean isHasData() {
		return hasData;
	}

	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	
	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	@Override
	public String execute() throws Exception {
		logger.debug("call execute()");
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("User"); 
		String activity_id = user.getActivity_id();
		Activity activity = loginService.getActivityById(activity_id);
		if (!cmdExists()) {
			startDate = dateToString(activity.getStartDate());
			endDate = dateToString(activity.getEndDate());
			merchantVos = reportService.getMerchantVos(activity_id);
			return SUCCESS;
		} else {
			Date sDate = stringToDate(startDate);
			Date eDate = stringToDate(endDate);
			if(sDate==null || eDate==null){
				this.addActionError("请输入正确的日期！");
				hasError = true;
				return INPUT;
			}
			
			Date now = new Date();
			if(sDate.after(eDate)){
				this.addActionError("输入的时间区间不正确！");
				hasError = true;
				return INPUT;
			}
			if(sDate.after(now)){
				this.addActionError("输入的时间区间不正确！");
				hasError = true;
				return INPUT;
			}
		}
		if(this.hasFieldErrors()){
			hasError = true;
			return INPUT;
		}
		hasError = false;
		if(merchantId==null || merchantId.trim().length()==0){
			hasData= false;
			return SUCCESS;
		}else{
			allCount = reportService.getDetailExchRecordCount(startDate, getToDate(endDate), activity_id, merchantId);
			if(allCount==0){
				hasData = false;
				return SUCCESS;
			}
			pageSize = 20;
			pageCount = allCount%pageSize==0? allCount/pageSize:(allCount/pageSize+1);
			logger.debug("offset="+offset);
			if(offset<1){
				offset=1;
			}
			if(offset>pageCount){
				offset=pageCount;
			}
			pre = (offset-1<1)?1:(offset-1);
			next = (offset+1>pageCount)?pageCount:(offset+1);
			detailExchRecords = reportService.getDetailExchRecords(startDate, getToDate(endDate), activity_id, merchantId, (offset-1)*pageSize, pageSize);
			if(detailExchRecords==null || detailExchRecords.size()==0){
				hasData = false;
				return SUCCESS;
			}else{
				hasData = true;
				return SUCCESS;
			}
		}
		
	}

	private boolean cmdExists() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request.getParameter("cmd") != null
				&& request.getParameter("cmd").equals("1")) {
			return true;
		}
		return false;
	}
	
	private Date stringToDate(String str){
		try {
			return sdf.parse(str);
		} catch (Exception e) {
			return null;
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
}
