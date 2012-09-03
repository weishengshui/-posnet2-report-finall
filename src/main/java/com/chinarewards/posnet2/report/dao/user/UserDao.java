package com.chinarewards.posnet2.report.dao.user;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.dao.BaseDao;
import com.chinarewards.posnet2.report.domain.User;
import com.chinarewards.posnet2.report.exception.DaoLevelException;

public class UserDao  extends BaseDao<User>{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public User getUserByUsername(String username) throws DaoLevelException {
		logger.debug("call getUserByUsername()");
		String hsql = "from User u where u.username=:username ";
		logger.trace(hsql);
		try {
			Query q = getSession().createQuery(hsql);
			q.setParameter("username", username);
			return (User)q.uniqueResult();
		} catch (Throwable e) {
			throw new DaoLevelException(e);
		}
	}
}
