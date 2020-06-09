package com.grain.dao.impl.user;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.grain.dao.user.DepotUserDao;
import com.grain.entity.user.DepotUser;

@Repository("depotUserDaoImpl")
public class DepotUserDaoImpl extends BaseDaoImpl<DepotUser, String> implements DepotUserDao{

	@Override
	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from DepotUser admin where lower(admin.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		return count > 0;
	}

	@Override
	public DepotUser findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			String jpql = "select admin from DepotUser admin where lower(admin.username) = lower(:username)";
			return entityManager.createQuery(jpql, DepotUser.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findGraindepot(Long graindirectionid) {
		String jpql="";
		if (graindirectionid!=null){
			jpql = "select a.lkbm,a.lkmc from t_Graindepot a where a.direct in (select id from xx_area b where b.graindirect = :graindirectionid)";
			return entityManager.createNativeQuery(jpql).setFlushMode(FlushModeType.COMMIT)
					.setParameter("graindirectionid", graindirectionid).getResultList();
		}else{
			jpql="select a.lkbm,a.lkmc from t_Graindepot a";
			return entityManager.createNativeQuery(jpql).setFlushMode(FlushModeType.COMMIT).getResultList();
		}
	}



}
