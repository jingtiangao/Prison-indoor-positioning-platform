package com.grain.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.TMiteIdentifyDao;
import com.grain.entity.TMiteidentify;
import com.grain.service.TMiteIdentifyService;

/**
 * Service - 3.2 螨鉴定表
 * @author Mark
 */
@Service("tmiteIdentifyServiceImpl")
public class TMiteIdentifyServiceImpl extends BaseServiceImpl<TMiteidentify, String> implements TMiteIdentifyService{

	@Resource(name = "tmiteIdentifyDaoImpl")
	private TMiteIdentifyDao miteIdentifyDao;

	@Resource(name = "tmiteIdentifyDaoImpl")
	public void setBaseDao(TMiteIdentifyDao miteIdentifyDao) {
		super.setBaseDao(miteIdentifyDao);
	}
	
	@Transactional(readOnly = true)
	public boolean miteIdentifyExists(String sm) {
		return miteIdentifyExists(sm);
	}

	@Transactional(readOnly = true)
	public TMiteidentify findBySM(String sm) {
		return miteIdentifyDao.findBySM(sm);
	}

	@Override
	@Transactional
	public void save(TMiteidentify miteIdentify) {
		super.save(miteIdentify);
	}

	@Override
	@Transactional
	public TMiteidentify update(TMiteidentify miteIdentify) {
		return super.update(miteIdentify);
	}
	
	@Override
	@Transactional
	public TMiteidentify update(TMiteidentify miteIdentify, String... ignoreProperties) {
		return super.update(miteIdentify, ignoreProperties);
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
	public void delete(TMiteidentify miteIdentify) {
		super.delete(miteIdentify);
	}
}