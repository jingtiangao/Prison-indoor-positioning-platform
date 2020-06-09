package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.DepotProtectedDao;
import com.grain.entity.DepotProtected;
import com.grain.service.DepotProtectedService;

/*
 * 防护剂调查总表 
 */
@Service("depotProtectedServiceImpl")
public class DepotProtectedServiceImpl extends BaseServiceImpl<DepotProtected,Long>
implements DepotProtectedService{
	@Resource(name = "depotProtectedDaoImpl")
	DepotProtectedDao dao;
	
	@Resource(name = "depotProtectedDaoImpl")
	public void setBaseDao(DepotProtectedDao dao){
		super.setBaseDao(dao);
		
	}
}
