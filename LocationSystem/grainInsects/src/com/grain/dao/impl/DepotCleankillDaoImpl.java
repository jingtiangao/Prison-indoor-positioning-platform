package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.DepotCleankillDao;
import com.grain.entity.DepotCleankill;

/**
 * Dao - 空仓杀虫调查总表
 * 
 */
@Repository("depotCleankillDaoImpl")
public class DepotCleankillDaoImpl extends BaseDaoImpl<DepotCleankill, Long>
	implements DepotCleankillDao{

}
