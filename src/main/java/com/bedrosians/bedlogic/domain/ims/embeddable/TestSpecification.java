package com.bedrosians.bedlogic.domain.ims.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TestSpecification  implements java.io.Serializable {

	private static final long serialVersionUID = -8732135821787L;
	
	private Float waterabsorption;
	private Float scratchresistance;
	private String frostresistance;
	private String chemicalresistance;
	private Float peiabrasion;
	private Float scofwet;
	private Float scofdry;
	private Integer breakingstrength;
	private String scratchstandard;
	private String breakingstandard;
    private Character restricted;
	private Character warpage;
	private Character wedging;
	private Float dcof;
	private Character thermalshock;
	private String bondstrength;
	private Character greenfriendly;
	private Float moh;
	private String leadpoint;
	private Float preconsummer;
	private Float posconsummer;
	
	public TestSpecification(){}
	
	@Column(name = "water_absorption", precision = 4)
	public Float getWaterabsorption() {
		return this.waterabsorption;
	}

	public void setWaterabsorption(Float waterabsorption) {
		this.waterabsorption = waterabsorption;
	}

	@Column(name = "scratch_resistance", precision = 5)
	public Float getScratchresistance() {
		return this.scratchresistance;
	}

	public void setScratchresistance(Float scratchresistance) {
		this.scratchresistance = scratchresistance;
	}

	@Column(name = "frost_resistance", length = 1)
	public String getFrostresistance() {
	   return this.frostresistance;
	}

	public void setFrostresistance(String frostresistance) {
		this.frostresistance = frostresistance;
	}

	@Column(name = "chemical_resistance", length = 1)
	public String getChemicalresistance() {
	   return chemicalresistance;
	}

	public void setChemicalresistance(String chemicalresistance) {
		this.chemicalresistance = chemicalresistance;
	}

	@Column(name = "pei_abrasion", precision = 4, scale = 1)
	public Float getPeiabrasion() {
		return this.peiabrasion;
	}
	
	@Column(name = "moh", precision = 5)
	public Float getMoh() {
		return this.moh;
	}

	public void setMoh(Float moh) {
		this.moh = moh;
	}

	public void setPeiabrasion(Float peiabrasion) {
		this.peiabrasion = peiabrasion;
	}

	@Column(name = "scof_wet", precision = 4)
	public Float getScofwet() {
		return this.scofwet;
	}

	public void setScofwet(Float scofwet) {
		this.scofwet = scofwet;
	}

	@Column(name = "scof_dry", precision = 4)
	public Float getScofdry() {
		return this.scofdry;
	}

	public void setScofdry(Float scofdry) {
		this.scofdry = scofdry;
	}

	@Column(name = "breaking_strength", precision = 5, scale = 0)
	public Integer getBreakingstrength() {
		return this.breakingstrength;
	}

	public void setBreakingstrength(Integer breakingstrength) {
		this.breakingstrength = breakingstrength;
	}
	
	@Column(name = "sr_standard", length = 15)
	public String getScratchstandard() {
		return this.scratchstandard;
	}

	public void setScratchstandard(String scratchstandard) {
		this.scratchstandard = scratchstandard;
	}

	@Column(name = "bk_standard", length = 15)
	public String getBreakingstandard() {
		return this.breakingstandard;
	}

	public void setBreakingstandard(String breakingstandard) {
		this.breakingstandard = breakingstandard;
	}

	@Column(name = "greenfriendly", length = 1)
	public Character getGreenfriendly() {
		return this.greenfriendly;
	}

	public void setGreenfriendly(Character greenfriendly) {
		this.greenfriendly = greenfriendly;
	}
	
	@Column(name = "pre_consummer", precision = 5)
	public Float getPreconsummer() {
		return this.preconsummer;
	}

	public void setPreconsummer(Float preconsummer) {
		this.preconsummer = preconsummer;
	}

	@Column(name = "pos_consummer", precision = 5)
	public Float getPosconsummer() {
		return this.posconsummer;
	}

	public void setPosconsummer(Float posconsummer) {
		this.posconsummer = posconsummer;
	}

	@Column(name = "lead_point", length = 4)
	public String getLeadpoint() {
		return this.leadpoint;
	}

	public void setLeadpoint(String leadpoint) {
		this.leadpoint = leadpoint;
	}
	
	@Column(name = "restricted", length = 1)
	public Character getRestricted() {
		return this.restricted;
	}

	public void setRestricted(Character restricted) {
		this.restricted = restricted;
	}

	@Column(name = "warpage", length = 1)
	public Character getWarpage() {
		return this.warpage;
	}

	public void setWarpage(Character warpage) {
		this.warpage = warpage;
	}

	@Column(name = "wedging", length = 1)
	public Character getWedging() {
		return this.wedging;
	}

	public void setWedging(Character wedging) {
		this.wedging = wedging;
	}

	@Column(name = "dcof", precision = 4, scale = 4)
	public Float getDcof() {
		return this.dcof;
	}

	public void setDcof(Float dcof) {
		this.dcof = dcof;
	}

	@Column(name = "thermal_shock", length = 1)
	public Character getThermalshock() {
		return this.thermalshock;
	}

	public void setThermalshock(Character thermalshock) {
		this.thermalshock = thermalshock;
	}

	@Column(name = "bond_strength", length = 6)
	public String getBondstrength() {
		return this.bondstrength;
	}

	public void setBondstrength(String bondstrength) {
		this.bondstrength = bondstrength;
	}
	
}
