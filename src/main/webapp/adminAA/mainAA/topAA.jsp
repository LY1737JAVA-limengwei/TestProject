<%@ page language="java" import="com.keyan.pojo.*" contentType="text/html; charset=UTF-8"
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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})
	//退出功能
	$("#out").click(function(){
		var flag=window.confirm("你真的要退出吗?");
		if(flag){
			window.top.location.href="user?oper=out";
		}
	})
	
	
})	
</script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="adminAA/mainAA/mainAA.jsp" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
    </div>
        
            
    <div class="topright">    
    <ul>
    <li><a href="javascript:void(0)" id="out">退出</a></li>
    </ul>
     
    <div class="user">
    <span>${admin.aname}</span>
    </div>    
    
    </div>

</body>
</html>