<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	 .tree-node  {
	 	margin-bottom: 4px;
	 }
	 .tree-title{
	 	padding-left: 8px;
	 	font-size: 15px;
	 	font-family : 微软雅黑;
	 }
</style>
<script type="text/javascript">
	var layout_west_tree;
	var layout_west_tree_url = '';
	var sessionInfo_userId = '${principal.username}';
	if (sessionInfo_userId) {
		layout_west_tree_url = '${pageContext.request.contextPath}/resourceController/tree';
	}
	$(function() {
		layout_west_tree = $('#layout_west_tree').tree({
				url : 'admin/menu/display',  
			//url : '/',
			parentField : 'pid',
			//lines : true,
			onClick : function(node) {
				if (true||node.attributes && node.attributes.url) {
					var url;
					if (node.attributes.url.indexOf('/') == 0) {/*如果url第一位字符是"/"，那么代表打开的是本地的资源*/
						url = '${pageContext.request.contextPath}' + node.attributes.url;
						if (url.indexOf('/druidController') == -1) {/*如果不是druid相关的控制器连接，那么进行遮罩层屏蔽*/
							parent.$.messager.progress({
								title : '提示',
								text : '数据处理中，请稍后....'
							});
						}
					} else {/*打开跨域资源*/
						url = node.attributes.url;
					}    
					addTab({
						//url : 'resources/views/portal/location_personmanage.html',
						url:url,
						title : node.text,
						iconCls : node.iconCls
					});
				}
			},
			onBeforeLoad : function(node, param) {
				if (layout_west_tree_url) {//只有刷新页面才会执行这个方法
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
				}
			},
			onLoadSuccess : function(node, data) {
				parent.$.messager.progress('close');
			}
		});
	});

	function addTab(params) {
		if (params.title.indexOf('图形监控')!=-1 ){
			window.open(params.url,'txjk','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no,channelmode=yes');
			return;
		}
			
		var iframe = '<iframe id="iframe" src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			parent.$.messager.progress('close');
		} else {
			t.tabs('add', opts);
		}
	}
</script>
<div class="easyui-accordion" data-options="fit:true,border:false">
<!--  	<div title="系统菜单" style="padding: 5px;" data-options="border:false,isonCls:'anchor',tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					$('#layout_west_tree').tree('reload');
				}
			}, {
				iconCls : 'resultset_next',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('expandAll', node.target);
					} else {
						$('#layout_west_tree').tree('expandAll');
					}
				}
			}, {
				iconCls : 'resultset_previous',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('collapseAll', node.target);
					} else {
						$('#layout_west_tree').tree('collapseAll');
					}
				}
			} ]">
		<div class="well well-small">
			<ul id="layout_west_tree"></ul>
		</div>
	</div> -->
	<ul id="layout_west_tree" class="easyui-tree">  
				<li>   
			        <span>实时监控</span>   
			    </li> 
			    <li>   
			        <span>数据维护</span>   
			        <ul>   
			             
			            <li>   
			                <span>人员管理</span>   
			            </li>   
			            <li>   
			                <span>分组管理</span>   
			            </li>
			             <li>   
			                <span>区域管理</span>   
			            </li>   
			            <li>   
			                <span>设备管理</span>   
			            </li>
			             <li>   
			                <span>监区管理</span>   
			            </li>      
			        </ul>   
			    </li>   
			       
			</ul>  
	<!-- 
	<div title="其他示例" data-options="border:false,iconCls:'anchor'">
		<ul>
			<li>菜单</li>
			<li>菜单</li>
			<li>菜单</li>
		</ul>
	</div>  -->
</div>