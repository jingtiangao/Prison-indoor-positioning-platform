package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.FactoryInfoDao;
import com.grain.entity.FactoryInfo;

/**
 * 农户
 * @author Mark
 *
 */
@Repository("factoryInfoDaoImpl")
public class FactoryInfoDaoImpl extends BaseDaoImpl<FactoryInfo, String> implements FactoryInfoDao{

	@Override
	public boolean factoryExists(String smFactory) {
		if (smFactory == null) {
			return false;
		}
		String jpql = "select count(*) from FactoryInfo factoryInfo where lower(factoryInfo.smFactory) = lower(:smFactory)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("smFactory", smFactory).getSingleResult();
		return count > 0;
	}

	@Override
	public FactoryInfo findBySMFactory(String smFactory) {
		if (smFactory == null) {
			return null;
		}
		try {
			String jpql = "select factoryInfo from FactoryInfo factoryInfo where lower(factoryInfo.smFactory) = lower(:smFactory)";
			return entityManager.createQuery(jpql, FactoryInfo.class).setFlushMode(FlushModeType.COMMIT).setParameter("smFactory", smFactory).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
