package com.location.dao.Impl.user;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.location.dao.user.GroupInfoDao;
import com.location.entity.GroupInfo;
@Repository("groupInfoDaoImpl")
public class GroupInfoDaoImpl extends BaseDaoImpl<GroupInfo, String> implements GroupInfoDao{

	@Override
	public GroupInfo findByName(String groupName) {
		// TODO Auto-generated method stub
		if(groupName==null){
			return null;
		}
		try {
			String jpql = "select groupInfo from GroupInfo groupInfo where lower(groupInfo.groupName) = lower(:groupName)";
			return entityManager.createQuery(jpql, GroupInfo.class).setFlushMode(FlushModeType.COMMIT).setParameter("groupName", groupName).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
