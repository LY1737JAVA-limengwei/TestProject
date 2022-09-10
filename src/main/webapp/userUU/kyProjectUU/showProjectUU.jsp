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

  
<!-- <script src="js/axios.min.js"></script>
<script type="text/javascript">
	$.ajax({
		type:"get",//请求方式
		url:""
		
	});
	
</script> -->

</head>

<body class="sarchbody">

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="">首页</a></li>
    <li><a href="#">查看项目信息</a></li>
    </ul>
    </div>
    
    <form action="kyProject" method="post">
    	<input type="hidden" name="oper" value="showProjectsearchuser" /> 
	    <ul class="prosearch">
		    <li>
		    	<label>查询：</label>
		    	<i>项目标题</i><a><input name="ptitle" type="text" class="scinput" /></a>
		    	<a><input name="" type="submit" class="sure" value="查询"/></a>
		    </li>
	    </ul>
	</form>
    
    <div class="formtitle"><span>项目列表</span></div>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>标题</th>
        <th>申报人</th>
        <th>项目类型</th>
        <th>发布时间</th>
        <th>是否审核</th>
        </tr>
        </thead>
        <tbody>
	        <%
        		List<KyProject> showProject = (ArrayList<KyProject>)request.getAttribute("showProject");
        		for(KyProject kyp:showProject){
        	%>
				        <tr>
					        <td><%=kyp.getPid() %></td>
					        <td><%=kyp.getPtitle() %></td>
					        <td><%=kyp.getApname() %></td>
					        <td><%=kyp.getPtype() %></td>
					        <td><%=kyp.getPtime() %></td>
					       <% 	int Pstate = kyp.getPstate(); 
					        	if(Pstate == 1){
					        %>
					        		<td>待审批</td>
					       <%}else if(Pstate == 2){
					    	   		%>
					    	   		<td>已通过</td>
					       <%}else if(Pstate == 3){
					    	   		%>
					    	   		<td>未通过</td>
				       	   <%}else{
					    	   		%>
					    	   		<td>状态异常</td>
					       <%} %>
				        </tr> 
	        	<%} %>
        </tbody>
    </table>
    

       
	</div> 
 
    
    </div>


</body>

</html>