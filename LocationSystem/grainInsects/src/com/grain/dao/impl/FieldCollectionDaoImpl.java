package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.FieldCollectionDao;
import com.grain.entity.InsectOnfield;

/**
 * 野外害虫采集
 * @author Mark
 *
 */
@Repository("fieldCollectionDaoImpl")
public class FieldCollectionDaoImpl extends BaseDaoImpl<InsectOnfield, Long> implements FieldCollectionDao{

	@Override
	public boolean fieldCollectionExists(String id) {
		if (id == null) {
			return false;
		}
		String jpql = "select count(*) from InsectOnfield insectOnfield where lower(insectOnfield.id) = lower(:id)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("id", id).getSingleResult();
		return count > 0;
	}

	@Override
	public InsectOnfield findById(String id) {
		if (id == null) {
			return null;
		}
		try {
			String jpql = "select insectOnfield from InsectOnfield insectOnfield where lower(insectOnfield.id) = lower(:id)";
			return entityManager.createQuery(jpql, InsectOnfield.class).setFlushMode(FlushModeType.COMMIT).setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
