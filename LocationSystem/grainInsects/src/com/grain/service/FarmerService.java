package com.grain.service;

import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.Farmer;

/**
 * Service - 农户
 * 
 */
public interface FarmerService extends BaseService<Farmer, String> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean farmerExists(String smFarmer);

	/**
	 * 根据用户名查找专家用户
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 专家用户，若不存在则返回null
	 */
	Farmer findBySMFarmer(String smFarmer);

	/**
	 * 获取当前登录专家用户
	 * 
	 * @return 当前登录专家用户,若不存在则返回null
	 */
	Farmer getCurrent();

	Page<Farmer> findPage(Pageable pageable, Farmer queryParm);

}