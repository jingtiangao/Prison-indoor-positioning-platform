package com.location.dao.user;

import com.grain.dao.BaseDao;
import com.location.entity.GroupInfo;

public interface GroupInfoDao extends BaseDao<GroupInfo, String> {
public GroupInfo findByName(String groupName);
}
