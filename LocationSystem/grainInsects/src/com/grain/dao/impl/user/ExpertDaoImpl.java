package com.grain.dao.impl.user;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.grain.dao.user.ExpertDao;
import com.grain.entity.user.Expert;

/**
 * 专家用户
 * @author Mark
 *
 */
@Repository("expertDaoImpl")
public class ExpertDaoImpl extends BaseDaoImpl<Expert, String> implements ExpertDao{

	@Override
	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from Expert expert where lower(expert.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		return count > 0;
	}

	@Override
	public Expert findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			String jpql = "select expert from Expert expert where lower(expert.username) = lower(:username)";
			return entityManager.createQuery(jpql, Expert.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
