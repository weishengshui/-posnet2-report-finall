<%@ include file="/WEB-INF/jsp/rootPath/ctxrootPath.jsp" %>
<% 
session.invalidate();
response.sendRedirect(ctxRootPath+"/"); 
%>
