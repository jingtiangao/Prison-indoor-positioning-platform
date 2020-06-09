package com.grain.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.grain.Filter;
import com.grain.Order;
import com.grain.entity.Article;
import com.grain.entity.Attribute;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TCatalogIndex.OrderType;
import com.grain.entity.Tag;
import com.grain.service.AttributeService;
import com.grain.service.TCatalogIndexService;
import com.grain.service.TagService;
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
@Component("insectCategoryChildrenListDirective")
public class InsectCategoryChildrenListDirective extends BaseDirective {

	/** "昆虫分类ID"参数名称 */
	private static final String INSECT_CATEGORY_ID_PARAMETER_NAME = "insectCategoryId";
	
	private static final String INSECT_CATEGORY_NAME_PARAMETER_NAME = "insectCategoryName";

	/** "标签ID"参数名称 */
	private static final String TAG_IDS_PARAMETER_NAME = "tagIds";
	
	/** "排序类型"参数名称 */
	private static final String ORDER_TYPE_PARAMETER_NAME = "orderType";
	
	/** "属性值"参数名称 */
	private static final String ATTRIBUTE_VALUE_PARAMETER_NAME = "attributeValue";
	
	/** 变量名称 */
	private static final String VARIABLE_NAME = "insectCategories";

	/** ID参数配比 */
	private static final Pattern ID_PATTERN = Pattern.compile("\\d+");
	
	@Resource(name = "tcatalogIndexServiceImpl")
	private TCatalogIndexService catalogIndexService;
	@Resource(name = "attributeServiceImpl")
	private AttributeService attributeService;
	@Resource(name = "tagServiceImpl")
	private TagService tagService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Long categoryId = FreemarkerUtils.getParameter(INSECT_CATEGORY_ID_PARAMETER_NAME, Long.class, params);
		String categoryName = FreemarkerUtils.getParameter(INSECT_CATEGORY_NAME_PARAMETER_NAME, String.class, params);
		Map<String, String> attributeValue = FreemarkerUtils.getParameter(ATTRIBUTE_VALUE_PARAMETER_NAME, Map.class, params);
		Long[] tagIds = FreemarkerUtils.getParameter(TAG_IDS_PARAMETER_NAME, Long[].class, params);
		OrderType orderType = FreemarkerUtils.getParameter(ORDER_TYPE_PARAMETER_NAME, OrderType.class, params);
		
		List<TCatalogIndex> insectCategories;
		List<Tag> tags = tagService.findList(tagIds);
		Map<Attribute, String> attributeValueMap = new HashMap<Attribute, String>();
		if (attributeValue != null) {
			for (Entry<String, String> entry : attributeValue.entrySet()) {
				if (ID_PATTERN.matcher(entry.getKey()).matches()) {
					Long attributeId = Long.valueOf(entry.getKey());
					Attribute attribute = attributeService.find(attributeId);
					if (attribute != null) {
						attributeValueMap.put(attribute, entry.getValue());
					}
				}
			}
		}
		if(categoryId == null){
			if (categoryName == null || (tagIds != null && tags.isEmpty()) || (attributeValue != null && attributeValueMap.isEmpty())) {
				insectCategories = new ArrayList<TCatalogIndex>();
			} else {
				boolean useCache = useCache(env, params);
//				String cacheRegion = getCacheRegion(env, params);
				Integer count = getCount(params);
				List<Filter> filters = getFilters(params, Article.class);
				List<Order> orders = getOrders(params);
				if (useCache) {
					if(tags != null || attributeValueMap.size() > 0 || orderType != null || filters != null || orders != null){
						insectCategories = catalogIndexService.findByCatalogIndexName(categoryName, tags, attributeValueMap, true, orderType, count, filters, orders);
					}else{
						insectCategories = catalogIndexService.findByCatalogIndexName(categoryName, count);
					}
//					insectCategories = catalogIndexService.findChildren(insectCategory, count, cacheRegion);
//					insectCategories = catalogIndexService.findChildren(insectCategory, tags, attributeValueMap, true, orderType, count, filters, orders);
				} else {
					insectCategories = catalogIndexService.findByCatalogIndexName(categoryName, count);
				}
			}
		}else{
			TCatalogIndex insectCategory = catalogIndexService.find(categoryId);
			
			if (insectCategory == null || (tagIds != null && tags.isEmpty()) || (attributeValue != null && attributeValueMap.isEmpty())) {
				insectCategories = new ArrayList<TCatalogIndex>();
			} else {
				boolean useCache = useCache(env, params);
				String cacheRegion = getCacheRegion(env, params);
				Integer count = getCount(params);
				List<Filter> filters = getFilters(params, Article.class);
				List<Order> orders = getOrders(params);
				if (useCache) {
//				insectCategories = catalogIndexService.findChildren(insectCategory, count, cacheRegion);
					if(tags != null || attributeValueMap.size() > 0 || orderType != null || filters != null || orders != null){
						insectCategories = catalogIndexService.findChildren(insectCategory, tags, attributeValueMap, true, orderType, count, filters, orders);
					}else{
						insectCategories = catalogIndexService.findChildren(insectCategory, count, cacheRegion);
					}
				} else {
					insectCategories = catalogIndexService.findChildren(insectCategory, count);
				}
			}
		}
		setLocalVariable(VARIABLE_NAME, insectCategories, env, body);
	}

}