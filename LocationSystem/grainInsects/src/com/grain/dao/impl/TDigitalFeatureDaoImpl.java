package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TDigitalFeatureDao;
import com.grain.entity.TDigitalFeature;
/**
 * Dao - 数字识别特征
 * 
 */
@Repository("tdigitalfeatureDaoImpl")
public class TDigitalFeatureDaoImpl extends BaseDaoImpl<TDigitalFeature, Long> 
implements TDigitalFeatureDao{

}
