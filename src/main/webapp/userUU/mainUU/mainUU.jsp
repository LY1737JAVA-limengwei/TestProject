<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 解决(相对/绝对)路径问题 -->
<%
	String base = request.getContextPath()+"/";
	String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+base;
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=url%>">
<meta charset="UTF-8">
<title>信息管理系统界面</title>
</head>
<frameset rows="88,*,31" cols="*" frameborder="no" border="0" framespacing="0">

  <frame src="userUU/mainUU/topUU.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="userUU/mainUU/leftUU.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="userUU/mainUU/rightUU.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
  
  <frame src="userUU/mainUU/footerUU.jsp" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame" />
  
</frameset>
<noframes><body>
</body></noframes>
</html>