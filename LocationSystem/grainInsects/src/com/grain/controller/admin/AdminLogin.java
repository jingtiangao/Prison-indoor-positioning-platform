package com.grain.controller.admin;


import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.BaiduMapApi;
import com.grain.CommonAttributes;
import com.grain.Principal;
import com.grain.Setting;
import com.grain.Principal.UserType;
import com.grain.Setting.CaptchaType;
import com.grain.entity.user.Admin;
import com.grain.service.CaptchaService;
import com.grain.service.user.AdminService;
import com.grain.util.SettingUtils;
import com.grain.util.WebUtils;

/**
 * Controller - 管理员登录
 * 
 */
@Controller
@RequestMapping("/admin")
public class AdminLogin extends BaseController {

	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminsrv;

	@Resource(name = "baiduMapApi")
	BaiduMapApi baidu;
	/**
	 * 登录检测
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody
	Boolean check() {
		return adminsrv.isAuthenticated();
	}
	
	/**
	 * 登录页面
	 */	
	@RequestMapping(value="/login")
	public String login() {
		return "/admin/login";
	}
	
	/**
	 * 管理员主页面
	 */
	@RequestMapping(value="/main")
	public String main(HttpSession session,ModelMap model) {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			Admin u = adminsrv.find(p.getUsername());
			if (u==null) return "redirect:/common/login";
			model.addAttribute("userName",u.getUsername());
		} else
			return "redirect:/common/login";
		return "/admin/main";
	}

	/**
	 * 登录页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(String redirectUrl, HttpServletRequest request, ModelMap model) {
		Setting setting = SettingUtils.get();
		if (redirectUrl != null && !redirectUrl.equalsIgnoreCase(setting.getSiteUrl()) 
				&& !redirectUrl.startsWith(request.getContextPath() + "/") 
				&& !redirectUrl.startsWith(setting.getSiteUrl() + "/")) {
			redirectUrl = null;
		}
		model.addAttribute("redirectUrl", redirectUrl);
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/admin/login";
	}

	/**
	 * 登录提交
	 */
	@RequestMapping(value="/submit", method = RequestMethod.POST)
	public String submit(String captchaId, String captcha, String username,String password , Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		if (!captchaService.isValid(CaptchaType.memberLogin, captchaId, captcha)) {
			model.addAttribute("errormsg", "验证码错误");
			return "/admin/login";
		}
		Admin admin= new Admin();
		admin.setUsername(username);
		admin.setPassword(password==null?"":password);
		if (!adminsrv.loginCheck(admin)){
			model.addAttribute("errormsg", "用户名或密码错误");
			return "/admin/login";
		}
		
		session.invalidate();
		session = request.getSession();

		session.setAttribute(CommonAttributes.Principal, new Principal(UserType.admin, admin.getUsername()));
		session.setAttribute(CommonAttributes.BaiduMapApiAccount, baidu.getAccount());
		WebUtils.addCookie(request, response, "admin", admin.getUsername());
		
		return "/admin/main";
	}

	@RequestMapping("/logout")
	public String logout( HttpSession session){
		session.removeAttribute(CommonAttributes.Principal);
		return "redirect:/common/login";
	}
}