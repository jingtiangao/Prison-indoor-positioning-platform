package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.DepotCollectionDao;
import com.grain.entity.TInsectsInbinOnDepot;
import com.grain.service.DepotCollectionService;

/**
 * Service - 储备库采集记录
 * @author Mark
 */
@Service("depotCollectionServiceImpl")
public class DepotCollectionServiceImpl extends BaseServiceImpl<TInsectsInbinOnDepot, String> implements DepotCollectionService{

	@Resource(name = "depotCollectionDaoImpl")
	private DepotCollectionDao depotCollectionDao;

	@Resource(name = "depotCollectionDaoImpl")
	public void setBaseDao(DepotCollectionDao depotCollectionDao) {
		super.setBaseDao(depotCollectionDao);
	}
	
	@Transactional(readOnly = true)
	public boolean depotCollectionExists(String smCollection) {
		return depotCollectionDao.depotCollectionExists(smCollection);
	}

	@Transactional(readOnly = true)
	public TInsectsInbinOnDepot findBySMCollection(String smCollection) {
		return depotCollectionDao.findBySMCollection(smCollection);
	}

	@Override
	@Transactional
	public void save(TInsectsInbinOnDepot insectsInbinOnDepot) {
		super.save(insectsInbinOnDepot);
	}

	@Override
	@Transactional
	public TInsectsInbinOnDepot update(TInsectsInbinOnDepot insectsInbinOnDepot) {
		return super.update(insectsInbinOnDepot);
	}
	
	@Override
	@Transactional
	public TInsectsInbinOnDepot update(TInsectsInbinOnDepot insectsInbinOnDepot, String... ignoreProperties) {
		return super.update(insectsInbinOnDepot, ignoreProperties);
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
	public void delete(TInsectsInbinOnDepot insectsInbinOnDepot) {
		super.delete(insectsInbinOnDepot);
	}
}