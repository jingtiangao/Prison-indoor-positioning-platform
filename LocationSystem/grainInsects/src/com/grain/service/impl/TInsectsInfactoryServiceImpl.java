package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TInsectsInfactoryDao;
import com.grain.entity.TInsectsInfactory;
import com.grain.service.TInsectsInfactoryService;

@Service("tInsectsInfactoryServiceImpl")
public class TInsectsInfactoryServiceImpl 
	extends BaseServiceImpl<TInsectsInfactory,Long>
	implements TInsectsInfactoryService{
	
	@Resource(name = "tInsectsInfactoryDaoImpl")
	TInsectsInfactoryDao dao;
	
	@Resource(name = "tInsectsInfactoryDaoImpl")
	public void setBaseDao(TInsectsInfactoryDao dao){
		super.setBaseDao(dao);
		
	}

}
