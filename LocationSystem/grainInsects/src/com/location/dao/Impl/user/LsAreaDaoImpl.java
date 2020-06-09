package com.location.dao.Impl.user;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.location.dao.user.LsAreaDao;
import com.location.entity.LsArea;

@Repository("lsAreaDaoImpl")
public class LsAreaDaoImpl extends BaseDaoImpl<LsArea, String> implements LsAreaDao {

	@Override
	public LsArea findByName(String areaName) {
		// TODO Auto-generated method stub
		if(areaName==null){
			return null;
		}
		try {
			String jpql = "select area from Area area where lower(area.areaName) = lower(:areaName)";
			return entityManager.createQuery(jpql, LsArea.class).setFlushMode(FlushModeType.COMMIT).setParameter("areaName", areaName).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
