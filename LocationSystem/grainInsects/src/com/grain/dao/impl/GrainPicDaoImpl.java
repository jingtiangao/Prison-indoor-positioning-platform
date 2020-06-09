package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.GrainPicDao;
import com.grain.entity.GrainPic;

/**
 * Dao - 粮食图片信息
 * 
 */
@Repository("grainPicDaoImpl")
public class GrainPicDaoImpl extends BaseDaoImpl<GrainPic, Long> 
implements GrainPicDao {


}