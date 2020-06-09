package com.grain.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.service.FileService;
import com.grain.service.TCatalogIndexService;
import com.grain.service.TCatalogPicService;
import com.grain.service.TDigitalFeatureService;
import com.grain.service.TInsectsSpecificationService;
import com.grain.service.TPreventprocessService;
import com.grain.service.TProcTypeService;
import com.grain.service.TagService;
import com.grain.util.SpringUtils;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.FileInfo.FileType;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TCatalogPic;
import com.grain.entity.TDigitalFeature;
import com.grain.entity.TInsectsSpecification;
import com.grain.entity.TPreventprocess;
import com.grain.entity.Tag;
import com.grain.entity.Tag.Type;
import com.grain.entity.page.CatalogIndex;

/**
 * Controller - 知识库 害虫类型
 * 
 */
@Controller
@RequestMapping("/admin/catalogIndex")
public class CatalogIndexController extends BaseController {

	
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
	
	@Resource(name = "tInsectsSpecificationServiceImpl")//数字特征表
	TInsectsSpecificationService tInsectsSpecificationService;
	
	/**
	 * 检查编号是否唯一
	 */
	@RequestMapping(value = "/check_bm", method = RequestMethod.POST)
	public @ResponseBody
	boolean checkBm(String bm) {
		if (StringUtils.isEmpty(bm)) {
			return false;
		}
		List<TCatalogIndex> list = catalogIndexService.findAll();
		for(Iterator<TCatalogIndex> iterator = list.iterator(); iterator.hasNext(); ) {
			TCatalogIndex item = iterator.next();
			if(!catalogIndexService.mcUnique(item.getBm(), bm))
				return false;
		}
		return true;
	}
	

	/**
	 * 检查中文名是否唯一
	 
	@RequestMapping(value = "/check_mc", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkMc(String previousSn, String sn) {
		if (StringUtils.isEmpty(sn)) {
			return false;
		}
		if (catalogIndexService.mcUnique(previousSn, sn)) {
			return true;
		} else {
			return false;
		}
	}
	*/
	
