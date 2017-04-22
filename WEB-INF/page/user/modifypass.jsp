<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>修改密码</title>
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
    <h2>修改个人密码</h2>
    <div class="regist-wrapper">
        <form id="form1" name="form1" method="post" action=""> 
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">输入旧密码：</div>
        <div class="input-wrapper-input"><input type="password" name="password" id="password" class="regist-input"/></div>
        <div class="input-wrapper-tip">请输入旧密码</div>
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">设置新密码：</div>
        <div class="input-wrapper-input"><input type="password" name="passsordNew" id="passsordNew" class="regist-input"/></div>
        <div class="input-wrapper-tip">请输入新密码 最少6位</div>
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">确认新密码：</div>
        <div class="input-wrapper-input"><input type="password" name="passsordNew-check" id="passsordNew-check" class="regist-input"/></div>
        <div class="input-wrapper-tip">请确认新密码</div>
        </div>
        
        <div class="input-wrapper-but"><input id="subBtn" type="button" value="立即修改" class="but-org" onclick="regist()"/></div>
      </form>
    </div>
    
  </div>
  <%@ include file="/base/jsp/footer.jsp"%>
  </body>
  <script type="text/javascript">
  var userid = "${sessionScope.userid}";
  function regist(){
      var password = $("input[name=password]").val();
      var passsordNew = $("input[name=passsordNew]").val();
      var passsordNew_check = $("input[name=passsordNew-check]").val();
      
      if(!isDefine(password)||!isDefine(passsordNew)||!isDefine(passsordNew_check)){
          alert("请输入完整信息");
          return ;
      }else if(passsordNew.length < 6){
    	  alert("密码长度小于6位");
          return ;
      }else if(passsordNew != passsordNew_check){
          alert("两次输入的密码不一致");
          return ;
      }
      var query = "id="+userid+"&userid="+userid+"&password="+$.md5(password)+"&passsordNew="+$.md5(passsordNew);
      request("MChangePass.do",query,function(data){
          alert("修改成功");
          location.href="game.do";
      })
  }
  </script>
</html>