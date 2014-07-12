package com.bedrosians.bedlogic.domain.account.embeddable;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
public class Credit{
	
	private Float creditLimit;
	private String creditStatus;
	private Date lastCreditApprovalDate;
	private String cgCredit;
	private Date creditStatChgDate;
	private String  creditStatNote ;
	
	
	@Column(name="CreditStatus")
	public String getCreditStatus() {
		return creditStatus;
	}
	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}
	
	@Column(name="CreditLimit")
	public Float getCreditLimit() {
		return creditLimit;
	}
	
	public void setCreditLimit(Float creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	@Column(name="cg_credit")
	public String getCgCredit() {
		return cgCredit;
	}
	public void setCgCredit(String cgCredit) {
		this.cgCredit = cgCredit;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="LastCredApRecvdDate")
	public Date getLastCreditApprovalDate() {
		return lastCreditApprovalDate;
	}
	
	public void setLastCreditApprovalDate(Date lastCreditApprovalDate) {
		this.lastCreditApprovalDate = lastCreditApprovalDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="credstatchgdate")
	public Date getCreditStatChgDate() {
		return creditStatChgDate;
	}
	public void setCreditStatChgDate(Date creditStatChgDate) {
		this.creditStatChgDate = creditStatChgDate;
	}
	
	@Column(name="credstatnote")
	public String getCreditStatNote() {
		return creditStatNote;
	}
	public void setCreditStatNote(String creditStatNote) {
		this.creditStatNote = creditStatNote;
	}
}
