package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TInsectsInbinOnDepotDao;
import com.grain.entity.TInsectsInbinOnDepot;
import com.grain.service.TInsectsInbinOnDepotService;
/*
 * 农户储粮害虫现场采集记录表
 */
@Service("tInsectsInbinOnDepotServiceImpl")
public class TInsectsInbinOnDepotServiceImpl 
	extends BaseServiceImpl<TInsectsInbinOnDepot,Long>
	implements TInsectsInbinOnDepotService{
	
	@Resource(name = "tInsectsInbinOnDepotDaoImpl")
	TInsectsInbinOnDepotDao dao;
	
	@Resource(name = "tInsectsInbinOnDepotDaoImpl")
	public void setBaseDao(TInsectsInbinOnDepotDao dao){
		super.setBaseDao(dao);
		
	}

}
