package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TInsectsSpecificationDao;
import com.grain.entity.TInsectsSpecification;


@Repository("tInsectsSpecificationDaoImpl")
public class TInsectsSpecificationDaoImpl extends BaseDaoImpl<TInsectsSpecification, Long> implements TInsectsSpecificationDao {

}
