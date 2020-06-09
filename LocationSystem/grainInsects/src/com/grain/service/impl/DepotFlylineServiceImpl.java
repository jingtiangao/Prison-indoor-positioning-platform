package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.DepotFlylineDao;
import com.grain.entity.DepotFlyline;
import com.grain.service.DepotFlylineService;
/*
 * 防虫线杀虫总表
 */
@Service("depotFlylineServiceImpl")
public class DepotFlylineServiceImpl 
	extends BaseServiceImpl<DepotFlyline,Long>
	implements DepotFlylineService{
	
	@Resource(name = "depotFlylineDaoImpl")
	DepotFlylineDao dao;
	
	@Resource(name = "depotFlylineDaoImpl")
	public void setBaseDao(DepotFlylineDao dao){
		super.setBaseDao(dao);
		
	}

}
