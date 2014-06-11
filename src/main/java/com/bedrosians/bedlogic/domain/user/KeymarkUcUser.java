package com.bedrosians.bedlogic.domain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;


@Entity
@Table(name = "uc", schema = "public")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Immutable
public class KeymarkUcUser implements Serializable {

	    private static final long serialVersionUID = -3213582221787L;
	
		private String userCode;
		private String username;
		private String passwd;
		private Character active;
		private Long employeenbr;
		private String groupcode;
		private Short storenbr;
		private Character arallowadminpriv;
		private Character arcreatecustcd;
		private Character arallowpostpmt;
		private Character arallowcreatestmt;
		private Character arallowcreatefc;
		private Character imallowcreateitem;
		private Character imchgavgcost;
		private Character imchgpurchcost;
		private Character imchgtmpprice;
		private Character imchgqty;
		private Character imallowchgims;
		private Character imsetcustpricing;
		private Character arenternotes;
		private Character viewSlabCosts;
		private Character permInventory;
		
		//private Character permApv;
		//private Character permUc;
		//private Character permOffshade;
		//private Character displayMenuIcons;
		//private Short brStore1;
		//private String notes;
		//private String database;
		//private String onlyipaddress;
		//private Character arapprovecheck;
		//private Character areditcheck;
		//private Character oballowauthprelien;
		//private Character oballowchgatc;
		//private Character oballowchgati;
		//private Character oballowchgbacktoc;
		//private Character oballowchgbacktoo;
		//private Character oballowchgcost;
		//private Character oballowchgcustcd;
		//private Character oballowchgpmtterms;
		//private Character obcheckcrholdatst;
		//private String obdefsourcecd;
		//private Character obdisplaycost;
		//private Character obdisplayonly;
		//private BigDecimal obmaxcramtperinv;
		//private BigDecimal obmaxinvamt;
		//private BigDecimal obmaxinvamtwoprel;
		//private String obonlycustcd;
		//private String obonlybranchcd;
		//private Short obonlystorenbr;
		//private Character oboverridecrlimit;
		//private Character oboverridecrhold;
		//private Short obsalesnbr;
		//private Short obminmarginpct;
		//private Character oballowchgatu;
		//private Character arenterbankdep;
		//private Character oballowsamples;
		//private Character oballowtransfers;
		//private Character oballowwriteoffs;
		//private Character oboverridemargin;
		//private String note2;
		//private Character arviewreports;
		//private Character crupdatebranchdep;
		//private Character arallowglentry;
		//private Character prallowbankrec;
		//private Character rptcreatecsv;
		//private Character poallowcreatepo;
		//private Character poallowreceive;
		//private Character poallowinvoice;
		//private Character podisplayprice;
		//private Character podisplayonly;
		//private Character posuperuser;
		//private String email;
		//private Date inactivedate;
		//private Character poallowconfirm;
		//private Character poallowapprove;
		//private Character permGlm;
		//private Character permGlrec;
		//private Date setupdate;
		//private Character permApi;
		//private Character permTask;
		//private Character permBatchinv;
		//private Character permEom;
		//private Short obonlystorenbr2;
		//private Short obonlystorenbr3;
		//private Short obonlystorenbr4;
		//private Short obonlystorenbr5;
		//private Character regioncd;
		//private Short obmaxdiscpct;
		//private Character permDmsrpt;
		//private Character permAdjslabsize;
		//private Character oboverridecustpricing;
		//private Short obdefaultstorenbr;
		//private Short brStore2;
		//private Short brStore3;
		//private Short brStore4;
		//private Short brStore5;
		//private Character uploadPic;
		//private Character permSlabMaint;
		//private Character permPromoMaint;
		//private Character permBulletin;
		//private String accesstoken;
		//private Date tokents;
		//private Integer retries;

		public KeymarkUcUser() {
		}

		public KeymarkUcUser(String userCode) {
			this.userCode = userCode;
		}

