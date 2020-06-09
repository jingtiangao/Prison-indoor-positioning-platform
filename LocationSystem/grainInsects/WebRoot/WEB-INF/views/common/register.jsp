<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- doctype一定要写在这个位置 -->
<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/easyui.jsp"></jsp:include>
<%
	String path = request.getContextPath();
	String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
		<title>main - Powered By Szy++</title>
		<meta name="author" content="Szy++ Team" />
		<meta name="copyright" content="Szy++" />
		<link href="<%=base %>resources/admin/css/common.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=base %>resources/easyUI/themes/cupertino/easyui.css"></link>
		<style>
			.clearfix:after { 
			    content:"\200B"; 
			    display:block; 
			    height:0; 
			    clear:both; 
			} 
			.clearfix {*zoom:1;}/*IE/7/6*/
		</style>
		<script type="text/javascript" src="<%=base %>resources/admin/js/common.js"></script>
		<script type="text/javascript" src="<%=base %>resources/admin/js/list.js"></script>
		<script type="text/javascript" src="<%=base %>resources/admin/js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/admin/js/input.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/admin/js/jquery.tools.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/admin/editor/kindeditor.js"></script>
		<script type="text/javascript">
		 	doAdd = function(){
				$("#inputForm").submit();
			}
			$(function(){
				var $inputForm = $("#inputForm");
				// 表单验证
				if($("#juese").val() == "zhuanjia"||$("#juese").val() == "nonghu"){
				$inputForm.validate({
					rules: {
						username: {
							required: true,
							remote: {
								url: "check_username",
								cache: false
							}
						},
						point: "required",
						password: {
							required: true,
							pattern: /^[^\s&\"<>]+$/,
							minlength: 4,
							maxlength: 20
						},
						rePass: {
							required: true,
							equalTo: "#password"
						},
						hasaudit:	{required:true},
					},messages: {
						username: {
							required: "必填",
							remote: "用户名已存在"
						}
					},
					submitHandler: function() {
						var formData = new FormData($("#inputForm")[0]); 
						if($("#juese").val() == "zhuanjia"){
						$.ajax({
							url : 'addexp',
							data: formData,
							dataType : 'json',
							type: 'POST',
							/* async: false,  
					        cache: false,  */
							contentType: false,  
					        processData: false,  
							success : function(r) {
								if (r && r.success) {
									parent.$.messager.alert('提示', r.msg); //easyui中的控件messager
									goback();
								} else {
									parent.$.messager.alert('数据更新或插入', r.msg, 'error'); //easyui中的控件messager
								}
							}
						});}
						
						
						
						if($("#juese").val() == "nonghu"){
							$.ajax({
								url : 'addfar',
								data: formData,
								dataType : 'json',
								type: 'POST',
								/* async: false,  
						        cache: false,  */
								contentType: false,  
						        processData: false,  
								success : function(r) {
									if (r && r.success) {
										parent.$.messager.alert('提示', r.msg); //easyui中的控件messager
										goback();
									} else {
										parent.$.messager.alert('数据更新或插入', r.msg, 'error'); //easyui中的控件messager
									}
								}
							});}
						
					}
				});}
				if($("#juese").val() == "jiagongchang"||$("#juese").val() == "chongdiao"){
					$inputForm.validate({
						rules: {
							username: {
								required: true,
								remote: {
									url: "check_username",
									cache: false
								}
							},
							point: "required",
							pass: {
								required: true,
								pattern: /^[^\s&\"<>]+$/,
								minlength: 4,
								maxlength: 20
							},
							rePass: {
								required: true,
								equalTo: "#pass"
							},
							hasaudit:	{required:true},
						},messages: {
							username: {
								required: "必填",
								remote: "用户名已存在"
							}
						},
						submitHandler: function() {
							var formData = new FormData($("#inputForm")[0]); 
							
							
							if($("#juese").val() == "chongdiao"){
								$.ajax({
									url : 'addinv',
									data: formData,
									dataType : 'json',
									type: 'POST',
									/* async: false,  
							        cache: false,  */
									contentType: false,  
							        processData: false,  
									success : function(r) {
										if (r && r.success) {
											parent.$.messager.alert('提示', r.msg); //easyui中的控件messager
											goback();
										} else {
											parent.$.messager.alert('数据更新或插入', r.msg, 'error'); //easyui中的控件messager
										}
									}
								});}
							
							
							if($("#juese").val() == "jiagongchang"){
								$.ajax({
									url : 'addfac',
									data: formData,
									dataType : 'json',
									type: 'POST',
									/* async: false,  
							        cache: false,  */
									contentType: false,  
							        processData: false,  
									success : function(r) {
										if (r && r.success) {
											parent.$.messager.alert('提示', r.msg); //easyui中的控件messager
											goback();
										} else {
											parent.$.messager.alert('数据更新或插入', r.msg, 'error'); //easyui中的控件messager
										}
									}
								});}
						}
					});
				}
			})
			goback = function(){
				window.history.back();
			}
		</script>
	</head>
	<body>
		<div class="container" style="width:400px;position:fixed;top:30px;left:500px;" align="center">
			
				<div style="font-size:22px;">
				新用户注册
				</div>
			
			<form id="inputForm" method="post">
				<table class="input tabContent">
					<tbody>
					<tr id="usertype">
						<th>用户类型：</th>
    					<td><select name="juese" id="juese" >
      						<option value="zhuanjia" selected="zhuanjia" >专家</option>
      						<option value="chongdiao">虫调人员</option>
      						<option value="nonghu">储粮农户</option>
      						<option value="jiagongchang">加工厂用户</option>
   						 	</select>
   						 </td>
  					</tr>
					<tr id="username">
						<th><span class="requiredField">*</span>用户名：</th>
						<td><input name="username" class="text" type="text"/></td>
					</tr>
					<tr id="name">
						<th>真实姓名：</th>
						<td><input name="name" class="text" type="text"/></td>
					</tr>
					<tr id="realname">
						<th>真实姓名：</th>
						<td><input name="realname" class="text" type="text"/></td>
					</tr>
					<tr id="pass1">
						<th><span class="requiredField">*</span>密码：</th>
						<td><input id="password" name="password" class="text" type="password"/></td>
					</tr>
					<tr id="pass2">
						<th><span class="requiredField">*</span>密码：</th>
						<td><input id="pass" name="pass" class="text" type="password"/></td>
					</tr>
					<tr id="rePass">
						<th><span class="requiredField">*</span>确认密码：</th>
						<td><input name="rePass" class="text" type="password"/></td>
					</tr>
					
					<tr id="title">
						<th>职称：</th>
						<td>
							<input name="title" class="text" type="text"/>
						</td>
					</tr>
					<tr id="company">
						<th>单位：</th>
						<td>
							<input name="company" class="text" type="text"/>
						</td>
					</tr>
					<tr id="mobile">
						<th><span class="requiredField">*</span>手机：</th>
						<td>
							<input name="mobile" class="text" type="text"/>
						</td>
					</tr>
					<tr id="direct">
						<th>地区：</th>
						<td>
							<input name="direct" class="text" type="text"/>
						</td>
					</tr>
					<tr id="address">
						<th>详细地址：</th>
						<td>
							<input name="address" class="text" type="text"/>
						</td>
					</tr>
					<tr id="longitude">
						<th>经度（°）：</th>
						<td>
							<input name="longitude" class="text" type="text"/>
						</td>
					</tr>
					<tr id="latitude">
						<th>纬度（°）：</th>
						<td>
							<input name="latitude" class="text" type="text"/>
						</td>
					</tr>
					<tr id="altitude">
						<th>海拔（m）：</th>
						<td>
							<input name="altitude" class="text" type="text"/>
						</td>
					</tr>
					<tr id="postcode">
						<th>邮编：</th>
						<td>
							<input name="postcode" class="text" type="text"/>
						</td>
					</tr>
					<tr id="specialty">
						<th>特长领域：</th>
						<td>
							<input name="specialty" class="text" type="text"/>
						</td>
					</tr>
					<tr id="name_factory">
						<th>加工厂名称：</th>
						<td>
							<input name="name_factory" class="text" type="text"/>
						</td>
					</tr>
					<tr id="constructiondate">
						<th>建厂日期：</th>
						<td>
							<input name="constructiondate" class="text" type="text"/>
						</td>
					</tr>
					<tr id="annualpurchase">
						<th>年收购量（吨）：</th>
						<td>
							<input name="annualpurchase" class="text" type="text"/>
						</td>
					</tr>
					<tr id="majorkindofpurchase">
						<th>主要收购粮种：</th>
						<td>
							<input name="majorkindofpurchase" class="text" type="text"/>
						</td>
					</tr>
					<tr>
						<td style="border:none;padding:5px 100px;">
							<span style="display:inline-block;"><a href="javascript:void(0)" class="easyui-linkbutton"
									plain="false" onclick="doAdd()" iconCls="icon-save">注册</a></span>
							<span style="display:inline-block;"><a href="javascript:void(0)" class="easyui-linkbutton"
									plain="false" onclick="goback()" iconCls="icon-back">返回</a></span>
						</td>
					</tr>	
					</table>
			</form>
		</div>
	</body>
	<script type="text/javascript">
  $(function(){
	  $("#usertype").css('display', 'block');
      $("#username").css('display', 'block');
      $("#name").css('display', 'block');
      $("#realname").css('display', 'none');
      $("#pass1").css('display', 'block');
      $("#pass2").css('display', 'none');
	  $("#rePass").css('display', 'block');
      $("#title").css('display', 'block');
      $("#company").css('display', 'block');
      $("#mobile").css('display', 'block');
	  $("#direct").css('display', 'none');
      $("#address").css('display', 'none');
      $("#longitude").css('display', 'none');
      $("#latitude").css('display', 'none');
	  $("#altitude").css('display', 'none');
      $("#postcode").css('display', 'none');
      $("#specialty").css('display', 'block');
      $("#name_factory").css('display', 'none');
	  $("#constructiondate").css('display', 'none');
      $("#annualpurchase").css('display', 'none');
      $("#majorkindofpurchase").css('display', 'none');
    $("#juese").change(function(event) {
    	
      if ($("#juese").val() == "zhuanjia") {
      $("#usertype").css('display', 'block');
      $("#username").css('display', 'block');
      $("#name").css('display', 'block');
      $("#realname").css('display', 'none');
      $("#pass1").css('display', 'block');
      $("#pass2").css('display', 'none');
	  $("#rePass").css('display', 'block');
      $("#title").css('display', 'block');
      $("#company").css('display', 'block');
      $("#mobile").css('display', 'block');
	  $("#direct").css('display', 'none');
      $("#address").css('display', 'none');
      $("#longitude").css('display', 'none');
      $("#latitude").css('display', 'none');
	  $("#altitude").css('display', 'none');
      $("#postcode").css('display', 'none');
      $("#specialty").css('display', 'block');
      $("#name_factory").css('display', 'none');
	  $("#constructiondate").css('display', 'none');
      $("#annualpurchase").css('display', 'none');
      $("#majorkindofpurchase").css('display', 'none');
    }
    if($("#juese").val() == "chongdiao"){
      $("#usertype").css('display', 'block');
      $("#username").css('display', 'block');
      $("#name").css('display', 'none');
      $("#realname").css('display', 'block');
      $("#pass1").css('display', 'none');
      $("#pass2").css('display', 'block');
	  $("#rePass").css('display', 'block');
      $("#title").css('display', 'block');
      $("#company").css('display', 'block');
      $("#mobile").css('display', 'block');
	  $("#direct").css('display', 'none');
      $("#address").css('display', 'none');
      $("#longitude").css('display', 'none');
      $("#latitude").css('display', 'none');
	  $("#altitude").css('display', 'none');
      $("#postcode").css('display', 'none');
      $("#specialty").css('display', 'none');
      $("#name_factory").css('display', 'none');
	  $("#constructiondate").css('display', 'none');
      $("#annualpurchase").css('display', 'none');
      $("#majorkindofpurchase").css('display', 'none');
    }
    if($("#juese").val() == "nonghu"){
      $("#usertype").css('display', 'block');
      $("#username").css('display', 'block');
      $("#name").css('display', 'none');
      $("#realname").css('display', 'block');
      $("#pass1").css('display', 'block');
      $("#pass2").css('display', 'none');
	  $("#rePass").css('display', 'block');
      $("#title").css('display', 'none');
      $("#company").css('display', 'none');
      $("#mobile").css('display', 'block');
	  $("#direct").css('display', 'block');
      $("#address").css('display', 'block');
      $("#longitude").css('display', 'block');
      $("#latitude").css('display', 'block');
	  $("#altitude").css('display', 'block');
      $("#postcode").css('display', 'block');
      $("#specialty").css('display', 'none');
      $("#name_factory").css('display', 'none');
	  $("#constructiondate").css('display', 'none');
      $("#annualpurchase").css('display', 'none');
      $("#majorkindofpurchase").css('display', 'none');
    }
    if($("#juese").val() == "jiagongchang"){
      $("#usertype").css('display', 'block');
      $("#username").css('display', 'block');
      $("#name").css('display', 'none');
      $("#realname").css('display', 'block');
      $("#pass1").css('display', 'none');
      $("#pass2").css('display', 'block');
	  $("#rePass").css('display', 'block');
      $("#title").css('display', 'block');
      $("#company").css('display', 'none');
      $("#mobile").css('display', 'block');
	  $("#direct").css('display', 'block');
      $("#address").css('display', 'block');
      $("#longitude").css('display', 'block');
      $("#latitude").css('display', 'block');
	  $("#altitude").css('display', 'block');
      $("#postcode").css('display', 'block');
      $("#specialty").css('display', 'none');
      $("#name_factory").css('display', 'block');
	  $("#constructiondate").css('display', 'block');
      $("#annualpurchase").css('display', 'block');
      $("#majorkindofpurchase").css('display', 'block');
    }
    });
  });
</script>
</html>