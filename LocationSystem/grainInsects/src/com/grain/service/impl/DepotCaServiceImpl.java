package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.DepotCaDao;
import com.grain.entity.DepotCa;
import com.grain.service.DepotCaService;

/*
 * 氮气气调总表 
 */
@Service("depotCaServiceImpl")
public class DepotCaServiceImpl extends BaseServiceImpl<DepotCa,Long>
implements DepotCaService{
	@Resource(name = "depotCaDaoImpl")
	DepotCaDao dao;
	
	@Resource(name = "depotCaDaoImpl")
	public void setBaseDao(DepotCaDao dao){
		super.setBaseDao(dao);
		
	}
}
