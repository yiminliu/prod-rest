package com.bedrosians.bedlogic.domain.ims;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.ContainedIn;

import com.bedrosians.bedlogic.domain.ims.enums.OriginCountry;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ims_icon", schema = "public")
@DynamicUpdate
@DynamicInsert
//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class IconCollection implements java.io.Serializable {
  
	private static final long serialVersionUID = -1113582221787L;
	
	private String itemCode;
	private OriginCountry madeInCountry;
    //The possible values should be either "Yes" of "No" when insert or update the following properties
	private String exteriorProduct = "No";
	private String adaAccessibility = "No";
	private String throughColor = "No";
	private String colorBody = "No";
	private String inkJet = "No";
	private String glazed = "No";
	private String unglazed = "No";
	private String rectifiedEdge = "No";
	private String chiseledEdge = "No";
	private String versaillesPattern = "No";
	private String recycled = "No";
	private String postRecycled = "No";
	private String preRecycled = "No";
	private String leadPointIcon = "No";
	private String greenFriendlyIcon = "No";
	private String coefficientOfFriction = "No";
	private Integer version;
	private Ims item;

	@JsonIgnore
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "item"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "item_code", unique = true, nullable = false)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_code")
	@ContainedIn
	public Ims getItem() {
		return this.item;
	}

	public void setItem(Ims item) {
		this.item = item;
	}
    
	@Column(name = "made_in_country", length = 20)
	@Enumerated(EnumType.STRING)
	public OriginCountry getMadeInCountry() {
		return this.madeInCountry;
	}

	public void setMadeInCountry(OriginCountry madeInCountry) {
		this.madeInCountry = madeInCountry;
	}

	@Column(name = "exterior_product")
	public String getExteriorProduct() {
		return this.exteriorProduct;
	}

	public void setExteriorProduct(String exteriorProduct) {
		this.exteriorProduct = exteriorProduct;
	}

	@Column(name = "ada_accessibility")
	public String getAdaAccessibility() {
		return this.adaAccessibility;
	}

	public void setAdaAccessibility(String adaAccessibility) {
		this.adaAccessibility = adaAccessibility;
	}

	@Column(name = "through_color")
	public String getThroughColor() {
		return this.throughColor;
	}

	public void setThroughColor(String throughColor) {
		this.throughColor = throughColor;
	}

	@Column(name = "color_body")
	public String getColorBody() {
		return this.colorBody;
	}

	public void setColorBody(String colorBody) {
		this.colorBody = colorBody;
	}

	@Column(name = "ink_jet")
	public String getInkJet() {
		return this.inkJet;
	}

	public void setInkJet(String inkJet) {
		this.inkJet = inkJet;
	}

	@Column(name = "glazed")
	public String getGlazed() {
		return this.glazed;
	}

	public void setGlazed(String glazed) {
		this.glazed = glazed;
	}

	@Column(name = "unglazed")
	public String getUnglazed() {
		return this.unglazed;
	}

	public void setUnglazed(String unglazed) {
		this.unglazed = unglazed;
	}

	@Column(name = "rectified_edge")
	public String getRectifiedEdge() {
		return this.rectifiedEdge;
	}

	public void setRectifiedEdge(String rectifiedEdge) {
		this.rectifiedEdge = rectifiedEdge;
	}

	@Column(name = "chiseled_edge")
	public String getChiseledEdge() {
		return this.chiseledEdge;
	}

	public void setChiseledEdge(String chiseledEdge) {
		this.chiseledEdge = chiseledEdge;
	}

	@Column(name = "versailles_pattern")
	public String getVersaillesPattern() {
		return this.versaillesPattern;
	}

	public void setVersaillesPattern(String versaillesPattern) {
		this.versaillesPattern = versaillesPattern;
	}

	@Column(name = "recycled")
	public String getRecycled() {
		return this.recycled;
	}

	public void setRecycled(String recycled) {
		this.recycled = recycled;
	}

	@Column(name = "post_recycled")
	public String getPostRecycled() {
		return this.postRecycled;
	}

	public void setPostRecycled(String postRecycled) {
		this.postRecycled = postRecycled;
	}

	@Column(name = "pre_recycled")
	public String getPreRecycled() {
		return this.preRecycled;
	}

	public void setPreRecycled(String preRecycled) {
		this.preRecycled = preRecycled;
	}

	@Column(name = "lead_point")
	public String getLeadPointIcon() {
		return this.leadPointIcon;
	}

	public void setLeadPointIcon(String leadPointIcon) {
		this.leadPointIcon = leadPointIcon;
	}

	@Column(name = "green_friendly")
	public String getGreenFriendlyIcon() {
		return this.greenFriendlyIcon;
	}

	public void setGreenFriendlyIcon(String greenFriendlyIcon) {
		this.greenFriendlyIcon = greenFriendlyIcon;
	}

	@Column(name = "coefficient_of_friction")
	public String getCoefficientOfFriction() {
		return this.coefficientOfFriction;
	}

	public void setCoefficientOfFriction(String coefficientOfFriction) {
		this.coefficientOfFriction = coefficientOfFriction;
	}
	
	@JsonIgnore
    @Version
    @Column(name = "version")
    public Integer gerVersion(){
    	return version;
    }
	
    private void setVersion(Integer version){
		this.version = version;
	}
	
    public IconCollection() {
	}
	
	public IconCollection(String itemCode) {
		this.itemCode = itemCode;
	}

	public IconCollection(String itemCode, OriginCountry madeInCountry,
			String exteriorProduct, String adaAccessibility,
			String throughColor, String colorBody, String inkJet,
			String glazed, String unglazed, String rectifiedEdge,
			String chiseledEdge, String versaillesPattern, String recycled,
			String postRecycled, String preRecycled, String leadPointIcon,
			String greenFriendlyIcon, String coefficientOfFriction,
			Integer version, Ims ims) {
		super();
		this.itemCode = itemCode;
		this.madeInCountry = madeInCountry;
		this.exteriorProduct = exteriorProduct;
		this.adaAccessibility = adaAccessibility;
		this.throughColor = throughColor;
		this.colorBody = colorBody;
		this.inkJet = inkJet;
		this.glazed = glazed;
		this.unglazed = unglazed;
		this.rectifiedEdge = rectifiedEdge;
		this.chiseledEdge = chiseledEdge;
		this.versaillesPattern = versaillesPattern;
		this.recycled = recycled;
		this.postRecycled = postRecycled;
		this.preRecycled = preRecycled;
		this.leadPointIcon = leadPointIcon;
		this.greenFriendlyIcon = greenFriendlyIcon;
		this.coefficientOfFriction = coefficientOfFriction;
		this.version = version;
		this.item = item;
	}
	
    @Transient
    public String toLegancyIcons(){
    	char[] legacyIcons = new char[20];
    	
    	/*
		* Stored as 20 character string
		* 0 - Made in Italy
		* 1 - Outdoor
		* 2 - Made in USA
		* 3 - ADA
		* 4 - Thru Color
		* 5 - Inkjet
		* 6 - Recycled
		* 7 - Color Body
		* 8 - Glazed
		* 9 - Rectified
		* 10 - Unglazed
		* 11 - Post Recycled
		* 12 - Pre Recycled
		* 13 - Made in China
		* 14 - Made in Turkey
		* 15 - Made in Mexico
		* 16 - Coefficient of Friction
		* 17 - Chiseled Edge
		* 18 - Unused
		* 19 - Unused
		*/
    	legacyIcons[0] = OriginCountry.Italy.equals(madeInCountry) ? 'Y' : 'N'; 
    	legacyIcons[1] = (exteriorProduct != null && exteriorProduct.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[2] = OriginCountry.USA.equals(madeInCountry) ? 'Y' : 'N'; 
    	legacyIcons[3] = (adaAccessibility != null && adaAccessibility.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[4] = (throughColor != null && throughColor.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[5] = (inkJet != null && inkJet.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[6] = (recycled != null && recycled.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[7] = (colorBody != null && colorBody.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[8] = (glazed != null && glazed.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[9] = (rectifiedEdge != null && rectifiedEdge.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[10] = (unglazed != null && unglazed.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[11] = (postRecycled != null && postRecycled.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[12] = (preRecycled != null && preRecycled.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[13] = OriginCountry.China.equals(madeInCountry) ? 'Y' : 'N'; 
    	legacyIcons[14] = OriginCountry.Turkey.equals(madeInCountry) ? 'Y' : 'N'; 
    	legacyIcons[15] = OriginCountry.Mexico.equals(madeInCountry) ? 'Y' : 'N'; 
    	legacyIcons[16] = (coefficientOfFriction != null && coefficientOfFriction.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[17] = (chiseledEdge != null && chiseledEdge.equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[18] = 'N'; 
    	legacyIcons[19] = 'N'; 
    	    
    	return new String(legacyIcons);
    }
    
	@Transient
	static public List<String> allPropertis(){
		return Arrays.asList("madeInCountry", "exteriorProduct", "adaAccessibility", "throughColor", "colorBody", "inkJet",
				             "glazed", "unglazed", "rectifiedEdge", "chiseledEdge", "versaillesPattern", "recycled", "postRecycled",
				             "preRecycled", "leadPointIcon", "greenFriendlyIcon", "coefficientOfFriction");
	}

	@JsonIgnore
	@Transient
	public boolean isEmpty(){
		return exteriorProduct == null && adaAccessibility == null && throughColor == null && colorBody == null && 
			   inkJet == null && glazed == null && unglazed == null && rectifiedEdge == null &&
			   chiseledEdge == null && versaillesPattern == null && recycled == null && postRecycled == null && 
			   preRecycled == null && leadPointIcon == null && greenFriendlyIcon == null && coefficientOfFriction == null;
	}
	
	@JsonIgnore
	@Transient
	public boolean isDefault(){
		return exteriorProduct.equals("No") && adaAccessibility.equals("No") && throughColor.equals("No") && colorBody.equals("No") && 
			   inkJet.equals("No") && glazed.equals("No") && unglazed.equals("No") && rectifiedEdge.equals("No") &&
			   chiseledEdge.equals("No") && versaillesPattern.equals("No") && recycled.equals("No") && postRecycled.equals("No") && 
			   preRecycled.equals("No") && leadPointIcon.equals("No") && greenFriendlyIcon.equals("No") && coefficientOfFriction.equals("No");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemCode == null) ? 0 : itemCode.trim().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IconCollection))
			return false;
		IconCollection other = (IconCollection) obj;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.trim().equals(other.itemCode.trim()))
			return false;
		return true;
	}

	
}
