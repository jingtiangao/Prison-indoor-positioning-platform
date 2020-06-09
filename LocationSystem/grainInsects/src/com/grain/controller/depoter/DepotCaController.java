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
import com.grain.entity.DepotCa;
import com.grain.entity.Grain;
import com.grain.entity.Grainbin;
import com.grain.entity.TBinCa;
import com.grain.entity.user.DepotUser;
import com.grain.service.DepotCaService;
import com.grain.service.GrainService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.TBinCaService;
import com.grain.service.user.DepotUserService;

/*
 * 	氮气，包括：总表和分仓调查表
 *  
 */

@Controller
@RequestMapping("depoter/depotca")
public class DepotCaController {

	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name="depotCaServiceImpl")
	DepotCaService depotCaService;	//氮气气调总表
	
	@Resource(name="tbinCaServiceImpl")
	TBinCaService binCaService; // 氮气分仓调查表
	
	@Resource(name="grainServiceImpl")
	GrainService grainService;
	
	@RequestMapping("/depot_ca_list")
	public String depotInvestCaList(){
		
		return "depoter/dataReport/depotCa/depot_ca_list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DepotCa>getDataGrid(Pageable pageable,DepotCa queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new DepotCa();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			queryParm.setGraindepot(u.getGraindepot());
		}
		return depotCaService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addDepotCaEntrance")
	public String addDepotCaEntrance(HttpSession session,ModelMap model) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());//粮库编码
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());//粮库名称
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotCa/addDepotCa";
	}
	
	@RequestMapping("/addDepotCa")
	@ResponseBody
	public Json addDepotCa(DepotCa depotCa,String lkbm){
		Json j = new Json();
		
		try{
			depotCa.setGraindepot(depotSrv.find(lkbm));
			
			depotCaService.save(depotCa);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editDepotCaEntrance")
	public String editDepotCaEntrance(HttpSession session,ModelMap model,Long id, String lkbm) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());//粮库编码
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());//粮库名称
			model.addAttribute("depotCa", depotCaService.find(id));//氮气气调实体
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotCa/editDepotCa";
	}
	
	@RequestMapping("/editDepotCa")
	@ResponseBody
	public Json editDepotInvest(DepotCa depotCa, String lkbm){
		Json j = new Json();
		
		try{
			depotCa.setGraindepot(depotSrv.find(lkbm));
			depotCaService.update(depotCa);
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
			depotCaService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/bin_ca_list")
	public String binInvestList(){
		return "depoter/dataReport/binCa/bin_ca_list";
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
	public Page<TBinCa> getGrainBin(Pageable pageable,TBinCa queryParm, 
			HttpSession session, String Annual, String lcbm) {
		if (queryParm==null)
			queryParm = new TBinCa();
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
		return binCaService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addBinCaEntrance")
	public String addBinCaEntrance(HttpSession session,ModelMap model, String lcbm, Integer annual) 
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
		return "depoter/dataReport/binCa/addBinCa";
	}
	
	@RequestMapping("/addBinCa")
	@ResponseBody
	public Json addBinCa(TBinCa binCa,String lcbm, Integer annual){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		binCa.setAnnual(annual);
		
		try{
			binCa.setTGrainbin(binService.find(lcbm));
			binCaService.save(binCa);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editBinCaEntrance")
	public String editBinCaEntrance(HttpSession session,ModelMap model,Long id) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			TBinCa binCa = binCaService.find(id);
			Grainbin grainbin = binCa.getTGrainbin();
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
			model.addAttribute("binCa", binCa);
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/binCa/editBinCa";
	}
	
	@RequestMapping("/editBinCa")
	@ResponseBody
	public Json editBinCa(TBinCa binCa){
		Json j = new Json();
		Long id = binCa.getId();
		TBinCa binCa2 = binCaService.find(id);
		Grainbin grainbin = binCa2.getTGrainbin();
		String lcbm = grainbin.getLcbm();
		binCa.setAnnual(binCa2.getAnnual());
		try{
			binCa.setTGrainbin(binService.find(lcbm));
			binCaService.update(binCa);
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
			binCaService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
