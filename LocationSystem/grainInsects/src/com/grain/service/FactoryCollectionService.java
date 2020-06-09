package com.grain.service;


import com.grain.entity.InsectsOnfactory;
import com.grain.service.BaseService;

/**
 * Service - 加工厂害虫采集
 * 
 */
public interface FactoryCollectionService extends BaseService<InsectsOnfactory, String> {

	boolean factoryCollectionExists(String smCollect);

	InsectsOnfactory findBySMCollection(String smCollect);

}