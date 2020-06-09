package com.grain.dao;

import java.util.List;

import com.grain.entity.DeviceRealdata;

public interface DeviceRealdataDao extends BaseDao<DeviceRealdata,Long> {
	
	//查找所有虫种
	public List<String> findAllKinds(Integer count);
	
	//查找一个粮仓内的所有设备来源
	public List<String> findAllSources(String lcbm, Integer count);
}
