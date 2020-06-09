package com.grain.controller.investor;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.TInsectscollectdistribution;
import com.grain.entity.TInsectsdistribution;
import com.grain.service.TInsectsCollectDistributionService;
import com.grain.util.SpringUtils;


@Controller
@RequestMapping("/investor/register/collectDistribution")
public class TInsectsCollectDistributionController{
	
	@Resource(name="tinsectsCollectDistributionServiceImpl")
	private TInsectsCollectDistributionService insectsCollectDistributionService; 
	
	@RequestMapping(value="/list")
	public String getList(){
		return "/investor/register/collectDistribution/collectDistributionList";
	}
	
	/**
	 * 得到昆虫分布信息列表
	 */
	@RequestMapping(value="getDistributionList")
	@ResponseBody
	public Page<TInsectscollectdistribution> getDistributionList(Pageable pageable,TInsectscollectdistribution queryParm,
			HttpSession session) throws Exception{
		Page<TInsectscollectdistribution> map = insectsCollectDistributionService.findPage(pageable, queryParm);
		return map;
	}
	
	@RequestMapping(value="addCollectDistributionEntrance")
	public String addCollectDistributionEntrance(){
		return "/investor/register/collectDistribution/collectDistributionAdd";
	}
	
	@RequestMapping(value="/distributionRecordAdd")
	@ResponseBody
	public Json distributionRecordAdd(TInsectscollectdistribution insectsCollectDistribution, HttpServletRequest req, Model model){
		Json json = new Json();
		
		// 过滤无用昆虫分布信息
		for(Iterator<TInsectsdistribution> iterator = insectsCollectDistribution.getTInsectsdistributions().iterator();iterator.hasNext();){
			TInsectsdistribution insectsDistribution = iterator.next();
			if(insectsDistribution == null || isEmpty(insectsDistribution)){
				iterator.remove();
				continue;
			}
			//维护父子关系
			insectsDistribution.setTInsectscollectdistribution(insectsCollectDistribution);
		}
		
		try {
			insectsCollectDistributionService.save(insectsCollectDistribution);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("添加成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="/collectDistributionEditEntrance")
	public String collectDistributionEditEntrance(String smInsects, Model model){
		model.addAttribute("smInsects", smInsects);
		TInsectscollectdistribution insectsCollectDistribution = insectsCollectDistributionService.findBySMInsects(smInsects);
		model.addAttribute("insectsCollectDistribution", insectsCollectDistribution);
		model.addAttribute("recordTotal", insectsCollectDistribution.getTInsectsdistributions().size());
		return "/investor/register/collectDistribution/collectDistributionEdit";
	}
	
	
	@RequestMapping(value="/collectDistributionEdit")
	@ResponseBody
	public Json collectDistributionEdit(TInsectscollectdistribution insectsCollectDistribution, String smInsects, Model model){
		Json json = new Json();
		
		// 过滤昆虫采集分布信息
		for(Iterator<TInsectsdistribution> iterator = insectsCollectDistribution.getTInsectsdistributions().iterator();iterator.hasNext();){
			TInsectsdistribution insectsDistribution = iterator.next();
			if(insectsDistribution == null || isEmpty(insectsDistribution)){
				iterator.remove();
				continue;
			}
			insectsDistribution.setTInsectscollectdistribution(insectsCollectDistribution);
		}
		
		try {
			insectsCollectDistributionService.update(insectsCollectDistribution);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("修改成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="collectDistributionDelete")
	@ResponseBody
	public Json collectDistributionDelete(String smInsects){
		Json json = new Json();
		try {
			insectsCollectDistributionService.delete(smInsects);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 判断s是否为空或者是否为空白字符串
	 * @param s
	 * @return
	 */
	private boolean isValidate(String s){
		boolean flag = true;
		
		if(s==null || s.trim().equals(""))
			flag = false;
		
		return flag;
	}
	
	/**
	 * 判断该TInsectsdistribution是否每个属性都为空
	 * @return
	 */
	public boolean isEmpty(TInsectsdistribution insectsDistribution){
		if(isValidate(insectsDistribution.getCompany()) || insectsDistribution.getNum() != null
				|| isValidate(insectsDistribution.getCollectyear()) 
				|| isValidate(insectsDistribution.getCollectmonth()))
			return false;
		return true;
		
	}
}
