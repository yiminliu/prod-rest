package com.bedrosians.bedlogic.domain.item;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "ims_icon", schema = "public")
public class IconDescription implements java.io.Serializable {
  
	private static final long serialVersionUID = -1113582221787L;
	
	private Long iconId;
	private String originCountry;
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
	
	public IconDescription() {
	}

	public IconDescription(Long iconId) {
		this.iconId = iconId;
	}

	@JsonIgnore
	@Id
	@Column(name = "icon_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ims_icon_id_seq_gen")
	@SequenceGenerator(name="ims_icon_id_seq_gen", sequenceName="ims_icon_icon_id_seq")
	public Long getIconId() {
		return this.iconId;
	}

	public void setIconId(Long iconId) {
		this.iconId = iconId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemcd")
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "origin_country", length = 20)
	public String getOriginCountry() {
		return this.originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((adaAccessibility == null) ? 0 : adaAccessibility.hashCode());
		result = prime * result
				+ ((chiseledEdge == null) ? 0 : chiseledEdge.hashCode());
		result = prime
				* result
				+ ((coefficientOfFriction == null) ? 0 : coefficientOfFriction
						.hashCode());
		result = prime * result
				+ ((colorBody == null) ? 0 : colorBody.hashCode());
		result = prime * result
				+ ((exteriorProduct == null) ? 0 : exteriorProduct.hashCode());
		result = prime * result + ((glazed == null) ? 0 : glazed.hashCode());
		result = prime * result
				+ ((greenFriendly == null) ? 0 : greenFriendly.hashCode());
		result = prime * result + ((iconId == null) ? 0 : iconId.hashCode());
		result = prime * result + ((inkJet == null) ? 0 : inkJet.hashCode());
		result = prime * result
				+ ((leadPoint == null) ? 0 : leadPoint.hashCode());
		result = prime * result
				+ ((originCountry == null) ? 0 : originCountry.hashCode());
		result = prime * result
				+ ((postRecycled == null) ? 0 : postRecycled.hashCode());
		result = prime * result
				+ ((preRecycled == null) ? 0 : preRecycled.hashCode());
		result = prime * result
				+ ((rectifiedEdge == null) ? 0 : rectifiedEdge.hashCode());
		result = prime * result
				+ ((recycled == null) ? 0 : recycled.hashCode());
		result = prime * result
				+ ((throughColor == null) ? 0 : throughColor.hashCode());
		result = prime * result
				+ ((unglazed == null) ? 0 : unglazed.hashCode());
		result = prime
				* result
				+ ((versaillesPattern == null) ? 0 : versaillesPattern
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IconDescription other = (IconDescription) obj;
		if (adaAccessibility == null) {
			if (other.adaAccessibility != null)
				return false;
		} else if (!adaAccessibility.equals(other.adaAccessibility))
			return false;
		if (chiseledEdge == null) {
			if (other.chiseledEdge != null)
				return false;
		} else if (!chiseledEdge.equals(other.chiseledEdge))
			return false;
		if (coefficientOfFriction == null) {
			if (other.coefficientOfFriction != null)
				return false;
		} else if (!coefficientOfFriction.equals(other.coefficientOfFriction))
			return false;
		if (colorBody == null) {
			if (other.colorBody != null)
				return false;
		} else if (!colorBody.equals(other.colorBody))
			return false;
		if (exteriorProduct == null) {
			if (other.exteriorProduct != null)
				return false;
		} else if (!exteriorProduct.equals(other.exteriorProduct))
			return false;
		if (glazed == null) {
			if (other.glazed != null)
				return false;
		} else if (!glazed.equals(other.glazed))
			return false;
		if (greenFriendly == null) {
			if (other.greenFriendly != null)
				return false;
		} else if (!greenFriendly.equals(other.greenFriendly))
			return false;
		if (iconId == null) {
			if (other.iconId != null)
				return false;
		} else if (!iconId.equals(other.iconId))
			return false;
		if (inkJet == null) {
			if (other.inkJet != null)
				return false;
		} else if (!inkJet.equals(other.inkJet))
			return false;
		if (leadPoint == null) {
			if (other.leadPoint != null)
				return false;
		} else if (!leadPoint.equals(other.leadPoint))
			return false;
		if (originCountry == null) {
			if (other.originCountry != null)
				return false;
		} else if (!originCountry.equals(other.originCountry))
			return false;
		if (postRecycled == null) {
			if (other.postRecycled != null)
				return false;
		} else if (!postRecycled.equals(other.postRecycled))
			return false;
		if (preRecycled == null) {
			if (other.preRecycled != null)
				return false;
		} else if (!preRecycled.equals(other.preRecycled))
			return false;
		if (rectifiedEdge == null) {
			if (other.rectifiedEdge != null)
				return false;
		} else if (!rectifiedEdge.equals(other.rectifiedEdge))
			return false;
		if (recycled == null) {
			if (other.recycled != null)
				return false;
		} else if (!recycled.equals(other.recycled))
			return false;
		if (throughColor == null) {
			if (other.throughColor != null)
				return false;
		} else if (!throughColor.equals(other.throughColor))
			return false;
		if (unglazed == null) {
			if (other.unglazed != null)
				return false;
		} else if (!unglazed.equals(other.unglazed))
			return false;
		if (versaillesPattern == null) {
			if (other.versaillesPattern != null)
				return false;
		} else if (!versaillesPattern.equals(other.versaillesPattern))
			return false;
		return true;
	}
	
}
