<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/checklogin.jsp"%>

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
	<h2>明细报表查询条件选择</h2>
	<form
		action="<s:url action='detailStatementsResult' namespace="/view/qqmeishi" />?cmd=1"
		target="buttom" method="post">
	<table cellpadding="0" cellspacing="0">
	
		<tr>
			<td colspan="2">时间区间选择</td>
			<td>商户选择</td>
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
			<td>
			<s:if test="merchantVos==null || merchantVos.size()==0">
				<span>没有商户</span>
			</s:if>
			<s:else>
				<select name="merchantId">
					<option value="all" selected="selected">所有商户</option>
					<s:iterator value="merchantVos" id="vo">
						<option value="<s:property value='#vo.id'/>"><s:property value="#vo.shopName"/></option>
					</s:iterator>
				</select>
			</s:else>
			</td>
	
		</tr>
	
		<tr align="center">
			<td colspan="3">
			<div align='center'><input type="submit" value="提交" /></div>
			</td>
		</tr>
	</table>
	</form>
	
	<br>

</body>

</html>