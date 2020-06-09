package com.grain.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.TInsectsIdentifyDao;
import com.grain.entity.TInsectsIdentify;
import com.grain.service.TInsectsIdentifyService;

/**
 * Service - 昆虫采集分布信息
 * @author Mark
 */
@Service("tinsectsIdentifyServiceImpl")
public class TInsectsIdentifyServiceImpl extends BaseServiceImpl<TInsectsIdentify, String> implements TInsectsIdentifyService{

	@Resource(name = "tinsectsIdentifyDaoImpl")
	private TInsectsIdentifyDao insectsIdentifyDao;

	@Resource(name = "tinsectsIdentifyDaoImpl")
	public void setBaseDao(TInsectsIdentifyDao insectsIdentifyDao) {
		super.setBaseDao(insectsIdentifyDao);
	}
	
	@Transactional(readOnly = true)
	public boolean insectsIdentifyExists(String sm) {
		return insectsIdentifyDao.insectsIdentifyExists(sm);
	}

	@Transactional(readOnly = true)
	public TInsectsIdentify findBySM(String sm) {
		return insectsIdentifyDao.findBySM(sm);
	}

	@Override
	@Transactional
	public void save(TInsectsIdentify insectsIdentify) {
		super.save(insectsIdentify);
	}

	@Override
	@Transactional
	public TInsectsIdentify update(TInsectsIdentify insectsIdentify) {
		return super.update(insectsIdentify);
	}
	
	@Override
	@Transactional
	public TInsectsIdentify update(TInsectsIdentify insectsIdentify, String... ignoreProperties) {
		return super.update(insectsIdentify, ignoreProperties);
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
	public void delete(TInsectsIdentify insectsIdentify) {
		super.delete(insectsIdentify);
	}
}