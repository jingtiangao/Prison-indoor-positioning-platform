package com.grain.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.TCatalogIcon;
import com.grain.service.TCatalogIconService;

@Controller
@RequestMapping("/admin/catalogIcon")
public class CatalogIconController {
	
	@Resource(name = "tCatalogIconServiceImpl")
	TCatalogIconService catalogIconService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "/admin/catalogIcon/list";
	}
	
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public Page<TCatalogIcon> dataGrid(Pageable pageable,TCatalogIcon queryParm){
		return catalogIconService.findPage(pageable, queryParm);
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/addCatalogIconEntrance")
	public String addCatalogIconEntrance(ModelMap model) {
		return "/admin/catalogIcon/add";
	}
	
	/**
	 * 添加保存
	 */
	@RequestMapping(value = "/addCatalogIcon")
	@ResponseBody
	public Json addCatalogIcon(TCatalogIcon tCatalogIcon) {
		Json j= new Json();
		try{
			catalogIconService.save(tCatalogIcon);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editCatalogIconEntrance")
	public String editCatalogIconEntrance(Long id, ModelMap model) {
		TCatalogIcon catalogIcon = catalogIconService.find(id);
		model.addAttribute("catalogIconId", id);
		model.addAttribute("catalogIcon", catalogIcon);
		return "/admin/catalogIcon/edit";
	}
	
	/**
	 * 编辑保存
	 */
	@RequestMapping(value = "/editCatalogIcon")
	@ResponseBody
	public Json editCatalogIcon(TCatalogIcon tCatalogIcon) {
		Json j= new Json();
		try{
			catalogIconService.update(tCatalogIcon);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Json delete(Long id) {
		Json j=new Json();
		try{
			catalogIconService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * 图标名 异步加载
	 */
	@RequestMapping(value = "/selectList")
	@ResponseBody
	public Map<String,Object> selectList(Pageable pageable,TCatalogIcon queryParm,
			@RequestParam(value="q") String term, @RequestParam(value="page") int pg,HttpSession session) throws Exception {
		pageable.setPage(pg);
		pageable.setRows(1000);
		Map<String,Object> r = new HashMap<String,Object>();
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		List<TCatalogIcon> result = new ArrayList<TCatalogIcon>();
		try{
		//Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		//if (p == null) throw new Exception("session is losed!");
		Page<TCatalogIcon> page = catalogIconService.findPage(pageable, queryParm);
		result = page.getRows();
		int size = result.size();
		for (int i = size-1; i >= 0; i--) {
			TCatalogIcon c = result.get(i);
			if ((!c.getName().contains(term)) || c == null)
				result.remove(i);
		}
		size = result.size();
		if(pg==0 || term!=""){
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("id", "0");
			m.put("text", "无");
			m.put("name", "");
			l.add(m);
		}
		for (int i = 0; i < size; i++) {
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("id", result.get(i).getId());
			m.put("text", result.get(i).getName());
			m.put("name", result.get(i).getCssname());
			l.add(m);
		}
		
			r.put("success", true);
		}catch (Exception e) {
			r.put("success", false);
		}
		r.put("rows", l);
		r.put("total_count", result.size());
		return r;
	}
	
}
