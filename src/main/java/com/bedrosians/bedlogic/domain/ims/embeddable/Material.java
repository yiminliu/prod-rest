package com.bedrosians.bedlogic.domain.ims.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

@Embeddable
public class Material implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private String materialtype;
	private String materialcategory;
	private String materialclass;
	private String materialstyle;
	private String materialfeature;

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(2.0f)
	@Column(name = "mattype", length = 24)
	public String getMaterialtype() {
		return this.materialtype;
	}

	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}
	
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(1.5f)
	@Column(name = "materialclass_cd", length = 5)
	public String getMaterialclass() {
		return this.materialclass;
	}

	public void setMaterialclass(String materialclass) {
		this.materialclass = materialclass;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(2.0f)
	@Column(name = "matcategory", length = 10)
	public String getMaterialcategory() {
		return this.materialcategory;
	}

	public void setMaterialcategory(String materialcategory) {
		this.materialcategory = materialcategory;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(2.0f)
	@Column(name = "matstyle", length = 7)
	public String getMaterialstyle() {
		return this.materialstyle;
	}

	public void setMaterialstyle(String materialstyle) {
		this.materialstyle = materialstyle;
	}

	@Column(name = "mfeature", length = 15)
	public String getMaterialfeature() {
		return this.materialfeature;
	}

	public void setMaterialfeature(String materialfeature) {
		this.materialfeature = materialfeature;
	}
}
