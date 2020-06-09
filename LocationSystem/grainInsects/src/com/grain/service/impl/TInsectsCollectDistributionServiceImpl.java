package com.grain.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.TInsectsCollectDistributionDao;
import com.grain.entity.TInsectscollectdistribution;
import com.grain.service.TInsectsCollectDistributionService;

/**
 * Service - 昆虫采集分布信息
 * @author Mark
 */
@Service("tinsectsCollectDistributionServiceImpl")
public class TInsectsCollectDistributionServiceImpl extends BaseServiceImpl<TInsectscollectdistribution, String> implements TInsectsCollectDistributionService{

	@Resource(name = "tinsectsCollectDistributionDaoImpl")
	private TInsectsCollectDistributionDao insectsCollectDistributionDao;

	@Resource(name = "tinsectsCollectDistributionDaoImpl")
	public void setBaseDao(TInsectsCollectDistributionDao insectsCollectDistributionDao) {
		super.setBaseDao(insectsCollectDistributionDao);
	}
	
	@Transactional(readOnly = true)
	public boolean insectsCollectDistributionExists(String smInsects) {
		return insectsCollectDistributionDao.insectsCollectDistributionExists(smInsects);
	}

	@Transactional(readOnly = true)
	public TInsectscollectdistribution findBySMInsects(String smInsects) {
		return insectsCollectDistributionDao.findBySMInsects(smInsects);
	}

	@Override
	@Transactional
	public void save(TInsectscollectdistribution insectscollectdistribution) {
		super.save(insectscollectdistribution);
	}

	@Override
	@Transactional
	public TInsectscollectdistribution update(TInsectscollectdistribution insectscollectdistribution) {
		return super.update(insectscollectdistribution);
	}
	
	@Override
	@Transactional
	public TInsectscollectdistribution update(TInsectscollectdistribution insectscollectdistribution, String... ignoreProperties) {
		return super.update(insectscollectdistribution, ignoreProperties);
	}
	
	
	@Override
	@Transactional
	public void delete(String smInsects) {
		super.delete(smInsects);
	}

	@Override
	@Transactional
	public void delete(String... smInsects) {
		super.delete(smInsects);
	}

	
	
	@Override
	@Transactional
	public void delete(TInsectscollectdistribution insectscollectdistribution) {
		super.delete(insectscollectdistribution);
	}
}