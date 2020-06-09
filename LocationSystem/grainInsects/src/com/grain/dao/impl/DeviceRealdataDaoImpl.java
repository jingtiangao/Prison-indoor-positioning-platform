package com.grain.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.grain.dao.DeviceRealdataDao;
import com.grain.entity.DeviceRealdata;

/**
 * Dao - 实时数据
 * 
 */
@Repository("deviceRealdataDaoImpl")
public class DeviceRealdataDaoImpl extends BaseDaoImpl<DeviceRealdata, Long> implements DeviceRealdataDao {
	
	//查找所有虫种
	@SuppressWarnings("rawtypes")
	public List<String> findAllKinds(Integer count){
		String jpql = "select distinct d.kind from DeviceRealdata d where not(kind =?1) ";
		Query query = entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT);
		query.setParameter(1, new String(""));
		if (count!=null)
			query.setMaxResults(count);
		List result = query.getResultList();
		List<String> resultList = new ArrayList<String>();
		if (result!=null){
			Iterator iterator = result.iterator();
			while( iterator.hasNext() ){
			String row = ( String) iterator.next();
			resultList.add(row);
			}
		}
		return resultList;
	}
	
	//查找一个粮仓内的所有设备来源
	@SuppressWarnings("rawtypes")
	public List<String> findAllSources(String lcbm, Integer count){
		String jpql = "select distinct d.source from DeviceRealdata d where lcbm =?1 and source is not null";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, new String(lcbm));
		query.setFlushMode(FlushModeType.COMMIT);
		if (count!=null)
			query.setMaxResults(count);
		List result = query.getResultList();
		List<String> resultList = new ArrayList<String>();
		if (result!=null){
			Iterator iterator = result.iterator();
			while( iterator.hasNext() ){
			String row = ( String) iterator.next();
			resultList.add(row);
			}
		}
		return resultList;
	}

}