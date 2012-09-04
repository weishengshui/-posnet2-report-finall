<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/checklogin.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>主菜单</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=ctxRootPath%>/css/base.css" />
</head>

<body>

<div align="center">
<div class="box_001 edge_001">

<table width="400" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="286" align="left" valign="top" class="brd_001 edge_002">
		<div id="nav">
		<ul>

			
			<li class="edge_003" onclick="javascript:clickMenu('Layer3');">
			<h2><s:property value="activity_name"/></h2>
			
			<div id="Layer3" class="list" style="display: none">
			<%
			 //url = ctxRootPath + "/templateReport/detailStatementsTemplate.jsp?startDate="+startDate+"&endDate="+endDate+"&activity_id="+activity_id;
			 %>
			  
			  <a	href="<s:url action='detailStatements' namespace="/view/qqmeishi" />"
				target="right">明细报表</a><br />
			<%
			 	//url = ctxRootPath + "/templateReport/userTokenQueryTemplate.jsp?activity_id="+activity_id;
			 %>
			<a
				href="<s:url action='tokenUse' namespace="/view/qqmeishi" />"
				target=right>验证码使用查询</a><br />
           
			 <%
				 //url = ctxRootPath + "/templateReport/totalStatementsTemplate.jsp?startDate="+startDate+"&endDate="+endDate+"&activity_id="+activity_id+"&activity_name="+URLEncoder.encode(activity_name,"UTF-8");
			 %>
			<a href="<s:url action='totalStatements' namespace="/view/qqmeishi" />"
				target=right>总计报表</a></div>

			</li>
		</ul>
		</div>
		</td>

	</tr>
</table>
</div>
</div>

<script>
	function clickMenu(param) {
		if (document.getElementById(param).style.display == "none") {
			document.getElementById(param).style.display = "";
			document.getElementById(param).focus();
		} else {
			document.getElementById(param).style.display = "none";
		}
	}

	function showMenu(param) {
		document.getElementById(param).style.display = "";
		document.getElementById(param).focus();
	}
	function hideMenu(param) {
		document.getElementById(param).style.display = "none";
	}
</script>
</body>
</html>