<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			name="startDate" value="<s:property value='startDate'/>" /> <span
			id="startDateMsg"></span> <script charset="utf-8">
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
<s:if test="totalExists">
	<table border="1">
		<tr>
			<s:iterator value="total" status="id">
				<td width='80'><s:property value="total[#id.index][0]" />总数</td>
			</s:iterator>
		</tr>
		<tr>
			<s:iterator value="total" status="id">
				<td width='80'><s:property value="total[#id.index][1]" /></td>
			</s:iterator>
		</tr>
	</table>
</s:if>
<s:else>

<span>总计报表没有数据显示</span>
</s:else>
<!--<s:iterator value="total" status="id">-->
<!--	<s:iterator value="total[#id.index]">-->
<!--		<s:property />-->
<!--	</s:iterator><br>-->
<!--</s:iterator>-->

<br>
<h2>每日报表</h2>

<s:if test="everydayTotalExists">
<table border="1">
	<s:iterator value="everydayTotal" id="et" status="st">
		<s:if test="#st.first">
		<tr>
			<td width="25">序号</td><td width="100" align="center">Date <br>(yyyy / mm / dd)</td><td width="80"  align="center">Weekday / <br>WK.of Yr.</td>
			<s:iterator value="exchTypes" id="ext">
				<td width="80"><s:property value="#ext"/></td>
			</s:iterator>
			<td width="80">金额</td>
			</tr>
		</s:if>
		<tr>
		<td><s:property value="#st.count"/> </td>
		<s:iterator value="#et.value" id="vo">
			<td><s:property value="#vo.time"/></td>
			<s:if test="#vo.weekend.toString().equals('SATDAY')">
				<td bgcolor='#18ecff'><s:property value="#vo.weekday"/> </td>
			</s:if>
			<s:elseif test="#vo.weekend.toString().equals('SUNDAY')">
				<td bgcolor='red'><s:property value="#vo.weekday"/> </td>
			</s:elseif>
			<s:else>
				<td><s:property value="#vo.weekday"/></td>
			</s:else>
			<s:iterator value="#vo.count" id="count">
				<td><s:property /></td>
			</s:iterator>
			<td><s:property value="#vo.amount"/></td>
		</s:iterator>
		</tr>
	</s:iterator>
</table>
<table>
	<s:iterator value="everydayGraphs">
		<tr>
			<td>
				<img alt="" src="<s:property/>">	
			</td>
		</tr>
		<tr><td></td></tr>
	</s:iterator>
</table>
</s:if>
<s:else>
<span>每日报表没有数据显示</span>
</s:else>



<br>
<br>




</body>
</html>