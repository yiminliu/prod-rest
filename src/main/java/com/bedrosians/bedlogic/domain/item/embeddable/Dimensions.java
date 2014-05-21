package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Dimensions implements java.io.Serializable {

	private static final long serialVersionUID = -582221787L;
	
	private Float nominallength;
	private Float nominalwidth;
	private String sizeunits;
	private String thickness;
	private String thicknessunit;
	private String length;
	private String width;
	private Float nominalthickness;
	
	@Column(name = "nm_length", precision = 5)
	public Float getNominallength() {
		return this.nominallength;
	}

	public void setNominallength(Float nominallength) {
		this.nominallength = nominallength;
	}

	@Column(name = "nm_width", precision = 5)
	public Float getNominalwidth() {
		return this.nominalwidth;
	}

	public void setNominalwidth(Float nominalwidth) {
		this.nominalwidth = nominalwidth;
	}

	@Column(name = "nm_thickness", precision = 5)
	public Float getNominalthickness() {
		return this.nominalthickness;
	}

	public void setNominalthickness(Float nominalthickness) {
		this.nominalthickness = nominalthickness;
	}
	
	@Column(name = "thicknessunit", length = 3)
	public String getThicknessunit() {
		return this.thicknessunit;
	}

	public void setThicknessunit(String thicknessunit) {
		this.thicknessunit = thicknessunit;
	}
	
	@Column(name = "length", length = 12)
	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Column(name = "width", length = 12)
	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Column(name = "thickness", length = 12)
	public String getThickness() {
		return this.thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	@Column(name = "sizeunits", length = 3)
	public String getSizeunits() {
		return this.sizeunits;
	}

	public void setSizeunits(String sizeunits) {
		this.sizeunits = sizeunits;
	}
}
