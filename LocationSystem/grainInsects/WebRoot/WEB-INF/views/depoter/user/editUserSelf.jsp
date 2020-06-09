<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/common/easyui.jsp"></jsp:include>

<html>
<head>
<meta name="author" content="Szy++ Team" />
<meta name="copyright" content="Szy++" />
<link href="<%= request.getContextPath() %>/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/admin/js/common.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {
	var $inputForm = $("#inputForm");
	// 表单验证
	$inputForm.validate({
		rules: {
			pass: {
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			rePass: {
				equalTo: "#pass"
			},
			hasaudit:{required:true},
			manager:{required:true},
			graindepotid:{required:true},
		},
 		submitHandler: function() {
 			var formData = new FormData($( "#inputForm" )[0]); 
 			var data=$("#inputForm").serialize();
 			data = data.replace(/\+/g," ");   // g表示对整个字符串中符合条件的都进行替换
			$.ajax({
				url : 'editDepotUser',//$("#inputForm").attr("action"),
				data : data, //$("#inputForm").serialize(),
				dataType : 'json',
/*  				contentType: false,  
		        processData: false,   */
				success : function(r) {
					if (r && r.success) {
						parent.$.messager.alert('提示', r.msg); //easyui中的控件messager
					} else {
						parent.$.messager.alert('数据更新或插入', r.msg, 'error'); //easyui中的控件messager
					}
				}
			});
		} 
	});
	
	var $binsTable = $("#binsTable");
	var $addbins = $("#addbins");
	var $selectbins = $("#bins");
	var $deleteBin = $("a.deleteBin");

	// 增加粮仓
	$addbins.click(function() {
		var trHtml = 
			'<tr>' +
 				'<td>' +
					'<select id="bins" name="bins" style="width:250px;" >' + 
					'<c:forEach var="bin" items="${nousedbins}" begin="0">' +
						'<option value="${bin.lcbm}">${bin.lcbm} , ${bin.typebin} </option>' +
					'</c:forEach>' +
					'</select>' +
				'</td>' +  
				'<td id="lcbm" class="text">' +'</td>' +
				'<td id="typebin" class="text">' +'</td>' +
				'<td> ' +
					'<a href="javascript:;" class="deleteBin" style="width:50px,align:left" >[ 删除 ]</a>' +
				'</td> ' +
			'</tr>';
		$binsTable.append(trHtml);
	});
	
	// 删除粮仓
	$deleteBin.live("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "删除该粮仓吗？请确认！",
			onOk: function() {
				$this.closest("tr").remove();
			}
		});
	});
	
 	$selectbins.live("change", function(value) {
		var $this = $(this);
		var select=$this.closest("tr").find("#bins")[0];
		var value = select.value;
		var index=select.selectedIndex; //序号，取当前选中选项的序号
		var text = select.options[index].text;
		var str= text.split(","); //字符分割 

		$this.closest("tr").find("#lcbm").html(str[1]);
		$this.closest("tr").find("#typebin").html(str[2]);
	}); 
 	
});
	//submit the form
 	doAdd = function(){
		$("#inputForm").submit();
	} 
	
	goback = function(){
		window.history.back();
	}

</script>	
</head>
<body class="easyui-layout">
		<div data-options="region:'center',border:false" title="个人信息管理 > 修改个人信息  ">
		<form id="inputForm"    method="post" enctype="multipart/form-data">
<!-- 			<ul id="tab" class="tab"> -->
<!-- 				<li> -->
<!-- 					<input type="button" value="基本信息" /> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<input type="button" value="管理粮仓" /> -->
<!-- 				</li>  -->
<!-- 				<li> -->
<!-- 					<input type="button" value="修改密码" /> -->
<!-- 				</li> -->
<!-- 			</ul>			 -->
			<table class="input tabContent">
				<tr><th> 储粮区：</th> <td>${user.graindepot.area.gd.fullname}</td></tr>
				<tr><th><span class="requiredField">*</span> 粮库：</th> <td>${user.graindepot.lkmc}
					<input type="hidden" name="graindepotid" value="${user.graindepot.lkbm}">
					<input type="hidden" name="manager" value="${user.manager}">
					<input type="hidden" name="hasaudit" value="${user.hasaudit}">
					</td></tr>
				<tr><th><span class="requiredField">*</span> 用户名：</th><td>
					<input type="text" name="username" class="text" readonly="readonly" value="${user.username}"/></td></tr>
				<tr><th> 真实姓名：</th><td><input type="text" name="realname" class="text" value="${user.realname}" /></td></tr>
<!-- 				<tr><th> 密码：</th><td><input type="text" id="pass" name="pass" class="text" /></td></tr> -->
<!-- 				<tr><th> 确认密码：</th><td><input type="text" id="rePass"  class="text" /></td></tr> -->
				<tr><th> 职称：</th><td><input type="text" name="title" class="text" value="${user.title}" /></td></tr>
				<tr><th> 手机：</th><td><input type="text" name="mobile" class="text" value="${user.mobile}" /></td></tr>
				<!-- <tr><th><span class="requiredField">*</span> 审核：</th><td>
					<select name="hasaudit" >
						<option value=0 <c:if test="${!user.hasaudit}"> selected="true" </c:if> >否</option>
						<option value=1 <c:if test="${user.hasaudit}"> selected="true" </c:if> >是</option>
					</select></td>
				</tr>
				<tr><th><span class="requiredField">*</span> 所长：</th><td>
					<select name="manager" >
						<option value=0 <c:if test="${!user.manager}"> selected="true" </c:if> >否</option>
						<option value=1 <c:if test="${user.manager}"> selected="true" </c:if> >是</option>
					</select></td>
				</tr>  -->
			</table>
			
<!-- 			<table id="binsTable" class="input tabContent"> -->
<!-- 				<tr> -->
<!-- 					<td colspan="2"> -->
<!-- 						<a href="javascript:;" id="addbins" class="button">增加粮仓</a> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr class="title"> -->
<!-- 					<td >粮仓编码</td> -->
<!-- 					<td >粮仓类型</td> -->
<!-- 				</tr> -->
<%-- 				<c:forEach var="bin" items="${user.grainbins}" varStatus="status"> --%>
<!-- 					<tr> -->
<%-- 						<td><input type="text" name="bins" value="${bin.lcbm}" readonly="readonly" /></td> --%>
<%-- 						<td>${bin.typebin}</td> --%>
<!-- 						<td> -->
<!-- 							<a href="javascript:;" class="deleteBin">[ 删除 ]</a> -->
<!-- 						</td> -->
<!-- 					</tr>				 -->
<%-- 				</c:forEach> --%>
<!-- 			</table> -->
			<table class="input">
				<tr><th>&nbsp;</th>
				<td >
				<!--  <input type="submit" class="button" value="save" /> -->
				<a href="javascript:void(0)" class="easyui-linkbutton"
						plain="false" onclick="doAdd()" iconCls="icon-save">保存</a>
<!-- 				<a href="javascript:void(0)" class="easyui-linkbutton" -->
<!-- 						plain="false" onclick="goback()" iconCls="icon-back">返回</a> -->
				</td>
				</tr>	
			</table>							
		</form>
	</div>
</body>
</html>