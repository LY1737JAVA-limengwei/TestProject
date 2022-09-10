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
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson .header").click(function(){
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		
		$parent.addClass("active");
		if(!!$(this).next('.sub-menus').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.sub-menus').hide();
			}else{
				$parent.addClass("open").find('.sub-menus').show();	
			}
			
			
		}
	});
	
	// 三级菜单点击
	$('.sub-menus li').click(function(e) {
        $(".sub-menus li.active").removeClass("active")
		$(this).addClass("active");
    });
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});
})	

</script>


</head>

<body style="background:#fff3e1;">
	<div class="lefttop"><span></span>教师</div>
    
  <dl class="leftmenu">
  	<dd>
		<div class="title">
	    	<span><!-- <img src="images/leftico01.png" /> --></span>这里是教师权限页面
		 
	</dd>
    <dd>
	    <div class="title">
		    <span><!-- 这两个图片没有垂直居中<img src="images/leftico01.png" /> --></span>用户管理
		</div>
		    <ul class="menuson">
		        <li><cite></cite><a href="user?oper=showUser" target="rightFrame">查看用户信息</a></li>
		        <li><cite></cite><a href="userUU/yonghuUU/pwdUU.jsp" target="rightFrame">修改密码</a></li>
		    </ul> 
		       
    </dd>
    <dd>
		<div class="title">
	    	<span><!-- <img src="images/leftico01.png" /> --></span>项目管理
		</div>
		    <ul class="menuson">
		        <li><cite></cite><a href="kyProject?oper=showProject&yonghu=jiaoshi" target="rightFrame">查看项目信息</a><i></i></li>
		        <li><cite></cite><a href="userUU/kyProjectUU/addProjectUU.jsp" target="rightFrame">申报新项目</a><i></i></li>
		    </ul>  
	</dd>
	<dd>
		<div class="title">
	    	<span><!-- <img src="images/leftico01.png" /> --></span>公告通知
		</div>
		    <ul class="menuson">
		        <li><cite></cite><a href="notice?oper=showNotice&yonghu=jiaoshi" target="rightFrame">查看公告信息</a><i></i></li>
		        <!-- <li><cite></cite><a href="userUU/kyProjectUU/addProjectUU.jsp" target="rightFrame">申报新项目</a><i></i></li> -->
		    </ul>  
	</dd>
  </dl>
</body>
</html>