package com.chinarewards.posnet2.report.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.dao.user.ActivityDao;
import com.chinarewards.posnet2.report.dao.user.UserDao;
import com.chinarewards.posnet2.report.domain.Activity;
import com.chinarewards.posnet2.report.domain.User;
import com.chinarewards.posnet2.report.exception.DaoLevelException;
import com.chinarewards.posnet2.report.exception.ServiceLevelException;

public class LoginServiceImpl implements LoginService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ActivityDao activityDao;
	private UserDao userDao;

	@Override
	public List<Activity> findAllActivityList() throws ServiceLevelException {
		logger.debug("service.findAllActivityList();  fuck jrebel-5.0a");
		try {
			return activityDao.findAll();
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
	}

	@Override
	public Activity getActivityById(String id) throws ServiceLevelException {
		logger.debug("loginService.getActivityById({})", id);
		Activity activity = activityDao.getById(Activity.class, id);
		return activity;
	}

	@Override
	public User getUserByUsernamePwd(String username, String password)
			throws ServiceLevelException {
		logger.debug("loginService.getUserByUsernamePwd({},{})", new Object[] {
				username, password });
		User report_User = userDao.getById(User.class, username);
		if (report_User == null) {
			return null;
		}
		return report_User;
	}

	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
