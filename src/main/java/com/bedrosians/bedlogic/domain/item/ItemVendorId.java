package com.bedrosians.bedlogic.domain.item;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnore;

@Embeddable
public class ItemVendorId implements Serializable{

	private static final long serialVersionUID = -5432421787L;
	
	private String itemcd;
	private Integer vendorId;

	public ItemVendorId() {
	}

	public ItemVendorId(String itemcd, Integer vendorId) {
		this.itemcd = itemcd;
		this.vendorId = vendorId;
	}

	@JsonIgnore
	@Column(name = "itemcd", nullable = false, length = 15)
	public String getitemcd() {
		return this.itemcd;
	}

	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}

	@Column(name = "vendor_id", nullable = false, precision = 10, scale = 0)
	public Integer getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItemVendorId))
			return false;
		ItemVendorId castOther = (ItemVendorId) other;

		return ((this.getitemcd() == castOther.getitemcd()) || 
				(this.getitemcd() != null && castOther.getitemcd() != null && 
				this.getitemcd().equals(castOther.getitemcd()))) && 
				(this.getVendorId() == castOther.getVendorId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getitemcd() == null ? 0 : this.getitemcd().hashCode());
		result = 37 * result + getVendorId().intValue();
		return result;
	}

}

