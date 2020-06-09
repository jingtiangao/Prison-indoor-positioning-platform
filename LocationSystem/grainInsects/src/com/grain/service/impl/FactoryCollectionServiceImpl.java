package com.grain.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.FactoryCollectionDao;
import com.grain.entity.InsectsOnfactory;
import com.grain.service.FactoryCollectionService;

/**
 * Service - 加工厂采集记录
 * @author Mark
 */
@Service("factoryCollectionServiceImpl")
public class FactoryCollectionServiceImpl extends BaseServiceImpl<InsectsOnfactory, String> implements FactoryCollectionService{

	@Resource(name = "factoryCollectionDaoImpl")
	private FactoryCollectionDao factoryCollectionDao;

	@Resource(name = "factoryCollectionDaoImpl")
	public void setBaseDao(FactoryCollectionDao factoryCollectionDao) {
		super.setBaseDao(factoryCollectionDao);
	}
	
	@Transactional(readOnly = true)
	public boolean factoryCollectionExists(String smCollect) {
		return factoryCollectionDao.factoryCollectionExists(smCollect);
	}

	@Transactional(readOnly = true)
	public InsectsOnfactory findBySMCollection(String smCollect) {
		return factoryCollectionDao.findBySMCollection(smCollect);
	}

	@Override
	@Transactional
	public void save(InsectsOnfactory insectsOnfactory) {
		super.save(insectsOnfactory);
	}

	@Override
	@Transactional
	public InsectsOnfactory update(InsectsOnfactory insectsOnfactory) {
		return super.update(insectsOnfactory);
	}
	
	@Override
	@Transactional
	public InsectsOnfactory update(InsectsOnfactory insectsOnfactory, String... ignoreProperties) {
		return super.update(insectsOnfactory, ignoreProperties);
	}
	
	@Override
	@Transactional
	public void delete(String smCollect) {
		super.delete(smCollect);
	}

	@Override
	@Transactional
	public void delete(String... smCollections) {
		super.delete(smCollections);
	}

	@Override
	@Transactional
	public void delete(InsectsOnfactory insectsCollection) {
		super.delete(insectsCollection);
	}
}