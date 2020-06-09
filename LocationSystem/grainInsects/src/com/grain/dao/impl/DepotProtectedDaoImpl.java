package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.DepotProtectedDao;
import com.grain.entity.DepotProtected;

/**
 * Dao - 防护剂总表
 * 
 */
@Repository("depotProtectedDaoImpl")
public class DepotProtectedDaoImpl extends BaseDaoImpl<DepotProtected, Long>
implements DepotProtectedDao{

}
