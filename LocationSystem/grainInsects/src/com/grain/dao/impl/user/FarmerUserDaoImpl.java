package com.grain.dao.impl.user;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.grain.dao.user.FarmerUserDao;
import com.grain.entity.user.FarmerUser;

@Repository("farmerUserDaoImpl")
public class FarmerUserDaoImpl extends BaseDaoImpl<FarmerUser, String> implements FarmerUserDao{

	@Override
	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from FarmerUser farmerUser where lower(farmerUser.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		return count > 0;
	}

	@Override
	public FarmerUser findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			String jpql = "select farmerUser from FarmerUser farmerUser where lower(farmerUser.username) = lower(:username)";
			return entityManager.createQuery(jpql, FarmerUser.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
