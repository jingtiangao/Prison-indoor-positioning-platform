package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TBinCaDao;
import com.grain.entity.TBinCa;
import com.grain.service.TBinCaService;

/**
 * 氮气分仓调查表
 * 
 * */
@Service("tbinCaServiceImpl")
public class TBinCaServiceImpl extends BaseServiceImpl<TBinCa, Long> implements TBinCaService {

	@Resource(name = "tbinCaDaoImpl")
	TBinCaDao dao;
	
	@Resource(name = "tbinCaDaoImpl")
	public void setBaseDao(TBinCaDao dao){
		super.setBaseDao(dao);
		
	}
	
}
