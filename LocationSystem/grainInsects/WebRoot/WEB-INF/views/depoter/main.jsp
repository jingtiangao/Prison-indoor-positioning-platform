<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 导入公用配置 -->
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/resources.jsp"%>
<%@ include file="/common/easyui.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//System.out.print(path);
%>

<html>
<head>
<base href="<%=basePath%>">
<title>监狱点名系统</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/bootstrap/css/nav.css"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/bootstrap/css/grain_home.css"/>
<link rel="icon" href="<%=basePath%>resources/images/fonter-icon.ico" type="image/x-icon" />

<style>
<!-- <fmt:message key="app.title" />-->
</style>
<script language="JavaScript">

	var index_tabs;
	var index_tabsMenu;
	var index_layout;
	$(function() {
		$.get('<%= request.getContextPath() %>/getIpAddr', function(data, status) {
			$('#ip').html('您的登陆IP是:'+data);
		});
		
		index_layout = $('#index_layout').layout({
			fit : true
		});
		/*index_layout.layout('collapse', 'east');*/

		index_tabs = $('#index_tabs')
				.tabs({	fit : true,
						border : false,
						onContextMenu : function(e, title) {
							e.preventDefault();
							index_tabsMenu.menu('show', {
								left : e.pageX,
								top : e.pageY
							}).data('tabTitle', title);
						},
						tools : [{
									iconCls : 'database_refresh',
									handler : function() {
										var href = index_tabs.tabs(
												'getSelected').panel(
												'options').href;
										if (href) {/*说明tab是以href方式引入的目标页面*/
											var index = index_tabs
													.tabs('getTabIndex',index_tabs.tabs('getSelected'));
											index_tabs.tabs('getTab', index).panel('refresh');
										} else {/*说明tab是以content方式引入的目标页面*/
											var panel = index_tabs.tabs(
													'getSelected').panel(
													'panel');
											var frame = panel
													.find('iframe');
											try {
												if (frame.length > 0) {
													for (var i = 0; i < frame.length; i++) {
														frame[i].contentWindow.document.write('');
														frame[i].contentWindow.close();
														frame[i].src = frame[i].src;
													}
													if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
														try {
															CollectGarbage();
														} catch (e) {
														}
													}
												}
											} catch (e) {
											}
										}
									}
								},
									{	iconCls : 'cancel',
										handler : function() {
											var index = index_tabs.tabs('getTabIndex',index_tabs.tabs('getSelected'));
											var tab = index_tabs.tabs('getTab',index);
											if (tab.panel('options').closable) {
												index_tabs.tabs('close', index);
											} else {
												$.messager.alert('提示','[' + tab.panel('options').title + ']不可以被关闭！','error');
											}
										}
									} ]
						});

		index_tabsMenu = $('#index_tabsMenu').menu(
				{
					onClick : function(item) {
						var curTabTitle = $(this).data('tabTitle');
						var type = $(item.target).attr('title');

						if (type === 'refresh') {
							index_tabs.tabs('getTab', curTabTitle).panel(
									'refresh');
							return;
						}

						if (type === 'close') {
							var t = index_tabs.tabs('getTab', curTabTitle);
							if (t.panel('options').closable) {
								index_tabs.tabs('close', curTabTitle);
							}
							return;
						}

						var allTabs = index_tabs.tabs('tabs');
						var closeTabsTitle = [];

						$.each(allTabs, function() {
							var opt = $(this).panel('options');
							if (opt.closable && opt.title != curTabTitle
									&& type === 'closeOther') {
								closeTabsTitle.push(opt.title);
							} else if (opt.closable && type === 'closeAll') {
								closeTabsTitle.push(opt.title);
							}
						});

						for (var i = 0; i < closeTabsTitle.length; i++) {
							index_tabs.tabs('close', closeTabsTitle[i]);
						}
					}
				});
		  $("tt").tree(
			        {
			        onClick:function(node){
			            openPage(node);
			        }
			    });
			     
			    function openPage(node){
			       
			            $("#index_tabs").tabs('add',{
			                title:node.text,
			                content:"xxx",
			                closable:true
			            });
			        
			    }
	});
	

