package com.bedrosians.bedlogic.domain.item.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Test {

	private Float waterAbsorption;
	private Float scratchResistance;
	private Character frostResistance;
	private Character chemicalResistance;
	private Float peiAbrasion;
	private Float scofWet;
	private Float scofDry;
	private Integer breakingStrength;
    private Character restricted;
	private Character warpage;
	private Character wedging;
	private Float dcof;
	private Character thermalShock;
	private String bondStrength;
	private Character greenfriendly;
	private Float moh;
	private String leadPoint;
	private Float preConsummer;
	private Float posConsummer;
	
	@Column(name = "water_absorption", precision = 4)
	public Float getWaterAbsorption() {
		return FormatUtil.process(this.waterAbsorption);
	}

	public void setWaterAbsorption(Float waterAbsorption) {
		this.waterAbsorption = waterAbsorption;
	}

	@Column(name = "scratch_resistance", precision = 5)
	public Float getScratchResistance() {
		return FormatUtil.process(this.scratchResistance);
	}

	public void setScratchResistance(Float scratchResistance) {
		this.scratchResistance = scratchResistance;
	}

	@Column(name = "frost_resistance", length = 1)
	public Character getFrostResistance() {
		return FormatUtil.process(this.frostResistance);
	}

	public void setFrostResistance(Character frostResistance) {
		this.frostResistance = frostResistance;
	}

	@Column(name = "chemical_resistance", length = 1)
	public Character getChemicalResistance() {
		return FormatUtil.process(this.chemicalResistance);
	}

	public void setChemicalResistance(Character chemicalResistance) {
		this.chemicalResistance = chemicalResistance;
	}

	@Column(name = "pei_abrasion", precision = 4, scale = 1)
	public Float getPeiAbrasion() {
		return FormatUtil.process(this.peiAbrasion);
	}
	
	@Column(name = "moh", precision = 5)
	public Float getMoh() {
		return FormatUtil.process(this.moh);
	}

	public void setMoh(Float moh) {
		this.moh = moh;
	}

	public void setPeiAbrasion(Float peiAbrasion) {
		this.peiAbrasion = peiAbrasion;
	}

	@Column(name = "scof_wet", precision = 4)
	public Float getScofWet() {
		return FormatUtil.process(this.scofWet);
	}

	public void setScofWet(Float scofWet) {
		this.scofWet = scofWet;
	}

	@Column(name = "scof_dry", precision = 4)
	public Float getScofDry() {
		return FormatUtil.process(this.scofDry);
	}

	public void setScofDry(Float scofDry) {
		this.scofDry = scofDry;
	}

	@Column(name = "breaking_strength", precision = 5, scale = 0)
	public Integer getBreakingStrength() {
		return FormatUtil.process(this.breakingStrength);
	}

	public void setBreakingStrength(Integer breakingStrength) {
		this.breakingStrength = breakingStrength;
	}

	@Column(name = "greenfriendly", length = 1)
	public Character getGreenfriendly() {
		return FormatUtil.process(this.greenfriendly);
	}

	public void setGreenfriendly(Character greenfriendly) {
		this.greenfriendly = greenfriendly;
	}
	
	@Column(name = "pre_consummer", precision = 5)
	public Float getPreConsummer() {
		return FormatUtil.process(this.preConsummer);
	}

	public void setPreConsummer(Float preConsummer) {
		this.preConsummer = preConsummer;
	}

	@Column(name = "pos_consummer", precision = 5)
	public Float getPosConsummer() {
		return FormatUtil.process(this.posConsummer);
	}

	public void setPosConsummer(Float posConsummer) {
		this.posConsummer = posConsummer;
	}

	@Column(name = "lead_point", length = 4)
	public String getLeadPoint() {
		return FormatUtil.process(this.leadPoint);
	}

	public void setLeadPoint(String leadPoint) {
		this.leadPoint = leadPoint;
	}
	
	@Column(name = "restricted", length = 1)
	public Character getRestricted() {
		return FormatUtil.process(this.restricted);
	}

	public void setRestricted(Character restricted) {
		this.restricted = restricted;
	}

	@Column(name = "warpage", length = 1)
	public Character getWarpage() {
		return FormatUtil.process(this.warpage);
	}

	public void setWarpage(Character warpage) {
		this.warpage = warpage;
	}

	@Column(name = "wedging", length = 1)
	public Character getWedging() {
		return FormatUtil.process(this.wedging);
	}

	public void setWedging(Character wedging) {
		this.wedging = wedging;
	}

	@Column(name = "dcof", precision = 4, scale = 4)
	public Float getDcof() {
		return FormatUtil.process(this.dcof);
	}

	public void setDcof(Float dcof) {
		this.dcof = dcof;
	}

	@Column(name = "thermal_shock", length = 1)
	public Character getThermalShock() {
		return FormatUtil.process(this.thermalShock);
	}

	public void setThermalShock(Character thermalShock) {
		this.thermalShock = thermalShock;
	}

	@Column(name = "bond_strength", length = 6)
	public String getBondStrength() {
		return FormatUtil.process(this.bondStrength);
	}

	public void setBondStrength(String bondStrength) {
		this.bondStrength = bondStrength;
	}
	
}
