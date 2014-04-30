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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.bedrosians.bedlogic.domain.item.enums.OriginCountry;

@Entity
@Table(name = "ims_icon", schema = "public")
public class IconCollection implements java.io.Serializable {
  
	private static final long serialVersionUID = -1113582221787L;
	
	private Integer iconId;
	//private String  itemCode;
	private OriginCountry  madeInCountry;
	private Boolean exteriorProduct;
	private Boolean adaAccessibility;
	private Boolean throughColor;
	private Boolean colorBody;
	private Boolean inkJet;
	private Boolean glazed;
	private Boolean unglazed;
	private Boolean rectifiedEdge;
	private Boolean chiseledEdge;
	private Boolean versaillesPattern = false;
	private Boolean recycled;
	private Boolean postRecycled;
	private Boolean preRecycled;
	private Boolean leadPoint = false;
	private Boolean greenFriendly = false;
	private Boolean coefficientOfFriction;
	private Item item;
	
	public IconCollection() {
	}

	//public IconCollection(String itemCode) {
	//	this.itemCode = itemCode;
	//}	
		
	
	public IconCollection(Integer iconId) {
		this.iconId = iconId;
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

	/*
	@JsonIgnore
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "item"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "item_code", unique = true, nullable = false, length = 15)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	*/
	
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
	public Boolean getLeadPoint() {
		return this.leadPoint;
	}

	public void setLeadPoint(Boolean leadPoint) {
		this.leadPoint = leadPoint;
	}

	@Column(name = "green_friendly")
	public Boolean getGreenFriendly() {
		return this.greenFriendly;
	}

	public void setGreenFriendly(Boolean greenFriendly) {
		this.greenFriendly = greenFriendly;
	}

	@Column(name = "coefficient_of_friction")
	public Boolean getCoefficientOfFriction() {
		return this.coefficientOfFriction;
	}

	public void setCoefficientOfFriction(Boolean coefficientOfFriction) {
		this.coefficientOfFriction = coefficientOfFriction;
	}
	
	@Transient
	static public List<String> allPropertis(){
		return Arrays.asList("originCountry", "exteriorProduct", "adaAccessibility", "throughColor", "colorBody", "inkJet",
				             "glazed", "unglazed", "rectifiedEdge", "chiseledEdge", "versaillesPattern", "recycled", "postRecycled",
				             "preRecycled", "icon_leadPoint", "icon_greenFriendly", "coefficientOfFriction");
	}

	
}
