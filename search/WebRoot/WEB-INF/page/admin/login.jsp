<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>后台系统登入</title>
	<%@ include file="/base/jsp/include.jsp"%>
	 <style type="text/css">
    body
    {
        width:100%;height:100%;margin:0;overflow:hidden;
    }
    </style>
    <script type="text/javascript">
    	//防止嵌套页面
		if (window.top != window) {
			window.top.location = window.location;
		}
    </script>
  </head>
  <body>
  <div id="loginWindow" class="mini-window" title="用户登录" style="width:350px;height:165px;" 
   showModal="true" showCloseButton="false" allowDrag="false">
    <div id="loginForm" style="padding:15px;padding-top:10px;">
        <table>
            <tr>
                <td style="width:60px;"><label for="account$text">帐号：</label></td>
                <td>
                    <input id="account" name="account" requiredErrorText="帐号不能为空" class="mini-textbox" required="true" style="width:150px;"/>
                </td>    
            </tr>
            <tr>
                <td style="width:60px;"><label for="pwd$text">密码：</label></td>
                <td>
                    <input id="pwd" name="pwd" class="mini-password" requiredErrorText="密码不能为空" required="true" style="width:150px;" onenter="onLoginClick"/>
                </td>
            </tr>            
            <tr>
                <td></td>
                <td style="padding-top:5px;">
                    <a onclick="onLoginClick" class="mini-button" style="width:60px;">登录</a>
                    <a onclick="onResetClick" class="mini-button" style="width:60px;">重置</a>
                </td>
            </tr>
        </table>
    </div>
</div>
    <script type="text/javascript">
    	var appid = '${appid}';
        mini.parse();
        var loginWindow = mini.get("loginWindow");
        loginWindow.show();
        function onLoginClick(e) {
            var form = new mini.Form("#loginWindow");
            form.validate();
            if (form.isValid() == false) return;
            loginWindow.hide();
            
            $.ajax({
            	url: "loginM.do", 
            	data:form.getData(),
            	success: function(data){
                	if(data == 'success'){
                		mini.loading("登录成功，马上转到系统...", "登录成功");
                        setTimeout(function () { window.location = "indexManager.do"; }, 1500);
                	}else{
                		var msg = "";
                		if(data == '1'){
                			msg = "账号和密码不能为空";
                		}else{
                			msg = "账号或密码不正确";
                		}
                		loginWindow.show();
                		mini.alert(msg);
                	}
            	}
            });
            
        }
        function onResetClick(e) {
            var form = new mini.Form("#loginWindow");
            form.clear();
        }
        mini.get('account').focus();
    </script>
  </body>
</html>