		@Id
		@Column(name = "usercd", unique = true, nullable = false, length = 10)
		public String getUserCode() {
			return this.userCode;
		}

		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}

		@Column(name = "username", length = 25)
		public String getUsername() {
			return this.username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
		
		@Column(name = "passwd", length = 10)
		public String getPasswd() {
			return this.passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		@Column(name = "arallowadminpriv", length = 1)
		public Character getArallowadminpriv() {
			return this.arallowadminpriv;
		}

		public void setArallowadminpriv(Character arallowadminpriv) {
			this.arallowadminpriv = arallowadminpriv;
		}

		@Column(name = "arcreatecustcd", length = 1)
		public Character getArcreatecustcd() {
			return this.arcreatecustcd;
		}

		public void setArcreatecustcd(Character arcreatecustcd) {
			this.arcreatecustcd = arcreatecustcd;
		}

	
		@Column(name = "arallowpostpmt", length = 1)
		public Character getArallowpostpmt() {
			return this.arallowpostpmt;
		}

		public void setArallowpostpmt(Character arallowpostpmt) {
			this.arallowpostpmt = arallowpostpmt;
		}

		@Column(name = "arallowcreatestmt", length = 1)
		public Character getArallowcreatestmt() {
			return this.arallowcreatestmt;
		}

		public void setArallowcreatestmt(Character arallowcreatestmt) {
			this.arallowcreatestmt = arallowcreatestmt;
		}

		@Column(name = "arallowcreatefc", length = 1)
		public Character getArallowcreatefc() {
			return this.arallowcreatefc;
		}

		public void setArallowcreatefc(Character arallowcreatefc) {
			this.arallowcreatefc = arallowcreatefc;
		}

		@Column(name = "imallowcreateitem", length = 1)
		public Character getImallowcreateitem() {
			return this.imallowcreateitem;
		}

		public void setImallowcreateitem(Character imallowcreateitem) {
			this.imallowcreateitem = imallowcreateitem;
		}

		@Column(name = "imchgavgcost", length = 1)
		public Character getImchgavgcost() {
			return this.imchgavgcost;
		}

		public void setImchgavgcost(Character imchgavgcost) {
			this.imchgavgcost = imchgavgcost;
		}

		@Column(name = "imchgpurchcost", length = 1)
		public Character getImchgpurchcost() {
			return this.imchgpurchcost;
		}

		public void setImchgpurchcost(Character imchgpurchcost) {
			this.imchgpurchcost = imchgpurchcost;
		}

		@Column(name = "imchgtmpprice", length = 1)
		public Character getImchgtmpprice() {
			return this.imchgtmpprice;
		}

		public void setImchgtmpprice(Character imchgtmpprice) {
			this.imchgtmpprice = imchgtmpprice;
		}

		@Column(name = "imchgqty", length = 1)
		public Character getImchgqty() {
			return this.imchgqty;
		}

		public void setImchgqty(Character imchgqty) {
			this.imchgqty = imchgqty;
		}

		@Column(name = "imallowchgims", length = 1)
		public Character getImallowchgims() {
			return this.imallowchgims;
		}

		public void setImallowchgims(Character imallowchgims) {
			this.imallowchgims = imallowchgims;
		}

		@Column(name = "imsetcustpricing", length = 1)
		public Character getImsetcustpricing() {
			return this.imsetcustpricing;
		}

		public void setImsetcustpricing(Character imsetcustpricing) {
			this.imsetcustpricing = imsetcustpricing;
		}

		
		@Column(name = "arenternotes", length = 1)
		public Character getArenternotes() {
			return this.arenternotes;
		}

		public void setArenternotes(Character arenternotes) {
			this.arenternotes = arenternotes;
		}

		
		@Column(name = "active", length = 1)
		public Character getActive() {
			return this.active;
		}

		public void setActive(Character active) {
			this.active = active;
		}

	
		@Column(name = "employeenbr", precision = 10, scale = 0)
		public Long getEmployeenbr() {
			return this.employeenbr;
		}

		public void setEmployeenbr(Long employeenbr) {
			this.employeenbr = employeenbr;
		}

		@Column(name = "groupcode", length = 5)
		public String getGroupcode() {
			return this.groupcode;
		}

		public void setGroupcode(String groupcode) {
			this.groupcode = groupcode;
		}


		@Column(name = "storenbr", precision = 3, scale = 0)
		public Short getStorenbr() {
			return this.storenbr;
		}

		public void setStorenbr(Short storenbr) {
			this.storenbr = storenbr;
		}

		
	
		@Column(name = "view_slab_costs", length = 1)
		public Character getViewSlabCosts() {
			return this.viewSlabCosts;
		}

		public void setViewSlabCosts(Character viewSlabCosts) {
			this.viewSlabCosts = viewSlabCosts;
		}

		@Column(name = "perm_inventory", length = 1)
		public Character getPermInventory() {
			return this.permInventory;
		}

		public void setPermInventory(Character permInventory) {
			this.permInventory = permInventory;
		}

}
