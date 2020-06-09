package com.location.dao.Impl.user;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.location.dao.user.UserAreaDao;
import com.location.entity.UserArea;
@Repository("userAreaDaoImpl")
public class UserAreaDaoImpl extends BaseDaoImpl<UserArea, String> implements UserAreaDao{

}
