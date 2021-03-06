package com.chinarewards.posnet2.report.action;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.domain.Activity;
import com.chinarewards.posnet2.report.domain.User;
import com.chinarewards.posnet2.report.service.user.LoginService;
import com.chinarewards.posnet2.report.service.user.ReportService;
import com.chinarewards.posnet2.report.vo.EverydayRecordVo;
import com.chinarewards.posnet2.report.vo.MerchantExRecord;
import com.chinarewards.posnet2.report.vo.MerchantExRecordVo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 总计报表的action
 * @author weishengshui
 *
 */
public class ReportTemplateAction extends ActionSupport {

	private static final long serialVersionUID = -7259475053417870072L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String activity_name;
	private String startDate;
	private String endDate;
	private List total;//历史总计
	private boolean totalExists=false;//历史总计是否有数据
	private Map<String, EverydayRecordVo> everydayTotal;//每日报表
	private List<String> everydayGraphs;//每日报表的统计图表
	private boolean everydayTotalExists = false;//每日总计是否有数据
	private Map<String,List<MerchantExRecordVo>> merchantTotal;//商户总计报表
	private boolean merchantTotalExists = false;
	private List<String> merchantTotalGraph;
	private List<String> exchTypes;
	private boolean cmdExists = false;
	
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
	
	public boolean isTotalExists() {
		return totalExists;
	}
	public void setTotalExists(boolean totalExists) {
		this.totalExists = totalExists;
	}
	
	public Map<String, EverydayRecordVo> getEverydayTotal() {
		return everydayTotal;
	}
	public void setEverydayTotal(Map<String, EverydayRecordVo> everydayTotal) {
		this.everydayTotal = everydayTotal;
	}
	public boolean isEverydayTotalExists() {
		return everydayTotalExists;
	}
	
	public List<String> getMerchantTotalGraph() {
		return merchantTotalGraph;
	}
	public void setMerchantTotalGraph(List<String> merchantTotalGraph) {
		this.merchantTotalGraph = merchantTotalGraph;
	}
	public List<String> getExchTypes() {
		return exchTypes;
	}
	public void setExchTypes(List<String> exchTypes) {
		this.exchTypes = exchTypes;
	}
	
	public boolean isCmdExists() {
		return cmdExists;
	}
	public List<String> getEverydayGraphs() {
		return everydayGraphs;
	}
	public void setEverydayGraphs(List<String> everydayGraphs) {
		this.everydayGraphs = everydayGraphs;
	}
	public void setEverydayTotalExists(boolean everydayTotalExists) {
		this.everydayTotalExists = everydayTotalExists;
	}
	
