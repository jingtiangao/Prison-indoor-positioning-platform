package com.grain.service.user;


import java.util.List;

import com.grain.entity.user.DepotUser;
import com.grain.service.BaseService;


/**
 * Service - 管理员
 * 
 */
public interface DepotUserService extends BaseService<DepotUser, String> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 根据用户名查找管理员
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 管理员，若不存在则返回null
	 */
	DepotUser findByUsername(String username);

	/**
	 * 根据ID查找权限
	 * 
	 * @param id
	 *            ID
	 */
	boolean hasAutohrize(String userid,String targetURI);

	/**
	 * 判断管理员是否登录
	 * 
	 * @return 管理员是否登录
	 */
	boolean isAuthenticated();

	/**
	 * 获取当前登录管理员
	 * 
	 * @return 当前登录管理员,若不存在则返回null
	 */
	DepotUser getCurrent();

	/**
	 * 获取当前登录用户名
	 * 
	 * @return 当前登录用户名,若不存在则返回null
	 */
	String getCurrentUsername();

	List<Object[]> findGriandepot(Long graindirectionid);

	boolean loginCheck(DepotUser user);

}