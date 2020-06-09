package com.grain.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.DepotInertaerosolDao;
import com.grain.entity.DepotInertaerosol;
import com.grain.service.DepotInertaerosolService;
/*
 * 惰性气溶液总表 
 * @author ll
 */
@Service("depotInertaerosolServiceImpl")
public class DepotInertaerosolServiceImpl 
	extends BaseServiceImpl<DepotInertaerosol,Long>
	implements DepotInertaerosolService{
	
	@Resource(name = "depotInertaerosolDaoImpl")
	DepotInertaerosolDao dao;
	
	@Resource(name = "depotInertaerosolDaoImpl")
	public void setBaseDao(DepotInertaerosolDao dao){
		super.setBaseDao(dao);
		
	}

}



