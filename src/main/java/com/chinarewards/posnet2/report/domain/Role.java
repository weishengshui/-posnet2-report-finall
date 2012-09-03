package com.chinarewards.posnet2.report.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="report_role")
public class Role  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2508794065204258241L;

	@Id
	@Column(name="rolename")
	private String rolename;
	
	@Column(name="descn")
	private String descn;
	
	public Role() {
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}
	
	
	
}
