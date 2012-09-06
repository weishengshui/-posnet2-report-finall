<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ include file="/WEB-INF/jsp/rootPath/ctxrootPath.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><decorator:title /> - Report System - China Rewards</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=ctxRootPath%>/css/base.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=ctxRootPath%>/css/main.css" />
<decorator:head />
</head>

<body>

<div id="wrapper"><!-- Top Menu starts -->
<div id="topmenu">
<s:if test="#session.User.username==null">
		<span>&nbsp;Welcome Report System</span>
	</s:if>
	<s:else>
		<span>Hello&nbsp;<s:property value="#session.User.username" />&nbsp;Welcome Report System</span>
	</s:else>
</div>
<!-- Top Menu ends --> <!-- Body starts -->
<div id="mainbody"><decorator:body /></div>
</div>



</body>
</html>