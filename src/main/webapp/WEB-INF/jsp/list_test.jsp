<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login test</title>
</head>
<body>
	<h1>Hello List!FUCK Jrebel...  222</h1>
	
	<table>
		<s:iterator id="activity" value="#request.activityList" status="i">
			<tr>
				<td><s:property value="#i.count"/></td>
				<td><s:property value="#activity.id"/></td>
				<td><s:property value="#activity.activityName"/></td>
			</tr>
		</s:iterator>
	</table>
	
</body>
</html>