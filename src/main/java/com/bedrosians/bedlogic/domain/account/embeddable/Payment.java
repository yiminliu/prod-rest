package com.bedrosians.bedlogic.domain.account.embeddable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
public class Payment{
	
	private String paymentTerm;
	private String isConsolidatedPayment;
	private Integer daysToPay;
	private String latePaymentChargeId;
	private Float lastPaymentAmount; 
	private Date lastPaymentDate;
	private Integer latePaymentChargeRate;
	private Integer nonSufficientFundChecks; 
	
	@Column(name="PmtTermsCd")
	public String getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	
	@Column(name="ConsolidStmt")
	public String getIsConsolidatedPayment() {
		return isConsolidatedPayment;
	}
	public void setIsConsolidatedPayment(String isConsolidatedPayment) {
		this.isConsolidatedPayment = isConsolidatedPayment;
	}
	
	@Column(name="daystopay")
	public Integer getDaysToPay() {
		return daysToPay;
	}
	public void setDaysToPay(Integer daysToPay) {
		this.daysToPay = daysToPay;
	}
	
	@Column(name="finchgcd")
	public String getLatePaymentChargeId() {
		return latePaymentChargeId;
	}
	
	public void setLatePaymentChargeId(String latePaymentChargeId) {
		this.latePaymentChargeId = latePaymentChargeId;
	}
	
	@Column(name="last_pmt_amt", columnDefinition = "numeric default 0")
    public Float getLastPaymentAmount() {
		return lastPaymentAmount;
	}
	public void setLastPaymentAmount(Float lastPaymentAmount) {
		this.lastPaymentAmount = lastPaymentAmount;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_pmt_date")
	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	
	@Column(name="FinChgRate")
	public Integer getLatePaymentChargeRate() {
		return latePaymentChargeRate;
	}
	public void setLatePaymentChargeRate(Integer latePaymentChargeRate) {
		this.latePaymentChargeRate = latePaymentChargeRate;
	}
	
	@Column(name="nsf_count", columnDefinition = "numeric default 0")
	public Integer getNonSufficientFundChecks() {
		return nonSufficientFundChecks;
	}
	public void setNonSufficientFundChecks(Integer nonSufficientFundChecks) {
		this.nonSufficientFundChecks = nonSufficientFundChecks;
	}
}
