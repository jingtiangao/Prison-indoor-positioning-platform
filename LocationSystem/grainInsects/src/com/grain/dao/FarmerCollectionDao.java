package com.grain.dao;


import com.grain.dao.BaseDao;
import com.grain.entity.TInsectsCollectionOnFarmer;

/**
 * Dao - 本地储粮害虫采集
 * @author Mark
 *
 */
public interface FarmerCollectionDao extends BaseDao<TInsectsCollectionOnFarmer, String>{

	boolean insectsCollectionExists(String smCollection);

	/**
	 * 
	 * 
	 * @param smCollection 采虫记录编号
	 * @return
	 */
	TInsectsCollectionOnFarmer findBySMCollection(String smCollection);

}
