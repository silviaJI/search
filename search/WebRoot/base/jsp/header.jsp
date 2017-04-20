<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="header">
<div id="header-content" class="clear">
<div id="header-left" class="header-wrapper">
<a href="index.do">首页</a><b>|</b>
<a href="game.do">个人中心</a>
<b>|</b>
<a href="rank.do">查看排名</a>
</div>
<div id="header-right" class="header-wrapper">
<%
Object obj = session.getAttribute("name");
if(obj == null){
%>
<a href="login.do">登录</a><b>|</b>
<a onclick="fff()" href="regist.do">注册</a>
<%}else{%>
<a href="#">欢迎您:${sessionScope.name}</a><b>|</b>
<a href="#" class="logout">注销</a><b>|</b>
<a href="modifypass.do?userid=${sessionScope.userid}&isM=1">修改密码</a>
<script type="text/javascript">
    $(".logout").click(function(){
      // if(QC.Login.check()){
        //   QC.Login.signOut();
       //}
       location.href="MLogout.do?userid=${sessionScope.userid}";
    })
    function fff(){
   	 alert("现在是复赛阶段,无法注册");
   	 return false;
    }
     
</script>
<%}%>
</div>
</div>
</div>


