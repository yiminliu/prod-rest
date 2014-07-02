package com.bedrosians.bedlogic.domain.account.embeddable;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
public class Contract{
		
	private Date contractStartDate;
	private Date contractEndDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="contract_sdate")
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="contract_edate")
	public Date getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	
	
}
