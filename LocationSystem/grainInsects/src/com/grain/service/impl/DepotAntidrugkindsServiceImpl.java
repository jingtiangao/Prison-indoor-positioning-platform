package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.DepotAntidrugkindsDao;
import com.grain.entity.DepotAntidrugkinds;
import com.grain.service.DepotAntidrugkindsService;
/*
 * 抗药虫种总表
 */
@Service("depotAntidrugkindsServiceImpl")
public class DepotAntidrugkindsServiceImpl 
	extends BaseServiceImpl<DepotAntidrugkinds,Long>
	implements DepotAntidrugkindsService{
	
	@Resource(name = "depotAntidrugkindsDaoImpl")
	DepotAntidrugkindsDao dao;
	
	@Resource(name = "depotAntidrugkindsDaoImpl")
	public void setBaseDao(DepotAntidrugkindsDao dao){
		super.setBaseDao(dao);
		
	}

}
