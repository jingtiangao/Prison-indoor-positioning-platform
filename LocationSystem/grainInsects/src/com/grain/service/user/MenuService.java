package com.grain.service.user;

import java.util.List;

import com.grain.EasyUItreeNode;
import com.grain.MenuNode;

public interface MenuService {
	/**
	 * 根据用户id取得菜单节点
	 * @param sid
	 * @return
	 */
	public List<MenuNode> getMenuNode(Integer sid);
	/**
	 * 
	 * @param userid	role.id
	 * @param menuid     菜单id 
	 * @return
	 */
	public List<EasyUItreeNode> getEasyNode(String username,String menuid);
}
