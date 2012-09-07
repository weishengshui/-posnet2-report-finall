package com.chinarewards.posnet2.report.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Report_Role")
public class Report_Role {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -131608202580078267L;
	
	@Id
	@Column(name="role_id")
	private String role_id;
	
	@Column(name="role_name")
	private String role_name;
	
	@Column(name="descn")
	private String descn;
	
	public Report_Role() {
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}
	
	
}
