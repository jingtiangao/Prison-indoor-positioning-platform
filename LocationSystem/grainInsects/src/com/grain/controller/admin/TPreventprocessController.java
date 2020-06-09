package com.grain.controller.admin;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.TPreventprocess;
import com.grain.entity.TPreventprocessdetach;
import com.grain.entity.TProcType;
import com.grain.entity.page.ProcType;
import com.grain.service.TPreventprocessService;
import com.grain.service.TProcTypeService;

/*
 * 	防治工艺表
 *  
 */
@Controller
@RequestMapping("/admin/process")
public class TPreventprocessController extends BaseController{
	//防治工艺表
	@Resource(name = "tpreventprocessServiceImpl")
	TPreventprocessService tPreventProcessService;
	
	//防治工艺类型表
	@Resource(name = "tprocTypeServiceImpl")
	private TProcTypeService procTypeService;
	
	/**
	 * 检查名称是否唯一
	
	@RequestMapping(value = "/check_proname", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkSn(String previousSn, String sn) {
		if (StringUtils.isEmpty(sn)) {
			return false;
		}
		if (tPreventProcessService.(previousSn, sn)) {
			return true;
		} else {
			return false;
		}
	}
	 */
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/addPreventprocessEntrance", method = RequestMethod.POST)
	public String add( ModelMap model) {
		model.addAttribute("proctypes", procTypeService.findRoots());
		return "/admin/preventprocess/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Json save(TPreventprocess tPreventprocess, String procId) {
		Json j = new Json();
		//TPreventprocessdetach
		for(Iterator<TPreventprocessdetach> iterator = tPreventprocess.getTPreventprocessdetaches().iterator(); iterator.hasNext(); ) {
			TPreventprocessdetach preventProcess = iterator.next();
			if(preventProcess == null) {
				iterator.remove();
			} else {
				preventProcess.setTPreventprocess(tPreventprocess);
			}
		}
		try{
			TProcType proctype = procTypeService.find(procId);
			tPreventprocess.setTProctype(proctype);
			tPreventProcessService.save(tPreventprocess);
			
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
	 * 编辑
	 */
	@RequestMapping(value = "/editPreventprocessEntrance", method = RequestMethod.POST)
	public String edit(String id, ModelMap model) {
		model.addAttribute("preventprocess", tPreventProcessService.find(id));
		return "/admin/preventprocess/edit";
	}
	
	
	/**
	 * 依据sm寻找防治工艺表
	 */
	@RequestMapping(value = "/getPreventprocessDetail", method = RequestMethod.GET)
	public String getDetail(String sm, ModelMap model) {
		model.addAttribute("preventprocess", tPreventProcessService.find(sm));
		return "/admin/preventprocess/view";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(TPreventprocess tPreventprocess, String procId) {
		Json j = new Json();
		//TPreventprocessdetach
		for(Iterator<TPreventprocessdetach> iterator = tPreventprocess.getTPreventprocessdetaches().iterator(); iterator.hasNext(); ) {
			TPreventprocessdetach preventProcess = iterator.next();
			if(preventProcess == null) {
				iterator.remove();
			} else {
				preventProcess.setTPreventprocess(tPreventprocess);
			}
		}
		try{
			TProcType proctype = procTypeService.find(procId);
			tPreventprocess.setTProctype(proctype);
			tPreventProcessService.update(tPreventprocess);
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
		return "/admin/preventprocess/list";
	}
	
	/**
	 * 列表数据
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<TPreventprocess>getDataGrid(Pageable pageable,TPreventprocess queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new TPreventprocess();
		return tPreventProcessService.findPage(pageable, queryParm);
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
	Json delete(String sm) {
		Json j = new Json();
		try{
			tPreventProcessService.delete(sm);
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
