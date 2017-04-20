<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>欢迎页管理</title>
<%@ include file="/base/jsp/include.jsp"%>
</head>
<body>
	<div id="imgs" class="mini-toolbar" style="padding:2px;border-bottom:0;">
	   
	</div>
</body>
<script type="text/javascript">
	mini.parse();
	//标准方法接口定义
    function SetData(data) {
         data = mini.clone(data);
         var ids = data.id.split(',');
         
         for(var i = 0,len = ids.length; i < len; i++) {
         	var url = projectPath+"download.do?id="+ids[i];
         	var html = '<div><img src=\"'+url+'\" class=\"fancyzoom\"/></div>';
         	$("#imgs").html($("#imgs").html()+html);
         }
        
    }
</script>
</html>
