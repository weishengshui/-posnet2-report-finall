package com.chinarewards.posnet2.report.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public class ReportTemplateAction extends ActionSupport {

	private static final long serialVersionUID = -7259475053417870072L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String activity_name;
	
	
	
	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
}
