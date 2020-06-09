package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TBinProtectedDao;
import com.grain.entity.TBinProtected;
import com.grain.service.TBinProtectedService;

/*
 * 防护剂分仓调查表
 */
@Service("tbinProtectedServiceImpl")
public class TBinProtectedServiceImpl extends BaseServiceImpl<TBinProtected,Long> implements TBinProtectedService{

	@Resource(name = "tbinProtectedDaoImpl")
	TBinProtectedDao dao;
	
	@Resource(name = "tbinProtectedDaoImpl")
	public void setBaseDao(TBinProtectedDao dao){
		super.setBaseDao(dao);
	}
	
}
