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
import com.grain.entity.TInsectsIdentify;
import com.grain.service.TInsectsIdentifyService;
import com.grain.util.SpringUtils;


@Controller
@RequestMapping("/investor/register/insectsIdentify")
public class TInsectsIdentifyController{
	
	@Resource(name="tinsectsIdentifyServiceImpl")
	private TInsectsIdentifyService insectsIdentifyService; 
	
	@RequestMapping(value="/list")
	public String getList(){
		return "/investor/register/insectsIdentify/insectsIdentifyList";
	}
	
	/**
	 * 得到昆虫鉴定信息列表
	 */
	@RequestMapping(value="getInsectsIdentifyList")
	@ResponseBody
	public Page<TInsectsIdentify> getInsectsIdentifyList(Pageable pageable,TInsectsIdentify queryParm,
			HttpSession session) throws Exception{
		Page<TInsectsIdentify> map = insectsIdentifyService.findPage(pageable, queryParm);
		return map;
	}
	
	@RequestMapping(value="addInsectsIdentifyEntrance")
	public String addInsectsIdentifyEntrance(){
		return "/investor/register/insectsIdentify/insectsIdentifyAdd";
	}
	
	@RequestMapping(value="/insectsIdentifyAdd")
	@ResponseBody
	public Json insectsIdentifyAdd(TInsectsIdentify insectsIdentify){
		Json json = new Json();
		
		try {
			insectsIdentifyService.save(insectsIdentify);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("添加成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="/editInsectsIdentifyEntrance")
	public String editInsectsIdentifyEntrance(String sm, Model model){
		model.addAttribute("sm", sm);
		TInsectsIdentify insectsIdentify = insectsIdentifyService.findBySM(sm);
		model.addAttribute("insectsIdentify", insectsIdentify);
		return "/investor/register/insectsIdentify/insectsIdentifyEdit";
	}
	
	@RequestMapping(value="/insectsIdentifyEdit")
	@ResponseBody
	public Json collectDistributionEdit(TInsectsIdentify insectsIdentify){
		Json json = new Json();
		
		try {
			insectsIdentifyService.update(insectsIdentify);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("修改成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="insectsIdentifyDelete")
	@ResponseBody
	public Json insectsIdentifyDelete(String sm){
		Json json = new Json();
		try {
			insectsIdentifyService.delete(sm);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 得到所有昆虫的信息，并在地图上显示出来
	 * @return
	 */
	@RequestMapping(value="getFootprints")
	@ResponseBody
	public List<TInsectsIdentify> getFootprints(){
		List<TInsectsIdentify> list = null;
		try {
			list = insectsIdentifyService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="getSpecFootprint")
	@ResponseBody
	public TInsectsIdentify getSpecFootprint(String sm){
		TInsectsIdentify insectsIdentify = null;
		if(sm != null && StringUtils.isNotEmpty(sm)){
			insectsIdentify = insectsIdentifyService.findBySM(sm);
		}
		return insectsIdentify;
	}
}
