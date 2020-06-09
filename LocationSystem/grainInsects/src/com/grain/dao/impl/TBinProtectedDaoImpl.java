package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TBinProtectedDao;
import com.grain.entity.TBinProtected;

/**
 * Dao - 防护剂分仓调查表
 * 
 */
@Repository("tbinProtectedDaoImpl")
public class TBinProtectedDaoImpl extends BaseDaoImpl<TBinProtected, Long> implements TBinProtectedDao {

}
