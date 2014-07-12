package com.bedrosians.bedlogic.domain.miscellaneous;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;


@Entity
@NamedQuery(
		name = "findLastCustCd",
		query = "from Obmh"
	)
@Table(name="obmh")
public class Obmh {
		
	private String name;
	private long lastCustId;

	@Id
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="lastcustseq")
	public long getLastCustId() {
		return lastCustId;
	}

	public void setLastCustId(long lastCustId) {
		this.lastCustId = lastCustId;
	}		
	
	
	

}
