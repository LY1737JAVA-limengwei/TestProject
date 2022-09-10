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
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />

<!-- 引入jQuery文件 -->
<script type="text/javascript" src="js/jquery.js"></script>
<!-- 修改密码表单验证 -->
<script type="text/javascript">
$(function(){
	//校验密码修改
	$("#tj").submit(function(){
		if($("#newPwd").val() == ""){//校验新密码
			alert("新密码不能为空");
			return false;
		}else if($("#cfPwd").val() == ""){//校验确认密码
			alert("确认密码不能为空");
			return false;
		}else if($("#newPwd").val() != $("#cfPwd").val()){//校验新密码和确认密码是否一致
			alert("两次密码 不一致");
			return false;
		}else{
			return true;
		}
	})
})
</script>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">用户管理</a></li>
	    <li><a href="#">修改教师密码</a></li>
    </ul>
</div>

	<div class="formbody">
	  <div class="formtitle"><span>修改本人密码信息</span></div>
		<form action="user?uid=${user.uid }" method="post" id="tj" target="_top">
		<input type="hidden" name="oper" value="alterpwd" />
			<ul class="forminfo">
				<li><label>新密码</label><input name="newPwd" id="newPwd" type="text" class="dfinput" /></li>
				<li><label>确认密码</label><input name="" id="cfPwd" type="text" class="dfinput" /></li>
				<li><label>&nbsp;</label><input style="background-color: #d06800;" name="" type="submit" class="btn" value="提交"/></li>
			</ul>
		</form>
	</div>
</body>
</html>