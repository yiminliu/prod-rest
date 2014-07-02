package com.bedrosians.bedlogic.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CheckPayment {
	
	@Id
	@Column(name="seqnbr")
	private int seqnbr;
	
	@Column(name="ordernbr")
	private int orderNo;
	
	@Column(name="shipmentnbr")
	private int shipmentNo;
	
	@Column(name="companyname")
	private String companyName;
	
	@Column(name="checknbr")
	private Long checkNo;
	
	@Column(name="checkamt")
	private double amount;

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getShipmentNo() {
		return shipmentNo;
	}

	public void setShipmentNo(int shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

	public Long getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(Long checkNo) {
		this.checkNo = checkNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}