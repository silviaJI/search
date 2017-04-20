<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>检索大赛后台系统</title>
	<%@ include file="/base/jsp/include.jsp"%>
	<style type="text/css">
    html, html body
    {
        padding:0;border:0;margin:0;
        width:100%;height:100%;overflow:hidden;
        font-family:Verdana;
        font-size:12px;    
        line-height:22px;
    }
    .header{background:url(base/images/header.gif) repeat-x;}
    .mini-layout-region-south img{vertical-align:top;}
 	.Note{background:url(base/images/Notes_Large.png) no-repeat;width:32px;height:32px;}
    .user{background:url(base/images/user.png) no-repeat;width:32px;height:32px;}
    .Order{background:url(base/images/order-1.png) no-repeat;width:32px;height:32px;}
    .customers{background:url(base/images/customers.png) no-repeat;width:32px;height:32px;}
    .delicious{background:url(base/images/delicious.png) no-repeat;width:32px;height:32px;}
    .lightbulb{background:url(base/images/lightbulb.png) no-repeat;width:32px;height:32px;}
    .admin{background:url(base/images/administrative-docs.png) no-repeat;width:32px;height:32px;}
    .comment{background:url(base/images/comment.png) no-repeat;width:32px;height:32px;}
    .Config{background:url(base/images/config.png) no-repeat;width:32px;height:32px;}
    .Cv{background:url(base/images/cv.png) no-repeat;width:32px;height:32px;}
    .Design{background:url(base/images/graphic-design.png) no-repeat;width:32px;height:32px;}
    .heart{background:url(base/images/heart.png) no-repeat;width:32px;height:32px;}
    .link{background:url(base/images/link.png) no-repeat;width:32px;height:32px;}
    .payment{background:url(base/images/payment-card.png) no-repeat;width:32px;height:32px;}
    .product{background:url(base/images/product.png) no-repeat;width:32px;height:32px;}
    .photography{background:url(base/images/photography.png) no-repeat;width:32px;height:32px;}
    .publish{background:url(base/images/publish.png) no-repeat;width:32px;height:32px;}
    .zoom{background:url(base/images/zoom.png) no-repeat;width:32px;height:32px;}
    </style>
  </head>
  <body style="padding:0;margin:0">
	<!--Layout-->
	<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
	    <div class="header" region="north" height="80" showSplit="false" showHeader="false">
	        <h1 style="margin:0;padding:15px;cursor:default;font-family:微软雅黑,黑体,宋体;">后台管理系统</h1>
	        <div style="position:absolute;top:5px;right:10px;text-align: right;">
	        	<div style="t-size:12px;line-height:25px;font-weight:normal;text-align: right;">
		    	选择皮肤：
	            <select id="selectSkin" onchange="onSkinChange(this.value)" style="width:100px;margin-right: 10px" >
	                <option value="">Default</option>
	                <option value="blue">Blue</option>
	                <option value="gray">Gray</option>
	                <option value="olive2003">Olive2003</option>
	                <option value="blue2003">Blue2003</option>
	                <option value="blue2010">Blue2010</option>
	            </select>
	            </div>
	            <a class="mini-button mini-button-iconTop" iconCls="icon-edit" onclick="changePwd"  plain="true" >修改密码</a>      
	            <a class="mini-button mini-button-iconTop" iconCls="icon-close" 
	            onclick="mini.confirm('确定注销?', '确定?',function (action) {if (action == 'ok') {location.href = 'logoutM.do';}});"  plain="true" >注销</a>        
	        </div>
    	</div>
	   <!--<div title="south" region="south" showSplit="false" showHeader="false" height="25">    
	    <center>Copyright ©  xxxx有限公司版权所有 </center>
	    </div>   -->
	    <div title="center" region="center" bodyStyle="overflow:hidden;">    
	        <!--Splitter-->
	        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
	            <div size="180" maxSize="180" minSize="180" showCollapseButton="true">
	                <!--OutlookMenu-->
					<!--<div class="mini-outlookmenu" url="base/menu.json" onitemselect="onItemSelect" id="menu"
					    idField="id" parentField="pid" textField="text">
					</div>
					-->
					<!--OutlookTree-->
                        <div id="leftTree" class="mini-outlooktree" url="base/menu.json" onnodeclick="onNodeSelect"
                           textField="text" idField="id" parentField="pid" expandOnLoad="0">
                        </div>
	            </div>
	            <div showCollapseButton="false">
	                <!--Tabs-->
					<div id="mainTabs" class="mini-tabs bg-toolbar" activeIndex="0" style="width:100%;height:100%;"      
					    bodyStyle="border:0;background:white;">
					    <div title="首页" url="base/welcome.jsp">        
					    </div>
					</div>
	            </div>        
	        </div>
	    </div>
	</div>
