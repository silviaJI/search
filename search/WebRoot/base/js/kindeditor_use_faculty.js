var editorContent;
var editorBackground;
var editorOption1;
var editorOption2;
var editorOption3;
var editorOption4;
var editorOption5;
var editorOption6;
var editorAnswer;
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
    		editorContent = K.create('textarea[name="content-editor"]', options);
    		editorBackground = K.create('textarea[name="background-editor"]', options);
    		editorOption1 = K.create('textarea[name="option1-editor"]', options);
    		editorOption2 = K.create('textarea[name="option2-editor"]', options);
    		editorOption3 = K.create('textarea[name="option3-editor"]', options);
    		editorOption4 = K.create('textarea[name="option4-editor"]', options);
    		editorOption5 = K.create('textarea[name="option5-editor"]', options);
    		editorOption6 = K.create('textarea[name="option6-editor"]', options);
    		editorAnswer = K.create('textarea[name="answer-editor"]', options);
});

function preview() {
	editor.sync();
	html = $('#editor_id').val(); //获取文本框当前内容
	$.ajax({
        url: "preview.do",
        type: 'post',
        data: {contextReal : html},
        cache: false,
        success: function (text) {
        	if(text != null) {
        		var url = apachePath + text;
        		window.open (url, 'newwindow');
        	}
        }
    });
}

function getEditorContent(html) {
	var content = $("<html></html>");
	content.append(html);
	var contentHtml = content.find('.editor-wrapper').html();
	return contentHtml;
}