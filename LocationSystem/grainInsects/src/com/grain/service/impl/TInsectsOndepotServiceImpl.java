package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TInsectsOndepotDao;
import com.grain.entity.TInsectsOndepot;
import com.grain.service.TInsectsOndepotService;

@Service("tInsectsOndepotServiceImpl")
public class TInsectsOndepotServiceImpl 
	extends BaseServiceImpl<TInsectsOndepot,Long>
	implements TInsectsOndepotService{
	
	@Resource(name = "tInsectsOndepotDaoImpl")
	TInsectsOndepotDao dao;
	
	@Resource(name = "tInsectsOndepotDaoImpl")
	public void setBaseDao(TInsectsOndepotDao dao){
		super.setBaseDao(dao);
		
	}

}
