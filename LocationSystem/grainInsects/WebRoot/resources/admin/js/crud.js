//选中用户的id
var username;
// 查询
function doSearch() {
	// 取得查询条件，发送给后台

	$('#tt').datagrid('load', {
		// 参数名与后台pojo属性名一致即可自动填充
		'username' : $('#username').val(),
		// 支持级联属性填充
		'role.id' : $('#role').val()
	});
}
// 清查询条件
function clearSearch() {
	$('#tt').datagrid('load', {});
	$('#username').attr("value", "");
	$("option[value='-1']").attr("selected", "true");
}
// 进入修改页面
function edit() {
	var row = $('#tt').datagrid('getSelected');
	if (row) {
		username = row.username;// 用户id
		$('#popwin1').attr("iconCls", "icon-edit");// 弹窗图标
		$('#popwin1').attr("title", "修改管理员信息");// 弹窗标题

		$('#popwin1').window({
			href : "edit/" + username,
			width : 600,
			height : 450,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			cache : false,
			modal : true,
			
			onClose : function() {
				// 清session
				$.post("edit/" + username + "/quit");
				// 刷新表格
				doSearch();
			}
		});

	} else {
		$.messager.alert('', '请先选中要修改的用户');
	}

}
// 角色修改提交
function doroleEdit() {
	var roleID = $("#roleID").val();
	$.post( "edit/" + username, {
		roleID : roleID
	}, function(data) {
		if (data != "success") {
			$.messager.alert('', '操作失败', "error");
		} else {
			$.messager.alert('', '操作成功', "info");
		}
	});

}
// 密码修改提交
function dopwEdit() {

	var pw = $("#pw").val();
	var pwcfm = $("#pwcfm").val();
	if (pw == "") {
		$.messager.alert('', '密码不能为空', "error");
		return;
	}
	if (pw != pwcfm) {
		$.messager.alert('', '两次密码输入不一致', "error");
		return;
	}

	$.post("edit/" + username, {
		passWord : pw
	}, function(data) {
		if (data != "success") {
			$.messager.alert('', '操作失败', "error");
		} else {
			$.messager.alert('', '操作成功', "info");
		}
	});
}
// 删除
function remove() {
	var row = $('#tt').datagrid('getSelected');
	if (row) {
		var username = row.username;
		$.messager.confirm("确认删除", "确定删除该用户？", function(flag) {
			if (flag) {
				$.post( "remove", {
					username : username
				}, function(data) {
					if (data != "success") {
						$.messager.alert('', '操作失败', "error");

					} else {
						$.messager.alert('', '操作成功', "info");
						// 刷新表格
						doSearch();
					}
				});
			}
		});
	} else {
		$.messager.alert('', '请先选中要删除的用户');
	}
}
// 进入添加页面
function add() {
	$('#popwin2').attr("iconCls", "icon-add");// 弹窗图标
	$('#popwin2').attr("title", "新增用户");// 弹窗标题
	$('#popwin2').window({
		href :  "addEntrance",
		width : 600,
		height : 300,
		collapsible : false,
		minimizable : false,
		maximizable : false,
		cache : false,
		modal : true,
		onClose : doSearch
	});

}
// 检查用户名是否重复
function checkName(obj) {
	//console.info(obj);
	var username = $(obj).val();
	if (username == "") {
		$("p.errmsg")[0].style.display = "block";
		$("span", $("p.errmsg")[0])[0].innerHTML = "用户名不能为空"
	} else {
		$.ajax({
			async : false,// 取消异步请求
			type : "POST",
			url : "checkName",
			dataType : "text",
			data : "username=" + username,
			success : function(data) {
				if (data == "ok") {
					$("p.errmsg")[0].style.display = "none";
				} else {
					$("p.errmsg")[0].style.display = "block";
					$("span", $("p.errmsg")[0])[0].innerHTML = "该用户名已被注册";
				}

			}

		});

	}

}
function checkPw(obj) {
	var pw = $(obj).val();
	if (pw == "") {
		$("p.errmsg")[1].style.display = "block";
	} else {
		$("p.errmsg")[1].style.display = "none";
	}
}
function checkPwcfm(obj) {
	var pwcfm = $(obj).val();
	var pw = $("#pw").val();
	if (pwcfm != pw) {
		$("p.errmsg")[2].style.display = "block";
	} else {
		$("p.errmsg")[2].style.display = "none";
	}
}
function doAdd() {
	var flag = true;
	$("#suName").blur();
	$("#pw").blur();
	$("#pwcfm").blur();
	// 填写信息有误
	$("p.errmsg").each(function() {
		if (this.style.display == "block") {
			flag = false;
			return false;
		}
	});
	// 填写信息正确
	if (flag) {
		var name = $("#suName").val();
		var pw = $("#pw").val();
		var roleID = $("#roleID").val();
		$.post("add", {
			username : name,
			passWord : pw,
			roleID : roleID
		}, function(data) {
			if (data == "ok") {
				$.messager.alert('', '操作成功', "info");
			} else {
				$.messager.alert('', '操作失败', "error");
			}

		}, "text"

		);
	}
}
// 页面加载
$(function() {
	// 初始化表格
	$('#tt').datagrid({
		pagination : true, // 分页向导栏
		title : "管理员维护",
		iconCls : "icon-tip",// 图标css,见icon.css
		rownumbers : true,// 显示行号
		singleSelect : true,// 只能单选
		toolbar : [ {
			iconCls : 'icon-add',
			text : '新增',
			handler : function() {
				add();
			}
		}, '-', {
			iconCls : 'icon-edit',
			text : '修改',
			handler : function() {
				edit();
			}
		}, '-', {
			iconCls : 'icon-remove',
			text : '删除',
			handler : function() {
				remove();
			}
		} ],
		striped : true,// 隔行变色
		remoteSort : true,// 服务器端排序
		idField : 'id', // 主键字段
		url : "list",

		fit : true,

		// 加载失败事件函数
		onLoadError : function() {
			$.messager.alert('出错了', '无法获取数据');
		},
		// 加载成功，清除之前的选中
		onLoadSuccess : function(data) {
			$('#tt').datagrid('clearSelections');
		},

		// 列定义
		columns : [ [ {
			field : 'username',
			title : '用户名',
			width : 100,
			sortable : true,
			align : "center"
		}, {
			field : 'role',
			title : '角色',
			width : 80,
			sortable : false,
			align : "center"
		}, {
			field : 'name',
			title : '真实姓名',
			width : 100,
			sortable : false,
			align : "center"
		}, {
			field : 'department',
			title : '部门',
			width : 100,
			sortable : false,
			align : "center"
		}, {
			field : 'loginDate',
			title : '最后登录日期',
			width : 100,
			sortable : false,
			align : "center"
		}, {
			field : 'loginIp',
			title : '最后登录IP',
			width : 100,
			sortable : false,
			align : "center"
		}

		] ]

	});

});