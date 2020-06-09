package com.grain.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
	设备注册表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_device_register")
public class DeviceRegister implements java.io.Serializable {

	private String deviceno;
	private String name;
	private String kind;
	private String productor;
	private String registerdate;
	private Set<DeviceRealdata> TDeviceRealdatas = new HashSet<DeviceRealdata>(0);

	@Id
	@Column(name = "deviceno", unique = true, nullable = false)
	public String getDeviceno() {
		return deviceno;
	}


	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}
	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "kind", length = 100)
	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Column(name = "productor", length = 100)
	public String getProductor() {
		return this.productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	@Column(name = "registerdate", length = 30)
	public String getRegisterdate() {
		return this.registerdate;
	}

	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TDeviceRegister")
	public Set<DeviceRealdata> getTDeviceRealdatas() {
		return this.TDeviceRealdatas;
	}

	public void setTDeviceRealdatas(Set<DeviceRealdata> TDeviceRealdatas) {
		this.TDeviceRealdatas = TDeviceRealdatas;
	}

}
