package com.bedrosians.bedlogic.domain.item.embeddable;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.contrib.hibernate.PersistentDateTime;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Price implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private BigDecimal price;//sellprice;
	private String priceGroup;
	private Float priceMarginPct = 0F; //sellpriceMarginPct
	private Float minimalMarginPct;
	private Integer priceRoundAccuracy = 0; //sellpriceroundaccuracy
	private BigDecimal futurePrice; //futuresell;
	private BigDecimal priorPrice;//priorsellprice;
	private BigDecimal tempPrice;
	private Date tempDateFrom = null;
	private Date tempDateThrough = null;
	private BigDecimal listPrice;
	private BigDecimal priorListPrice;
	
	public Price(){}
	
	@Column(name = "listprice", precision = 9, scale = 4)
	public BigDecimal getListPrice() {
		return FormatUtil.process(this.listPrice);
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	@Column(name = "sellprice", precision = 9, scale = 4)
	public BigDecimal getPrice() {
		return FormatUtil.process(this.price);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Column(name = "pricegroup", length = 2)
	public String getPriceGroup() {
		return FormatUtil.process(this.priceGroup);
	}

	public void setPriceGroup(String priceGroup) {
		this.priceGroup = priceGroup;
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
	public Date getTempDateFrom() {
		return FormatUtil.process(this.tempDateFrom);
	}

	public void setTempDateFrom(Date tempDateFrom) {
		this.tempDateFrom = tempDateFrom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tempdatethru", length = 13, nullable = true)
	public Date getTempDateThrough() {
		return FormatUtil.process(this.tempDateThrough);
	}

	public void setTempDateThrough(Date tempDateThrough) {
		this.tempDateThrough = tempDateThrough;
	}

	@PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_BUYER')") 
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
	
	@PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_BUYER')") 
	@Column(name = "minmarginpct", precision = 4, scale = 1)
	public Float getMinimalMarginPct() {
		return FormatUtil.process(this.minimalMarginPct);
	}

	public void setMinimalMarginPct(Float minimalMarginPct) {
		this.minimalMarginPct = minimalMarginPct;
	}
	
	@Column(name = "priorlistprice", precision = 9, scale = 4)
	public BigDecimal getPriorListPrice() {
		return FormatUtil.process(this.priorListPrice);
	}

	public void setPriorListPrice(BigDecimal priorListPrice) {
		this.priorListPrice = priorListPrice;
	}

	@Column(name = "priorsellprice", precision = 9, scale = 4)
	public BigDecimal getPriorPrice() {
		return FormatUtil.process(this.priorPrice);
	}

	public void setPriorPrice(BigDecimal priorPrice) {
		this.priorPrice = priorPrice;
	}
	
}
