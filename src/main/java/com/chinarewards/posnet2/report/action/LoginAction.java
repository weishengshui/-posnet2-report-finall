package com.chinarewards.posnet2.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.chinarewards.posnet2.report.service.user.LoginService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 7954451762449371728L;
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String username;
	private String password;
	// 用户输入的验证码
	private String code;

	private LoginService loginService;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() throws Exception {

		// HttpSession session = ServletActionContext.getRequest().getSession();
		// String exceptedCode = (String)session.getAttribute("code");//期望得到的验证码
		// if(code==null || !code.equalsIgnoreCase(exceptedCode)){
		// this.addActionError("验证码不正确！");
		// session.removeAttribute("code");
		// return INPUT;
		// }
		// if(loginService.isRightUsernamePwd(username, password)){
		// HttpServletRequest request = ServletActionContext.getRequest();
		// String userLoginIP = request.getRemoteAddr();
		// logger.info("!Login attempt detected. Username={}, IP={}",new
		// Object[]{username, userLoginIP});
		// session.setAttribute("Login", "OK");
		// session.setAttribute("User", username);
		// return SUCCESS;
		// }else{
		// this.addActionError("用户名或密码不正确！");
		// return INPUT;
		// }

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		username = userDetails.getUsername();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String exceptedCode = (String) session.getAttribute("code");// 期望得到的验证码
		System.out.println("exceptedCode: "+exceptedCode);
		System.out.println("code: "+code);
//		if (code == null || !code.equalsIgnoreCase(exceptedCode)) {
//			this.addActionError("验证码不正确！");
//			session.removeAttribute("code");
//			return INPUT;
//		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String userLoginIP = request.getRemoteAddr();
		logger.info("!Login attempt detected. Username={}, IP={}",
				new Object[] { username, userLoginIP });
		session.setAttribute("Login", "OK");
		session.setAttribute("User", username);
		return SUCCESS;
	}

	public void validateLogin() {
		// org.apache.commons.lang.StringUtils.isBlank(testString)，当testString为空，长度为0或者仅有空白字符串组成是，返回true；否则返回false
		// if(StringUtils.isBlank(username)){
		// this.addActionError("用户名不能为空！");
		// return;
		// }
		// if(StringUtils.isBlank(password)){
		// this.addActionError("密码不能为空！");
		// return;
		// }
	}

	// public LoginService getLoginService() {
	// return loginService;
	// }
	//
	// public void setLoginService(LoginService loginService) {
	// this.loginService = loginService;
	// }

}
