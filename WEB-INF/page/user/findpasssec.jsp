<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>找回密码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@ include file="/base/jsp/includeweb.jsp"%>
    <link type="text/css" rel="stylesheet" href="${path}/base/css/user.css"/>
  </head>
  <body>
   <%@ include file="/base/jsp/ans-regs-per_bg.jsp"%>
  <%@ include file="/base/jsp/header.jsp"%>
 
  <div id="content-wrapper" class="content-wrapper clear">
  
    <div class="regist-wrapper">
        <form id="form1" name="form1" method="post" action="">
        
        <div class="regist-info-wrapper">
              <h3>找回密码-Setp2</h3>
              <div>输入新密码</div>
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">设置密码：</div>
        <div class="input-wrapper-input"><input type="password" name="password" id="password" class="regist-input"/></div>
        <div class="input-wrapper-tip">设置新密码 最少6位</div>
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">确认密码：</div>
        <div class="input-wrapper-input"><input type="password" name="pass-check" id="pass-check" class="regist-input"/></div>
        <div class="input-wrapper-tip">确认新密码</div>
        </div>
        
        <div class="input-wrapper-but"><input id="subBtn" type="button" value="提交信息" class="but-org" onclick="regist()"/></div>
      </form>
    </div>
    
  </div>
  <%@ include file="/base/jsp/footer.jsp"%>
  </body>
  <script type="text/javascript">
  var token = getParameter("token");
  var userid;
  $(function(){
    requestWithoutCheck("tokenCheck.do","token="+token,function(data){
          if(data.errorCode != 0){
              alert(data.errorMsg);
              location.href="findpfirst.do";
          }else{
             userid = data.data;
          }
      })
  })
  
  
  function regist(){
      var password = $("input[name=password]").val();
      var passcheck = $("input[name=pass-check]").val();
      if(!isDefine(password)){
          alert("请输入完整信息");
          return ;
      }else if(password.length < 6){
    	  alert("密码长度小于6位");
          return ;
      }else if(password != passcheck){
          alert("两次输入的密码不一致");
          return ;
      }
      var query = "id="+userid+"&password="+$.md5(password)+"&token="+token;
      request("passReset.do",query,function(data){
          alert("密码修改成功，现在即可登录!!");
          location.href="login.do";
      })
  }
  </script>
</html>