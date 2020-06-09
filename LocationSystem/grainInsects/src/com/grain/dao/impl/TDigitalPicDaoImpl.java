package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TDigitalPicDao;
import com.grain.entity.TDigitalPic;

@Repository("digitalPicDaoImpl")
public class TDigitalPicDaoImpl extends BaseDaoImpl<TDigitalPic, Long> implements TDigitalPicDao {

}
