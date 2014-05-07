package com.bedrosians.bedlogic.domain.item.embeddable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class VendorInfo  implements java.io.Serializable {

	private static final long serialVersionUID = -338982221787L;
	
	//------ vendors ------//
		//Used to update the existing items in ims table
	    private Integer vendornbr;
	    private Integer vendornbr1;
	    private Integer vendornbr2;
	    private Integer vendornbr3;
		private String vendorxrefcd;
		private BigDecimal vendorlistprice = BigDecimal.valueOf(0.00);
		private String vendorpriceunit = "PCS";
		private String vendorfob;
		private Float vendordiscpct = 0F;
		private Integer vendorroundaccuracy = 1;
		private BigDecimal vendornetprice = new BigDecimal(0.00);
		private Float vendormarkuppct = 0F;
		private Float vendorfreightratecwt = 0F;
		private Float dutypct;
		private Integer leadtime;
		//To meet ims_check5 constraint
		private BigDecimal vendorLandedBaseCost = new BigDecimal(0.00);
		private Float vendordiscpct2 = 0F;
		private Float vendordiscpct3 = 0F;


		@Column(name = "vendorxrefcd", length = 30)
		public String getVendorxrefcd() {
			return FormatUtil.process(this.vendorxrefcd);
		}

		public void setVendorxrefcd(String vendorxrefcd) {
			this.vendorxrefcd = vendorxrefcd;
		}

		@Column(name = "vendornbr", precision = 6, scale = 0)
		public Integer getVendornbr() {
			return FormatUtil.process(this.vendornbr);
		}

		public void setVendornbr(Integer vendornbr) {
			this.vendornbr = vendornbr;
		}

		@Column(name = "vendorpriceunit", length = 4)
		public String getVendorpriceunit() {
			return FormatUtil.process(this.vendorpriceunit);
		}

		public void setVendorpriceunit(String vendorpriceunit) {
			this.vendorpriceunit = vendorpriceunit;
		}

		@Column(name = "vendorfob", length = 10)
		public String getVendorfob() {
			return FormatUtil.process(this.vendorfob);
		}

		public void setVendorfob(String vendorfob) {
			this.vendorfob = vendorfob;
		}
	   
		@Column(name = "vendorlistprice", precision = 9, scale = 4)
		public BigDecimal getVendorlistprice() {
			return FormatUtil.process(this.vendorlistprice);
		}

		public void setVendorlistprice(BigDecimal vendorlistprice) {
			this.vendorlistprice = vendorlistprice;
		}

		@Column(name = "vendordiscpct1", precision = 5)
		public Float getVendordiscpct() {
			return FormatUtil.process(this.vendordiscpct);
		}

		public void setVendordiscpct(Float vendordiscpct) {
			this.vendordiscpct = vendordiscpct;
		}

		@Column(name = "vendordiscpct2", precision = 5)
		public Float getVendordiscpct2() {
			return FormatUtil.process(this.vendordiscpct2);
		}

		public void setVendordiscpct2(Float vendordiscpct2) {
			this.vendordiscpct2 = vendordiscpct2;
		}

		@Column(name = "vendordiscpct3", precision = 5)
		public Float getVendordiscpct3() {
			return FormatUtil.process(this.vendordiscpct3);
		}

		public void setVendordiscpct3(Float vendordiscpct3) {
			this.vendordiscpct3 = vendordiscpct3;
		}

		@Column(name = "vendorroundaccuracy", precision = 1, scale = 0)
		public Integer getVendorroundaccuracy() {
			return FormatUtil.process(this.vendorroundaccuracy);
		}

		public void setVendorroundaccuracy(Integer vendorroundaccuracy) {
			this.vendorroundaccuracy = vendorroundaccuracy;
		}

		@Column(name = "vendornetprice", precision = 9, scale = 4)
		public BigDecimal getVendornetprice() {
			return FormatUtil.process(this.vendornetprice);
		}

		public void setVendornetprice(BigDecimal vendornetprice) {
			this.vendornetprice = vendornetprice;
		}

		@Column(name = "vendormarkuppct", precision = 4, scale = 1)
		public Float getVendormarkuppct() {
			return FormatUtil.process(this.vendormarkuppct);
		}

		public void setVendormarkuppct(Float vendormarkuppct) {
			this.vendormarkuppct = vendormarkuppct;
		}

		@Column(name = "vendorfreightratecwt", precision = 9, scale = 4)
		public Float getVendorfreightratecwt() {
			return FormatUtil.process(this.vendorfreightratecwt);
		}

		public void setVendorfreightratecwt(Float vendorfreightratecwt) {
			this.vendorfreightratecwt = vendorfreightratecwt;
		}

		@Column(name = "vendorlandedbasecost", precision = 13, scale = 6)
		public BigDecimal getVendorLandedBaseCost() {
			return FormatUtil.process(this.vendorLandedBaseCost);
		}

		public void setVendorLandedBaseCost(BigDecimal vendorLandedBaseCost) {
			this.vendorLandedBaseCost = vendorLandedBaseCost;
		}

		@Column(name = "vendornbr1", precision = 6, scale = 0)
		public Integer getVendornbr1() {
			return FormatUtil.process(this.vendornbr1);
		}

		public void setVendornbr1(Integer vendornbr1) {
			this.vendornbr1 = vendornbr1;
		}
		
		@Column(name = "vendornbr2", precision = 6, scale = 0)
		public Integer getVendornbr2() {
			return FormatUtil.process(this.vendornbr2);
		}

		public void setVendornbr2(Integer vendornbr2) {
			this.vendornbr2 = vendornbr2;
		}

		@Column(name = "vendornbr3", precision = 6, scale = 0)
		public Integer getVendornbr3() {
			return FormatUtil.process(this.vendornbr3);
		}

		public void setVendornbr3(Integer vendornbr3) {
			this.vendornbr3 = vendornbr3;
		}
		
		@Column(name = "dutypct", precision = 7, scale = 4)
		public Float getDutypct() {
			return FormatUtil.process(this.dutypct);
		}

		public void setDutypct(Float dutypct) {
			this.dutypct = dutypct;
		}
		
		@Column(name = "leadtime", precision = 4, scale = 0)
		public Integer getLeadtime() {
			return FormatUtil.process(this.leadtime);
		}

		public void setLeadtime(Integer leadtime) {
			this.leadtime = leadtime;
		}
}
