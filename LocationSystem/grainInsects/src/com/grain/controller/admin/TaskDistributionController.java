package com.grain.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Filter;
import com.grain.Filter.Operator;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TInsectsIndex;
import com.grain.entity.TaskDistribution;
import com.grain.entity.page.CatalogIndex;
import com.grain.entity.user.Expert;
import com.grain.service.TCatalogIndexService;
import com.grain.service.TInsectsIndexService;
import com.grain.service.TaskDistributionService;
import com.grain.service.user.ExpertService;

/**
 * 管理员任务分配控制器
 * @author Mark
 *
 */
@Controller
@RequestMapping("/admin/taskDistribution")
public class TaskDistributionController {
	
	@Resource(name="expertServiceImpl")
	private ExpertService expertService;
	
	@Resource(name="taskDistributionServiceImpl")
	private TaskDistributionService taskDistributionService;
	
	@Resource(name="tcatalogIndexServiceImpl")
	private TCatalogIndexService catalogIndexService;
	
	@Resource(name="TInsectsIndexServiceImpl")
	private TInsectsIndexService insectsIndexService;
	
	@RequestMapping(value="list")
	public String taskDistributelist(){
		return "/admin/taskDistribution/list";
	}
	/**
	 * 得到所有专家用户
	 * @return
	 */
	@RequestMapping(value="getExperts")
	@ResponseBody
	public List<Map<String, String>> getExperts(){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String, String> map = null;
		List<Expert> experts = expertService.findAll();
		for(Expert expert : experts){
			if(expert.getUsername() != null && !"".equals(expert.getUsername().trim())){
				map = new HashMap<String, String>();
				map.put("value", expert.getUsername());
				map.put("text", expert.getUsername());
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * 得到所有未分配的虫种分类信息列表
	 * @return
	 */
	@RequestMapping(value="getCatalogIndexs")
	@ResponseBody
	public Map<String,Object> getCatalogIndexs(Pageable pageable, Long id, String bm, String mc){
		
		/*Filter filter = new Filter("taskDistributions", Operator.eq, null);
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(filter);
		pageable.setFilters(filters);
		return catalogIndexService.findPage(pageable, queryParam);*/
		
		int pg = pageable.getPage();
		int rows = pageable.getRows();
		int total=0;
		Map<String,Object> mr = new HashMap<String,Object> ();
		List<CatalogIndex> lr = new ArrayList<CatalogIndex> ();
		List<TCatalogIndex> l = new ArrayList<TCatalogIndex> ();
		if(id == null &&bm == null && mc == null){
			List<TCatalogIndex> temp =new ArrayList<TCatalogIndex>( catalogIndexService.findRoots());
			
			for(TCatalogIndex catalogIndex : temp){  // 过滤已分配任务
				if(catalogIndex.getTaskDistributions() != null && catalogIndex.getTaskDistributions().size() > 0){
					temp.remove(catalogIndex);
				}
			}
			l = temp.subList((pg-1)*rows, pg*rows>temp.size()?temp.size():pg*rows);
			total = temp.size();
		}else if(id!= null){
			l = catalogIndexService.findDirectChildren(catalogIndexService.find(id));
			for(TCatalogIndex catalogIndex : l){  // 过滤已分配任务
				if(catalogIndex.getTaskDistributions() != null && catalogIndex.getTaskDistributions().size() > 0){
					l.remove(catalogIndex);
				}
			}
			
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
			for(TCatalogIndex catalogIndex : l){  // 过滤已分配任务
				if(catalogIndex.getTaskDistributions() != null && catalogIndex.getTaskDistributions().size() > 0){
					l.remove(catalogIndex);
				}
			}
		}else if(mc!=null){
			TCatalogIndex queryParm = new TCatalogIndex();
			queryParm.setMc(mc);
			Page<TCatalogIndex> t = catalogIndexService.findPage(pageable, queryParm);
			l = t.getRows();
			for(TCatalogIndex catalogIndex : l){  // 过滤已分配任务
				if(catalogIndex.getTaskDistributions() != null && catalogIndex.getTaskDistributions().size() > 0){
					l.remove(catalogIndex);
				}
			}
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
		mr.put("page", pg);
		return mr;
	}
	
	/**
	 * 得到所有未分配的检索类别信息列表
	 * @return
	 */
	@RequestMapping(value="getInsectsIndexs")
	@ResponseBody
	public Page<TInsectsIndex> getInsectsIndexs(Pageable pageable, TInsectsIndex queryParam){
		Filter filter = new Filter();
		filter.setOperator(Operator.isNull);
		filter.setProperty("taskDistributions");
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(filter);
		pageable.setFilters(filters);
		return insectsIndexService.findPage(pageable, queryParam);
	}
	
	/**
	 * 分配任务
	 * @param catalogIndexs
	 * @param insectsIndexs
	 * @param deadLine
	 * @param expertName
	 * @return
	 */
	@RequestMapping(value="distributeTasks")
	@ResponseBody
	public Json distributeTasks(Long[] catalogIndexIds, Long[] insectsIndexIds, String deadLine, String expertName){
		Json json = new Json();
		if((catalogIndexIds == null || catalogIndexIds.length == 0) 
				&& (insectsIndexIds == null || insectsIndexIds.length ==0)){
			json.setSuccess(false);
			return json;
		}
		if(expertName == null || !"".equals(expertName.trim())){
			json.setSuccess(false);
			return json;
		}
		if(deadLine == null || !"".equals(deadLine.trim())){
			json.setSuccess(false);
			return json;
		}
		if(catalogIndexIds.length>0){
			TaskDistribution taskDistribution = new TaskDistribution();
			taskDistribution.setExpert(expertService.findByUsername(expertName));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			taskDistribution.setDistributeTime(new Date());  // 暂时设置为date
			try {
				taskDistribution.setDeadLine(sdf.parse(deadLine));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			List<TCatalogIndex> catalogIndexs = new ArrayList<TCatalogIndex>();
			for(int i=0;i<catalogIndexIds.length;i++){
				if(catalogIndexIds[i] == null){
					continue;
				}
				catalogIndexs.add(catalogIndexService.find(catalogIndexIds[i]));
			}
			taskDistribution.setCatalogIndexs(catalogIndexs);
			taskDistribution.setTaskType(0);  //0：虫种分类
		}
		
		if(insectsIndexIds.length>0){
			TaskDistribution taskDistribution = new TaskDistribution();
			taskDistribution.setExpert(expertService.findByUsername(expertName));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			taskDistribution.setDistributeTime(new Date());  // 暂时设置为date
			try {
				taskDistribution.setDeadLine(sdf.parse(deadLine));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Set<TInsectsIndex> insectsIndexs = new HashSet<TInsectsIndex>();
			for(int i=0;i<insectsIndexIds.length;i++){
				if(insectsIndexIds[i] == null){
					continue;
				}
				insectsIndexs.add(insectsIndexService.find(insectsIndexIds[i]));
			}
			taskDistribution.setInsectsIndexs(insectsIndexs);
			taskDistribution.setTaskType(1); //1：检索表
		}
		json.setSuccess(true);
		return json;
	}
	
	@RequestMapping(value="tt")
	@ResponseBody
	public String test(Long[] ids, String date){
		System.out.println(date);
		return "success";
	}
}
