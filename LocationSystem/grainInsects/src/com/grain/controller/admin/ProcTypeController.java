package com.grain.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grain.Message;
import com.grain.entity.TProcType;
import com.grain.service.TProcTypeService;

/**
 * Controller - 防治工艺类型
 * 
 */
@Controller
@RequestMapping("/admin/proctype")
public class ProcTypeController extends BaseController {
	
	@Resource(name = "tprocTypeServiceImpl")
	private TProcTypeService procTypeService;

	/**
	 * 检查编号是否唯一
	 */
	@RequestMapping(value = "/check_sn", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkSn(String previousSn, String sn) {
		if (StringUtils.isEmpty(sn)) {
			return false;
		}
		if (procTypeService.snUnique(previousSn, sn)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(String parentId, ModelMap model) {
		model.addAttribute("parent", procTypeService.find(parentId));
		return "/admin/proctype/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TProcType proctype, String parentId, Long grainDirectionid,
			RedirectAttributes redirectAttributes) {
		proctype.setParent(procTypeService.find(parentId));
		if (!isValid(proctype)) {
			return ERROR_VIEW;
		}
		proctype.setFullName(null);
		proctype.setTreePath(null);
		proctype.setChildren(null);
		procTypeService.save(proctype);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, ModelMap model) {
		model.addAttribute("proctype", procTypeService.find(id));
		return "/admin/proctype/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(TProcType proctype,Long grainDirectionid,
			RedirectAttributes redirectAttributes) {
		if (!isValid(proctype)) {
			return ERROR_VIEW;
		}
		procTypeService.update(proctype, "fullName", "treePath", "parent", "children");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list";
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(String parentId, ModelMap model) {
		TProcType parent = procTypeService.find(parentId);
		if (parent != null) {
			model.addAttribute("parent", parent);
			model.addAttribute("proctypes", new ArrayList<TProcType>(parent.getChildren()));
		} else {
			model.addAttribute("proctypes", procTypeService.findRoots());
		}
		return "/admin/proctype/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(String sm) {
		TProcType proctype=procTypeService.find(sm);
		//有下级地区，不能删除
		List<TProcType> children = proctype.getChildren();
		
		if (children != null && !children.isEmpty()) {
			return Message.error("admin.proctype.deleteExistChildren");
		}
		
		procTypeService.delete(sm);
		return SUCCESS_MESSAGE;
	}
}
