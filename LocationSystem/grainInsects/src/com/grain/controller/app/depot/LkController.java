package com.grain.controller.app.depot;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Message;
import com.grain.Principal;
import com.grain.FileInfo.FileType;
import com.grain.Message.Type;
import com.grain.Principal.UserType;
import com.grain.entity.BintypePic;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.user.DepotUser;
import com.grain.service.BintypeService;
import com.grain.util.SpringUtils;
/**
 * @author Administrator
 * 粮库关联相关api
 */
@Controller
@RequestMapping("/app/depot")
public class LkController extends BaseController {
	@Resource(name="bintypeServiceImpl")
	BintypeService bintypeService;	//粮仓
	
	@RequestMapping(value="/login")
	@ResponseBody
	public Message login(String username,/*String lkbm,*/String password,
			HttpServletRequest request, HttpSession session){
		Message mes = super.isValidUser(username, /*lkbm,*/ password);
		if (mes.getType()==Type.success){
			session.invalidate();
			session = request.getSession();
			mes.setContent(session.getId());		// 设置访问JSESSIONID
			session.setAttribute(CommonAttributes.Principal, new Principal(UserType.depot,username));
		}
		return mes;
	}
	
	//更新粮库
	@RequestMapping("/editDepot")
	@ResponseBody
	public Json editDepot(Graindepot entity,HttpSession session){
		Json j = new Json();
		Message mes =hasAuthourize(session);
		if (mes.getType()==Type.error){	//必须具有 manager权限
			j.setSuccess(false);
			j.setMsg(mes.getContent());
			return j;
		}	
		
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
	
	
	@RequestMapping("/addDepotUser")
	@ResponseBody
	public Message addDepotUser(DepotUser entity,String graindepotid, HttpSession session){
		Message mes =hasAuthourize(session );
		if (mes.getType()==Type.error)	//必须具有manager权限
			return mes;
		Graindepot gd = depotService.find(graindepotid);
		entity.setGraindepot(gd);
		try{
			depotuserSrv.save(entity);
			mes.setType(Type.success);
			mes.setContent("添加成功！");
		}catch (Exception e) {
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
		}
		return mes;
	}

	@RequestMapping("/editDepotUser")
	@ResponseBody
	public Message editDepotUser(HttpServletRequest req,
			DepotUser entity,String graindepotid, 
			HttpSession session){
		Message mes =hasAuthourize(session , entity.getUsername());
		if (mes.getType()==Type.error)	//必须具有权限
			return mes;
		String [] bins=req.getParameterValues("bins");		//服务器端不能直接接受字符串数组，否则会自动去掉首位空格字符
		Graindepot gd = depotService.find(graindepotid);
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
		try {
			depotuserSrv.update(entity);
			mes.setType(Type.success);
			mes.setContent("修改成功！");
		} catch (Exception e) {
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
		}
		return mes;
	}
	
	// 删除库管员
	@RequestMapping("/deleteDepotuser")
	@ResponseBody
	public Json deleteDepotuser(String username ,
			HttpSession session){
		Json j = new Json();
		Message mes =hasAuthourize(session , username);
		if (mes.getType()==Type.error){	//必须具有权限
			j.setSuccess(false);
			j.setMsg(mes.getContent());
			return j;
		}
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
	
	/**
	 * 增加粮仓
	 * @param bin	粮仓对象
	 * @param lkbm 	粮库编码
	 * @param usernames	库管员用户名数组
	 * @return
	 */
	@RequestMapping("/addBin")
	@ResponseBody
	public Json addBin(Grainbin bin,String lkbm,String [] usernames,
			HttpSession session){
		Json j = new Json();
		Message mes =hasAuthourize(session);
		if (mes.getType()==Type.error){	//必须具有 manager权限
			j.setSuccess(false);
			j.setMsg(mes.getContent());
			return j;
		}
		
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
	
	@RequestMapping("/editBin")
	@ResponseBody
	public Json editBin(Grainbin bin,HttpServletRequest req,HttpSession session){
		Json j = new Json();
		Message mes =hasAuthourize(session);
		if (mes.getType()==Type.error){	//必须具有 manager权限
			j.setSuccess(false);
			j.setMsg(mes.getContent());
			return j;
		}

		String [] usernames=req.getParameterValues("usernames");		//服务器端不能直接接受字符串数组，否则会自动去掉首位空格字符

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
			DepotUser dp = depotuserSrv.find(id);
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
	public Json deleteBin(String lcbm,HttpSession session){
		Json j = new Json();
		Message mes =hasAuthourize(session);
		if (mes.getType()==Type.error){	//必须具有 manager权限
			j.setSuccess(false);
			j.setMsg(mes.getContent());
			return j;
		}	
		
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
}
