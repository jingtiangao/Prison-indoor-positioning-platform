package com.grain.controller.depoter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import com.grain.entity.Area;
import com.grain.entity.BintypePic;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.Graindirection;
import com.grain.entity.page.AreaP;
import com.grain.entity.user.DepotUser;
import com.grain.service.AreaService;
import com.grain.service.BintypeService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.GraindirectionService;
import com.grain.service.user.DepotUserService;
import com.grain.util.SpringUtils;

import com.grain.FileInfo.FileType;
import com.grain.service.FileService;

/**
 * 
 * @author szy
 * 粮库基本资料维护控制器
 */
@Controller
@RequestMapping("/depoter/depot")
public class DepotLkController {
	
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotService;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@Resource(name="bintypeServiceImpl")
	BintypeService bintypeService;	//粮仓
	
	@Resource(name = "graindirectionServiceImpl")
	private GraindirectionService gdSrv;	//储粮区
	
	@Resource(name = "areaServiceImpl")
	private AreaService areaService;	//地区
	
	@Resource(name = "fileServiceImpl")	//file
	private FileService fileService;
	
	@Resource(name="depotUserServiceImpl")	//	库管员
	private DepotUserService depotuserService;
	
	@RequestMapping("/listbins")
	public String Entrance(){
		return "depoter/depot/listbins";
	}
	
	
	@RequestMapping("/getBins")
	@ResponseBody
	public Page<Grainbin> getBins(Pageable pageable,Grainbin queryParm,
			HttpSession session) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username="";
		if (p!=null){
			username=p.getUsername();
		}else
			throw new Exception("session is losed!");
		String lkbm=depotuserService.find(username).getGraindepot().getLkbm();
		if (lkbm!=null){
			Graindepot gd = depotService.find(lkbm);
			queryParm.setGraindepot(gd);
		}
		Page<Grainbin> map=binService.findPage(pageable,queryParm);
		return map;
	}
	
	
	@RequestMapping("/editDepotEntrance") //菜单进入
	public String editDepotEntrance(HttpSession session,ModelMap model) 
			throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username="";
		if (p!=null){
			username=p.getUsername();
		}else
			throw new Exception("session is losed!");
		DepotUser depot = depotuserService.findByUsername(username);
		String role = depotuserService.userRole(username);
		if(role.equalsIgnoreCase("depotmanager")){
			String lkbm=depotuserService.find(username).getGraindepot().getLkbm();
			Graindepot gd = depotService.find(lkbm);
			model.addAttribute("graindepot", gd);
			model.addAttribute("graindirect", gd.getArea().getGd().getFullname());
			return "depoter/depot/editDepot";
		}else{
			String lkbm=depotuserService.find(username).getGraindepot().getLkbm();
			Graindepot gd = depotService.find(lkbm);
			model.addAttribute("graindepot", gd);
			model.addAttribute("graindirect", gd.getArea().getGd().getFullname());
			return "depoter/depot/watchDepot";
		}
	}
	
	@RequestMapping("/editDepot")
	@ResponseBody
	public Json editDepot(Graindepot entity){
		Json j = new Json();
		try{
			Graindepot gd = depotService.find(entity.getLkbm());
			//BeanUtils.copyProperties(t, r);
			entity.setArea(gd.getArea());		//把所在地区找回来
			depotService.update(entity,"depotPhosphinefumigations","tdepotInertaerosols","tdepotAntiphosphines",
					"tdepotFlylines","tdepoEnvitemperatures","tdepotHumidities",
					"tdepotCas","grainbins","tdepotCleankills",
					"depotusers","tdepotGraintemperatures","tdepotProtecteds","area");
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/addBinEntrance")
	public String addBinEntrance(HttpSession session,ModelMap model) 
			throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username="";
		if (p!=null){
			username=p.getUsername();
		}else
			throw new Exception("session is losed!");
		String lkbm = depotuserService.find(username).getGraindepot().getLkbm();
		String lkmc = depotuserService.find(username).getGraindepot().getLkmc();
		model.addAttribute("lkbm", lkbm);
		model.addAttribute("lkmc", lkmc);
		model.addAttribute("nousedusers", depotService.find(lkbm).getDepotusers());
		return "depoter/depot/addbin";
	}
	
	@RequestMapping("/addBin")
	@ResponseBody
	public Json addBin(Grainbin bin,String lkbm,String [] usernames){
		Json j = new Json();
		
		for (Iterator<BintypePic> iterator = bin.getBintypePics().iterator();iterator.hasNext();){
			BintypePic bintypePic = iterator.next();
			if (bintypePic == null || bintypePic.isEmpty()) {
				iterator.remove();
				continue;
			}
			
			if (bintypePic.getFile() != null && !bintypePic.getFile().isEmpty()) {
				if (!fileService.isValid(FileType.image, bintypePic.getFile())) {
					j.setSuccess(false);
					j.setMsg(SpringUtils.getMessage("admin.upload.invalid"));
					return j;
				}
			}
		}
		
		if (binService.equals(bin.getLcbm())){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("admin.invalid.add.grainbin"));
			return j;
		}
		Graindepot gd = depotService.find(lkbm);
		bin.setGraindepot(gd);
		
		for (BintypePic pic : bin.getBintypePics()){		// deal with bin type picture
			bintypeService.build(pic);
		}
		
		try{
			binService.save(bin,usernames);
			j.setSuccess(true);
			j.setMsg(SpringUtils.getMessage("admin.success.add.grainbin"));
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
		
	
	@RequestMapping("/editBinEntrance")
	public String editBinEntrance(String lcbm,ModelMap model){
		Grainbin bin = binService.find(lcbm);
		model.addAttribute("bin", bin);
		model.addAttribute("picTotal", bin.getBintypePics()==null?0:bin.getBintypePics().size());
		model.addAttribute("depotTotal", bin.getDepotusers()==null?0:bin.getDepotusers().size());
		
		Set<DepotUser> wholeusers =bin.getGraindepot().getDepotusers();
		if (wholeusers!=null&&!wholeusers.isEmpty()){
			Set<DepotUser> usedusers = bin.getDepotusers();
			if (usedusers!=null&&!usedusers.isEmpty())
				wholeusers.removeAll(usedusers);		//这个粮仓的没有用到的库管员
		}
		model.addAttribute("nousedusers", wholeusers);
		return "depoter/depot/editbin";
	}
	
	@RequestMapping("/editBin")
	@ResponseBody
	public Json editBin(Grainbin bin,HttpServletRequest req){
		String [] usernames=req.getParameterValues("usernames");		//服务器端不能直接接受字符串数组，否则会自动去掉首位空格字符
		Json j = new Json();
		for (Iterator<BintypePic> iterator = bin.getBintypePics().iterator();iterator.hasNext();){
			BintypePic bintypePic = iterator.next();
			if ((bintypePic == null || bintypePic.isEmpty()) && bintypePic.getThumbnail()==null) {
				iterator.remove();
				continue;
			}
			
			if (bintypePic.getFile() != null && !bintypePic.getFile().isEmpty()) {
				if (!fileService.isValid(FileType.image, bintypePic.getFile())) {
					j.setSuccess(false);
					j.setMsg(SpringUtils.getMessage("admin.upload.invalid"));
					return j;
				}
			}
		}
		
		Set<String> userset = new HashSet<String>();
		//去掉重复的用户名
		if (usernames!=null&& usernames.length>0)
			for (int i=0;i<usernames.length;i++)
				userset.add(usernames[i]);
		
		Set<DepotUser> deptusrs = new HashSet<DepotUser>();
		for(String id: userset){
			DepotUser dp = depotuserService.find(id);
			deptusrs.add(dp);
		}
		bin.setDepotusers(deptusrs);
		
		for (BintypePic pic : bin.getBintypePics()){		// deal with bin type picture
			bintypeService.build(pic);
		}
		
		try{
			binService.update(bin,"graindepot","binProtecteds","binPhosphinefumigations",
					"binInertaerosols","binCas","binAntiphosphines",
					"binFlylines","grains","binInvests",
					"insectsInbinOnDepots","binCleankills",
					"deviceRealdatas","graindepot");
			j.setSuccess(true);
			j.setMsg(SpringUtils.getMessage("admin.success.update.grainbin"));
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}		
		
		return j;
	}
	
	@RequestMapping("/deleteBin")
	@ResponseBody
	public Json deleteBin(String lcbm){
		Json j = new Json();
		Grainbin bin = binService.find(lcbm);
		if (bin.getBinProtecteds()!=null && !bin.getBinProtecteds().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.binProtecteds"));
			return j;
		}
		if (bin.getDepotusers()!=null && !bin.getDepotusers().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.depotusers"));
			return j;
		}
		
		if (bin.getBinPhosphinefumigations()!=null && !bin.getBinPhosphinefumigations().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.binPhosphinefumigations"));
			return j;
		}	
		if (bin.getBinInertaerosols()!=null && !bin.getBinInertaerosols().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.binInertaerosols"));
			return j;
		}	
		if (bin.getBinCas()!=null && !bin.getBinCas().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.binCas"));
			return j;
		}	
		if (bin.getBinAntiphosphines()!=null && !bin.getBinAntiphosphines().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.binAntiphosphines"));
			return j;
		}	
		if (bin.getBinFlylines()!=null && !bin.getBinFlylines().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.binFlylines"));
			return j;
		}	
		if (bin.getGrains()!=null && !bin.getGrains().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.grains"));
		}	
		if (bin.getBinInvests()!=null && !bin.getBinInvests().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.binInvests"));
			return j;
		}	
		if (bin.getInsectsInbinOnDepots()!=null && !bin.getInsectsInbinOnDepots().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.insectsInbinOnDepots"));
			return j;
		}
		if (bin.getBinCleankills()!=null && !bin.getBinCleankills().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.binCleankills"));
			return j;
		}	
		if (bin.getDeviceRealdatas()!=null && !bin.getDeviceRealdatas().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("grainbin.delete.invalid.deviceRealdatas"));
			return j;
		}	
		try{
			binService.delete(lcbm);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	// 获取储粮区域
	@RequestMapping("/getGraindirect")
	@ResponseBody
	public List<Graindirection> getGraindirects(){
		return gdSrv.findAll();
	}
	
	// 获取储粮地域
	@RequestMapping("/getArea")
	@ResponseBody
	public List<AreaP> getAreas(Pageable pageable, Long graindirectionid){
		Graindirection gd=gdSrv.find(graindirectionid);
		Area t = new Area();
		if (gd!=null)
			t.setGd(gd);
		else
			t.setGd(null);
		Page<Area> p=	areaService.findPage(pageable, t);
		List<AreaP> l = new ArrayList<AreaP>();
		for (Area a:p.getRows()){
			AreaP e = new AreaP();
			e.setId(a.getId());
			e.setFullname(a.getFullName());
			l.add(e);
		}
		return l;
	}
	
	
}
