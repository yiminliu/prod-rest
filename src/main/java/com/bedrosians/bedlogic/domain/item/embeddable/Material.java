package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Material implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private String materialtype;
	private String materialcategory;
	private String materialclass;
	private String materialstyle;
	private String materialfeature;
		
	@Column(name = "mattype", length = 24)
	public String getMaterialtype() {
		return this.materialtype;
	}

	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}
	
	@Column(name = "materialclass_cd", length = 5)
	public String getMaterialclass() {
		return this.materialclass;
	}

	public void setMaterialclass(String materialclass) {
		this.materialclass = materialclass;
	}

	@Column(name = "matcategory", length = 10)
	public String getMaterialcategory() {
		return this.materialcategory;
	}

	public void setMaterialcategory(String materialcategory) {
		this.materialcategory = materialcategory;
	}

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
