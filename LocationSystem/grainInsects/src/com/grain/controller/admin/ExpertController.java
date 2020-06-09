package com.grain.controller.admin;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.controller.depoter.BaseController;
import com.grain.entity.user.Expert;
import com.grain.service.user.ExpertService;

@Controller
@RequestMapping("/admin/expert")
public class ExpertController extends BaseController {
	@Resource(name="expertServiceImpl")
	private ExpertService expert;

	/**
	 * 进入用户列表页面，将角色信息添加到model中，供下拉框选择
	 * @param model
	 * @return 页面名称
	 */
	@RequestMapping("/entrance")
	public String Entrance(){
		return "admin/expert/list";
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
	public Page<Expert> getDataGrid(Pageable pageable, Expert queryParm){
		Page<Expert> map=expert.findPage(pageable,queryParm); 
		return map;
	}
	
	@RequestMapping(value = "/check_username")
	public @ResponseBody boolean checkUserName(String username ) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		return !expert.exists(username);
	}
	
	/**
	 * 进入新增用户界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addEntrance")
	public String addEntrance(){
		return "admin/expert/add";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Expert newsu){
		Json json = new Json();
		try {
			expert.save(newsu);
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
	public String editEntrance(String username,Model model) {
		model.addAttribute("expert",expert.find(username));
		return "admin/expert/edit";
	}

	@RequestMapping(value="/edit")
	@ResponseBody
	public Json edit(Expert su) {
		Json json = new Json();
		try {
			expert.update(su);
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(true);
			json.setMsg("修改成功");
		}
		return json;
	}
	
	/**
	 * 删除专家用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	@ResponseBody
	public Json delete(String username){
		Json json = new Json();
		Expert su = expert.findByUsername(username);
		try {
			expert.delete(su);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("删除失败");
		}
		return json;
	}
}