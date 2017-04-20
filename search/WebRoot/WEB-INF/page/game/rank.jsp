<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE>
<html>
  <head>
    <title>当前排名</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/base/jsp/includeweb.jsp"%>
	 <link type="text/css" rel="stylesheet" href="${path}/base/css/game.css"/>
  </head>
  
  <body>
 <%@ include file="/base/jsp/background.jsp"%>
  <%@ include file="/base/jsp/header.jsp"%>
   <div id="content-wrapper" class="content-wrapper clear">
        <div id="rank-warn"><!--当前参赛人数${total}人-->复赛成绩排名</div>
       <div id="rank-wrapper">
        <c:set var="i" value="1"></c:set>
          <table class="rank" border="1">
          <tr><th style="width:10%;">名次</th><th style="width:20%;">姓名</th><th style="width:15%;">学校</th><th style="width:20%;">院系</th><th style="width:15%;">得分</th><th style="width:15%;">用时</th></tr>
            <c:forEach items="${users}" var="user">
            <tr><td>${i + (page-1) * limit}</td><td>${user.name}</td>
            <td>${user.school}</td>
            <td>${user.depart}</td>
            <!-- 用时 -->
            <!--
            <c:choose>
            <c:when test="${user.isV==2 and user.rank==0}">
            <td>未答题</td>
            </c:when>
            <c:when test="${user.isV==2 and user.rank!=0}">
            <td>批改中</td>
             -->
            <c:choose>
            <c:when test="${user.isV==2 and user.score==0}">
            <td>未完成答题</td>
            </c:when>
            <c:when test="${user.isV==2 and user.score!=0}">
            <td>${user.score}</td>
            </c:when>
            </c:choose>
			<td><fmt:formatNumber type="number" value="${(user.rank-user.rank%60)/60}" pattern="#"/>分钟${user.rank%60}秒</td>
            <!--<td>初赛</td>-->
           
          </tr>
          <c:set var="i" value="${i + 1}"></c:set>
        </c:forEach>
        </table>
        </div>
        <%@ include file="/base/jsp/page.jsp"%>
     
  </div>
     <%@ include file="/base/jsp/footer.jsp"%>
     <script type="text/javascript">
     var index = true;
     $("#next-page").click(function(){
    	   location.href="rank.do?page=${page+1}"; 
       });
       $("#pre-page").click(function(){
    	   var page = "${page}";
    	   if(page > 1){
    		   location.href="rank.do?page=${page-1}";
    	   }
       });
       /*
        <!--<c:choose>
            <c:when test="${user.rank >= 60}">
            <td><fmt:formatNumber type="number" value="${user.rank /60-0.5}" maxFractionDigits="0"/>分${user.rank %60}秒</td>
            </c:when>
            <c:when test="${user.rank < 60 and user.rank > 0}">
            <td>${user.rank}秒</td>
            </c:when>
            <c:otherwise>
            <td></td>
            </c:otherwise>
             </c:choose>
             <!-- 得分 -->
             <c:choose>
            <c:when test="${user.isV==2 and user.score ==0}">
            <td>批改中或未答题</td>
            </c:when>
            <c:when test="${user.isV==1 and user.score ==0}">
            <td>未答题</td>
            </c:when>
            <c:otherwise>
            <td>已批阅</td>
            </c:otherwise>
            </c:choose>
            </td>-->
       */
     </script>
  </body>
  
</html>
