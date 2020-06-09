<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<script type = "text/javascript" src = "jquery.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<meta charset='utf-8'>
		<title></title>
		<style>
			html,body{
				margin:0px;
				padding:0px;
			}
			#container{
				position:relative;
				width:800px;
				height:800px;
                border: 1px solid #000;
			}
			#canvas{
				width:100%;
				height:100%;
			}
			#icon{
				position:absolute;
				right:10px;
				bottom:10px;
			}
			button{
				padding:0;
				margin:0;
			}
			.search{
  				position:absolute;
  				top:10px;
 				right:10px;
  				width:250px;
  				padding:3px;
  				box-shadow:0 3px 3px 0 #ddd;
			}
			.label{
  				float:left;
			}
			form div span{
  				color:#666;
  				width:70px;
  				display:inline-block;
  				text-align:center;
			}
			form div span:hover{
  				color:#5af;
			}
			
		</style>
		<jsp:include page="/common/easyui.jsp"></jsp:include>
	</head>
	<body class="easyui-layout" data-options="fit:false,border:false">
		<div id="container" data-options="region:'center',split:false,border:true" 
			style="overflow: hidden; ">
			<div id="container_map">
				<canvas id="canvas" width="800" height="800"></canvas>
				<canvas id="layer2" width="800" height="800"></canvas>
			</div>
			<div id="icon">
			    <i class="fa fa-warning" style="font-size:50px;color:green"></i>
				<button type="button" id="zoom-in"><img src="zoom-in.png" alt="zoom-in" /></button>	
				<button type="button" id="zoom-out"><img src="zoom-out.png" alt="zoom-out" /></button>	
				<button type="button" id="revert"><img src="key.png" alt="revert" /></button>				
			</div>
			<div id="search_div">
  				 <form class="search" onsubmit="search(); return false;">
				    <div class="label"><span value='1'>所有</span></div>
				    <div class="label">|</div>
				    <div class="label"><span value='2'>组</span></div>
				    <div class="label">|</div>
				    <div class="label"><span value='3'>犯人编号</span></div>
				   
				    <input  type="search" name="search" placeholder="搜索组、人、监区" id="search">
				 	 <!--   <input type="submit" value="定位" >  -->
					<button type="button"  onclick="locate(event)">定位</button>
				  </form>
			</div>
		</div>
		<div data-options="region:'east',split:false " title=" "
		style="overflow: hidden;width: 220px;">
			<ul id="tt" class="easyui-tree" data-options="url:'http://localhost:8080/grainInsects/depoter/common//allprisoner_count'">  
				   
			        
			           
			       
			</ul>  
		</div>
		<script type="text/javascript" src="main.js"></script>
		<script type="text/javascript" src="search.js"></script>
	</body>
	 
</html>
