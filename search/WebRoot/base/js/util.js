function getParameter(name) {
var url = location.search;
	if (url.indexOf("?") != -1) {
      	var str = url.substr(1);
      	strs = str.split("&");
      	for(var i = 0; i < strs.length; i++) {
        	 if(strs[i].split("=")[0] == name) 
			 	return decodeURI(strs[i].split("=")[1]);
   	    }
	}
	return null;
}
function getCookie(name){
	var str = document.cookie;
	if(!str || str.indexOf(name + "=") < 0)
		return;
	var cookies = str.split("; ");
	for(var i=0; i<cookies.length; i++){
		var cookie = cookies[i];
		if(cookie.indexOf(name + "=") == 0){
			var value = cookie.substring(name.length + 1);
			return decodeURI(value);
		}
	}
}
function setCookie(name, value){
	// document.cookie = name + "=" + encodeURI(value);
    var expires = (arguments.length > 2) ? new Date(arguments[2]) : null;
    var path = (arguments.length > 3) ? arguments[3] : null;
    var domain = (arguments.length > 4) ? arguments[4] : null;
    var secure = (arguments.length > 5) ? arguments[5] : false;
    document.cookie = encodeURI(name) + "=" + encodeURI(value) +
      ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
      ((path == null) ? "" : ("; path=" + path)) +
      ((domain == null) ? "" : ("; domain=" + domain)) +
      ((secure == true) ? "; secure" : "") + ";";
   
}
function deleteCookie(name) {
	var str = document.cookie; 
	var exp = new Date();
	var path = (arguments.length > 1) ? arguments[1] : null;
	exp.setTime(exp.getTime()  -  1);
	if(!str || str.indexOf(name) < 0) 
		return;
	var cookie = encodeURI(name) + "=" + getCookie(name) + ";  expires="+  exp.toGMTString() + ((path == null) ? "" : ("; path=" + path));
	document.cookie = cookie;
}

function navigatorSuggest(){
 	var browser=navigator.appName;
	if(browser=="Microsoft Internet Explorer"){
	   	var b_version=navigator.appVersion;
		var version=b_version.split(";"); 
		var trim_Version=version[1].replace(/[ ]/g,"");
		if(trim_Version=="MSIE6.0") 
	{ 
		alertNavigator();
	} 
	else if(trim_Version=="MSIE7.0") 
	{ 
		alertNavigator();
	} 
	else if(trim_Version=="MSIE8.0") 
	{ 
		alertNavigator();
	}
	}
  }
  
  function alertNavigator(){
  	alert("您当前的IE版本过低，请使用IE9及以上,CHROME,FireFox,Opera或者360浏览器、搜狗浏览器等国产浏览器的极速模式");
  	location.href="index.do";
  }
