package com.grain.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.grain.dao.TProcTypeDao;
import com.grain.entity.TProcType;

/**
 * Dao - 防治工艺类型
 * 
 */
@Repository("tprocTypeDaoImpl")
public class TProcTypeDaoImpl extends BaseDaoImpl<TProcType, String> 
implements TProcTypeDao {

	@Override
	public List<TProcType> findRoots(Integer count) {
		String jpql = "select proctype from TProcType proctype where proctype.parent is null order by proctype.sm asc";
		TypedQuery<TProcType> query = entityManager.createQuery(jpql, TProcType.class)
				.setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public boolean snExists(String sn) {
		if (sn == null) {
			return false;
		}
		String jpql = "select count(*) from TProcType proctype where lower(proctype.sn) = lower(:sn)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
				.setParameter("sn", sn).getSingleResult();
		return count > 0;
	}

}