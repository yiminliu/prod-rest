package com.bedrosians.bedlogic.domain.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class ProductVendorId implements Serializable{

	private static final long serialVersionUID = -5432421787L;
	
	private String itemCode;
	private Integer vendorId;

	public ProductVendorId() {
	}

	public ProductVendorId(String itemCode, Integer vendorId) {
		this.itemCode = itemCode;
		this.vendorId = vendorId;
	}

	@JsonIgnore
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Column(name = "item_code")//, nullable = false)
	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@NumericField
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
		if (!(other instanceof ProductVendorId))
			return false;
		ProductVendorId castOther = (ProductVendorId) other;

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

