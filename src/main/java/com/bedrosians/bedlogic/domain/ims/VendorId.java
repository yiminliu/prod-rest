package com.bedrosians.bedlogic.domain.ims;

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
public class VendorId implements Serializable{

	private static final long serialVersionUID = -5432421787L;
	
	private String itemCode;
	private Integer id;

	public VendorId() {
	}
	
	public VendorId(String itemCode, Integer id) {
		this.itemCode = itemCode;
		this.id = id;
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
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VendorId))
			return false;
		VendorId castOther = (VendorId) other;

		return ((this.getItemCode() == castOther.getItemCode()) || 
				(this.getItemCode() != null && castOther.getItemCode() != null && 
				this.getItemCode().equals(castOther.getItemCode()))) && 
				(this.getId() == castOther.getId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getItemCode() == null ? 0 : this.getItemCode().hashCode());
		result = 37 * result + (getId()==null? 1 : getId().intValue());
		return result;
	}

}

