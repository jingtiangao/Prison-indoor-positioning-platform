package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.GrainbinDao;
import com.grain.entity.Grainbin;

/**
 * Dao - 粮仓
 * 
 */
@Repository("grainbinDaoImpl")
public class GrainbinDaoImpl extends BaseDaoImpl<Grainbin, String> 
implements GrainbinDao {


}