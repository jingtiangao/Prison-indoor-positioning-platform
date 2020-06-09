package com.grain.dao.user;

import com.grain.dao.BaseDao;
import com.grain.entity.user.Factoryuser;

/**
 * Dao - 加工厂
 *
 */

public interface FactoryuserDao extends BaseDao<Factoryuser, String>{
	
	boolean usernameExists(String username);

	Factoryuser findByUsername(String username);

}
