package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.DepotCleankillDao;
import com.grain.entity.DepotCleankill;
import com.grain.service.DepotCleankillService;

@Service("depotCleankillServiceImpl")
public class DepotCleankillServiceImpl 
	extends BaseServiceImpl<DepotCleankill, Long>
	implements DepotCleankillService{
	
	@Resource(name = "depotCleankillDaoImpl")
	DepotCleankillDao dao;
	
	@Resource(name = "depotCleankillDaoImpl")
	public void setBaseDao(DepotCleankillDao dao){
		super.setBaseDao(dao);		
	}

}
