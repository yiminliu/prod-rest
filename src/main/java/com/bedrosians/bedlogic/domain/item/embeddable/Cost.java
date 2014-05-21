package com.bedrosians.bedlogic.domain.item.embeddable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Cost implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private BigDecimal cost1;
	private BigDecimal priorcost;
    private BigDecimal priorcost1;
    private BigDecimal futurecost;
    private BigDecimal futurecost1;
    private Character poincludeinvendorcost;
	private Float nonstockcostpct;
    private Float costrangepct;
	
	public Cost(){}
	
	@Column(name = "poincludeinvendorcost", length = 1)
	public Character getPoincludeinvendorcost() {
		return this.poincludeinvendorcost;
	}

	public void setPoincludeinvendorcost(Character poincludeinvendorcost) {
		this.poincludeinvendorcost = poincludeinvendorcost;
	}

	
	@Column(name = "cost1", precision = 9, scale = 4)
	public BigDecimal getCost1() {
		return this.cost1;
	}

	public void setCost1(BigDecimal cost1) {
		this.cost1 = cost1;
	}
	
    @Column(name = "priorcost", precision = 9, scale = 4)
	public BigDecimal getPriorcost() {
		return this.priorcost;
    }

	public void setPriorcost(BigDecimal priorcost) {
		this.priorcost = priorcost;
	}

	@Column(name = "priorcost1", precision = 9, scale = 4)
	public BigDecimal getPriorcost1() {
		return this.priorcost1;
	}

	public void setPriorcost1(BigDecimal priorcost1) {
		this.priorcost1 = priorcost1;
	}

	@Column(name = "futurecost", precision = 9, scale = 4)
	public BigDecimal getFuturecost() {
		return this.futurecost;
	}

	public void setFuturecost(BigDecimal futurecost) {
		this.futurecost = futurecost;
	}

	@Column(name = "futurecost1", precision = 9, scale = 4)
	public BigDecimal getFuturecost1() {
		return this.futurecost1;
	}

	public void setFuturecost1(BigDecimal futurecost1) {
		this.futurecost1 = futurecost1;
	}

	@Column(name = "nonstockcostpct", precision = 4, scale = 1)
	public Float getNonstockcostpct() {
		return this.nonstockcostpct;
	}

	public void setNonstockcostpct(Float nonstockcostpct) {
		this.nonstockcostpct = nonstockcostpct;
	}
	
	@Column(name = "costrangepct", precision = 4, scale = 1)
	public Float getCostrangepct() {
		return this.costrangepct;
	}

	public void setCostrangepct(Float costrangepct) {
		this.costrangepct = costrangepct;
	}
}
