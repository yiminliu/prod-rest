package com.bedrosians.bedlogic.domain.item.embeddable;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Notes implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private String ponotes;
	private String notes1;
	private String notes2;
	private String notes3;
	
	public Notes(){}
	
	@Column(name = "po_notes", length = 120)
	public String getPonotes() {
		return this.ponotes;
	}

	public void setPonotes(String ponotes) {
		this.ponotes = ponotes;
	}
	
	@Column(name = "notes1", length = 35)
	public String getNotes1() {
		return this.notes1;
	}

	public void setNotes1(String notes1) {
		this.notes1 = notes1;
	}

	@Column(name = "notes2", length = 35)
	public String getNotes2() {
		return this.notes2;
	}

	public void setNotes2(String notes2) {
		this.notes2 = notes2;
	}

	@Column(name = "notes3", length = 120)
	public String getNotes3() {
		return this.notes3;
	}

	public void setNotes3(String notes3) {
		this.notes3 = notes3;
	}
	
}
