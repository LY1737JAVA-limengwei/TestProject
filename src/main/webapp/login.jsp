<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎登录高校科研成果管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
		
</script> 

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
<form action="user" method="post">
<input type="hidden" name="oper" value="login" />    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
    <ul>
	    <li>
	    	<!-- 操作提示语句(刷新后仍然显示，session没清掉，bug暂放) -->
	    	<div>
	    		${sessionScope.altsuccess}<% session.removeAttribute("altsuccess"); %>
	    		${sessionScope.logfailed}<% session.removeAttribute("logfailed"); %>
	    	</div>
	    </li>
	    <li><input name="uname" type="text" class="loginuser" placeholder="用户名" /></li>
	    <li><input name="pwd" type="password" class="loginpwd" placeholder="密码" /></li>
	    <li><input name="" type="submit" class="loginbtn" value="登录" />
	    	
			    <!-- <select  class="select" data-placeholder="请选择">
			    	<option value=""></option>
				    <option value="1">教师</option>
				    <option value="2">管理员</option>
			    </select> -->
		    
	    	<label><input name="identity" type="radio" value="teacher" checked="" />教师</label>
	    	<label><input name="identity" type="radio" value="admin" checked="" />管理员</label>
	    </li>
    </ul>
    
    
    </div>
    
    </div>
</form>
    
    
    <div class="loginbm">版权所有  2014  <a href="http://www.uimaker.com">uimaker.com</a>高校科研成果管理系统</div>
	
    
</body>
</html>