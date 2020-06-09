package com.grain.controller.depoter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.grain.controller.admin.BaseController;
import com.grain.entity.Grainbin;
import com.grain.entity.InfoReport;
import com.grain.entity.TProcType;
import com.grain.entity.page.ProcType;
import com.grain.entity.user.DepotUser;
import com.grain.service.InfoReportService;
import com.grain.service.TProcTypeService;
import com.grain.service.user.DepotUserService;


/*
 * 	粮仓- 防治工艺上报表
 *  
 */
@Controller
@RequestMapping("/depoter/inforeport")
public class InfoReportController extends BaseController{

		//防治工艺表
		@Resource(name = "infoReportServiceImpl")
		InfoReportService infoReportService;
		
		//防治工艺类型表
		@Resource(name = "tprocTypeServiceImpl")
		private TProcTypeService procTypeService;
		
		@Resource(name="depotUserServiceImpl")
		private DepotUserService depotuserSrv;//用户
		
		
		/**
		 * 添加
		 */
		@RequestMapping(value = "/addInfoReportEntrance")
		public String add( ModelMap model) {
			model.addAttribute("proctypes", procTypeService.findRoots());
			return "/depoter/preventprocess/add";
		}
		
		/**
		 * 粮仓名称列表
		 */
		@RequestMapping("/grainBins")
		@ResponseBody
		public List<Map<String,Object>> grainBins(HttpSession session) {
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			List<Map<String,Object>> bCountList = new ArrayList<Map<String,Object>>();//粮仓统计
			if (p!=null){
				DepotUser u = depotuserSrv.find(p.getUsername());
				Set<Grainbin> grainbins = u.getGrainbins();
			
				
				for(Grainbin grainbin : grainbins) {
					Map<String,Object> details = new HashMap<String,Object>();
					String lcbm = grainbin.getLcbm();
					details.put("lcbm", lcbm);
					bCountList.add(details);
				}
			}
			return bCountList;
		}

		/**
		 * 保存
		 */
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		@ResponseBody
		public Json save(HttpSession session, InfoReport tPreventprocess, String procId) {
			Json j = new Json();
			DepotUser u = new DepotUser();
			
			try{
				Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
				if(p!=null)
					u = depotuserSrv.find(p.getUsername());
				TProcType proctype = procTypeService.find(procId);
				tPreventprocess.setTProctype(proctype);
				tPreventprocess.setDepotuser(u);
				infoReportService.save(tPreventprocess);
				
				j.setSuccess(true);
				j.setMsg("添加成功！");
			}catch(Exception e){
				j.setSuccess(false);
				j.setMsg(e.getMessage());
				e.printStackTrace();
			}
			return j;
		}
		
		/**
		 * 依据id寻找防治工艺表
		 */
		@RequestMapping(value = "/getInfoReportDetail")
		public String getDetail(ModelMap model, Long id) {
			InfoReport r = infoReportService.find(id);
			model.addAttribute("inforeport", r);
			model.addAttribute("proctypes", procTypeService.findRoots());
			return "/depoter/preventprocess/edit";
		}

		/**
		 * 更新
		 */
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		@ResponseBody
		public Json update(InfoReport tPreventprocess, String procId) {
			Json j = new Json();
			
			try{
				TProcType proctype = procTypeService.find(procId);
				tPreventprocess.setTProctype(proctype);
				infoReportService.update(tPreventprocess,"depotuser");
				j.setSuccess(true);
				j.setMsg("更新成功！");
			}catch(Exception e){
				j.setSuccess(false);
				j.setMsg(e.getMessage());
				e.printStackTrace();
			}
			return j;
		}
		
		/**
		 * 列表
		 */
		@RequestMapping(value = "/list" )
		public String list() {
			return "/depoter/preventprocess/list";
		}
		
		/**
		 * 列表数据  获取当前用户提交的
		 */
		@RequestMapping("/dataGrid")
		@ResponseBody
		public Map<String,Object> getDataGrid(Pageable pageable, 
				HttpSession session)throws Exception
		{
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			int pg = pageable.getPage();
			int rows = pageable.getRows();
			int total=0;
			Map<String,Object> mr = new HashMap<String,Object> ();
			List <InfoReport> temp = new ArrayList<InfoReport> ();
			List <InfoReport> l = new ArrayList<InfoReport> ();
			if(p!=null){
				temp = infoReportService.findInfoReportWithUsername(p.getUsername());
				l = temp.subList((pg-1)*rows, pg*rows>temp.size()?temp.size():pg*rows);
				total = temp.size();
				
			}else throw new Exception("session is losed!");
			mr.put("total", total);
			mr.put("rows", l);
			return mr;
		}
		
		/**
		 * 防治类型列表  : 每次取子目录条数最大为100 
		 */
		@RequestMapping(value = "/combotextList")
		@ResponseBody
		public List<ProcType> list(Pageable pageable, String sm) {
			pageable.setPage(1);
			pageable.setRows(100);
			Page<ProcType> procList = new Page<ProcType> ();
			TProcType queryParm = new TProcType();
			if(sm == null){
				queryParm.setParent(null);
				procList = procTypeService.TreeGrid(pageable, queryParm);	
			}else{
				queryParm.setParent(procTypeService.find(sm));
				procList = procTypeService.TreeGrid(pageable, queryParm);
			}
			return procList.getRows();
		}
		
		/**
		 * 删除
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public @ResponseBody
		Json delete(Long id) {
			Json j = new Json();
			try{
				infoReportService.delete(id);
				j.setSuccess(true);
				j.setMsg("删除成功！");
			}catch(Exception e){
				j.setSuccess(false);
				j.setMsg(e.getMessage());
				e.printStackTrace();
			}
			return j;
		}
}
