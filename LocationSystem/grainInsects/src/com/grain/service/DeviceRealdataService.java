package com.grain.service;

import java.util.List;

import com.grain.entity.DeviceRealdata;


/**
 * Service - 实时数据记录
 * 
 */
public interface DeviceRealdataService extends BaseService<DeviceRealdata, Long> {

	void saveReal(DeviceRealdata data, String lcbm, String deviceno);

	/**
	 * 查找所有虫种
	 * 
	 * @return 虫种列表
	 */
	List<String> findAllKinds();
	
	List<String> findAllSources(String lcbm);
	
}