<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Exception"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>错误页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<SCRIPT>window.wpo={start:new Date*1,pid:109,page:'qing'}</SCRIPT>
	<!--[if IE]>
	<SCRIPT type=text/javascript>(function(){var a="abbr,article,aside,audio,canvas,datalist,details,dialog,eventsource,figure,footer,header,hgroup,mark,menu,meter,nav,output,progress,section,time,video".split(","),b=a.length;while(b--)document.createElement(a[b])})();</SCRIPT>
	<![endif]-->
	<!--[if (lt IE 8.0)]><LINK href="files/qbase.css" type=text/css 
	rel=stylesheet><![endif]--><!--[if (!IE)|(gte IE 8.0)]><!-->
	<link href="base/css/error404.css" type=text/css rel=stylesheet><!--<![endif]-->
	<STYLE type=text/css>.mod-notfound {
		BORDER-RIGHT: #e1e1e1 1px solid; BORDER-TOP: #e1e1e1 1px solid; MARGIN-TOP: 10px; BACKGROUND: #fff; BORDER-LEFT: #e1e1e1 1px solid; BORDER-BOTTOM: #e1e1e1 1px solid; HEIGHT: 460px; TEXT-ALIGN: center; border-radius: 10px
	}
	</STYLE>
  </head>
  <body>
	<SECTION class=mod-page-body>
	<DIV class="mod-page-main wordwrap clearfix">
		<DIV class=x-page-container>
			<DIV class="mod-notfound grid-98">
				<IMG class=img-notfound height=320 src="base/img/notfound.gif"
					width=520>
					<P style="FONT-SIZE: 24px; LINE-HEIGHT: 70px">啊~哦~
						您要查看的页面不存在或已删除！</P>
					<!-- <P style="MARGIN-BOTTOM: 30px">请检查您输入的网址是否正确，或者点击链接继续浏览空间</P> 
					<P style="FONT-SIZE: 14px; LINE-HEIGHT: 20px">
						您可以回到 <A href="#">网站首页</A>
					</P>-->
			</DIV>
		</DIV>
	</DIV>
	</SECTION>
</body>
</html>
