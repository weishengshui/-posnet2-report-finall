package com.chinarewards.posnet2.report.service.user;

import java.util.List;

import com.chinarewards.posnet2.report.domain.Activity;
import com.chinarewards.posnet2.report.domain.User;
import com.chinarewards.posnet2.report.exception.ServiceLevelException;

public interface LoginService {
	
	public List<Activity> findAllActivityList() throws ServiceLevelException;
	
	public User getUserByUsernamePwd(String username, String password)throws ServiceLevelException;
	
}
