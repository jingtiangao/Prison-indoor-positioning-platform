<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="java.util.UUID"%>
<%@page import="org.apache.commons.lang.ArrayUtils"%>
<%@page import="com.grain.Setting"%>
<%@page import="com.grain.util.SettingUtils"%>
<%@page import="com.grain.util.SpringUtils"%>
<%@page import="com.grain.Setting.CaptchaType"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>A<%=SpringUtils.getMessage("admin.login.title")%> - Powered By SHOP++</title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="author" content="szy Team" />
<meta name="copyright" content="szy ++" />
<%
String path = request.getContextPath();
String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path +"/";
//System.out.println(base);
String captchaId = UUID.randomUUID().toString();
Setting setting = SettingUtils.get();

%>
<link rel="icon" href="<%=base%>favicon.ico" type="image/x-icon" />
<link href="<%=base%>resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=base%>resources/admin/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/easyUI/extJquery.js" charset="utf-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/admin/js/common.js"></script>

<script type="text/javascript">
$().ready( function() {
	var $loginForm = $("#loginForm");
	var $captchaImage = $("#captchaImage");
	var $isRememberUsername = $("#isRememberUsername");
	
	// 更换验证码
	$captchaImage.click( function() {
		$captchaImage.attr("src", "<%=base%>depoter/common/captcha?captchaId=<%=captchaId%>&timestamp=" + (new Date()).valueOf());
	});

	// 表单验证
	$loginForm.validate({
		rules: {
			username: {required:true},
			password : {required:true},
			captcha : {required:true},
		},
		submitHandler: function(){
			if ($isRememberUsername.prop("checked")) {
				addCookie("depotUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
			} else {
				removeCookie("depotUsername");
			}
			$.ajax({
				url : 'loginsubmit',
				data : $("#loginForm").serialize(),
				dataType : 'json',
				success : function(r) {
					if (r && r.success) {
						if (r.msg=='manager'){
							parent.$("#depotlist").css('display', 'block');	//显示菜单
							parent.$("#userRegister").css('display', 'block');	//显示菜单
							parent.$("#depotlk").css('display', 'block');
							parent.$("#depotlc").css('display', 'block');
						}else{
							parent.$("#depotlist").css('display', 'none');	//
							parent.$("#userRegister").css('display', 'none');	//
							parent.$("#depotlk").css('display', 'none');
							parent.$("#depotlc").css('display', 'none');
						}
						openBlank("<%= request.getContextPath() %>/depoter/common/success",{} );
					} else {
						$("#errid").html(r.msg);
					}
				}
			});
		}
	});
	
	
});
</script>

</head>
<body>
		<div class="login">
		<p id="errid">${errormsg}</p>
			<form id="loginForm"  method="post">
				<input type="hidden" name="captchaId" value="<%=captchaId%>" />
				<table>
					<tr>
						<td width="190" rowspan="2" align="center" valign="bottom">
							<img src="<%=base%>resources/images/common/logo1.jpg" alt="grainInsects" />
						</td>
						<th>
							<%=SpringUtils.getMessage("admin.login.username")%>
						</th>
						<td>
							<input type="text" id="username" name="username" class="text" maxlength="20" />
						</td>
					</tr>
					<tr>
						<th>
							<%=SpringUtils.getMessage("admin.login.password")%>:
						</th>
						<td>
							<input type="password" id="password" name="password" class="text" maxlength="20"  />
						</td>
					</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<th>
								<%=SpringUtils.getMessage("admin.captcha.name")%>:
							</th>
							<td>
								<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4"  />
								<img id="captchaImage" class="captchaImage" src="<%=base%>depoter/common/captcha?captchaId=<%=captchaId%>" 
								title="<%=SpringUtils.getMessage("admin.captcha.imageTitle")%>" />
							</td>
						</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
						<td>
							<label>
								<input type="checkbox" id="isRememberUsername" value="true" />
								<%=SpringUtils.getMessage("admin.login.rememberUsername")%>
							</label>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='<%=base%>/'" />
							<input type="submit" class="loginButton" value="<%=SpringUtils.getMessage("admin.login.login")%>" />
							<input type="button" class="registerButton" value="注册" onclick="location.href='<%=base%>depoter/common/addDepotUserEntrance'" />
						</td>
					</tr>
				</table>
				<div class="powered">COPYRIGHT © 2015-2018 szy.xxx.net ALL RIGHTS RESERVED.</div>
				<div class="link">
					<a href="<%=base%>/"><%=SpringUtils.getMessage("admin.login.home")%></a> |
					<a href="http://www.szy.net"><%=SpringUtils.getMessage("admin.login.official")%></a> |
					<a href="http://bbs.szy.net"><%=SpringUtils.getMessage("admin.login.bbs")%></a> |
					<a href="http://www.szy.net/about.html"><%=SpringUtils.getMessage("admin.login.about")%></a> |
					<a href="http://www.szy.net/contact.html"><%=SpringUtils.getMessage("admin.login.contact")%></a> |
					<a href="http://www.szy.net/license.html"><%=SpringUtils.getMessage("admin.login.license")%></a>
				</div>
			</form>
		</div>
</body>
</html>