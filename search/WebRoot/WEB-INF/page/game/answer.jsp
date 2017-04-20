<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
  <head>
    <title>答题</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/base/jsp/includeweb.jsp"%>
	<script src="${path}/base/js/kindeditor-4.1.9/kindeditor-all-min.js" type="text/javascript"></script>
	<link type="text/css" rel="stylesheet" href="${path}/base/js/kindeditor-4.1.9/themes/simple/simple.css"/>
	<link type="text/css" rel="stylesheet" href="${path}/base/css/game.css"/>
  </head>
  
  <body>
  <%@ include file="/base/jsp/background.jsp"%>
  <%@ include file="/base/jsp/header-warn.jsp"%>
   <div id="content-wrapper" class="content-wrapper clear">
       
       <div class="time-wrapper">倒计时：<span id="time-min">00</span>分钟<span id="time-second">00</span>秒</div>
       <div class="question-wrappper">
       <div class="question-tip">第<span class="number">${detail.rank}</span>题(
       <span class="type-tip">
        <c:choose>
            <c:when test="${question.type==1}">
            单选
            </c:when>
             <c:when test="${question.type==2}">
            多选
            </c:when>
            <c:otherwise>
            主观
            </c:otherwise>
        </c:choose>
        ：${question.point}分
       </span>)：
       </div>
        <div class="question-content-wrapper">
       <c:if test="${question.background != '' and question.background != null}">
      <div class="question-background clear">
                       <strong> 任务背景：</strong><span class="background"></span>
       </div>
       </c:if>
       <div class="question-content">
       <strong>任务要求：</strong>    <span class="content"></span>
       </div>
       <div class="question-option clear">
       <c:choose>
       <c:when test="${question.type==1}">
       <label><input type="radio" class="option" name="option" value="A"/><span class="option-content"></span></label>
       <label><input type="radio" class="option" name="option" value="B"/><span class="option-content"></span></label>
       <label><input type="radio" class="option" name="option" value="C"/><span class="option-content"></span></label>
       <label><input type="radio" class="option" name="option" value="D"/><span class="option-content"></span></label>
       <c:if test="${question.option5 != '' and question.option5 != null}">
       <label><input type="radio" class="option" name="option" value="E"/><span class="option-content"></span></label>
       </c:if>
        <c:if test="${question.option6 != '' and question.option6 != null}">
       <label><input type="radio" class="option" name="option" value="F"/><span class="option-content"></span></label>
       </c:if>
       </c:when>
       <c:when test="${question.type==2}">
       <label><input type="checkbox" class="option" name="option" value="A"/><span class="option-content"></span></label>
       <label><input type="checkbox" class="option" name="option" value="B"/><span class="option-content"></span></label>
       <label><input type="checkbox" class="option" name="option" value="C"/><span class="option-content"></span></label>
       <label><input type="checkbox" class="option" name="option" value="D"/><span class="option-content"></span></label>
       <c:if test="${question.option5 != '' and question.option5 != null}">
       <label><input type="checkbox" class="option" name="option" value="E"/><span class="option-content"></span></label>
       </c:if>
        <c:if test="${question.option6 != '' and question.option6 != null}">
       <label><input type="checkbox" class="option" name="option" value="F"/><span class="option-content"></span></label>
       </c:if>
       </c:when>
       <c:otherwise>
       <textarea id="answer-textarea" name="answer-textarea" class="answer-textarea"></textarea>
       </c:otherwise>
     </c:choose>
     </div>
     </div>
     <input id="type" type="hidden" value="${question.type}"/>
     <input id="detailId" type="hidden" value="${detail.id}"/>
     <input id="detailPid" type="hidden" value="${detail.pid}"/>
     </div>
       <!-- end of question -->
     <div class="button-wrapper">
       <input id="submit" class="but-org" type="button" value="提交"/>
       <span class="warn-info"></span>
     </div>
       
    </div>
        <%@ include file="/base/jsp/footer.jsp"%>
  </body>
  <script type="text/javascript">
  var left = "${answer.timeLeft}";
  var restTimer = window.setInterval("setRestTime()", 1000);
  var level = "${level}";
  var content = "${question.content}";
  var background = "${question.background}";
  var option1 = "${question.option1}";
  var option2 = "${question.option2}";
  var option3 = "${question.option3}";
  var option4 = "${question.option4}";
  var option5 = "${question.option5}";
  var option6 = "${question.option6}";
  
  var type= $("#type").val();
  var id = $("#detailId").val();
  var pid = $("#detailPid").val();
  var editor;
  KindEditor.ready(function(K) {});
  

  /*var EventUtil = {
    	getEvent: function (event) {
       		return event ? event : window.event;
    	},
    	addHandler: function (element, type, handler) {
        	if (element.addEventListener) {
            	element.addEventListener(type, handler, false);
        	} else if (element.attachEvent) {
            	element.attachEvent("on" + type, handler);
        	} else {
            	element["on" + type] = handler;
        	}
    	}
	};

	EventUtil.addHandler(window, "beforeunload", function (event) {
    	//$("span.content").html("提交了");
    	
    	request("syschroTime.do","id="+pid+"&timeLeft="+left,null);
    	//window.setTimeout(function(){alert("dd")},1000);
    	event = EventUtil.getEvent(event);
    	//event.preventDefault();
    	//
    	//alert("dd");
    	//return false;
    	//event.returnValue = "";
	});*/

  $(function(){
      initQuestionOption();
      loadImg();
      if(type == 3){
      	  initEditor();
      }
	  $("#submit").click(function(){
		  var answer = getAnswer();
		  if(!isDefine(answer)){
			  $(".warn-info").text("请先选择答案");
			  return ;
		  }else{
		  	 $(".warn-info").text("");	
		  }
		  var query = "id="+id+"&pid="+pid+"&answer="+encodeURIComponent(answer)+"&timeLeft="+left+"&type="+type;
		  //alert(query);
		  //$(".question-wrappper").remove();
		  request("answerQues.do",query,function(data){
			  //说明返回的是最终的结果
			  processResult(data);
          });
          
	  });
	 
	 $(window).bind('beforeunload',function(){
		  request("syschroTime.do","id="+pid+"&timeLeft="+left,function(data){});
		  //alert(pid + "  " + left);
		  //return "答题尚未结束，确定离开么？(答题状态将会保存)";
	  });
  });
  
  
 
  function initQuestionOption(){
      $(".content").html(content);
	  $(".question-background span").html(background);
	 if(type != 3){
      $(".question-option").find(".option-content").eq(0).html(option1);
	  $(".question-option").find(".option-content").eq(1).html(option2);
	  $(".question-option").find(".option-content").eq(2).html(option3);
	  $(".question-option").find(".option-content").eq(3).html(option4);
	  if(isDefine(option5)){
	     $(".question-option").find(".option-content").eq(4).html(option5);
	  }
	  if(isDefine(option6)){
	     $(".question-option").find(".option-content").eq(5).html(option6);
	  }
	 }
  }
  
  function getAnswer(){
	  var ans="";
	  if(type == 1){
		  ans = $(".option:checked").val();
	  }else if(type == 2){
		  $(".option:checked").each(function(){
			 ans += $(this).val();
		  });
	  }else{
	  	 ans = editor.html();
	  }
	  return ans;
  }
  
  
  
  function processResult(data){
	  if(isDefine(data.isOver)){
	     $(window).unbind('beforeunload');
	    
	     if(level == 2){
	      	showInfoForOver("duang！主页君已经接收你的试卷啦~~我们有“砖家”认真批改试卷，出结果后可以查看结果公示，也有专门人员通知的哦~~");
	      }else{
	      	showInfoForOver("答题结束，您的总分为:" + data.score +"分");
	      }
		  
	  }else{
		  //alert(data.content);
		  var ques = data.question;
		  var detail = data.detail;
		  id = detail.id;
		  type = ques.type;
		  if(detail.lastStatus == 1){
		  	  showInfo("这一题您已经提交过");
		  }else{
		  	  showInfo("提交成功",'',true);
		  }
		  $(".question-background").remove();
		  $(".question-option").remove();
		  //设置提示信息
		  $(".question-tip .number").text(detail.rank);
		  var typeTip = "单选";
		  if(ques.type == 2){
		      typeTip ="多选";
		  }else if(ques.type == 3){
		  	typeTip = "主观";
		  }
		  typeTip += "："+ques.point+"分";
		  $(".question-tip .type-tip").text(typeTip);
		  //设置题目内容
		  $(".question-content .content").html(ques.content);
		  //题目背景不为空的时候才显示
		  if(isDefine(ques.background)){
		     var back = $('<div class="question-background clear">  <strong> 任务背景： </strong> <span class="background"></span></div>');
		     back.find(".background").html(ques.background);
		  	 $(".question-content").before(back);
		  }
		  //设置选项
		  var radio1 = $('<label><input type="radio" class="option" name="option" value="A"/></label>');
		  var radio2 = $('<label><input type="radio" class="option" name="option" value="B"/></label>');
		  var radio3 = $('<label><input type="radio" class="option" name="option" value="C"/></label>');
		  var radio4 = $('<label><input type="radio" class="option" name="option" value="D"/></label>');
		  var radio5 = $('<label><input type="radio" class="option" name="option" value="E"/></label>');
		  var radio6 = $('<label><input type="radio" class="option" name="option" value="F"/></label>');
		  var check1 = $('<label><input type="checkbox" class="option" name="option" value="A"/></label>');
		  var check2 = $('<label><input type="checkbox" class="option" name="option" value="B"/></label>');
		  var check3 = $('<label><input type="checkbox" class="option" name="option" value="C"/></label>');
		  var check4 = $('<label><input type="checkbox" class="option" name="option" value="D"/></label>');
		  var check5 = $('<label><input type="checkbox" class="option" name="option" value="E"/></label>');
		  var check6 = $('<label><input type="checkbox" class="option" name="option" value="F"/></label>');
		  var option1 = $('<span class="option-content"></span>');
		  var option2 = $('<span class="option-content"></span>');
		  var option3 = $('<span class="option-content"></span>');
		  var option4 = $('<span class="option-content"></span>');
		  var option5 = $('<span class="option-content"></span>');
		  var option6 = $('<span class="option-content"></span>');
		  var wrapper = $('<div class="question-option clear"></div>');
		  var area = $("<textarea id='answer-textarea' name='answer-textarea' class='answer-textarea'></textarea>");
		  if(ques.type == 1){
		       option1.html(ques.option1);
		       radio1.find("input").after(option1);
		       option2.html(ques.option2);
		       radio2.find("input").after(option2);
		       option3.html(ques.option3);
		       radio3.find("input").after(option3);
		       option4.html(ques.option4);
		       radio4.find("input").after(option4);
		       wrapper.append(radio1).append(radio2).append(radio3).append(radio4);
		       if(isDefine(ques.option5)){
		         option5.html(ques.option5);
		         radio5.find("input").after(option5);
		         wrapper.append(radio5);
		          if(isDefine(ques.option6)){
		              option6.html(ques.option6);
		              radio6.find("input").after(option6);
		              wrapper.append(radio6);
		          }
		       }
		  }else if(type == 2){
		       option1.html(ques.option1);
		       check1.find("input").after(option1);
		       option2.html(ques.option2);
		       check2.find("input").after(option2);
		       option3.html(ques.option3);
		       check3.find("input").after(option3);
		       option4.html(ques.option4);
		       check4.find("input").after(option4);
		       wrapper.append(check1).append(check2).append(check3).append(check4);
		       if(isDefine(ques.option5)){
		         option5.html(ques.option5);
		         check5.find("input").after(option5);
		         wrapper.append(check5);
		          if(isDefine(ques.option6)){
		              option6.html(ques.option6);
		              check6.find("input").after(option6);
		              wrapper.append(check6);
		          }
		       }
		  }else{
		  	wrapper.append(area);
		  }
		  $(".question-content-wrapper").append(wrapper);
		  if(type == 3){
		    $(".answer-textarea").focus();
		  	initEditor();
		  }
		  loadImg();
		  //location.reload();
	  }
  }
  
  //计算活动开始剩余时间
   function setRestTime() {
	  
      var seconds = left%60;
      var minutes=Math.floor(left/60);
      left --;
      if(left % 300 ==0){
      	request("syschroTime.do","id="+pid+"&timeLeft="+left,null);
      }
      if(minutes <= 0 && seconds <= 0) {
        clearInterval(restTimer);
        //到时间后自动提交结果
        var answer = getAnswer();
        var query = "id="+id+"&pid="+pid+"&answer="+encodeURIComponent(answer)+"&timeLeft="+left+"&type="+type;
		  //alert(query);
		  //$(".question-wrappper").remove();
		  request("answerQues.do",query,function(data){
			  //说明返回的是最终的结果
			  processResult(data);
          });
      } else {
    	  
        $('#time-min').text(minutes < 10 ? '0' + minutes : minutes);
        $('#time-second').text(seconds < 10 ? '0' + seconds : seconds);
      }
    }
    
    function initEditor(){
    	

KindEditor.ready(function(K) { 	
    		var options = {
    			//cssPath : '/css/index.css',
        		filterMode : false,
        		themeType : 'simple',
        		minWidth:800,
        		minHeight:200,
//        		uploadJson : '/zsnd/fileUpload.do',
                //fileManagerJson : '/zsnd/base/jsp/file_manager_json.jsp',
                allowFileManager : true
			};
    		editor = K.create('textarea[name="answer-textarea"]', options);
    		ajustHeight();
});
}
  
  

</script>

</html>
