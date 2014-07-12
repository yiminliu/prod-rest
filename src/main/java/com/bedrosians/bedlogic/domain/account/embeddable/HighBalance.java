package com.bedrosians.bedlogic.domain.account.embeddable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
public class HighBalance{
	
	private Float highBalanceAmount;
	private Date highBalanceDate;
	private Float highBalance6Amt; 
	private Date highBalance6Date;
	
	@Column(name="highbalamt")
	public Float getHighBalanceAmount() {
		return highBalanceAmount;
	}
	public void setHighBalanceAmount(Float highBalanceAmount) {
		this.highBalanceAmount = highBalanceAmount;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="highbaldate")
	public Date getHighBalanceDate() {
		return highBalanceDate;
	}
	public void setHighBalanceDate(Date highBalanceDate) {
		this.highBalanceDate = highBalanceDate;
	}
	
	@Column(name="highbalance6amt", columnDefinition = "numeric default 0")
	public Float getHighBalance6Amt() {
		return highBalance6Amt;
	}
	public void setHighBalance6Amt(Float highBalance6Amt) {
		this.highBalance6Amt = highBalance6Amt;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="highbalance6date")
	public Date getHighBalance6Date() {
		return highBalance6Date;
	}
	public void setHighBalance6Date(Date highBalance6Date) {
		this.highBalance6Date = highBalance6Date;
	}  
	
	
	
}
