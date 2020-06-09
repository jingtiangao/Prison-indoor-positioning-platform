package com.grain.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.grain.dao.GrainDao;
import com.grain.entity.Grain;

/**
 * Dao - 粮食信息
 * 
 */
@Repository("grainDaoImpl")
public class GrainDaoImpl extends BaseDaoImpl<Grain, Long> 
implements GrainDao {

	@Override
	public Grain findLast(String lcbm) {
		String jpql ="select grain from Grain grain where grain.id=(select max(id) from Grain a where a.grainbin.lcbm=:lcbm)";
		TypedQuery <Grain> query = entityManager.createQuery(jpql, Grain.class).setParameter("lcbm", lcbm).setFlushMode(FlushModeType.COMMIT);
		try{
			Grain grain = query.getSingleResult();
			return grain;
		}
		catch(NoResultException e){
			return null;
		}
	}


}