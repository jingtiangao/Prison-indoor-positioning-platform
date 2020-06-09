package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.GrainDao;
import com.grain.entity.Grain;
import com.grain.service.GrainService;
/*
 * 粮食信息 t_grain
 */
@Service("grainServiceImpl")
public class GrainServiceImpl 
	extends BaseServiceImpl<Grain,Long>
	implements GrainService{
	
	@Resource(name = "grainDaoImpl")
	GrainDao dao;
	
	@Resource(name = "grainDaoImpl")
	public void setBaseDao(GrainDao dao){
		super.setBaseDao(dao);
		
	}

	@Override
	public Grain findLast(String lcbm) {
		
		return dao.findLast(lcbm) ;
	}
	
}
