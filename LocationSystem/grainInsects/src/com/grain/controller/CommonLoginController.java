package com.grain.controller;

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
import com.grain.controller.admin.BaseController;
import com.grain.entity.user.Admin;
import com.grain.entity.user.DepotUser;
import com.grain.entity.user.Expert;
import com.grain.entity.user.Factoryuser;
import com.grain.entity.user.FarmerUser;
import com.grain.entity.user.Insectsinvestuser;
import com.grain.service.CaptchaService;
import com.grain.service.user.AdminService;
import com.grain.service.user.DepotUserService;
import com.grain.service.user.ExpertService;
import com.grain.service.user.FactoryuserService;
import com.grain.service.user.FarmerUserService;
import com.grain.service.user.InsectsinvestuserService;
import com.grain.util.SettingUtils;
import com.grain.util.WebUtils;

@Controller
@RequestMapping("/common")
public class CommonLoginController  extends BaseController{
	

	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminsrv;

	@Resource(name = "factoryuserServiceImpl")
	private FactoryuserService factoryuserService;
	
	@Resource(name = "farmerUserServiceImpl")
	private FarmerUserService farmerUserService;
	
	@Resource(name = "expertServiceImpl")
	private ExpertService expertService;
	
	@Resource(name = "depotUserServiceImpl")
	private DepotUserService depotUserService;
	
	@Resource(name = "insectsinvestuserServiceImpl")
	private InsectsinvestuserService insectsinvestuserService;

	@Resource(name = "baiduMapApi")
	BaiduMapApi baidu;
	/**
	 * 登录检测
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody
	Boolean check() {
		//////////////////////////////////
		return adminsrv.isAuthenticated();
	}
	
	/**
	 * 登录页面
	 */	
	@RequestMapping(value="/login")
	public String login() {
		return "/common/login";
	}
	
	/**
	 * 注册页面
	 */	
	@RequestMapping(value="/register")
	public String register() {
		return "/common/register";
	}
	
	/**
	 * 主页面
	 */
	@RequestMapping(value="/main")
	public String main() {
		return "/common/main";
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
		return "/common/login";
	}

	/**
	 * 登录提交
	 */
	@RequestMapping(value="/submit", method = RequestMethod.POST)
	public String submit(String captchaId, String captcha, String username,String password , Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		if (!captchaService.isValid(CaptchaType.memberLogin, captchaId, captcha)) {
			model.addAttribute("errormsg", "验证码错误");
			return "/common/login";
		}
		Admin admin= new Admin();
		admin.setUsername(username);
		admin.setPassword(password==null?"":password);
		
		DepotUser depot= new DepotUser();
		depot.setUsername(username);
		depot.setPass(password==null?"":password);
		
		Expert expert = new Expert();
		expert.setUsername(username);
		expert.setPassword(password==null?"":password);
		
		Factoryuser factory = new Factoryuser();
		factory.setUsername(username);
		factory.setPass(password==null?"":password);
		
		FarmerUser farmer = new FarmerUser();
		farmer.setUsername(username);
		farmer.setPassword(password==null?"":password);
		
		Insectsinvestuser insectsinvestuser = new Insectsinvestuser();
		insectsinvestuser.setUsername(username);
		insectsinvestuser.setPass(password==null?"":password);
		
		if (adminsrv.loginCheck(admin)) {

			session.invalidate();
			session = request.getSession();

			session.setAttribute(CommonAttributes.Principal, new Principal(UserType.admin, admin.getUsername()));
			session.setAttribute(CommonAttributes.BaiduMapApiAccount, baidu.getAccount());
			WebUtils.addCookie(request, response, "admin", admin.getUsername());

			return "redirect:/admin/main";

		}
		if (depotUserService.loginCheck(depot)) {

			session.invalidate();
			session = request.getSession();

			session.setAttribute(CommonAttributes.Principal, new Principal(UserType.depot, depot.getUsername()));
			session.setAttribute(CommonAttributes.BaiduMapApiAccount, baidu.getAccount());
			WebUtils.addCookie(request, response, "depot", depot.getUsername());

			return "redirect:/depoter/common/main";

		}
		if (expertService.loginCheck(expert)) {
			session.invalidate();
			session = request.getSession();

			session.setAttribute(CommonAttributes.Principal, new Principal(UserType.expert, expert.getUsername()));
			session.setAttribute(CommonAttributes.BaiduMapApiAccount, baidu.getAccount());
			WebUtils.addCookie(request, response, "expert", expert.getUsername());

			return "redirect:/expert/common/main";
		}
		if (factoryuserService.loginCheck(factory)) {

			session.invalidate();
			session = request.getSession();

			session.setAttribute(CommonAttributes.Principal, new Principal(UserType.factory, factory.getUsername()));
			session.setAttribute(CommonAttributes.BaiduMapApiAccount, baidu.getAccount());
			WebUtils.addCookie(request, response, "factory", factory.getUsername());

			return "redirect:/factory/common/main";

		}
		if (farmerUserService.loginCheck(farmer)) {

			session.invalidate();
			session = request.getSession();

			session.setAttribute(CommonAttributes.Principal, new Principal(UserType.farmer, farmer.getUsername()));
			session.setAttribute(CommonAttributes.BaiduMapApiAccount, baidu.getAccount());
			WebUtils.addCookie(request, response, "farmer", farmer.getUsername());

			return "redirect:/farmer/common/main";
		}
		if (insectsinvestuserService.loginCheck(insectsinvestuser)) {
			session.invalidate();
			session = request.getSession();

			session.setAttribute(CommonAttributes.Principal,
					new Principal(UserType.invest, insectsinvestuser.getUsername()));
			session.setAttribute(CommonAttributes.BaiduMapApiAccount, baidu.getAccount());
			WebUtils.addCookie(request, response, "invest", insectsinvestuser.getUsername());

			return "redirect:/investor/common/main";
		}

		model.addAttribute("errormsg", "用户名或密码错误");
		return "/common/login";
		
		
	}

	@RequestMapping("/logout")
	public String logout( HttpSession session){
		session.removeAttribute(CommonAttributes.Principal);
		return "/common/login";
	}

}
