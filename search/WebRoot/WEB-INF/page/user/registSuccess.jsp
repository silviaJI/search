<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>注册成功</title>
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
  <h2>注册成功</h2>
    <div class="regist-wrapper">
        <form id="form1" name="form1" method="post" action="">
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">姓名</div>
        <div class="input-wrapper-input"><input type="text" name="name" id="name" class="regist-input"/></div>
       
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">学号</div>
        <div class="input-wrapper-input"><input type="text" name="stuId" id="stuId" class="regist-input"/></div>
        <div class="input-wrapper-tip">务必输入正确的学号</div>
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">邮箱</div>
        <div class="input-wrapper-input"><input type="text" name="mail" id="mail" class="regist-input"/></div>
        <div class="input-wrapper-tip">务必输入正确的邮箱</div>
        </div>
        
         <div class="input-wrapper clear">
        <div class="input-wrapper-info">手机号码</div>
        <div class="input-wrapper-input"><input type="text" name="phone" id="phone" class="regist-input"/></div>
        <div class="input-wrapper-tip">手机号码方便与您取得联系</div>
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">选择学校</div>
        <div class="input-wrapper-input"><select name="school" id="school"><option>南京大学</option><option>南京师范大学</option><option>南京理工大学</option></select></div>
       
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">输入院系</div>
        <div class="input-wrapper-input"><input type="text" name="depart" id="depart" class="regist-input"/></div>
      
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">选择学历</div>
        <div class="input-wrapper-input"><select name="education" id="education"><option value="0">本科</option><option value="1">硕士</option><option value="2">博士</option></select></div>
      
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">设置密码</div>
        <div class="input-wrapper-input"><input type="password" name="password" id="password" class="regist-input"/></div>
       <div class="input-wrapper-tip">请设置密码 最少6位</div>
        </div>
        
        <div class="input-wrapper clear">
        <div class="input-wrapper-info">确认密码</div>
        <div class="input-wrapper-input"><input type="password" name="pass-check" id="pass-check" class="regist-input"/></div>
       
        </div>       
        <div class="input-wrapper-but"><input id="subBtn" type="button" value="立即注册" class="but-org" onclick="regist()"/></div>
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
     var date = new Date();
     var year = date.getFullYear();
      
    
  })
  
  
  function regist(){
      var name = $("input[name=name]").val();
      var password = $("input[name=password]").val();
      var passcheck = $("input[name=pass-check]").val();
      var mail = $("input[name=mail]").val();
      var phone = $("input[name=phone]").val();
      var school = $("#school option:selected").val();
      var education = $("#education option:selected").val();
      var depart = $("input[name=depart]").val();
      var stuId = $("input[name=stuId]").val();
      var emailPattern = /^[\w\._]+@[\w_]+\.[\w_\.]+$/;
      var phonePattern = /^1\d{10}$/;
      if(!isDefine(name)||!isDefine(password)||!isDefine(depart)||!isDefine(school)||!isDefine(education)||!isDefine(stuId)){
          alert("请输入完整信息");
          return ;
      }else if(!emailPattern.test(mail)){
          alert("请输入正确的邮箱格式");
          return ;
      }else if(!phonePattern.test(phone)){
          alert("请输入正确的手机号码");
          return ;
      }else if(password != passcheck){
          alert("两次输入的密码不一致");
          return ;
      }else if(password.length < 6){
    	  alert("密码长度小于6位");
          return ;
      }
      var query = "name="+name+"&stuId="+stuId+"&phone="+phone+"&school="+school+"&education="+education+"&password="+$.md5(password)+"&email="+mail+"&depart="+depart;
      loading();
      request("userRegist.do",query,function(data){
          alert("注册成功，现在即可登陆邮箱验证账号");
          location.href="registSuccess.do";
      })
  }
  </script>
</html>