</script>

</head>

<body>
	<div id="index_layout">
		<!--<div
			data-options="region:'north',href:'resources/views/layout/north.jsp'"
			style="overflow: hidden; height: 50px;" class="logo"></div>-->
		<div
			data-options="region:'north'"
			style="overflow: hidden; height: 82px;">
			<div class="person-center" style="background-color:#e0ecff;">
				
			    <div class="container" style="margin: 0 0 0 0;padding: 0 0 0 0;width:100%;">
			      <a class="c-personal" style="margin-left:15px;padding-left:0;" id='ip'>您的登陆IP:8.8.8.8</a>
			      <a href="admin/logout" class="c-personal right" style="margin-left:0px;padding-left:0px;">退出</a>
			      <a class="c-personal right" style="padding-left:0px;">|</a>
			      <a class="c-personal right" style="margin-right:0px;padding-left:0px;">欢迎您，${userName}</a>
    			</div>
  			</div>
  			<header>
			    <nav class="navbar navbar-inverse" role="navagation" style="background-color:#477db5;border:0px;">
			      <div class="container" style="margin-left:0px;">
			        <div class="navbar-header">
			          <a href="." class="navbar-brand navbar-a">监狱点名系统</a>
			        </div>
			        <div class="collapse navbar-collapse" id="example-navbar-collapse">
			          <ul  class="nav navbar-nav navbar-left">
			            <li  style="font-size:25px;display:none"><a class="navbar-a" style="padding-left:0px;padding-right:0px;">></a></li>
			            <li  style="font-size:25px;display:none"><a class="navbar-a" style="font-size:20px;">${graindepot}</a></li>
			          </ul>
			        </div>
			      </div>
			    </nav>
			 </header>
		</div>

		<div
			data-options="region:'south',href:'resources/views/layout/south.jsp'"
			style="overflow: hidden; height: 30px;"></div>
		<!-- href:'resources/views/layout/west.jsp' -->
		<div
			data-options="region:'west',split:true,href:'resources/views/layout/west.jsp'"
			title="导航模块" style="overflow: hidden; width: 200px;">
			
			</div>
		
		<div data-options="region:'center'"  
			 style="overflow: hidden;">
		 	<div id="index_tabs" style="overflow: hidden;">
		 	<div title="首页" data-options="border:false"
					style="overflow: hidden;">
					<iframe src="resources/views/portal/location_main.jsp" frameborder=0
						style="border: 0; width: 100%; height: 98%;"> </iframe>
				</div>
			<!--	<div title="首页" data-options="border:false"
					style="overflow: hidden;">
					<iframe src="resources/views/portal/index.jsp" frameborder=0
						style="border: 0; width: 100%; height: 98%;"> </iframe>
				</div>
				
		 <div title="6f" data-options="border:false"
					style="overflow: hidden;">
					<iframe src="resources/views/portal/point_d.jsp" frameborder=0
						style="border: 0; width: 100%; height: 98%;"> </iframe>
				</div>   
				<div title="MAC" data-options="border:false"
					style="overflow: hidden;">
					<iframe src="resources/views/portal/page-groupManage.html" frameborder=0
						style="border: 0; width: 100%; height: 98%;"> </iframe>
				</div>
			  <div title="MAC" data-options="border:false"
					style="overflow: hidden;">
					<iframe src="resources/views/portal/page-createPerson.html" frameborder=0
						style="border: 0; width: 100%; height: 98%;"> </iframe>
				</div>   -->
				
			</div>
		</div>

	</div>

	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">关闭</div>
		<div title="closeOther" data-options="iconCls:'delete'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'delete'">关闭所有</div>
	</div>
</body>
</html>
