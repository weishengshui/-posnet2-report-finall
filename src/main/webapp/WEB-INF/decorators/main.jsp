<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ include file="/WEB-INF/jsp/rootPath/ctxrootPath.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><decorator:title /> - Report System - China Rewards</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=ctxRootPath%>/css/base.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=ctxRootPath%>/css/main.css" />
<decorator:head />
<style type="text/css">
	div#div1 {
	background-image: url('<%=ctxRootPath %>/images/report_background.jpg');
		background-position:center;
		background-repeat:no-repeat;
	position: fixed;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	z-index: -1;
}
div#div1 > img{
	height: 100%;
	border: 0;
</style>
</head>

<body  style="background-image: url('<%=ctxRootPath %>/images/report_background.jpg');
		background-repeat:repeat-y; ;
		background-attachment: fixed;">
<div id="div1">
<img src="<%=ctxRootPath %>/images/report_background.jpg"/>
</div>		
<div id="wrapper">

<!-- Top Menu starts -->
<div id="topmenu">
<img src="<%=ctxRootPath%>/images/home_16x16.gif" border="0" />&nbsp;<a href="<s:url action='logout' namespace="/pub" />">登出</a>&nbsp;|&nbsp;<font color='red'>请点击菜单</font>
</div>
<!-- Top Menu ends -->

<!-- Body starts -->
<div id="mainbody">
<decorator:body />
</div>
<!-- Body ends -->

<!-- Footer starts -->
<div id="footer">
Report System | China Rewards
</div>
<!-- Footer ends -->

</div>



</body>
</html>