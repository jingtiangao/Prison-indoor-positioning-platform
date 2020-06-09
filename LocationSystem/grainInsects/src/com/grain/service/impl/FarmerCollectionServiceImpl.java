package com.grain.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.FarmerCollectionDao;
import com.grain.entity.TInsectsCollectionOnFarmer;
import com.grain.service.FarmerCollectionService;

/**
 * Service - 专家用户
 * 
 */
@Service("farmerCollectionServiceImpl")
public class FarmerCollectionServiceImpl extends BaseServiceImpl<TInsectsCollectionOnFarmer, String> implements FarmerCollectionService{

	@Resource(name = "farmerCollectionDaoImpl")
	private FarmerCollectionDao farmerCollectionDao;

	@Resource(name = "farmerCollectionDaoImpl")
	public void setBaseDao(FarmerCollectionDao farmerCollectionDao) {
		super.setBaseDao(farmerCollectionDao);
	}
	
	@Transactional(readOnly = true)
	public boolean farmerCollectionExists(String smCollection) {
		return farmerCollectionDao.insectsCollectionExists(smCollection);
	}

	@Transactional(readOnly = true)
	public TInsectsCollectionOnFarmer findBySMCollection(String smCollection) {
		return farmerCollectionDao.findBySMCollection(smCollection);
	}

	@Override
	@Transactional
	public void save(TInsectsCollectionOnFarmer insectsCollection) {
		super.save(insectsCollection);
	}

	@Override
	@Transactional
	public TInsectsCollectionOnFarmer update(TInsectsCollectionOnFarmer insectsCollection) {
		return super.update(insectsCollection);
	}
	
	@Override
	@Transactional
	public TInsectsCollectionOnFarmer update(TInsectsCollectionOnFarmer insectsCollection, String... ignoreProperties) {
		return super.update(insectsCollection, ignoreProperties);
	}
	
	@Override
	@Transactional
	public void delete(String smCollection) {
		super.delete(smCollection);
	}

	@Override
	@Transactional
	public void delete(String... smCollections) {
		super.delete(smCollections);
	}

	@Override
	@Transactional
	public void delete(TInsectsCollectionOnFarmer insectsCollection) {
		super.delete(insectsCollection);
	}
}