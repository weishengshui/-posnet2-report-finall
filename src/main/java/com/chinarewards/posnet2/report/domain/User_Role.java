package com.chinarewards.posnet2.report.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="Report_User_Role")
@IdClass(UserRolePK.class)
public class User_Role  implements Serializable {
	
	private static final long serialVersionUID = 2103977872382089771L;
	
	@Id
	@Column(name="username")
	private String username;
	@Id
	@Column(name="rolename")
	private String rolename;
	
	public User_Role() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
}
