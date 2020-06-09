package com.grain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.grain.Page;
import com.grain.Pageable;
import com.grain.ResourceNotFoundException;
import com.grain.entity.Attribute;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TCatalogIndex.OrderType;
import com.grain.entity.Tag;
import com.grain.service.SearchService;
import com.grain.service.TCatalogIndexService;
import com.grain.service.TagService;

@Controller("insectCategoryController")
@RequestMapping("/catalogIndex")
public class InsectCategoryController extends BaseController{
	
	@Resource(name = "tcatalogIndexServiceImpl")
	private TCatalogIndexService catalogIndexService;
	@Resource(name = "tagServiceImpl")
	private TagService tagService;
	@Resource(name="searchServiceImpl")
	private SearchService searchService;
	
	
	/**
	 * 主要虫种类别列表
	 * 根据重要类别名catalogIndexName(zylb)，进行查询
	 */
//	@RequestMapping(value = "/mainList/{catalogIndexName}", method = RequestMethod.GET)
//	public String listMain(@PathVariable String catalogIndexName, Long promotionId, Long[] tagIds, OrderType orderType, Integer pageNumber, Integer pageSize, HttpServletRequest request, ModelMap model) {
//		try {
//			catalogIndexName = new String(catalogIndexName.getBytes("ISO-8859-1"), "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		TCatalogIndex insectCategory = catalogIndexService.findByCatalogIndexName(catalogIndexName);
//		if (insectCategory == null) {
//			throw new ResourceNotFoundException();
//		}
//		List<Tag> tags = tagService.findList(tagIds);
//		Map<Attribute, String> attributeValue = new HashMap<Attribute, String>();
//		if (insectCategory != null) {
//			Set<Attribute> attributes = insectCategory.getAttributes();
//			for (Attribute attribute : attributes) {
//				String value = request.getParameter("attribute_" + attribute.getId());
//				if (StringUtils.isNotEmpty(value) && attribute.getOptions().contains(value)) {
//					attributeValue.put(attribute, value);
//				}
//			}
//		}
//		Pageable pageable = new Pageable(pageNumber, pageSize);
//		model.addAttribute("orderTypes", OrderType.values());
//		model.addAttribute("insectCategory", insectCategory);
//		model.addAttribute("tags", tags);
//		model.addAttribute("attributeValue", attributeValue);
//		model.addAttribute("orderType", orderType);
//		model.addAttribute("pageNumber", pageNumber);
//		model.addAttribute("pageSize", pageSize);
//		model.addAttribute("page", catalogIndexService.findPage(insectCategory, tags, attributeValue, true, orderType, pageable));
//		return "/fonter/catalogIndex/list";
//	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list/{productCategoryId}", method = RequestMethod.GET)
	public String list(@PathVariable Long productCategoryId, Long promotionId, Long[] tagIds, OrderType orderType, Integer pageNumber, Integer pageSize, HttpServletRequest request, ModelMap model) {
		TCatalogIndex insectCategory = catalogIndexService.find(productCategoryId);
		if (insectCategory == null) {
			throw new ResourceNotFoundException();
		}
		List<Tag> tags = tagService.findList(tagIds);
		Map<Attribute, String> attributeValue = new HashMap<Attribute, String>();
		if (insectCategory != null) {
			Set<Attribute> attributes = insectCategory.getAttributes();
			for (Attribute attribute : attributes) {
				String value = request.getParameter("attribute_" + attribute.getId());
				if (StringUtils.isNotEmpty(value) && attribute.getOptions().contains(value)) {
					attributeValue.put(attribute, value);
				}
			}
		}
		if(pageSize == null){
			pageSize = 4;
		}
		Pageable pageable = new Pageable(pageNumber, pageSize);
		model.addAttribute("orderTypes", OrderType.values());
		model.addAttribute("insectCategory", insectCategory);
		model.addAttribute("tags", tags);
		model.addAttribute("attributeValue", attributeValue);
		model.addAttribute("orderType", orderType);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", catalogIndexService.findPage(insectCategory, tags, attributeValue, true, orderType, pageable));
		return "/fonter/catalogIndex/list";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Long[] tagIds, OrderType orderType, Integer pageNumber, Integer pageSize, HttpServletRequest request, ModelMap model) {
		List<Tag> tags = tagService.findList(tagIds);
		Pageable pageable = new Pageable(pageNumber, pageSize);
		model.addAttribute("orderTypes", OrderType.values());
		model.addAttribute("tags", tags);
		model.addAttribute("orderType", orderType);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", catalogIndexService.findPage(null, tags, null, true, orderType, pageable));
		return "/fonter/catalogIndex/list";
	}
	
	/**
	 * 虫种搜索
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchCatalogIndex(String keyword, Integer pageNumber, Integer pageSize, ModelMap model) {
		if (StringUtils.isEmpty(keyword)) {
			throw new ResourceNotFoundException();
		}
		Pageable pageable = new Pageable(pageNumber, pageSize);
		Page<TCatalogIndex> page = searchService.searchCatalogIndex(keyword, pageable);
		if(page.getTotal() == 1){
			Long insectId = page.getRows().get(0).getId();
			return "redirect:/catalogIndex/list/"+insectId+".jhtml";
		}else {
			model.addAttribute("catalogIndexKeyword", keyword);
			model.addAttribute("page", page);
			return "/fonter/catalogIndex/searchResultList";
		}
	}
	
}
