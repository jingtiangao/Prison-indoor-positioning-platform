package com.grain.controller.depoter;

import java.util.Date;
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
import com.grain.entity.DepotFlyline;
import com.grain.entity.Grain;
import com.grain.entity.Grainbin;
import com.grain.entity.TBinFlyline;
import com.grain.entity.user.DepotUser;
import com.grain.service.GrainService;
import com.grain.service.DepotFlylineService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.TBinFlylineService;
import com.grain.service.user.DepotUserService;

@Controller
@RequestMapping("depoter/depotflyline")
public class DepotFlylineController {

	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@Resource(name="grainServiceImpl")
	GrainService grainService;
	
	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name="depotFlylineServiceImpl")
	DepotFlylineService depotFlylineService;	//
	
	@Resource(name="tBinFlylineServiceImpl")
	TBinFlylineService tBinFlylineService;	//
	
	@RequestMapping("/depot_flyline_list")
	public String depotFlylineList(){
		return "depoter/dataReport/depotFlyline/depot_flyline_list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DepotFlyline> getDataGrid(Pageable pageable,DepotFlyline queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new DepotFlyline();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			queryParm.setGraindepot(u.getGraindepot());
		}
		return depotFlylineService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addDepotFlylineEntrance")
	public String addDepotFlylineEntrance(HttpSession session,ModelMap model) 
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
		return "depoter/dataReport/depotFlyline/addDepotFlyline";
	}
	
	@RequestMapping("/addDepotFlyline")
	@ResponseBody
	public Json addDepotFlyline(HttpServletRequest request,DepotFlyline depotFlyline,String lkbm){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		
		try{
			depotFlyline.setGraindepot(depotSrv.find(lkbm));
			Date nowDate = new Date();
			depotFlyline.setReportdate(nowDate);
			depotFlylineService.save(depotFlyline);
			depotFlyline.setModifydate(nowDate);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editDepotFlylineEntrance")
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
			model.addAttribute("depotFlyline", depotFlylineService.find(id));
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotFlyline/editDepotFlyline";
	}
	
	@RequestMapping("/editDepotFlyline")
	@ResponseBody
	public Json editDepotInvest(HttpServletRequest request,DepotFlyline depotFlyline, String lkbm){
		Json j = new Json();
		
		try{
			depotFlyline.setGraindepot(depotSrv.find(lkbm));
			depotFlyline.setModifydate(new Date());
			depotFlylineService.update(depotFlyline);
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
			depotFlylineService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/bin_flyline_list")
	public String binFlylineList(){
		return "depoter/dataReport/binFlyline/bin_flyline_list";
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
	public Page<TBinFlyline> getGrainBin(Pageable pageable,TBinFlyline queryParm, 
			HttpSession session, String Annual, String lcbm) {
		if (queryParm==null)
			queryParm = new TBinFlyline();
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
		return tBinFlylineService.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/addBinFlylineEntrance")
	public String addBinFlylineEntrance(HttpSession session,ModelMap model, String lcbm, Integer annual) 
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
			model.addAttribute("grainbin", grainbin);
			Grain grain = grainService.findLast(lcbm);
			model.addAttribute("grain", grain);
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/binFlyline/addBinFlyline";
	}
	
	@RequestMapping("/addBinFlyline")
	@ResponseBody
	public Json addBinFlyline(TBinFlyline binFlyline,String lcbm, Integer annual){
		Json j = new Json();
		binFlyline.setAnnual(annual);
		
		try{
			if(lcbm == null) {
				System.out.println("no lcbm");
			}
			if(binService.find(lcbm) == null) {
				System.out.println("no bin");
			}
			binFlyline.setTGrainbin(binService.find(lcbm));
			Date nowDate = new Date();
			binFlyline.setReportdate(nowDate);
			binFlyline.setModifydate(nowDate);
			tBinFlylineService.save(binFlyline);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editBinFlylineEntrance")
	public String editBinDepotInvestEntrance(HttpSession session,ModelMap model,Long id) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			TBinFlyline tBinFlyline = tBinFlylineService.find(id);
			Grainbin grainbin = tBinFlyline.getTGrainbin();
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
			model.addAttribute("binFlyline", tBinFlyline);
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/binFlyline/editBinFlyline";
	}
	
	@RequestMapping("/editBinFlyline")
	@ResponseBody
	public Json editDepotInvest(HttpServletRequest request,TBinFlyline binFlyline,String lcbm){
		Json j = new Json();
		
		try{
			binFlyline.setAnnual(tBinFlylineService.find(binFlyline.getId()).getAnnual());
			
			binFlyline.setTGrainbin(binService.find(lcbm));
			binFlyline.setModifydate(new Date());
			tBinFlylineService.update(binFlyline);
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
			tBinFlylineService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
