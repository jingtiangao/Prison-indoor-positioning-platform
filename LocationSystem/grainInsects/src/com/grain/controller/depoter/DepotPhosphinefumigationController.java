package com.grain.controller.depoter;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.DepotPhosphinefumigation;
import com.grain.entity.Grain;
import com.grain.entity.Grainbin;
import com.grain.entity.TBinPhosphinefumigation;
import com.grain.entity.user.DepotUser;
import com.grain.service.DepotPhosphinefumigationService;
import com.grain.service.GrainService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.TBinPhosphinefumigationService;
import com.grain.service.user.DepotUserService;

/*
 * 	磷化氢熏蒸，包括：总表和分仓调查表
 *  
 */
@Controller
@RequestMapping("depoter/depotphosphinefumigation")
public class DepotPhosphinefumigationController {
	
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name="depotPhosphinefumigationServiceImpl")
	DepotPhosphinefumigationService depotPhosphinefumigationService;// 磷化氢熏蒸总表
	
	@Resource(name="tbinPhosphinefumigationServiceImpl")
	TBinPhosphinefumigationService binPhosphinefumigationService; // 磷化氢熏蒸分仓调查表
	
	@Resource(name = "grainServiceImpl")
	GrainService grainService;
	
	@RequestMapping("/depot_phosphinefumigation_list")
	public String depotPhosphinefumigationList(){
		
		return "depoter/dataReport/depotPH3Invest/depot_PH3_list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DepotPhosphinefumigation>getDataGrid(Pageable pageable,DepotPhosphinefumigation queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new DepotPhosphinefumigation();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			queryParm.setGraindepot(u.getGraindepot());
		}
		return depotPhosphinefumigationService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addDepotPhosphinefumigationEntrance")
	public String addDepotPhosphinefumigationEntrance(HttpSession session,ModelMap model) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());
			model.addAttribute("postcode",u.getGraindepot().getPostcode());
			model.addAttribute("lkdz",u.getGraindepot().getLkdz());
			model.addAttribute("longtitude",u.getGraindepot().getLongtitude());		//经度
			model.addAttribute("altitude",u.getGraindepot().getAltitude());			//海拔
			model.addAttribute("latitude",u.getGraindepot().getLatitude());			//纬度			
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotPH3Invest/addDepotPH3";
	}
	
	@RequestMapping("/addDepotPhosphinefumigation")
	@ResponseBody
	public Json addDepotPhosphinefumigation(DepotPhosphinefumigation depotPhosphinefumigation,String lkbm) {
		Json j = new Json();
		
		try{
			depotPhosphinefumigation.setGraindepot(depotSrv.find(lkbm));
			depotPhosphinefumigationService.save(depotPhosphinefumigation);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
		
	}
	
	@RequestMapping("/editDepotPhosphinefumigationEntrance")
	public String editDepotInvestEntrance(HttpSession session,ModelMap model,Long id, String lkbm) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());
			model.addAttribute("depotPhosphinefumigation", depotPhosphinefumigationService.find(id));
