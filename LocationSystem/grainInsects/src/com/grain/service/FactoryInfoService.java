package com.grain.service;

import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.FactoryInfo;

/**
 * Service - 加工厂
 * @author Mark
 *
 */
public interface FactoryInfoService extends BaseService<FactoryInfo, String> {

	boolean factoryExists(String smFactory);

	FactoryInfo findBySMFactory(String smFactory);

	FactoryInfo getCurrent();

	Page<FactoryInfo> findPage(Pageable pageable, FactoryInfo queryParm);

}