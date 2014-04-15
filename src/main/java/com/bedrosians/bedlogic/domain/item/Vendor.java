package com.bedrosians.bedlogic.domain.item;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.bedrosians.bedlogic.util.FormatUtil;


@Entity
@Table(name="ims_vendor")
public class Vendor implements java.io.Serializable {

	private static final long serialVersionUID = -582265865921787L;
	
	private Integer vendorOrder;
	private String vendorName;
	private String vendorName2;
	private String vendorXrefId;
	private BigDecimal vendorListPrice;
	private BigDecimal vendorNetPrice;
	private String vendorPriceUnit;
	private String vendorFob;
	private Float vendorDiscountPct;
	private Integer vendorPriceRoundAccuracy;
	private Float vendorMarkupPct = 0F;
	private Float vendorFreightRateCwt = 0F;
	private BigDecimal vendorLandedBaseCost = new BigDecimal(0.00);
	private Integer leadTime;
	private Float dutyPct;
	private Item item;

	ItemVendorId itemVendorId = new ItemVendorId();
		
	public Vendor() {
	}
	
	public Vendor(ItemVendorId itemVendorId) {
		this.itemVendorId = itemVendorId;
	}
	
	
	@JsonIgnore
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "itemcd", column = @Column(name = "itemcd", nullable = false, length = 20)),
		@AttributeOverride(name = "vendorId", column = @Column(name = "vendor_id", nullable = false, precision = 10, scale = 0)) })
    public ItemVendorId getItemVendorId() {
		return this.itemVendorId;
	}

	public void setItemVendorId(ItemVendorId itemVendorId) {
		this.itemVendorId = itemVendorId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="itemcd", updatable=false, insertable=false)
	public Item getItem(){
		return this.item;
	}
	
	public void setItem(Item item){
		this.item = item;
	}

	@Transient
	public Long getVendorId(){
		return this.itemVendorId.getVendorId();
	}
	
	@Column(name = "vendor_order", length = 2)
	public Integer getVendorOrder() {
		return this.vendorOrder;
	}

	public void setVendorOrder(Integer vendorOrder) {
		this.vendorOrder = vendorOrder;
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
		return FormatUtil.process(vendorFob);
	}

	public void setVendorFob(String vendorFob) {
		this.vendorFob = vendorFob;
	}

	@Column(name = "vendor_list_price", precision = 9, scale = 4)
	public BigDecimal getVendorListPrice() {
		return FormatUtil.process(vendorListPrice);
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

	@Column(name = "vendorlandedbasecost", precision = 13, scale = 6)
	public BigDecimal getVendorLandedBaseCost() {
		return this.vendorLandedBaseCost;
	}

	public void setVendorLandedBaseCost(BigDecimal vendorLandedBaseCost) {
		this.vendorLandedBaseCost = vendorLandedBaseCost;
	}
	
	@Column(name = "vendor_freight_rate_cwt", precision = 9, scale = 4)
	public Float getVendorFreightRateCwt() {
		return this.vendorFreightRateCwt;
	}

	public void setVendorFreightRateCwt(Float freightRateCwt) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemVendorId == null) ? 0 : itemVendorId.hashCode());
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
		Vendor other = (Vendor) obj;
		if (itemVendorId == null) {
			if (other.itemVendorId != null)
				return false;
		} else if (!itemVendorId.equals(other.itemVendorId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vendor ["
				+ "vendorId=" + itemVendorId.getVendorId() 
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
