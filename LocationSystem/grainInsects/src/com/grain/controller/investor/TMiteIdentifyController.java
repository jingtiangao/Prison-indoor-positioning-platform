package com.grain.controller.investor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.TMiteidentify;
import com.grain.service.TMiteIdentifyService;
import com.grain.util.SpringUtils;


@Controller
@RequestMapping("/investor/register/miteIdentify")
public class TMiteIdentifyController{
	
	@Resource(name="tmiteIdentifyServiceImpl")
	private TMiteIdentifyService miteIdentifyService; 
	
	@RequestMapping(value="/list")
	public String getList(){
		return "/investor/register/miteIdentify/miteIdentifyList";
	}
	
	/**
	 * 得到螨鉴定信息列表
	 */
	@RequestMapping(value="getMiteIdentifyList")
	@ResponseBody
	public Page<TMiteidentify> getMiteIdentifyList(Pageable pageable,TMiteidentify queryParm,
			HttpSession session) throws Exception{
		Page<TMiteidentify> map = miteIdentifyService.findPage(pageable, queryParm);
		
		return map;
	}
	
	@RequestMapping(value="addMiteIdentifyEntrance")
	public String addMiteIdentifyEntrance(){
		return "/investor/register/miteIdentify/miteIdentifyAdd";
	}
	
	@RequestMapping(value="/miteIdentifyAdd")
	@ResponseBody
	public Json miteIdentifyAdd(TMiteidentify miteIdentify){
		Json json = new Json();
		
		try {
			miteIdentifyService.save(miteIdentify);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("添加成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="/editMiteIdentifyEntrance")
	public String editMiteIdentifyEntrance(String sm, Model model){
		model.addAttribute("sm", sm);
		TMiteidentify miteIdentify = miteIdentifyService.findBySM(sm);
		model.addAttribute("miteIdentify", miteIdentify);
		return "/investor/register/miteIdentify/miteIdentifyEdit";
	}
	
	@RequestMapping(value="/miteIdentifyEdit")
	@ResponseBody
	public Json miteIdentifyEdit(TMiteidentify miteIdentify){
		Json json = new Json();
		
		try {
			miteIdentifyService.update(miteIdentify);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("修改成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="miteIdentifyDelete")
	@ResponseBody
	public Json miteIdentifyDelete(String sm){
		Json json = new Json();
		try {
			miteIdentifyService.delete(sm);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 得到所有螨虫的信息，并在地图上显示出来
	 * @return
	 */
	@RequestMapping(value="getFootprints")
	@ResponseBody
	public List<TMiteidentify> getFootprints(){
		List<TMiteidentify> list = null;
		try {
			list = miteIdentifyService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="getSpecFootprint")
	@ResponseBody
	public TMiteidentify getSpecFootprint(String sm){
		TMiteidentify miteidentify = null;
		if(sm != null && StringUtils.isNotEmpty(sm)){
			miteidentify = miteIdentifyService.findBySM(sm);
		}
		return miteidentify;
	}
}
