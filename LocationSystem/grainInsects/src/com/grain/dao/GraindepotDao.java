package com.grain.dao;

import java.util.List;

import com.grain.entity.Graindepot;

public interface GraindepotDao extends BaseDao<Graindepot,String> {

	//查找区域内所有示范库 查找所有示范库
	public List<Graindepot> findModelDepots(Integer count, Long area);
}
