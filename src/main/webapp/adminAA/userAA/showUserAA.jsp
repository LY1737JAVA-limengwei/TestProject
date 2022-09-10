<%@page import="java.util.*,com.keyan.pojo.*"%>
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
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">首页</a></li>
		    <li><a href="#">用户管理</a></li>
		    <li><a href="#">查看用户信息</a></li>
	    </ul>
    </div>
    
    <div class="rightinfo">
    
        <div class="tools">
    
    	<ul class="toolbar">
    	<a href="adminAA/userAA/addUserAA.jsp" class="tablelink" onclick="">
	        <li class="click"><span><img src="images/t01.png" /></span>添加</li>
        </a>
        <!-- <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li> -->
        </ul>
        
        
        <!-- <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul> -->
    
    </div>
    
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>id<i class="sort"><img src="images/px.gif" /></i></th>
		        <th>用户名</th>
		        <th>密码</th>
		        <th>操作</th>
	        </tr>
        </thead>
        <tbody>
        	<%
        		List<User> showAllUser = (ArrayList<User>)request.getAttribute("showAllUser");
        		for(User u:showAllUser){
        	%>
				        <tr>
					        <td><%=u.getUid() %></td>
					        <td><%=u.getUname() %></td>
					        <td><%=u.getPwd() %></td>
					        <td>
					        	<a href="user?oper=showMmodifyUser&uid=<%=u.getUid() %>" class="tablelink" onclick="">修改</a>
					        	<label>&nbsp;</label>
					        	<a href="user?oper=deleteUser&uid=<%=u.getUid() %>" class="tablelink" onclick="">删除</a>
					        </td>
				        </tr> 
	        	<%} %>
        </tbody>
    </table>
    
   
    <!-- <div class="pagin">
    	<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div> -->
    
    
</body>

</html>