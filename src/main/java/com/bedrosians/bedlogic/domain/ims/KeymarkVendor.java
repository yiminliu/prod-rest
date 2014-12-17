package com.bedrosians.bedlogic.domain.ims;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.bedrosians.bedlogic.util.FormatUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="apv")
@DynamicUpdate
@DynamicInsert
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class KeymarkVendor implements java.io.Serializable {

	private static final long serialVersionUID = -582265865921787L;
	
	private Integer vendorNumber;
	private String name;
	private String dbaName;
	private String fobCode;
	//private Vendor vendor;
		
	public KeymarkVendor() {
	}
	
	public KeymarkVendor(Integer vendorNumber) {
		this.vendorNumber = vendorNumber;
	}
	
	@Id
	@Column(name = "vendornbr")
    public Integer getvendorNumber() {
		return this.vendorNumber;
	}

	public void setvendorNumber(Integer vendorNumber) {
		this.vendorNumber = vendorNumber;
	}

	@Column(name = "name", length = 35)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "dbaname", length = 40)
	public String getDbaName() {
		return this.dbaName;
	}

	public void setDbaName(String dbaName) {
		this.dbaName = dbaName;
	}

	@Column(name = "fobcd", length = 10)
	public String getFobCode() {
		return FormatUtil.process(fobCode);
	}

	public void setFobCode(String fobCode) {
		this.fobCode = fobCode;
	}
/*
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="vendorNumber.vendorNumber")//, updatable=false, insertable=false)
	public Vendor getVendor(){
		return this.vendor;
	}
		
	public void setVendor(Vendor vendor){
		this.vendor = vendor;
	}
*/	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vendorNumber == null) ? 0 : vendorNumber.hashCode());
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
		KeymarkVendor other = (KeymarkVendor) obj;
		if (vendorNumber == null) {
			if (other.vendorNumber != null)
				return false;
		} else if (!vendorNumber.equals(other.vendorNumber))
			return false;
		return true;
	}
	
	@JsonIgnore
	@Transient
	static public List<String> allProperties(){
		return Arrays.asList("vendorNumber", "vendorName", "vendorXrefId", "vendorPriceUnit", "vendorFob", "vendorlistprice", "vendorNetPrice", "vendorDiscountPct", 
				               "vendorPriceRoundAccuracy" , "leadTime", "vendorFreightRateCwt", "dutyPct");
	}

	
	@JsonIgnore
	@Transient
	public boolean isEmpty(){
		return vendorNumber == null;
		//&& vendorName == null && vendorXrefId == null && vendorListPrice == null && 
		//	   vendorNetPrice == null && vendorPriceUnit == null && vendorFob == null;
	}
	
	

}
