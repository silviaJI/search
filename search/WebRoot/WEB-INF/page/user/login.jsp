<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<%@ include file="/base/jsp/includeweb.jsp"%>
    <link type="text/css" rel="stylesheet" href="${path}/base/css/user.css"/>
  </head>
  <body >
  <%@ include file="/base/jsp/background.jsp"%>
  <%@ include file="/base/jsp/header.jsp"%>
    <div id="login_logo">
        <img src="${path}/base/img/magnifier.png">
    </div>
    <div id="login-content-wrapper" class="content-wrapper clear">
        <iframe name="login_iframe" width=0 height=0 style="visibility: hidden;"></iframe>
        <div class="login-wrapper">
            <form id="form1" name="form1" target="login_iframe" method="post" action="" onsubmit="login()">
                <div class="login-input-wrapper"><img src="${path}/base/img/name.png"><input type="text" name="username" id="username"  value="手机号/邮箱" class="login-input"/></div>
                <div class="login-input-wrapper"><img src="${path}/base/img/password.png"><input type="text" name="pwd-text" id="pwd-text" value="密码" class="login-input"/></div>
                <div class="login-input-wrapper  hide hidden-pwd"><img src="${path}/base/img/password.png"><input type="password" name="password" id="password" value="" class=" login-input"/></div>
                <div class="login-but-wrapper"><input id="subBtn" type="button" value="Log in" onclick="login()"/></div>
                <div class="login-tip-wrapper"><a href="findpfirst.do">忘记密码?</a> | <a onclick="fff()" href="regist.do">注册账号</a>
                    <!--<a href="#">快速登录</a> --></div>
            </form>
        </div>
    </div>
  <%@ include file="/base/jsp/footer.jsp"%>
  </body>
  <script type="text/javascript">
  
  //登录页面时就防止用户登录
  //navigatorSuggest();
  
 function fff(){
	 alert("现在是复赛阶段,无法注册");
	 return false;
 }
  
  
  
  var tipu = '手机号/邮箱';
  var tipp = "密码";
  var userid = "${sessionScope.userid}";
  if(isDefine(userid)){
      location.href="game.do?userid="+userid;
  }
  
  var pwdtext = $('<div><input type="text" name="pwd-text" id="pwd-text" value="密码" class="login-input border-radius-bottom"/></div>');
  var pwd = $('<div><input type="password" name="password" id="password" class="login-input border-radius-bottom"/></div>');
  $(function(){
       $("#pwd-text").focusin(function(){
           //$(this).blur();
           //$(this).hide();
           $(this).parent().remove();
           $(".hidden-pwd").show();
           $("#password").focus();
       });
       
       /*$("#password").focusout(function(){
          if($(this).val() == ""){
            //$(this).hide();
            $(this).parent().remove();
            //$("input[name=password]").hide();
            //$("input[name=pwd-text]").show();
            pwdtext.insertBefore(".login-but-wrapper");
            pwdtext.find("input[name=pwd-text]").focusin(function(){
                 $(this).parent().remove();
                 pwd.insertBefore(".login-but-wrapper");
            });
          }
       });
       */
     
     $("#username").focus(function(){
       if($(this).val() == tipu){
           $(this).val("");
       }
     });
     
     $("#username").blur(function(){
          if($(this).val() == ""){
           $(this).val(tipu);
       }
     });
  });
   
  function login(){
     var username = $("#username").val();
     var password = $("#password").val();
     if(username=='' || password=="" || username==tipu || password==tipp){
            showInfo("请输入用户名和密码");
            return;
      }
      var query = "username="+username+"&password="+$.md5(password);
      request("Mlogin.do",query,function(data){
          username = data.username;
          userid = data.id;
          //nameGlobal = data.name;
          //phoneGlobal = data.phone;
          //entranceYear = $.formatDate("yyyy",data.entranceDate);
          //var yearCode = getYearCode();
          location.href="game.do";
      })
  }
  
  
  </script>
</html>