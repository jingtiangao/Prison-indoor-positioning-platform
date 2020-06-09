package com.grain.service;

import java.util.List;
import java.util.Map;

import com.grain.Filter;
import com.grain.Order;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.Attribute;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.Tag;
import com.grain.entity.TCatalogIndex.OrderType;
import com.grain.entity.page.CatalogIndex;


/**
 * Service - 害虫分类表
 * 
 */
public interface TCatalogIndexService extends BaseService<TCatalogIndex, Long> {

	List <TCatalogIndex> findTree();

	boolean snUnique(String previousSn, String sn);

	Page<TCatalogIndex> findPage(TCatalogIndex catalogIndex/*父类*/, List<Tag> tags, Boolean isTop, Boolean passaudit, Pageable pageable);

	Page<TCatalogIndex> findPage(TCatalogIndex productCategory, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop, OrderType orderType, Pageable pageable);
	
	boolean mcUnique(String previousSn, String sn);

	boolean ywmUnique(String previousSn, String sn);

	Page<CatalogIndex> TreeGrid(Pageable pageable, TCatalogIndex queryParm);

	
	
	List<TCatalogIndex> findRoots();

	List<TCatalogIndex> findRoots(Integer count);

	List<TCatalogIndex> findRoots(Integer count, String cacheRegion);

	List<TCatalogIndex> findParents(TCatalogIndex catalogIndex);

	List<TCatalogIndex> findParents(TCatalogIndex catalogIndex, Integer count);

	List<TCatalogIndex> findParents(TCatalogIndex catalogIndex, Integer count, String cacheRegion);

	List<TCatalogIndex> findChildren(TCatalogIndex catalogIndex);
	
	List<TCatalogIndex> findDirectChildren(TCatalogIndex catalogIndex);

	List<TCatalogIndex> findChildren(TCatalogIndex catalogIndex, Integer count);

	List<TCatalogIndex> findChildren(TCatalogIndex CatalogIndex, Integer count, String cacheRegion);
	
	List<TCatalogIndex> findChildren(TCatalogIndex insectCategory, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop,OrderType orderType, Integer count, List<Filter> filters, List<Order> orders);

	List<TCatalogIndex> findByCatalogIndexName(String catalogIndexName, Integer count);
	
	List<TCatalogIndex> findByCatalogIndexName(String catalogIndexName, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop,OrderType orderType, Integer count, List<Filter> filters, List<Order> orders);
}