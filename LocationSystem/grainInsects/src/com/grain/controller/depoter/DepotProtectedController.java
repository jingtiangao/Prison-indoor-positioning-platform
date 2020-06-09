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
import com.grain.entity.DepotProtected;
import com.grain.entity.Grain;
import com.grain.entity.Grainbin;
import com.grain.entity.TBinProtected;
import com.grain.entity.user.DepotUser;
import com.grain.service.DepotProtectedService;
import com.grain.service.GrainService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.TBinProtectedService;
import com.grain.service.user.DepotUserService;

/*
 * 	防护剂，包括：总表和分仓调查表
 *  
 */
@Controller
@RequestMapping("depoter/depotprotected")
public class DepotProtectedController {
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name="depotProtectedServiceImpl")
	DepotProtectedService depotProtectedService;	//防护剂总表
	
	@Resource(name="tbinProtectedServiceImpl")
	TBinProtectedService binProtectedService; // 防护剂分仓调查表
	
	@Resource(name = "grainServiceImpl")
	GrainService grainService;
	
	@RequestMapping("/depot_protected_list")
	public String depotProtectedList(){
		return "depoter/dataReport/depotProtected/depot_protected_list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DepotProtected> getDataGrid(Pageable pageable,DepotProtected queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new DepotProtected();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			queryParm.setGraindepot(u.getGraindepot());
		}
		return depotProtectedService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addDepotProtectedEntrance")
	public String addDepotProtectedEntrance(HttpSession session,ModelMap model) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());//粮库编码
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());//粮库名称
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotProtected/addDepotProtected";
	}
	
	@RequestMapping("/addDepotProtected")
	@ResponseBody
	public Json addDepotProtected(DepotProtected depotProtected,String lkbm){
		Json j = new Json();
		
		try{
			depotProtected.setGraindepot(depotSrv.find(lkbm));
			
			depotProtectedService.save(depotProtected);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editDepotProtectedEntrance")
	public String editDepotProtectedEntrance(HttpSession session,ModelMap model,Long id, String lkbm) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());//粮库编码
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());//粮库名称
			model.addAttribute("depotProtected", depotProtectedService.find(id));//防护剂实体
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotProtected/editDepotProtected";
	}
	
	@RequestMapping("/editDepotProtected")
	@ResponseBody
	public Json editDepotInvest(DepotProtected depotProtected, String lkbm){
		Json j = new Json();
		
		try{
			depotProtected.setGraindepot(depotSrv.find(lkbm));
			depotProtectedService.update(depotProtected);
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
			depotProtectedService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/bin_protected_list")
	public String binInvestList(){
		return "depoter/dataReport/binProtected/bin_protected_list";
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
	public Page<TBinProtected> getGrainBin(Pageable pageable,TBinProtected queryParm, 
			HttpSession session, String Annual, String lcbm) {
		if (queryParm==null)
			queryParm = new TBinProtected();
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
		return binProtectedService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addBinProtectedEntrance")
	public String addBinProtectedEntrance(HttpSession session,ModelMap model, String lcbm, Integer annual) 
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
		return "depoter/dataReport/binProtected/addBinProtected";
	}
	
	@RequestMapping("/addBinProtected")
	@ResponseBody
	public Json addBinProtected(TBinProtected binProtected,String lcbm, Integer annual){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		binProtected.setAnnual(annual);
		
		try{
			binProtected.setTGrainbin(binService.find(lcbm));
			binProtectedService.save(binProtected);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editBinProtectedEntrance")
	public String editBinProtectedEntrance(HttpSession session,ModelMap model,Long id) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			TBinProtected binProtected = binProtectedService.find(id);
			Grainbin grainbin = binProtected.getTGrainbin();
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
			model.addAttribute("binProtected", binProtected);
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/binProtected/editBinProtected";
	}
	
	@RequestMapping("/editBinProtected")
	@ResponseBody
	public Json editBinProtected(TBinProtected binProtected){
		Json j = new Json();
		Long id = binProtected.getId();
		TBinProtected binProtected2 = binProtectedService.find(id);
		Grainbin grainbin = binProtected2.getTGrainbin();
		String lcbm = grainbin.getLcbm();
		binProtected.setAnnual(binProtected2.getAnnual());
		try{
			binProtected.setTGrainbin(binService.find(lcbm));
			binProtectedService.update(binProtected);
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
			binProtectedService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
