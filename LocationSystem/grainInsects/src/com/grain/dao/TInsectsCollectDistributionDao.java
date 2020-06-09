package com.grain.dao;

import com.grain.entity.TInsectscollectdistribution;

/**
 * Dao - 昆虫采集分布信息
 * @author Mark
 *
 */
public interface TInsectsCollectDistributionDao extends BaseDao<TInsectscollectdistribution, String>{

	boolean insectsCollectDistributionExists(String smInsects);

	TInsectscollectdistribution findBySMInsects(String smInsects);

}
