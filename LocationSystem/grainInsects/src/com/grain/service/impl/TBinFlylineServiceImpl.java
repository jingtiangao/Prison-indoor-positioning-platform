package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TBinFlylineDao;
import com.grain.entity.TBinFlyline;
import com.grain.service.TBinFlylineService;
/*
 * 防虫线防治调查
 */
@Service("tBinFlylineServiceImpl")
public class TBinFlylineServiceImpl 
	extends BaseServiceImpl<TBinFlyline,Long>
	implements TBinFlylineService{
	
	@Resource(name = "tBinFlylineDaoImpl")
	TBinFlylineDao dao;
	
	@Resource(name = "tBinFlylineDaoImpl")
	public void setBaseDao(TBinFlylineDao dao){
		super.setBaseDao(dao);
		
	}

}
