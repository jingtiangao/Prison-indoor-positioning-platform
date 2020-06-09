package com.grain.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.grain.dao.AreaDao;
import com.grain.entity.Area;

/**
 * Dao - 地区
 * 
 */
@Repository("areaDaoImpl")
public class AreaDaoImpl extends BaseDaoImpl<Area, Long> implements AreaDao {

	public List<Area> findRoots(Integer count) {
		String jpql = "select area from Area area where area.parent is null order by area.order asc";
		TypedQuery<Area> query = entityManager.createQuery(jpql, Area.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}
	
	public List<Area> findAreasWithDirection(Integer count, Long grainDirectionsId){
		String jpql = "select area from Area area where graindirect =?1";
		TypedQuery<Area> query = entityManager.createQuery(jpql, Area.class).setFlushMode(FlushModeType.COMMIT);
		query.setParameter(1, grainDirectionsId);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

}