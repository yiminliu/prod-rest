package com.bedrosians.bedlogic.domain.item;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bedrosians.bedlogic.domain.item.enums.OriginCountry;

@Entity
@Table(name = "ims_icon", schema = "public")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="iconCollection")
public class IconCollection implements java.io.Serializable {
  
	private static final long serialVersionUID = -1113582221787L;
	
	private Integer iconId;
	private OriginCountry madeInCountry;
    //The should be either "true" of "false when insert or update the following properties
	private Boolean exteriorProduct;
	private Boolean adaAccessibility;
	private Boolean throughColor;
	private Boolean colorBody;
	private Boolean inkJet;
	private Boolean glazed;
	private Boolean unglazed;
	private Boolean rectifiedEdge;
	private Boolean chiseledEdge;
	private Boolean versaillesPattern;// = false;
	private Boolean recycled;
	private Boolean postRecycled;
	private Boolean preRecycled;
	private Boolean leadPointIcon;// = false;
	private Boolean greenFriendlyIcon;// = false;
	private Boolean coefficientOfFriction;
	private Integer version;
	private Item item;
	
	public IconCollection() {
	}
	
	public IconCollection(Integer iconId) {
		this.iconId = iconId;
	}

	public IconCollection(Integer iconId, OriginCountry madeInCountry,
			Boolean exteriorProduct, Boolean adaAccessibility,
			Boolean throughColor, Boolean colorBody, Boolean inkJet,
			Boolean glazed, Boolean unglazed, Boolean rectifiedEdge,
			Boolean chiseledEdge, Boolean versaillesPattern, Boolean recycled,
			Boolean postRecycled, Boolean preRecycled, Boolean leadPointIcon,
			Boolean greenFriendlyIcon, Boolean coefficientOfFriction,
			Integer version, Item item) {
		super();
		this.iconId = iconId;
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

	@JsonIgnore
	@Id
	@Column(name = "icon_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ims_icon_id_seq_gen")
	@SequenceGenerator(name="ims_icon_id_seq_gen", sequenceName="ims_icon_icon_id_seq")
	public Integer getIconId() {
		return this.iconId;
	}

	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="item_code")
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
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
	public Boolean getExteriorProduct() {
		return this.exteriorProduct;
	}

	public void setExteriorProduct(Boolean exteriorProduct) {
		this.exteriorProduct = exteriorProduct;
	}

	@Column(name = "ada_accessibility")
	public Boolean getAdaAccessibility() {
		return this.adaAccessibility;
	}

	public void setAdaAccessibility(Boolean adaAccessibility) {
		this.adaAccessibility = adaAccessibility;
	}

	@Column(name = "through_color")
	public Boolean getThroughColor() {
		return this.throughColor;
	}

	public void setThroughColor(Boolean throughColor) {
		this.throughColor = throughColor;
	}

	@Column(name = "color_body")
	public Boolean getColorBody() {
		return this.colorBody;
	}

	public void setColorBody(Boolean colorBody) {
		this.colorBody = colorBody;
	}

	@Column(name = "ink_jet")
	public Boolean getInkJet() {
		return this.inkJet;
	}

	public void setInkJet(Boolean inkJet) {
		this.inkJet = inkJet;
	}

	@Column(name = "glazed")
	public Boolean getGlazed() {
		return this.glazed;
	}

	public void setGlazed(Boolean glazed) {
		this.glazed = glazed;
	}

	@Column(name = "unglazed")
	public Boolean getUnglazed() {
		return this.unglazed;
	}

	public void setUnglazed(Boolean unglazed) {
		this.unglazed = unglazed;
	}

	@Column(name = "rectified_edge")
	public Boolean getRectifiedEdge() {
		return this.rectifiedEdge;
	}

	public void setRectifiedEdge(Boolean rectifiedEdge) {
		this.rectifiedEdge = rectifiedEdge;
	}

