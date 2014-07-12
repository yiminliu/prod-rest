package com.bedrosians.bedlogic.domain.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.stereotype.Component;

import com.bedrosians.bedlogic.domain.account.embeddable.Address;
import com.bedrosians.bedlogic.domain.account.embeddable.Contact;
import com.bedrosians.bedlogic.domain.account.enums.Status;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.account.AccountDataUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value = "accountBranch")
@Entity
@Table(name="armbr")
@DynamicUpdate(value=true)
@SelectBeforeUpdate(value=true)
@DynamicInsert(value=true)

public class Branch implements Serializable {
	
	private static final long serialVersionUID = 5137707010951170389L;

	private BranchPK branchPK;
	private String branchName;
	private Integer storeNumber;
	private String inactiveCode;
	private String priceGroup;
	private String customerGroup;
	private String groupCode;
	private String userCode;
	private Integer branchSalesNo;
	private String contractLicenseNo;
	private String taxClass;
	private String resaleNo;
	private String authOnly;
	private String b2FromCorp;
	private String accountManager;
	private Address address;
	private Contact apContact;
	private Contact managerContact;
	private Contact purchaserContact;
	private Contact salesContact;
	private Account account;
	
	
	@JsonIgnore
	@EmbeddedId
	public BranchPK getBranchPK() {
		return branchPK;
	}
	public void setBranchPK(BranchPK branchPK) {
		this.branchPK = branchPK;
	}
	
	@Column(name="storenbr")
	public Integer getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(Integer storeNumber) {
		this.storeNumber = storeNumber;
	}

	@Column(name="pricegroup")
	public String getPriceGroup() {
		return priceGroup;
	}
	public void setPriceGroup(String priceGroup) {
		this.priceGroup = priceGroup;
	}

