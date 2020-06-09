package com.grain.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.BaseDao;
import com.grain.dao.FieldCollectionDao;
import com.grain.entity.InsectOnfield;
import com.grain.service.FieldCollectionService;

/**
 * Service - 野外采集记录
 * @author Mark
 */
@Service("fieldCollectionServiceImpl")
public class FieldCollectionServiceImpl extends BaseServiceImpl<InsectOnfield, Long> implements FieldCollectionService{

	@Resource(name = "fieldCollectionDaoImpl")
	private FieldCollectionDao fieldCollectionDao;

	@Resource(name = "fieldCollectionDaoImpl")
	public void setBaseDao(BaseDao<InsectOnfield, Long> fieldCollectionDao) {
		super.setBaseDao(fieldCollectionDao);
	}
	
	@Transactional(readOnly = true)
	public boolean fieldCollectionExists(String id) {
		return fieldCollectionDao.fieldCollectionExists(id);
	}

	@Transactional(readOnly = true)
	public InsectOnfield findById(String id) {
		return fieldCollectionDao.findById(id);
	}

	@Override
	@Transactional
	public void save(InsectOnfield insectsOnfield) {
		super.save(insectsOnfield);
	}

	@Override
	@Transactional
	public InsectOnfield update(InsectOnfield insectsOnfield) {
		return super.update(insectsOnfield);
	}
	
	@Override
	@Transactional
	public InsectOnfield update(InsectOnfield insectsOnfield, String... ignoreProperties) {
		return super.update(insectsOnfield, ignoreProperties);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	public void delete(Long... ids) {
		super.delete(ids);
	}

	
	
	@Override
	@Transactional
	public void delete(InsectOnfield insectsOnfield) {
		super.delete(insectsOnfield);
	}
}