package com.grain.controller.depoter;

import java.util.Iterator;
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
import com.grain.entity.DepotHumidity;
import com.grain.entity.Grain;
import com.grain.entity.Grainbin;
import com.grain.entity.TBinInsects;
import com.grain.entity.TBinInvest;
import com.grain.entity.TBinPreprotect;
import com.grain.entity.TBinProtect;
import com.grain.entity.TBinUseDrug;
import com.grain.entity.TDepoEnvitemperature;
import com.grain.entity.TDepotGraintemperature;
import com.grain.entity.TDepotInvest;
import com.grain.entity.TDepotPreprotect;
import com.grain.entity.TDepotProtect;
import com.grain.entity.TDepotUseDrug;
import com.grain.entity.TDepotsInsects;
import com.grain.entity.user.DepotUser;
import com.grain.service.GrainService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.TBinInvestService;
import com.grain.service.TDepotInvestService;
import com.grain.service.user.DepotUserService;

/*
 * 	害虫调查，包括：总表和分仓调查表
 *  
 */
@Controller
@RequestMapping("depoter/depotinvest")
public class DepotInvestController {

	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓

	@Resource(name="tdepotInvestServiceImpl")
	TDepotInvestService depotInvestService;	//虫调总表
	
	@Resource(name="tbinInvestServiceImpl")
	TBinInvestService binInvestService; // 分仓调查表
	
	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name = "grainServiceImpl")
	GrainService grainService;
	
	@RequestMapping("/depot_invest_list")
	public String depotInvestList(){
		
		return "depoter/dataReport/depotInvest/depot_invest_list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<TDepotInvest>getDataGrid(Pageable pageable,TDepotInvest queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new TDepotInvest();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			queryParm.setGraindepot(u.getGraindepot());
		}
		return depotInvestService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addDepotInvestEntrance")
	public String addDepotInvestEntrance(HttpSession session,ModelMap model) 
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
		return "depoter/dataReport/depotInvest/addDepotInvest";
	}
	
	@RequestMapping("/addDepotInvest")
	@ResponseBody
	public Json addDepotInvest(TDepotInvest depotInvest,String lkbm){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		//TDepotsInsects 
		for (Iterator <TDepotsInsects> iterator = depotInvest.getTDepotsInsectses().iterator(); iterator.hasNext(); ){
			TDepotsInsects insect = iterator.next();
			if (insect==null || insect.getKinds().trim().length()<1){
				iterator.remove();
			}else{
				insect.setTDepotInvest(depotInvest);
			}
		}
		
		//TDepotUseDrug 
		for (Iterator <TDepotUseDrug> iterator = depotInvest.getTDepotUseDrugs().iterator(); iterator.hasNext(); ){
			TDepotUseDrug usedrug = iterator.next();
			if (usedrug==null || usedrug.getDrugname().trim().length()<1){
				iterator.remove();
			}else{
				usedrug.setTDepotInvest(depotInvest);
				int o = 0;
			}
		}
		
		//TDepotGraintemperature	粮温
		for (Iterator <TDepotGraintemperature> iterator = depotInvest.getDepotGraintemperatures().iterator(); iterator.hasNext(); ){
			TDepotGraintemperature depotGraintemperature = iterator.next();
			if (depotGraintemperature==null ){
				iterator.remove();
			}else{
				depotGraintemperature.setTDepotInvest(depotInvest);
			}
		}
		
		//DepotHumidity 环境湿度
		for (Iterator <DepotHumidity> iterator = depotInvest.getTDepotHumidities().iterator(); iterator.hasNext(); ){
			DepotHumidity depotHumiditie = iterator.next();
			if (depotHumiditie==null ){
				iterator.remove();
			}else{
				depotHumiditie.setTDepotInvest(depotInvest);
			}
		}
		
		//TDepoEnvitemperature 环境温度 
		for (Iterator <TDepoEnvitemperature> iterator = depotInvest.getTDepoEnvitemperatures().iterator(); iterator.hasNext(); ){
			TDepoEnvitemperature depoEnvitemperature = iterator.next();
			if (depoEnvitemperature==null ){
				iterator.remove();
			}else{
				depoEnvitemperature.setTDepotInvest(depotInvest);
			}
		}

		//TDepotPreprotect 
		for (Iterator <TDepotPreprotect> iterator = depotInvest.getDepotPreprotects().iterator(); iterator.hasNext(); ){
			TDepotPreprotect depotPreprotect = iterator.next();
			if (depotPreprotect==null ){
				iterator.remove();
			}else{
				depotPreprotect.setTDepotInvest(depotInvest);
			}
		}
		
		//TDepotProtect
		for (Iterator <TDepotProtect> iterator = depotInvest.getDepotProtects().iterator(); iterator.hasNext(); ){
			TDepotProtect depotProtect = iterator.next();
			if (depotProtect==null ){
				iterator.remove();
			}else{
				depotProtect.setTDepotInvest(depotInvest);
			}
		}
		
		try{
			depotInvest.setGraindepot(depotSrv.find(lkbm));
			depotInvestService.save(depotInvest);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editDepotInvestEntrance")
	public String editDepotInvestEntrance(HttpSession session,ModelMap model,Long sm, String lkbm) 
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
			model.addAttribute("depotInvest", depotInvestService.find(sm));
			model.addAttribute("indexInsect", depotInvestService.find(sm).getTDepotsInsectses().size());
			model.addAttribute("indexDrug", depotInvestService.find(sm).getTDepotUseDrugs().size());
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotInvest/editDepotInvest";
	}
	
	@RequestMapping("/editDepotInvest")
	@ResponseBody
	public Json editDepotInvest(TDepotInvest depotInvest, String lkbm){
		Json j = new Json();
		//TDepotsInsects 
		for (Iterator <TDepotsInsects> iterator = depotInvest.getTDepotsInsectses().iterator(); iterator.hasNext(); ){
			TDepotsInsects insect = iterator.next();
			if (insect==null || insect.getKinds().trim().length()<1){
				iterator.remove();
			}else{
				insect.setTDepotInvest(depotInvest);
			}
		}
		
		//TDepotUseDrug 
		for (Iterator <TDepotUseDrug> iterator = depotInvest.getTDepotUseDrugs().iterator(); iterator.hasNext(); ){
			TDepotUseDrug usedrug = iterator.next();
			if (usedrug==null || usedrug.getDrugname().trim().length()<1){
				iterator.remove();
			}else{
				usedrug.setTDepotInvest(depotInvest);
			}
		}
		
		//TDepotGraintemperature	粮温
		for (Iterator <TDepotGraintemperature> iterator = depotInvest.getDepotGraintemperatures().iterator(); iterator.hasNext(); ){
			TDepotGraintemperature depotGraintemperature = iterator.next();
			if (depotGraintemperature==null ){
				iterator.remove();
			}else{
				depotGraintemperature.setTDepotInvest(depotInvest);
			}
		}
		
		//DepotHumidity 环境湿度
		for (Iterator <DepotHumidity> iterator = depotInvest.getTDepotHumidities().iterator(); iterator.hasNext(); ){
			DepotHumidity depotHumiditie = iterator.next();
			if (depotHumiditie==null ){
				iterator.remove();
			}else{
				depotHumiditie.setTDepotInvest(depotInvest);
			}
		}
		
		//TDepoEnvitemperature 环境温度 
		for (Iterator <TDepoEnvitemperature> iterator = depotInvest.getTDepoEnvitemperatures().iterator(); iterator.hasNext(); ){
			TDepoEnvitemperature depoEnvitemperature = iterator.next();
			if (depoEnvitemperature==null ){
				iterator.remove();
			}else{
				depoEnvitemperature.setTDepotInvest(depotInvest);
			}
		}

		//TDepotPreprotect 
		for (Iterator <TDepotPreprotect> iterator = depotInvest.getDepotPreprotects().iterator(); iterator.hasNext(); ){
			TDepotPreprotect depotPreprotect = iterator.next();
			if (depotPreprotect==null ){
				iterator.remove();
			}else{
				depotPreprotect.setTDepotInvest(depotInvest);
			}
		}
		
		//TDepotProtect
		for (Iterator <TDepotProtect> iterator = depotInvest.getDepotProtects().iterator(); iterator.hasNext(); ){
			TDepotProtect depotProtect = iterator.next();
			if (depotProtect==null ){
				iterator.remove();
			}else{
				depotProtect.setTDepotInvest(depotInvest);
			}
		}	
		
		try{
			depotInvest.setGraindepot(depotSrv.find(lkbm));
			depotInvestService.update(depotInvest);
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
	public Json deleteReportRec(Long sm){
		Json j = new Json();
		
		try{
			depotInvestService.delete(sm);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/bin_invest_list")
	public String binInvestList(){
		
		return "depoter/dataReport/binInvest/bin_invest_list";
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
	public Page<TBinInvest> getGrainBin(Pageable pageable,TBinInvest queryParm, 
			HttpSession session, String Annual, String lcbm) {
		if (queryParm==null)
			queryParm = new TBinInvest();
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
		return binInvestService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addBinInvestEntrance")
	public String addBinInvestEntrance(HttpSession session,ModelMap model, String lcbm, Integer annual) 
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
			model.addAttribute("designcapacity", grainbin.getDesigncapacity());
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
		return "depoter/dataReport/binInvest/addBinInvest";
	}
	
	@RequestMapping("/addBinInvest")
	@ResponseBody
	public Json addBinInvest(TBinInvest binInvest,String lcbm, Integer annual){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		
		binInvest.setAnnual(annual);
		
		//TBinProtect
		for(Iterator<TBinProtect> iterator = binInvest.getTBinProtect().iterator(); iterator.hasNext(); ) {
			TBinProtect insect = iterator.next();
			if(insect == null) {
				iterator.remove();
			} else {
				insect.setTBinInvest(binInvest);
			}
		}
		
		//TBinPreprotect
		for(Iterator<TBinPreprotect> iterator = binInvest.getTBinPreprotect().iterator(); iterator.hasNext(); ) {
			TBinPreprotect insect = iterator.next();
			if(insect == null) {
				iterator.remove();
			} else {
				insect.setTBinInvest(binInvest);
			}
		}
		
		//TBinInsectses
		for(Iterator<TBinInsects> iterator = binInvest.getTBinInsectses().iterator(); iterator.hasNext(); ) {
			TBinInsects insect = iterator.next();
			if(insect == null) {
				iterator.remove();
			} else {
				insect.setTBinInvest(binInvest);
			}
		}
		
		//TBinUseDrugs
		for(Iterator<TBinUseDrug> iterator = binInvest.getTBinUseDrugs().iterator(); iterator.hasNext(); ) {
			TBinUseDrug usedrug = iterator.next();
			if(usedrug == null || usedrug.getDrugname().trim().length()<1) {
				iterator.remove();
			} else {
				usedrug.setTBinInvest(binInvest);
			}
		}
		
		try{
			binInvest.setTGrainbin(binService.find(lcbm));
			binInvestService.save(binInvest);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editBinInvestEntrance")
	public String editBinInvestEntrance(HttpSession session,ModelMap model,Long sm) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			TBinInvest binInvest = binInvestService.find(sm);
			Grainbin grainbin = binInvest.getTGrainbin();
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
			model.addAttribute("binInvest", binInvest);
			model.addAttribute("indexInsect", binInvest.getTBinInsectses().size());
			model.addAttribute("indexDrug", binInvest.getTBinUseDrugs().size());
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/binInvest/editBinInvest";
	}
	
	@RequestMapping("/editBinInvest")
	@ResponseBody
	public Json editBinInvest(TBinInvest binInvest){
		Json j = new Json();
		Long sm = binInvest.getSm();
		TBinInvest binInvest2 = binInvestService.find(sm);
		Grainbin grainbin = binInvest2.getTGrainbin();
		String lcbm = grainbin.getLcbm();
		binInvest.setAnnual(binInvest2.getAnnual());
		//TBinProtect
		for(Iterator<TBinProtect> iterator = binInvest.getTBinProtect().iterator(); iterator.hasNext(); ) {
			TBinProtect insect = iterator.next();
			if(insect == null) {
				iterator.remove();
			} else {
				insect.setTBinInvest(binInvest);
			}
		}
		//TBinPreprotect
		for(Iterator<TBinPreprotect> iterator = binInvest.getTBinPreprotect().iterator(); iterator.hasNext(); ) {
			TBinPreprotect insect = iterator.next();
			if(insect == null) {
				iterator.remove();
			} else {
				insect.setTBinInvest(binInvest);
			}
		}
		
		//TBinInsectses
		for(Iterator<TBinInsects> iterator = binInvest.getTBinInsectses().iterator(); iterator.hasNext(); ) {
			TBinInsects insect = iterator.next();
			if(insect == null) {
				iterator.remove();
			} else {
				insect.setTBinInvest(binInvest);
			}
		}
		
		//TBinUseDrugs
		for(Iterator<TBinUseDrug> iterator = binInvest.getTBinUseDrugs().iterator(); iterator.hasNext(); ) {
			TBinUseDrug usedrug = iterator.next();
			if(usedrug == null || usedrug.getDrugname().trim().length()<1) {
				iterator.remove();
			} else {
				usedrug.setTBinInvest(binInvest);
			}
		}
		
		try{
			binInvest.setTGrainbin(binService.find(lcbm));
			binInvestService.update(binInvest);
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
	public Json deleteBinReportRec(Long sm){
		Json j = new Json();
		try{
			binInvestService.delete(sm);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
