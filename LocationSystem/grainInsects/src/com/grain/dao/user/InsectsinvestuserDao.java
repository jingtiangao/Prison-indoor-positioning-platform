package com.grain.dao.user;


import com.grain.dao.BaseDao;
import com.grain.entity.user.Insectsinvestuser;

/**
 * Dao - 虫调用户
 * @author Mark
 *
 */
public interface InsectsinvestuserDao extends BaseDao<Insectsinvestuser, String>{

	boolean usernameExists(String username);

	Insectsinvestuser findByUsername(String username);

}
