package com.grain.controller.farmer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.FileInfo.FileType;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.controller.admin.BaseController;
import com.grain.entity.Area;
import com.grain.entity.InsectOnfield;
import com.grain.entity.TFieldPic;
import com.grain.entity.user.FarmerUser;
import com.grain.service.AreaService;
import com.grain.service.FieldCollectionPicService;
import com.grain.service.FieldCollectionService;
import com.grain.service.FileService;
import com.grain.service.user.FarmerUserService;
import com.grain.util.SpringUtils;


@Controller
@RequestMapping("/farmer/enter")
public class FarmerController extends BaseController{
	
	//野外采集Service
	@Resource(name="fieldCollectionServiceImpl")
	FieldCollectionService fieldCollectionSrv;
	
	//野外害虫图片采集Service
	@Resource(name="fieldCollectionPicServiceImpl")
	FieldCollectionPicService fieldCollectionPicSrv;
	
	@Resource(name = "fileServiceImpl")	//file
	private FileService fileService;
	
	//农户用户信息
	@Resource(name="farmerUserServiceImpl")
	FarmerUserService farmerUserService;
	
	//地区信息
	@Resource
	AreaService areaService;
	
	@RequestMapping("/list")
	public String farmerInsectOnCollectionList() {
		return "/farmer/enter/list";
	}
	
	@RequestMapping("/datagrid")
	@ResponseBody
	public Page<InsectOnfield> getDataGrid(Pageable pageable, InsectOnfield queryParm,
			HttpSession session)throws Exception {
		if (queryParm==null )
			queryParm = new InsectOnfield();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			queryParm.setModifer(p.getUsername());
		}
//		queryParm.setModifer(farmerUserService.getCurrentUsername());
		return fieldCollectionSrv.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/recordAddEntrance")
	public String addFarmerInsectEntrance(Model model) {
		model.addAttribute("farmeruser", farmerUserService.getCurrent());
		return "/farmer/enter/add";
	}
	
	@RequestMapping("/getDirect")
	@ResponseBody
	public Page<Area> getDirect(@RequestParam(value="q") String key,Pageable pageable) {
		pageable.setSearchProperty("name");
		pageable.setSearchValue(key);
		return areaService.findPage(pageable);
	}
	
	@RequestMapping("/addinsectonfield")
	@ResponseBody
	public Json addInsectOnField(HttpServletRequest req, InsectOnfield insectOnfield, @RequestParam(value="direct") Long direct) {
		Json json = new Json();
		FarmerUser farmer = farmerUserService.getCurrent();
		insectOnfield.setModifer(farmer.getUsername());
		insectOnfield.setModifydate(modifyDate(new Date()));
		insectOnfield.setCollector(farmer.getUsername());
		insectOnfield.setArea(areaService.find(direct));
		
		// 过滤无用图片信息
		for(Iterator<TFieldPic> iterator = insectOnfield.getTFieldPics().iterator();iterator.hasNext();){
			TFieldPic fieldPic = iterator.next();
			if(fieldPic == null || fieldPic.isEmpty()){
				iterator.remove();
				continue;
			}
			
			if(fieldPic.getFile() != null && !fieldPic.getFile().isEmpty()){
				if (!fileService.isValid(FileType.image, fieldPic.getFile())) {
					json.setSuccess(false);
					json.setMsg(SpringUtils.getMessage("文件格式/大小无效"));
					return json;
				}
			}
			fieldPic.setCollecttime(new Date());
			fieldPic.setTInsectOnfield(insectOnfield);
		}
		
		for(TFieldPic pic : insectOnfield.getTFieldPics()){
			fieldCollectionPicSrv.build(pic);
		}
		
		try {
			fieldCollectionSrv.save(insectOnfield);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("添加成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping("/recordEditEntrance")
	public String editInsectOnFieldEntrance(String recordId, Model model) {
		InsectOnfield insectOnField = fieldCollectionSrv.findById(recordId);
		model.addAttribute("recordId", recordId);
		model.addAttribute("fieldCollectRecord", insectOnField);
		int size = insectOnField.getTFieldPics().size();
		model.addAttribute("picTotal", size);
		return "/farmer/enter/edit";
	}
	
	@RequestMapping("/editinsectonfield")
	@ResponseBody
	public Json editInsectOnField(HttpServletRequest req, InsectOnfield insectOnfield, String recordId) {
		Json json = new Json();
		
		FarmerUser farmer = farmerUserService.getCurrent();
		insectOnfield.setModifer(farmer.getUsername());
		insectOnfield.setModifydate(modifyDate(new Date()));
		insectOnfield.setCollector(farmer.getUsername());
		insectOnfield.setArea(fieldCollectionSrv.findById(recordId).getArea());
		
		// 过滤无用图片信息
		for(Iterator<TFieldPic> iterator = insectOnfield.getTFieldPics().iterator();iterator.hasNext();){
			TFieldPic fieldPic = iterator.next();
			if(fieldPic == null){
				iterator.remove();
				continue;
			}
			
			if(fieldPic.getFile() != null && !fieldPic.getFile().isEmpty()){
				if (!fileService.isValid(FileType.image, fieldPic.getFile())) {
					json.setSuccess(false);
					json.setMsg(SpringUtils.getMessage("文件格式/大小无效"));
					return json;
				}
			}
			fieldPic.setTInsectOnfield(insectOnfield);
			fieldPic.setCollecttime(new Date());
			
			// 判断图片是否重新上传，或者是否增加图片
			if(fieldPic.getId()==null || (fieldPic.getFile()!=null && fieldPic.getFile().getOriginalFilename()!=null && !fieldPic.getFile().getOriginalFilename().trim().equals(""))) {
				fieldCollectionPicSrv.build(fieldPic);
			}
		}
		
		try {
			insectOnfield.setId(Long.parseLong(recordId));
			fieldCollectionSrv.update(insectOnfield);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("修改成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping("/deleteInsectOnField")
	@ResponseBody
	public Json deleteInsectOnField(Long id) {
		Json j = new Json();
		
		try {
			fieldCollectionSrv.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
	/**
	 * 判断s是否为空或者是否为空白字符串
	 * @param s
	 * @return
	 */
	private boolean isValidate(String s){
		boolean flag = true;
		
		if(s==null || s.trim().equals(""))
			flag = false;
		
		return flag;
		
	}
	
	//录入日期
	private String modifyDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}
