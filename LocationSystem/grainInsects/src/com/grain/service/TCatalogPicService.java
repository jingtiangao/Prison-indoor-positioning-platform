package com.grain.service;

import com.grain.entity.TCatalogPic;

/**
 * Service - 分类特征图片
 * 
 */
public interface TCatalogPicService extends BaseService<TCatalogPic, Long>{

		void build(TCatalogPic pic);
}
