package com.grain.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.grain.dao.GraindepotDao;
import com.grain.entity.Graindepot;

/**
 * Dao - 粮仓
 * 
 */
@Repository("graindepotDaoImpl")
public class GraindepotDaoImpl extends BaseDaoImpl<Graindepot, String> 
	implements GraindepotDao {
	
	//查找所有示范库
		
	@SuppressWarnings("rawtypes")
		public List<Graindepot> findModelDepots(Integer count, Long area){
			String jpql = new String();
			Query query;
			if(area == null){
				jpql = "select d from Graindepot d where d.hasreal=TRUE";
				query = entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT);
			}else{
				jpql = "select d from Graindepot d where d.hasreal=TRUE and direct =?1";
				query = entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT);
				query.setParameter(1, area);
			}
			if (count!=null)
				query.setMaxResults(count);
			List result = query.getResultList();
			List<Graindepot> resultList = new ArrayList<Graindepot>();
			if (result!=null){
				Iterator iterator = result.iterator();
				while( iterator.hasNext() ){
					Graindepot row = ( Graindepot) iterator.next();
					resultList.add(row);
				}
			}
			return resultList;
		}

}