package com.bedrosians.bedlogic.domain.item;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnore;

@Embeddable
public class ItemVendorId implements Serializable{

	private static final long serialVersionUID = -5432421787L;
	
	private String itemCode;
	private Integer vendorId;

	public ItemVendorId() {
	}

	public ItemVendorId(String itemCode, Integer vendorId) {
		this.itemCode = itemCode;
		this.vendorId = vendorId;
	}

	@JsonIgnore
	@Column(name = "item_code")//, nullable = false)
	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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

		return ((this.getItemCode() == castOther.getItemCode()) || 
				(this.getItemCode() != null && castOther.getItemCode() != null && 
				this.getItemCode().equals(castOther.getItemCode()))) && 
				(this.getVendorId() == castOther.getVendorId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getItemCode() == null ? 0 : this.getItemCode().hashCode());
		result = 37 * result + getVendorId().intValue();
		return result;
	}

}

