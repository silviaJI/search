<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>
<html>
  <head>
    
    <title>用户激活</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/base/jsp/includeweb.jsp"%>

  </head>
  <body>
        正在激活，请稍后.........   
  </body>
  <script type="text/javascript">
  var token = getParameter("token");
  
  $(function(){
	  requestWithoutCheck("active.do","token="+token,function(data){
		 if(data.errorCode != 0){
			 alert(data.errorMsg);
			 location.href="login.do";
		 }else{
			 alert("激活成功");
			 location.href="login.do";
		 }
	  })
  })
  
  </script>

</html>
