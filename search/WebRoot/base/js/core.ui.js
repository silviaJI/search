$(function(){
	$.fn.clearFileId = function(){
		$(this).attr("fileids","");
		var v_itemId = $(this).attr("id");
		$("#"+ v_itemId + "_img_queue").html("");
	}
	
	$.fn.setFileId = function(fid,multi,showImage) {
		var ids = $(this).attr("fileids");
		if(ids == undefined || ids == '' || !multi){
			$(this).attr("fileids",fid);
		}else{
			$(this).attr("fileids",ids+","+fid);
		}
		
		var v_itemId = $(this).attr("id");
		if(showImage){
			var array = fid.split(",");
			for ( var i = 0; i < array.length; i++) {
				if(multi){
	    			$("#"+ v_itemId + "_img_queue").append("<div class='uploadify-img-queue-div'>" +
	    					"<a href=\""+path+"/download.do?id="+array[i]+"\" class=\"uploadify_img\"><img src='"+path+"/download.do?w=80&h=80&id=" + array[i] + "'/></a>" +
	    							"<a class='uploadify-img-queue-div-a' href='javascript:void(0)' onclick='$(\"#"+v_itemId+"\").delFileId(\""+array[i]+"\","+multi+");$(this).parent().remove();'></a></div>");
	    		}else{
	    			$("#"+ v_itemId + "_img_queue").html("<div class='uploadify-img-queue-div'>" +
	    					"<a href=\""+path+"/download.do?id="+array[i]+"\" class=\"uploadify_img\"><img src='"+path+"/download.do?w=80&h=80&id=" + array[i] + "'/></a>" +
							"<a class='uploadify-img-queue-div-a' href='javascript:void(0)' onclick='$(\"#"+v_itemId+"\").delFileId(\""+array[i]+"\","+multi+");$(this).parent().remove();'></a></div>");
	    		}
			}
			$("a.uploadify_img").fancyzoom();
		}
	};
	
	$.fn.delFileId = function(fid,multi) {
		var ids = $(this).attr("fileids");
		if(ids == undefined || ids == '' || !multi){
			$(this).attr("fileids","");
		}else{
			var array = ids.split(",");
			for ( var i = 0; i < array.length; i++) {
				if(array[i] == fid){
					array.splice(i,1);
					break;
				}
			}
			ids=array.join(",");
			$(this).attr("fileids",ids);
		}
	};
	
	$.fn.getFileId = function() {
		var ids = $(this).attr("fileids");
		if(ids == undefined || ids == ''){
			return '';
		}else{
			return ids;
		}
	};
	
	  //文件上传控件
	  $.each($("input[type=file]"),function(index,obj){
		  	 var v_init = ("false" != $(obj).attr('init'));
		  	 if(!v_init)return;
	  		 var method = $(obj).attr('onUploadSuccess');
	  		 var showImage = ("false" != $(obj).attr("showImage"));
	  		 var o = obj;
	  		 var v_itemId = $(obj).attr("id");
	  		 var v_multi = ("false" != $(obj).attr('multi'));
	  		 if (showImage) {
	  		 	$("#" + v_itemId).before('<div id="' + v_itemId + '_img_queue" style="width:100%;height:60%;"></div><div style="clear:both;"></div>');
	  		 }
	  		$("#" + v_itemId).after("<div id='" + v_itemId + "_queue'></div>");
	  		
	  		var v_fileType = $(obj).attr('fileType') || "";
	  		var v_fileSize = $(obj).attr('fileSize') || "";
	  		var v_fileCountLimit = $(obj).attr("fileCountLimit") || "";
	  		var v_formData = $(obj).attr("formData") || "";
	  		v_formData = $.parseJSON(v_formData);
		  	 $(obj).uploadify({
		    	//开启调试
		        'debug' : false,
		      	//是否自动上传
		        'auto':$(obj).attr('auto')||true,
		      	//超时时间
		        'successTimeout':99999,
		      	//附带值
		        'formData':v_formData,
		      	//flash
		        'swf': projectPath+'/base/js/uploadify/uploadify.swf',
		        'uploader': projectPath+'/fileUpload.do',
		        'buttonText':"<div class=\"uploadify_button_defined\"><a href=\"javascript:void(0);\">" + $(obj).attr('buttonText') + "</a></div>",
				itemTemplate : '<div id="${fileID}" class="uploadify-queue-item">\
                    <div class="cancel"><a id="${fileID}_cancelBtn" href="javascript:$(\'#${instanceID}\').uploadify(\'cancel\', \'${fileID}\')">X</a></div>\
                    <span class="fileName">${fileName} (${fileSize})</span><span class="data"></span></div>',
		        'multi': v_multi||false, // 是否支持多文件一起上传
		      	//文件选择后的容器ID
		        'queueID':v_itemId + '_queue',
		        'removeCompleted' : false,
		        //'fileObjName': 'filedata',
		      	//浏览按钮的宽度
		        //'width':'120',
		        //浏览按钮的高度
		        //'height':'30',
		      	//允许上传的文件后缀
		        'fileTypeExts':v_fileType||'*',
		        //上传文件的大小限制
		        'fileSizeLimit':v_fileSize||'1MB',
		        'overrideEvents' : ['onDialogClose'],
		        'onSelect':function(file){
		        	var ids = $("#"+v_itemId).attr("fileids");
		        	var file_cnt = 0;
		    		if(ids == undefined || ids == ''){
		    			file_cnt = 0;
		    		}else{
		    			var array = ids.split(",");
		    			file_cnt = array.length;
		    		}
		        	if (v_fileCountLimit != "" && parseInt(v_fileCountLimit) <= file_cnt) {
						alert("文件上传数量超过限制:" + v_fileCountLimit + "个!");
						this.cancelUpload(file.id);
						$("#" + v_itemId).uploadify("cancel",file.id);
						return false;
					}
		        },
		        //返回一个错误，选择文件的时候触发
		        'onSelectError':function(file, errorCode, errorMsg){
		        	var msg = "";
		        	switch(errorCode) {
			        	case -100:
			        		msg = "每次最多上传"+this.settings.queueSizeLimit+"个文件！";
		                    break;
		                case -110:
		                	msg = "文件 ["+file.name+"] 大小超出系统限制的"+this.settings.fileSizeLimit+"大小！";
		                    break;
		                case -120:
		                	msg = "文件 ["+file.name+"] 大小为0！";
		                    break;
		                case -130:
		                	msg = "文件 ["+file.name+"] 格式不正确！";
		                    break;
		                default:
		                	msg = "错误代码：" + errorCode + "\n" + errorMsg;
		            }
		        	alert(msg);
		        },
		      	//检测FLASH失败调用
		        'onFallback':function(){
		            var msg = "您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。";
		            alert(msg);
		        },
		        //出错
		        'onUploadError':function(file, errorCode, errorMsg, errorString){
		        	var msg = "";
		        	switch (errorCode) {
			            case -200:
			            	msg = "HTTP 错误\n" + errorMsg;
			                break;
			            case -210:
			            	msg = "上传文件丢失，请重新上传";
			                break;
			            case -220:
			            	msg = "IO错误";
			                break;
			            case -230:
			            	msg = "安全性错误\n" + errorMsg;
			                break;
			            case -240:
			            	msg = "每次最多上传 " + this.settings.uploadLimit + "个";
			                break;
			            case -250:
			            	msg = errorMsg;
			                break;
			            case -260:
			            	msg = "找不到指定文件，请重新操作";
			                break;
			            case -270:
			            	msg = "参数错误";
			                break;
			        }
		        	alert(msg);
		        },
		        //上传到服务器，服务器返回相应信息到data里
		        'onUploadSuccess':function(file, data, response){
		        	var ret = $.parseJSON(data);
		        	if (ret.error == 0) {
		        		$("#"+v_itemId).setFileId(ret.url,v_multi,showImage);
		        		// 去除队列信息
						$("#" + v_itemId).uploadify('cancel', file.id);
						$("#" + file.id).remove();
		        		if(method!='' && window[method]){
			        		window[method].call(window, ret);
			        	}
		        	}else{
		        		alert(ret.message);
		        	}
		        }
		    });
	  });
});