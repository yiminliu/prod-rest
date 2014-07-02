package com.bedrosians.bedlogic.domain.account.embeddable;

import javax.persistence.Column;

public class Owner {
	
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerDriverLicenseNo;
	
	public Owner(){}
			
	public Owner(String ownerFirstName, String ownerLastName, String ownerDriverLicenseNo) {
		super();
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerDriverLicenseNo = ownerDriverLicenseNo;
	}

	@Column(name = "OwnerFirstName")
	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	@Column(name = "OwnerLastName")
	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	@Column(name = "OwnerDriverLicNbr")
	public String getOwnerDriverLicenseNo() {
		return ownerDriverLicenseNo;
	}

	public void setOwnerDriverLicenseNo(String ownerDriverLicenseNo) {
		this.ownerDriverLicenseNo = ownerDriverLicenseNo;
	}
	
}
