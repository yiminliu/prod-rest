package com.bedrosians.bedlogic.domain.item;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name="ims_vendor")
public class Vendor implements java.io.Serializable {

	private long vendorId;
	private String vendorName;
	private String vendorName2;
	private String vendorXrefId;
	private BigDecimal vendorListPrice;
	private BigDecimal vendorNetPrice;
	private String vendorPriceUnit;
	private String vendorFob;
	private Float vendorDiscountPct;
	private Integer vendorPriceRoundAccuracy;
	private Float vendorMarkupPct = 0.0F;
	private BigDecimal vendorFreightRateCwt;
	private Integer leadTime;
	private Float dutyPct;
	private String itemcd;
	private Item item;

	
	public Vendor() {
	}

	public Vendor(long vendorId) {
		this.vendorId = vendorId;
	}


	@Id
	@Column(name="vendor_id")
	public long getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="itemcd")
	public Item getItem(){
		return this.item;
	}
	
	public void setItem(Item item){
		this.item = item;
	}
	
	@Column(name = "itemcd", length = 15, insertable = false, updatable = false)
	public String getItemcd() {
		return itemcd;
	}

	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}
	
	@Column(name = "vendor_name", length = 60)
	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Column(name = "vendor_name2", length = 60)
	public String getVendorName2() {
		return this.vendorName2;
	}

	public void setVendorName2(String vendorName2) {
		this.vendorName2 = vendorName2;
	}

	@Column(name = "vendor_xref_id", length = 60)
	public String getVendorXrefId() {
		return this.vendorXrefId;
	}

	public void setVendorXrefId(String vendorXrefId) {
		this.vendorXrefId = vendorXrefId;
	}

	@Column(name = "vendor_price_unit", length = 4)
	public String getVendorPriceUnit() {
		return this.vendorPriceUnit;
	}

	public void setVendorPriceUnit(String vendorPriceUnit) {
		this.vendorPriceUnit = vendorPriceUnit;
	}

	@Column(name = "vendor_fob", length = 10)
	public String getVendorFob() {
		return this.vendorFob;
	}

	public void setVendorFob(String vendorFob) {
		this.vendorFob = vendorFob;
	}

	@Column(name = "vendor_list_price", precision = 9, scale = 4)
	public BigDecimal getVendorListPrice() {
		return vendorListPrice;
	}

	public void setVendorListPrice(BigDecimal vendorListPrice) {
		this.vendorListPrice = vendorListPrice;
	}

	@Column(name = "vendor_discount_pct", precision = 5)
	public Float getVendorDiscountPct() {
		return this.vendorDiscountPct;
	}

	public void setVendorDiscountPct(Float vendorDiscountPct) {
		this.vendorDiscountPct = vendorDiscountPct;
	}

	@Column(name = "vendor_round_accuracy", precision = 1, scale = 0)
	public Integer getVendorPriceRoundAccuracy() {
		return this.vendorPriceRoundAccuracy;
	}

	public void setVendorPriceRoundAccuracy(Integer vendorPriceRoundAccuracy) {
		this.vendorPriceRoundAccuracy = vendorPriceRoundAccuracy;
	}

	@Column(name = "vendor_net_price", precision = 9, scale = 4)
	public BigDecimal getVendorNetPrice() {
		return this.vendorNetPrice;
	}

	public void setVendorNetPrice(BigDecimal vendorNetPrice) {
		this.vendorNetPrice = vendorNetPrice;
	}

	@Column(name = "vendor_markup_pct", precision = 4, scale = 1)
	public Float getVendorMarkupPct() {
		return vendorMarkupPct;
	}

	public void setVendorMarkupPct(Float vendorMarkupPct) {
		this.vendorMarkupPct = vendorMarkupPct;
	}

	@Column(name = "lead_time", precision = 4, scale = 0)
	public Integer getLeadTime() {
		return this.leadTime;
	}

	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}

	@Column(name = "vendor_freight_rate_cwt", precision = 9, scale = 4)
	public BigDecimal getVendorFreightRateCwt() {
		return this.vendorFreightRateCwt;
	}

	public void setVendorFreightRateCwt(BigDecimal freightRateCwt) {
		this.vendorFreightRateCwt = freightRateCwt;
	}

	@Column(name = "duty_pct", precision = 7, scale = 4)
	public Float getDutyPct() {
		return this.dutyPct;
	}

	public void setDutyPct(Float dutyPct) {
		this.dutyPct = dutyPct;
	}

	@Override
	public String toString() {
		return "Vendor ["
				+ "vendorId=" + vendorId 
				+ ", vendorName=" + vendorName
				+ ", vendorName2=" + vendorName2 
				+ ", vendorXrefId=" + vendorXrefId 
				+ ", vendorPriceUnit=" + vendorPriceUnit
				+ ", vendorFob=" + vendorFob 
				+ ", vendorlistprice=" + vendorListPrice
				+ ", vendorDiscountPct=" + vendorDiscountPct
				+ ", vendorPriceRoundAccuracy=" + vendorPriceRoundAccuracy
				+ ", vendorNetPrice=" + vendorNetPrice 
				+ ", leadTime=" + leadTime 
				+ ", vendorFreightRateCwt=" + vendorFreightRateCwt
				+ ", dutyPct=" + dutyPct 
				+ "]";
	}

}
