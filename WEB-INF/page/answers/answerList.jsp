<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>复赛答题管理</title>
<%@ include file="/base/jsp/include.jsp"%>
</head>
<body>
	<div class="mini-toolbar" style="padding:2px;border-bottom:0;">
		<table style="width:100%;">
			<tr>
				<td style="width:100%;"><a class="mini-button"
					iconCls="icon-edit" plain="true" onclick="edit()">现在批阅</a> <span
					class="separator"></span> <a class="mini-button"
					iconCls="icon-cancel" plain="true" href="javascript:clearFilter()">清除筛选</a></td>
			</tr>
		</table>
	</div>
	<span id="module-name" style="display:none;">news</span>
	<!--撑满页面-->
	<div class="mini-fit">
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;"
			pageSize="20" borderStyle="border:1;" url="listAnswer.do" idField="id"
			multiSelect="true" showFilterRow="true">
			<div property="columns">
				<div type="checkcolumn" field="id"></div>
				<div type="indexcolumn" headerAlign="center">序号</div>
				<div field="name" width="70" headerAlign="center" align="center">姓名
			<!--  	<input id="nameFilter" property="filter" class="mini-textbox" style="width:100%;" onvaluechanged="onFilterChanged" />--></div>
				<div field="timeLeft" width="70" headerAlign="center" align="center">剩余时间(秒)</div>
				<div field="score" width="50" headerAlign="center" align="center">当前分数</div>
				<div field="times" width="30" headerAlign="center" align="center">答题次序</div>
				<div field="isOver" width="50" headerAlign="center" align="center" renderer="whetherHandler">是否答题结束
				<input id="overFilter" property="filter" class="mini-combobox" textField="text" valueField="id" emptyText="是否"
                        url="${path}/base/json/whether.json" allowInput="false" showNullItem="true" nullItemText="请选择..." onvaluechanged="onFilterChanged"/>
				</div>
				<div field="isChecked" width="50" headerAlign="center" align="center" renderer="whetherHandler">已批阅
				<input id="checkFilter" property="filter" class="mini-combobox" textField="text" valueField="id" emptyText="是否"
                        url="${path}/base/json/whether.json" allowInput="false" showNullItem="true" nullItemText="请选择..." onvaluechanged="onFilterChanged"/>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	mini.parse();
	var grid = mini.get("grid");
	grid.load();	
	var rssData;
	function addRow() {
		mini.open({
			url : "toEditQuestion.do",
			title : "问题新增",
			width : editorWinWidth,
			height : editorWinHeight,
			showMaxButton: Boolean,     //显示最大化按钮
			onload : function() {
				var iframe = this.getIFrameEl();
				var data = {
					action : "new"
				};
				iframe.contentWindow.SetData(data);
			},
			ondestroy : function(action) {
				grid.reload();
			}
		});
	}

	function del() {
		var rows = grid.getSelecteds();
		if (rows.length > 0) {
			mini.confirm("确定删除吗？", "确定？", function(action) {
				if (action == "ok") {
					var ids = [];
					for ( var i = 0, l = rows.length; i < l; i++) {
						var r = rows[i];
						ids.push(r.id);
					}
					var id = ids.join(",");
					grid.loading("操作中，请稍后......");
					$.ajax({
						url : "delQuestion.do?id=" + id,
						success : function(text) {
							grid.reload();
						},
						error : function() {
						}
					});
				}
			});
		} else {
			mini.alert("请选中一条记录");
		}
	}
	
	//将栏目id转换成栏目title
	function rssName(e) {
     for ( var i = 0, l = rssData.length; i < l; i++) {
          var g = rssData[i];
          if (g.id == e.value)
            return g.title;
      }
	}


	function edit() {
		var rows = grid.getSelecteds();
		if (rows.length == 1) {
			var row = grid.getSelected();
			if(row.isOver !=1){
				mini.alert("这次答题还未结束");
				return ;
			}
			/*else if(row.isChecked != 0){
				mini.alert("这次答题已经批阅过了");
				return ;
			}*/
			mini.open({
				url : "toEditAnswer.do",
				title : "答题批阅",
				width : editorWinWidth,
				height : editorWinHeight,
				showMaxButton: true,     //显示最大化按钮
				onload : function() {
					var iframe = this.getIFrameEl();
					var data = {
						action : "edit",
						id : row.id
					};
					iframe.contentWindow.SetData(data);
				},
				ondestroy : function(action) {
					grid.reload();
				}
			});
		} else if (rows.length > 1) {
			mini.alert("只能选一条记录");
		} else {
			mini.alert("请选中一条记录");
		}
	}

	function search() {
		var key = mini.get("key").getValue();
		grid.load({
			key : key.trim()
		});
	}

       
   function onFilterChanged() {
       var namebox = mini.get("nameFilter");
       var overbox = mini.get("overFilter");
       var checkbox = mini.get("checkFilter");
       
       
       var name = namebox.getValue();
       var isOver = overbox.getValue();
       var isChecked = checkbox.getValue();
     
       grid.load({name: name, isOver : isOver,isChecked : isChecked});
   }
	
	function clearFilter() {
	  var namebox = mini.get("nameFilter");
       var overbox = mini.get("overFilter");
       var checkbox = mini.get("checkFilter");
       
	   namebox.setValue("");
	   overbox.setValue("");
	   checkbox.setValue("");
	  
       grid.load();
   }
   
   var whethers = [
	{ id: "0", text: "否"},
	{ id: "1", text: "是"}
	]
	function whetherHandler(e) {
		for ( var i = 0, l = whethers.length; i < l; i++) {
			var g = whethers[i];
			if (g.id == e.value)
				return g.text;
		}
		return "";
	}
</script>
</body>

</html>
