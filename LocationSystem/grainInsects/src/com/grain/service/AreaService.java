package com.grain.service;

import java.util.List;

import com.grain.entity.Area;

/**
 * Service - 地区
 * 
 */
public interface AreaService extends BaseService<Area, Long> {

	/**
	 * 查找顶级地区
	 * 
	 * @return 顶级地区
	 */
	List<Area> findRoots();

	/**
	 * 查找顶级地区
	 * 
	 * @param count
	 *            数量
	 * @return 顶级地区
	 */
	List<Area> findRoots(Integer count);
	
	/**
	 * 查找储粮区下的全部地区
	 * 
	 * @param count
	 *            数量
	 * @param grainDirectionsId
	 *            储粮区id
	 * @return 全部地区
	 */
	List<Area> findAreasWithDirection(Long grainDirectionsId);

}