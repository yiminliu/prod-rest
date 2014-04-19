package com.bedrosians.bedlogic.domain.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "uc", schema = "public")
public class KeymarkUcUser implements Serializable {

		private String userCode;
		private String username;
		private String notes;
		private String passwd;
		private String database;
		private String onlyipaddress;
		private Character arallowadminpriv;
		private Character arapprovecheck;
		private Character arcreatecustcd;
		private Character areditcheck;
		private Character oballowauthprelien;
		private Character oballowchgatc;
		private Character oballowchgati;
		private Character oballowchgbacktoc;
		private Character oballowchgbacktoo;
		private Character oballowchgcost;
		private Character oballowchgcustcd;
		private Character oballowchgpmtterms;
		private Character obcheckcrholdatst;
		private String obdefsourcecd;
		private Character obdisplaycost;
		private Character obdisplayonly;
		private BigDecimal obmaxcramtperinv;
		private BigDecimal obmaxinvamt;
		private BigDecimal obmaxinvamtwoprel;
		private String obonlycustcd;
		private String obonlybranchcd;
		private Short obonlystorenbr;
		private Character oboverridecrlimit;
		private Character oboverridecrhold;
		private Short obsalesnbr;
		private Short obminmarginpct;
		private Character oballowchgatu;
		private Character arenterbankdep;
		private Character oballowsamples;
		private Character oballowtransfers;
		private Character oballowwriteoffs;
		private Character oboverridemargin;
		private Character arallowpostpmt;
		private Character arallowcreatestmt;
		private Character arallowcreatefc;
		private Character imallowcreateitem;
		private String note2;
		private Character imchgavgcost;
		private Character imchgpurchcost;
		private Character imchgtmpprice;
		private Character imchgqty;
		private Character imallowchgims;
		private Character imsetcustpricing;
		private Character arviewreports;
		private Character crupdatebranchdep;
		private Character arallowglentry;
		private Character prallowbankrec;
		private Character arenternotes;
		private Character rptcreatecsv;
		private Character poallowcreatepo;
		private Character poallowreceive;
		private Character poallowinvoice;
		private Character podisplayprice;
		private Character podisplayonly;
		private Character posuperuser;
		private String email;
		private Character active;
		private Date inactivedate;
		private Character poallowconfirm;
		private Character poallowapprove;
		private Character permApv;
		private Character permGlm;
		private Character permUc;
		private Character permGlrec;
		private Date setupdate;
		private Long employeenbr;
		private String groupcode;
		private Character permApi;
		private Character permTask;
		private Short storenbr;
		private Character permBatchinv;
		private Character permEom;
		private Short obonlystorenbr2;
		private Short obonlystorenbr3;
		private Short obonlystorenbr4;
		private Short obonlystorenbr5;
		private Character regioncd;
		private Short obmaxdiscpct;
		private Character permDmsrpt;
		private Character permAdjslabsize;
		private Character oboverridecustpricing;
		private Character viewSlabCosts;
		private Character permOffshade;
		private Character permInventory;
		private Short obdefaultstorenbr;
		private Character displayMenuIcons;
		private Short brStore1;
		private Short brStore2;
		private Short brStore3;
		private Short brStore4;
		private Short brStore5;
		private Character uploadPic;
		private Character permSlabMaint;
		private Character permPromoMaint;
		private Character permBulletin;
		private String accesstoken;
		private Date tokents;
		private Integer retries;

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

