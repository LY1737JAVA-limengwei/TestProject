<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- 解决(相对/绝对)路径问题 -->
<%
	String base = request.getContextPath()+"/";
	String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+base;
%>

<html>
<head>
<base href="<%=url%>">
    <title>首页</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="/day07/js/jquery.min.js"></script>

<script type="text/javascript">
     $(function(){
         $('#btn').click(function () {
             $.post("KyProjectServlet?oper=ajax",function (resp) {
                 console.log(resp);
                 
                 var brands = resp.data;
                 
                 var tableData = "<tr>"+
                     "<td>编号</td>"+
                     "<td>标题</td>"+
                     "<td>申报人</td>"+
                     "<td>项目类型</td>"+
                     "<td>发布时间</td>"+
                     "<td>是否审核</td>"+
                     "<td>审核人</td>"+
                 "</tr>";
                 
                 //判断后台传来的数据是否为空
                 /* if(val.reportArray){
                     this.projectReportSumNum = val.reportArray.length;
                   } */
                 
                 for (var i = 0; i < brands.length; i++) {
                	 var brand = brands.i;
                	 
                	 tableData += "\n" +"<tr>"+
                     "<td>"+ brand[i].pid+"</td>"+
                     "<td>"+ brand[i].ptitle+"</td>"+
                     "<td>"+ brand[i].apname+"</td>"+
                     "<td>"+ brand[i].ptype+"</td>"+
                     "<td>"+ brand[i].ptime+"</td>"+
                     "<td>"+ brand[i].ptitle+"</td>"+
                     "<td>"+ brand[i].exname+"</td>"+
                     "\n"+
                 "</tr>";
				}
                 //循环外，设置数据
                 document.getElementById("brandTable").innerHTML = "tableData";
                 
                 
                 /* var html = "";
                 for(let i = 0; i <brands.length;i++){
                     html += "<tr>" +
                     "<td>"+ brands[i].pstate+"</td>"+
                     "<td>"+ data[i].apname+"</td>"+
                     "<td>"+ data[i].exname+"</td>"+
                     "<td>"+ a[i].pid+"</td>"+
                     "<td>"+ a[i].ptitle+"</td>"+
                     "<td>"+ a[i].ptype+"</td>"+
                     "<td>"+ a[i].ptime+"</td>"+
                     "</tr>";
                 }
               $('#content').html(html); */

             })
         })
     })

</script>




</head>
<body>
	

<input type="button" name="" id="btn" value="showProject"/>
<table width="80%"  align="center">
    <tr id="brandTable">
        <td>编号</td>
        <td>标题</td>
        <td>申报人</td>
        <td>项目类型</td>
        <td>发布时间</td>
        <td>是否审核</td>
        <td>操作</td>
    </tr>
    <tbody id="content">
    </tbody>

</table>

</body>
</html>