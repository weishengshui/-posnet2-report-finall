package com.chinarewards.posnet2.report.dao.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.dao.BaseDao;
import com.chinarewards.posnet2.report.vo.DayAmount;
import com.chinarewards.posnet2.report.vo.DayCount;
import com.chinarewards.posnet2.report.vo.EverydayRecordVo;
import com.chinarewards.posnet2.report.vo.Weekend;

public class ReportDao extends BaseDao<Object> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public List getTotalStatement(String sDate, String eDate, String activity_id) {

		logger.debug("call getTotalStatement({}, {}, {})", new Object[] {
				sDate, eDate, activity_id });
		StringBuffer sql = new StringBuffer();
		sql.append("select  m1.exchangeType , COUNT(meishi.id)  from QQMeishiXaction meishi, Merchant m1, Activitymerchant am1 ");
		sql = concatLimitTime(sql, "meishi.ts", sDate, eDate);
		sql.append(" and   m1.id=am1.merchant_id and meishi.posid = am1.posid and meishi.xactResultCode = '0' and am1.activity_id=:activity_id GROUP BY m1.exchangeType DESC");
		logger.trace(sql.toString());
		try {
			SQLQuery q = getSession().createSQLQuery(sql.toString());
			q.setParameter("activity_id", activity_id);
			return q.list();
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public Map<String,EverydayRecordVo> getEverydayStatement(String sDate, String eDate,
			String activity_id) {
		logger.debug("call getEverydayStatement({}, {}, {})", new Object[] {
				sDate, eDate, activity_id });
		StringBuffer sql = null;
		List<String> exchangeTypes = getExchangeTypes(activity_id);
		if(exchangeTypes==null|| exchangeTypes.size()==0){
			return null;
		}
		int etSize = exchangeTypes.size();
		Map<String,EverydayRecordVo> everydayRecords = new TreeMap<String, EverydayRecordVo>();
		Calendar d1 = stringFormatDateToCalendar(sDate);
		Calendar d2 = stringFormatDateToCalendar(eDate);
		int bet = getDaysBetween(d1, d2);
		String[] weekday = { "", "星期日", "星期一", "星期二", "星期三", "星期四",
				"星期五", "星期六" };
		for(int i=0;i<bet+1;i++){
			String tmpday = calendarToStringFormatDate(d1);
			int dayOfWeek = d1.get(Calendar.DAY_OF_WEEK);
			int weekOfYear = d1.get(Calendar.WEEK_OF_YEAR);
			String dayOfWeekString="";
			Weekend weekend = Weekend.OTHER;
			switch (dayOfWeek) {
			case Calendar.SUNDAY:
				dayOfWeekString = weekday[Calendar.SUNDAY];
				weekend = Weekend.SUNDAY;
				break;
			case Calendar.MONDAY:
				dayOfWeekString = weekday[Calendar.MONDAY];
				break;
			case Calendar.TUESDAY:
				dayOfWeekString = weekday[Calendar.TUESDAY];
				break;
			case Calendar.WEDNESDAY:
				dayOfWeekString = weekday[Calendar.WEDNESDAY];
				break;
			case Calendar.THURSDAY:
				dayOfWeekString = weekday[Calendar.THURSDAY];
				break;
			case Calendar.FRIDAY:
				dayOfWeekString = weekday[Calendar.FRIDAY];
				break;
			case Calendar.SATURDAY:
				weekend = Weekend.SATDAY;
				dayOfWeekString = weekday[Calendar.SATURDAY];
				break;
			}
			dayOfWeekString += " / "+weekOfYear;
			List<Integer> counts = new ArrayList<Integer>();
			for(int j=0;j<etSize;j++){
				counts.add(0);
			}
			EverydayRecordVo vo = new EverydayRecordVo();
			vo.setTime(tmpday);
			vo.setWeekend(weekend);
			vo.setWeekday(dayOfWeekString);
			vo.setCount(counts);
			vo.setAmount(0.0);
			everydayRecords.put(tmpday, vo);
			d1.add(Calendar.DATE, 1);
		}
		
		for(int i=0;i<etSize+1;i++){
			if(i==etSize){
				sql = new StringBuffer();
				sql.append("select date_format(mshi1.ts,'%Y/%m/%d') as 'time' , sum(mshi1.consumeAmount) as 'amount'  from QQMeishiXaction  mshi1,Activitymerchant am, Merchant m  ");
				sql = concatLimitTime(sql, "mshi1.ts", sDate, eDate);
				sql.append(" and am.merchant_id=m.id  and am.activity_id=:activity_id  " +
						" and  mshi1.xactResultCode = '0' and  mshi1.posid  = am.posid and m.exchangeType!=" +
						":exchangeType group by year(mshi1.ts),month(mshi1.ts),day(mshi1.ts)");
				logger.trace(sql.toString());
				SQLQuery q = getSession().createSQLQuery(sql.toString()).addScalar("time", Hibernate.STRING).addScalar("amount", Hibernate.DOUBLE);
				q.setParameter("exchangeType", "礼品");
				q.setParameter("activity_id", activity_id);
				q.setResultTransformer(Transformers.aliasToBean(DayAmount.class));
				List<DayAmount> amountList = q.list();
				for(DayAmount dayAmount:amountList){
					EverydayRecordVo vo = everydayRecords.get(dayAmount.getTime());
					vo.setAmount(dayAmount.getAmount());
				}
			}else{
				String exchType = (String)exchangeTypes.get(i);
				sql = new StringBuffer();
				sql.append("select date_format(mshi1.ts,'%Y/%m/%d') as 'time' , count(*) as 'count' from QQMeishiXaction  mshi1,Activitymerchant am, Merchant m  ");
				sql = concatLimitTime(sql, "ts", sDate, eDate);
				sql.append(" and am.merchant_id=m.id and m.exchangeType=:exchangeType and am.activity_id=:activity_id and  " +
						"mshi1.xactResultCode='0' and  mshi1.posid  = am.posid group by year(mshi1.ts),month(mshi1.ts),day(mshi1.ts)");
				logger.trace(sql.toString());
				SQLQuery q = getSession().createSQLQuery(sql.toString()).addScalar("time", Hibernate.STRING).addScalar("count", Hibernate.INTEGER);
				q.setParameter("exchangeType", exchType);
				q.setParameter("activity_id", activity_id);
				q.setResultTransformer(Transformers.aliasToBean(DayCount.class));
				List<DayCount> dayCounts = q.list();
				for(DayCount dayCount:dayCounts){
					EverydayRecordVo vo = everydayRecords.get(dayCount.getTime());
					vo.getCount().remove(i);
					vo.getCount().add(i, dayCount.getCount());
				}
			}
		}
		return everydayRecords;
	}
	
	public List<String> getExchangeTypes(String activity_id){
		logger.debug("call getExchangeTypes({})", 
				activity_id );
		StringBuffer sql = new StringBuffer();
		sql.append("select m.exchangeType as 'exchangeType' from Merchant m, Activitymerchant am where am.activity_id=:activity_id and am.merchant_id=m.id group  by m.exchangeType  desc");
		logger.trace(sql.toString());
		try {
			SQLQuery q = getSession().createSQLQuery(sql.toString());
			q.setParameter("activity_id", activity_id);
			return q.addScalar("exchangeType", Hibernate.STRING).list();
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
	
	private Calendar stringFormatDateToCalendar(String stringDate) {

		String[] ds = stringDate.split("/");
		int year = Integer.valueOf(ds[0]).intValue();
		int month = Integer.valueOf(ds[1]).intValue() - 1;
		int day = Integer.valueOf(ds[2]).intValue();

		Calendar calendar = new GregorianCalendar(year, month, day);

		return calendar;

	}

	private String calendarToStringFormatDate(Calendar calendar) {

		int yearto = calendar.get(Calendar.YEAR);
		int monthto = calendar.get(Calendar.MONTH) + 1;
		int dayto = calendar.get(Calendar.DAY_OF_MONTH);

		String monthStr = null;
		if (monthto < 10) {
			monthStr = "0" + monthto;
		} else {
			monthStr = "" + monthto;
		}

		String dayStr = null;
		if (dayto < 10) {
			dayStr = "0" + dayto;
		} else {
			dayStr = "" + dayto;
		}

		String stringDate = new String(yearto + "/" + monthStr + "/" + dayStr);

		return stringDate;

	}
	
	private int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
				- d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {
			d1 = (java.util.Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}
	

}
