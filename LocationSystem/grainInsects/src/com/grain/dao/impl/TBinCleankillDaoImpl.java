package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TBinCleankillDao;
import com.grain.entity.TBinCleankill;

/**
 * Dao - 分仓空仓杀虫调查表
 * 
 */
@Repository("tBinCleankillDaoImpl")
public class TBinCleankillDaoImpl extends BaseDaoImpl<TBinCleankill, Long>
	implements TBinCleankillDao{

}
