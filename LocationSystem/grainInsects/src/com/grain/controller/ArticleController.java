package com.grain.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Pageable;
import com.grain.ResourceNotFoundException;
import com.grain.entity.Article;
import com.grain.entity.ArticleCategory;
import com.grain.service.ArticleCategoryService;
import com.grain.service.ArticleService;
import com.grain.service.SearchService;

@Controller("insectArticleController")
@RequestMapping("/article")
public class ArticleController extends BaseController{
	
	/** 每页记录数 */
	private static final int PAGE_SIZE = 20;

	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	@Resource(name="searchServiceImpl")
	private SearchService searchService;

	/**
	 * 文章列表
	 */
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public String list(@PathVariable Long id, Integer pageNumber, ModelMap model) {
		ArticleCategory articleCategory = articleCategoryService.find(id);
		if (articleCategory == null) {
			throw new ResourceNotFoundException();
		}
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("articleCategory", articleCategory);
		model.addAttribute("page", articleService.findPage(articleCategory, null, pageable));
		return "/fonter/article/list";
	}
	
	/**
	 * 文章
	 */
	@RequestMapping(value = "/content/{createDate}/{id}/{pageNumber}", method = RequestMethod.GET)
	public String content(@PathVariable String createDate, @PathVariable Long id, @PathVariable Integer pageNumber, ModelMap model) {
		Article article = articleService.find(id);  // 找到该文章
//		ArticleCategory articleCategory = articleCategoryService.find(id);
		if (article == null) {
			throw new ResourceNotFoundException();
		}
//		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("article", article);
//		model.addAttribute("page", articleService.findPage(articleCategory, null, pageable));
		return "/fonter/article/content";
	}

	/**
	 * 搜索
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(String keyword, Integer pageNumber, ModelMap model) {
		if (StringUtils.isEmpty(keyword)) {
			throw new ResourceNotFoundException();
		}
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("articleKeyword", keyword);
		model.addAttribute("page", searchService.search(keyword, pageable));
//		return "/fonter/article/search";  // 这个页面暂没用
		return "/fonter/article/list";
	}

	/**
	 * 点击数
	 */
	@RequestMapping(value = "/hits/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Long hits(@PathVariable Long id) {
		return articleService.viewHits(id);
	}
}
