package com.grain.service.user;

import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.user.FarmerUser;
import com.grain.service.BaseService;

/**
 * Service - 农户
 * 
 */
public interface FarmerUserService extends BaseService<FarmerUser, String>{
	

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 根据用户名查找加农户
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 农户，若不存在则返回null
	 */
	FarmerUser findByUsername(String username);

	/**
	 * 根据ID查找权限
	 * 
	 * @param id
	 *            ID
	 */
	boolean hasAutohrize(String userid, String targetURI);

	/**
	 * 判断管理员是否登录
	 * 
	 * @return 农户用户是否登录
	 */
	boolean isAuthenticated();

	/**
	 * 获取当前登录农户用户
	 * 
	 * @return 当前登录农户用户,若不存在则返回null
	 */
	FarmerUser getCurrent();

	/**
	 * 获取当前登录用户名
	 * 
	 * @return 当前登录用户名,若不存在则返回null
	 */
	String getCurrentUsername();

	Page<FarmerUser> findPage(Pageable pageable, FarmerUser queryParm);
	
	boolean loginCheck(FarmerUser farmerUser);

	void updatePassword(FarmerUser farmerUser);
}
