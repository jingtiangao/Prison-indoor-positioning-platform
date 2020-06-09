package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TBinAntidrugkindsDao;
import com.grain.entity.TBinAntidrugkinds;
import com.grain.service.TBinAntidrugkindsService;
/*
 * 抗药虫种分仓
 */
@Service("tBinAntidrugkindsServiceImpl")
public class TBinAntidrugkindsServiceImpl 
	extends BaseServiceImpl<TBinAntidrugkinds,Long>
	implements TBinAntidrugkindsService{
	
	@Resource(name = "tBinAntidrugkindsDaoImpl")
	TBinAntidrugkindsDao dao;
	
	@Resource(name = "tBinAntidrugkindsDaoImpl")
	public void setBaseDao(TBinAntidrugkindsDao dao){
		super.setBaseDao(dao);
		
	}

}
