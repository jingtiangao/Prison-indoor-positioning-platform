package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.CompanyDao;
import com.grain.dao.TInsectsIndexDao;
import com.grain.entity.Company;
import com.grain.service.CompanyService;

@Service("companyServiceImpl")
public class CompanyServiceImpl extends BaseServiceImpl<Company,Long> implements CompanyService {
	
	@Resource(name = "companyDaoImpl")
	CompanyDao dao;
	
	@Resource(name = "companyDaoImpl")
	public void setBaseDao(CompanyDao dao){
		super.setBaseDao(dao);
		
	}

}
