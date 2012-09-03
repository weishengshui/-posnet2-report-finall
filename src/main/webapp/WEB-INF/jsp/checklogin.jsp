<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/rootPath/ctxrootPath.jsp" %>


<s:if test="#session.Login==null">
	<% 	response.sendRedirect(ctxRootPath+"/pub/index.jsp"); %>
</s:if>

