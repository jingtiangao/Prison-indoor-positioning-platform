package com.grain.controller.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.Graindirection;
import com.grain.entity.page.Depot;
import com.grain.entity.user.DepotUser;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.GraindirectionService;
import com.grain.service.user.DepotUserService;
import com.grain.util.SpringUtils;
/**
 * 	库管员
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/depot/user")
public class DepoterController {

	@Resource(name="depotUserServiceImpl")
	private DepotUserService depotuserSrv;
	
	@Resource(name = "graindirectionServiceImpl")
	private GraindirectionService gdSrv;	//储粮区
	
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@RequestMapping("/entrance")
	public String Entrance(){
		return "admin/depotuser/list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DepotUser> getDataGrid(Pageable pageable,DepotUser queryParm){
		if (queryParm!=null &&queryParm.getGraindepot()!=null && 
				queryParm.getGraindepot().getLkbm()=="")
			queryParm.setGraindepot(null);
		Page<DepotUser> map=depotuserSrv.findPage(pageable,queryParm);
		return map;
	}
	
	@RequestMapping("/addDepotUserEntrance")
	public String addDepotUserEntrance( ){
		return "admin/depotuser/addDepotUser";
	}
	
/*	@RequestMapping("/addDepotUser") showFlassMessage() 实验代码
	public String addDepotUser(DepotUser entity,String lkbm,String lcbm,HttpServletRequest req){
		
		req.setAttribute("FlashMessage", "添加成功！");
		return Entrance();
	}*/
	
	@RequestMapping("/addDepotUser")
	@ResponseBody
	public Json addDepotUser(DepotUser entity,String graindepotid){
		Json j = new Json();
		Graindepot gd = depotSrv.find(graindepotid);
		entity.setGraindepot(gd);
		try{
			depotuserSrv.save(entity);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/editDepotUserEntrance")
	public String editDepotUserEntrance(String username,ModelMap model ){
		model.addAttribute("user", depotuserSrv.find(username));
		String lkbm=depotuserSrv.find(username).getGraindepot().getLkbm();
		model.addAttribute("lkbm", lkbm);
		Set<Grainbin> wholebins=depotSrv.find(lkbm).getGrainbins();
		
		if (wholebins!=null &&!wholebins.isEmpty() ){
			Set<Grainbin> usedbins=depotuserSrv.find(username).getGrainbins();
			if (usedbins!=null&&!usedbins.isEmpty())
				wholebins.removeAll(usedbins);
		}
		model.addAttribute("nousedbins", wholebins);
		
		return "admin/depotuser/editDepotUser";
	}
	
	@RequestMapping("/editDepotUser")
	@ResponseBody
	public Json editDepotUser(HttpServletRequest req,
			DepotUser entity,String graindepotid){
		String [] bins=req.getParameterValues("bins");		//服务器端不能直接接受字符串数组，否则会自动去掉首位空格字符
		Json j = new Json();
		Graindepot gd = depotSrv.find(graindepotid);
		entity.setGraindepot(gd);
		
		//去掉重复的粮仓名
		Set<String> binStrset = new HashSet<String>();
		if (bins!=null&& bins.length>0)
			for (int i=0;i<bins.length;i++)
				binStrset.add(bins[i]);
		
		Set<Grainbin> binset= new HashSet<Grainbin>();
		for(String id: binStrset){
			binset.add(binService.find(id));
		}
		entity.setGrainbins(binset);
		try{
			depotuserSrv.update(entity);
			j.setSuccess(true);
			j.setMsg("修改成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	// 删除库管员
	@RequestMapping("/deleteDepotuser")
	@ResponseBody
	public Json deleteDepotuser(String username){
		Json j = new Json();
		DepotUser u = depotuserSrv.find(username);
		if (u==null){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("depoter.delete.depotuser.error.user"));
			return j;
		}
		if (u.getGrainbins()!=null&& !u.getGrainbins().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("depoter.delete.depoteruser.hasbins"));
			return j;
		}
		try{
			depotuserSrv.delete(u);
			j.setSuccess(true);
			j.setMsg(SpringUtils.getMessage("depoter.delete.depoteruser.success"));
		}catch(Exception e){
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
	
	@RequestMapping("/getGraindepot")
	@ResponseBody
	public List<Depot> getGraindepot(Long graindirectionid){
		List<Depot> lst= new ArrayList<Depot>();
		List<Object[]> l=depotuserSrv.findGriandepot(graindirectionid);
		for (int i=0; i<l.size();i++){
			Depot d= new Depot();
			d.setLkbm((String)l.get(i)[0]);
			d.setLkmc((String)l.get(i)[1]);
			lst.add(d);
		}
		return lst;
	}
	
	@RequestMapping(value = "/check_username")
	public @ResponseBody
	boolean checkUserName(String username ) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		return !depotuserSrv.existUserName(username);
	}
}
