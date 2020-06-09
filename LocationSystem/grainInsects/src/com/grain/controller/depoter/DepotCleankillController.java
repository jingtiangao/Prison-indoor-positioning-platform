package com.grain.controller.depoter;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.DepotCleankill;
import com.grain.entity.Grainbin;
import com.grain.entity.TBinCleankill;
import com.grain.entity.user.DepotUser;
import com.grain.service.DepotCleankillService;
import com.grain.service.GrainService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.TBinCleankillService;
import com.grain.service.user.DepotUserService;

/*
 * 	空仓杀虫，包括：总表和分仓调查表
 *  
 */
@Controller
@RequestMapping("depoter/depotcleankill")
public class DepotCleankillController {
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	//空仓杀虫调查总表
	@Resource(name = "depotCleankillServiceImpl")
	DepotCleankillService depotCleankillService;
	
	//分仓空仓杀虫调查表
	@Resource(name = "tBinCleankillServiceImpl")
	TBinCleankillService tBinCleankillService;
	
	@Resource(name = "grainServiceImpl")
	GrainService grainService;
	
	@RequestMapping("/depot_cleankill_list")
	public String depotInvestList(){
		
		return "depoter/dataReport/depotCleankill/depot_cleankill_list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DepotCleankill> getDataGrid(Pageable pageable,DepotCleankill queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new DepotCleankill();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			queryParm.setGraindepot(u.getGraindepot());
		}
		return depotCleankillService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addDepotCleankillEntrance")
	public String addDepotCleankillEntrance(HttpSession session,ModelMap model) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());        //粮库编号
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());        //粮库名称
					
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotCleankill/addDepotCleankill";
	}
	
	@RequestMapping("/addDepotCleankill")
	@ResponseBody
	public Json addDepotCleankill(DepotCleankill depotCleankill,String lkbm) {
		Json j = new Json();
		//DepotCleankill，空仓杀虫数据添加
		try{
			depotCleankill.setGraindepot(depotSrv.find(lkbm));
			depotCleankillService.save(depotCleankill);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
		
	}
	
	@RequestMapping("/editDepotCleankillEntrance")
	public String editDepotCleankillEntrance(HttpSession session,ModelMap model,Long id,String lkbm) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());        //粮库编号
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());        //粮库名称
			model.addAttribute("depotCleankill", depotCleankillService.find(id));    //空仓杀虫表实体		
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotCleankill/editDepotCleankill";
	}
	
	//modified by zjl at 2016/6/4
	@RequestMapping("/editDepotCleankill")
	@ResponseBody
	public Json editDepotCleankill(DepotCleankill depotCleankill, String lkbm) {
		Json j = new Json();
		try {
			depotCleankill.setGraindepot(depotSrv.find(lkbm));
			depotCleankillService.update(depotCleankill);
			j.setSuccess(true);
			j.setMsg("更新成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	//modified by zjl at 2016/6/4
	@RequestMapping("/deleteDeportCleankillReportRec")
	@ResponseBody
	public Json deleteDeportCleankillReportRec(Long id) {
		Json j = new Json();		
		try{
			depotCleankillService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/bin_cleankill_list")
	public String binCleankillList() {
		return "depoter/dataReport/binCleankill/bin_cleankill_list";
	}
	
	@RequestMapping("/grainBins")
	@ResponseBody
	public String grainBins(HttpSession session) {
		String json = "";
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			Set<Grainbin> grainbins = u.getGrainbins();
			for(Grainbin grainbin : grainbins) {
				String lcbm = grainbin.getLcbm();
				json = json + ",{\"lcbm\":\"" + lcbm + "\"}";
			}
		}
		if(json.length() > 0) {
			json = "[" + json.substring(1) + "]";
		} else {
			json = "[]";
		}
		return json;
	}
	
	@RequestMapping("getBinCleankillList")
	@ResponseBody
	public Page<TBinCleankill> getBinCleankillList(Pageable pageable, TBinCleankill queryParm, 
			HttpSession session, String Annual, String lcbm)throws Exception
	{
		if (queryParm==null )
			queryParm = new TBinCleankill();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			if(Annual != null)
				queryParm.setAnnual(Integer.valueOf(Annual));
			if(lcbm != null)
				queryParm.setTGrainbin(binService.find(lcbm));
			if(queryParm.getTGrainbin() == null) {
				if(!u.getManager()) {
					return null;
				}
			}
		}
		return tBinCleankillService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("addBinCleankillEntrance")
	public String addBinCleankillEntrance(HttpSession session,ModelMap model, String lcbm, Integer annual) 
			throws Exception {
		Principal p = (Principal) session.getAttribute(CommonAttributes.Principal);
		if(p != null) {
			DepotUser u = depotuserSrv.find(p.getUsername());
			Grainbin grainbin = binService.find(lcbm);
			//粮库基本情况
			model.addAttribute("lkmc", u.getGraindepot().getLkmc());
			model.addAttribute("lkbm", u.getGraindepot().getLkbm());
			model.addAttribute("annual", annual);
			//粮仓基本情况
			model.addAttribute("lcbm", lcbm);
			model.addAttribute("typebin", grainbin.getTypebin());
			model.addAttribute("capacity", grainbin.getCapacity());
			model.addAttribute("structureofbody", grainbin.getStructureofbody());
			model.addAttribute("designgrainheapheight", grainbin.getDesigngrainheapheight());
			model.addAttribute("longth", grainbin.getLongth());
			model.addAttribute("width", grainbin.getWidth());
			model.addAttribute("height", grainbin.getHeight());
			model.addAttribute("circulatedevice", grainbin.getCirculatedevice());
			model.addAttribute("fanway", grainbin.getFanway());
			//改善仓储条件进行害虫的预防数据？
			
			//储粮信息
			model.addAttribute("grain", grainService.findLast(lcbm));
		
		} else 
			throw new Exception("session is losed!");
		
		return "depoter/dataReport/binCleankill/addBinCleankill";
		
	}
	
	@RequestMapping("/addBinCleankill")
	@ResponseBody
	public Json addBinCleankill(@RequestParam("lcbm") String lcbm, @RequestParam("annual") String annual, TBinCleankill binCleankill) {
		Json j = new Json();
		//DepotCleankill，空仓杀虫数据添加
		try{
			binCleankill.setTGrainbin(binService.find(lcbm));
			binCleankill.setAnnual(Integer.valueOf(annual));
			tBinCleankillService.save(binCleankill);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
		
	}
	
	@RequestMapping("editBinCleankillEntrance")
	public String editBinCleankillEntrance(HttpSession session, ModelMap model, Long id, String lcbm) 
			throws Exception {
		Principal p = (Principal) session.getAttribute(CommonAttributes.Principal);
		if(p != null) {
			DepotUser u = depotuserSrv.find(p.getUsername());
			Grainbin grainbin = binService.find(lcbm);
			TBinCleankill tBinCleankill = tBinCleankillService.find(id);
			//粮库基本情况
			model.addAttribute("lkmc", u.getGraindepot().getLkmc());
			model.addAttribute("lkbm", u.getGraindepot().getLkbm());
			model.addAttribute("annual", tBinCleankill.getAnnual());
			//粮仓基本情况
			model.addAttribute("lcbm", lcbm);
			model.addAttribute("typebin", grainbin.getTypebin());
			model.addAttribute("capacity", grainbin.getCapacity());
			model.addAttribute("structureofbody", grainbin.getStructureofbody());
			model.addAttribute("designgrainheapheight", grainbin.getDesigngrainheapheight());
			model.addAttribute("longth", grainbin.getLongth());
			model.addAttribute("width", grainbin.getWidth());
			model.addAttribute("height", grainbin.getHeight());
			model.addAttribute("circulatedevice", grainbin.getCirculatedevice());
			model.addAttribute("fanway", grainbin.getFanway());
			//改善仓储条件进行害虫的预防数据？
			
			//储粮信息
			model.addAttribute("grain", grainService.findLast(lcbm));
			//调查表信息
			model.addAttribute("tBinCleankill", tBinCleankill);
		
		} else 
			throw new Exception("session is losed!");
		return "depoter/dataReport/binCleankill/editBinCleankill";
		
	}
	
	@RequestMapping("/editBinCleankill")
	@ResponseBody
	public Json editBinCleankill(TBinCleankill binCleankill, String lcbm) {
		Json j = new Json();
		//DepotCleankill，空仓杀虫数据添加
		try{
			binCleankill.setTGrainbin(binService.find(lcbm));
			tBinCleankillService.update(binCleankill);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
		
	}
	
	@RequestMapping("/deleteBinCleankillReportRec")
	@ResponseBody
	public Json deleteBinCleankillReportRec(Long id){
		Json j = new Json();
		try{
			tBinCleankillService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	

}
