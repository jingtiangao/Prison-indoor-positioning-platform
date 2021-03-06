package com.location.entity;
// Generated 2016-11-26 12:39:38 by Hibernate Tools 3.6.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LsGroupInfo generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ls_group_info", catalog = "graininsects")
public class GroupInfo implements java.io.Serializable {

	private int groupId;
	private String groupName;
	private int maxDistance;
	private int createtime;//add by gxz 2016/12/09

	public GroupInfo() {
	}

	public GroupInfo(int groupId, String groupName, int maxDistance) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.maxDistance = maxDistance;
	}

	@Id

	@Column(name = "group_id", unique = true, nullable = false)
	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Column(name = "group_name", unique = true,nullable = false, length = 30)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "max_distance", nullable = false)
	public int getMaxDistance() {
		return this.maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	@Column(name = "createtime", nullable = false)
	public int getCreatetime() {
		return createtime;
	}

	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}

}