	@Column(name = "chiseled_edge")
	public Boolean getChiseledEdge() {
		return this.chiseledEdge;
	}

	public void setChiseledEdge(Boolean chiseledEdge) {
		this.chiseledEdge = chiseledEdge;
	}

	@Column(name = "versailles_pattern")
	public Boolean getVersaillesPattern() {
		return this.versaillesPattern;
	}

	public void setVersaillesPattern(Boolean versaillesPattern) {
		this.versaillesPattern = versaillesPattern;
	}

	@Column(name = "recycled")
	public Boolean getRecycled() {
		return this.recycled;
	}

	public void setRecycled(Boolean recycled) {
		this.recycled = recycled;
	}

	@Column(name = "post_recycled")
	public Boolean getPostRecycled() {
		return this.postRecycled;
	}

	public void setPostRecycled(Boolean postRecycled) {
		this.postRecycled = postRecycled;
	}

	@Column(name = "pre_recycled")
	public Boolean getPreRecycled() {
		return this.preRecycled;
	}

	public void setPreRecycled(Boolean preRecycled) {
		this.preRecycled = preRecycled;
	}

	@Column(name = "lead_point")
	public Boolean getLeadPointIcon() {
		return this.leadPointIcon;
	}

	public void setLeadPointIcon(Boolean leadPointIcon) {
		this.leadPointIcon = leadPointIcon;
	}

	@Column(name = "green_friendly")
	public Boolean getGreenFriendlyIcon() {
		return this.greenFriendlyIcon;
	}

	public void setGreenFriendlyIcon(Boolean greenFriendlyIcon) {
		this.greenFriendlyIcon = greenFriendlyIcon;
	}

	@Column(name = "coefficient_of_friction")
	public Boolean getCoefficientOfFriction() {
		return this.coefficientOfFriction;
	}

	public void setCoefficientOfFriction(Boolean coefficientOfFriction) {
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
	
    @Transient
    public String toLegancyIncons(){
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
    	legacyIcons[1] = (exteriorProduct != null && exteriorProduct == true) ? 'Y' : 'N'; 
    	legacyIcons[2] = OriginCountry.USA.equals(madeInCountry) ? 'Y' : 'N'; 
    	legacyIcons[3] = (adaAccessibility != null && adaAccessibility == true) ? 'Y' : 'N'; 
    	legacyIcons[4] = (throughColor != null && throughColor == true) ? 'Y' : 'N'; 
    	legacyIcons[5] = (inkJet != null && inkJet == true) ? 'Y' : 'N'; 
    	legacyIcons[6] = (recycled != null && recycled == true) ? 'Y' : 'N'; 
    	legacyIcons[7] = (colorBody != null && colorBody == true) ? 'Y' : 'N'; 
    	legacyIcons[8] = (glazed != null && glazed == true) ? 'Y' : 'N'; 
    	legacyIcons[9] = (rectifiedEdge != null && rectifiedEdge == true) ? 'Y' : 'N'; 
    	legacyIcons[10] = (unglazed != null && unglazed == true) ? 'Y' : 'N'; 
    	legacyIcons[11] = (postRecycled != null && postRecycled == true) ? 'Y' : 'N'; 
    	legacyIcons[12] = (preRecycled != null && preRecycled == true) ? 'Y' : 'N'; 
    	legacyIcons[13] = OriginCountry.China.equals(madeInCountry) ? 'Y' : 'N'; 
    	legacyIcons[14] = OriginCountry.Turkey.equals(madeInCountry) ? 'Y' : 'N'; 
    	legacyIcons[15] = OriginCountry.Mexico.equals(madeInCountry) ? 'Y' : 'N'; 
    	legacyIcons[16] = (coefficientOfFriction != null && coefficientOfFriction == true) ? 'Y' : 'N'; 
    	legacyIcons[17] = (chiseledEdge != null && chiseledEdge == true) ? 'Y' : 'N'; 
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
	
}