	/**
	 * 检查中文名是否唯一
	 */
	@RequestMapping(value = "/check_mc", method = RequestMethod.POST)
	public @ResponseBody
	boolean checkMc(String mc) {
		if (StringUtils.isEmpty(mc)) {
			return false;
		}
		List<TCatalogIndex> list = catalogIndexService.findAll();
		for(Iterator<TCatalogIndex> iterator = list.iterator(); iterator.hasNext(); ) {
			TCatalogIndex item = iterator.next();
			if(!catalogIndexService.mcUnique(item.getMc(), mc))
				return false;
		}
		return true;
	}
	
	
	/**
	 * 检查英文名是否唯一
	 */
	@RequestMapping(value = "/check_ywm", method = RequestMethod.POST)
	public @ResponseBody
	boolean checkYwm(String ywm) {
		if (StringUtils.isEmpty(ywm)) {
			return false;
		}
		List<TCatalogIndex> list = catalogIndexService.findAll();
		for(Iterator<TCatalogIndex> iterator = list.iterator(); iterator.hasNext(); ) {
			TCatalogIndex item = iterator.next();
			if(!catalogIndexService.mcUnique(item.getYwm(), ywm))
				return false;
		}
		return true;
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/addCatalogIndexEntrance")
	public String addCatalogIndexEntrance(Long id,String mc,ModelMap model) {
		//model.addAttribute("catalogIndexTree", catalogIndexService.findTree());
		//System.out.print(tagService.findList(Type.insect));
		model.addAttribute("tags", tagService.findList(Type.insect));
		model.addAttribute("preventprocesses", tPreventProcessService.findAll());
		if(id==null){
			model.addAttribute("parentId", "0");
			model.addAttribute("parentMc", "顶级 害虫类别");
		}else{
			model.addAttribute("parentId", id);
			model.addAttribute("parentMc", mc);
		}
		return "/admin/catalogIndex/addnew";
	}
	
	@RequestMapping(value = "/addCatalogIndex")
	@ResponseBody
	public Json addCatalogIndex(TCatalogIndex catalogindex, Long parentId, Long[] tagIds, String[] preventprocessIds, Long[] digitalFeatureIds) {
		Json j= new Json();
		
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		//TDigitalFeature
		for(Iterator<TDigitalFeature> iterator = catalogindex.getTDigitalFeatures().iterator(); iterator.hasNext(); ) {
			TDigitalFeature digitalFeature = iterator.next();
			if(digitalFeature == null) {
				iterator.remove();
			} else {
				digitalFeature.setTCatalogIndex(catalogindex);
			}
		}
		
//		//TPreventProcess
//		for(Iterator<TPreventprocess> iterator = catalogindex.getTPreventprocesses().iterator(); iterator.hasNext(); ) {
//			TPreventprocess digitalFeature = iterator.next();
//			if(digitalFeature == null) {
//				iterator.remove();
//			} else {
//				digitalFeature.setTCatalogIndex(catalogindex);
//			}
//		}
					
		//保存图片TCatalogPic
		for (Iterator<TCatalogPic> iterator = catalogindex.getTCatalogPics().iterator();iterator.hasNext();){
			TCatalogPic catatypePic = iterator.next();
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
		
		for (TCatalogPic pic : catalogindex.getTCatalogPics()){		// deal with catalog picture
			tCatalogPicService.build(pic);
		}
			
		try{
			//初始化点击数
			catalogindex.setHits(0L);
			catalogindex.setWeekHits(0L);
			catalogindex.setMonthHits(0L);
			
			if(parentId==0 || parentId == null){
				catalogindex.setTreePath("0");
				catalogindex.setGrade(1);
			}else{
				catalogindex.setTreePath(Long.toString(parentId));
				catalogindex.setGrade(catalogIndexService.find(parentId).getGrade()+1);
				
				
				//子节点添加父母
				catalogindex.setParent(catalogIndexService.find(parentId));
			
				//父节点添加孩子
				Set<TCatalogIndex> childCatalogIndex = catalogIndexService.find(parentId).getChildren();
				childCatalogIndex.add(catalogindex);
			}
			
			
			
			//设置多对多关系的标签
			catalogindex.setTags(new HashSet<Tag>(tagService.findList(tagIds)));

			catalogindex.settPreventprocesses(new HashSet<TPreventprocess>(tPreventProcessService.findList(preventprocessIds)));
			
			
			List<TDigitalFeature> digitalFeatures = tdigitalfeatureService.findList(digitalFeatureIds);
			//先给孩子设置爸爸
			//TDigitalFeature
			for(Iterator<TDigitalFeature> iterator = digitalFeatures.iterator(); iterator.hasNext(); ) {
				TDigitalFeature digitalFeature = iterator.next();
				if(digitalFeature == null) {
					iterator.remove();
				} else {
					digitalFeature.setTCatalogIndex(catalogindex);
				}
			}
			//再给爸爸设置孩子(嗯还不知道这个是不是必要的)
			catalogindex.setTDigitalFeatures(digitalFeatures);
			//catalogIndexService.update(catalogindex);
			catalogindex.setPassaudit(false);
			catalogIndexService.save(catalogindex);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		//model.addAttribute("catalogIndexTree", catalogIndexService.findTree());
		return j;
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
	 * 分类表 treegrid 异步加载
	 */
	@RequestMapping(value = "/combotextList")
	@ResponseBody
	public Map<String,Object> list(Pageable pageable, Long id, String bm, String mc) {
		int pg = pageable.getPage();
		int rows = pageable.getRows();
		int total=0;
		Map<String,Object> mr = new HashMap<String,Object> ();
		List<CatalogIndex> lr = new ArrayList<CatalogIndex> ();
		List<TCatalogIndex> l = new ArrayList<TCatalogIndex> ();
		if(id == null &&bm == null && mc == null){
				List<TCatalogIndex> temp =new ArrayList<TCatalogIndex>( catalogIndexService.findRoots());
				l = temp.subList((pg-1)*rows, pg*rows>temp.size()?temp.size():pg*rows);
				total = temp.size();
		}else if(id!= null){
			l = catalogIndexService.findDirectChildren(catalogIndexService.find(id));
			/*
			TCatalogIndex queryParm = new TCatalogIndex();
			queryParm.setParent(catalogIndexService.find(id));
			Page<TCatalogIndex> t = catalogIndexService.findPage(pageable, queryParm);
			l = t.getRows();
			*/
		}else if(bm!=null){
			TCatalogIndex queryParm = new TCatalogIndex();
			queryParm.setBm(bm);
			Page<TCatalogIndex> t = catalogIndexService.findPage(pageable, queryParm);
			l = t.getRows();
		}else if(mc!=null){
			TCatalogIndex queryParm = new TCatalogIndex();
			queryParm.setMc(mc);
			Page<TCatalogIndex> t = catalogIndexService.findPage(pageable, queryParm);
			l = t.getRows();
		}
		
			for (TCatalogIndex tc: l){
				CatalogIndex r = new CatalogIndex();
				BeanUtils.copyProperties(tc, r);
				//if(bm==null && mc ==null){
					if (tc.getChildren()!=null && tc.getChildren().size() >0)
						r.setState("closed");
					else
						r.setState("");
					r.set_parentId(id!=null?id:null);
				//}
				lr.add(r);
			}
		mr.put("total", total==0?id==null?lr.size():bm==null?(mc==null?0:lr.size()):lr.size():total);
		mr.put("rows", lr);
		return mr;
	}
	
	/**
	 * 分类表 ztree 异步加载
	 */
	@RequestMapping(value = "/ztreeList")
	@ResponseBody
	public List<CatalogIndex> ztreelist(Pageable pageable, Long id) {
		List<CatalogIndex> lr = new ArrayList<CatalogIndex>();
		List<TCatalogIndex> l = null;
		if(id == null){
			l = new ArrayList<TCatalogIndex>( catalogIndexService.findRoots());
		}else{
			l = new ArrayList<TCatalogIndex>( catalogIndexService.findDirectChildren(catalogIndexService.find(id)));
			/*
			TCatalogIndex queryParm = new TCatalogIndex();
			queryParm.setParent(catalogIndexService.find(id));
			//szy add 最好是把 所有的数据返回，因为你调用的函数是按分页返回的
			pageable.setRows(1000);
			Page<TCatalogIndex> t = catalogIndexService.findPage(pageable, queryParm);
			l = t.getRows();
			*/
		}
		for (TCatalogIndex tc: l){
			CatalogIndex r = new CatalogIndex();
			BeanUtils.copyProperties(tc, r);
			if (tc.getChildren()!=null && tc.getChildren().size() >0){
				r.setIsParent(true);
			}
			else{
				r.setIsParent(false);
			}
			r.set_parentId(id!=null?id:null);
			lr.add(r);
		}
		return lr;
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
		return "/admin/catalogIndex/edit";
	}
	
	@RequestMapping(value = "/editCatalogIndex")
	@ResponseBody
	public Json editCatalogIndex(TCatalogIndex catalogindex, Long[] tagIds, String[] preventprocessIds, Long[] digitalFeatureIds, Long parentId) {
		Json j= new Json();
		
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		//TDigitalFeature
		for(Iterator<TDigitalFeature> iterator = catalogindex.getTDigitalFeatures().iterator(); iterator.hasNext(); ) {
			TDigitalFeature digitalFeature = iterator.next();
			if(digitalFeature == null) {
				iterator.remove();
			} else {
				digitalFeature.setTCatalogIndex(catalogindex);
			}
		}
		
		//TPreventProcess
//		for(Iterator<TPreventprocess> iterator = catalogindex.getTPreventprocesses().iterator(); iterator.hasNext(); ) {
//			TPreventprocess digitalFeature = iterator.next();
//			if(digitalFeature == null) {
//				iterator.remove();
//			} else {
//				digitalFeature.setTCatalogIndex(catalogindex);
//			}
//		}
					
		//保存图片TCatalogPic
		for (Iterator<TCatalogPic> iterator = catalogindex.getTCatalogPics().iterator();iterator.hasNext();){
			TCatalogPic catatypePic = iterator.next();
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
		
		for (TCatalogPic pic : catalogindex.getTCatalogPics()){		// deal with catalog picture
			tCatalogPicService.build(pic);
		}
			
		TCatalogIndex catalogIndex = catalogIndexService.find(catalogindex.getId());
		try{
			
			//设置多对多关系的标签
			catalogindex.setTags(new HashSet<Tag>(tagService.findList(tagIds)));

			catalogindex.settPreventprocesses(new HashSet<TPreventprocess>(tPreventProcessService.findList(preventprocessIds)));
			
			List<TDigitalFeature> digitalFeatures = tdigitalfeatureService.findList(digitalFeatureIds);
			//先给孩子设置爸爸
			//TDigitalFeature
			for(Iterator<TDigitalFeature> iterator = digitalFeatures.iterator(); iterator.hasNext(); ) {
				TDigitalFeature digitalFeature = iterator.next();
				if(digitalFeature == null) {
					iterator.remove();
				} else {
					digitalFeature.setTCatalogIndex(catalogindex);
				}
			}
			//再给爸爸设置孩子(嗯还不知道这个是不是必要的)
			catalogindex.setTDigitalFeatures(digitalFeatures);
			
			if(parentId !=null){
				catalogindex.setParent(catalogIndexService.find(parentId));
				catalogIndexService.update(catalogindex,"treePath","grade","isTop","hits","weekHits","monthHits","auditor","audittime","passaudit","auditadvice");
			}else
				catalogIndexService.update(catalogindex,"parent","treePath","grade","isTop","hits","weekHits","monthHits","auditor","audittime","passaudit","auditadvice");
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
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "/admin/catalogIndex/list";
	}

	@RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
	@ResponseBody
	public Page<CatalogIndex> treeGrid(Pageable pageable,TCatalogIndex queryParm){
		return catalogIndexService.TreeGrid(pageable,queryParm);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Json delete(Long id) {
		Json j=new Json();
		try{
			TCatalogIndex catalogindex = catalogIndexService.find(id);
			
			// 还有检索表的数据呢
			//TInsectsSpecification检索表的特征项
//			for(Iterator<TInsectsSpecification> iterator = catalogindex.getTInsectsSpecifications().iterator(); iterator.hasNext(); ) {
//				TInsectsSpecification insectsSpecification = iterator.next();
//				tInsectsSpecificationService.delete(insectsSpecification.getId());
//			}
			Iterator<TInsectsSpecification> iterator1 = catalogindex.getTInsectsSpecifications().iterator();
			if(iterator1.hasNext())
			{
				j.setSuccess(false);
				j.setMsg("请先删除与其相关联的特征！");
			}
			else{
			//TDigitalFeature
				for(Iterator<TDigitalFeature> iterator = catalogindex.getTDigitalFeatures().iterator(); iterator.hasNext(); ) {
					TDigitalFeature digitalFeature = iterator.next();
					tdigitalfeatureService.delete(digitalFeature.getId());
				}
			

				catalogIndexService.delete(id);
				j.setSuccess(true);
				j.setMsg("删除成功！");
			}
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}

}
