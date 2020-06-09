package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.FarmerDao;
import com.grain.entity.Farmer;

/**
 * 农户
 * @author Mark
 *
 */
@Repository("farmerDaoImpl")
public class FarmerDaoImpl extends BaseDaoImpl<Farmer, String> implements FarmerDao{

	@Override
	public boolean farmerExists(String smFarmer) {
		if (smFarmer == null) {
			return false;
		}
		String jpql = "select count(*) from Farmer farmer where lower(farmer.smFarmer) = lower(:smFarmer)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("smFarmer", smFarmer).getSingleResult();
		return count > 0;
	}

	@Override
	public Farmer findBySMFarmer(String smFarmer) {
		if (smFarmer == null) {
			return null;
		}
		try {
			String jpql = "select farmer from Farmer farmer where lower(farmer.smFarmer) = lower(:smFarmer)";
			return entityManager.createQuery(jpql, Farmer.class).setFlushMode(FlushModeType.COMMIT).setParameter("smFarmer", smFarmer).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
