package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.CompanyDao;

import com.grain.entity.Company;

@Repository("companyDaoImpl")
public class CompanyDaoImpl extends BaseDaoImpl<Company, Long> implements CompanyDao {


}
