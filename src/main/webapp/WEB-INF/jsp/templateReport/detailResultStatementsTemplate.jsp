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
	
	<s:actionerror cssStyle="color:red" />
	<s:fielderror cssStyle="color:red"/>
	<s:if test="hasError==false">
		<s:if test="hasData">
		<h2>明细报表结果</h2>
			<table>
			<s:iterator value="detailExchRecords" id="ders" status="s">
				<s:if test="#s.first">
					<tr>
						<td width='30'>序号</td>
						<td width='125'>商家名称</td>
						<td width='125'>POS机编号</td>
						<td width='125'>验证码</td>
						<td width='125'>消费金额</td>
						<td width='125'>交易时间</td>
					</tr>
				</s:if>
				<tr>
					<td><s:property value="#s.count"/></td>
					<td><s:property value="#ders.shopName" /></td>
					<td><s:property value="#ders.posid" /></td>
					<td><s:property value="#ders.token" /></td>
					<td><s:property value="#ders.amount" /></td>
					<td><s:property value="#ders.time.substring(0,19)" /></td>
				</tr>
			</s:iterator>
			</table>
			
			<table>
				<tr>
					<td width="auto">
						<a href="<s:url action='detailStatementsResult' namespace="/view/qqmeishi" />?cmd=1&offset=1&merchantId=<s:property value='merchantId' />&startDate=<s:property value='startDate' />&endDate=<s:property value='endDate' />">首页</a>&nbsp;
						<s:if test="offset!=1">
							<a href="<s:url action='detailStatementsResult' namespace="/view/qqmeishi" />?cmd=1&offset=<s:property value='pre' />&merchantId=<s:property value='merchantId' />&startDate=<s:property value='startDate' />&endDate=<s:property value='endDate' />">上一页</a>&nbsp;	
						</s:if>
						<s:if test="offset >7 ">...&nbsp;</s:if>
							<s:bean name="org.apache.struts2.util.Counter" id="counter">
								<s:param name="first" value="offset >7 ? offset-5 : 1" />
								<s:param name="last" value="offset < 7 ? (pageCount > 12 ? 12 : pageCount) : (offset < pageCount-5? offset+5: pageCount)" />
								<s:iterator id="c">
									<!--	当前页码		-->
									<s:if test="#c==offset">
										<a href="<s:url action='detailStatementsResult' namespace="/view/qqmeishi" />?cmd=1&offset=<s:property/>&merchantId=<s:property value='merchantId' />&startDate=<s:property value='startDate' />&endDate=<s:property value='endDate' />"><font color="red"><s:property/></font></a>&nbsp;
									</s:if>	
									<!--	其它页码		-->
									<s:else>
										<a href="<s:url action='detailStatementsResult' namespace="/view/qqmeishi" />?cmd=1&offset=<s:property/>&merchantId=<s:property value='merchantId' />&startDate=<s:property value='startDate' />&endDate=<s:property value='endDate' />"><s:property/></a>&nbsp;
									</s:else>
								</s:iterator>
							</s:bean>
							<s:if test="offset < pageCount-7">...&nbsp;</s:if>
						<s:if test="offset!=pageCount">
							<a href="<s:url action='detailStatementsResult' namespace="/view/qqmeishi" />?cmd=1&offset=<s:property value='next' />&merchantId=<s:property value='merchantId' />&startDate=<s:property value='startDate' />&endDate=<s:property value='endDate' />">下一页</a>&nbsp;
						</s:if>							
						<a href="<s:url action='detailStatementsResult' namespace="/view/qqmeishi" />?cmd=1&offset=<s:property value='pageCount' />&merchantId=<s:property value='merchantId' />&startDate=<s:property value='startDate' />&endDate=<s:property value='endDate' />">末页</a>&nbsp;
						第<s:property value="offset"/>页&nbsp;共<s:property value="pageCount"/>页&nbsp;共<s:property value="allCount"/>条
					</td>
					<td width="auto">
						<form action="<s:url action='detailStatementsResult' namespace="/view/qqmeishi" />" method="get">
							<input type="hidden" name="merchantId" value="<s:property value='merchantId' />">
							<input type="hidden" name="startDate" value="<s:property value='startDate' />">
							<input type="hidden" name="endDate" value="<s:property value='endDate' />">
							<input type="hidden" name="cmd" value="1">
							<input type="text" name="offset" size="3">
							<input type="submit" value="跳转">
						</form>
					</td>
				</tr>
			</table>
		</s:if>
		<s:else>
			<span>没有数据显示！</span>
		</s:else>
	</s:if>
	<br><br>
	
</body>

</html>