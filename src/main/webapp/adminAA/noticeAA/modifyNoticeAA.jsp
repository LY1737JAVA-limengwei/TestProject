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
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">修改公告信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
	    <div class="formtitle"><span>修改公告信息</span></div>
	    
	    <form action="notice?oper=modifyNotice" method="post" id="tj" target="_top">
	    	<input type="hidden" name="id" value="${Notice.id }" /> 
		    <ul class="forminfo">
			    <li>
				    <label>公告标题<b>*</b></label>
				    <input name="nitile" type="text" class="dfinput" value="${Notice.nitile }"  style="width:518px;"/>
			    </li>
		   
		    
		    </li>
		    <li><label>通知内容<b>*</b></label>
		    	<textarea id="content7" name="ncontent" style="width:700px;height:250px;visibility:hidden;">${Notice.ncontent }</textarea>
		    </li>
		    <ul>
		   		<li>
			   		<label>&nbsp;</label>
			   		<li><label>&nbsp;</label><input style="background-color: #d06800;" name="" type="submit" class="btn" value="马上发布"/></li>
		   		</li>
		    </ul>	
		    
		</form>
	    
	    
	    
	    
	    
	    
	   <%--  <form action="kyProject?oper=modifyProject2" method="post" id="tj" target="_top">
	    
		    <ul class="forminfo">
			    <li><label>项目标题</label><input name="ptitle" type="text" class="dfinput" value="${Notice. }" /><i></i></li>
			    <li><label>项目类型</label><input name="ptype" type="text" class="dfinput"  value="${Notice.ptype }" /><i></i></li>
			    <li><label>&nbsp;</label><input style="background-color: #d06800;" name="" type="submit" class="btn" value="提交"/></li>
			    <!-- <li><label></label><cite><input name="" type="radio" value="" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="radio" value="" />否</cite></li>
			    <li><label>引用地址</label><input name="" type="text" class="dfinput" value="http://www.uimaker.com/uimakerhtml/uidesign/" /></li>
			    <li><label>文章内容</label><textarea name="" cols="" rows="" class="textinput"></textarea></li>
			    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存"/></li> -->
		    </ul>
	    </form> --%>
    
    </div>


</body>

</html>