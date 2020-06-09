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
import com.grain.dao.FarmerDao;
import com.grain.entity.Farmer;
import com.grain.service.FarmerService;

/**
 * Service - 农户
 * 
 */
@Service("farmerServiceImpl")
public class FarmerServiceImpl extends BaseServiceImpl<Farmer, String> implements FarmerService {

	@Resource(name = "farmerDaoImpl")
	private FarmerDao farmerDao;

	@Resource(name = "farmerDaoImpl")
	public void setBaseDao(FarmerDao farmerDao) {
		super.setBaseDao(farmerDao);
	}
	
	@Transactional(readOnly = true)
	public boolean farmerExists(String smFarmer) {
		return farmerDao.farmerExists(smFarmer);
	}

	@Transactional(readOnly = true)
	public Farmer findBySMFarmer(String smFarmer) {
		return farmerDao.findBySMFarmer(smFarmer);
	}

	@Transactional(readOnly = true)
	public Farmer getCurrent() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(CommonAttributes.Principal);
			if (principal != null) {
				return findBySMFarmer(principal.getUsername());
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
	public void save(Farmer farmer) {
		super.save(farmer);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Farmer update(Farmer farmer) {
		//Farmer e = find(farmer.getSmFarmer());
		return super.update(farmer);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Farmer update(Farmer farmer, String... ignoreProperties) {
		return super.update(farmer, ignoreProperties);
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
	public void delete(Farmer farmer) {
		super.delete(farmer);
	}
}