<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/rootPath/ctxrootPath.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Welcome to China Rewards Report Application</title>
<style type="text/css">
#centerpoint {
	top: 50%;
	left: 50%;
	position: absolute;
}

#dialog {
	position:absolute ;
	width: 600px;
	margin-left: -300px;
	height: 400px;
	margin-top: -100px;
}
</style>
<script language="JavaScript"> 
if (window != top) 
top.location.href = location.href; 
</script> 
</head>
<body>
	

	<div id="centerpoint">
	<div id="dialog">
		<center>
			<h2>Welcome to China Rewards Report Application</h2>
			<s:actionerror cssStyle="color:red"/>
			<s:form action="login" method="post" theme="simple" namespace="/pub">
		<table border="0">
			<tr>
				<td align="right" width="100">用户名：</td>
				<td  colspan="2"><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td  align="right" width="100">密码：</td>
				<td colspan="2"><input type="password" name="password" /></td>
			</tr>
			<tr valign="middle">
				<td  align="right" width="100">验证码：</td>
				<td valign="middle"><input type="text" name="code"  size="4"/></td><td> <img alt="" src="kaptcha.jpg" align="middle"/></td>
			</tr>
			<tr align="center">
				<td><input type="submit" value="登录" /></td>
				<td  colspan="2"><input type="reset" value="重置" /></td>
			</tr>
		</table>
	</s:form>
		</center>
	
	</div></div>
</body>
</html>