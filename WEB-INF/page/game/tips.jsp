<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE>
<html>
  <head>
    <title>答题须知</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/base/jsp/includeweb.jsp"%>
	 <link type="text/css" rel="stylesheet" href="${path}/base/css/game.css"/>
  </head>
  
  <body>
  <%@ include file="/base/jsp/ans-regs-per_bg.jsp"%>
  <%@ include file="/base/jsp/header.jsp"%>
   <div id="content-wrapper" class="content-wrapper clear">
     <div class="tips-content">
    <h2>欢迎登陆答题系统</h2>
<h3>答题须知：</h3>
<p>1、         	本次网络测试题型包括<span class="red">5道客观题和1道主观题</span><!--和<span class="red">1道主观题</span>-->。答题总时间为60分钟，超过时间页面将会自动弹出。</p>
<p>2、	每个选手<span class="red">只有一次</span>答题机会，让选手更好进行发挥。</p>
<p>3、	系统每次只显示一道题目，且没有回退功能，不能对已经回答的题目进行修改，所以请务必认真作答，并选择良好的网络环境，不要关闭答题页面。如果在比赛过程中出现故障，或有疑问，请及时通过比赛首页联系大赛工作人员。</p>
<p>4、	最终系统会按照答题分数排名，同分的人按照时间排序。</p>
<p>5、	我们衷心鼓励同学来参与比赛，挑战自我，认真答题，检验信息检索综合能力，祝大家取得好成绩，丰厚的奖品等着你！</p>

     </div>
     
     <div class="tips-button-wrapper">
      <input id="begin" class="but-org" type="button" value="开始答题"/>
     </div>   
     
  </div>
     <%@ include file="/base/jsp/footer.jsp"%>
     <script type="text/javascript">
     $(function(){
    	 $("#begin").click(function(){
    		 location.href="answer.do?id=${id}";
    	 })
    	 
    	 
    	 
     })
     </script>
  </body>
  
</html>
