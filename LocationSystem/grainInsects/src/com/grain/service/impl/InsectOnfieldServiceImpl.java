package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.InsectOnfieldDao;
import com.grain.entity.InsectOnfield;
import com.grain.service.InsectOnfieldService;

@Service("insectOnfieldServiceImpl")
public class InsectOnfieldServiceImpl 
	extends BaseServiceImpl<InsectOnfield,Long>
	implements InsectOnfieldService{
	
	@Resource(name = "insectOnfieldDaoImpl")
	InsectOnfieldDao dao;
	
	@Resource(name = "insectOnfieldDaoImpl")
	public void setBaseDao(InsectOnfieldDao dao){
		super.setBaseDao(dao);
		
	}

}
