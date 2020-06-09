package com.grain.dao.user;


import com.grain.dao.BaseDao;
import com.grain.entity.user.Expert;

/**
 * Dao - 专家用户
 * @author Mark
 *
 */
public interface ExpertDao extends BaseDao<Expert, String>{

	boolean usernameExists(String username);

	Expert findByUsername(String username);

}