	@Column(name="custgroup")
	public String getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	
	@Column(name="groupcd")
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	@Column(name="usercd")
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name="branchsalesnbr")
	public Integer getBranchSalesNo() {
		return branchSalesNo;
	}
	public void setBranchSalesNo(Integer branchSalesNo) {
		this.branchSalesNo = branchSalesNo;
	}

	@Column(name="contractLicnbr")
	public String getContractLicenseNo() {
		return contractLicenseNo;
	}
	public void setContractLicenseNo(String contractLicenseNo) {
		this.contractLicenseNo = contractLicenseNo;
	}

	@Column(name="taxclass")
	public String getTaxClass() {
		return taxClass;
	}
	public void setTaxClass(String taxClass) {
		this.taxClass = taxClass;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "custcd", updatable=false, insertable=false)
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	} 
	
	@Column(name="brname") 
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name="InactiveCd")
	public String getInactiveCode() {
		return inactiveCode;
	}		
	public void setInactiveCode(String inactiveCode) {
		this.inactiveCode = inactiveCode;
	}

	
	@Column(name="resalenbr")
	public String getResaleNo() {
		return resaleNo;
	}
	public void setResaleNo(String resaleNo) {
		this.resaleNo = resaleNo;
	}
	
	@Column(name="authonly")
	public String getAuthOnly() {
		return authOnly;
	}
	public void setAuthOnly(String authOnly) {
		this.authOnly = authOnly;
	}
	
	@Column(name="b2fromcorp")
	public String getB2FromCorp() {
		return b2FromCorp;
	}
	public void setB2FromCorp(String b2FromCorp) {
		this.b2FromCorp = b2FromCorp;
	}
	
	@Column(name="ourarcontact")	
	public String getAccountManager() {
		return accountManager;
	}
	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="streeLine1", column=@Column(name="brAddr1")),
		@AttributeOverride(name="streeLine2", column=@Column(name="brAddr2")),
		@AttributeOverride(name="city", column=@Column(name="brcity")),
		@AttributeOverride(name="state", column=@Column(name="brStateCd")),
		@AttributeOverride(name="zip", column=@Column(name="brZip")),
		@AttributeOverride(name="country", column=@Column(name="brCountryCd"))
	})
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="apContact")),
		@AttributeOverride(name="email", column=@Column(name="apEmail")),
		@AttributeOverride(name="fax", column=@Column(name="apFax")),
		@AttributeOverride(name="notes", column=@Column(name="apNotes")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="apPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="apExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="apCellPhone"))
	})
	public Contact getApContact() {
		return apContact;
	}

	public void setApContact(Contact apContact) {
		this.apContact = apContact;
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="mgrContact")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="mgrPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="mgrExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="mgrCellPhone")),
		@AttributeOverride(name="email", column=@Column(name="mgrEmail")),
		@AttributeOverride(name="fax", column=@Column(name="mgrFax")),
		@AttributeOverride(name="notes", column=@Column(name="mgrNotes"))		
	})
	public Contact getManagerContact() {
		return managerContact;
	}
	public void setManagerContact(Contact managerContact) {
		this.managerContact = managerContact;
	}
	
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="purContact")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="purPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="purExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="purCellPhone")),
		@AttributeOverride(name="email", column=@Column(name="purEmail")),
		@AttributeOverride(name="fax", column=@Column(name="purFax")),
		@AttributeOverride(name="notes", column=@Column(name="purNotes"))		
	})
	public Contact getPurchaserContact() {
		return purchaserContact;
	}
	public void setPurchaserContact(Contact purchaserContact) {
		this.purchaserContact = purchaserContact;
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="slsContact")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="slsPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="slsExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="slsCellPhone")),
		@AttributeOverride(name="email", column=@Column(name="slsEmail")),
		@AttributeOverride(name="fax", column=@Column(name="slsFax")),
		@AttributeOverride(name="notes", column=@Column(name="slsNotes"))	
	})
	public Contact getSalesContact() {
		return salesContact;
	}
	public void setSalesContact(Contact salesContact) {
		this.salesContact = salesContact;
	}


	@Transient
	public String getCustomerCode() {
		return branchPK.getCustomerCode();
	}
	
	@Transient
	public String getBranchCode() {
		return branchPK.getBranchCode();
	}
	
    //@Transient
    //public boolean isTreatedAsStore(){
	//       return AccountDataUtil.determineIsTreatedAsStore(account);	
	//}
	 
    @Transient
    public boolean isDefaultBranch(){
       return AccountDataUtil.determineIsDefaultBranch(account, branchPK.getBranchCode());	
    }
    
    @Transient
    public String getCreditStatus(){
       return AccountDataUtil.getCreditStatus(account);	
    }
    

	/*
	 *   $sql = "select arm.defbranchcd, arm.treatasstore, arm.creditstatus";
        $sql .= ", armbr.custcd, armbr.branchcd, armbr.inactivecd, armbr.pricegroup";
        $sql .= ", armbr.storenbr, armbr.brname, armbr.braddr1, armbr.braddr2, armbr.brcity, armbr.brstatecd, armbr.brzip";
        $sql .= ", armbr.apcontact, armbr.apphone, armbr.apext";
        $sql .= " from arm join armbr on arm.custcd=armbr.custcd";
      
      
	armbr.pricegroup";
    $sql .= ", armbr.storenbr, armbr.brname, armbr.braddr1, armbr.braddr2, armbr.brcity, armbr.brstatecd, armbr.brzip";
    $sql .= ", armbr.apcontact, armbr.apphone, armbr.apext";
    $sql .= " from arm join armbr on arm.custcd=armbr.custcd";
    */	
    
	public Branch(){
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result
				+ ((branchPK == null) ? 0 : branchPK.hashCode());
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
		Branch other = (Branch) obj;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		if (branchPK == null) {
			if (other.branchPK != null)
				return false;
		} else if (!branchPK.equals(other.branchPK))
			return false;
		return true;
	}
	
