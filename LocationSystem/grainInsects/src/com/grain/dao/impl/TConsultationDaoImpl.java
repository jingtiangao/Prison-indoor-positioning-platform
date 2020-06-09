package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TConsultationDao;
import com.grain.entity.TConsultation;

/**
 * Dao - 咨询
 *
 */
@Repository("tconsultationDaoImpl")
public class TConsultationDaoImpl extends BaseDaoImpl<TConsultation, Long>
	implements TConsultationDao {

}
