package com.grain.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.grain.CommonAttributes;
import com.grain.Principal;
import com.grain.service.user.InsectsinvestuserService;

/**
 * @author Mark 权限验证拦截器 虫调用户权限拦截器
 */

public class InvestorInterceptor implements HandlerInterceptor {
	private transient final Log log = LogFactory.getLog(this.getClass());
	// 根路径
	// 登陆表单路径
	private static String loginPath = "investor/common/login";
	
	// 注册
	private static String registerPath = "investor/common/addInvestorEntrance";
	
	private static String registerSubmitPath = "investor/common/register";
	
	// 静态资源路径
	private static String resourcesPath = "resources";
	// 登陆提交路径
	private static String submitPath = "investor/common/loginSubmit";
	// 注销
	private static String logoff = "investor/common/logout";
	// 主页面
	private static String mainPath = "investor/common/main";
	// 验证码
	private static String captchaPath = "investor/common/captcha";
	
	@Resource(name = "insectsinvestuserServiceImpl")
	private InsectsinvestuserService insectsinvestusersrv;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 权限判断
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		boolean logdebug = false;
		if (log.isDebugEnabled()) {
			logdebug = true;
		}

		// 上下文路径
		String contextPath = request.getContextPath();
		// 访问路径
		String targetURI = request.getRequestURI().replace(contextPath + "/", "");
		HttpSession session = request.getSession();

		// 请求的是静态资源文件(如 .js .css 图片)或者注销，则直接通过
		if (targetURI.contains(resourcesPath) || targetURI.equals(logoff) 
				|| targetURI.equals(captchaPath)||targetURI.equals(mainPath)) {
			return true;
		}

		// 未登录
		if (session.getAttribute(CommonAttributes.Principal) == null) {
			// 登陆路径与登陆表单不拦截
			if (targetURI.equals(submitPath) || targetURI.equals(loginPath) || targetURI.equals(registerPath) || targetURI.equals(registerSubmitPath)) {
				return true;
			} else {
				// 转到登陆页面
				if (logdebug) {
					log.debug("login is required!");
				}
				response.sendRedirect(contextPath + "/" + loginPath);
				return false;
			}
			// 已登录
		} else{
			Principal principal = (Principal) request.getSession().getAttribute(CommonAttributes.Principal);
			String username = principal.getUsername();

			String role = insectsinvestusersrv.userRole(username);
			if (role != null && role.equalsIgnoreCase("insectsinvestuser")) {
				return true;
			} else {
				session.invalidate();
				return true;
			}
		}
	}
}
