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
        <input name="id" class="mini-hidden" />
        <fieldset style="border:solid 1px #aaa;padding:10px;">
            <legend >问题信息</legend>
            <div style="padding:5px;">
            <table class="editor_table">
            
            <tr>
                    <td style="width:100px;">题目要求：</td>
                    <td style="width:500px;">
                    	<textarea id="content-editor" name="content-editor" emptyText="请输入" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                <tr style="display:none;">
                    <td style="width:70px;">题目要求正文：</td>
                    <td style="width:150px;">
                    	<textarea name="content" id="content" class="mini-textarea"></textarea>
                    </td>
                </tr>
                <tr>
                    <td style="width:100px;">题目背景：</td>
                    <td style="width:500px;">
                    	<textarea id="background-editor" name="background-editor" emptyText="请输入" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                 <tr style="display:none;">
                    <td style="width:70px;">题目背景正文：</td>
                    <td style="width:150px;">
                    	<textarea name="background" id="background" class="mini-textarea"></textarea>
                    </td>
                </tr>
 
                <tr>
                    <td style="width:200px;">题目类别：</td>
                    <td style="width:250px;">
                    	 <input name="type" id="type" value="1" required="true" class="mini-combobox" textField="text" valueField="id" emptyText="类型"
                        url="${path}/base/json/type.json" allowInput="false" showNullItem="true" nullItemText="请选择..."/>
                    </td>
                </tr>
            
                <tr>
                    <td style="width:50px;">题目难易：</td>
                    <td style="width:150px;">
                      <input name="rank" id="rank" value="1" required="true" class="mini-combobox" textField="text" valueField="id" emptyText="类型"
                        url="${path}/base/json/rank.json" allowInput="false" showNullItem="true" nullItemText="请选择..."/>
                    </td>
                 </tr>
                 
                  <tr>
                    <td style="width:50px;">题目领域：</td>
                    <td style="width:150px;">
                     <input name="area" id="area" value="1" required="true" class="mini-combobox" textField="text" valueField="id" emptyText="类型"
                        url="${path}/base/json/area.json" allowInput="false" showNullItem="true" nullItemText="请选择..."/>
                    </td>
                 </tr>
                 
               <tr>
                    <td style="width:200px;">答案：</td>
                    <td style="width:250px;">
                    	<input name="answer" id="answer" class="mini-textbox editor_len" vtype="maxLength:250" emptyText="请输入"/>
                    </td>
                </tr>
                
                <tr>
                    <td style="width:200px;">题目分值：</td>
                    <td style="width:250px;">
                     <select name="point" id="point" class="mini-radiobuttonlist">
                     	<option value="5">5</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                        <option value="40">40</option>
                        <option value="0">人工判定</option>
                   	 </select>
                    </td>
                </tr>
                 <tr>
                    <td style="width:100px;">选项一：</td>
                    <td style="width:500px;">
                    	<textarea id="option1-editor" name="option1-editor" emptyText="请输入" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                 <tr style="display:none;">
                    <td style="width:70px;">选项一正文：</td>
                    <td style="width:150px;">
                    	<textarea name="option1" id="option1" class="mini-textarea"></textarea>
                    </td>
                </tr>
                
                <tr>
                    <td style="width:100px;">选项二：</td>
                    <td style="width:500px;">
                    	<textarea id="option2-editor" name="option2-editor" emptyText="请输入" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                 <tr style="display:none;">
                    <td style="width:70px;">选项二正文：</td>
                    <td style="width:150px;">
                    	<textarea name="option2" id="option2" class="mini-textarea"></textarea>
                    </td>
                </tr>
                
                <tr>
                    <td style="width:100px;">选项三：</td>
                    <td style="width:500px;">
                    	<textarea id="option3-editor" name="option3-editor" emptyText="请输入" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                 <tr style="display:none;">
                    <td style="width:70px;">选项三正文：</td>
                    <td style="width:150px;">
                    	<textarea name="option3" id="option3" class="mini-textarea"></textarea>
                    </td>
                </tr>
                
                <tr>
                    <td style="width:100px;">选项四：</td>
                    <td style="width:500px;">
                    	<textarea id="option4-editor" name="option4-editor" emptyText="请输入" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                 <tr style="display:none;">
                    <td style="width:70px;">选项四：</td>
                    <td style="width:150px;">
                    	<textarea name="option4" id="option4" class="mini-textarea"></textarea>
                    </td>
                </tr>
                
                <tr>
                    <td style="width:100px;">选项五：</td>
                    <td style="width:500px;">
                    	<textarea id="option5-editor" name="option5-editor" emptyText="请输入" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                 <tr style="display:none;">
                    <td style="width:70px;">选项五正文：</td>
                    <td style="width:150px;">
                    	<textarea name="option5" id="option5" class="mini-textarea"></textarea>
                    </td>
                </tr>
                
                <tr>
                    <td style="width:100px;">选项六：</td>
                    <td style="width:500px;">
                    	<textarea id="option6-editor" name="option6-editor" emptyText="请输入" onvaluechanged="editorChange">
						</textarea>
                    </td>
                </tr>
                 <tr style="display:none;">
                    <td style="width:70px;">选项六正文：</td>
                    <td style="width:150px;">
                    	<textarea name="option6" id="option6" class="mini-textarea"></textarea>
                    </td>
                </tr>
            </table>            
         </div>
        </fieldset>
        
        <div style="text-align:center;padding:10px;">
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>        
    </form>
    <script type="text/javascript" src="${path}/base/js/kindeditor_use_faculty.js"></script>
    <script type="text/javascript">
    	var id = '';
        mini.parse();
        var form = new mini.Form("form");
        function SaveData() {
        	setDataForEditor(editorContent,"content-editor","content");
        	
        	setDataForEditor(editorBackground,"background-editor","background");
        	setDataForEditor(editorOption1,"option1-editor","option1");
        	setDataForEditor(editorOption2,"option2-editor","option2");
        	setDataForEditor(editorOption3,"option3-editor","option3");
        	setDataForEditor(editorOption4,"option4-editor","option4");
        	setDataForEditor(editorOption5,"option5-editor","option5");
        	setDataForEditor(editorOption6,"option6-editor","option6");
			
			var o = form.getData();
			if(o.type != 3 && (o.content.length < 1 || o.option1.length <1 || o.option2.length <2)){
				mini.alert("1.要求或背景不能为空 2.至少有两个选项");
				return;
			}
			if(o.type!=3 && o.answer.length <1){
				mini.alert("单选题或多选题必须输入答案");
				return;
			}
            form.validate();
            if(form.isValid() == false) return;
            var json = mini.encode(o);
            $.ajax({
                url: "${path}/saveQuestion.do",
                type: 'post',
                data: $.evalJSON(json),
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
        
        function setDataForEditor(editor,editor_id,field){
                editor.sync();
			    html = $("#"+ editor_id).val(); // jQuery
			   // mini.alert(html);
			    html = html.replace(/"/g,"'");
			    //mini.alert(html);
			    var webContent = $("<html></html>");
			    webContent.append(html);
			    var imgV = webContent.find('img');
			    for(var i = 0, len = imgV.length; i < len; i++) {
				   //imgV.eq(i).attr("style", "");
			    }	
			    mini.get(field).setValue(webContent.html());
        }
        
        //标准方法接口定义
        function SetData(data) {
       		editorContent.sync();
            editorBackground.sync();
            editorOption1.sync();
			editorOption2.sync();
			editorOption3.sync();
			editorOption4.sync();
			editorOption5.sync();
			editorOption6.sync();
			
			//html = $('#editor_id').val(); // jQuery
			//mini.get("contextReal").setValue(html);
            if (data.action == "edit") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
                $.ajax({
                    url: "findByIdQuestion.do?id=" + data.id,
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                        editorContent.html(o.content);
                        editorBackground.html(o.background);
                        editorOption1.html(o.option1);
						editorOption2.html(o.option2);
						editorOption3.html(o.option3);
						editorOption4.html(o.option4);
						editorOption5.html(o.option5);
						editorOption6.html(o.option6);
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