	public Map<String, List<MerchantExRecordVo>> getMerchantTotal() {
		return merchantTotal;
	}
	public void setMerchantTotal(Map<String, List<MerchantExRecordVo>> merchantTotal) {
		this.merchantTotal = merchantTotal;
	}
	public boolean isMerchantTotalExists() {
		return merchantTotalExists;
	}
	public void setMerchantTotalExists(boolean merchantTotalExists) {
		this.merchantTotalExists = merchantTotalExists;
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
		activity_name = activity.getActivityName();
		final Font font = new Font("宋体", Font.PLAIN, 15);
		if(!cmdExists()){
			logger.debug("validateTotalStatements cmd = 1");
			startDate = dateToString(activity.getStartDate());
			endDate = dateToString(activity.getEndDate()) ;
		}else {
			Date sDate = stringToDate(startDate);
			Date eDate = stringToDate(endDate);
//			checkStartDateEndDate(sDate, eDate);
			if(sDate==null || eDate==null){
				this.addActionError("请输入正确的日期！");
				cmdExists = false;
				return INPUT;
			}
			
			Date now = new Date();
			if(sDate.after(eDate)){
				this.addActionError("输入的时间区间不正确！");
				cmdExists = false;
				return INPUT;
			}
			if(sDate.after(now)){
				this.addActionError("输入的时间区间不正确！");
				cmdExists = false;
				return INPUT;
			}
		
			exchTypes = reportService.getExchTypes(activity_id);
		    //历史总计
			total =  reportService.getTotalStatement(startDate, getToDate(endDate), activity_id);
		    if(total!=null && total.size()>0){
		    	totalExists = true;
		    }
		    
		    //每日报表
		    everydayTotal = reportService.getEverydayStatement(startDate, getToDate(endDate), activity_id);
		    if(everydayTotal != null && everydayTotal.size()>0){
		    	everydayTotalExists = true;
		    	int recordsCount = everydayTotal.size();
		    	everydayGraphs = new ArrayList<String>();
		    	int mod = 0;
				int tmpIndex = 0;
				int index = 0;
				if(recordsCount>10){
					mod = recordsCount%10;
					index = recordsCount/10;
				}
				else{
					index = 1;
				}
				tmpIndex = index +1;
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		    	DefaultCategoryDataset amountDataset = new DefaultCategoryDataset();
		    	List<String> days = new ArrayList<String>();
		    	Set<String> dayset = everydayTotal.keySet();
		    	
		    	for(Iterator<String> it = dayset.iterator();it.hasNext();){
		    		days.add(it.next());
		    	}
				for(int dayIndex=0; dayIndex < recordsCount;){
		    		if(dayIndex>=recordsCount){
		    			
		    		}else{
					double[] tmpExCount = new double[exchTypes.size()+1];
		    		if(mod>0){
						index = tmpIndex;
						mod--;
					}
					String day = "";
					String sday = days.get(dayIndex);
					EverydayRecordVo vo = everydayTotal.get(sday);
					day = sday.substring(5, 10);
					List<Integer> daycount = vo.getCount();
					for (int countIndex = 0; countIndex < daycount
							.size(); countIndex++) {
							tmpExCount[countIndex] +=  daycount.get(countIndex);
					}
					tmpExCount[exchTypes.size()] += vo.getAmount();
					if (index > 1) {
						String eday = days.get(dayIndex
								+ index - 1);
						if (sday.subSequence(5, 7).equals(
								eday.subSequence(5, 7))) {
							day += "-" + eday.substring(8, 10);
						} else {
							day += "-" + eday.substring(5, 10);
						}
						vo = everydayTotal.get(eday);
						daycount = vo.getCount(); 
						for (int countIndex = 0; countIndex < daycount
								.size(); countIndex++) {
								tmpExCount[countIndex] +=  daycount.get(countIndex);
						}
						tmpExCount[exchTypes.size()] += vo.getAmount();
					}
					for (int k = 1; k < index - 1; k++) {
						String tday = days.get(dayIndex
								+ k);
						vo = everydayTotal.get(tday);
						daycount = vo.getCount();
						for (int countIndex = 0; countIndex < daycount
								.size(); countIndex++) {
								tmpExCount[countIndex] += daycount.get(countIndex);
						}
						tmpExCount[exchTypes.size()] += vo.getAmount();
					}
					for (int k = 0; k < exchTypes.size()+1; k++) {
						if(k==exchTypes.size()){
							amountDataset.addValue(tmpExCount[k],"",day);							
						}else{
							dataset.addValue(tmpExCount[k],
									exchTypes.get(k), day);
						}
					}
					dayIndex += index;
					if(mod==0){
						index = tmpIndex-1;
					}
					if (recordsCount - dayIndex < index) {
						index = recordsCount - dayIndex;
					}

				}
		    	}
				
				JFreeChart chart = ChartFactory.createLineChart(activity_name+"----按日统计", "日期", "交易数量", dataset, PlotOrientation.VERTICAL, true, false, false);
				setLineChartProperties(chart, font);
				String filename= ServletUtilities.saveChartAsJPEG(chart, 800, 600, session);
				everydayGraphs.add(ServletActionContext.getServletContext().getContextPath()+"/servlet/displayChart?filename="+filename);
				chart = ChartFactory.createLineChart(activity_name+"----按日统计","日期","消费金额",amountDataset,PlotOrientation.VERTICAL,false,false,false);
				setBarChartProperties(chart, font);
				filename= ServletUtilities.saveChartAsJPEG(chart, 800, 600, session);
				everydayGraphs.add(ServletActionContext.getServletContext().getContextPath()+"/servlet/displayChart?filename="+filename);
		    }
		    
		    //商户总计报表
		    merchantTotal = reportService.getMerchantTotal(startDate, getToDate(endDate), activity_id);
		    if(merchantTotal!=null && merchantTotal.size()>0){
		    	merchantTotalExists=true;
		    	merchantTotalGraph = new ArrayList<String>();
		    	for(String exType:exchTypes){
//		    		int countSum=0;
//		    		double amountSum=0;
//		    		int posSum = 0;
		    		DefaultCategoryDataset dataset = new DefaultCategoryDataset();//交易数量
			    	DefaultCategoryDataset amountDataset = new DefaultCategoryDataset();//消费金额
		    		List<MerchantExRecordVo> vos = merchantTotal.get(exType);
		    		if(vos!=null&& vos.size()>0){
		    			for(MerchantExRecordVo vo:vos){
		    				MerchantExRecord merchantExRecord = vo.getMerchantExRecord();
		    				String shopName = merchantExRecord.getShopName();
		    				dataset.addValue(merchantExRecord.getExCount(), shopName, shopName);
		    				amountDataset.addValue(merchantExRecord.getAmount(), shopName, shopName);
		    			}
		    			JFreeChart chart = ChartFactory.createBarChart3D(activity_name+"("+exType+")", "", "交易数量", dataset, PlotOrientation.VERTICAL, false, false, false);
		    			setBarChartProperties(chart, font);
		    			String filename = ServletUtilities.saveChartAsJPEG(chart, 800, 600, session);
		    			merchantTotalGraph.add(ServletActionContext.getServletContext().getContextPath()+"/servlet/displayChart?filename="+filename);
		    			if(!exType.equals("礼品")){
		    				chart = ChartFactory.createBarChart3D(activity_name+"("+exType+")", "", "消费金额", amountDataset, PlotOrientation.VERTICAL, false, false, false);
			    			setBarChartProperties(chart, font);
			    			filename = ServletUtilities.saveChartAsJPEG(chart, 800, 600, session);
			    			merchantTotalGraph.add(ServletActionContext.getServletContext().getContextPath()+"/servlet/displayChart?filename="+filename);
		    			}
		    			
		    		}
		    	}
		    	
		    	
		    }
		    
		}
		logger.debug("startDate={}, endDate={}",new Object[]{startDate,endDate});
		return SUCCESS;
	}
	
	
	
//	public void validateTotalStatements() {
//		if(cmdExists()){
//			logger.debug("validateTotalStatements cmd = 1");
//			Date sDate = stringToDate(startDate);
//			Date eDate = stringToDate(endDate);
////			checkStartDateEndDate(sDate, eDate);
//			if(sDate==null || eDate==null){
//				this.addActionError("请输入正确的日期！");
//				return ;
//			}
//			
//			Date now = new Date();
//			if(sDate.after(eDate)){
//				this.addActionError("输入的时间区间不正确！");
//				return ;
//			}
//			if(sDate.after(now)){
//				this.addActionError("输入的时间区间不正确！");
//				return ;
//			}
//			
//		}
//	}
	
//	private void checkStartDateEndDate(Date sDate, Date eDate){
//		if(sDate==null || eDate==null){
//			this.addActionError("请输入正确的日期！");
//			return;
//		}
//		
//		Date now = new Date();
//		if(sDate.after(eDate)){
//			this.addActionError("输入的时间区间不正确！");
//			return;
//		}
//		if(sDate.after(now)){
//			this.addActionError("输入的时间区间不正确！");
//			return;
//		}
//		
//	}
	
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
			return cmdExists=true;
		}
		return cmdExists=false;
	}
	
	private void setLineChartProperties(JFreeChart chart, Font font) {
		CategoryPlot plot = chart.getCategoryPlot();
		chart.getTitle().setFont(font);
		Axis x_Axis = plot.getDomainAxis();
		x_Axis.setLabelFont(font);
		x_Axis.setTickLabelFont(font);
		CategoryAxis categoryAxis = plot.getDomainAxis();
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 12));
		plot.setDomainAxis(categoryAxis);
		Axis y_Axis = plot.getRangeAxis();
		y_Axis.setLabelFont(font);
		y_Axis.setTickLabelFont(font);
		y_Axis.setLabelAngle(Math.PI * 0.5);
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(font);
	}
	
	private void setBarChartProperties(JFreeChart chart, Font font) {
		chart.getTitle().setFont(font);
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		Axis x_axis = categoryPlot.getDomainAxis();
		x_axis.setLabelFont(font);
		x_axis.setTickLabelFont(font);
		
		CategoryAxis domainAxis = categoryPlot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/5));
		categoryPlot.setDomainAxis(domainAxis);
		
		Axis y_axis = categoryPlot.getRangeAxis();
		y_axis.setLabelFont(font);
		y_axis.setTickLabelFont(font);
		y_axis.setLabelAngle(Math.PI*0.5);
		}
}
