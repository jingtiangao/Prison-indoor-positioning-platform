package com.grain.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.grain.entity.TCatalogIndex;
import com.grain.service.TCatalogIndexService;
import com.grain.util.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 下级昆虫分类列表
 * @author Mark
 *
 */
@Component("insectCategoryParentListDirective")
public class InsectCategoryParentListDirective extends BaseDirective {

	/** "昆虫分类ID"参数名称 */
	private static final String INSECT_CATEGORY_ID_PARAMETER_NAME = "insectCategoryId";

	/** 变量名称 */
	private static final String VARIABLE_NAME = "insectCategories";

	@Resource(name = "tcatalogIndexServiceImpl")
	private TCatalogIndexService catalogIndexService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Long productCategoryId = FreemarkerUtils.getParameter(INSECT_CATEGORY_ID_PARAMETER_NAME, Long.class, params);

		TCatalogIndex insectCategory = catalogIndexService.find(productCategoryId);

		List<TCatalogIndex> insectCategories;
		if (productCategoryId != null && insectCategory== null) {
			insectCategories = new ArrayList<TCatalogIndex>();
		} else {
			boolean useCache = useCache(env, params);
			String cacheRegion = getCacheRegion(env, params);
			Integer count = getCount(params);
			if (useCache) {
				insectCategories = catalogIndexService.findParents(insectCategory, count, cacheRegion);
			} else {
				insectCategories = catalogIndexService.findParents(insectCategory, count);
			}
		}
		setLocalVariable(VARIABLE_NAME, insectCategories, env, body);
	}

}