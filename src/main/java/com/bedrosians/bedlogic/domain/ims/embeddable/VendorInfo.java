package com.bedrosians.bedlogic.domain.ims.embeddable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Store;

@Embeddable
public class VendorInfo  implements java.io.Serializable {

	private static final long serialVersionUID = -338982221787L;
	
	    private Integer vendornbr;
	    private Integer vendornbr1;
	    private Integer vendornbr2;
		private String vendorxrefcd;
		private BigDecimal vendorlistprice = BigDecimal.ZERO;
		private String vendorpriceunit = "PCS";
		private String vendorfob;
		private Float vendordiscpct = 0F;
		private Integer vendorroundaccuracy = 1;
		private BigDecimal vendornetprice = BigDecimal.ZERO;
		private Float vendormarkuppct = 0F;
		private Float vendorfreightratecwt = 0F;
		private Float dutypct;
		private Integer leadtime;
		//To meet ims_check5 constraint
		private BigDecimal vendorlandedbasecost = BigDecimal.ZERO;
		private Float vendordiscpct2 = 0F;
		private Float vendordiscpct3 = 0F;

		public VendorInfo(){}

		@Column(name = "vendorxrefcd", length = 30)
		public String getVendorxrefcd() {
			return this.vendorxrefcd;
		}

		public void setVendorxrefcd(String vendorxrefcd) {
			this.vendorxrefcd = vendorxrefcd;
		}

		@Column(name = "vendornbr", precision = 6, scale = 0)
		public Integer getVendornbr() {
			return this.vendornbr;
		}

		public void setVendornbr(Integer vendornbr) {
			this.vendornbr = vendornbr;
		}

		@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
		@NumericField
		@Column(name = "vendornbr1", precision = 6, updatable=false)
		public Integer getVendornbr1() {
			return this.vendornbr1;
		}

		public void setVendornbr1(Integer vendornbr1) {
			this.vendornbr1 = vendornbr1;
		}
		
		@Column(name = "vendornbr2", precision = 6, scale = 0)
		public Integer getVendornbr2() {
			return this.vendornbr2;
		}

		public void setVendornbr2(Integer vendornbr2) {
			this.vendornbr2 = vendornbr2;
		}

		@Column(name = "vendorpriceunit", length = 4)
		public String getVendorpriceunit() {
			return this.vendorpriceunit;
		}

		public void setVendorpriceunit(String vendorpriceunit) {
			this.vendorpriceunit = vendorpriceunit;
		}

		@Column(name = "vendorfob", length = 10)
		public String getVendorfob() {
			return this.vendorfob;
		}

		public void setVendorfob(String vendorfob) {
			this.vendorfob = vendorfob;
		}
	   
		@Column(name = "vendorlistprice", precision = 9, scale = 4)
		public BigDecimal getVendorlistprice() {
			return this.vendorlistprice;
		}

		public void setVendorlistprice(BigDecimal vendorlistprice) {
			this.vendorlistprice = vendorlistprice;
		}

		@Column(name = "vendordiscpct1", precision = 5)
		public Float getVendordiscpct() {
			return this.vendordiscpct;
		}

		public void setVendordiscpct(Float vendordiscpct) {
			this.vendordiscpct = vendordiscpct;
		}

		@Column(name = "vendordiscpct2", precision = 5)
		public Float getVendordiscpct2() {
			return this.vendordiscpct2;
		}

		public void setVendordiscpct2(Float vendordiscpct2) {
			this.vendordiscpct2 = vendordiscpct2;
		}

		@Column(name = "vendordiscpct3", precision = 5)
		public Float getVendordiscpct3() {
			return this.vendordiscpct3;
		}

		public void setVendordiscpct3(Float vendordiscpct3) {
			this.vendordiscpct3 = vendordiscpct3;
		}

		@Column(name = "vendorroundaccuracy", precision = 1, scale = 0)
		public Integer getVendorroundaccuracy() {
			return this.vendorroundaccuracy;
		}

		public void setVendorroundaccuracy(Integer vendorroundaccuracy) {
			this.vendorroundaccuracy = vendorroundaccuracy;
		}

		@Column(name = "vendornetprice", precision = 9, scale = 4, updatable=false)
		public BigDecimal getVendornetprice() {
			return this.vendornetprice;
		}

		public void setVendornetprice(BigDecimal vendornetprice) {
			this.vendornetprice = vendornetprice;
		}

		@Column(name = "vendormarkuppct", precision = 4, scale = 1)
		public Float getVendormarkuppct() {
			return this.vendormarkuppct;
		}

		public void setVendormarkuppct(Float vendormarkuppct) {
			this.vendormarkuppct = vendormarkuppct;
		}

		@Column(name = "vendorfreightratecwt", precision = 9, scale = 4)
		public Float getVendorfreightratecwt() {
			return this.vendorfreightratecwt;
		}

		public void setVendorfreightratecwt(Float vendorfreightratecwt) {
			this.vendorfreightratecwt = vendorfreightratecwt;
		}

		@Column(name = "vendorlandedbasecost", precision = 13, scale = 6, updatable=false)
		public BigDecimal getVendorlandedbasecost() {
			return this.vendorlandedbasecost;
		}

		public void setVendorlandedbasecost(BigDecimal vendorlandedbasecost) {
			this.vendorlandedbasecost = vendorlandedbasecost;
		}
		
		@Column(name = "dutypct", precision = 7, scale = 4)
		public Float getDutypct() {
			return this.dutypct;
		}

		public void setDutypct(Float dutypct) {
			this.dutypct = dutypct;
		}
		
		@Column(name = "leadtime", precision = 4, scale = 0)
		public Integer getLeadtime() {
			return this.leadtime;
		}

		public void setLeadtime(Integer leadtime) {
			this.leadtime = leadtime;
		}
		
		@Transient
		public boolean isDefault(){
		  return vendornbr1 == null && vendornbr2 == null && vendorlistprice == BigDecimal.ZERO && vendorfob == null && vendornetprice == BigDecimal.ZERO;
		}
}
