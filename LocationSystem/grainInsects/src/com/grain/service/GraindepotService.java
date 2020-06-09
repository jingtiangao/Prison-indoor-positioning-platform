package com.grain.service;

import java.util.List;

import com.grain.entity.Graindepot;


/**
 * Service - 粮仓
 * 
 */
public interface GraindepotService extends BaseService<Graindepot, String> {

	//查找区域内的所有示范库
	public List<Graindepot> findModelDepotsWithinArea(Long area);
	
	//查找所有示范库
	public List<Graindepot> findModelDepots();
	
}