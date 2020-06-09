package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TBinPhosphinefumigationDao;
import com.grain.entity.TBinPhosphinefumigation;
import com.grain.service.TBinPhosphinefumigationService;

/*
 * 磷化氢熏蒸分仓调查表
 */
@Service("tbinPhosphinefumigationServiceImpl")
public class TBinPhosphinefumigationServiceImpl extends BaseServiceImpl<TBinPhosphinefumigation, Long> implements TBinPhosphinefumigationService{

	@Resource(name = "tbinPhosphinefumigationDaoImpl")
	TBinPhosphinefumigationDao dao;
	
	@Resource(name = "tbinPhosphinefumigationDaoImpl")
	public void setBaseDao(TBinPhosphinefumigationDao dao){
		super.setBaseDao(dao);
	}
	
}
