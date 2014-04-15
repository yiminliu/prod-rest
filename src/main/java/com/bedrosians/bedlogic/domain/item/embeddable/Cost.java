package com.bedrosians.bedlogic.domain.item.embeddable;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.contrib.hibernate.PersistentDateTime;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Cost implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private BigDecimal cost;
	private BigDecimal priorCost;
    private BigDecimal priorCost1;
    private BigDecimal futureCost;
    private BigDecimal futureCost1;
    private Character poIncludeinVendorCost;
	private Float nonstockCostPct;
    private Float costRangePct;
	
	public Cost(){}
	
	@PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER')") 
	@Column(name = "poincludeinvendorcost", length = 1)
	public Character getPoIncludeinVendorCost() {
		return FormatUtil.process(this.poIncludeinVendorCost);
	}

	public void setPoIncludeinVendorCost(Character poIncludeinVendorCost) {
		this.poIncludeinVendorCost = poIncludeinVendorCost;
	}

	
	@PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER')") 
	@Column(name = "cost1", precision = 9, scale = 4)
	public BigDecimal getCost() {
		return FormatUtil.process(this.cost);
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
    @Column(name = "priorcost", precision = 9, scale = 4)
	public BigDecimal getPriorCost() {
		return FormatUtil.process(this.priorCost);
    }

	public void setPriorCost(BigDecimal priorCost) {
		this.priorCost = priorCost;
	}

	@Column(name = "priorcost1", precision = 9, scale = 4)
	public BigDecimal getPriorCost1() {
		return FormatUtil.process(this.priorCost1);
	}

	public void setPriorCost1(BigDecimal priorCost1) {
		this.priorCost1 = priorCost1;
	}

	@Column(name = "futurecost", precision = 9, scale = 4)
	public BigDecimal getFutureCost() {
		return FormatUtil.process(this.futureCost);
	}

	public void setFutureCost(BigDecimal futureCost) {
		this.futureCost = futureCost;
	}

	@Column(name = "futurecost1", precision = 9, scale = 4)
	public BigDecimal getFutureCost1() {
		return FormatUtil.process(this.futureCost1);
	}

	public void setFutureCost1(BigDecimal futureCost1) {
		this.futureCost1 = futureCost1;
	}

	
	@PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER')") 
	@PostFilter("hasPermission(this, 'read') or hasPermission(this, 'admin')")
	@Column(name = "nonstockcostpct", precision = 4, scale = 1)
	public Float getNonstockCostPct() {
		return FormatUtil.process(this.nonstockCostPct);
	}

	public void setNonstockCostPct(Float nonstockCostPct) {
		this.nonstockCostPct = nonstockCostPct;
	}
	
	@Column(name = "costrangepct", precision = 4, scale = 1)
	public Float getCostRangePct() {
		return FormatUtil.process(this.costRangePct);
	}

	public void setCostRangePct(Float costRangePct) {
		this.costRangePct = costRangePct;
	}
}
