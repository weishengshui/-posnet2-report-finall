package com.chinarewards.posnet2.report.domain;

import java.io.Serializable;


public class RoleAuthoritiesPK implements Serializable {

	private static final long serialVersionUID = 2038061506811237278L;
	
	
	private String rolename;
	private String authority;
	
	public RoleAuthoritiesPK() {
	}
	
	public RoleAuthoritiesPK(String rolename, String authority) {
		this.rolename = rolename;
		this.authority = authority;
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
	
	@Override
	public int hashCode() {
		final int PRIME = 78;
		int result = 1;
		result = result * PRIME +((rolename==null)?0:rolename.hashCode());
		result = result * PRIME +((authority==null)?0:authority.hashCode());
		return super.hashCode();
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
		
		final RoleAuthoritiesPK other = (RoleAuthoritiesPK)obj;
		if(rolename==null){
			if(other.getRolename()!=null){
				return false;
			}
		}else if(!rolename.equals(other.getRolename())){
			return false;
		}
		if(authority==null){
			if(other.getAuthority()!=null){
				return false;
			}
		}else if(!authority.equals(other.getAuthority())){
			return false;
		}
		return true;
	}
	
	
	
}
