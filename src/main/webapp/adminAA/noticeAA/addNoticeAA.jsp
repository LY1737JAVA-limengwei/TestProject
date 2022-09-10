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
		    <li><a href="#">发布公告</a></li>
	    </ul>
    </div>
    
    <div class="formbody">
    
    	<div id="usual1" class="usual"> 
    
		    <div class="itab">
			  	<ul> 
			   		<li><a href="#tab1" class="selected">发布通知</a></li> 
			  	</ul>
		    </div> 
		    
		  	<div id="tab1" class="tabson">
		    	<div class="formtext">Hi，<b>${admin.aname}</b>，欢迎您使用信息发布功能！</div>
		    
		    <form action="notice?oper=addNotice" method="post" id="tj" target="_top">
		    <ul class="forminfo">
			    <li>
				    <label>公告标题<b>*</b></label>
				    <input name="nitile" type="text" class="dfinput" value="请填写标题"  style="width:518px;"/>
			    </li>
		   
		    
		    </li>
		    <li><label>通知内容<b>*</b></label>
		    	<textarea id="content7" name="ncontent" style="width:700px;height:250px;visibility:hidden;"></textarea>
		    </li>
		    <ul>
		   		<li>
			   		<label>&nbsp;</label>
			   		<li><label>&nbsp;</label><input style="background-color: #d06800;" name="" type="submit" class="btn" value="马上发布"/></li>
		   		</li>
		    </ul>
			</form>
			    </div> 
		    </div>  
		</div> 
 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	
    </div>


</body>

</html>