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
   <%@ include file="/base/jsp/background.jsp"%>
  <%@ include file="/base/jsp/header.jsp"%>
  <div id="content-wrapper" class="content-wrapper clear">
  <h2>找回密码-Step1</h2>
    <div class="regist-wrapper">
        <form id="form1" name="form1" method="post" action="">
        
       
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">注册邮箱：</div>
        <div class="input-wrapper-input"><input type="text" name="mail" id="mail" class="regist-input"/></div>
        <div class="input-wrapper-tip">输入注册邮箱</div>
        </div>
        <div class="input-wrapper-but"><input id="subBtn" type="button" value="提交信息" class="but-org" onclick="regist()"/></div>
      </form>
    </div>
    
  </div>
  <%@ include file="/base/jsp/footer.jsp"%>
  </body>
  <script type="text/javascript">
  var stip = "学校";
  var dtip = "院系";
  var mtip = "专业";
  var ttip = "入学年份";
  
  $(function(){
    
  })
  
  
  function regist(){
      var mail = $("input[name=mail]").val();
      var pattern = /^[\w\._]+@[\w_]+\.[\w_\.]+$/;
      if(!isDefine(mail)){
          alert("请输入完整信息");
          return ;
      }else if(!pattern.test(mail)){
          alert("请输入正确的邮箱格式");
          return ;
      }
      var query = "email="+mail;
      request("passMail.do",query,function(data){
          alert("邮件发送成功！！请在一天之内进行密码重置");
          location.href="index.do";
      })
  }
  </script>
</html>