<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="apachePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}" />
<c:set var="appid" value="${sessionScope.appid}" />
<c:set var="account" value="${sessionScope.account}" />
<script type="text/javascript">
var projectPath = "${pageContext.request.scheme}" + "://" + "${pageContext.request.serverName}:" + "${pageContext.request.serverPort}" + "${pageContext.request.contextPath}"+"/";
var appid= "${appid}";
var account= "${account}";
var apachePath = "http://jiaoc8.com:81/";
var editorWinWidth = 1000;
var editorWinHeight = 500;
</script>
<script src="${path}/base/js/boot.js" type="text/javascript"></script> 
<script src="${path}/base/js/jquery.json.js" type="text/javascript"></script> 

<link href="${path}/base/js/uploadify/uploadify.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${path}/base/js/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${path}/base/js/uploadify/jquery.uploadify.min.js"></script>

<script type="text/javascript" src="${path}/base/js/core.ui.js"></script>

<script src="${path}/base/js/fancyzoom1.5/jquery.shadow.js" type="text/javascript"></script>
<script src="${path}/base/js/fancyzoom1.5/jquery.ifixpng.js" type="text/javascript"></script>
<script src="${path}/base/js/fancyzoom1.5/jquery.fancyzoom.js" type="text/javascript"></script>

<script src="${path}/base/js/kindeditor-4.1.9/kindeditor-all-min.js" type="text/javascript"></script>
<script type="text/javascript">


function showImg(uid){
		mini.open({
		   url:"showImg.do",
		   title:"查看图片",
		   allowResize: Boolean,       //允许尺寸调节
		   allowDrag: Boolean,         //允许拖拽位置
		   showCloseButton: Boolean,   //显示关闭按钮
		   showMaxButton: Boolean,     //显示最大化按钮
		   height:300,
		   width:500,
		   onload:function(){
		    var iframe = this.getIFrameEl();
					var data = {
						id : uid
					};
					iframe.contentWindow.SetData(data);
		   },
		   ondestroy:function(){}
		});
}

function pictureHandler(e) {
	if(e.value != null && e.value!= ""){
		var imgs = e.value.split(",");
		var s = "";
		for(var i = 0,len = imgs.length; i < len; i++) 
			s += "<a class=\"New_Button\" href=\"javascript:showImg(\'" + imgs[i] + "\')\">显示图片</a>";
		return s;
	} else 
		return e.value;
	
}

function urlHandler(e) {
	var tempUrl = e.value;
	if(tempUrl.indexOf('http://') == -1) {
		tempUrl = apachePath + e.value;
	}
	return "<a class=\"New_Button\" href=\"" + tempUrl + "\" target=\"_blank\">点击访问</a>";
}


function topControlHandler(e) {
            var record = e.record;
            var uid = record.id;
            var s;
            if(e.value > 0) {
            	s = '<a class="New_Button" href="javascript:topDel(\'' + uid + '\')">取消置顶</a> ';
            } else {
            	s = '<a class="New_Button" href="javascript:topSet(\'' + uid + '\')">置顶</a> ';
            }
            return s;
        }
function orderControlHandler(e) {
            var record = e.record;
            var uid = record.id;
            var s;
            s = '<a class="New_Button" href="javascript:moveUp(\'' + uid + '\')">上移</a> '
				+ '<a class="New_Button" href="javascript:moveDown(\'' + uid + '\')">下移</a> ';        
            return s;
}
        
function showControlHandler(e) {
    var record = e.record;
    var uid = record.id;
    var s;
 
    if(e.value > 0) {
    	s = '<a class="New_Button" href="javascript:showDel(\'' + uid + '\')">取消显示</a> ';
    } else {
    	s = '<a class="New_Button" href="javascript:showSet(\'' + uid + '\')">显示</a> ';
    }
    return s;
}

function pushWebUrl(device) {
	var rows = grid.getSelecteds();
	var module = $('#module-name').text();

	if(appid != "all") {
		mini.alert("您不是超级管理员，无权限使用推送功能");
		return;
	}
	if (rows.length > 0) {
		mini.confirm("确定推送吗？", "确定？", function(action) {
			if (action == "ok") {
				var ids = [];
				for ( var i = 0, l = rows.length; i < l; i++) {
					var r = rows[i];
					ids.push(r.id);
				}
				var id = ids.join(",");
				grid.loading("操作中，请稍后......");
					
    			$.ajax({
                	url: "pushWebUrl.do?ids=" + id + "&module=" + module + "&device=" + device,
                	type: 'get',
                	cache: false,
                	success: function (text) {
               			mini.alert("推送成功");
               			grid.reload();
               			mini.get("msg").setValue("");
                	},
                	error: function (jqXHR, textStatus, errorThrown) {
                	    alert(jqXHR.responseText);
                	}
        		});
			}
			});
		} else {
			mini.alert("请选中一条记录");
		}
   	return;
}

function onUploadSuccess(e) {
	mini.get("img").setValue($("#file_upload").getFileId());
}



var basicFilters = [{ text: '大于', value: '>' }, { text: '小于', value: '<' }, { text: '等于', value: '=='}];
var totalWrapper = $('<div></div>');
</script>
<script type="text/javascript" src="${path}/base/js/kindeditor-4.1.9/kindeditor.js"></script>
<script type="text/javascript" src="${path}/base/js/kindeditor-4.1.9/lang/zh_CN.js"></script>

<style type="text/css">
 body{
     margin:0;padding:0;border:0;width:100%;height:100%;
 }   
 .asLabel .mini-textbox-border,
 .asLabel .mini-textbox-input,
 .asLabel .mini-buttonedit-border,
 .asLabel .mini-buttonedit-input,
 .asLabel .mini-textboxlist-border
 {
     border:0;background:none;cursor:default;
 }
 .asLabel .mini-buttonedit-button,
 .asLabel .mini-textboxlist-close
 {
     display:none;
 }
 .asLabel .mini-textboxlist-item
 {
     padding-right:8px;
 }    
 .New_Button
 {
     font-size:11px;color:#1B3F91;font-family:Verdana;  
     margin-right:5px;
     text-decoration: none;
 }
 .New_Button:hover{
 	color: #ff6600;
 }
 #editor_id {
 	width:850px;height:350px;
 }
 .editor_len {
 	width: 500px;
 }
</style> 
