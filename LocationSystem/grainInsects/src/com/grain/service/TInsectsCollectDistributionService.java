package com.grain.service;

import com.grain.entity.TInsectscollectdistribution;

/**
 * Service - 昆虫采集分布信息
 * @author Mark
 * 
 */
public interface TInsectsCollectDistributionService extends BaseService<TInsectscollectdistribution, String> {

	boolean insectsCollectDistributionExists(String smInsects);

	TInsectscollectdistribution findBySMInsects(String smInsects);

}