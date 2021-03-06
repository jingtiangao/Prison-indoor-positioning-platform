package com.grain.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TInsectsIndex;
import com.grain.entity.TInsectsSpecification;
import com.grain.entity.page.CatalogIndex;
import com.grain.entity.user.Admin;
import com.grain.service.TCatalogIndexService;
import com.grain.service.TInsectsIndexService;
import com.grain.service.user.AdminService;

/**
 * Controller - 索引表
 * 
 */

@Controller
@RequestMapping("/admin/insectsIndex")
public class InsectsIndexController {	
	
	@Resource(name= "TInsectsIndexServiceImpl")
	TInsectsIndexService insectsindexService;
	
	@Resource(name="adminServiceImpl")
	AdminService admin;
	
	@Resource(name = "tcatalogIndexServiceImpl")
	TCatalogIndexService catalogIndexService;
	
	//the insects index list page
		@RequestMapping("/list")
		public String list() {
			return "/admin/insectsIndex/list";
		}
		
		
	@RequestMapping("/getList")
	@ResponseBody
	public Page<TInsectsIndex> getDataGrid(Pageable pageable,TInsectsIndex queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new TInsectsIndex();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			admin.find(p.getUsername());
		}
		Page<TInsectsIndex> map1 = insectsindexService.findPage(pageable);
		return map1;
		
	}

	
	@RequestMapping("/addTInsectsIndexEntrance")
	public String addTInsectsIndexEntrance(HttpSession session,ModelMap model) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			Admin u = admin.find(p.getUsername());
			
		}else
			throw new Exception("session is losed!");
		return "admin/insectsIndex/add";
	}
	
	@RequestMapping("/addInsectsIndex")
	@ResponseBody
	public Json addTInsectsIndex(HttpServletRequest request,TInsectsIndex insectsIndex, @RequestParam(value="jsjg") Long[] jsjg){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系

		int i = 0;
		// TInsectsSpecification
		for (Iterator <TInsectsSpecification> specificationiterator = insectsIndex.getTInsectsSpecifications().iterator(); specificationiterator.hasNext(); ){
			
			TInsectsSpecification insectsSpec = specificationiterator.next();
			
			if (insectsSpec == null) {
				specificationiterator.remove();
			} else {
				insectsSpec.setTInsectsIndex(insectsIndex);
				
				insectsSpec.setTCatalogIndex(catalogIndexService.find(jsjg[i++]));

			}
			
		}
		insectsIndex.setPassaudit(false);
		try {
			insectsindexService.save(insectsIndex);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editTInsectsIndexEntrance")
	public String editTInsectsIndexEntrance(HttpSession session,ModelMap model,@RequestParam(value="id")Long id) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			Admin u = admin.find(p.getUsername());
			//
			TInsectsIndex insectsIndex = insectsindexService.find(id);

			//获取检索表
			model.addAttribute("insectsIndex", insectsIndex);
			//获取检索表下特征表数目
			int indexSpecification = insectsIndex.getTInsectsSpecifications().size();
			model.addAttribute("indexSpecification", indexSpecification);
			List<CatalogIndex> l = new ArrayList<CatalogIndex>();
			for (int i = 0; i < indexSpecification; i++) {
				CatalogIndex r = new CatalogIndex();
				TInsectsSpecification tis = insectsIndex.getTInsectsSpecifications().get(i);
				TCatalogIndex x = new TCatalogIndex();
				if(tis.getTCatalogIndex()!=null){
					x = tis.getTCatalogIndex();
				}
//				BeanUtils.copyProperties(tis.getTCatalogIndex(), r);
				BeanUtils.copyProperties(x, r);
				l.add(r);
			}
			
			model.addAttribute("jsjglist", l);
			
			
//			//获取检索表下特征表对应的图片数目
//			int[] aArray = new int[insectsSpecifications];  
//			for(int i = 0; i < insectsSpecifications;i++){
//				
//				aArray[i] = insectsIndex.getTInsectsSpecifications().get(i).getTInsectsSpecPics().size();
//				
//			}
//			model.addAttribute("insectsSpecPic", aArray);
			
		}else
			throw new Exception("session is losed!");
		return "admin/insectsIndex/edit";
	}
	
	@RequestMapping("/editInsectsIndex")
	@ResponseBody
	public Json editTInsectsIndex(HttpServletRequest request,TInsectsIndex insectsIndex,  @RequestParam(value="jsjg") Long[] jsjg){
		Json j = new Json();

		int i = 0;
		//TInsectsSpecification
		for (Iterator<TInsectsSpecification> specificationiterator = insectsIndex.getTInsectsSpecifications().iterator(); specificationiterator.hasNext(); ){
			TInsectsSpecification insectsSpec = specificationiterator.next();
			if (insectsSpec==null){
				specificationiterator.remove();
			}else{
				insectsSpec.setTInsectsIndex(insectsIndex);
//				insectsSpec.setTCatalogIndex(null);
				insectsSpec.setTCatalogIndex(catalogIndexService.find(jsjg[i++]));
				
			}
			
		}

		try{
			insectsindexService.update(insectsIndex);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;

	}
	
	@RequestMapping("/deleteInsectsIndex")
	@ResponseBody
	public Json deleteReportRec(Long id){
		Json j = new Json();
		
		try{
			insectsindexService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/getCatalogIndexName")
	@ResponseBody
	public Map<String,Object> getCatalogIndexName(Pageable pageable,TCatalogIndex queryParm,
			@RequestParam(value="q") String term, @RequestParam(value="page") int pg,HttpSession session) throws Exception {
		pageable.setPage(pg);
		pageable.setRows(1000);
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p == null) throw new Exception("session is losed!");
		Page<CatalogIndex> page = catalogIndexService.TreeGrid(pageable, queryParm);
		List<CatalogIndex> result = page.getRows();
		int size = result.size();
		for (int i = size-1; i >= 0; i--) {
			CatalogIndex c = result.get(i);
			if ((!c.getMc().contains(term)) || c == null)
				result.remove(i);
		}
		size = result.size();
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < size; i++) {
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("id", result.get(i).getId());
			m.put("text", result.get(i).getMc());
			l.add(m);
		}
		Map<String,Object> r = new HashMap<String,Object>();
		r.put("rows", l);
		r.put("total_count", l.size());
		return r;
/*		int size = result.size();
		for (int i = 0; i < size; i++) {
			Map<String,Object> m = new HashMap<String,Object>();
			TCatalogIndex tci = result.get(i);
			m.put("id",tci.)
		}*/
	}
	
}
