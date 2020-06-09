<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//System.out.print(path);
%>
<html>
<head>
<title>库管员信息维护</title>
<jsp:include page="/common/easyui.jsp"></jsp:include>

<script type="text/javascript">
	// 查询
	function doSearch() {
		// 取得查询条件，发送给后台
		//console.info($('#graindepot').combobox('getValue'));
		$('#dataGrid').datagrid('load', {
			// 参数名与后台pojo属性名一致即可自动填充
			'username' : $('#username').val(),
			// 支持级联属性填充
			'realname' : $('#realname').val(),
			'title'	: $('#title').val(),
			'graindepot.lkbm': $('#graindepot').combobox('getValue')
		});
	}
	// 清查询条件
	function clearSearch() {
		$('#dataGrid').datagrid('load', {});
		$('#username').attr("value", "");
		$('#title').attr("value", "");
		$('#realname').attr("value", "");
		$('#graindepot').combobox('setValue', '');
	}

	var dataGrid;
	$(function() {
		//showFlash("${FlashMessage}");
		
		dataGrid = $('#dataGrid').datagrid({
			url : 'dataGrid',
			pagination: true,
			fit : true,
			fitColumns : true,
			rownumbers : true,// 显示行号
			singleSelect : true,// 只能单选
			border : false,
			striped : true,// 隔行变色
			idField : 'username', // 主键字段
			columns : [ [ {
				field : 'username',
				title : '用户名',
				width : 40
			}, {
				field : 'realname',
				title : '真实姓名',
				width : 50,
				sortable : true,
				align : "center",
			}, 
			 {
				field : 'mobile',
				title : '手机',
				width : 50,
				sortable : true,
				align : "center",
			},{
				field : 'title',
				title : '职称',
				width : 40,
				sortable : true,
				align : "center",
			}, {
				field : 'manager',
				title : '是否所长',
				width : 40,
				sortable : true,
				align : "center",
			},{
				field : 'hasaudit',
				title : '审核标志',
				width : 40,
				sortable : true,
				align : "center",
			},{
				field : 'point',
				title : '积分',
				width : 30,
				sortable : true,
				align : "center",
			},{
				field : 'graindepot',
				title : '所属库',
				width : 60,
				sortable : true,
				align : "center",
				formatter : function(value, row, index) {if (value!=null) return value.lkmc;	},
			},{
				field : 'actor',
				title : '操作',
				width : 40,
				formatter : function(value, row, index) {
					var str = '';
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="修改"/> ',row.username, '${pageContext.request.contextPath}/resources/easyUI/themes/extjs_icons/pencil.png');
					str += '&nbsp;';
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/> ',row.username, '${pageContext.request.contextPath}/resources/easyUI/themes/extjs_icons/cancel.png');
					return str;
				}
			} ] ],
			toolbar : '#toolbar',

			onLoadSuccess : function() {
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			},
		});
		
	});

	function deleteFun(id) {
		if (id!=null){
			parent.$.messager.confirm('询问', '您是否要删除当前库管员 :' + id +'？', function(b) {
				if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('deleteDepotuser', {
						username : id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}else
							parent.$.messager.alert('提示', result.msg, 'error');
						parent.$.messager.progress('close');
					}, 'JSON');
				}
			});
		}
	}

	function editFun(index) {
		openBlank('editDepotUserEntrance',{username:index}/* , 'editDepotUser' */ );
	}

	function addFun() {
		openBlank('addDepotUserEntrance',{ } );
	}


</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div region="north" title=" 库管员列表>>过滤条件" style="height:55px ;overflow:hidden;">
		<span>用户名:</span>
		<input id="username" style="border: 1px solid #ccc">
		<span>真实姓名</span>
		<input id="realname" style="border: 1px solid #ccc">
		<span>职&nbsp;&nbsp;&nbsp;称:</span>
		<input id="title" style="border: 1px solid #ccc">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			plain="false" onclick="doSearch()" iconCls="icon-search">查询</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			plain="false" onclick="clearSearch()" iconCls="icon-redo">清空</a>
    </div>
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<table id="dataGrid"></table>
	</div>
	<div id="toolbar" style="display: none;">
		库管员管理: <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		<!--  <a onclick="cancelFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">撤销</a> 
		<a onclick="saveFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存</a> 
		-->
		<a onclick="dataGrid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>

</body>
</html>