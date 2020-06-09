package com.grain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grain.entity.user.Expert;

/**
 * 咨询
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_consultation")
public class TConsultation implements java.io.Serializable {
	// Fields

		private Long id;
		//@JsonBackReference
		private Expert expert;
		private String username;
		private String title;
		private Integer type;
		private String describle;
		private String consulttime;
		private String expertadvice;
		private Date experttime;
		private boolean sfzw;
		//private Long zwID;
		@JsonIgnore
		private TConsultation zw;

		private boolean hasshare;

		// Constructors

		/** default constructor */
		public TConsultation() {
		}

		/** minimal constructor */
		public TConsultation(Long id, String username, String title, Integer type) {
			this.id = id;
			this.username = username;
			this.title = title;
			this.type = type;
		}

		/** full constructor */
		public TConsultation(Long id, Expert expert, String username,
				String title, Integer type, String describle, String expertadvice) {
			this.id = id;
			this.expert = expert;
			this.username = username;
			this.title = title;
			this.type = type;
			this.describle = describle;
			this.expertadvice = expertadvice;
		}

		// Property accessors
		
		@Id
		@Column(name = "ID", unique = true, nullable = false)
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "expertuser")
		@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
				"consultationList","password","mobile","modifer","modifydate"
				})	
		public Expert getExpert() {
			return this.expert;
		}
		
		public void setExpert(Expert expert) {
			this.expert = expert;
		}
		
		@Column(name = "username", nullable = false)
		public String getUsername() {
			return this.username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
		
		@Column(name = "title", nullable = false)
		public String getTitle() {
			return this.title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
		@Column(name = "type", nullable = false)
		public Integer getType() {
			return this.type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
		
		@Column(name = "describle", nullable = true)
		public String getDescrible() {
			return this.describle;
		}

		public void setDescrible(String describle) {
			this.describle = describle;
		}
		
		@Column(name = "expertadvice", nullable = true)
		public String getExpertadvice() {
			return this.expertadvice;
		}

		public void setExpertadvice(String expertadvice) {
			this.expertadvice = expertadvice;
		}
		
		@Column(name = "experttime", nullable = true)
		public Date getExperttime() {
			return experttime;
		}

		public void setExperttime(Date experttime) {
			this.experttime = experttime;
		}
		
		@Column(name = "consulttime", nullable = false)
		public String getConsulttime() {
			return consulttime;
		}

		public void setConsulttime(String consulttime) {
			this.consulttime = consulttime;
		}
		
		@Column(name = "sfzw")
		public boolean isSfzw() {
			return sfzw;
		}

		public void setSfzw(boolean sfzw) {
			this.sfzw = sfzw;
		}
		
/*		@Column(name = "zwID", nullable = true)
		public Long getZwID() {
			return zwID;
		}

		public void setZwID(Long zwID) {
			this.zwID = zwID;
		}*/
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "zwID")
		public TConsultation getZw() {
			return zw;
		}

		public void setZw(TConsultation zw) {
			this.zw = zw;
		}
		
		@Column(name = "hasshare", nullable = false)
		public boolean isHasshare() {
			return hasshare;
		}

		public void setHasshare(boolean hasshare) {
			this.hasshare = hasshare;
		}
		
		
		
		
		
		
}
