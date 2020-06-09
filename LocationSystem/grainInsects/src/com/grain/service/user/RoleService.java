package com.grain.service.user;

import java.util.List;

import com.grain.EasyUItreeNode;
import com.grain.Principal;
import com.grain.entity.user.Role;
import com.grain.service.BaseService;

/**
 * Service - 角色
 * 
 */
public interface RoleService extends BaseService<Role, Long> {

	List<EasyUItreeNode> tree(Principal p, String id);

	void grant(Role role, Long... ids);


}