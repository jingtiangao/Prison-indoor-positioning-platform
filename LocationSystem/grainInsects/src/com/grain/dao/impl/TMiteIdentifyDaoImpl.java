package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.TMiteIdentifyDao;
import com.grain.entity.TMiteidentify;

/**
 * 3.2 螨鉴定表
 * @author Mark
 *
 */
@Repository("tmiteIdentifyDaoImpl")
public class TMiteIdentifyDaoImpl extends BaseDaoImpl<TMiteidentify, String> implements TMiteIdentifyDao{

	@Override
	public boolean miteIdentifyExists(String sm){
		if (sm == null) {
			return false;
		}
		String jpql = "select count(*) from TMiteidentify miteIdentify where lower(miteIdentify.sm) = lower(:sm)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("sm", sm).getSingleResult();
		return count > 0;
	}

	@Override
	public TMiteidentify findBySM(String sm) {
		if (sm == null) {
			return null;
		}
		try {
			String jpql = "select miteIdentify from TMiteidentify miteIdentify where lower(miteIdentify.sm) = lower(:sm)";
			return entityManager.createQuery(jpql, TMiteidentify.class).setFlushMode(FlushModeType.COMMIT).setParameter("sm", sm).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
