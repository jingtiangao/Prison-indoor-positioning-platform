package com.grain.service.user;

import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.user.Factoryuser;
import com.grain.service.BaseService;

/**
 * Service - 加工厂
 * 
 */
public interface FactoryuserService extends BaseService<Factoryuser, String> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 根据用户名查找加工厂
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 加工厂，若不存在则返回null
	 */
	Factoryuser findByUsername(String username);

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
	 * @return 加工厂用户是否登录
	 */
	boolean isAuthenticated();

	/**
	 * 获取当前登录加工厂用户
	 * 
	 * @return 当前登录加工厂用户,若不存在则返回null
	 */
	Factoryuser getCurrent();

	/**
	 * 获取当前登录用户名
	 * 
	 * @return 当前登录用户名,若不存在则返回null
	 */
	String getCurrentUsername();
	
	Page<Factoryuser> findPage(Pageable pageable, Factoryuser queryParm);
	
	boolean loginCheck(Factoryuser factoryuser);

	void updatePassword(Factoryuser factoryuser);
}
