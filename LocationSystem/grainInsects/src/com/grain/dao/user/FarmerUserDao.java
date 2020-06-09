package com.grain.dao.user;

import com.grain.dao.BaseDao;
import com.grain.entity.user.Factoryuser;
import com.grain.entity.user.FarmerUser;

/**
 * Dao - 农户
 *
 */

public interface FarmerUserDao extends BaseDao<FarmerUser, String>{
	
	boolean usernameExists(String username);

	FarmerUser findByUsername(String username);
}
