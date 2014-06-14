package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Notes implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private String ponotes;
	private String buyernotes;
	private String invoicenotes;
	private String internalnotes;

	public Notes(){}
	
	@Column(name = "po_notes", length = 120)
	public String getPonotes() {
		return this.ponotes;
	}

	public void setPonotes(String ponotes) {
		this.ponotes = ponotes;
	}
	
	@Column(name = "notes1", length = 35)
	public String getBuyernotes() {
		return this.buyernotes;
	}

	public void setBuyernotes(String notes1) {
		this.buyernotes = notes1;
	}

	@Column(name = "notes2", length = 35)
	public String getInternalnotes() {
		return this.internalnotes;
	}

	public void setInternalnotes(String notes2) {
		this.internalnotes = notes2;
	}

	@Column(name = "notes3", length = 120)
	public String getInvoicenotes() {
		return this.invoicenotes;
	}

	public void setInvoicenotes(String notes3) {
		this.invoicenotes = notes3;
	}
	
}
