package com.grain.dao;

import com.grain.entity.InsectsOnfactory;

/**
 * Dao - 加工厂害虫采集
 * @author Mark
 *
 */
public interface FactoryCollectionDao extends BaseDao<InsectsOnfactory, String>{

	boolean factoryCollectionExists(String smCollect);

	/**
	 * 
	 * 
	 * @param smCollection 采虫记录编号
	 * @return
	 */
	InsectsOnfactory findBySMCollection(String smCollect);

}
