package com.grain.dao.impl;



import org.springframework.stereotype.Repository;

import com.grain.dao.TInsectsIndexDao;
import com.grain.entity.TInsectsIndex;


/**
 * Dao - 检索表
 * 
 */

@Repository("TInsectsIndexDaoImpl")
public class TInsectsIndexDaoImpl extends BaseDaoImpl<TInsectsIndex, Long> implements TInsectsIndexDao {

}