<div id="pwdWindow" class="mini-window" title="修改密码" style="width:350px;height:180px;" showModal="true" showCloseButton="true">
    <div id="pwdForm" style="padding:15px;padding-top:10px;">
        <table >
            <tr>
                <td style="width:60px;"><label for="oldpwd$text">初始密码：</label></td>
                <td>
                    <input id="oldpwd" name="oldpwd" class="mini-password" requiredErrorText="初始密码不能为空" class="mini-textbox" required="true" style="width:150px;"/>
                </td>    
            </tr>
            <tr>
                <td style="width:60px;"><label for="newpwd$text">新密码：</label></td>
                <td>
                    <input id="newpwd" name="newpwd" class="mini-password" requiredErrorText="新密码不能为空" required="true" style="width:150px;"/>
                </td>
            </tr>      
            <tr>
                <td style="width:60px;"><label for="newpwd2$text">确认密码：</label></td>
                <td>
                    <input id="newpwd2" name="newpwd2" class="mini-password" onvalidation="onPwdValidation" requiredErrorText="确认密码不能为空" required="true" style="width:150px;"/>
                </td>
            </tr>      
            <tr>
                <td></td>
                <td style="padding-top:5px;">
                    <a onclick="onPwdClick" class="mini-button" style="width:60px;">修改</a>
                    <a onclick="onResetClick" class="mini-button" style="width:60px;">重置</a>
                </td>
            </tr>
        </table>
    </div>
</div>
  	<script type="text/javascript">
	if(appid == "lrf") {
		$('.header h1').text("订单管理系统");
	}
    
  	$(function(){
  			$("#selectSkin").val(getCookie("miniuiSkin"));
  		});
	  	mini.parse();
	    var pwdWindow = mini.get("pwdWindow");
	    function changePwd(){
	    	pwdWindow.show();
	    }
	    function onPwdValidation(e) {
            if (e.isValid) {
            	var pwd = mini.get("newpwd").getValue();
                if (e.value != pwd) {
                    e.errorText = "密码输入不一致";
                    e.isValid = false;
                }
            }
        }
	    
	    function onPwdClick(e) {
	        var form = new mini.Form("#pwdWindow");
	        form.validate();
	        if (form.isValid() == false) return;
	        $.ajax({ 
	        	url: "changePwd.do", 
	        	data:form.getData(),
	        	success: function(data){
	            	if(data == 'success'){
	            		mini.alert("修改成功");
	            		pwdWindow.hide();
	            		form.clear();
	            	}else{
	            		mini.alert("初始密码不正确");
	            	}
	        	}
	        });
	    }
	    
	    function onResetClick(e) {
	        var form = new mini.Form("#pwdWindow");
	        form.clear();
	    }
	  	
		function onItemSelect(e) {
		    var item = e.item;
		    showTab(item);
		}

		function showTab(node) {
		    var tabs = mini.get("mainTabs");            
		    var id = "tab$" + node.id;
		    var tab = tabs.getTab(id);
		    if (!tab) {
		        tab = {};
		        tab.name = id;
		        tab.title = node.text;
		        tab.showCloseButton = true;
		        tab.url = node.url;
		        tabs.addTab(tab);
		    }
		    tabs.activeTab(tab);
		}        
		
		var tabs = mini.get("mainTabs");
		var menu = mini.get("leftTree");
		function showTabTree(node) {
		    var tabs = mini.get("mainTabs");            
		    var id = "tab$" + node.id;
		    var tab = tabs.getTab(id);
		    
		    if (!tab) {
		        tab = {};
		        tab.name = id;
		        tab.title = node.text;
		        tab.showCloseButton = true;
		        tab.url = node.url;
		        tabs.addTab(tab);
		    }
		    tabs.activeTab(tab);
		}        

		function onNodeSelect(e) {
		    var node = e.node;
		    var pid = node.pid;
		    
		    var isLeaf = e.isLeaf;
		    if (isLeaf) {
		    	showTabTree(node);
		    }
		}

		
		tabs.on("closeclick", function () {
			var tabname = tabs.getActiveTab().name;
			var nodeid = tabs.getActiveTab().name.substring(4,tabname.length);
			menu.selectNode(nodeid);
	    });
		
		tabs.on("activechanged", function () {
			var tabname = tabs.getActiveTab().name;
			var nodeid = tabs.getActiveTab().name.substring(4,tabname.length);
			menu.selectNode(nodeid);
	    });
	</script>  
  </body>
</html>
