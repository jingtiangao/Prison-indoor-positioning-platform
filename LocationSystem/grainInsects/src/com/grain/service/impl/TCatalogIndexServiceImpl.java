package com.grain.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.Filter;
import com.grain.Order;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.dao.TCatalogIndexDao;
import com.grain.entity.Attribute;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TCatalogIndex.OrderType;
import com.grain.entity.Tag;
import com.grain.entity.page.CatalogIndex;
import com.grain.service.TCatalogIndexService;
/*
 * 害虫分类表
 */
@Service("tcatalogIndexServiceImpl")
public class TCatalogIndexServiceImpl 
	extends BaseServiceImpl<TCatalogIndex,Long>
	implements TCatalogIndexService{
	
	@Resource(name = "tcatalogIndexDaoImpl")
	TCatalogIndexDao dao;
	
	@Resource(name = "tcatalogIndexDaoImpl")
	public void setBaseDao(TCatalogIndexDao dao){
		super.setBaseDao(dao);
		
	}

	@Override
	public List<TCatalogIndex> findTree() {
		
		return dao.findChildren(null, null);
	}

	@Override
	public boolean snUnique(String previousSn, String currentSn) {
		if (StringUtils.equalsIgnoreCase(previousSn, currentSn)) {
			return true;
		} else {
			if (dao.snExists(currentSn)) {
				return false;
			} else {
				return true;
			}
		}
	}


	@Override
	public boolean mcUnique(String previousSn, String currentSn) {
		if (StringUtils.equalsIgnoreCase(previousSn, currentSn)) {
			return true;
		} else {
			if (dao.mcExists(currentSn)) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public boolean ywmUnique(String previousSn, String currentSn) {
		if (StringUtils.equalsIgnoreCase(previousSn, currentSn)) {
			return true;
		} else {
			if (dao.ywmExists(currentSn)) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public Page<TCatalogIndex> findPage(TCatalogIndex catalogIndex, List<Tag> tags, Boolean isTop, Boolean passaudit,
			Pageable pageable) {
		return null;
	}

	
	@Transactional(readOnly = true)
	public Page<TCatalogIndex> findPage(TCatalogIndex productCategory, List<Tag> tags, Map<Attribute, String> attributeValue,Boolean isTop, OrderType orderType, Pageable pageable) {
		return dao.findPage(productCategory, tags, attributeValue, isTop, orderType, pageable);
	}

	@Transactional(readOnly = true)
	public Page<TCatalogIndex> findPage(Pageable pageable) {
		return dao.findPage(pageable);
	}
	
	@Override
	public Page<CatalogIndex> TreeGrid(Pageable pageable,TCatalogIndex queryParm) {
		Page<TCatalogIndex> l = null;
		Page<CatalogIndex > lr = new Page<CatalogIndex>();
		l = dao.findPage(pageable, queryParm);
		if (l==null ||l.getRows()==null || l.getRows().size()==0 ) return lr;
		else{
			if (l.getRows()!=null && l.getRows().size()>0){
				for (TCatalogIndex tc: l.getRows()){
					CatalogIndex r = new CatalogIndex();
					BeanUtils.copyProperties(tc, r);
					if (tc.getParent()!=null){
						r.set_parentId(tc.getParent().getId());
					}
					lr.getRows().add(r);
				}
			}
		}
		lr.setTotal(l.getTotal());
		return lr;
	}
	
	
	
	@Transactional(readOnly = true)
	public List<TCatalogIndex> findRoots() {
		return dao.findRoots(null);
	}

	@Transactional(readOnly = true)
	public List<TCatalogIndex> findRoots(Integer count) {
		return dao.findRoots(count);
	}

	@Transactional(readOnly = true)
	@Cacheable("insectCategory")
	public List<TCatalogIndex> findRoots(Integer count, String cacheRegion) {
		return dao.findRoots(count);
	}

	@Transactional(readOnly = true)
	public List<TCatalogIndex> findParents(TCatalogIndex insectCategory) {
		return dao.findParents(insectCategory, null);
	}

	@Transactional(readOnly = true)
	public List<TCatalogIndex> findParents(TCatalogIndex insectCategory, Integer count) {
		return dao.findParents(insectCategory, count);
	}

	@Transactional(readOnly = true)
	@Cacheable("insectCategory")
	public List<TCatalogIndex> findParents(TCatalogIndex insectCategory, Integer count, String cacheRegion) {
		return dao.findParents(insectCategory, count);
	}

	@Transactional(readOnly = true)
	public List<TCatalogIndex> findChildren(TCatalogIndex insectCategory) {
		return dao.findChildren(insectCategory, null);
	}
	
	@Transactional(readOnly = true)
	public List<TCatalogIndex> findDirectChildren(TCatalogIndex catalogIndex){
		return dao.findDirectChildren(catalogIndex, null);
	}
	
	@Transactional(readOnly = true)
	public List<TCatalogIndex> findChildren(TCatalogIndex insectCategory, Integer count) {
		return dao.findChildren(insectCategory, count);
	}

	@Transactional(readOnly = true)
	@Cacheable("insectCategory")
	public List<TCatalogIndex> findChildren(TCatalogIndex insectCategory, Integer count, String cacheRegion) {
		return dao.findChildren(insectCategory, count);
	}
	
	@Transactional(readOnly = true)
	@Cacheable("insectCategory")
	public List<TCatalogIndex> findChildren(TCatalogIndex insectCategory, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop,OrderType orderType, Integer count, List<Filter> filters, List<Order> orders) {
		return dao.findChildren(insectCategory, tags, attributeValue, isTop, orderType, count, filters, orders);
	}

	@Override
	public List<TCatalogIndex> findByCatalogIndexName(String catalogIndexName, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop,OrderType orderType, Integer count, List<Filter> filters, List<Order> orders) {
		return dao.findByCatalogIndexName(catalogIndexName, tags, attributeValue, isTop, orderType, count, filters, orders);
	}
	
	@Transactional(readOnly = true)
	public List<TCatalogIndex> findByCatalogIndexName(String catalogIndexName, Integer count) {
		return dao.findByCatalogIndexName(catalogIndexName, count);
	}
}
