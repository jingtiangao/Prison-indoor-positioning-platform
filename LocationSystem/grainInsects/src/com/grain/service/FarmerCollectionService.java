package com.grain.service;


import com.grain.entity.TInsectsCollectionOnFarmer;
import com.grain.service.BaseService;

/**
 * Service - 农户本地储粮害虫采集
 * 
 */
public interface FarmerCollectionService extends BaseService<TInsectsCollectionOnFarmer, String> {

	boolean farmerCollectionExists(String smCollection);

	TInsectsCollectionOnFarmer findBySMCollection(String smCollection);

}