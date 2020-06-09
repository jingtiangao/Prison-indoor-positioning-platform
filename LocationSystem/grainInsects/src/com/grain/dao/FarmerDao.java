package com.grain.dao;

import com.grain.entity.Farmer;

/**
 * Dao - 农户
 * @author Mark
 *
 */
public interface FarmerDao extends BaseDao<Farmer, String>{

	/**
	 * 农户是否存在
	 * @param smFarmer
	 * @return
	 */
	boolean farmerExists(String smFarmer);

	/**
	 * 根据农户编码查找农户
	 * @param smFarmer
	 * @return
	 */
	Farmer findBySMFarmer(String smFarmer);

}
