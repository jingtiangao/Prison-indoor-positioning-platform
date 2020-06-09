package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.DepotCollectionDao;
import com.grain.entity.TInsectsInbinOnDepot;

/**
 * 储备库粮仓害虫采集
 * @author Mark
 *
 */
@Repository("depotCollectionDaoImpl")
public class DepotCollectionDaoImpl extends BaseDaoImpl<TInsectsInbinOnDepot, String> implements DepotCollectionDao{

	@Override
	public boolean depotCollectionExists(String smCollection) {
		if (smCollection == null) {
			return false;
		}
		String jpql = "select count(*) from TInsectsInbinOnDepot insectsInbinOnDepot where lower(insectsInbinOnDepot.smCollection) = lower(:smCollection)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("smCollection", smCollection).getSingleResult();
		return count > 0;
	}

	@Override
	public TInsectsInbinOnDepot findBySMCollection(String smCollection) {
		if (smCollection == null) {
			return null;
		}
		try {
			String jpql = "select insectsInbinOnDepot from TInsectsInbinOnDepot insectsInbinOnDepot where lower(insectsInbinOnDepot.smCollection) = lower(:smCollection)";
			return entityManager.createQuery(jpql, TInsectsInbinOnDepot.class).setFlushMode(FlushModeType.COMMIT).setParameter("smCollection", smCollection).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
