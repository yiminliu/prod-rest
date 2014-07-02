package com.bedrosians.bedlogic.domain.account.embeddable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
public class Statement{
	
	private Integer statementNo;
	private String statementType;  
	private String invoiceType;
	private Date statementDate;
	private Float statementAmount;    
	private String paperStatementMailingFrequency;
	private String emailStatementMailingFrequency;
	private Float lastStatementAmount;
	private Date lastStatementDate;
	
	@Column(name="stmtnbr")
	public Integer getStatementNo() {
		return statementNo;
	}
	public void setStatementNo(Integer statementNo) {
		this.statementNo = statementNo;
	}
	
	@Column(name="stmttype")
	public String getStatementType() {
		return statementType;
	}
	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}
	
	@Column(name="invtype")
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="stmtdate")
	public Date getStatementDate() {
		return statementDate;
	}
	public void setStatementDate(Date statementDate) {
		this.statementDate = statementDate;
	}
	
	@Column(name="stmtamt")
	public Float getStatementAmount() {
		return statementAmount;
	}
	public void setStatementAmount(Float statementAmount) {
		this.statementAmount = statementAmount;
	}
	
	@Column(name="StmtFreq_M")
	public String getPaperStatementMailingFrequency() {
		return paperStatementMailingFrequency;
	}
	public void setPaperStatementMailingFrequency(
			String paperStatementMailingFrequency) {
		this.paperStatementMailingFrequency = paperStatementMailingFrequency;
	}
		
	@Column(name="StmtFreq_E")
	public String getEmailStatementMailingFrequency() {
		return emailStatementMailingFrequency;
	}
	public void setEmailStatementMailingFrequency(
			String emailStatementMailingFrequency) {
		this.emailStatementMailingFrequency = emailStatementMailingFrequency;
	}
	
	@Column(name="laststmtamt")
	public Float getLastStatementAmount() {
		return lastStatementAmount;
	}
	public void setLastStatementAmount(Float lastStatementAmount) {
		this.lastStatementAmount = lastStatementAmount;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="laststmtdate")
	public Date getLastStatementDate() {
		return lastStatementDate;
	}
	public void setLastStatementDate(Date lastStatementDate) {
		this.lastStatementDate = lastStatementDate;
	}
	
	
	
}
