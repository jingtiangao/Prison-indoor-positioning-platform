package com.grain.service.impl;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.grain.CommonAttributes;
import com.grain.Principal;
import com.grain.dao.FactoryInfoDao;
import com.grain.entity.FactoryInfo;
import com.grain.service.FactoryInfoService;

/**
 * Service - 农户
 * 
 */
@Service("factoryServiceImpl")
public class FactoryInfoServiceImpl extends BaseServiceImpl<FactoryInfo, String> implements FactoryInfoService {

	@Resource(name = "factoryInfoDaoImpl")
	private FactoryInfoDao factoryInfoDao;

	@Resource(name = "factoryInfoDaoImpl")
	public void setBaseDao(FactoryInfoDao factoryInfoDao) {
		super.setBaseDao(factoryInfoDao);
	}
	
	@Transactional(readOnly = true)
	public boolean factoryExists(String smFactory) {
		return factoryInfoDao.factoryExists(smFactory);
	}

	@Transactional(readOnly = true)
	public FactoryInfo findBySMFactory(String smFactory) {
		return factoryInfoDao.findBySMFactory(smFactory);
	}

	@Transactional(readOnly = true)
	public FactoryInfo getCurrent() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(CommonAttributes.Principal);
			if (principal != null) {
				return findBySMFactory(principal.getUsername());
			}
		}
		return null;
	}

	@Transactional(readOnly = true)
	public String getCurrentUsername() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(CommonAttributes.Principal);
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void save(FactoryInfo factoryInfo) {
		super.save(factoryInfo);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public FactoryInfo update(FactoryInfo factoryInfo) {
		//FactoryInfo e = find(factoryInfo.getSmFactory());
		return super.update(factoryInfo);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public FactoryInfo update(FactoryInfo factoryInfo, String... ignoreProperties) {
		return super.update(factoryInfo, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(String id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(String... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(FactoryInfo factoryInfo) {
		super.delete(factoryInfo);
	}
}