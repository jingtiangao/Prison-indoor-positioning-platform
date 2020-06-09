package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.FactoryCollectionDao;
import com.grain.entity.InsectsOnfactory;

/**
 * 本地储粮害虫采集
 * @author Mark
 *
 */
@Repository("factoryCollectionDaoImpl")
public class FactoryCollectionDaoImpl extends BaseDaoImpl<InsectsOnfactory, String> implements FactoryCollectionDao{

	@Override
	public boolean factoryCollectionExists(String smCollect) {
		if (smCollect == null) {
			return false;
		}
		String jpql = "select count(*) from InsectsOnfactory insectsOnfactory where lower(insectsOnfactory.smCollect) = lower(:smCollect)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("smCollect", smCollect).getSingleResult();
		return count > 0;
	}

	@Override
	public InsectsOnfactory findBySMCollection(String smCollect) {
		if (smCollect == null) {
			return null;
		}
		try {
			String jpql = "select insectsOnfactory from InsectsOnfactory insectsOnfactory where lower(insectsOnfactory.smCollect) = lower(:smCollect)";
			return entityManager.createQuery(jpql, InsectsOnfactory.class).setFlushMode(FlushModeType.COMMIT).setParameter("smCollect", smCollect).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
