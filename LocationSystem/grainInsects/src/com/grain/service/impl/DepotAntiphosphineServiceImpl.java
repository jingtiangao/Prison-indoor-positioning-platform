package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.DepotAntiphosphineDao;
import com.grain.entity.DepotAntiphosphine;
import com.grain.service.DepotAntiphosphineService;
/*
 * 磷化氢抗性调查汇总表
 */
@Service("depotAntiphosphineServiceImpl")
public class DepotAntiphosphineServiceImpl 
	extends BaseServiceImpl<DepotAntiphosphine,Long>
	implements DepotAntiphosphineService{
	
	@Resource(name = "depotAntiphosphineDaoImpl")
	DepotAntiphosphineDao dao;
	
	@Resource(name = "depotAntiphosphineDaoImpl")
	public void setBaseDao(DepotAntiphosphineDao dao){
		super.setBaseDao(dao);
		
	}

}
