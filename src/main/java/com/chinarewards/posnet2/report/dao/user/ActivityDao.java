package com.chinarewards.posnet2.report.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.dao.BaseDao;
import com.chinarewards.posnet2.report.domain.Activity;
import com.chinarewards.posnet2.report.exception.DaoLevelException;

public class ActivityDao extends BaseDao<Activity> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public List<Activity> findAll() throws DaoLevelException {
		logger.debug("damn u jrebel-2.1a");
		String hql = "FROM Activity a1";
		logger.trace(hql);
		
		Query q = null;
		try {
			q = getSession().createQuery(hql);
			return q.list();
		}catch(Throwable e){
			throw new DaoLevelException(e);
		}
	}
	
	

}
