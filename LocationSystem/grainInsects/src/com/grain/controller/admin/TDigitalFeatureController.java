package com.grain.controller.admin;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.FileInfo.FileType;
import com.grain.entity.TCatalogPic;
import com.grain.entity.TDigitalFeature;
import com.grain.entity.TDigitalPic;
import com.grain.entity.TInsectsIndex;
import com.grain.entity.TInsectsSpecification;
import com.grain.entity.TPreventprocess;
import com.grain.entity.user.Admin;
import com.grain.service.FileService;
import com.grain.service.TCatalogIndexService;
import com.grain.service.TCatalogPicService;
import com.grain.service.TDigitalFeatureService;
import com.grain.service.TDigitalPicService;
import com.grain.service.TInsectsIndexService;
import com.grain.service.TProcTypeService;
import com.grain.service.TagService;
import com.grain.service.user.AdminService;
import com.grain.util.SpringUtils;
import com.sun.jmx.snmp.Timestamp;

/**
 * Controller - 知识库 数字特征
 * 
 */
@Controller
@RequestMapping("/admin/digitalfeature")
public class TDigitalFeatureController extends BaseController{

	@Resource(name = "tdigitalfeatureServiceImpl")
	TDigitalFeatureService tdigitalfeatureService;
	
	@Resource(name = "tDigitalPicServiceImpl")
	TDigitalPicService tDigitalPicService;
	
	@Resource(name = "tcatalogIndexServiceImpl")
	TCatalogIndexService catalogIndexService;
	
	@Resource(name="adminServiceImpl")
	AdminService admin;
	
	@Resource(name = "fileServiceImpl")	//file
	private FileService fileService;
	
	@RequestMapping(value = "/addtdigitalfeatureEntrance", method = RequestMethod.GET)
	public String addTDigitalFeatureEntrance(long index, Timestamp time,HttpSession session,ModelMap model) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			Admin u = admin.find(p.getUsername());
			model.addAttribute("index", index);
		}else
			throw new Exception("session is losed!");
		return "admin/digitalFeature/add";
	}
	
	@RequestMapping("/addtdigitalfeature")
	@ResponseBody
	public Json addTDigitalFeature(HttpServletRequest request,TDigitalFeature digitalfeature){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		/*
		for (Iterator<TDigitalPic> iterator = digitalfeature.getTDigitalPics().iterator();iterator.hasNext();){
			TDigitalPic catatypePic = iterator.next();
			if (catatypePic == null || catatypePic.isEmpty()) {
				iterator.remove();
				continue;
			}
			
			if (catatypePic.getFile() != null && !catatypePic.getFile().isEmpty()) {
				if (!fileService.isValid(FileType.image, catatypePic.getFile())) {
					j.setSuccess(false);
					j.setMsg(SpringUtils.getMessage("admin.upload.invalid"));
					return j;
				}
			}
		}
		
		for (TDigitalPic pic : digitalfeature.getTDigitalPics()){		// deal with catalog picture
			tDigitalPicService.build(pic);
		}
		*/
		
		try {
			tdigitalfeatureService.save(digitalfeature);
			
			//将刚添加的对象返回TDigitalFeature以显示在datagrid上并记录id
			j.setObj(digitalfeature);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editTDigitalFeatureEntrance")
	public String editTDigitalFeatureEntrance(HttpSession session,ModelMap model,Long id,long index) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			Admin u = admin.find(p.getUsername());
			model.addAttribute("digitalfeature", tdigitalfeatureService.find(id));
			model.addAttribute("index", index);
		}else
			throw new Exception("session is losed!");
		return "admin/digitalFeature/edit";
	}
	
	@RequestMapping("/editTDigitalFeature")
	@ResponseBody
	public Json editTDigitalFeature(HttpServletRequest request,TDigitalFeature digitalfeature){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		/*
		for (Iterator<TDigitalPic> iterator = digitalfeature.getTDigitalPics().iterator();iterator.hasNext();){
			TDigitalPic catatypePic = iterator.next();
			if (catatypePic == null || catatypePic.isEmpty()) {
				iterator.remove();
				continue;
			}
			
			if (catatypePic.getFile() != null && !catatypePic.getFile().isEmpty()) {
				if (!fileService.isValid(FileType.image, catatypePic.getFile())) {
					j.setSuccess(false);
					j.setMsg(SpringUtils.getMessage("admin.upload.invalid"));
					return j;
				}
			}
		}
		
		for (TDigitalPic pic : digitalfeature.getTDigitalPics()){		// deal with catalog picture
			tDigitalPicService.build(pic);
		}
		*/
		try{
			tdigitalfeatureService.update(digitalfeature);
			
			//将刚修改的对象返回TDigitalFeature以显示在datagrid上并记录id
			j.setObj(digitalfeature);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;

	}
	
	/**
	 * 列表数据
	 
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<TPreventprocess>getDataGrid(Pageable pageable,String index, 
			HttpSession session)throws Exception
	{
		catalogIndexService.find
		return tdigitalfeatureService;
	}
	*/
	
	
	@RequestMapping("/deleteTDigitalFeature")
	@ResponseBody
	public Json deleteTDigitalFeature(Long id){
		Json j = new Json();
		
		try{
			tdigitalfeatureService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
