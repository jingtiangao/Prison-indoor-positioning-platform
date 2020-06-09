package com.grain.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Json;
import com.grain.controller.depoter.BaseController;
import com.grain.entity.user.FarmerUser;
import com.grain.entity.user.Expert;
import com.grain.entity.user.Factoryuser;
import com.grain.entity.user.Insectsinvestuser;
import com.grain.service.user.FarmerUserService;
import com.grain.service.user.ExpertService;
import com.grain.service.user.FactoryuserService;
import com.grain.service.user.InsectsinvestuserService;

@Controller
@RequestMapping("/common")
public class UserRegisterController extends BaseController{

	@Resource(name="expertServiceImpl")
	private ExpertService expert;
	
	@Resource(name="farmerUserServiceImpl")
	private FarmerUserService farmer;	
	
	@Resource(name="factoryuserServiceImpl")
	private FactoryuserService factory;
	
	@Resource(name="insectsinvestuserServiceImpl")
	private InsectsinvestuserService insectsinvestuser;
	
	@RequestMapping(value = "/check_username")
	public @ResponseBody boolean checkUserName(String username ) {
		System.out.println(username);
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		if(expert.exists(username))
		{
			return false;
		}
		if(farmer.exists(username))
		{
			return false;
		}
		if(factory.exists(username))
		{
			return false;
		}
		if(insectsinvestuser.exists(username))
		{
			return false;
		}
		return true;
	}

//	/**
//	 * 进入新增用户界面
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("/addEntrance")
//	public String addEntrance(){
//		return "/common/register/expert/addexp";
//	}

	@RequestMapping("/addexp")
	@ResponseBody
	public Json addexp(Expert newsu){
		Json json = new Json();
		try {
			expert.save(newsu);
			json.setSuccess(true);
			json.setMsg("注册成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("注册失败");
		}
		return json;
	}

	
	/**
	 * @param newsu
	 * @return
	 */
	@RequestMapping("/addinv")
	@ResponseBody
	public Json addinv(Insectsinvestuser newsu){
		Json json = new Json();
		try {
			insectsinvestuser.save(newsu);
			json.setSuccess(true);
			json.setMsg("注册成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("注册失败");
		}
		return json;
	}
	
	/**
	 * @param newsu
	 * @return
	 */
	@RequestMapping("/addfar")
	@ResponseBody
	public Json addfar(FarmerUser newsu){
		Json json = new Json();
		try {
			farmer.save(newsu);
			json.setSuccess(true);
			json.setMsg("注册成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("注册失败");
		}
		return json;
	}
	/**
	 * @param newsu
	 * @return
	 */
	@RequestMapping("/addfac")
	@ResponseBody
	public Json addfac(Factoryuser newsu){
		Json json = new Json();
		try {
			factory.save(newsu);
			json.setSuccess(true);
			json.setMsg("注册成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("注册失败");
		}
		return json;
	}
	
}
