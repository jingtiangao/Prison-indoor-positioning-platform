package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TCatalogIconDao;
import com.grain.entity.TCatalogIcon;
import com.grain.service.TCatalogIconService;

@Service("tCatalogIconServiceImpl")
public class TCatalogIconServiceImpl extends BaseServiceImpl<TCatalogIcon, Long> implements TCatalogIconService {

	@Resource(name = "tCatalogIconDaoImpl")
	public void setBaseDao(TCatalogIconDao tCatalogIconDao) {
		super.setBaseDao(tCatalogIconDao);
	}
}
