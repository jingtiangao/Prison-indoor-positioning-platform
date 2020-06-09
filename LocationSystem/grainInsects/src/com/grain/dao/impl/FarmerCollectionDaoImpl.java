package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.FarmerCollectionDao;
import com.grain.entity.TInsectsCollectionOnFarmer;

/**
 * 本地储粮害虫采集
 * @author Mark
 *
 */
@Repository("farmerCollectionDaoImpl")
public class FarmerCollectionDaoImpl extends BaseDaoImpl<TInsectsCollectionOnFarmer, String> implements FarmerCollectionDao{

	@Override
	public boolean insectsCollectionExists(String smCollection) {
		if (smCollection == null) {
			return false;
		}
		String jpql = "select count(*) from TInsectsCollectionOnFarmer insectsCollection where lower(insectsCollection.smCollection) = lower(:smCollection)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("smCollection", smCollection).getSingleResult();
		return count > 0;
	}

	@Override
	public TInsectsCollectionOnFarmer findBySMCollection(String smCollection) {
		if (smCollection == null) {
			return null;
		}
		try {
			String jpql = "select insectsCollection from TInsectsCollectionOnFarmer insectsCollection where lower(insectsCollection.smCollection) = lower(:smCollection)";
			return entityManager.createQuery(jpql, TInsectsCollectionOnFarmer.class).setFlushMode(FlushModeType.COMMIT).setParameter("smCollection", smCollection).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
