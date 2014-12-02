package com.bedrosians.bedlogic.domain.ims;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.bedrosians.bedlogic.util.FormatUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="apv")
@DynamicUpdate
@DynamicInsert
//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class KeymarkVendor implements java.io.Serializable {

	private static final long serialVersionUID = -582265865921787L;
	
	private Integer vendorId;
	private String name;
	private String abaName;
	private String fobCode;
		
	public KeymarkVendor() {
	}
	
	public KeymarkVendor(Integer vendorId) {
		this.vendorId = vendorId;
	}
	
	@Id
	@Column(name = "vendornbr")
    public Integer getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}


	
	@Column(name = "name", length = 35)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "abaname", length = 40)
	public String getAbaName() {
		return this.abaName;
	}

	public void setAbaName(String abaName) {
		this.abaName = abaName;
	}

	@Column(name = "fobcd", length = 10)
	public String getFobCode() {
		return FormatUtil.process(fobCode);
	}

	public void setFobCode(String fobCode) {
		this.fobCode = fobCode;
	}


	//@JsonIgnore
		//@ManyToOne(fetch = FetchType.EAGER)
		//@JoinColumn(name="productVendorId.vendorId")//, updatable=false, insertable=false)
		//public Vendor getVendor(){
		//	return this.vendor;
		//}
		
		//public void setVendor(Vendor vendor){
		//	this.vendor = vendor;
		//}
		
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
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
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}
	
	@JsonIgnore
	@Transient
	static public List<String> allProperties(){
		return Arrays.asList("vendorId", "vendorName", "vendorXrefId", "vendorPriceUnit", "vendorFob", "vendorlistprice", "vendorNetPrice", "vendorDiscountPct", 
				               "vendorPriceRoundAccuracy" , "leadTime", "vendorFreightRateCwt", "dutyPct");
	}

	
	@JsonIgnore
	@Transient
	public boolean isEmpty(){
		return vendorId == null;
		//&& vendorName == null && vendorXrefId == null && vendorListPrice == null && 
		//	   vendorNetPrice == null && vendorPriceUnit == null && vendorFob == null;
	}
	
	

}
