package com.chinarewards.posnet2.report.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="report_role_authorities")
public class Role_Authorities  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7782046758548562633L;
	@Id
	@Column(name="rolename")
	private String rolename;
	@Id
	@Column(name="authority")
	private String authority;
	
	public Role_Authorities() {
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

}
