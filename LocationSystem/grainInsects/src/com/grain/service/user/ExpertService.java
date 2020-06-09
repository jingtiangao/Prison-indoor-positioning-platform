package com.grain.service.user;

import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.user.Expert;
import com.grain.service.BaseService;

/**
 * Service - 专家用户
 * 
 */
public interface ExpertService extends BaseService<Expert, String> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 根据用户名查找专家用户
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 专家用户，若不存在则返回null
	 */
	Expert findByUsername(String username);

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
	 * @return 专家用户是否登录
	 */
	boolean isAuthenticated();

	/**
	 * 获取当前登录专家用户
	 * 
	 * @return 当前登录专家用户,若不存在则返回null
	 */
	Expert getCurrent();

	/**
	 * 获取当前登录用户名
	 * 
	 * @return 当前登录用户名,若不存在则返回null
	 */
	String getCurrentUsername();

	Page<Expert> findPage(Pageable pageable, Expert queryParm);
	
	boolean loginCheck(Expert expert);
}