		@Column(name = "notes", length = 60)
		public String getNotes() {
			return this.notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		@Column(name = "passwd", length = 10)
		public String getPasswd() {
			return this.passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		@Column(name = "database", length = 4)
		public String getDatabase() {
			return this.database;
		}

		public void setDatabase(String database) {
			this.database = database;
		}

		@Column(name = "onlyipaddress", length = 16)
		public String getOnlyipaddress() {
			return this.onlyipaddress;
		}

		public void setOnlyipaddress(String onlyipaddress) {
			this.onlyipaddress = onlyipaddress;
		}

		@Column(name = "arallowadminpriv", length = 1)
		public Character getArallowadminpriv() {
			return this.arallowadminpriv;
		}

		public void setArallowadminpriv(Character arallowadminpriv) {
			this.arallowadminpriv = arallowadminpriv;
		}

		@Column(name = "arapprovecheck", length = 1)
		public Character getArapprovecheck() {
			return this.arapprovecheck;
		}

		public void setArapprovecheck(Character arapprovecheck) {
			this.arapprovecheck = arapprovecheck;
		}

		@Column(name = "arcreatecustcd", length = 1)
		public Character getArcreatecustcd() {
			return this.arcreatecustcd;
		}

		public void setArcreatecustcd(Character arcreatecustcd) {
			this.arcreatecustcd = arcreatecustcd;
		}

		@Column(name = "areditcheck", length = 1)
		public Character getAreditcheck() {
			return this.areditcheck;
		}

		public void setAreditcheck(Character areditcheck) {
			this.areditcheck = areditcheck;
		}

		@Column(name = "oballowauthprelien", length = 1)
		public Character getOballowauthprelien() {
			return this.oballowauthprelien;
		}

		public void setOballowauthprelien(Character oballowauthprelien) {
			this.oballowauthprelien = oballowauthprelien;
		}

		@Column(name = "oballowchgatc", length = 1)
		public Character getOballowchgatc() {
			return this.oballowchgatc;
		}

		public void setOballowchgatc(Character oballowchgatc) {
			this.oballowchgatc = oballowchgatc;
		}

		@Column(name = "oballowchgati", length = 1)
		public Character getOballowchgati() {
			return this.oballowchgati;
		}

		public void setOballowchgati(Character oballowchgati) {
			this.oballowchgati = oballowchgati;
		}

		@Column(name = "oballowchgbacktoc", length = 1)
		public Character getOballowchgbacktoc() {
			return this.oballowchgbacktoc;
		}

		public void setOballowchgbacktoc(Character oballowchgbacktoc) {
			this.oballowchgbacktoc = oballowchgbacktoc;
		}

		@Column(name = "oballowchgbacktoo", length = 1)
		public Character getOballowchgbacktoo() {
			return this.oballowchgbacktoo;
		}

		public void setOballowchgbacktoo(Character oballowchgbacktoo) {
			this.oballowchgbacktoo = oballowchgbacktoo;
		}

		@Column(name = "oballowchgcost", length = 1)
		public Character getOballowchgcost() {
			return this.oballowchgcost;
		}

		public void setOballowchgcost(Character oballowchgcost) {
			this.oballowchgcost = oballowchgcost;
		}

		@Column(name = "oballowchgcustcd", length = 1)
		public Character getOballowchgcustcd() {
			return this.oballowchgcustcd;
		}

		public void setOballowchgcustcd(Character oballowchgcustcd) {
			this.oballowchgcustcd = oballowchgcustcd;
		}

		@Column(name = "oballowchgpmtterms", length = 1)
		public Character getOballowchgpmtterms() {
			return this.oballowchgpmtterms;
		}

		public void setOballowchgpmtterms(Character oballowchgpmtterms) {
			this.oballowchgpmtterms = oballowchgpmtterms;
		}

		@Column(name = "obcheckcrholdatst", length = 1)
		public Character getObcheckcrholdatst() {
			return this.obcheckcrholdatst;
		}

		public void setObcheckcrholdatst(Character obcheckcrholdatst) {
			this.obcheckcrholdatst = obcheckcrholdatst;
		}

		@Column(name = "obdefsourcecd", length = 6)
		public String getObdefsourcecd() {
			return this.obdefsourcecd;
		}

		public void setObdefsourcecd(String obdefsourcecd) {
			this.obdefsourcecd = obdefsourcecd;
		}

		@Column(name = "obdisplaycost", length = 1)
		public Character getObdisplaycost() {
			return this.obdisplaycost;
		}

		public void setObdisplaycost(Character obdisplaycost) {
			this.obdisplaycost = obdisplaycost;
		}

		@Column(name = "obdisplayonly", length = 1)
		public Character getObdisplayonly() {
			return this.obdisplayonly;
		}

		public void setObdisplayonly(Character obdisplayonly) {
			this.obdisplayonly = obdisplayonly;
		}

		@Column(name = "obmaxcramtperinv", precision = 9)
		public BigDecimal getObmaxcramtperinv() {
			return this.obmaxcramtperinv;
		}

		public void setObmaxcramtperinv(BigDecimal obmaxcramtperinv) {
			this.obmaxcramtperinv = obmaxcramtperinv;
		}

		@Column(name = "obmaxinvamt", precision = 9)
		public BigDecimal getObmaxinvamt() {
			return this.obmaxinvamt;
		}

		public void setObmaxinvamt(BigDecimal obmaxinvamt) {
			this.obmaxinvamt = obmaxinvamt;
		}

		@Column(name = "obmaxinvamtwoprel", precision = 9)
		public BigDecimal getObmaxinvamtwoprel() {
			return this.obmaxinvamtwoprel;
		}

		public void setObmaxinvamtwoprel(BigDecimal obmaxinvamtwoprel) {
			this.obmaxinvamtwoprel = obmaxinvamtwoprel;
		}

		@Column(name = "obonlycustcd", length = 10)
		public String getObonlycustcd() {
			return this.obonlycustcd;
		}

		public void setObonlycustcd(String obonlycustcd) {
			this.obonlycustcd = obonlycustcd;
		}

		@Column(name = "obonlybranchcd", length = 4)
		public String getObonlybranchcd() {
			return this.obonlybranchcd;
		}

		public void setObonlybranchcd(String obonlybranchcd) {
			this.obonlybranchcd = obonlybranchcd;
		}

		@Column(name = "obonlystorenbr", precision = 3, scale = 0)
		public Short getObonlystorenbr() {
			return this.obonlystorenbr;
		}

		public void setObonlystorenbr(Short obonlystorenbr) {
			this.obonlystorenbr = obonlystorenbr;
		}

		@Column(name = "oboverridecrlimit", length = 1)
		public Character getOboverridecrlimit() {
			return this.oboverridecrlimit;
		}

		public void setOboverridecrlimit(Character oboverridecrlimit) {
			this.oboverridecrlimit = oboverridecrlimit;
		}

		@Column(name = "oboverridecrhold", length = 1)
		public Character getOboverridecrhold() {
			return this.oboverridecrhold;
		}

		public void setOboverridecrhold(Character oboverridecrhold) {
			this.oboverridecrhold = oboverridecrhold;
		}

		@Column(name = "obsalesnbr", precision = 4, scale = 0)
		public Short getObsalesnbr() {
			return this.obsalesnbr;
		}

		public void setObsalesnbr(Short obsalesnbr) {
			this.obsalesnbr = obsalesnbr;
		}

		@Column(name = "obminmarginpct", precision = 3, scale = 0)
		public Short getObminmarginpct() {
			return this.obminmarginpct;
		}

		public void setObminmarginpct(Short obminmarginpct) {
			this.obminmarginpct = obminmarginpct;
		}

		@Column(name = "oballowchgatu", length = 1)
		public Character getOballowchgatu() {
			return this.oballowchgatu;
		}

		public void setOballowchgatu(Character oballowchgatu) {
			this.oballowchgatu = oballowchgatu;
		}

		@Column(name = "arenterbankdep", length = 1)
		public Character getArenterbankdep() {
			return this.arenterbankdep;
		}

		public void setArenterbankdep(Character arenterbankdep) {
			this.arenterbankdep = arenterbankdep;
		}

		@Column(name = "oballowsamples", length = 1)
		public Character getOballowsamples() {
			return this.oballowsamples;
		}

		public void setOballowsamples(Character oballowsamples) {
			this.oballowsamples = oballowsamples;
		}

		@Column(name = "oballowtransfers", length = 1)
		public Character getOballowtransfers() {
			return this.oballowtransfers;
		}

		public void setOballowtransfers(Character oballowtransfers) {
			this.oballowtransfers = oballowtransfers;
		}

		@Column(name = "oballowwriteoffs", length = 1)
		public Character getOballowwriteoffs() {
			return this.oballowwriteoffs;
		}

		public void setOballowwriteoffs(Character oballowwriteoffs) {
			this.oballowwriteoffs = oballowwriteoffs;
		}

		@Column(name = "oboverridemargin", length = 1)
		public Character getOboverridemargin() {
			return this.oboverridemargin;
		}

		public void setOboverridemargin(Character oboverridemargin) {
			this.oboverridemargin = oboverridemargin;
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

		@Column(name = "note2", length = 20)
		public String getNote2() {
			return this.note2;
		}

		public void setNote2(String note2) {
			this.note2 = note2;
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

		@Column(name = "arviewreports", length = 1)
		public Character getArviewreports() {
			return this.arviewreports;
		}

		public void setArviewreports(Character arviewreports) {
			this.arviewreports = arviewreports;
		}

		@Column(name = "crupdatebranchdep", length = 1)
		public Character getCrupdatebranchdep() {
			return this.crupdatebranchdep;
		}

		public void setCrupdatebranchdep(Character crupdatebranchdep) {
			this.crupdatebranchdep = crupdatebranchdep;
		}

		@Column(name = "arallowglentry", length = 1)
		public Character getArallowglentry() {
			return this.arallowglentry;
		}

		public void setArallowglentry(Character arallowglentry) {
			this.arallowglentry = arallowglentry;
		}

		@Column(name = "prallowbankrec", length = 1)
		public Character getPrallowbankrec() {
			return this.prallowbankrec;
		}

		public void setPrallowbankrec(Character prallowbankrec) {
			this.prallowbankrec = prallowbankrec;
		}

		@Column(name = "arenternotes", length = 1)
		public Character getArenternotes() {
			return this.arenternotes;
		}

		public void setArenternotes(Character arenternotes) {
			this.arenternotes = arenternotes;
		}

		@Column(name = "rptcreatecsv", length = 1)
		public Character getRptcreatecsv() {
			return this.rptcreatecsv;
		}

		public void setRptcreatecsv(Character rptcreatecsv) {
			this.rptcreatecsv = rptcreatecsv;
		}

		@Column(name = "poallowcreatepo", length = 1)
		public Character getPoallowcreatepo() {
			return this.poallowcreatepo;
		}

		public void setPoallowcreatepo(Character poallowcreatepo) {
			this.poallowcreatepo = poallowcreatepo;
		}

		@Column(name = "poallowreceive", length = 1)
		public Character getPoallowreceive() {
			return this.poallowreceive;
		}

		public void setPoallowreceive(Character poallowreceive) {
			this.poallowreceive = poallowreceive;
		}

		@Column(name = "poallowinvoice", length = 1)
		public Character getPoallowinvoice() {
			return this.poallowinvoice;
		}

		public void setPoallowinvoice(Character poallowinvoice) {
			this.poallowinvoice = poallowinvoice;
		}

		@Column(name = "podisplayprice", length = 1)
		public Character getPodisplayprice() {
			return this.podisplayprice;
		}

		public void setPodisplayprice(Character podisplayprice) {
			this.podisplayprice = podisplayprice;
		}

		@Column(name = "podisplayonly", length = 1)
		public Character getPodisplayonly() {
			return this.podisplayonly;
		}

		public void setPodisplayonly(Character podisplayonly) {
			this.podisplayonly = podisplayonly;
		}

		@Column(name = "posuperuser", length = 1)
		public Character getPosuperuser() {
			return this.posuperuser;
		}

		public void setPosuperuser(Character posuperuser) {
			this.posuperuser = posuperuser;
		}

		@Column(name = "email", length = 50)
		public String getEmail() {
			return this.email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Column(name = "active", length = 1)
		public Character getActive() {
			return this.active;
		}

		public void setActive(Character active) {
			this.active = active;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "inactivedate", length = 13)
		public Date getInactivedate() {
			return this.inactivedate;
		}

		public void setInactivedate(Date inactivedate) {
			this.inactivedate = inactivedate;
		}

		@Column(name = "poallowconfirm", length = 1)
		public Character getPoallowconfirm() {
			return this.poallowconfirm;
		}

		public void setPoallowconfirm(Character poallowconfirm) {
			this.poallowconfirm = poallowconfirm;
		}

		@Column(name = "poallowapprove", length = 1)
		public Character getPoallowapprove() {
			return this.poallowapprove;
		}

		public void setPoallowapprove(Character poallowapprove) {
			this.poallowapprove = poallowapprove;
		}

		@Column(name = "perm_apv", length = 1)
		public Character getPermApv() {
			return this.permApv;
		}

		public void setPermApv(Character permApv) {
			this.permApv = permApv;
		}

		@Column(name = "perm_glm", length = 1)
		public Character getPermGlm() {
			return this.permGlm;
		}

		public void setPermGlm(Character permGlm) {
			this.permGlm = permGlm;
		}

		@Column(name = "perm_uc", length = 1)
		public Character getPermUc() {
			return this.permUc;
		}

		public void setPermUc(Character permUc) {
			this.permUc = permUc;
		}

		@Column(name = "perm_glrec", length = 1)
		public Character getPermGlrec() {
			return this.permGlrec;
		}

		public void setPermGlrec(Character permGlrec) {
			this.permGlrec = permGlrec;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "setupdate", length = 13)
		public Date getSetupdate() {
			return this.setupdate;
		}

		public void setSetupdate(Date setupdate) {
			this.setupdate = setupdate;
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

		@Column(name = "perm_api", length = 1)
		public Character getPermApi() {
			return this.permApi;
		}

		public void setPermApi(Character permApi) {
			this.permApi = permApi;
		}

		@Column(name = "perm_task", length = 1)
		public Character getPermTask() {
			return this.permTask;
		}

		public void setPermTask(Character permTask) {
			this.permTask = permTask;
		}

		@Column(name = "storenbr", precision = 3, scale = 0)
		public Short getStorenbr() {
			return this.storenbr;
		}

		public void setStorenbr(Short storenbr) {
			this.storenbr = storenbr;
		}

		@Column(name = "perm_batchinv", length = 1)
		public Character getPermBatchinv() {
			return this.permBatchinv;
		}

		public void setPermBatchinv(Character permBatchinv) {
			this.permBatchinv = permBatchinv;
		}

		@Column(name = "perm_eom", length = 1)
		public Character getPermEom() {
			return this.permEom;
		}

		public void setPermEom(Character permEom) {
			this.permEom = permEom;
		}

		@Column(name = "obonlystorenbr2", precision = 3, scale = 0)
		public Short getObonlystorenbr2() {
			return this.obonlystorenbr2;
		}

		public void setObonlystorenbr2(Short obonlystorenbr2) {
			this.obonlystorenbr2 = obonlystorenbr2;
		}

		@Column(name = "obonlystorenbr3", precision = 3, scale = 0)
		public Short getObonlystorenbr3() {
			return this.obonlystorenbr3;
		}

		public void setObonlystorenbr3(Short obonlystorenbr3) {
			this.obonlystorenbr3 = obonlystorenbr3;
		}

		@Column(name = "obonlystorenbr4", precision = 3, scale = 0)
		public Short getObonlystorenbr4() {
			return this.obonlystorenbr4;
		}

		public void setObonlystorenbr4(Short obonlystorenbr4) {
			this.obonlystorenbr4 = obonlystorenbr4;
		}

		@Column(name = "obonlystorenbr5", precision = 3, scale = 0)
		public Short getObonlystorenbr5() {
			return this.obonlystorenbr5;
		}

		public void setObonlystorenbr5(Short obonlystorenbr5) {
			this.obonlystorenbr5 = obonlystorenbr5;
		}

		@Column(name = "regioncd", length = 1)
		public Character getRegioncd() {
			return this.regioncd;
		}

		public void setRegioncd(Character regioncd) {
			this.regioncd = regioncd;
		}

		@Column(name = "obmaxdiscpct", precision = 3, scale = 0)
		public Short getObmaxdiscpct() {
			return this.obmaxdiscpct;
		}

		public void setObmaxdiscpct(Short obmaxdiscpct) {
			this.obmaxdiscpct = obmaxdiscpct;
		}

		@Column(name = "perm_dmsrpt", length = 1)
		public Character getPermDmsrpt() {
			return this.permDmsrpt;
		}

		public void setPermDmsrpt(Character permDmsrpt) {
			this.permDmsrpt = permDmsrpt;
		}

		@Column(name = "perm_adjslabsize", length = 1)
		public Character getPermAdjslabsize() {
			return this.permAdjslabsize;
		}

		public void setPermAdjslabsize(Character permAdjslabsize) {
			this.permAdjslabsize = permAdjslabsize;
		}

		@Column(name = "oboverridecustpricing", length = 1)
		public Character getOboverridecustpricing() {
			return this.oboverridecustpricing;
		}

		public void setOboverridecustpricing(Character oboverridecustpricing) {
			this.oboverridecustpricing = oboverridecustpricing;
		}

		@Column(name = "view_slab_costs", length = 1)
		public Character getViewSlabCosts() {
			return this.viewSlabCosts;
		}

		public void setViewSlabCosts(Character viewSlabCosts) {
			this.viewSlabCosts = viewSlabCosts;
		}

		@Column(name = "perm_offshade", length = 1)
		public Character getPermOffshade() {
			return this.permOffshade;
		}

		public void setPermOffshade(Character permOffshade) {
			this.permOffshade = permOffshade;
		}

		@Column(name = "perm_inventory", length = 1)
		public Character getPermInventory() {
			return this.permInventory;
		}

		public void setPermInventory(Character permInventory) {
			this.permInventory = permInventory;
		}

		@Column(name = "obdefaultstorenbr", precision = 3, scale = 0)
		public Short getObdefaultstorenbr() {
			return this.obdefaultstorenbr;
		}

		public void setObdefaultstorenbr(Short obdefaultstorenbr) {
			this.obdefaultstorenbr = obdefaultstorenbr;
		}

		@Column(name = "display_menu_icons", length = 1)
		public Character getDisplayMenuIcons() {
			return this.displayMenuIcons;
		}

		public void setDisplayMenuIcons(Character displayMenuIcons) {
			this.displayMenuIcons = displayMenuIcons;
		}

		@Column(name = "br_store1", precision = 3, scale = 0)
		public Short getBrStore1() {
			return this.brStore1;
		}

		public void setBrStore1(Short brStore1) {
			this.brStore1 = brStore1;
		}

		@Column(name = "br_store2", precision = 3, scale = 0)
		public Short getBrStore2() {
			return this.brStore2;
		}

		public void setBrStore2(Short brStore2) {
			this.brStore2 = brStore2;
		}

		@Column(name = "br_store3", precision = 3, scale = 0)
		public Short getBrStore3() {
			return this.brStore3;
		}

		public void setBrStore3(Short brStore3) {
			this.brStore3 = brStore3;
		}

		@Column(name = "br_store4", precision = 3, scale = 0)
		public Short getBrStore4() {
			return this.brStore4;
		}

		public void setBrStore4(Short brStore4) {
			this.brStore4 = brStore4;
		}

		@Column(name = "br_store5", precision = 3, scale = 0)
		public Short getBrStore5() {
			return this.brStore5;
		}

		public void setBrStore5(Short brStore5) {
			this.brStore5 = brStore5;
		}

		@Column(name = "upload_pic", length = 1)
		public Character getUploadPic() {
			return this.uploadPic;
		}

		public void setUploadPic(Character uploadPic) {
			this.uploadPic = uploadPic;
		}

		@Column(name = "perm_slab_maint", length = 1)
		public Character getPermSlabMaint() {
			return this.permSlabMaint;
		}

		public void setPermSlabMaint(Character permSlabMaint) {
			this.permSlabMaint = permSlabMaint;
		}

		@Column(name = "perm_promo_maint", length = 1)
		public Character getPermPromoMaint() {
			return this.permPromoMaint;
		}

		public void setPermPromoMaint(Character permPromoMaint) {
			this.permPromoMaint = permPromoMaint;
		}

		@Column(name = "perm_bulletin", length = 1)
		public Character getPermBulletin() {
			return this.permBulletin;
		}

		public void setPermBulletin(Character permBulletin) {
			this.permBulletin = permBulletin;
		}

		@Column(name = "accesstoken", length = 32)
		public String getAccesstoken() {
			return this.accesstoken;
		}

		public void setAccesstoken(String accesstoken) {
			this.accesstoken = accesstoken;
		}

		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "tokents", length = 22)
		public Date getTokents() {
			return this.tokents;
		}

		public void setTokents(Date tokents) {
			this.tokents = tokents;
		}

		@Column(name = "retries", precision = 6, scale = 0)
		public Integer getRetries() {
			return this.retries;
		}

		public void setRetries(Integer retries) {
			this.retries = retries;
		}

}
