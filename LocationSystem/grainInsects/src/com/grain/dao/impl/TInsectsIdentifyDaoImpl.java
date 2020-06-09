package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.TInsectsIdentifyDao;
import com.grain.entity.TInsectsIdentify;

/**
 * 昆虫鉴定表
 * @author Mark
 *
 */
@Repository("tinsectsIdentifyDaoImpl")
public class TInsectsIdentifyDaoImpl extends BaseDaoImpl<TInsectsIdentify, String> implements TInsectsIdentifyDao{

	@Override
	public boolean insectsIdentifyExists(String sm){
		if (sm == null) {
			return false;
		}
		String jpql = "select count(*) from TInsectsIdentify insectsIdentify where lower(insectsIdentify.sm) = lower(:sm)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("sm", sm).getSingleResult();
		return count > 0;
	}

	@Override
	public TInsectsIdentify findBySM(String sm) {
		if (sm == null) {
			return null;
		}
		try {
			String jpql = "select insectsIdentify from TInsectsIdentify insectsIdentify where lower(insectsIdentify.sm) = lower(:sm)";
			return entityManager.createQuery(jpql, TInsectsIdentify.class).setFlushMode(FlushModeType.COMMIT).setParameter("sm", sm).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
