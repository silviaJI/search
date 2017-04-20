<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="projectPath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<c:set var="apachePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}" />
<c:set var="account" value="${sessionScope.account}" />
<script type="text/javascript">
var apachPath = "${apachePath}/";
var apacheTempPath="http://jiaoc8.com:81/";
var projectPath = "${projectPath}";
var path="${path}";
var imgUrl = "${projectPath}downloadweb.do?id=";
var tempImgSrc = "${path}/base/img/loading.gif";
var username = '';
var userid = '';
var entranceYear;
</script>
<link href="${path}/base/css/global.css" type="text/css" rel="stylesheet" />
<link href="${path}/base/css/ui-dialog.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${path}/base/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${path}/base/js/core.ui.js"></script>
<script src="${path}/base/js/jQuery.md5.js" type="text/javascript"></script>
<script src="${path}/base/js/dialog-min.js" type="text/javascript"></script>
<script src="${path}/base/js/util.js" type="text/javascript"></script>

<script type="text/javascript">
   

var index = null;

    $(function(){
      ajustHeight();
      if(!IsPC() && !isDefine(index)){
      	alert("请使用PC端答题");
      	location.href="index.do";
      }
    });
    
    function IsPC() {
        var userAgentInfo = navigator.userAgent;
        var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
        var flag = true;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    
    //显示加载框
    function loading(){
	    var loading = '<div id="loading" style="width:100%; height: 100%; position: absolute; top: 0px; left: 0px; z-index: 9000;background-color: #000000; opacity: 0.5; filter:alpha(opacity=50);">'
	    +'<div id="loading-img" style="margin:0px auto;width:124px;" ><img src="${path}/base/img/loading.gif"/></div></div>';
	    $("body").append(loading);
	    var scrollH = $(document).scrollTop();
	    var docH = $(document).height();
	    $("#loading").css({"height":docH});
	    $("#loading #loading-img").css({"margin-top":scrollH+150});
	    $("#loading").show("slow");
    }
    //停止加载框
    function stopLoad(){
	    $("#loading").hide("slow");
	    $("#loading").remove();
    }
    //简单的form检查
    function formValidate(id){
        var fid = "#"+id;
        var result = true;
        $(fid).find("input[type=text]").each(function(){
            if($(this).attr("req")=="true" && $(this).val()==""){
                showInfo("信息输入不完整");
                result = false;
                return false;
            }
        });
        return result;
    }
    
    function loadImg(){
        	$("img[src]").load(function(){
			 var __this__ = $(this); 
			 //var url = __this__.attr('data'); 
			 var src = __this__.attr('src');
			 var img =new Image();//实例化一个图片的对象
			 img.src = src;//将要显示的图片加载进来  
			 if(img.complete)//如果图片已经加载存在浏览器缓存中直接处理  
		     {  
		        ajustHeight();
		        //__this__.attr('src',url);//将要显示的图片替换过来  
		        return;  
		     }
		     img.onload =function(){//要显示的图片加载完成后做处理  
		      ajustHeight();
		        //__this__.attr('src',url);
		     } 
		});
    }
   
    function ajustHeight(){
        var screenH = $(window).height();
        var headerHeight = $("#header").height();
        var footerHeight = $("#footer").height();
        $("#content-wrapper").css("min-height",screenH-headerHeight-footerHeight);
        var bodyHeight = $(document).height();
        //alert(document.body.scrollHeight);
        //alert(screenH-headerHeight-footerHeight);
        $("img.background").height(document.body.scrollHeight+footerHeight);
    }
    
    function getImg(id){
        var path = "";
        $.ajax({
		   type:"post",
		   async:false,
		   url:imgUrl+id,
		   data:"&isM=1",
		   cache:false,
		   timeout:30000,
		   success:function(text){
		     path = text;
		   },
           complete:function(xhr, ts){
             //stopLoad();
           }
	   });
	   return apacheTempPath + "bs/" + path;
    }
     //ajax请求封装函数
     function requestWithoutCheck(url ,data, success){
      //loading();
      $.ajax({
		type:"post",
		url:url,
		data:data,
		cache:false,
		timeout:30000,
		success:function(text){
		//alert(text);
		   var obj = eval('(' + text + ')');
	       success(obj);
		},
        complete:function(xhr, ts){
          //stopLoad();
       }
	 });
     }
    
     //ajax请求封装函数
     function request(url ,data, success){
      //loading();
      $.ajax({
		type:"post",
		url:url,
		data:data,
		cache:false,
		timeout:30000,
		success:function(text){
		//alert(text);
		   var obj = eval('(' + text + ')');
	         if(obj.errorCode != 0){
                 if(url.indexOf("userRegist") > -1 || url.indexOf("passMail") > -1 || url.indexOf("Mlogin") > -1 || url.indexOf('saveUser') > -1 || url.indexOf('MChangePass') > -1){
                     showInfo(obj.errorMsg);
                 }else{
                     showInfo("操作失败");
                 }
                 return ;
             }else{
                  var data = obj.data;
                  success(data);
             }
		},
        complete:function(xhr, ts){
          stopLoad();
       }
	 });
     }
     
     //处理url，如果不是完整的url，那么就需要附加一些
     function proUrl(url){
         if(url.indexOf("http") < 0){
             url = apacheTempPath + url;
         }
         return url;
     }
     
    //判断是否是空
    function isDefine(value){
       if(value == null || value == "" || value == "undefined" || value == "(null)" || value == 'NULL' || typeof(value) == 'undefined'){
          return false;
       }
       return true;
    }
    
    
    function showInfoForOver(content){
    	var d = dialog({
    		title:'答题结束',
    		content: content,
    		cancel: false,
    		width:250,
    		okValue: '确定',
    		ok: function () {
        		location.href="game.do";
    		}
    	});
    	d.show();
    }
    
    function showInfo(content,title,disappear){
    	if(!isDefine(title)){
    		title = "提示";
    	}
    	var d = dialog({
    		title:title,
    		content: content,
    		width:250,
    		okValue: '确定',
    		ok: function () {
        		d.close().remove();
        		goTopEx();
    		}
    	});
    	if(isDefine(disappear) && disappear == true){
    		setTimeout(function () {
    			d.close().remove();
    			goTopEx();
			}, 2000);	
    	}
		d.show();
    }
    
    function goTopEx(){
	    //alert(document.body.childNodes[1].innerHTML);
        //var obj=document.getElementById("goTopBtn");
        function getScrollTop(){
			     return document.documentElement.scrollTop||document.body.scrollTop;
        }
        function setScrollTop(value){
			     if(document.body.scrollTop)
				 {
					 document.body.scrollTop=value;
				 }
				 else 
				 if(document.documentElement.scrollTop)
				 {
                    document.documentElement.scrollTop=value;
				 }
				
            }    
        window.onscroll=function(){
        }
         var goTop=setInterval(scrollMove,10);
            function scrollMove(){
                    setScrollTop(getScrollTop()/1.1);
                    if(getScrollTop()<1)clearInterval(goTop);
          }
    }
    
</script>
<script src="${path}/base/js/mobileRedirect.js" type="text/javascript"></script>