package com.bedrosians.bedlogic.domain.item.embeddable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Packaginginfo  implements java.io.Serializable {

	private static final long serialVersionUID = -338982221787L;
	
	private String stdunit;
	private Float stdratio;
	private String ordunit;
	private Float ordratio;
	private String baseunit = "PCS";
	private Character baseisstdsell;
	private Character baseisstdord;
	private Character baseisfractqty;
	private Character baseispackunit;
	private Long baseupc;
	private String baseupcdesc;
	private BigDecimal basevolperunit;
	private BigDecimal basewgtperunit;
	private String unit1unit;
	private Float unit1ratio;
	private Character unit1isstdsell;
	private Character unit1isstdord;
	private Character unit1isfractqty;
	private Character unit1ispackunit;
	private Long unit1upc;
	private String unit1upcdesc;
	private BigDecimal unit1wgtperunit;
	private String unit2unit;
	private Float unit2ratio;
	private Character unit2isstdsell;
	private Character unit2isstdord;
	private Character unit2isfractqty;
	private Character unit2ispackunit;
	private Long unit2upc;
	private String unit2upcdesc;
	private BigDecimal unit2wgtperunit;
	private String unit3unit;
	private Float unit3ratio;
	private Character unit3isstdsell;
	private Character unit3isstdord;
	private Character unit3isfractqty;
	private Character unit3ispackunit;
	private Long unit3upc;
	private String unit3upcdesc;
	private BigDecimal unit3wgtperunit;
	private String unit4unit;
	private Float unit4ratio;
	private Character unit4isstdsell;
	private Character unit4isstdord;
	private Character unit4isfractqty;
	private Character unit4ispackunit;
	private Long unit4upc;
	private String unit4upcdesc;
	private BigDecimal unit4wgtperunit;
	
    public Packaginginfo(){}
    
    @Column(name = "stdunit", length = 4)
	public String getStdunit() {
		return FormatUtil.process(this.stdunit);
	}

	public void setStdunit(String stdunit) {
		this.stdunit = stdunit;
	}

	@Column(name = "stdratio", precision = 9, scale = 4)
	public Float getStdratio() {
		return FormatUtil.process(this.stdratio);
	}

	public void setStdratio(Float stdratio) {
		this.stdratio = stdratio;
	}

	@Column(name = "ordunit", length = 4)
	public String getOrdunit() {
		return FormatUtil.process(this.ordunit);
	}

	public void setOrdunit(String ordunit) {
		this.ordunit = ordunit;
	}

	@Column(name = "ordratio", precision = 9, scale = 4)
	public Float getOrdratio() {
		return FormatUtil.process(this.ordratio);
	}

	public void setOrdratio(Float ordratio) {
		this.ordratio = ordratio;
	}

    @Column(name = "baseunit", length = 4)
	public String getBaseunit() {
		return FormatUtil.process(this.baseunit);
	}

	public void setBaseunit(String baseunit) {
		this.baseunit = baseunit;
	}

	@Column(name = "baseisstdsell", length = 1)
	public Character getBaseisstdsell() {
		return FormatUtil.process(this.baseisstdsell);
	}

	public void setBaseisstdsell(Character baseisstdsell) {
		this.baseisstdsell = baseisstdsell;
	}

	@Column(name = "baseisstdord", length = 1)
	public Character getBaseisstdord() {
		return FormatUtil.process(this.baseisstdord);
	}

	public void setBaseisstdord(Character baseisstdord) {
		this.baseisstdord = baseisstdord;
	}

	@Column(name = "baseisfractqty", length = 1)
	public Character getBaseisfractqty() {
		return FormatUtil.process(this.baseisfractqty);
	}

	public void setBaseisfractqty(Character baseisfractqty) {
		this.baseisfractqty = baseisfractqty;
	}

	@Column(name = "baseispackunit", length = 1)
	public Character getBaseispackunit() {
		return FormatUtil.process(this.baseispackunit);
	}

	public void setBaseispackunit(Character baseispackunit) {
		this.baseispackunit = baseispackunit;
	}

	@Column(name = "baseupc", precision = 17, scale = 0)
	public Long getBaseupc() {
		return FormatUtil.process(this.baseupc);
	}

	public void setBaseupc(Long baseupc) {
		this.baseupc = baseupc;
	}

	@Column(name = "baseupcdesc", length = 15)
	public String getBaseupcdesc() {
		return FormatUtil.process(this.baseupcdesc);
	}

	public void setBaseupcdesc(String baseupcdesc) {
		this.baseupcdesc = baseupcdesc;
	}

	@Column(name = "basevolperunit", precision = 10, scale = 6)
	public BigDecimal getBasevolperunit() {
		return FormatUtil.process(this.basevolperunit);
	}

	public void setBasevolperunit(BigDecimal basevolperunit) {
		this.basevolperunit = basevolperunit;
	}

	@Column(name = "basewgtperunit", precision = 10, scale = 6)
	public BigDecimal getBasewgtperunit() {
		return FormatUtil.process(this.basewgtperunit);
	}

	public void setBasewgtperunit(BigDecimal basewgtperunit) {
		this.basewgtperunit = basewgtperunit;
	}

	@Column(name = "unit1unit", length = 4)
	public String getUnit1unit() {
		return FormatUtil.process(this.unit1unit);
	}

	public void setUnit1unit(String unit1unit) {
		this.unit1unit = unit1unit;
	}

	@Column(name = "unit1ratio", precision = 9, scale = 4)
	public Float getUnit1ratio() {
		return FormatUtil.process(this.unit1ratio);
	}

	public void setUnit1ratio(Float unit1ratio) {
		this.unit1ratio = unit1ratio;
	}

	@Column(name = "unit1isstdsell", length = 1)
	public Character getUnit1isstdsell() {
		return FormatUtil.process(this.unit1isstdsell);
	}

	public void setUnit1isstdsell(Character unit1isstdsell) {
		this.unit1isstdsell = unit1isstdsell;
	}

	@Column(name = "unit1isstdord", length = 1)
	public Character getUnit1isstdord() {
		return FormatUtil.process(this.unit1isstdord);
	}

	public void setUnit1isstdord(Character unit1isstdord) {
		this.unit1isstdord = unit1isstdord;
	}

	@Column(name = "unit1isfractqty", length = 1)
	public Character getUnit1isfractqty() {
		return FormatUtil.process(this.unit1isfractqty);
	}

	public void setUnit1isfractqty(Character unit1isfractqty) {
		this.unit1isfractqty = unit1isfractqty;
	}

	@Column(name = "unit1ispackunit", length = 1)
	public Character getUnit1ispackunit() {
		return FormatUtil.process(this.unit1ispackunit);
	}

	public void setUnit1ispackunit(Character unit1ispackunit) {
		this.unit1ispackunit = unit1ispackunit;
	}

	@Column(name = "unit1upc", precision = 17, scale = 0)
	public Long getUnit1upc() {
		return FormatUtil.process(this.unit1upc);
	}

	public void setUnit1upc(Long unit1upc) {
		this.unit1upc = unit1upc;
	}

	@Column(name = "unit1upcdesc", length = 15)
	public String getUnit1upcdesc() {
		return FormatUtil.process(this.unit1upcdesc);
	}

	public void setUnit1upcdesc(String unit1upcdesc) {
		this.unit1upcdesc = unit1upcdesc;
	}
	
	@Column(name = "unit1wgtperunit", precision = 12, scale = 6)
	public BigDecimal getUnit1wgtperunit() {
		return FormatUtil.process(this.unit1wgtperunit);
	}

	public void setUnit1wgtperunit(BigDecimal unit1wgtperunit) {
		this.unit1wgtperunit = unit1wgtperunit;
	}

	@Column(name = "unit2unit", length = 4)
	public String getUnit2unit() {
		return FormatUtil.process(this.unit2unit);
	}

	public void setUnit2unit(String unit2unit) {
		this.unit2unit = unit2unit;
	}

	@Column(name = "unit2ratio", precision = 9, scale = 4)
	public Float getUnit2ratio() {
		return FormatUtil.process(this.unit2ratio);
	}

	public void setUnit2ratio(Float unit2ratio) {
		this.unit2ratio = unit2ratio;
	}

	@Column(name = "unit2isstdsell", length = 1)
	public Character getUnit2isstdsell() {
		return FormatUtil.process(this.unit2isstdsell);
	}

	public void setUnit2isstdsell(Character unit2isstdsell) {
		this.unit2isstdsell = unit2isstdsell;
	}

	@Column(name = "unit2isstdord", length = 1)
	public Character getUnit2isstdord() {
		return FormatUtil.process(this.unit2isstdord);
	}

	public void setUnit2isstdord(Character unit2isstdord) {
		this.unit2isstdord = unit2isstdord;
	}

	@Column(name = "unit2isfractqty", length = 1)
	public Character getUnit2isfractqty() {
		return FormatUtil.process(this.unit2isfractqty);
	}

	public void setUnit2isfractqty(Character unit2isfractqty) {
		this.unit2isfractqty = unit2isfractqty;
	}

	@Column(name = "unit2ispackunit", length = 1)
	public Character getUnit2ispackunit() {
		return FormatUtil.process(this.unit2ispackunit);
	}

	public void setUnit2ispackunit(Character unit2ispackunit) {
		this.unit2ispackunit = unit2ispackunit;
	}

	@Column(name = "unit2upc", precision = 17, scale = 0)
	public Long getUnit2upc() {
		return FormatUtil.process(this.unit2upc);
	}

	public void setUnit2upc(Long unit2upc) {
		this.unit2upc = unit2upc;
	}

	@Column(name = "unit2upcdesc", length = 15)
	public String getUnit2upcdesc() {
		return FormatUtil.process(this.unit2upcdesc);
	}

	public void setUnit2upcdesc(String unit2upcdesc) {
		this.unit2upcdesc = unit2upcdesc;
	}

	@Column(name = "unit2wgtperunit", precision = 12, scale = 6)
	public BigDecimal getUnit2wgtperunit() {
		return FormatUtil.process(this.unit2wgtperunit);
	}

	public void setUnit2wgtperunit(BigDecimal unit2wgtperunit) {
		this.unit2wgtperunit = unit2wgtperunit;
	}
	
	@Column(name = "unit3unit", length = 4)
	public String getUnit3unit() {
		return FormatUtil.process(this.unit3unit);
	}
	
	public void setUnit3unit(String unit3unit) {
		this.unit3unit = unit3unit;
	}

	@Column(name = "unit3ratio", precision = 9, scale = 4)
	public Float getUnit3ratio() {
		return FormatUtil.process(this.unit3ratio);
	}

	public void setUnit3ratio(Float unit3ratio) {
		this.unit3ratio = unit3ratio;
	}

	@Column(name = "unit3isstdsell", length = 1)
	public Character getUnit3isstdsell() {
		return FormatUtil.process(this.unit3isstdsell);
	}

	public void setUnit3isstdsell(Character unit3isstdsell) {
		this.unit3isstdsell = unit3isstdsell;
	}

	@Column(name = "unit3isstdord", length = 1)
	public Character getUnit3isstdord() {
		return FormatUtil.process(this.unit3isstdord);
	}

	public void setUnit3isstdord(Character unit3isstdord) {
		this.unit3isstdord = unit3isstdord;
	}

	@Column(name = "unit3isfractqty", length = 1)
	public Character getUnit3isfractqty() {
		return FormatUtil.process(this.unit3isfractqty);
	}

	public void setUnit3isfractqty(Character unit3isfractqty) {
		this.unit3isfractqty = unit3isfractqty;
	}

	@Column(name = "unit3ispackunit", length = 1)
	public Character getUnit3ispackunit() {
		return FormatUtil.process(this.unit3ispackunit);
	}

	public void setUnit3ispackunit(Character unit3ispackunit) {
		this.unit3ispackunit = unit3ispackunit;
	}

	@Column(name = "unit3upc", precision = 17, scale = 0)
	public Long getUnit3upc() {
		return FormatUtil.process(this.unit3upc);
	}

	public void setUnit3upc(Long unit3upc) {
		this.unit3upc = unit3upc;
	}

	@Column(name = "unit3upcdesc", length = 15)
	public String getUnit3upcdesc() {
		return FormatUtil.process(this.unit3upcdesc);
	}

	public void setUnit3upcdesc(String unit3upcdesc) {
		this.unit3upcdesc = unit3upcdesc;
	}
	
	@Column(name = "unit3wgtperunit", precision = 12, scale = 6)
	public BigDecimal getUnit3wgtperunit() {
		return FormatUtil.process(this.unit3wgtperunit);
	}

	public void setUnit3wgtperunit(BigDecimal unit3wgtperunit) {
		this.unit3wgtperunit = unit3wgtperunit;
	}

	@Column(name = "unit4unit", length = 4)
	public String getUnit4unit() {
		return FormatUtil.process(this.unit4unit);
	}

	public void setUnit4unit(String unit4unit) {
		this.unit4unit = unit4unit;
	}

	@Column(name = "unit4ratio", precision = 9, scale = 4)
	public Float getUnit4ratio() {
		return FormatUtil.process(this.unit4ratio);
	}

	public void setUnit4ratio(Float unit4ratio) {
		this.unit4ratio = unit4ratio;
	}

	@Column(name = "unit4isstdsell", length = 1)
	public Character getUnit4isstdsell() {
		return FormatUtil.process(this.unit4isstdsell);
	}

	public void setUnit4isstdsell(Character unit4isstdsell) {
		this.unit4isstdsell = unit4isstdsell;
	}

	@Column(name = "unit4isstdord", length = 1)
	public Character getUnit4isstdord() {
		return FormatUtil.process(this.unit4isstdord);
	}

	public void setUnit4isstdord(Character unit4isstdord) {
		this.unit4isstdord = unit4isstdord;
	}

	@Column(name = "unit4isfractqty", length = 1)
	public Character getUnit4isfractqty() {
		return FormatUtil.process(this.unit4isfractqty);
	}

	public void setUnit4isfractqty(Character unit4isfractqty) {
		this.unit4isfractqty = unit4isfractqty;
	}

	@Column(name = "unit4ispackunit", length = 1)
	public Character getUnit4ispackunit() {
		return FormatUtil.process(this.unit4ispackunit);
	}

	public void setUnit4ispackunit(Character unit4ispackunit) {
		this.unit4ispackunit = unit4ispackunit;
	}

	@Column(name = "unit4upc", precision = 17, scale = 0)
	public Long getUnit4upc() {
		return FormatUtil.process(this.unit4upc);
	}

	public void setUnit4upc(Long unit4upc) {
		this.unit4upc = unit4upc;
	}

	@Column(name = "unit4upcdesc", length = 15)
	public String getUnit4upcdesc() {
		return FormatUtil.process(this.unit4upcdesc);
	}

	public void setUnit4upcdesc(String unit4upcdesc) {
		this.unit4upcdesc = unit4upcdesc;
	}

	@Column(name = "unit4wgtperunit", precision = 12, scale = 6)
	public BigDecimal getUnit4wgtperunit() {
		return FormatUtil.process(this.unit4wgtperunit);
	}

	public void setUnit4wgtperunit(BigDecimal unit4wgtperunit) {
		this.unit4wgtperunit = unit4wgtperunit;
	}

}
