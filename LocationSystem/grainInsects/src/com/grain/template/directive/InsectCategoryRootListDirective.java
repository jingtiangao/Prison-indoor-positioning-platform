package com.grain.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.grain.entity.TCatalogIndex;
import com.grain.service.TCatalogIndexService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 下级昆虫分类列表
 * @author Mark
 *
 */
@Component("insectCategoryRootListDirective")
public class InsectCategoryRootListDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "insectCategories";

	@Resource(name = "tcatalogIndexServiceImpl")
	private TCatalogIndexService catalogIndexService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<TCatalogIndex> insectCategories;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		Integer count = getCount(params);
		if (useCache) {
			insectCategories = catalogIndexService.findRoots(count, cacheRegion);
		} else {
			insectCategories = catalogIndexService.findRoots(count);
		}
		setLocalVariable(VARIABLE_NAME, insectCategories, env, body);
	}

}