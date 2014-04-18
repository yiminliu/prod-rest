package com.bedrosians.bedlogic.domain.item.embeddable;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Prices implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private BigDecimal sellprice;
	private String pricegroup;
	private Float sellpricemarginpct = 0F;
	private Float minmarginpct;
	private Integer sellpriceroundaccuracy = 0; 
	private BigDecimal futuresell; 
	private BigDecimal priorsellprice;
	private BigDecimal tempprice;
	private Date tempdatefrom = null;
	private Date tempdatethru = null;
	private BigDecimal listprice;
	private BigDecimal priorlistprice;
	
	public Prices(){}
	
	@Column(name = "listprice", precision = 9, scale = 4)
	public BigDecimal getListprice() {
		return FormatUtil.process(this.listprice);
	}

	public void setListprice(BigDecimal listprice) {
		this.listprice = listprice;
	}

	@Column(name = "sellprice", precision = 9, scale = 4)
	public BigDecimal getSellprice() {
		return FormatUtil.process(this.sellprice);
	}

	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}
	
	@Column(name = "pricegroup", length = 2)
	public String getPricegroup() {
		return FormatUtil.process(this.pricegroup);
	}

	public void setPricegroup(String pricegroup) {
		this.pricegroup = pricegroup;
	}
	
	@Column(name = "futuresell", precision = 9, scale = 4)
	public BigDecimal getFuturesell() {
		return FormatUtil.process(this.futuresell);
	}

	public void setFuturesell(BigDecimal futuresell) {
		this.futuresell = futuresell;
	}
	
	@Column(name = "tempprice", precision = 9, scale = 4)
	public BigDecimal getTempprice() {
		return FormatUtil.process(this.tempprice);
	}

	public void setTempprice(BigDecimal tempprice) {
		this.tempprice = tempprice;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "tempdatefrom", length = 13)
	public Date getTempdatefrom() {
		return FormatUtil.process(this.tempdatefrom);
	}

	public void setTempdatefrom(Date tempdatefrom) {
		this.tempdatefrom = tempdatefrom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tempdatethru", length = 13, nullable = true)
	public Date getTempdatethru() {
		return FormatUtil.process(this.tempdatethru);
	}

	public void setTempdatethru(Date tempdatethru) {
		this.tempdatethru = tempdatethru;
	}

	@Column(name = "sellpricemarginpct", precision = 5)
	public Float getSellpricemarginpct() {
		return FormatUtil.process(this.sellpricemarginpct);
	}

	public void setSellpricemarginpct(Float sellpricemarginpct) {
		this.sellpricemarginpct = sellpricemarginpct;
	}

	@Column(name = "sellpriceroundaccuracy", precision = 1, scale = 0)
	public Integer getSellpriceroundaccuracy() {
		return FormatUtil.process(this.sellpriceroundaccuracy);
	}

	public void setSellpriceroundaccuracy(Integer sellpriceroundaccuracy) {
		this.sellpriceroundaccuracy = sellpriceroundaccuracy;
	}
	
	@Column(name = "minmarginpct", precision = 4, scale = 1)
	public Float getMinmarginpct() {
		return FormatUtil.process(this.minmarginpct);
	}

	public void setMinmarginpct(Float minmarginpct) {
		this.minmarginpct = minmarginpct;
	}
	
	@Column(name = "priorlistprice", precision = 9, scale = 4)
	public BigDecimal getPriorlistprice() {
		return FormatUtil.process(this.priorlistprice);
	}

	public void setPriorlistprice(BigDecimal priorlistprice) {
		this.priorlistprice = priorlistprice;
	}

	@Column(name = "priorsellprice", precision = 9, scale = 4)
	public BigDecimal getPriorsellprice() {
		return FormatUtil.process(this.priorsellprice);
	}

	public void setPriorsellprice(BigDecimal priorsellprice) {
		this.priorsellprice = priorsellprice;
	}
	
}
