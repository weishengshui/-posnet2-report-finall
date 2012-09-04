package com.chinarewards.posnet2.report.dao.user;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.dao.BaseDao;
import com.chinarewards.posnet2.report.domain.User;
import com.chinarewards.posnet2.report.exception.DaoLevelException;

public class ReportDao  extends BaseDao<Object>{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public List getTotalStatement(String sDate, String eDate, String activity_id) {
		
		logger.debug("call getTotalStatement({}, {}, {})",new Object[]{sDate, eDate,activity_id});
		StringBuffer sql = new StringBuffer();
		sql.append("select  m1.exchangeType , COUNT(meishi.id)  from QQMeishiXaction meishi, Merchant m1, Activitymerchant am1 ");
		sql = concatLimitTime(sql, "meishi.ts", sDate, eDate);
		sql.append(" and   m1.id=am1.merchant_id and meishi.posid = am1.posid and meishi.xactResultCode = '0' and am1.activity_id=:activity_id GROUP BY m1.exchangeType DESC");
		logger.trace(sql.toString());
		try {
			SQLQuery q = getSession().createSQLQuery(sql.toString());
			q.setParameter("activity_id", activity_id);
			return q.list();
			//			List list = q.list();
//			Map<String,String> map = new TreeMap<String, String>();
//			for(Iterator<Object[]> it = list.iterator();it.hasNext();){
//				Object[] kv = (Object[])it.next();
//				String k = kv[0].toString();
//				String v = kv[1].toString();
//				map.put(k, v);
//			}
//			return map;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private StringBuffer concatLimitTime(StringBuffer sqlBuf,
			String timeFieldName, String fromDate, String toDate) {

		if (fromDate != null && !fromDate.isEmpty()) {

			sqlBuf.append(" where ").append(timeFieldName).append(" >= '")
					.append(getMysqlDateStr(true, fromDate)).append("'");

		}

		if (toDate != null && !toDate.isEmpty()) {
			sqlBuf.append(" and ").append(timeFieldName).append(" <= '")
					.append(getMysqlDateStr(false, toDate)).append("'");
		}

		System.out.println("after concatLimitTime sql is " + sqlBuf.toString());

		return sqlBuf;

	}
	
private String getMysqlDateStr(boolean isHeadOfMonth, String dateStr) {
		
		String[] ds = dateStr.split("/");
		String newdate = ds[0].concat("-").concat(ds[1]).concat("-")
				.concat(ds[2]);

		String res;
		if (isHeadOfMonth) {
			res = newdate.concat(" 00:00:00");
		} else {
			res = newdate.concat(" 23:59:59");
		}

		return res;
	}
	
}
