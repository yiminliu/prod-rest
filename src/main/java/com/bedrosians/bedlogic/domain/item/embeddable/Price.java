package com.bedrosians.bedlogic.domain.item.embeddable;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;


@Embeddable
public class Price implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private BigDecimal listprice;
	private BigDecimal sellprice;
	private String pricegroup;
	private String priceunit;
	private Float sellpricemarginpct = 0F;
	private Integer sellpriceroundaccuracy = 0;
	private Float listpricemarginpct = 0F;
	private Float minmarginpct;
	private BigDecimal futuresell; 
	private BigDecimal tempprice;
	private Date tempdatefrom = null;
	private Date tempdatethru = null;
	private BigDecimal priorlistprice;
	private BigDecimal priorsellprice;
	
	public Price(){}
	
	@Column(name = "sellprice", precision = 9, scale = 4)
	public BigDecimal getSellprice() {
		return this.sellprice;
	}

	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}
	
	@Column(name = "pricegroup", length = 2)
	public String getPricegroup() {
		return this.pricegroup;
	}

	public void setPricegroup(String pricegroup) {
		this.pricegroup = pricegroup;
	}
	
	@Column(name = "listprice", precision = 9, scale = 4)
	public BigDecimal getListprice() {
		return this.listprice;
	}

	public void setListprice(BigDecimal listprice) {
		this.listprice = listprice;
	}
	
	@Column(name = "listpricemarginpct", precision = 5)
	public Float getListpricemarginpct() {
		return this.listpricemarginpct;
	}

	public void setListpricemarginpct(Float listpricemarginpct) {
		this.listpricemarginpct = listpricemarginpct;
	}
	
	@Column(name = "futuresell", precision = 9, scale = 4)
	public BigDecimal getFuturesell() {
		return this.futuresell;
	}

	public void setFuturesell(BigDecimal futuresell) {
		this.futuresell = futuresell;
	}
	
	@Column(name = "tempprice", precision = 9, scale = 4)
	public BigDecimal getTempprice() {
		return this.tempprice;
	}

	public void setTempprice(BigDecimal tempprice) {
		this.tempprice = tempprice;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "tempdatefrom", length = 13)
	public Date getTempdatefrom() {
		return this.tempdatefrom;
	}

	public void setTempdatefrom(Date tempdatefrom) {
		this.tempdatefrom = tempdatefrom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tempdatethru", length = 13, nullable = true)
	public Date getTempdatethru() {
		return this.tempdatethru;
	}

	public void setTempdatethru(Date tempdatethru) {
		this.tempdatethru = tempdatethru;
	}

	@Column(name = "sellpricemarginpct", precision = 5)
	public Float getSellpricemarginpct() {
		return this.sellpricemarginpct;
	}

	public void setSellpricemarginpct(Float sellpricemarginpct) {
		this.sellpricemarginpct = sellpricemarginpct;
	}

	@Column(name = "sellpriceroundaccuracy", precision = 1, scale = 0)
	public Integer getSellpriceroundaccuracy() {
		return this.sellpriceroundaccuracy;
	}

	public void setSellpriceroundaccuracy(Integer sellpriceroundaccuracy) {
		this.sellpriceroundaccuracy = sellpriceroundaccuracy;
	}
	
	@Column(name = "minmarginpct", precision = 4, scale = 1)
	public Float getMinmarginpct() {
		return this.minmarginpct;
	}

	public void setMinmarginpct(Float minmarginpct) {
		this.minmarginpct = minmarginpct;
	}
	
	@Column(name = "priorlistprice", precision = 9, scale = 4, updatable=false)
	public BigDecimal getPriorlistprice() {
		return this.priorlistprice;
	}

	public void setPriorlistprice(BigDecimal priorlistprice) {
		this.priorlistprice = priorlistprice;
	}

	@Column(name = "priorsellprice", precision = 9, scale = 4, updatable=false)
	public BigDecimal getPriorsellprice() {
		return this.priorsellprice;
	}

	public void setPriorsellprice(BigDecimal priorsellprice) {
		this.priorsellprice = priorsellprice;
	}

	@Transient
	public String getPriceunit() {
	    return priceunit;
	}

	public void setPriceunit(String priceunit) {
		this.priceunit = priceunit;
	}
	
	@JsonIgnore
	@Transient
	public boolean isDefault(){
		return listprice == null && sellprice == null && pricegroup == null && priceunit == null && tempprice == null && futuresell == null  &&  priorsellprice == null;
	}
}
