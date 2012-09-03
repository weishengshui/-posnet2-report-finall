package com.chinarewards.posnet2.report.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="report_authorities")
public class Authorities  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8495325519514117814L;
	@Id
	@Column(name="authority")
	private String authority;
	@Column(name="descn")
	private String descn;
	
	public Authorities() {
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}
	
	
}
