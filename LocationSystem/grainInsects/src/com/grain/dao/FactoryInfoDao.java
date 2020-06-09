package com.grain.dao;

import com.grain.entity.FactoryInfo;

/**
 * Dao - 加工厂
 * @author Mark
 *
 */
public interface FactoryInfoDao extends BaseDao<FactoryInfo, String>{

	/**
	 * 加工厂是否存在
	 * @param smFactory
	 * @return
	 */
	boolean factoryExists(String smFactory);

	/**
	 * 根据工厂编码查找工厂
	 * @param smFarmer
	 * @return
	 */
	FactoryInfo findBySMFactory(String smFactory);

}
