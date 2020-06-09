package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.DepotPhosphinefumigationDao;
import com.grain.entity.DepotPhosphinefumigation;
import com.grain.service.DepotPhosphinefumigationService;

@Service("depotPhosphinefumigationServiceImpl")
public class DepotPhosphinefumigationServiceImpl 
	extends BaseServiceImpl<DepotPhosphinefumigation, Long>
	implements DepotPhosphinefumigationService{
	
	@Resource(name = "depotPhosphinefumigationDaoImpl")
	DepotPhosphinefumigationDao dao;
	
	@Resource(name = "depotPhosphinefumigationDaoImpl")
	public void setBaseDao(DepotPhosphinefumigationDao dao){
		super.setBaseDao(dao);
		
	}

}
