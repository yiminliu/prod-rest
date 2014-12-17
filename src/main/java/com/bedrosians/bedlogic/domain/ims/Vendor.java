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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name="ims_item_vendor")
@DynamicUpdate
@DynamicInsert
//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vendor implements java.io.Serializable {

	private static final long serialVersionUID = -582265865921787L;
	
	private VendorId vendorId = new VendorId();
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
	private Integer version;
	private KeymarkVendor keymarkVendor;
	private Ims ims;
		
	public Vendor() {
	}
	
	public Vendor(VendorId vendorId) {
		this.vendorId = vendorId;
	}
	
	@JsonIgnore
	@IndexedEmbedded
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "itemCode", column = @Column(name = "item_code", nullable = false, length = 20)),
		@AttributeOverride(name = "id", column = @Column(name = "vendor_id", nullable = false, precision = 10, scale = 0)) })
    public VendorId getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(VendorId vendorId) {
		this.vendorId = vendorId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_code", updatable=false, insertable=false)
	@ContainedIn
	public Ims getIms(){
		return this.ims;
	}
	
	public void setIms(Ims ims){
		this.ims = ims;
	}
/*
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="productVendorId.vendorId")//, updatable=false, insertable=false)
	public Vendor getVendor(){
		return this.vendor;
	}
		
	public void setVendor(Vendor vendor){
		this.vendor = vendor;
	}
*/	
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="vendor_id", insertable = false, updatable = false)
	public KeymarkVendor getKeymarkVendor() {
		return keymarkVendor;
	}

	public void setKeymarkVendor(KeymarkVendor keymarkVendor) {
		this.keymarkVendor = keymarkVendor;
	}

	@Transient
	public Integer getId(){
		return this.vendorId.getId();
	}
	
	public void setId(Integer id){
	   this.vendorId.setId(id);
	}
	
	@Column(name = "vendor_order", length = 2)
	public Integer getVendorOrder() {
		return this.vendorOrder;
	}

	public void setVendorOrder(Integer vendorOrder) {
		this.vendorOrder = vendorOrder;
	}
	
	@Transient
	//@Column(name = "vendor_name", length = 60)
	public String getVendorName() {
		//return vendorName;
		return (keymarkVendor == null)? null : keymarkVendor.getName();
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
		//return new BigDecimal(vendorListPrice.floatValue() * vendorDiscountPct/100.00);
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

	@Column(name = "vendor_landed_base_cost", precision = 13, scale = 6, updatable=false)
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

	@JsonIgnore
	@Version
	@Column(name = "version")
	public Integer getVersion(){
		return version;
	}
	
	private void setVersion(Integer version){
		this.version = version;
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
		
	
	public Vendor(VendorId vendorId, Integer vendorOrder,
			String vendorName, String vendorName2, String vendorXrefId,
			BigDecimal vendorListPrice, BigDecimal vendorNetPrice,
			String vendorPriceUnit, String vendorFob, Float vendorDiscountPct,
			Integer vendorPriceRoundAccuracy, Float vendorMarkupPct,
			Float vendorFreightRateCwt, BigDecimal vendorLandedBaseCost,
			Integer leadTime, Float dutyPct, Integer version, Ims ims) {
		super();
		this.vendorId = vendorId;
		this.vendorOrder = vendorOrder;
		this.vendorName = vendorName;
		this.vendorName2 = vendorName2;
		this.vendorXrefId = vendorXrefId;
		this.vendorListPrice = vendorListPrice;
		this.vendorNetPrice = vendorNetPrice;
		this.vendorPriceUnit = vendorPriceUnit;
		this.vendorFob = vendorFob;
		this.vendorDiscountPct = vendorDiscountPct;
		this.vendorPriceRoundAccuracy = vendorPriceRoundAccuracy;
		this.vendorMarkupPct = vendorMarkupPct;
		this.vendorFreightRateCwt = vendorFreightRateCwt;
		this.vendorLandedBaseCost = vendorLandedBaseCost;
		this.leadTime = leadTime;
		this.dutyPct = dutyPct;
		this.version = version;
		this.ims = ims;
	}

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
		Vendor other = (Vendor) obj;
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
	
	@Override
	public String toString() {
		return "Vendor ["
				+ "vendorId=" + vendorId.getId() 
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
