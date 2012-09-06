package com.chinarewards.posnet2.report.service.user;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.chinarewards.posnet2.report.dao.user.ReportDao;
import com.chinarewards.posnet2.report.exception.DaoLevelException;
import com.chinarewards.posnet2.report.exception.ServiceLevelException;
import com.chinarewards.posnet2.report.vo.DetailExchRecord;
import com.chinarewards.posnet2.report.vo.EverydayRecordVo;
import com.chinarewards.posnet2.report.vo.MerchantExRecordVo;
import com.chinarewards.posnet2.report.vo.MerchantVo;

public class ReportServiceImpl implements ReportService {
	
	private ReportDao reportDao; 
	@Override
	public List getTotalStatement(String sDate, String eDate, String activity_id) throws ServiceLevelException{
		
		try {
			return reportDao.getTotalStatement(sDate, eDate, activity_id);
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
		
	}
	
	@Override
	public Map<String,EverydayRecordVo> getEverydayStatement(String sDate, String eDate,
			String activity_id)  throws ServiceLevelException{
		
		try {
			return reportDao.getEverydayStatement(sDate, eDate, activity_id);
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
		
	}
	
	@Override
	public Map<String, List<MerchantExRecordVo>> getMerchantTotal(String sDate, String eDate, String activity_id)   throws ServiceLevelException{
		
		try {
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
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
		
	}
	
	@Override
	public DetailExchRecord getDetailExchRecordByTokenAndActivityId(
			String activity_id, String token) throws ServiceLevelException{
		
		try {
			return reportDao.getDetailExchRecordByTokenAndActivityId(activity_id, token);
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
		
	}
	
	@Override
	public List<String> getExchTypes(String activity_id) throws ServiceLevelException {
		
		try {
			return reportDao.getExchangeTypes(activity_id);
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
		
	}
	
	@Override
	public List<MerchantVo> getMerchantVos(String activity_id)
			throws ServiceLevelException {
		
		try {
			return reportDao.getMerchantVos(activity_id);
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
		
	}
	@Override
	public List<DetailExchRecord> getDetailExchRecords(String sDate,
			String eDate, String activity_id, String merchantId, int pageIndex,
			int pageSize)throws ServiceLevelException {
		
		try {
			return  reportDao.getDetailExchRecords(sDate, eDate, activity_id, merchantId, pageIndex, pageSize);
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
		
	}
	
	@Override
	public int getDetailExchRecordCount(String sDate, String eDate,
			String activity_id, String merchantId) throws ServiceLevelException {

		try {
			return reportDao.getDetailExchRecordCount(sDate, eDate, activity_id, merchantId);
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
		
	}
	
	public ReportDao getReportDao() {
		
		return reportDao;
	}
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	

}
