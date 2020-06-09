package com.grain.service.impl;



import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TBinInertaerosolDao;
import com.grain.entity.TBinInertaerosol;
import com.grain.service.TBinInertaerosolService;
/*
 * 惰性气溶液分仓
 * @author ll
 */
@Service("tbinInertaerosolServiceImpl")
public class TBinInertaerosolServiceImpl 
	extends BaseServiceImpl<TBinInertaerosol,Long>
	implements TBinInertaerosolService{
	
	@Resource(name = "tbinInertaerosolDaoImpl")
	TBinInertaerosolDao dao;
	
	@Resource(name = "tbinInertaerosolDaoImpl")
	public void setBaseDao(TBinInertaerosolDao dao){
		super.setBaseDao(dao);
		
	}

}





