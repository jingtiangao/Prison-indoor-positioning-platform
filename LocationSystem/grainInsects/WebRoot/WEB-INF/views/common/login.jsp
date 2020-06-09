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
<title><%=SpringUtils.getMessage("admin.login.title")%></title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="author" content="szy Team" />
<meta name="copyright" content="szy ++" />
<%
String path = request.getContextPath();
String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path +"/";
// System.out.println(base);
String captchaId = UUID.randomUUID().toString();
Setting setting = SettingUtils.get();

%>
<link rel="icon" href="<%=base%>resources/images/fonter-icon.ico" type="image/x-icon" />
<link href="<%=base%>resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=base%>resources/admin/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>resources/admin/js/jquery.js"></script>
<script type="text/javascript">
$().ready( function() {
	var $loginForm = $("#loginForm");
	var $username = $("#username");
	var $password = $("#password");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $isRememberUsername = $("#isRememberUsername");
	
	// 更换验证码
	$captchaImage.click( function() {
		$captchaImage.attr("src", "<%=base%>admin/common/captcha?captchaId=<%=captchaId%>&timestamp=" + (new Date()).valueOf());
	});
	
	// 表单验证、记住用户名
	$loginForm.submit( function() {
		if ($username.val() == "") {
			$.message("warn", "<%=SpringUtils.getMessage("admin.login.usernameRequired")%>");
			return false;
		}
		if ($password.val() == "") {
			$.message("warn", "<%=SpringUtils.getMessage("admin.login.passwordRequired")%>");
			return false;
		}
		if ($captcha.val() == "") {
			$.message("warn", "<%=SpringUtils.getMessage("admin.login.captchaRequired")%>");
			return false;
		}
		
		if ($isRememberUsername.prop("checked")) {
			addCookie("adminUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
		} else {
			removeCookie("adminUsername");
		}
	});
	
});
</script>

</head>
<body>
		<div class="login">
		<p>${errormsg}</p>
			<form id="loginForm" action="<%=base%>common/submit" method="post">
				<input type="hidden" name="captchaId" value="<%=captchaId%>" />
				<table>
					<tr>
						<td width="190" rowspan="2" align="center" valign="bottom">
							<img src="<%=base%>resources/images/common/logo1.jpg" alt="grainInsects" />
						</td>
						<th>
							<%=SpringUtils.getMessage("admin.login.username")%>:
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
								<img id="captchaImage" class="captchaImage" src="<%=base%>admin/common/captcha?captchaId=<%=captchaId%>" 
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
							<input type="button" class="homeButton" value="" onclick="location.href='<%=base%>'" />
							<input type="submit" class="loginButton" value="<%=SpringUtils.getMessage("admin.login.login")%>" />
						</td>
					</tr>
				</table>
				<div class="powered">COPYRIGHT © 2015-2018 szy.xxx.net ALL RIGHTS RESERVED.</div>
				<div class="link">
					<a href="<%=base%>"><%=SpringUtils.getMessage("admin.login.home")%></a> |
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