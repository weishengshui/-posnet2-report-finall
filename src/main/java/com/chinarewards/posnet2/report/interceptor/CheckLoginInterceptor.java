package com.chinarewards.posnet2.report.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckLoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 6374905655008964315L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("Login")==null){
			logger.debug("Users are not logged in!");
			return Action.LOGIN;
		}
		return invocation.invoke();
	}

}
