<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" charset="utf-8">
	function logoutFun(b) {
		$.getJSON('admin/logoff', {
			t : new Date()
		}, function(result) {
			if (b) {
				location.replace('${pageContext.request.contextPath}/');
			} else {
				$('#sessionInfoDiv').html('');
				$('#loginDialog').dialog('open');
			}
		});
	}
</script>
<div id="sessionInfoDiv"
	style="position: absolute; right: 10px; top: 5px;  font-size:12px;"
	class="lalert alert-info">
	<c:if test="${principal.username != null}">[<strong>${principal.username}</strong>]，欢迎你！您使用[<strong>${sessionInfo.ip}</strong>]IP登录！
	</c:if>
</div>

<div style="font-size:18px; position: absolute; right: 0px; bottom: 0px;">
	<a href="admin/logout"  ><img src="resources/easyUI/themes/extjs_icons/cog.png" />注销</a>
</div>

