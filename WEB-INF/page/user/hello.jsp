<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>
<html>
  <head>
    
    <title>用户你好</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/base/jsp/include.jsp"%>

  </head>
  <%
    String str = "我的爱情";
  %>
  
  <body>
           您好${users[1].name}
         <div style="color:#f00">
           <% for(int i = 0 ; i < 5 ; i++){
           %>
           <%}%>
           <c:forEach items="${users}" var="user">
           <span style="color:#0f0">${user.name}</span>
           
           </c:forEach>
         </div>
   <c:import url="/nav.action"></c:import>
  </body>


</html>
