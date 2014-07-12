package com.bedrosians.bedlogic.domain.account.embeddable;

import javax.persistence.Column;

public class Owner {
	
	private String firstName;
	private String lastName;
	private String driverLicenseNo;
	
	public Owner(){}
			

	@Column(name = "OwnerFirstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "OwnerLastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "OwnerDriverLicNbr")
	public String getDriverLicenseNo() {
		return driverLicenseNo;
	}

	public void setDriverLicenseNo(String driverLicenseNo) {
		this.driverLicenseNo = driverLicenseNo;
	}
	
}
