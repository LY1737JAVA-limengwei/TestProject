<%@ page import="java.util.*,com.keyan.pojo.*" language="java" contentType="text/html; charset=UTF-8"
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
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jsapi.js"></script>
<script type="text/javascript" src="js/format+zh_CN,default,corechart.I.js"></script>		
<script type="text/javascript" src="js/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="js/jquery.ba-resize.min.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="images/sun.png" alt="天气" /></span>
    <b>${admin.aname}早上好，欢迎使用信息管理系统</b>(admin@www.lmw.com)
    <!-- <a href="#">用户管理</a> -->
    </div>
    
    <div class="welinfo">
    <!-- <span><img src="images/time.png" alt="时间" /></span> -->
    <!-- <i>您上次登录的时间：2013-10-09 15:22</i> （不是您登录的？<a href="#">请点这里</a>） -->
    <span>美好的一天从现在开始吧！</span>
    </div>
    
    <div class="xline"></div>
    
    <ul class="iconlist">
    
    <li><img src="images/ico01.png" /><p><a href="index.jsp">主页</a></p></li>
    <li><img src="images/ico02.png" /><p><a href="#">发布公告</a></p></li>
    <!-- <li><img src="images/ico03.png" /><p><a href="#">数据统计</a></p></li>
    <li><img src="images/ico04.png" /><p><a href="#">文件上传</a></p></li> -->
    <li><img src="images/ico05.png" /><p><a href="#">公告管理</a></p></li>
    <!-- <li><img src="images/ico06.png" /><p><a href="#">项目管理</a></p></li> --> 
            
    </ul>
    
    <!-- <div class="ibox"><a class="ibtn"><img src="images/iadd.png" />添加新的快捷功能</a></div> -->
    
    <div class="xline"></div>
    <div class="box"></div>
    
    <div class="welinfo">
    <span><img src="images/dp.png" alt="提醒" /></span>
    <b>高校科研管理系统使用指南</b>
    </div>
    
    <ul class="infolist">
	    <li><span>您可以快速进行账号管理操作<!-- </span><a class="ibtn">发布或管理文章</a></li> -->
	    <li><span>您可以操作科研项目</span><!-- <a class="ibtn">发布或管理产品</a></li> -->
	    <li><span>您可以进行密码修改、账户设置等操作<!-- </span><a class="ibtn">账户管理</a></li> -->
    </ul>
    
    <div class="xline"></div>
    
    <div class="welinfo">
    <span><img src="images/dp.png" alt="提醒" /></span>
    <b>管理员审批项目指南</b>
    </div>
    
    <ul class="infolist">
	    <li><span>点击项目管理<!-- </span><a class="ibtn">发布或管理文章</a></li> -->
	    <li><span>点击待审批项目</span><!-- <a class="ibtn">发布或管理产品</a></li> -->
	    <li><span>您可以进行审批、查询等操作<!-- </span><a class="ibtn">账户管理</a></li> -->
    </ul>
    
    
    <!-- <div class="uimakerinfo"><b>最新公告</b>(<a href="http://www.lmw.com" target="_blank">www.lmw.com</a>)</div>
    
    <ul class="umlist">
    <li><a href="#">如何发布文章</a></li>
    <li><a href="#">如何访问网站</a></li>
    <li><a href="#">如何管理广告</a></li>
    <li><a href="#">后台用户设置(权限)</a></li>
    <li><a href="#">系统设置</a></li>
    </ul>
    
    
    </div> -->
    
    

</body>

</html>
