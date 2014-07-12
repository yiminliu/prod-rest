package com.bedrosians.bedlogic.domain.product.embeddable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PriorVendor  implements java.io.Serializable {

	private static final long serialVersionUID = -388582221787L;
	
    private String priorvendorpriceunit;
    private String priorvendorfob;
    private BigDecimal priorvendorlistprice;
    private Float priorvendordiscpct1;
    private Integer priorvendorroundaccuracy;
    private BigDecimal priorvendornetprice;
    private Float priorvendormarkuppct;
	private Float priorvendorfreightratecwt;
    private BigDecimal priorvendorlandedbasecost;
	
	public PriorVendor(){}
	
	@Column(name = "priorvendorpriceunit", length = 4)
	public String getPriorvendorpriceunit() {
		return this.priorvendorpriceunit;
	}

	public void setPriorvendorpriceunit(String priorvendorpriceunit) {
		this.priorvendorpriceunit = priorvendorpriceunit;
	}

	@Column(name = "priorvendorfob", length = 10)
	public String getPriorvendorfob() {
		return this.priorvendorfob;
	}

	public void setPriorvendorfob(String priorvendorfob) {
		this.priorvendorfob = priorvendorfob;
	}

	@Column(name = "priorvendorlistprice", precision = 9, scale = 4)
	public BigDecimal getPriorvendorlistprice() {
		return this.priorvendorlistprice;
	}

	public void setPriorvendorlistprice(BigDecimal priorvendorlistprice) {
		this.priorvendorlistprice = priorvendorlistprice;
	}

	@Column(name = "priorvendordiscpct1", precision = 5)
	public Float getPriorvendordiscpct1() {
		return this.priorvendordiscpct1;
	}

	public void setPriorvendordiscpct1(Float priorvendordiscpct1) {
		this.priorvendordiscpct1 = priorvendordiscpct1;
	}

	@Column(name = "priorvendorroundaccuracy", precision = 1, scale = 0)
	public Integer getPriorvendorroundaccuracy() {
		return this.priorvendorroundaccuracy;
	}

	public void setPriorvendorroundaccuracy(Integer priorvendorroundaccuracy) {
		this.priorvendorroundaccuracy = priorvendorroundaccuracy;
	}

	@Column(name = "priorvendornetprice", precision = 9, scale = 4)
	public BigDecimal getPriorvendornetprice() {
		return this.priorvendornetprice;
	}

	public void setPriorvendornetprice(BigDecimal priorvendornetprice) {
		this.priorvendornetprice = priorvendornetprice;
	}

	@Column(name = "priorvendormarkuppct", precision = 4, scale = 1)
	public Float getPriorvendormarkuppct() {
		return this.priorvendormarkuppct;
	}

	public void setPriorvendormarkuppct(Float priorvendormarkuppct) {
		this.priorvendormarkuppct = priorvendormarkuppct;
	}

	@Column(name = "priorvendorfreightratecwt", precision = 9, scale = 4)
	public Float getPriorvendorfreightratecwt() {
		return this.priorvendorfreightratecwt;
	}

	public void setPriorvendorfreightratecwt(Float priorvendorfreightratecwt) {
		this.priorvendorfreightratecwt = priorvendorfreightratecwt;
	}

	@Column(name = "priorvendorlandedbasecost", precision = 13, scale = 6)
	public BigDecimal getPriorvendorlandedbasecost() {
		return this.priorvendorlandedbasecost;
	}

	public void setPriorvendorlandedbasecost(BigDecimal priorvendorlandedbasecost) {
		this.priorvendorlandedbasecost = priorvendorlandedbasecost;
	}

	//@Column(name = "priorvendordiscpct2", precision = 5)
		//public Float getPriorvendordiscpct2() {
		//	return this.priorvendordiscpct2;
		//}

		//public void setPriorvendordiscpct2(Float priorvendordiscpct2) {
		//	this.priorvendordiscpct2 = priorvendordiscpct2;
		//}

		//@Column(name = "priorvendordiscpct3", precision = 5)
		//public Float getPriorvendordiscpct3() {
		//	return this.priorvendordiscpct3;
		//}

		//public void setPriorvendordiscpct3(Float priorvendordiscpct3) {
		//	this.priorvendordiscpct3 = priorvendordiscpct3;
		//}
	
}
