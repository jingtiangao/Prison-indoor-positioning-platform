package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.GraindirectionDao;
import com.grain.entity.Graindirection;
import com.grain.service.GraindirectionService;
/**
 * @author Administrator
 *	储粮区
 */
@Service("graindirectionServiceImpl")
public class GraindirectionServiceImpl 
	extends BaseServiceImpl<Graindirection,Long>
	implements GraindirectionService{
	
	@Resource(name = "graindirectionDaoImpl")
	GraindirectionDao dao;
	
	@Resource(name = "graindirectionDaoImpl")
	public void setBaseDao(GraindirectionDao dao){
		super.setBaseDao(dao);
		
	}
}
