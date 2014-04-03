package com.bedrosians.bedlogic.domain.item.embeddable;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import org.joda.time.contrib.hibernate.PersistentDateTime;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Price implements java.io.Serializable {

	private BigDecimal price;//sellprice;
	private String pricegroup;
	private Float priceMarginPct = 0F; //sellpriceMarginPct
	private Float minimalMarginPct;
	private Integer priceRoundAccuracy = 0; //sellpriceroundaccuracy
	private BigDecimal futurePrice; //futuresell;
	private BigDecimal priorPrice;//priorsellprice;
	private BigDecimal tempPrice;
	private Date tempdatefrom = null;
	private Date tempdatethru = null;
	private BigDecimal listprice;
	private BigDecimal priorlistprice;
	
	public Price(){}
	
	@Column(name = "listprice", precision = 9, scale = 4)
	public BigDecimal getListprice() {
		return FormatUtil.process(this.listprice);
	}

	public void setListprice(BigDecimal listprice) {
		this.listprice = listprice;
	}

	@Column(name = "sellprice", precision = 9, scale = 4)
	public BigDecimal getPrice() {
		return FormatUtil.process(this.price);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Column(name = "pricegroup", length = 2)
	public String getPricegroup() {
		return FormatUtil.process(this.pricegroup);
	}

	public void setPricegroup(String pricegroup) {
		this.pricegroup = pricegroup;
	}
	
	@Column(name = "futuresell", precision = 9, scale = 4)
	public BigDecimal getFuturePrice() {
		return FormatUtil.process(this.futurePrice);
	}

	public void setFuturePrice(BigDecimal futurePrice) {
		this.futurePrice = futurePrice;
	}
	
	@Column(name = "tempprice", precision = 9, scale = 4)
	public BigDecimal getTempPrice() {
		return FormatUtil.process(this.tempPrice);
	}

	public void setTempPrice(BigDecimal tempPrice) {
		this.tempPrice = tempPrice;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "tempdatefrom", length = 13)
	public Date getTempdatefrom() {
		return FormatUtil.process(this.tempdatefrom);
	}

	public void setTempdatefrom(Date tempdatefrom) {
		this.tempdatefrom = tempdatefrom;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "tempdatethru", length = 13, nullable = true)
	//@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	
	public Date getTempdatethru() {
		return FormatUtil.process(this.tempdatethru);
	}

	public void setTempdatethru(Date tempdatethru) {
		this.tempdatethru = tempdatethru;
	}

	@Column(name = "sellpricemarginpct", precision = 5)
	public Float getPriceMarginPct() {
		return FormatUtil.process(this.priceMarginPct);
	}

	public void setPriceMarginPct(Float priceMarginPct) {
		this.priceMarginPct = priceMarginPct;
	}

	@Column(name = "sellpriceroundaccuracy", precision = 1, scale = 0)
	public Integer getPriceRoundAccuracy() {
		return FormatUtil.process(this.priceRoundAccuracy);
	}

	public void setPriceRoundAccuracy(Integer priceRoundAccuracy) {
		this.priceRoundAccuracy = priceRoundAccuracy;
	}
	
	@Column(name = "minmarginpct", precision = 4, scale = 1)
	public Float getMinimalMarginPct() {
		return FormatUtil.process(this.minimalMarginPct);
	}

	public void setMinimalMarginPct(Float minimalMarginPct) {
		this.minimalMarginPct = minimalMarginPct;
	}
	
	@Column(name = "priorlistprice", precision = 9, scale = 4)
	public BigDecimal getPriorlistprice() {
		return FormatUtil.process(this.priorlistprice);
	}

	public void setPriorlistprice(BigDecimal priorlistprice) {
		this.priorlistprice = priorlistprice;
	}

	@Column(name = "priorsellprice", precision = 9, scale = 4)
	public BigDecimal getPriorPrice() {
		return FormatUtil.process(this.priorPrice);
	}

	public void setPriorPrice(BigDecimal priorPrice) {
		this.priorPrice = priorPrice;
	}
	
}
