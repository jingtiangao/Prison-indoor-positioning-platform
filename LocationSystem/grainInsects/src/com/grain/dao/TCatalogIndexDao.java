package com.grain.dao;

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

public interface TCatalogIndexDao extends BaseDao<TCatalogIndex,Long> {

	List<TCatalogIndex> findChildren(TCatalogIndex catalogindex, Integer count);

	List<TCatalogIndex> findDirectChildren(TCatalogIndex catalogindex, Integer count);
	
	boolean snExists(String currentSn);

	boolean mcExists(String currentSn);

	boolean ywmExists(String currentSn);
	
	List<TCatalogIndex> findRoots(Integer count);

	List<TCatalogIndex> findParents(TCatalogIndex insectCategory, Integer count);
	
	// mark add
	Page<TCatalogIndex> findPage(TCatalogIndex productCategory, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop, OrderType orderType, Pageable pageable);
	
	List<TCatalogIndex> findChildren(TCatalogIndex insectCategory, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop, OrderType orderType, Integer count, List<Filter> filters, List<Order> orders);
	
	List<TCatalogIndex> findByCatalogIndexName(String catalogIndexName, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop,OrderType orderType, Integer count, List<Filter> filters, List<Order> orders);
	
	List<TCatalogIndex> findByCatalogIndexName(String catalogIndexName, Integer count);
	
	Page<TCatalogIndex> findCatalogIndexByKeyword(String keyword, Pageable pageable);
}
