package com.grain.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TInsectsIndexDao;
import com.grain.entity.TInsectsIndex;

import com.grain.service.TInsectsIndexService;

/*
 * 检索表
 */


@Service("TInsectsIndexServiceImpl")
public class TInsectsIndexServiceImpl extends BaseServiceImpl<TInsectsIndex,Long> implements TInsectsIndexService {
	
	@Resource(name = "TInsectsIndexDaoImpl")
	TInsectsIndexDao dao;
	
	@Resource(name = "TInsectsIndexDaoImpl")
	public void setBaseDao(TInsectsIndexDao dao){
		super.setBaseDao(dao);
		
	}

}
