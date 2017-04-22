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
<title>用户管理</title>
<%@ include file="/base/jsp/include.jsp"%>
</head>
<body>
	<div class="mini-toolbar" style="padding:2px;border-bottom:0;">
		<table style="width:100%;">
			<tr>
				<td style="width:100%;"><a class="mini-button"
					iconCls="icon-cancel" plain="true" href="javascript:clearFilter()">清除筛选</a></td>
			</tr>
		</table>
	</div>
	<span id="module-name" style="display:none;">news</span>
	<!--撑满页面-->
	<div class="mini-fit">
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;"
			pageSize="20" borderStyle="border:1;" url="listUser.do" idField="id"
			multiSelect="true" showFilterRow="true">
			<div property="columns">
				<div type="checkcolumn" field="id"></div>
				<div type="indexcolumn" headerAlign="center">序号</div>
				<div field="name" width="50" headerAlign="center" align="center">姓名
				<input id="nameFilter" property="filter" class="mini-textbox" style="width:100%;" onvaluechanged="onFilterChanged"/></div>
				<div field="stuId" width="50" headerAlign="center" align="center">学号
				<input id="stuIdFilter" property="filter" class="mini-textbox" style="width:100%;" onvaluechanged="onFilterChanged"/></div>
				<div field="school" width="50" headerAlign="center" align="center">学校
				<input id="schoolFilter" property="filter" class="mini-combobox" textField="text" valueField="id" emptyText="学校"
                        url="${path}/base/json/schools.json" allowInput="false" showNullItem="true" nullItemText="请选择..." onvaluechanged="onFilterChanged"/>
				</div>
				<div field="depart" width="50" headerAlign="center" align="center">院系
				<input id="departFilter" property="filter" class="mini-textbox" style="width:100%;" onvaluechanged="onFilterChanged"/>
				</div>
				<div field="phone" width="50" headerAlign="center" align="center">手机号
				<input id="phoneFilter" property="filter" class="mini-textbox" style="width:100%;" onvaluechanged="onFilterChanged"/>
				</div>
				<div field="email" width="70" headerAlign="center" align="center">邮箱
				<input id="emailFilter" property="filter" class="mini-textbox" style="width:100%;" onvaluechanged="onFilterChanged"/>
				</div>
				<div field="education" width="30" headerAlign="center" align="center" renderer="educationHandler">学历
				<input id="educationFilter" property="filter" class="mini-combobox" textField="text" valueField="id" emptyText="学历"
                        url="${path}/base/json/education.json" allowInput="false" showNullItem="true" nullItemText="请选择..." onvaluechanged="onFilterChanged"/>
				</div>
				<div field="isV" width="50" headerAlign="center" align="center"  renderer="statusHandler">账号状态
				<input id="statusFilter" property="filter" class="mini-combobox" textField="text" valueField="id" emptyText="状态"
                        url="${path}/base/json/status.json" allowInput="false" showNullItem="true" nullItemText="请选择..." onvaluechanged="onFilterChanged"/></div>
				<div field="score" width="50" headerAlign="center" align="center">当前分数</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	mini.parse();
	var grid = mini.get("grid");
	grid.load();
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
       var namebox = mini.get("nameFilter");
       var stuIdbox = mini.get("stuIdFilter");
       var schoolbox = mini.get("schoolFilter");
       var departbox = mini.get("departFilter");
       var phonebox = mini.get("phoneFilter");
       var emailbox = mini.get("emailFilter");
       var educationbox = mini.get("educationFilter");
       var statusbox = mini.get("statusFilter");
       
       var name = namebox.getValue();
       var stuId = stuIdbox.getValue();
       var school = schoolbox.getValue();
       var depart = departbox.getValue();
       var phone = phonebox.getValue();
       var email = emailbox.getValue();
       var education = educationbox.getValue();
       var status = statusbox.getValue();
     
       grid.load({name: name, stuId : stuId,school:school,depart:depart,phone:phone,email:email,education:education,isV:status});
   }
	
	function clearFilter() {
	   var namebox = mini.get("nameFilter");
       var stuIdbox = mini.get("stuIdFilter");
       var schoolbox = mini.get("schoolFilter");
       var departbox = mini.get("departFilter");
       var phonebox = mini.get("phoneFilter");
       var emailbox = mini.get("emailFilter");
       var educationbox = mini.get("educationFilter");
       var statusbox = mini.get("statusFilter");
	   namebox.setValue("");
	   stuIdbox.setValue("");
	   schoolbox.setValue("");
	   departbox.setValue("");
	   phonebox.setValue("");
	   emailbox.setValue("");
	   educationbox.setValue("");
	   statusbox.setValue("");
       grid.load();
   }
   
   var stauses = [
	{ id: "0", text: "未激活"},
	{ id: "1", text: "初赛"},
	{ id: "2", text: "进入复赛"}
	];
	function statusHandler(e) {
		for ( var i = 0, l = stauses.length; i < l; i++) {
			var g = stauses[i];
			if (g.id == e.value)
				return g.text;
		}
		return "";
	}
	var educations = [
	{ id: "0", text: "本科"},
	{ id: "1", text: "硕士"},
	{ id: "2", text: "博士"}
	]
	function educationHandler(e) {
		for ( var i = 0, l = educations.length; i < l; i++) {
			var g = educations[i];
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
