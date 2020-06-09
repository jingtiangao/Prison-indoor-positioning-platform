package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TBinAntiphosphineDao;
import com.grain.entity.TBinAntiphosphine;
import com.grain.service.TBinAntiphosphineService;
/*
 * 磷化氢抗药分仓
 */
@Service("tBinAntiphosphineServiceImpl")
public class TBinAntiphosphineServiceImpl 
	extends BaseServiceImpl<TBinAntiphosphine,Long>
	implements TBinAntiphosphineService{
	
	@Resource(name = "tBinAntiphosphineDaoImpl")
	TBinAntiphosphineDao dao;
	
	@Resource(name = "tBinAntiphosphineDaoImpl")
	public void setBaseDao(TBinAntiphosphineDao dao){
		super.setBaseDao(dao);
		
	}

}
