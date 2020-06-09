package com.grain.controller.investor;


import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Principal;
import com.grain.Principal.UserType;
import com.grain.Setting;
import com.grain.Setting.CaptchaType;
import com.grain.controller.admin.BaseController;
import com.grain.entity.user.Insectsinvestuser;
import com.grain.service.CaptchaService;
import com.grain.service.user.InsectsinvestuserService;
import com.grain.util.SettingUtils;
import com.grain.util.WebUtils;

/**
 * Controller - 虫调用户登录
 * 
 */
@Controller
@RequestMapping("/investor/front")
public class InsectsinvestuserLogin extends BaseController {

	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name = "insectsinvestuserServiceImpl")
	private InsectsinvestuserService insectsinvestusersrv;

	/**
	 * 只使用@ModelAttribute标记该方法，而不是用@RequestMapping，则请求该类中的任何一个url都会执行该方法
	 * @param model
	 */
	@ModelAttribute
	public void mytest(ModelMap model){
		model.addAttribute("captchaId", UUID.randomUUID().toString());
	}
	
	/**
	 * 登录检测
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody
	Boolean check() {
		return insectsinvestusersrv.isAuthenticated();
	}
	
	/**
	 * 登录页面
	 */	
	@RequestMapping(value="/login")
	public String login(ModelMap model) {
		//model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/investor/front/login";
	}
	
	@RequestMapping(value="/getCapchaId", method=RequestMethod.GET)
	public String getCapchaId(ModelMap model) {
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/investor/front/login";
	}
	
	/**
	 * 虫调用户主页面
	 */
	@RequestMapping(value="/main")
	public String main() {
		return "/investor/front/main";
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
		//model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/investor/front/login";
	}

	/**
	 * 登录提交
	 */
	@RequestMapping(value="/submit", method = RequestMethod.POST)
	public String submit(String captchaId, String captcha, String username,String password , Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		if (!captchaService.isValid(CaptchaType.memberLogin, captchaId, captcha)) {
			model.addAttribute("errormsg", "验证码错误");
			return "/investor/front/login";
		}
		Insectsinvestuser insectsinvestuser = new Insectsinvestuser();
		insectsinvestuser.setUsername(username);
		insectsinvestuser.setPass(password==null?"":password);
		if (!insectsinvestusersrv.loginCheck(insectsinvestuser)){
			model.addAttribute("errormsg", "用户名密码错误,或者该用户没有通过审核！");
			return "/investor/front/login";
		}
		
		session.invalidate();
		session = request.getSession();

		session.setAttribute(CommonAttributes.Principal, new Principal(UserType.invest, insectsinvestuser.getUsername()));
		WebUtils.addCookie(request, response, "invest", insectsinvestuser.getUsername());
		
		return "/investor/front/main";
	}
	
	/*@RequestMapping(value="/submit", method = RequestMethod.POST)
	public String submit(String username,String password , Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		Insectsinvestuser insectsinvestuser = new Insectsinvestuser();
		insectsinvestuser.setUsername(username);
		insectsinvestuser.setPass(password==null?"":password);
		if (!insectsinvestusersrv.loginCheck(insectsinvestuser)){
			model.addAttribute("errormsg", "用户名或密码错误");
			return "/investor/front/login";
		}
		
		session.invalidate();
		session = request.getSession();

		session.setAttribute(CommonAttributes.Principal, new Principal(UserType.invest, insectsinvestuser.getUsername()));
		WebUtils.addCookie(request, response, "invest", insectsinvestuser.getUsername());

		return "/investor/front/main";
	}*/
	
	/**
	 * 登录后的主界面
	 * @param redirectUrl
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/register")
	public String register(){
		return "/investor/front/register";
	}
	
	@RequestMapping(value="register_submit")
	public String registerSubmit(Insectsinvestuser insector){
		insectsinvestusersrv.save(insector);
		return "/investor/front/login";
	}
	
	@RequestMapping(value="/{target}", method = RequestMethod.GET)
	public String main(@PathVariable(value="target") String redirectUrl, HttpServletRequest request, ModelMap model) {
		return "/investor/front/"+redirectUrl;
	}

}