<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<%@ include file="/WEB-INF/jsp/checklogin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>report system</title>
</head>
<frameset id="mainframe" cols="20%,80%">
	<frame id="left" name="left" src="<s:url  action="reportTemplate" namespace="/view/qqmeishi" />">
	<frame id="right" name="right" src="<s:url  action="reportEmpty" namespace="/view/qqmeishi" />">
</frameset>
</html>