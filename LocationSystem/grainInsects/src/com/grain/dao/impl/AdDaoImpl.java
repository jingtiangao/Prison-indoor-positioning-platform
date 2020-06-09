package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.AdDao;
import com.grain.entity.Ad;

/**
 * Dao - 广告
 * 
 */
@Repository("adDaoImpl")
public class AdDaoImpl extends BaseDaoImpl<Ad, Long> implements AdDao {

}