package com.location.dao.Impl.user;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.location.dao.user.RegionDao;
import com.location.entity.Region;

@Repository("regionDaoImpl")
public class RegionDaoImpl extends BaseDaoImpl<Region, String> implements RegionDao {

	@Override
	public Region findByName(String regionName) {
		if(regionName==null){
			return null;
		}
		try {
			String jpql = "select region from Region region where lower(region.regionName) = lower(:regionName)";
			return entityManager.createQuery(jpql, Region.class).setFlushMode(FlushModeType.COMMIT).setParameter("regionName", regionName).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
