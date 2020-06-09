<%@ page language="java" contentType="text/html;  =UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path +"/";

%>
<html>
<head>
<jsp:include page="/common/easyui.jsp"></jsp:include>

<style type="text/css">
	#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${baidu_map_api_account}"></script>

<script type="text/javascript">
	
	$(function() {
		// 百度地图API功能
		map = new BMap.Map("allmap");    // 创建Map实例
		var point = new BMap.Point(110.404, 33.915);
		map.centerAndZoom(point, 5);  // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
		//map.setCurrentCity("南京");          // 设置地图显示的城市 此项是必须设置的
	  
		map.addControl(new BMap.NavigationControl());        // 添加平移缩放控件
		map.addControl(new BMap.ScaleControl());             // 添加比例尺控件
		map.addControl(new BMap.OverviewMapControl());       //添加缩略地图控件
		
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		//创建自定义图标
		var pt = new BMap.Point(116.417, 39.909);
		var myIcon = new BMap.Icon("<%=base%>resources/images/common/ndepot.png", new BMap.Size(41, 26));
		var marker2 = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
		map.addOverlay(marker2); 
		
		var label = new BMap.Label("北京通州库",{offset:new BMap.Size(20,-10)});
		marker2.setLabel(label);
		marker2.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		
		var opts = {
				  width : 200,     // 信息窗口宽度
				  height: 100,     // 信息窗口高度
				  title : "北京通州库" , // 信息窗口标题
				  enableMessage:true,//设置允许信息窗发送短息
				  message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
				}
		var infoWindow = new BMap.InfoWindow("地址：北京市通州区果园路10号", opts);  // 创建信息窗口对象 
		marker2.addEventListener("click", function(){          
			map.openInfoWindow(infoWindow,pt); //开启信息窗口
		});
	});
	
	
</script>	
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',split:true" 
			style="overflow: hidden; ">
	<div id="allmap"></div>
	</div>
	
	<div data-options="region:'east',split:true " title="储粮区域信息"
		style="overflow: hidden;width: 220px;">
	
	</div>
</body>
</html>
