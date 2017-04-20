<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE>
<html>
  <head>
    <title>答题首页</title>
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
        
       <div id="status-wrapper">
        <c:set var="score" value="0"></c:set>
        <c:set var="i" value="1"></c:set>
        <c:forEach items="${answers}" var="ans">
          <table class="status" border="1">
          <tr><th>答题次序</th><th>题目总数</th><th>当前题目次序</th><th>已答次数</th><th>剩余时间</th><th>状态</th><th>操作</th></tr>
            <tr><td>${i}</td><td>${totalQuestion}</td><td>${ans.nowNumber}</td><td>${ans.ansTimes}</td><td><fmt:formatNumber type="number" value="${(ans.timeLeft-ans.timeLeft%60)/60}" pattern="#"/>分钟${ans.timeLeft%60}秒</td>
            <c:choose>
            <c:when test="${ans.isChecked == 0}">
            <td>批改中或未答题</td>
            </c:when>
            <c:otherwise>
            <td>已批阅</td>
            </c:otherwise>
            
            </c:choose>
            
            <td>
            <c:choose>
            <c:when test="${ans.isOver == 0 and ans.hasRead == 1}">
			<!--初赛将于4月20日开始，欢迎参加！-->
            <a href="answer.do?id=${ans.id}">答题</a>
            </c:when>
            <c:when test="${ans.isOver == 0 and ans.hasRead == 0}">
			<!--初赛将于4月20日开始，欢迎参加！-->
            <a href="tips.do?id=${ans.id}">答题</a>
            </c:when>
            <c:otherwise>
            	答题结束
			<!--初赛将于4月20日开始，欢迎参加！-->
            </c:otherwise>
            </c:choose>
            </td>
          </tr>
          </table>
          <c:if test="${ans.score > score}">
            <c:set var="score" value="${ans.score}"></c:set>
          </c:if>
          <c:set var="i" value="${i + 1}"></c:set>
        </c:forEach>
        </div>
        <div class="score-wrapper">
        当前是<c:choose>
        <c:when test="${level == 1}">初赛
        </c:when>
        <c:otherwise>复赛   
       </c:otherwise>
        </c:choose>
          <!--&nbsp;&nbsp;&nbsp;
        	目前您的得分为：<span class="finalScore">${score}</span>--></div>
        
        <!--<a href="answer.do">开始答题</a>
         <c:forEach items="${answers}" var="ans">
         <div style="width:700px;margin:50px auto;border:1px solid #f00">
                                          姓名：<span style="color:#0f0">${ans.name}</span><br/>
            剩余时间：<span style="color:#0f0">${ans.timeLeft}</span><br/>
            是否结束：<span style="color:#0f0">${ans.isOver}</span><br/>
            <span style="color:#0f0">${ans.score}</span><br/>
            <span style="color:#0f0">${ans.rank}</span><br/>
            <span style="color:#0f0">${ans.nowNumber}</span><br/>
            <span style="color:#0f0">${ans.questions}</span><br/>
             <span style="color:#0f0">${ans.times}</span><br/>
         </div>
         </c:forEach>-->
         </div>
     <%@ include file="/base/jsp/footer.jsp"%>
     <script type="text/javascript">
     var tag = "${tag}";
      if(!isDefine(tag)){
    	  location.href="game.do";
      }
     </script>
  </body>
  
</html>
