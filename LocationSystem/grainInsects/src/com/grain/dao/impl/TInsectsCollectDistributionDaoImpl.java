package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.TInsectsCollectDistributionDao;
import com.grain.entity.TInsectscollectdistribution;

/**
 * 昆虫采集分布信息
 * @author Mark
 *
 */
@Repository("tinsectsCollectDistributionDaoImpl")
public class TInsectsCollectDistributionDaoImpl extends BaseDaoImpl<TInsectscollectdistribution, String> implements TInsectsCollectDistributionDao{

	@Override
	public boolean insectsCollectDistributionExists(String smInsects){
		if (smInsects == null) {
			return false;
		}
		String jpql = "select count(*) from TInsectscollectdistribution tinsectscollectdistribution where lower(tinsectscollectdistribution.smInsects) = lower(:smInsects)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("smInsects", smInsects).getSingleResult();
		return count > 0;
	}

	@Override
	public TInsectscollectdistribution findBySMInsects(String smInsects) {
		if (smInsects == null) {
			return null;
		}
		try {
			String jpql = "select tinsectscollectdistribution from TInsectscollectdistribution tinsectscollectdistribution where lower(tinsectscollectdistribution.smInsects) = lower(:smInsects)";
			return entityManager.createQuery(jpql, TInsectscollectdistribution.class).setFlushMode(FlushModeType.COMMIT).setParameter("smInsects", smInsects).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
