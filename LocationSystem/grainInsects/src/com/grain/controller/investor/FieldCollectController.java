package com.grain.controller.investor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.FileInfo.FileType;
import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.InsectOnfield;
import com.grain.entity.TFieldPic;
import com.grain.service.FieldCollectionPicService;
import com.grain.service.FieldCollectionService;
import com.grain.service.FileService;
import com.grain.util.SpringUtils;


@Controller
@RequestMapping("/investor/field")
public class FieldCollectController{
	
	@Resource(name="fieldCollectionServiceImpl")
	private FieldCollectionService fieldCollectionService;
	
	@Resource(name = "fileServiceImpl")	//file
	private FileService fileService;
	
	@Resource(name="fieldCollectionPicServiceImpl")
	private FieldCollectionPicService fieldCollectionPicService;
	
	
	@RequestMapping(value="/list")
	public String recordList(){
		return "/investor/field/list";
	}
	
	@RequestMapping(value="getRecordList")
	@ResponseBody
	public Page<InsectOnfield> getRecordList(Pageable pageable,InsectOnfield queryParm,
			HttpSession session) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		queryParm.setCollector(username);
		Page<InsectOnfield> map = fieldCollectionService.findPage(pageable, queryParm);
		return map;
	}
	
	@RequestMapping(value="/recordAddEntrance")
	public String recordAddEntrance(Model model){
		
		return "/investor/field/recordAdd";
	}
	@RequestMapping(value="/recordAdd")
	@ResponseBody
	public Json recordAdd(InsectOnfield insectOnfield, HttpServletRequest req, Model model, HttpSession session) throws Exception{
		Json json = new Json();
		
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
			fieldPic.setTInsectOnfield(insectOnfield);
		}
		
		for(TFieldPic pic : insectOnfield.getTFieldPics()){
			fieldCollectionPicService.build(pic);
		}
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		try {
			insectOnfield.setModifer(username);
			insectOnfield.setCollector(username);
			insectOnfield.setModifydate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			fieldCollectionService.save(insectOnfield);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("添加成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	@RequestMapping(value="/recordEditEntrance")
	public String recordEditEntrance(String recordId, Model model){
		model.addAttribute("recordId", recordId);
		InsectOnfield insectOnfield = fieldCollectionService.findById(recordId);
		model.addAttribute("fieldCollectRecord", insectOnfield);
		
		model.addAttribute("picTotal", insectOnfield.getTFieldPics().size());
		return "/investor/field/recordEdit";
	}
	@RequestMapping(value="/recordEdit")
	@ResponseBody
	public Json recordEdit(InsectOnfield insectOnfield, String recordId, Model model, HttpSession session) {
		Json json = new Json();
		
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
			
			// 判断图片是否重新上传，或者是否增加图片
			if(fieldPic.getId()==null || 
					(fieldPic.getFile()!=null && fieldPic.getFile().getOriginalFilename()!=null && !fieldPic.getFile().getOriginalFilename().trim().equals(""))
					){
				fieldCollectionPicService.build(fieldPic);
			}
		}
		try {
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			String username = "";
			if(p != null){
				username = p.getUsername();
			}else{
				throw new Exception("session is losed!");
			}
			insectOnfield.setCollector(username);
			insectOnfield.setModifydate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			insectOnfield.setId(Long.parseLong(recordId));
			fieldCollectionService.update(insectOnfield);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("修改成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="recordDelete")
	@ResponseBody
	public Json recordDelete(@RequestParam("id")String id){
		Json json = new Json();
		try {
			if(id!=null && StringUtils.isNumeric(id)){
				fieldCollectionService.delete(Long.parseLong(id));
				json.setSuccess(true);
				json.setMsg("删除成功");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}