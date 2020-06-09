package com.grain.controller.admin;

import java.util.ArrayList;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grain.Message;
import com.grain.entity.Area;
import com.grain.service.AreaService;
import com.grain.service.GraindirectionService;

/**
 * Controller - 地区
 * 
 */
@Controller
@RequestMapping("/admin/area")
public class AreaController extends BaseController {

	@Resource(name = "areaServiceImpl")
	private AreaService areaService;

	@Resource(name = "graindirectionServiceImpl")
	private GraindirectionService gd;
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Long parentId, ModelMap model) {
		model.addAttribute("parent", areaService.find(parentId));
		model.addAttribute("graindirection", gd.findAll());
		return "/admin/area/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Area area, Long parentId, Long grainDirectionid,
			RedirectAttributes redirectAttributes) {
		area.setParent(areaService.find(parentId));
		area.setGd(gd.find(grainDirectionid));
		if (!isValid(area)) {
			return ERROR_VIEW;
		}
		area.setFullName(null);
		area.setTreePath(null);
		area.setChildren(null);
		areaService.save(area);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("area", areaService.find(id));
		model.addAttribute("graindirection", gd.findAll());
		return "/admin/area/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Area area,Long grainDirectionid,
			RedirectAttributes redirectAttributes) {
		if (!isValid(area)) {
			return ERROR_VIEW;
		}
		area.setGd(gd.find(grainDirectionid));
		areaService.update(area, "fullName", "treePath", "parent", "children");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Long parentId, ModelMap model) {
		Area parent = areaService.find(parentId);
		if (parent != null) {
			model.addAttribute("parent", parent);
			model.addAttribute("areas", new ArrayList<Area>(parent.getChildren()));
		} else {
			model.addAttribute("areas", areaService.findRoots());
		}
		return "/admin/area/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long id) {
		Area area=areaService.find(id);
		//有下级地区，不能删除
		Set<Area> children = area.getChildren();
		if (children != null && !children.isEmpty()) {
			return Message.error("admin.area.deleteExistChildren");
		}
		// 对应地区有粮库，不能删除
		if (area!=null && area.getGraindepots()!=null && !area.getGraindepots().isEmpty() ){
			return Message.error("admin.area.deleteExistgraindepot");
		}
		areaService.delete(id);
		return SUCCESS_MESSAGE;
	}

}