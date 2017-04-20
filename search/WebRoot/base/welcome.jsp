<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>首页</title>
    <style type="text/css">
    html,html body
    {
        font-family:宋体;
        font-size:13px;
    }
    h2
    {
        font-family: "Trebuchet MS",Arial,sans-serif;
    }
    p
    {
        line-height:22px;
    }
    </style>
    <script type="text/javascript">
    function getCurDate(){
	 var d = new Date();
	 var week;
	 switch (d.getDay()){
		 case 1: week="星期一"; break;
		 case 2: week="星期二"; break;
		 case 3: week="星期三"; break;
		 case 4: week="星期四"; break;
		 case 5: week="星期五"; break;
		 case 6: week="星期六"; break;
		 default: week="星期天";
	 }
	 var years = d.getYear()<1000?(d.getYear()+1900):d.getYear();
	 var month = add_zero(d.getMonth()+1);
	 var days = add_zero(d.getDate());
	 var hours = add_zero(d.getHours());
	 var minutes = add_zero(d.getMinutes());
	 var seconds=add_zero(d.getSeconds());
	 var ndate = years+"年"+month+"月"+days+"日 "+hours+":"+minutes+":"+seconds+" "+week;
	 document.getElementById('dd').innerHTML = ndate;
	}
	function add_zero(temp){
	 if(temp<10) return "0"+temp;
	 else return temp;
	}
	setInterval("getCurDate()",100);
	</script>
  </head>
  <body>
  	<h2><span id="dd"></span></h2>
    <p>欢迎您：${appid}管理员</p>
    <p>版本：V1.0beta</p>
    <br />
  </body>
</html>
