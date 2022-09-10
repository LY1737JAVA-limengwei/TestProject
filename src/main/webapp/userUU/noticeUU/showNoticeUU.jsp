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
  
<script type="text/javascript">
$(document).ready(function(e) {

	$(".select3").uedSelect({
		width : 152
	});
});
})	
</script>
</head>

<body class="sarchbody">

	<div class="place">
	    <span>位置：</span>
	    
	    <ul class="placeul">
		    <li><a href="">首页</a></li>
		    <li><a href="#">查看公告信息</a></li>
	    </ul>
    </div>
    
    <form action="notice" method="post">
    	<input type="hidden" name="oper" value="searchNoticeteacher" /> 
	    <ul class="prosearch">
		    <li>
		    	<label>查询：</label>
		    	<i>公告名称</i><a><input name="nitile" type="text" class="scinput" /></a>
		    	<a><input name="" type="submit" class="sure" value="查询"/></a>
		    </li>
	    </ul>
	</form>
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
		        <th>通知信息标题</th>
		        <th>通知信息内容</th>
		        <th>发布时间</th>
		        <th>发布人</th>
	        </tr>
        </thead>
    	<tbody>
	        <%
        		List<Notice> showNotice = (ArrayList<Notice>)request.getAttribute("showNotice");
        		for(Notice noi:showNotice){
        	%>
				        <tr>
					        <td><%=noi.getId() %></td>
					        <td><%=noi.getNitile() %></td>
					        <td><%=noi.getNcontent() %></td>
					        <td><%=noi.getNtime() %></td>
					        <td><%=noi.getNdepartid() %></td>
					        <%-- <td><a href="notice?oper=modifyNotice&id=<%=noi.getId() %>" class="tablelink" onclick="">修改</a></td> --%>
					        <%-- <td><a href="notice?oper=deleteNotice&id=<%=noi.getId() %>" class="tablelink" onclick="">删除</a></td> --%>
				        </tr> 
				  <%} %>
        </tbody>
    </table>

</body>

</html>