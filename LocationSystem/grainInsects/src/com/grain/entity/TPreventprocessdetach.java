package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 防治工艺附件表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_preventprocessdetach")
public class TPreventprocessdetach implements java.io.Serializable {

	private int id;
	private TPreventprocess TPreventprocess;
	private String detach;
	private String source;

	@Id

	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public TPreventprocess getTPreventprocess() {
		return this.TPreventprocess;
	}

	public void setTPreventprocess(TPreventprocess TPreventprocess) {
		this.TPreventprocess = TPreventprocess;
	}

	@Column(name = "detach", nullable = false)
	public String getDetach() {
		return this.detach;
	}

	public void setDetach(String detach) {
		this.detach = detach;
	}

	@Column(name = "source", length = 100)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
