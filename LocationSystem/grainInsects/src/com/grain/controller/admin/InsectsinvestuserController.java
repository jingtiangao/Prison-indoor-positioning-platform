package com.grain.controller.admin;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.controller.depoter.BaseController;
import com.grain.entity.user.Insectsinvestuser;
import com.grain.service.user.InsectsinvestuserService;

@Controller
@RequestMapping("/admin/investor")
public class InsectsinvestuserController extends BaseController {
	
	@Resource(name="insectsinvestuserServiceImpl")
	private InsectsinvestuserService insectsinvestuser;

	/**
	 * 进入用户列表页面，将角色信息添加到model中，供下拉框选择
	 * @param model
	 * @return 页面名称
	 */
	@RequestMapping("/entrance")
	public String Entrance(){
		return "admin/investor/list";
	}

	/**
	 * 获取用户列表
	 * 
	 * @param datagrid
	 *            插件发送的分页信息的封装
	 * @param name queryParm 
	 *            查询条件的封转
	 *            
	 * @return 由@ResponseBody转化的json
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<Insectsinvestuser> getDataGrid(Pageable pageable, Insectsinvestuser queryParm){
		Page<Insectsinvestuser> map=insectsinvestuser.findPage(pageable,queryParm);  
		return map;
	}
	
	@RequestMapping(value = "/check_username")
	public @ResponseBody boolean checkUserName(String username ) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		return !insectsinvestuser.exists(username);
	}
	
	/**
	 * 进入新增用户界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addEntrance")
	public String addEntrance(){
		return "admin/investor/add";
	}
	/**
	 * @param newsu
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Insectsinvestuser newsu){
		Json json = new Json();
		try {
			insectsinvestuser.save(newsu);
			json.setSuccess(true);
			json.setMsg("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("添加失败");
		}
		return json;
	}
	
	@RequestMapping(value="/editEntrance")
	public String Entrance(String username, Model model) {
		model.addAttribute("investor",insectsinvestuser.find(username));
		return "admin/investor/edit";
	}

	@RequestMapping(value="/edit")
	@ResponseBody
	public Json doEdit(Insectsinvestuser su) {
		Json json = new Json();
		try {
			insectsinvestuser.update(su);
			json.setSuccess(true);
			json.setMsg("修改成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改失败");
		}
		return json;
	}
	
	/**
	 * 删除虫调用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	@ResponseBody
	public Json delete(@RequestParam(value="username") String username){
		Json json = new Json();
		try{
			Insectsinvestuser su = insectsinvestuser.findByUsername(username);
			insectsinvestuser.delete(su);
			
			json.setSuccess(true);
			json.setMsg("删除成功");
		}catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("删除失败");
		}
		return json;
	}
	
}
