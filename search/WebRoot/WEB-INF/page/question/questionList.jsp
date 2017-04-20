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
<title>题目管理</title>
<%@ include file="/base/jsp/include.jsp"%>
</head>
<body>
	<div class="mini-toolbar" style="padding:2px;border-bottom:0;">
		<table style="width:100%;">
			<tr>
				<td style="width:100%;"><a class="mini-button"
					iconCls="icon-add" plain="true" onclick="addRow()">添加</a> <span
					class="separator"></span> <a class="mini-button"
					iconCls="icon-edit" plain="true" onclick="edit()">修改</a> <span
					class="separator"></span> <a class="mini-button"
					iconCls="icon-remove" plain="true" onclick="del()">删除</a><span
					class="separator"></span><a class="mini-button"
					iconCls="icon-cancel" plain="true" href="javascript:clearFilter()">清除筛选</a></td>
			</tr>
		</table>
	</div>
	<span id="module-name" style="display:none;">news</span>
	<!--撑满页面-->
	<div class="mini-fit">
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;"
			pageSize="20" borderStyle="border:1;" url="listQuestion.do" idField="id"
			multiSelect="true" showFilterRow="true">
			<div property="columns">
				<div type="checkcolumn" field="id"></div>
				<div type="indexcolumn" headerAlign="center">序号</div>
				<div field="content" width="70" headerAlign="center" align="center">问题要求
				<input id="contentFilter" property="filter" class="mini-textbox" style="width:100%;" onvaluechanged="onFilterChanged"/></div>
				<div field="background" width="70" headerAlign="center" align="center">问题背景
				<input id="backFilter" property="filter" class="mini-textbox" style="width:100%;" onvaluechanged="onFilterChanged"/></div>
				<div field="type" width="50" headerAlign="center" align="center" renderer="typeHandler">问题类别
				<input id="typeFilter" property="filter" class="mini-combobox" textField="text" valueField="id" emptyText="类别"
                        url="${path}/base/json/type.json" allowInput="false" showNullItem="true" nullItemText="请选择..." onvaluechanged="onFilterChanged"/>
				</div>
				<div field="rank" width="50" headerAlign="center" align="center" renderer="rankHandler">难易级别
				<input id="rankFilter" property="filter" class="mini-combobox" textField="text" valueField="id" emptyText="难易"
                        url="${path}/base/json/rank.json" allowInput="false" showNullItem="true" nullItemText="请选择..." onvaluechanged="onFilterChanged"/>
				</div>
				<div field="area" width="50" headerAlign="center" align="center" renderer="areaHandler">问题领域
				<input id="areaFilter" property="filter" class="mini-combobox" textField="text" valueField="id" emptyText="领域"
                        url="${path}/base/json/area.json" allowInput="false" showNullItem="true" nullItemText="请选择..." onvaluechanged="onFilterChanged"/>
				</div>
				<div field="answer" width="30" headerAlign="center" align="center">答案</div>
				<div field="point" width="30" headerAlign="center" align="center">分值</div>
				<div field="option1" width="50" headerAlign="center" align="center">选项一</div>
				<div field="option2" width="50" headerAlign="center" align="center">选项二</div>
				<div field="option3" width="50" headerAlign="center" align="center">选项三</div>
				<div field="option4" width="50" headerAlign="center" align="center">选项四</div>
				<div field="option5" width="50" headerAlign="center" align="center">选项五</div>
				<div field="option6" width="50" headerAlign="center" align="center">选项六</div>
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
			mini.open({
				url : "toEditQuestion.do",
				title : "问题编辑",
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
       var contentbox = mini.get("contentFilter");
       var backbox = mini.get("backFilter");
       var typebox = mini.get("typeFilter");
       var rankbox = mini.get("rankFilter");
       var areabox = mini.get("areaFilter");
       
       var content = contentbox.getValue();
       var back = backbox.getValue();
       var type = typebox.getValue();
       var rank = rankbox.getValue();
       var area = areabox.getValue();
     
       grid.load({content: content, background : back,type:type,rank:rank,area:area});
   }
	
	function clearFilter() {
	   var contentbox = mini.get("contentFilter");
       var backbox = mini.get("backFilter");
       var typebox = mini.get("typeFilter");
       var rankbox = mini.get("rankFilter");
       var areabox = mini.get("areaFilter");
	   contentbox.setValue("");
	   backbox.setValue("");
	   typebox.setValue("");
	   rankbox.setValue("");
	   areabox.setValue("");
       grid.load();
   }
   
   var types = [
		{ id: "1", text: "单选"},
		{ id: "2", text: "多选"},
		{ id: "3", text: "简答"}
	];
	function typeHandler(e) {
		for ( var i = 0, l = types.length; i < l; i++) {
			var g = types[i];
			if (g.id == e.value)
				return g.text;
		}
		return "";
	}
	var ranks = [
		{ id: "1", text: "单选简单"},
		{ id: "2", text: "单选中等"},
		{ id: "3", text: "多选中等"},
		{ id: "4", text: "单选难题"},
		{ id: "5", text: "简答"}
	]
	function rankHandler(e) {
		for ( var i = 0, l = ranks.length; i < l; i++) {
			var g = ranks[i];
			if (g.id == e.value)
				return g.text;
		}
		return "";
	}
	var areas = [
		{ id: "1", text: "学术"},
		{ id: "2", text: "生活"},
		{ id: "3", text: "娱乐"},
		{ id: "4", text: "体育"},
		{ id: "5", text: "校史"},
		{ id: "6", text: "其他"}
	]
	function areaHandler(e) {
		for ( var i = 0, l = areas.length; i < l; i++) {
			var g = areas[i];
			if (g.id == e.value)
				return g.text;
		}
		return "";
	}
</script>
</body>

</html>