//			model.addAttribute("postcode",u.getGraindepot().getPostcode());
//			model.addAttribute("lkdz",u.getGraindepot().getLkdz());
//			model.addAttribute("longtitude",u.getGraindepot().getLongtitude());		//经度
//			model.addAttribute("altitude",u.getGraindepot().getAltitude());			//海拔
//			model.addAttribute("latitude",u.getGraindepot().getLatitude());			//纬度
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotPH3Invest/editDepotPH3";
	}
	
	@RequestMapping("/editDepotPhosphinefumigation")
	@ResponseBody
	public Json editDepotPhosphinefumigation(DepotPhosphinefumigation depotPhosphinefumigation,String lkbm) {
		Json j = new Json();
		
		try{
			depotPhosphinefumigation.setGraindepot(depotSrv.find(lkbm));
			depotPhosphinefumigationService.update(depotPhosphinefumigation);
			j.setSuccess(true);
			j.setMsg("更新成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/deleteReportRec")
	@ResponseBody
	public Json deleteReportRec(Long id){
		Json j = new Json();
		
		try{
			depotPhosphinefumigationService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	
	@RequestMapping("/bin_phosphinefumigation_list")
	public String binInvestList(){
		return "depoter/dataReport/binPH3Invest/bin_PH3_list";
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
	
	@RequestMapping("/getGrainBin")
	@ResponseBody
	public Page<TBinPhosphinefumigation> getGrainBin(Pageable pageable,TBinPhosphinefumigation queryParm, 
			HttpSession session, String Annual, String lcbm) {
		if (queryParm==null)
			queryParm = new TBinPhosphinefumigation();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			if(Annual != null) {
				queryParm.setAnnual(Integer.valueOf(Annual));
			}
			if(lcbm != null) {
				queryParm.setTGrainbin(binService.find(lcbm));
			}
			if(queryParm.getTGrainbin() == null) {
				if(!u.getManager()) {
					return null;
				}
			}
		}
		return binPhosphinefumigationService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addBinPhosphinefumigationEntrance")
	public String addBinPhosphinefumigationEntrance(HttpSession session,ModelMap model, String lcbm, Integer annual) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			Grainbin grainbin = binService.find(lcbm);
			//粮库基本信息
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());
			model.addAttribute("postcode",u.getGraindepot().getPostcode());
			model.addAttribute("lkdz",u.getGraindepot().getLkdz());
			model.addAttribute("longtitude",u.getGraindepot().getLongtitude());		//经度
			model.addAttribute("altitude",u.getGraindepot().getAltitude());			//海拔
			model.addAttribute("latitude",u.getGraindepot().getLatitude());			//纬度
			//粮仓基本信息
			model.addAttribute("lcbm", grainbin.getLcbm());
			model.addAttribute("typebin", grainbin.getTypebin());
			model.addAttribute("capacity", grainbin.getCapacity());
			model.addAttribute("structureofbody", grainbin.getStructureofbody());
			model.addAttribute("designgrainheapheight", grainbin.getDesigngrainheapheight());
			model.addAttribute("longth", grainbin.getLongth());
			model.addAttribute("width", grainbin.getWidth());
			model.addAttribute("height", grainbin.getHeight());
			model.addAttribute("annual", annual);
			//储粮信息
			Grain grain = grainService.findLast(lcbm);
			model.addAttribute("grain", grain);
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/binPH3Invest/addBinPH3";
	}
	
	@RequestMapping("/addBinPhosphinefumigation")
	@ResponseBody
	public Json addBinPhosphinefumigation(TBinPhosphinefumigation binPhosphinefumigation,String lcbm, Integer annual){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		binPhosphinefumigation.setAnnual(annual);
		
		try{
			binPhosphinefumigation.setTGrainbin(binService.find(lcbm));
			binPhosphinefumigationService.save(binPhosphinefumigation);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editBinPhosphinefumigationEntrance")
	public String editBinPhosphinefumigationEntrance(HttpSession session,ModelMap model,Long id) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			TBinPhosphinefumigation binPhosphinefumigation = binPhosphinefumigationService.find(id);
			Grainbin grainbin = binPhosphinefumigation.getTGrainbin();
			//粮库基本信息
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());
			model.addAttribute("postcode",u.getGraindepot().getPostcode());
			model.addAttribute("lkdz",u.getGraindepot().getLkdz());
			model.addAttribute("longtitude",u.getGraindepot().getLongtitude());		//经度
			model.addAttribute("altitude",u.getGraindepot().getAltitude());			//海拔
			model.addAttribute("latitude",u.getGraindepot().getLatitude());         //纬度
			//粮仓基本信息
			model.addAttribute("lcbm", grainbin.getLcbm());
			model.addAttribute("typebin", grainbin.getTypebin());
			model.addAttribute("capacity", grainbin.getCapacity());
			model.addAttribute("structureofbody", grainbin.getStructureofbody());
			model.addAttribute("designgrainheapheight", grainbin.getDesigngrainheapheight());
			model.addAttribute("longth", grainbin.getLongth());
			model.addAttribute("width", grainbin.getWidth());
			model.addAttribute("height", grainbin.getHeight());
			//储粮信息
			Grain grain = grainService.findLast(grainbin.getLcbm());
			model.addAttribute("grain", grain);
			//调查表基本信息
			model.addAttribute("binPhosphinefumigation", binPhosphinefumigation);
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/binPH3Invest/editBinPH3";
	}
	
	@RequestMapping("/editBinPhosphinefumigation")
	@ResponseBody
	public Json editBinPhosphinefumigation(TBinPhosphinefumigation binPhosphinefumigation){
		Json j = new Json();
		Long id = binPhosphinefumigation.getId();
		TBinPhosphinefumigation binPhosphinefumigation2 = binPhosphinefumigationService.find(id);
		Grainbin grainbin = binPhosphinefumigation2.getTGrainbin();
		String lcbm = grainbin.getLcbm();
		binPhosphinefumigation.setAnnual(binPhosphinefumigation.getAnnual());
		try{
			binPhosphinefumigation.setTGrainbin(binService.find(lcbm));
			binPhosphinefumigationService.update(binPhosphinefumigation);
			j.setSuccess(true);
			j.setMsg("更新成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/deleteBinReportRec")
	@ResponseBody
	public Json deleteBinReportRec(Long id){
		Json j = new Json();
		try{
			binPhosphinefumigationService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	

}
