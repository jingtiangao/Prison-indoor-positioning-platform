package com.grain.dao.impl.user;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.grain.dao.user.InsectsinvestuserDao;
import com.grain.entity.user.Insectsinvestuser;

/**
 * 虫调用户
 * @author Mark
 *
 */
@Repository("insectsinvestuserDaoImpl")
public class InsectsinvestuserDaoImpl extends BaseDaoImpl<Insectsinvestuser, String> implements InsectsinvestuserDao{

	@Override
	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from Insectsinvestuser insectsinvestuser where lower(insectsinvestuser.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		return count > 0;
	}

	@Override
	public Insectsinvestuser findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			String jpql = "select Insectsinvestuser from Insectsinvestuser insectsinvestuser where lower(insectsinvestuser.username) = lower(:username)";
			return entityManager.createQuery(jpql, Insectsinvestuser.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
