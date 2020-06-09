package com.grain.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.grain.dao.InfoReportDao;
import com.grain.entity.InfoReport;


/**
 * Dao - 粮仓信息上报
 * 
 */
@Repository("infoReportDaoImpl")
public class InfoReportDaoImpl extends BaseDaoImpl<InfoReport, Long> implements InfoReportDao {

	public List<InfoReport> findInfoReportWithUsername(Integer count, String username){
		String jpql = "select i from InfoReport i where username =?1";
		TypedQuery<InfoReport> query = entityManager.createQuery(jpql, InfoReport.class).setFlushMode(FlushModeType.COMMIT);
		query.setParameter(1, username);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}
}
