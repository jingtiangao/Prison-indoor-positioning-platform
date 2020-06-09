package com.grain.dao.user;

import java.util.List;

import com.grain.dao.BaseDao;
import com.grain.entity.user.DepotUser;

public interface DepotUserDao extends BaseDao<DepotUser, String>{

	boolean usernameExists(String username);

	DepotUser findByUsername(String username);

	List<Object[]> findGraindepot(Long graindirectionid);

}
