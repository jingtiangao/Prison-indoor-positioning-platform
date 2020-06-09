package com.grain.dao;

import com.grain.entity.TInsectsInbinOnDepot;

/**
 * Dao - 储备库害虫采集
 * @author Mark
 *
 */
public interface DepotCollectionDao extends BaseDao<TInsectsInbinOnDepot, String>{

	boolean depotCollectionExists(String smCollection);

	/**
	 * 
	 * 
	 * @param smCollection 采虫记录编号
	 * @return
	 */
	TInsectsInbinOnDepot findBySMCollection(String smCollection);

}
