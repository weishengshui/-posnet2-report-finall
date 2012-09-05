<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<h2>验证码使用查询</h2>
	<form
		action="<s:url action='tokenUse' namespace="/view/qqmeishi" />?cmd=1"
		method="post">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="2" width="100">请输入验证码：</td>
				<td>
					<input type="text" name="token"/>
				</td>
				<td><input type="submit" value="提交" /></td>
			</tr>

		</table>
	</form>
	<s:actionerror cssStyle="color:red" />
	<br>
	<s:if test="cmdExists">
		<s:if test="detailExchRecord!=null">
			<table border="1">
				<tr>
					<td width="100">商家名称</td>
					<td width="100">POS机编号</td>
					<td width="100">验证码</td>
					<td width="100">消费金额</td>
					<td width="120">交易时间</td>
				</tr>
				<tr>
					<td><s:property value="detailExchRecord.shopName"/> </td>
					<td><s:property value="detailExchRecord.posid"/> </td>
					<td><s:property value="detailExchRecord.token"/> </td>
					<td><s:property value="detailExchRecord.amount"/> </td>
					<td><s:property value="detailExchRecord.time.substring(0,19)"/> </td>
				</tr>
			</table>
			
		</s:if>
		<s:else>
			<span>该验证码不存在或尚未使用</span>
		</s:else>
	</s:if>
	<br><br>
</body>

</html>