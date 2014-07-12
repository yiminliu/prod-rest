package com.bedrosians.bedlogic.domain.product;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductVendor implements java.io.Serializable {

	private static final long serialVersionUID = -582265865921787L;
	
	private ProductVendorId productVendorId = new ProductVendorId();
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
	private Product product;
		
	public ProductVendor() {
	}
	
	public ProductVendor(ProductVendorId productVendorId) {
		this.productVendorId = productVendorId;
	}
	
	@JsonIgnore
	@IndexedEmbedded
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "itemCode", column = @Column(name = "item_code", nullable = false, length = 20)),
		@AttributeOverride(name = "vendorId", column = @Column(name = "vendor_id", nullable = false, precision = 10, scale = 0)) })
    public ProductVendorId getProductVendorId() {
		return this.productVendorId;
	}

	public void setProductVendorId(ProductVendorId productVendorId) {
		this.productVendorId = productVendorId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_code", updatable=false, insertable=false)
	@ContainedIn
	public Product getProduct(){
		return this.product;
	}
	
	public void setProduct(Product product){
		this.product = product;
	}

	@Transient
	public Integer getVendorId(){
		return this.productVendorId.getVendorId();
	}
	
	public void setVendorId(Integer vendorId){
	   this.productVendorId.setVendorId(vendorId);
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
		return vendorName;
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

	@Column(name = "vendor_net_price", precision = 9, scale = 4, updatable=false)
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
		
	
	public ProductVendor(ProductVendorId productVendorId, Integer vendorOrder,
			String vendorName, String vendorName2, String vendorXrefId,
			BigDecimal vendorListPrice, BigDecimal vendorNetPrice,
			String vendorPriceUnit, String vendorFob, Float vendorDiscountPct,
			Integer vendorPriceRoundAccuracy, Float vendorMarkupPct,
			Float vendorFreightRateCwt, BigDecimal vendorLandedBaseCost,
			Integer leadTime, Float dutyPct, Integer version, Product product) {
		super();
		this.productVendorId = productVendorId;
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
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productVendorId == null) ? 0 : productVendorId.hashCode());
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
		ProductVendor other = (ProductVendor) obj;
		if (productVendorId == null) {
			if (other.productVendorId != null)
				return false;
		} else if (!productVendorId.equals(other.productVendorId))
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
		return productVendorId == null;
		//&& vendorName == null && vendorXrefId == null && vendorListPrice == null && 
		//	   vendorNetPrice == null && vendorPriceUnit == null && vendorFob == null;
	}
	
	@Override
	public String toString() {
		return "Vendor ["
				+ "vendorId=" + productVendorId.getVendorId() 
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
