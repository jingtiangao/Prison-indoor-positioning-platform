package com.grain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.GraindepotDao;
import com.grain.entity.Graindepot;
import com.grain.service.GraindepotService;

@Service("grainDepotServiceImpl")
public class GraindepotServiceImpl 
	extends BaseServiceImpl<Graindepot,String>
	implements GraindepotService{
	
	@Resource(name = "graindepotDaoImpl")
	GraindepotDao dao;
	
	@Resource(name = "graindepotDaoImpl")
	public void setBaseDao(GraindepotDao dao){
		super.setBaseDao(dao);
		
	}
	
	@Transactional(readOnly = true)
	public List<Graindepot> findModelDepotsWithinArea(Long area){
		return dao.findModelDepots(null, area);
	}
	
	@Transactional(readOnly = true)
	public List<Graindepot> findModelDepots(){
		return dao.findModelDepots(null, null);
	}
}
