package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TBinInvestDao;
import com.grain.entity.TBinInvest;

/**
 * Dao - 粮库害虫分仓调查表
 * 
 */
@Repository("tbinInvestDaoImpl")
public class TBinInvestDaoImpl extends BaseDaoImpl<TBinInvest, Long> 
implements TBinInvestDao {

}