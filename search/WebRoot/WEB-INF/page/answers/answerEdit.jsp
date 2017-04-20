<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
	<%@ include file="/base/jsp/include.jsp"%>
  </head>
  <body>
    <form id="form" method="post">
        <input name="id1" class="mini-hidden" />
        <input name="id2" class="mini-hidden" />
        <input name="pid" class="mini-hidden" />
        <fieldset style="border:solid 1px #aaa;padding:10px;">
            <legend >问题批阅</legend>
            <div style="padding:5px;">
            <table class="editor_table">
            <tr>
                    <td style="width:100px;">主观题目一：</td>
                    <td style="width:500px;">
                    	<textarea id="q1Content" name="q1Content" emptyText="" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
            <tr>
             <tr>
                    <td style="width:200px;">主观题目一总分：</td>
                    <td style="width:250px;">
                    	<input name="q1point" id="q1point" class="mini-textbox editor_len" allowInput="false"/>
                    </td>
                </tr>
                    <td style="width:100px;">主观题目一答题：：</td>
                    <td style="width:500px;">
                    	<textarea id="q1Answer" name="q1Answer" emptyText="" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                <tr>
                    <td style="width:200px;">输入评分：</td>
                    <td style="width:250px;">
                    	<input name="q1score" id="q1score" vtype="int" class="mini-textbox editor_len" required="true" emptyText="请输入"/>
                    </td>
                </tr>
                
               <!-- <tr>
                    <td style="width:100px;">主观题目二：</td>
                    <td style="width:500px;">
                    	<textarea id="q2Content" name="q2Content" emptyText="请输入" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                
                 <tr>
                    <td style="width:200px;">主观题目二总分：</td>
                    <td style="width:250px;">
                    	<input name="q2point" id="q2point" class="mini-textbox editor_len" allowInput="false"/>
                    </td>
                </tr>
                
               
            <tr>
                    <td style="width:100px;">主观题目一答题：：</td>
                    <td style="width:500px;">
                    	<textarea id="q2Answer" name="q2Answer" emptyText="" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                
                <tr>
                    <td style="width:200px;">输入评分：</td>
                    <td style="width:250px;">
                    	<input name="q2score" id="q2score" vtype="int" class="mini-textbox editor_len" required="true" emptyText="请输入"/>
                    </td>
                </tr>-->
            </table>            
         </div>
        </fieldset>
        
        <div style="text-align:center;padding:10px;">
            <a class="mini-button" onclick="onOk" style="margin-right:20px;">完成评分</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>        
    </form>
   
    <script type="text/javascript">
    	var id = '';
        mini.parse();
        var q1ContentEditor;
		var q1AnswerEditor;
		//var q2ContentEditor;
		//var q2AnswerEditor;
        var form = new mini.Form("form");
        KindEditor.ready(function(K) {    		
    		var options = {
    			//cssPath : '/css/index.css',
        		filterMode : false,
        		minWidth:800,
        		minHeight:200,
//        		uploadJson : '/zsnd/fileUpload.do',
                //fileManagerJson : '/zsnd/base/jsp/file_manager_json.jsp',
                allowFileManager : true
			};
    		q1ContentEditor = K.create('textarea[name="q1Content"]', options);
    		q1AnswerEditor = K.create('textarea[name="q1Answer"]', options);
    		//q2ContentEditor = K.create('textarea[name="q2Content"]', options);
    		//q2AnswerEditor = K.create('textarea[name="q2Answer"]', options);
		});
		
        function SaveData() {
			var o = form.getData();
            form.validate();
            if(form.isValid() == false) return;
            if(parseInt(o.q1score) > parseInt(o.q1point)){
            	mini.alert("实际得分不得超过总分");
            	return;
            }
			//var query ="id1="+o.id1 + "&score1="+o.q1score+"&id2="+o.id2+"&score2="+o.q2score+"&pid="+o.pid;
			var query ="id1="+o.id1 + "&score1="+o.q1score+"&pid="+o.pid;
            $.ajax({
                url: "${path}/checkAnswer.do",
                type: 'post',
                data: query,
                cache: false,
                success: function (text) {
                	if(text.indexOf('true') > -1){
				    	mini.alert("操作成功");
				    	CloseWindow("save");
				    }else{
				    	mini.alert("操作失败");
				    	CloseWindow();
				    }
                },
                error: function (textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }
        
        //标准方法接口定义
        function SetData(data) {
       		q1AnswerEditor.sync();
            q1ContentEditor.sync();
            //q2AnswerEditor.sync();
            //q2ContentEditor.sync();
			//html = $('#editor_id').val(); // jQuery
			//mini.get("contextReal").setValue(html);
            if (data.action == "edit") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
                mini.getbyName("pid").setValue(data.id);
                $.ajax({
                    url: "findByIdAnswer.do?id=" + data.id,
                    cache: false,
                    success: function (text) {
                    
                        var o = mini.decode(text);
                        for(var i = 0 ; i < o.length; i++){
                        	var obj = o[i];
                        	mini.getbyName("id" + (i+1)).setValue(obj.id);
                        	mini.getbyName("q"+(i+1)+"point").setValue(obj.score);
                        	if(i == 0){
                        		q1AnswerEditor.html(obj.questionAns);
						        q1ContentEditor.html(obj.questionKey);
                        	}else if(i == 1){
                        		q2AnswerEditor.html(obj.questionAns);
						        q2ContentEditor.html(obj.questionKey);
                        	}
                        }
                    }
                });
            }
        }

        function GetData() {
            var o = form.getData();
            return o;
        }
        function CloseWindow(action) {
            if (action == "cancel" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                	SaveData();
                	return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
        	//var imgId = mini.get("img").getValue();
        	/*var imgId = mini.get("img").getValue();
        	if(imgId == ""){
        		mini.alert("请上传图片");
        		return ;
        	}*/
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
        function onUploadSuccess(e) {
          mini.get("img").setValue($("#file_upload").getFileId());
        }
    </script>
  </body>
</html>
