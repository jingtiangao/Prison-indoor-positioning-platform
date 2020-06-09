package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.AdPositionDao;
import com.grain.entity.AdPosition;

/**
 * Dao - 广告位
 * 
 */
@Repository("adPositionDaoImpl")
public class AdPositionDaoImpl extends BaseDaoImpl<AdPosition, Long> implements AdPositionDao {

}