package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TCatalogPicDao;
import com.grain.entity.TCatalogPic;

/**
 * Dao - 害虫类别图片信息
 * 
 */
@Repository("catalogPicDaoImpl")
public class TCatalogPicDaoImpl extends BaseDaoImpl<TCatalogPic, Long> implements TCatalogPicDao {

}
