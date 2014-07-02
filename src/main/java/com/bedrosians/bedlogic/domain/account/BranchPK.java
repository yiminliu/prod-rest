package com.bedrosians.bedlogic.domain.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BranchPK implements Serializable {
	
	private static final long serialVersionUID = -6402519899819097510L;
	
	@Column(name="custcd")
	private String customerCode;
	
	@Column(name="branchcd")
	private String branchCode;
	
	public BranchPK(){}

	public BranchPK(String customerCode, String branchCode) {
		super();
		this.customerCode = customerCode;
		this.branchCode = branchCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerCode == null) ? 0 : customerCode.hashCode());
		result = prime * result
				+ ((branchCode == null) ? 0 : branchCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BranchPK other = (BranchPK) obj;
		if (customerCode == null) {
			if (other.customerCode != null)
				return false;
		} else if (!customerCode.equals(other.customerCode))
			return false;
		if (branchCode == null) {
			if (other.branchCode != null)
				return false;
		} else if (!branchCode.equals(other.branchCode))
			return false;
		return true;
	}
	
}
