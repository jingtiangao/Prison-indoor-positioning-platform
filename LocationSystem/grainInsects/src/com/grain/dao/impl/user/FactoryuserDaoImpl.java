package com.grain.dao.impl.user;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.grain.dao.user.FactoryuserDao;
import com.grain.entity.user.Factoryuser;

@Repository("factoryuserDaoImpl")
public class FactoryuserDaoImpl extends BaseDaoImpl<Factoryuser, String> implements FactoryuserDao {


	@Override
	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from Factoryuser factoryuser where lower(factoryuser.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		return count > 0;
	}

	@Override
	public Factoryuser findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			String jpql = "select factoryuser from Factoryuser factoryuser where lower(factoryuser.username) = lower(:username)";
			Factoryuser factoryuser = entityManager.createQuery(jpql, Factoryuser.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
			return entityManager.createQuery(jpql, Factoryuser.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
