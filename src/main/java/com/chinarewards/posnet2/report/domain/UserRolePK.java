package com.chinarewards.posnet2.report.domain;

import java.io.Serializable;


public class UserRolePK implements Serializable {

	private static final long serialVersionUID = 5295436594945536947L;
	
	private String username;
	private String rolename;
	
	public UserRolePK() {
	}
	
	public UserRolePK(String username, String rolename) {
		this.username = username;
		this.rolename = rolename;
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

	@Override
	public int hashCode() {
		final int PRIME = 85;
		int result = 1;
		result = PRIME * result + ((username==null)?0:username.hashCode());
		result = PRIME * result + ((rolename==null)?0:rolename.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj==null){
			return false;
		}
		if(getClass()!=obj.getClass()){
			return false;
		}
		
		final UserRolePK other = (UserRolePK)obj;
		if(username==null){
			if(other.getUsername()!=null){
				return false;
			}
		}else if(!username.equals(other.getUsername())){
			return false;
		}
		if(rolename==null){
			if(other.getRolename()!=null){
				return false;
			}
		}else if(!rolename.equals(other.getRolename())){
			return false;
		}
		return true;
	}
}
