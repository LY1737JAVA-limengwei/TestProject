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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">添加项目</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
	    <div class="formtitle"><span>基本信息</span></div>
	    <form action="kyProject?oper=addProject" method="post" id="tj" target="_top">
		    <ul class="forminfo">
			    <li><label>项目标题</label><input name="ptitle" type="text" class="dfinput" /><i></i></li>
			    <li><label>项目类型</label><input name="ptype" type="text" class="dfinput" /><i></i></li>
			    <li><label>&nbsp;</label><input style="background-color: #d06800;" name="" type="submit" class="btn" value="提交"/></li>
			    <!-- <li><label></label><cite><input name="" type="radio" value="" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="radio" value="" />否</cite></li>
			    <li><label>引用地址</label><input name="" type="text" class="dfinput" value="http://www.uimaker.com/uimakerhtml/uidesign/" /></li>
			    <li><label>文章内容</label><textarea name="" cols="" rows="" class="textinput"></textarea></li>
			    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存"/></li> -->
		    </ul>
	    </form>
    
    </div>


</body>

</html>