/*@Embedded
	private Contact apContact;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="mgrContact")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="mgrPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="mgrExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="mgrCellPhone")),
		@AttributeOverride(name="email", column=@Column(name="mgrEmail")),
		@AttributeOverride(name="fax", column=@Column(name="mgrFax")),
		@AttributeOverride(name="notes", column=@Column(name="mgrNotes"))		
	})
	private Contact managerContact;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="purContact")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="purPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="purExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="purCellPhone")),
		@AttributeOverride(name="email", column=@Column(name="purEmail")),
		@AttributeOverride(name="fax", column=@Column(name="purFax")),
		@AttributeOverride(name="notes", column=@Column(name="purNotes"))		
	})
	private Contact purchaseContact;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="slsContact")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="slsPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="slsExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="slsCellPhone")),
		@AttributeOverride(name="email", column=@Column(name="slsEmail")),
		@AttributeOverride(name="fax", column=@Column(name="slsFax")),
		@AttributeOverride(name="notes", column=@Column(name="slsNotes"))
		
	})
	private Contact salesContact;


	@Column(name="authonly")
	private String authOnly;
	
	@Column(name="b2email")
	private String b2Email;
	
	@Column(name="b2fax")
	private Long b2Fax;
	
	@Column(name="b2fromcorp")
	private String b2FromCorp;
	
	@Column(name="branchsalesnbr")
	private Integer branchSalesNo;
	
	@Column(name="contractLicnbr")
	private String contractLicenseNo;
	
	@Column(name="daystopay")
	private Integer daysToPay;
	
	@Temporal(TemporalType.DATE)
	@Column(name="firstsolddate")
	private Date firstSoldDate;
	
	@Column(name="highbalamt")
	private Float highBalAmt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="highbaldate")
	private Date highBalDate;
	
	@Column(name="invtype")
	private String invType;
	
	@Column(name="laststmtamt")
	private Float lastStatementAmount;
	
	@Temporal(TemporalType.DATE)
	@Column(name="laststmtdate")
	private Date lastStatmentDate;
	
	@Column(name="ourarcontact")
	private String accountManager;
	
	@Column(name="resalenbr")
	private String resaleNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="setupdate")
	private Date setupDate;
	
	@Column(name="stmtnbr")
	private String statementNo;
	
	@Column(name="stmttype")
	private String  statementType;
	
	@Column(name="storenbr")
	private Integer storeNo;
		
	@Column(name="taxclass")
	private String taxClass;
	
	@Column(name="totbalamt")
	private Float totbalAmount;
	
	@Temporal(TemporalType.DATE)
	@Column(name="invlaste_date")
	private Date invLasteDate;
	
	@Column(name="batchinv")
	private String batchInventory;
	
	@Column(name="printstmt")
	private String printStatement ;
	
	@Column(name="custgroup")
	private Integer customerGroup;
	
	@Column(name="pricegroup")
	private String priceGroup;	
	
	@Temporal(TemporalType.DATE)
	@Column(name="invlastm_date")
	private Date inventoryLastMDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="stmtlaste_date")
	private Date statementLastEDate;
		
	//@Column(name="apemail2")
	//private String  apEmail2;
	
	@Column(name="stmtlaste_amt")
	private Float statementLastEAmount;
	
	@Column(name="wdaystopay")
	private Integer wDaysToPay;
	
	@Column(name="setupby")
	private String setupBy;
	
	@Column(name="notblockcheck")
	private String  notBlockCheck;
	
	@Temporal(TemporalType.DATE)
	@Column(name="resalenbr_expdate")
	private Date resaleNumberExpDate;
	
	@Column(name="groupcd")
	private String groupId;
	
	@Column(name="usercd")
	private String userId;
	
	@Column(name="infoEmail1")
	private String infoEmail1;
	@Column(name="infoEmail2")
	private String infoEmail2;
	@Column(name="infoEmail3")
	private String infoEmail3;
	@Column(name="infoEmail4")
	private String infoEmail4;
	@Column(name="infoEmail5")
	private String infoEmail5;
	@Column(name="infoEmail6")
	private String infoEmail6;
	@Column(name="infoEmail7")
	private String infoEmail7;
	@Column(name="infoEmail8")
	private String infoEmail8;
	@Column(name="infoEmail9")
	private String infoEmail9;
	
	@Column(name="resalenbr2")
	private Integer resaleNumber2;
	@Temporal(TemporalType.DATE)
	@Column(name="resalenbr2_expdate")
	private Date resaleNumber2ExpDate;
	
	@Column(name="resalenbr3")
	private String resaleNumber3;
	@Temporal(TemporalType.DATE)
	@Column(name="resalenbr3_expdate")
	private Date resaleNumber3ExpDate;
	@Column(name="resalenbr4")
	private String resaleNumnber4;
	@Temporal(TemporalType.DATE)
	@Column(name="resalenbr4_expdate")
	private Date resaleNumber4ExpDate;
	@Column(name="resalenbr5")
	private String resaleNumber5;
	@Temporal(TemporalType.DATE)
	@Column(name="resalenbr5_expdate")
	private Date resaleNumber5ExpDate;
		
	@Column(name="resaleNBr1_statecd")
	private String resaleNumber1StateCode;
		
	@Column(name="resaleNBr2_statecd")
	private String resaleNumber2StateCode;
	@Column(name="resaleNBr3_statecd")
	private String resaleNumber3StateCode;
	@Column(name="resaleNBr4_statecd")
	private String resaleNumber4StateCode;
	@Column(name="resaleNBr5_statecd")
	private String resaleNumber5StateCode;
	
	@Column(name="taxstatecd")
	private String taxStateId;
	@Column(name="taxlocalcd")
	private String taxLocalId;
		
	@Temporal(TemporalType.DATE)
	@Column(name="branchSalesnbrdate")
	private Date branchSalesNumberDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="pricegroupdate")
	private Date priceGroupDate;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="streeLine1", column=@Column(name="physAddr1")),
		@AttributeOverride(name="streeLine2", column=@Column(name="physAddr2")),
		@AttributeOverride(name="city", column=@Column(name="physcity")),
		@AttributeOverride(name="state", column=@Column(name="physStateCd")),
		@AttributeOverride(name="zip", column=@Column(name="physZip")),
	})
	private Address physicalAddress;
*/	

	/*$ArmBr_CustCd,
	 $ArmBr_BranchCd,
	 $ArmBr_BrName,
	 $ArmBr_BrAddr1,
	 $ArmBr_BrAddr2,
	 $ArmBr_BrCity,
	 $ArmBr_BrStateCd,
	 $ArmBr_BrZip,
	 $ArmBr_BrCountryCd,
	 $ArmBr_InactiveCd,
	 $ArmBr_ApContact,
	 $ArmBr_ApPhone,
	 $ArmBr_ApExt,
	 $ArmBr_ApCellPhone,
	 $ArmBr_ApFax,
	 $ArmBr_ApEmail,
	 $ArmBr_ApNotes,
	 $ArmBr_MgrContact,
	 $ArmBr_MgrPhone,
	 $ArmBr_MgrExt,
	 $ArmBr_MgrCellPhone,
	 $ArmBr_MgrFax,
	 $ArmBr_MgrEmail,
	 $ArmBr_MgrNotes,
	 $ArmBr_PurContact,
	 $ArmBr_PurPhone,
	 $ArmBr_PurExt,
	 $ArmBr_PurCellPhone,
	 $ArmBr_PurFax,
	 $ArmBr_PurEmail,
	 $ArmBr_PurNotes,
	 $ArmBr_SlsContact,
	 $ArmBr_SlsPhone,
	 $ArmBr_SlsExt,
	 $ArmBr_SlsCellPhone,
	 $ArmBr_SlsFax,
	 $ArmBr_SlsEmail,
	 $ArmBr_SlsNotes,
	 $ArmBr_AcctNote1,
	 $ArmBr_AcctNote2,
	 $ArmBr_AcctNote3,
	 $ArmBr_AcctNote4,
	 $ArmBr_AuthOnly,
	 $ArmBr_B2Email,
	 $ArmBr_B2Fax,
	 $ArmBr_B2FromCorp,
	 $ArmBr_BranchSalesNbr,
	 $ArmBr_ContractLicNbr,
	 $ArmBr_DaysToPay,
	 $ArmBr_DefShipToCd,
	 $ArmBr_FirstSoldDate,
	 $ArmBr_HighBalAmt,
	 $ArmBr_HighBalDate,
	 $ArmBr_InvType,
	 $ArmBr_LastStmtAmt,
	 $ArmBr_LastStmtDate,
	 $ArmBr_OurArContact,
	 $ArmBr_ResaleNbr,
	 $ArmBr_SetupDate,
	 $ArmBr_StmtNbr,
	 $ArmBr_StmtType,
	 $ArmBr_StoreNbr,
	 $ArmBr_TaxClass,
	 $ArmBr_TotBalAmt,
	 $ArmBr_BatchInv,
	 $ArmBr_PrintStmt,
	 $ArmBr_CustGroup,
	 $ArmBr_PriceGroup,
	 $ArmBr_InvLastE_Date,
	 $ArmBr_InvLastM_Date,
	 $ArmBr_StmtLastE_Date,
	 $ArmBr_ApEmail2,
	 $ArmBr_StmtLastE_Amt,
	 $ArmBr_WDaysToPay,
	 $ArmBr_SetupBy,
	 $ArmBr_NotBlockCheck,
	 $ArmBr_ResaleNbr_ExpDate,
	 $ArmBr_GrpCode,
	 $ArmBr_UserCd,
	 $ArmBr_InfoEmail1,
	 $ArmBr_InfoEmail2,
	 $ArmBr_InfoEmail3,
	 $ArmBr_InfoEmail4,
	 $ArmBr_InfoEmail5,
	 $ArmBr_InfoEmail6,
	 $ArmBr_InfoEmail7,
	 $ArmBr_InfoEmail8,
	 $ArmBr_InfoEmail9,
	 $ArmBr_ResaleNbr2,
	 $ArmBr_ResaleNbr2_ExpDate,
	 $ArmBr_ResaleNbr3,
	 $ArmBr_ResaleNbr3_ExpDate,
	 $ArmBr_ResaleNbr4,
	 $ArmBr_ResaleNbr4_ExpDate,
	 $ArmBr_ResaleNbr5,
	 $ArmBr_ResaleNbr5_ExpDate,
	 $ArmBr_ResaleNbr1_StateCd,
	 $ArmBr_ResaleNbr2_StateCd,
	 $ArmBr_ResaleNbr3_StateCd,
	 $ArmBr_ResaleNbr4_StateCd,
	 $ArmBr_ResaleNbr5_StateCd,
	 $ArmBr_TaxStateCd,
	 $ArmBr_TaxLocalCd,
	 $ArmBr_BranchSalesNbrDate,
	 $ArmBr_PriceGroupDate,
	 $ArmBr_PhysAddr1,
	 $ArmBr_PhysAddr2,
	 $ArmBr_PhysCity,
	 $ArmBr_PhysStateCd,
	 $ArmBr_PhysZip
	) = trim_array($ArmBr_Record);
	if ($ArmBr_Found == 'no') $ArmBr_Record = array();
	$ArmBr_FirstSoldDate_df = cnv_date("$ArmBr_FirstSoldDate");
	$ArmBr_LastStmtDate_df = cnv_date("$ArmBr_LastStmtDate");
	$ArmBr_SetupDate_df = cnv_date("$ArmBr_SetupDate");
	$ArmBr_InvLastE_Date_df = cnv_date("$ArmBr_InvLastE_Date");
	$ArmBr_InvLastM_Date_df = cnv_date("$ArmBr_InvLastM_Date");
	$ArmBr_StmtLastE_Date_df = cnv_date("$ArmBr_StmtLastE_Date");
	$ArmBr_ResaleNbr_ExpDate_df = cnv_date("$ArmBr_ResaleNbr_ExpDate");
	$ArmBr_ResaleNbr2_ExpDate_df = cnv_date("$ArmBr_ResaleNbr2_ExpDate");
	$ArmBr_ResaleNbr3_ExpDate_df = cnv_date("$ArmBr_ResaleNbr3_ExpDate");
	$ArmBr_ResaleNbr4_ExpDate_df = cnv_date("$ArmBr_ResaleNbr4_ExpDate");
	$ArmBr_ResaleNbr5_ExpDate_df = cnv_date("$ArmBr_ResaleNbr5_ExpDate");
	$ArmBr_BranchSalesNbrDate_df = cnv_date("$ArmBr_BranchSalesNbrDate");
	$ArmBr_PriceGroupDate_df = cnv_date("$ArmBr_PriceGroupDate");
	*/
}
