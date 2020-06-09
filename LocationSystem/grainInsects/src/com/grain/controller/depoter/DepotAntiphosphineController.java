package com.grain.controller.depoter;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.grain.entity.DepotAntidrugkinds;
import com.grain.entity.DepotAntiphosphine;
import com.grain.entity.Grain;
import com.grain.entity.Grainbin;
import com.grain.entity.TBinAntidrugkinds;
import com.grain.entity.TBinAntiphosphine;
import com.grain.entity.user.DepotUser;
import com.grain.service.DepotAntidrugkindsService;
import com.grain.service.DepotAntiphosphineService;
import com.grain.service.GrainService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.TBinAntidrugkindsService;
import com.grain.service.TBinAntiphosphineService;
import com.grain.service.user.DepotUserService;

@Controller
@RequestMapping("depoter/depotantiphosphine")
public class DepotAntiphosphineController {
	
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@Resource(name="grainServiceImpl")
	GrainService grainService;
	
	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name="depotAntiphosphineServiceImpl")
	DepotAntiphosphineService depotAntiphosphineService;	//
	
	@Resource(name="depotAntidrugkindsServiceImpl")
	DepotAntidrugkindsService depotAntidrugkindsService;	//
	
	@Resource(name="tBinAntiphosphineServiceImpl")
	TBinAntiphosphineService tBinAntiphosphineService;	//
	
	@Resource(name="tBinAntidrugkindsServiceImpl")
	TBinAntidrugkindsService tBinAntidrugkindsService;	//
	
	@RequestMapping("/depot_antiphosphine_list")
	public String depotAntiPhosphineList(){
		return "depoter/dataReport/depotAntiphosphine/depot_antiphosphine_list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DepotAntiphosphine>getDataGrid(Pageable pageable,DepotAntiphosphine queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new DepotAntiphosphine();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			queryParm.setGraindepot(u.getGraindepot());
		}
		return depotAntiphosphineService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addDepotAntiphosphineEntrance")
	public String addDepotAntiphosphineEntrance(HttpSession session,ModelMap model) 
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
		return "depoter/dataReport/depotAntiphosphine/addDepotAntiphosphine";
	}
	
	@RequestMapping("/addDepotAntiphosphine")
	@ResponseBody
	public Json addDepotAntiphosphine(HttpServletRequest request,DepotAntiphosphine depotAntiphosphine,String lkbm){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		//DepotAntidrugkinds 
		for (Iterator <DepotAntidrugkinds> iterator = depotAntiphosphine.getDepotAntidrugkindses().iterator(); iterator.hasNext(); ){
			DepotAntidrugkinds depotAntidrugkinds = iterator.next();
			if (depotAntidrugkinds==null || depotAntidrugkinds.getKind().trim().length()<1){
				iterator.remove();
			}else{
				depotAntidrugkinds.setDepotAntiphosphine(depotAntiphosphine);
			}
		}
		
		try{
			depotAntiphosphine.setGraindepot(depotSrv.find(lkbm));
			Date nowDate = new Date();
			depotAntiphosphine.setReportdate(nowDate);
			depotAntiphosphine.setModifytime(nowDate);
			depotAntiphosphineService.save(depotAntiphosphine);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editDepotAntiphosphineEntrance")
	public String editDepotInvestEntrance(HttpSession session,ModelMap model,Long id, String lkbm) 
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
			model.addAttribute("depotAntiphosphine", depotAntiphosphineService.find(id));
			model.addAttribute("indexAntidrugkinds", depotAntiphosphineService.find(id).getDepotAntidrugkindses().size());
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotAntiphosphine/editDepotAntiphosphine";
	}
	
	@RequestMapping("/editDepotAntiphosphine")
	@ResponseBody
	public Json editDepotInvest(HttpServletRequest request,DepotAntiphosphine depotAntiphosphine, String lkbm){
		Json j = new Json();
		
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		//DepotAntidrugkinds 
		for (Iterator <DepotAntidrugkinds> iterator = depotAntiphosphine.getDepotAntidrugkindses().iterator(); iterator.hasNext(); ){
			DepotAntidrugkinds depotAntidrugkinds = iterator.next();
			if (depotAntidrugkinds==null || depotAntidrugkinds.getKind()==null || depotAntidrugkinds.getKind().trim().length()<1){
				iterator.remove();
			}else{
				depotAntidrugkinds.setDepotAntiphosphine(depotAntiphosphine);
			}
		}
		try{
			depotAntiphosphine.setGraindepot(depotSrv.find(lkbm));
			depotAntiphosphine.setModifytime(new Date());
			depotAntiphosphineService.update(depotAntiphosphine);
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
			depotAntiphosphineService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/bin_antiphosphine_list")
	public String binAntiPhosphineList(){
		return "depoter/dataReport/binAntiphosphine/bin_antiphosphine_list";
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
	public Page<TBinAntiphosphine> getGrainBin(Pageable pageable,TBinAntiphosphine queryParm, 
			HttpSession session, String Annual, String lcbm) {
		if (queryParm==null)
			queryParm = new TBinAntiphosphine();
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
		return tBinAntiphosphineService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addBinAntiphosphineEntrance")
	public String addBinAntiphosphineEntrance(HttpSession session,ModelMap model, String lcbm, Integer annual) 
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
			model.addAttribute("annual",annual);
			model.addAttribute("grainbin", grainbin);
			Grain grain = grainService.findLast(lcbm);
			model.addAttribute("grain", grain);
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/binAntiphosphine/addBinAntiphosphine";
	}
	
	@RequestMapping("/addBinAntiphosphine")
	@ResponseBody
	public Json addBinAntiphosphine(TBinAntiphosphine binAntiphosphine,String lcbm, Integer annual){
		Json j = new Json();
		binAntiphosphine.setAnnual(annual);
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		//BinAntidrugkinds 
		for (Iterator <TBinAntidrugkinds> iterator = binAntiphosphine.getTBinAntidrugkindses().iterator(); iterator.hasNext(); ){
			TBinAntidrugkinds binAntidrugkinds = iterator.next();
			if (binAntidrugkinds==null || binAntidrugkinds.getKind().trim().length()<1){
				iterator.remove();
			}else{
				binAntidrugkinds.setTBinAntiphosphine(binAntiphosphine);
			}
		}
		
		try{
			if(lcbm == null) {
				System.out.println("no lcbm");
			}
			if(binService.find(lcbm) == null) {
				System.out.println("no bin");
			}
			
			binAntiphosphine.setTGrainbin(binService.find(lcbm));
			Date nowDate = new Date();
			binAntiphosphine.setReportdate(nowDate);
			binAntiphosphine.setModifytime(nowDate);
			tBinAntiphosphineService.save(binAntiphosphine);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editBinAntiphosphineEntrance")
	public String editBinDepotInvestEntrance(HttpSession session,ModelMap model,Long id) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			TBinAntiphosphine tBinAntiphosphine = tBinAntiphosphineService.find(id);
			Grainbin grainbin = tBinAntiphosphine.getTGrainbin();
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());
			model.addAttribute("postcode",u.getGraindepot().getPostcode());
			model.addAttribute("lkdz",u.getGraindepot().getLkdz());
			model.addAttribute("longtitude",u.getGraindepot().getLongtitude());		//经度
			model.addAttribute("altitude",u.getGraindepot().getAltitude());			//海拔
			model.addAttribute("latitude",u.getGraindepot().getLatitude());			//纬度
			//粮仓基本信息
			model.addAttribute("lcbm", grainbin.getLcbm());
			model.addAttribute("grainbin", grainbin);
			Grain grain = grainService.findLast(grainbin.getLcbm());
			model.addAttribute("grain", grain);
			//调查表基本信息
			model.addAttribute("binAntiphosphine", tBinAntiphosphine);
			model.addAttribute("indexAntidrugkinds", tBinAntiphosphine.getTBinAntidrugkindses().size());
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/binAntiphosphine/editBinAntiphosphine";
	}
	
	@RequestMapping("/editBinAntiphosphine")
	@ResponseBody
	public Json editDepotInvest(HttpServletRequest request,TBinAntiphosphine binAntiphosphine,String lcbm){
		Json j = new Json();
		
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		//BinAntidrugkinds 
		for (Iterator <TBinAntidrugkinds> iterator = binAntiphosphine.getTBinAntidrugkindses().iterator(); iterator.hasNext(); ){
			TBinAntidrugkinds binAntidrugkinds = iterator.next();
			if (binAntidrugkinds==null || binAntidrugkinds.getKind()==null || binAntidrugkinds.getKind().trim().length()<1){
				iterator.remove();
			}else{
				binAntidrugkinds.setTBinAntiphosphine(binAntiphosphine);
			}
		}
		try{	
			binAntiphosphine.setAnnual(tBinAntiphosphineService.find(binAntiphosphine.getId()).getAnnual());
			binAntiphosphine.setTGrainbin(binService.find(lcbm));
			binAntiphosphine.setModifytime(new Date());
			tBinAntiphosphineService.update(binAntiphosphine);
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
			tBinAntiphosphineService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
