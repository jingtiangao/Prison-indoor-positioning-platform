package com.grain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.grain.dao.TConsultationDao;
import com.grain.entity.TConsultation;
import com.grain.service.TConsultationService;

@Service("tconsultationServiceImpl")
public class TConsultationServiceImpl extends BaseServiceImpl<TConsultation, Long>
	implements TConsultationService {
	
	@Resource(name = "tconsultationDaoImpl")
	TConsultationDao dao;
	
	@Resource(name = "tconsultationDaoImpl")
	public void setBaseDao(TConsultationDao dao){
		super.setBaseDao(dao);		
	}

	@Override
	public String findExpert() {
		String sql = "select tb.username, tb.count, t_consultation.type FROM (SELECT t_expert.username, count(t_consultation.expertuser) as count from t_expert"
				+ " LEFT JOIN t_consultation on t_expert.username=t_consultation.expertuser"
				+ " GROUP BY t_expert.username) as tb left join t_consultation on tb.username=t_consultation.expertuser"
				+ " GROUP BY t_consultation.type having tb.count=MAX(tb.count)";
		List list;
		if((list=dao.findBysql(sql)) != null) {
			if(list.get(0) != null) {
				Object[] params = (Object[]) list.get(0);
				String expertname = (String) params[0];
				return expertname;
			}
		}
		return null;
	}
}
