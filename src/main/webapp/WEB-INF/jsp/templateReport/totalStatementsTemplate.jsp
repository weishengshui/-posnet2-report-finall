<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>	
<%@ include file="/WEB-INF/jsp/checklogin.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Operation</title>
<link rel="stylesheet" href="<%=ctxRootPath%>/css/ui.datepicker.css"
	type="text/css" />
<script type="text/javascript"
	src="<%=ctxRootPath%>/js/jquery/jquery-latest.js"></script>
<script type="text/javascript"
	src="<%=ctxRootPath%>/js/jquery/jquery.ui.i18n.all.js"></script>
<script type="text/javascript"
	src="<%=ctxRootPath%>/js/jquery/ui.datepicker.js"></script>
</head>


<body>
		<h2>总计报表</h2>
		<form
	action="<s:url action='totalStatements' namespace="/view/qqmeishi" />?cmd=1"
	method="post" onsubmit="return checkDate();">
<table cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">时间区间选择</td>
	</tr>
	<tr>
		<td width="280pix">&nbsp;&nbsp;&nbsp;时间从: <input id="startDate"
			name="startDate" value="<s:property value='startDate'/>" />
		<span id="startDateMsg"></span> <script charset="utf-8">
					jQuery('#startDate').datepicker({showOn: 'both', showOtherMonths: true, 
					showWeeks: true, firstDay: 1, changeFirstDay: false,
					buttonImageOnly: true, buttonImage: '<%=ctxRootPath%>/images/calendar.gif'});
				</script></td>

		<td width="280pix">&nbsp;&nbsp;&nbsp;时间到: <input id="endDate"
			name="endDate" value="<s:property value='endDate'/>" /> <span
			id="endDateMsg"></span> <script charset="utf-8">
					jQuery('#endDate').datepicker({showOn: 'both', showOtherMonths: true, 
					showWeeks: true, firstDay: 1, changeFirstDay: false,
					buttonImageOnly: true, buttonImage: '<%=ctxRootPath%>/images/calendar.gif'});
					</script></td>
	</tr>

	<tr align="center">
		<td colspan="2">
		<div align='center'><input type="submit" value="提交" /></div>
		</td>
	</tr>
</table>
</form>
<s:actionerror cssStyle="color:red" />

<!-- 总计报表 -->
<table border="1">
<!--<tr>-->
<!--<s:iterator value="total">-->
<!--	<td width='100'><s:property value="key"/>总数</td>-->
<!--</s:iterator>-->
<!--</tr>-->
<!--<tr>-->
<!--<s:iterator value="total">-->
<!--	<td width='100'><s:property value="value"/></td>-->
<!--</s:iterator>-->
<!--</tr>-->
<tr>
<s:iterator value="total">
	<td width='100'><s:property value="key"/>总数</td>
</s:iterator>
</tr>
<tr>
<s:iterator value="total">
	<td width='100'><s:property value="value"/></td>
</s:iterator>
</tr>
</table>




</body>
</html>