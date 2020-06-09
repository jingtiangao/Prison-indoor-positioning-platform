package com.grain.controller.expert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
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
import com.grain.controller.admin.BaseController;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TCatalogPic;
import com.grain.entity.TDigitalFeature;
import com.grain.entity.TPreventprocess;
import com.grain.entity.Tag;
import com.grain.entity.Tag.Type;
import com.grain.entity.page.CatalogIndex;
import com.grain.service.FileService;
import com.grain.service.TCatalogIndexService;
import com.grain.service.TCatalogPicService;
import com.grain.service.TDigitalFeatureService;
import com.grain.service.TPreventprocessService;
import com.grain.service.TProcTypeService;
import com.grain.service.TagService;
import com.grain.util.SpringUtils;

/*
 * 虫螨基础数据审核
 */

@Controller
@RequestMapping("/expert/audit/catalogIndex")
public class ExpertCatelogIndexAuditController extends BaseController{
	
	@Resource(name = "tcatalogIndexServiceImpl")
	TCatalogIndexService catalogIndexService;
	
	@Resource(name = "tagServiceImpl")
	TagService tagService;
	
	@Resource(name = "fileServiceImpl")	//file
	private FileService fileService;
	
	@Resource(name = "tCatalogPicServiceImpl")
	TCatalogPicService tCatalogPicService;
	
	@Resource(name = "tprocTypeServiceImpl") //防治工艺类型
	private TProcTypeService procTypeService;
	
	@Resource(name = "tpreventprocessServiceImpl")//防治工艺表
	TPreventprocessService tPreventProcessService;
	
	@Resource(name = "tdigitalfeatureServiceImpl")//数字特征表
	TDigitalFeatureService tdigitalfeatureService;
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "/expert/insectsCatalogAudit/list";
	}

	@RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
	@ResponseBody
	public Page<CatalogIndex> treeGrid(Pageable pageable,TCatalogIndex queryParm){
		return catalogIndexService.TreeGrid(pageable,queryParm);
	}
	
	/**
	 * 获取数字特征表列表
	*/
	@RequestMapping("/getTDigitalFeatures")
	@ResponseBody
	public Page<TDigitalFeature> getTDigitalFeatures(Pageable pageable,Long id){
		TCatalogIndex catalogIndex = catalogIndexService.find(id);
		TDigitalFeature digitalFeature = new TDigitalFeature();
		digitalFeature.setTCatalogIndex(catalogIndex);
		return tdigitalfeatureService.findPage(pageable,digitalFeature);
	}
	
	/**
	 * 获取防治工艺表列表
	*/
	@RequestMapping("/getTPreventprocesses")
	@ResponseBody
	public List<TPreventprocess> getTPreventprocess(){
		return tPreventProcessService.findAll();
	}
		
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editCatalogIndexEntrance")
	public String edit(Long id, ModelMap model) {
		//model.addAttribute("catalogIndexTree", catalogIndexService.findTree());
		TCatalogIndex catalogIndex = catalogIndexService.find(id);
		if(catalogIndex.getParent()!=null)
			model.addAttribute("parentMc", catalogIndex.getParent().getMc());
		else
			model.addAttribute("parentMc", "顶级 害虫类别");
		model.addAttribute("tags", tagService.findList(Type.insect));
		model.addAttribute("preventprocesses", tPreventProcessService.findAll());
		model.addAttribute("catalogIndex", catalogIndex);
		model.addAttribute("cid", catalogIndex.getId());
		model.addAttribute("picTotal", catalogIndex.getTCatalogPics()==null?0:catalogIndex.getTCatalogPics().size());
		return "/expert/insectsCatalogAudit/edit";
	}
	
	@RequestMapping(value = "/editCatalogIndex")
	@ResponseBody
	public Json editCatalogIndex(TCatalogIndex catalogindex,HttpSession session) {
		Json j= new Json();
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			String expertname = p.getUsername();
			catalogindex.setAuditor(expertname);
			
			Date nowDate = new Date();
			SimpleDateFormat at=new SimpleDateFormat("yyyy-MM-dd");
			String audittime=at.format(nowDate);
			catalogindex.setAudittime(audittime);
			
		try{
			
			catalogIndexService.update(catalogindex,"parent","bm","flm","mc","ywm","zylb","ymc","tz","shxx","fb","wh","keywords","ms","source","image","iconCls","modifer",
					  
					"treePath","grade","isTop","hits","weekHits","monthHits");
			j.setSuccess(true);
			j.setMsg("修改成功！");
			
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		//model.addAttribute("catalogIndexTree", catalogIndexService.findTree());
		return j;
	}
	

}
