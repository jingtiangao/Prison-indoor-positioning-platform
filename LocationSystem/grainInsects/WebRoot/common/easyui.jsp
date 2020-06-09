<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<!-- jquery easy ui -->
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/resources/easyUI/themes/default/easyui.css"></link>
			  <style>
   				 .datagrid-header-row td{background-color:blue;color:#fff;}
   				 .datagrid-header .datagrid-cell span {  font-size: 14px;}
   				 .datagrid-cell,.datagrid-cell-group,.datagrid-header-rownumber,.datagrid-cell-rownumber {
/* 						  margin: 0;
						  padding: 0 4px;
						  white-space: nowrap;
						  word-wrap: normal;
						  overflow: hidden;
						  height: 18px;
						  line-height: 18px;
						  font-weight: normal; */
						  font-size: 14px;
						  font-family : 微软雅黑;
						}
					.tree-title {font-size: 14px;  font-family : 微软雅黑;}
    		</style>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/resources/easyUI/themes/icon.css"></link>
		<!-- 加载扩展自定义图标按钮 -->
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/resources/easyUI/themes/myicon.css"></link>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/resources/easyUI/themes/insects.css"></link>
						
		<script type="text/javascript"
			src="<%= request.getContextPath() %>/resources/admin/js/jquery.js"></script>
		<script type="text/javascript"
			src="<%= request.getContextPath() %>/resources/easyUI/jquery.easyui.min.js"></script>
		<!-- 中文 -->
		<script type="text/javascript"
			src="<%= request.getContextPath() %>/resources/easyUI/locale/easyui-lang-zh_CN.js"></script>
	
	<!-- 扩展EasyUI -->
	<script type="text/javascript" 
		src="<%= request.getContextPath() %>/resources/easyUI/extEasyUI.js" charset="utf-8"></script>
	
	<!-- 扩展jQuery -->
	<script type="text/javascript" 
		src="<%= request.getContextPath() %>/resources/easyUI/extJquery.js" charset="utf-8"></script>
	