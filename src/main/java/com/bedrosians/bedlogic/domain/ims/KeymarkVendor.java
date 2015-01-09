package com.bedrosians.bedlogic.domain.ims;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="apv")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class KeymarkVendor implements java.io.Serializable {

	private static final long serialVersionUID = -582265865921787L;
	
	private Integer vendorNumber = null;
	private String name;
    private String addr1;
    private String addr2;
    private String city;
    private String statecd;
    private String zip;
    private String countrycd;
    private String email;
    private Long fax;
    private Long phone;
    private Character alwaysonhold;
    private Character codea1;
    private Character codea2;
    private Integer coden1;
    private Integer coden2;
    private Byte daysoffloat;
    private Float discountpct;
    private String fobcd;
    private Integer glacct;
    private String inactivecd;
    private String lastChgipaddr;
    private String lastChgusercd;
    private String memodesc;
    private String notes1;
    private String notes2;
    private String notes3;
    private String notes4;
    private String notes5;
    private String ourcustcd;
    private Short paymentdate;
    private String pmttermscd;
    private Boolean preflevel;
    private Character printmemodesc;
    private Character printnoteonchk;
    private Short estrcvbydays;
    private Short cancelbydays;
    private String shipviacd;
    private Character t99cd;
    private String t99fedid;
    private Float vendorfreightratecwt;
    private Float vendorchargeamt;
    private Float otherfreightratecwt;
    private Float othermarkuppct;
    private Float vendormarkuppct;
    private Float otherchargeamt;
    private Float t99Avail;
    private Float t99Actual;
    private Character filingCode;
    private String dbaname;
    private Character t99fedidisssnbr;
    private String setupby;
    private Date setupdate;
    private String accountnbr;
    private String pinnbr;
    private String passwd;
    private Character import_;
    private String poEmail;
    private Short cashflowdays;
    private Character excfromcashflow;
    private Character ecutopia;
    private Byte portToStockDays;
  
    @Id
	@Column(name = "vendornbr", unique = true, nullable = false, precision = 6, scale = 0)
    public Integer getvendorNumber() {
		return this.vendorNumber;
	}

	public void setvendorNumber(Integer vendorNumber) {
		this.vendorNumber = vendorNumber;
	}

	@Column(name = "name", length = 35)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Column(name = "addr1", length = 30)
    public String getAddr1() {
        return this.addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    @Column(name = "addr2", length = 30)
    public String getAddr2() {
        return this.addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    @Column(name = "city", length = 30)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "statecd", length = 2)
    public String getStatecd() {
        return this.statecd;
    }

    public void setStatecd(String statecd) {
        this.statecd = statecd;
    }

    @Column(name = "zip", length = 10)
    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "countrycd", length = 4)
    public String getCountrycd() {
        return this.countrycd;
    }

    public void setCountrycd(String countrycd) {
        this.countrycd = countrycd;
    }

    @Column(name = "email", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "fax", precision = 13, scale = 0)
    public Long getFax() {
        return this.fax;
    }

    public void setFax(Long fax) {
        this.fax = fax;
    }

    @Column(name = "phone", precision = 17, scale = 0)
    public Long getPhone() {
        return this.phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Column(name = "alwaysonhold", length = 1)
    public Character getAlwaysonhold() {
        return this.alwaysonhold;
    }

    public void setAlwaysonhold(Character alwaysonhold) {
        this.alwaysonhold = alwaysonhold;
    }

    @Column(name = "codea1", length = 1)
    public Character getCodea1() {
        return this.codea1;
    }

    public void setCodea1(Character codea1) {
        this.codea1 = codea1;
    }

    @Column(name = "codea2", length = 1)
    public Character getCodea2() {
        return this.codea2;
    }

    public void setCodea2(Character codea2) {
        this.codea2 = codea2;
    }

    @Column(name = "coden1", precision = 8, scale = 0)
    public Integer getCoden1() {
        return this.coden1;
    }

    public void setCoden1(Integer coden1) {
        this.coden1 = coden1;
    }

    @Column(name = "coden2", precision = 8, scale = 0)
    public Integer getCoden2() {
        return this.coden2;
    }

    public void setCoden2(Integer coden2) {
        this.coden2 = coden2;
    }

    @Column(name = "daysoffloat", precision = 2, scale = 0)
    public Byte getDaysoffloat() {
        return this.daysoffloat;
    }

    public void setDaysoffloat(Byte daysoffloat) {
        this.daysoffloat = daysoffloat;
    }

    @Column(name = "discountpct", precision = 5)
    public Float getDiscountpct() {
        return this.discountpct;
    }

    public void setDiscountpct(Float discountpct) {
        this.discountpct = discountpct;
    }

    @Column(name = "fobcd", length = 6)
    public String getFobcd() {
        return this.fobcd;
    }

    public void setFobcd(String fobcd) {
        this.fobcd = fobcd;
    }

    @Column(name = "glacct", precision = 9, scale = 0)
    public Integer getGlacct() {
        return this.glacct;
    }

    public void setGlacct(Integer glacct) {
        this.glacct = glacct;
    }

    @Column(name = "inactivecd", length = 3)
    public String getInactivecd() {
        return this.inactivecd;
    }

    public void setInactivecd(String inactivecd) {
        this.inactivecd = inactivecd;
    }

    @Column(name = "last_chgipaddr", length = 50)
    public String getLastChgipaddr() {
        return this.lastChgipaddr;
    }

    public void setLastChgipaddr(String lastChgipaddr) {
        this.lastChgipaddr = lastChgipaddr;
    }

    @Column(name = "last_chgusercd", length = 10)
    public String getLastChgusercd() {
        return this.lastChgusercd;
    }

    public void setLastChgusercd(String lastChgusercd) {
        this.lastChgusercd = lastChgusercd;
    }

    @Column(name = "memodesc", length = 10)
    public String getMemodesc() {
        return this.memodesc;
    }

    public void setMemodesc(String memodesc) {
        this.memodesc = memodesc;
    }

    @Column(name = "notes1", length = 25)
    public String getNotes1() {
        return this.notes1;
    }

    public void setNotes1(String notes1) {
        this.notes1 = notes1;
    }

    @Column(name = "notes2", length = 45)
    public String getNotes2() {
        return this.notes2;
    }

    public void setNotes2(String notes2) {
        this.notes2 = notes2;
    }

    @Column(name = "notes3", length = 45)
    public String getNotes3() {
        return this.notes3;
    }

    public void setNotes3(String notes3) {
        this.notes3 = notes3;
    }

    @Column(name = "notes4", length = 45)
    public String getNotes4() {
        return this.notes4;
    }

    public void setNotes4(String notes4) {
        this.notes4 = notes4;
    }

    @Column(name = "notes5", length = 45)
    public String getNotes5() {
        return this.notes5;
    }

    public void setNotes5(String notes5) {
        this.notes5 = notes5;
    }

    @Column(name = "ourcustcd", length = 10)
    public String getOurcustcd() {
        return this.ourcustcd;
    }

    public void setOurcustcd(String ourcustcd) {
        this.ourcustcd = ourcustcd;
    }

    @Column(name = "paymentdate", precision = 3, scale = 0)
    public Short getPaymentdate() {
        return this.paymentdate;
    }

    public void setPaymentdate(Short paymentdate) {
        this.paymentdate = paymentdate;
    }

    @Column(name = "pmttermscd", length = 4)
    public String getPmttermscd() {
        return this.pmttermscd;
    }

    public void setPmttermscd(String pmttermscd) {
        this.pmttermscd = pmttermscd;
    }

    @Column(name = "preflevel", precision = 1, scale = 0)
    public Boolean getPreflevel() {
        return this.preflevel;
    }

    public void setPreflevel(Boolean preflevel) {
        this.preflevel = preflevel;
    }

    @Column(name = "printmemodesc", length = 1)
    public Character getPrintmemodesc() {
        return this.printmemodesc;
    }

    public void setPrintmemodesc(Character printmemodesc) {
        this.printmemodesc = printmemodesc;
    }

    @Column(name = "printnoteonchk", length = 1)
    public Character getPrintnoteonchk() {
        return this.printnoteonchk;
    }

    public void setPrintnoteonchk(Character printnoteonchk) {
        this.printnoteonchk = printnoteonchk;
    }

    @Column(name = "estrcvbydays", precision = 3, scale = 0)
    public Short getEstrcvbydays() {
        return this.estrcvbydays;
    }

    public void setEstrcvbydays(Short estrcvbydays) {
        this.estrcvbydays = estrcvbydays;
    }

    @Column(name = "cancelbydays", precision = 3, scale = 0)
    public Short getCancelbydays() {
        return this.cancelbydays;
    }

    public void setCancelbydays(Short cancelbydays) {
        this.cancelbydays = cancelbydays;
    }

    @Column(name = "shipviacd", length = 6)
    public String getShipviacd() {
        return this.shipviacd;
    }

    public void setShipviacd(String shipviacd) {
        this.shipviacd = shipviacd;
    }

    @Column(name = "t99cd", length = 1)
    public Character getT99cd() {
        return this.t99cd;
    }

    public void setT99cd(Character t99cd) {
        this.t99cd = t99cd;
    }

    @Column(name = "t99fedid", length = 12)
    public String getT99fedid() {
        return this.t99fedid;
    }

    public void setT99fedid(String t99fedid) {
        this.t99fedid = t99fedid;
    }

    @Column(name = "vendorfreightratecwt", precision = 9, scale = 4)
    public Float getVendorfreightratecwt() {
        return this.vendorfreightratecwt;
    }

    public void setVendorfreightratecwt(Float vendorfreightratecwt) {
        this.vendorfreightratecwt = vendorfreightratecwt;
    }

    @Column(name = "vendorchargeamt", precision = 9)
    public Float getVendorchargeamt() {
        return this.vendorchargeamt;
    }

    public void setVendorchargeamt(Float vendorchargeamt) {
        this.vendorchargeamt = vendorchargeamt;
    }

    @Column(name = "otherfreightratecwt", precision = 9, scale = 4)
    public Float getOtherfreightratecwt() {
        return this.otherfreightratecwt;
    }

    public void setOtherfreightratecwt(Float otherfreightratecwt) {
        this.otherfreightratecwt = otherfreightratecwt;
    }

    @Column(name = "othermarkuppct", precision = 4, scale = 1)
    public Float getOthermarkuppct() {
        return this.othermarkuppct;
    }

    public void setOthermarkuppct(Float othermarkuppct) {
        this.othermarkuppct = othermarkuppct;
    }

    @Column(name = "vendormarkuppct", precision = 4, scale = 1)
    public Float getVendormarkuppct() {
        return this.vendormarkuppct;
    }

    public void setVendormarkuppct(Float vendormarkuppct) {
        this.vendormarkuppct = vendormarkuppct;
    }

    @Column(name = "otherchargeamt", precision = 9)
    public Float getOtherchargeamt() {
        return this.otherchargeamt;
    }

    public void setOtherchargeamt(Float otherchargeamt) {
        this.otherchargeamt = otherchargeamt;
    }

    @Column(name = "t99_avail", precision = 12)
    public Float getT99Avail() {
        return this.t99Avail;
    }

    public void setT99Avail(Float t99Avail) {
        this.t99Avail = t99Avail;
    }

    @Column(name = "t99_actual", precision = 12)
    public Float getT99Actual() {
        return this.t99Actual;
    }

    public void setT99Actual(Float t99Actual) {
        this.t99Actual = t99Actual;
    }

    @Column(name = "filing_code", length = 1)
    public Character getFilingCode() {
        return this.filingCode;
    }

    public void setFilingCode(Character filingCode) {
        this.filingCode = filingCode;
    }

    @Column(name = "dbaname", length = 40)
    public String getDbaname() {
        return this.dbaname;
    }

    public void setDbaname(String dbaname) {
        this.dbaname = dbaname;
    }

    @Column(name = "t99fedidisssnbr", length = 1)
    public Character getT99fedidisssnbr() {
        return this.t99fedidisssnbr;
    }

    public void setT99fedidisssnbr(Character t99fedidisssnbr) {
        this.t99fedidisssnbr = t99fedidisssnbr;
    }

    @Column(name = "setupby", length = 10)
    public String getSetupby() {
        return this.setupby;
    }

    public void setSetupby(String setupby) {
        this.setupby = setupby;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "setupdate", length = 13)
    public Date getSetupdate() {
        return this.setupdate;
    }

    public void setSetupdate(Date setupdate) {
        this.setupdate = setupdate;
    }

    @Column(name = "accountnbr", length = 50)
    public String getAccountnbr() {
        return this.accountnbr;
    }

    public void setAccountnbr(String accountnbr) {
        this.accountnbr = accountnbr;
    }

    @Column(name = "pinnbr", length = 50)
    public String getPinnbr() {
        return this.pinnbr;
    }

    public void setPinnbr(String pinnbr) {
        this.pinnbr = pinnbr;
    }

    @Column(name = "passwd", length = 50)
    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Column(name = "import", length = 1)
    public Character getImport_() {
        return this.import_;
    }

    public void setImport_(Character import_) {
        this.import_ = import_;
    }

    @Column(name = "po_email", length = 50)
    public String getPoEmail() {
        return this.poEmail;
    }

    public void setPoEmail(String poEmail) {
        this.poEmail = poEmail;
    }

    @Column(name = "cashflowdays", precision = 3, scale = 0)
    public Short getCashflowdays() {
        return this.cashflowdays;
    }

    public void setCashflowdays(Short cashflowdays) {
        this.cashflowdays = cashflowdays;
    }

    @Column(name = "excfromcashflow", length = 1)
    public Character getExcfromcashflow() {
        return this.excfromcashflow;
    }

    public void setExcfromcashflow(Character excfromcashflow) {
        this.excfromcashflow = excfromcashflow;
    }

    @Column(name = "ecutopia", length = 1)
    public Character getEcutopia() {
        return this.ecutopia;
    }

    public void setEcutopia(Character ecutopia) {
        this.ecutopia = ecutopia;
    }

    @Column(name = "port_to_stock_days", precision = 2, scale = 0)
    public Byte getPortToStockDays() {
        return this.portToStockDays;
    }

    public void setPortToStockDays(Byte portToStockDays) {
        this.portToStockDays = portToStockDays;
    }

    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "apv")
    public Set getImsItemVendors() {
        return this.imsItemVendors;
    }

    public void setImsItemVendors(Set imsItemVendors) {
        this.imsItemVendors = imsItemVendors;
    }
*/
    
		
	public KeymarkVendor() {
	}
	
	public KeymarkVendor(Integer vendorNumber) {
		this.vendorNumber = vendorNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vendorNumber == null) ? 0 : vendorNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeymarkVendor other = (KeymarkVendor) obj;
		if (vendorNumber == null) {
			if (other.vendorNumber != null)
				return false;
		} else if (!vendorNumber.equals(other.vendorNumber))
			return false;
		return true;
	}

}
