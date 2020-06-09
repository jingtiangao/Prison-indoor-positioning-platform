package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.InsectsOnfactoryDao;
import com.grain.entity.InsectsOnfactory;
import com.grain.service.InsectsOnfactoryService;

@Service("insectsOnfactoryServiceImpl")
public class InsectsOnfactoryServiceImpl 
	extends BaseServiceImpl<InsectsOnfactory,Long>
	implements InsectsOnfactoryService{
	
	@Resource(name = "insectsOnfactoryDaoImpl")
	InsectsOnfactoryDao dao;
	
	@Resource(name = "insectsOnfactoryDaoImpl")
	public void setBaseDao(InsectsOnfactoryDao dao){
		super.setBaseDao(dao);
		
	}

}
