package com.bedrosians.bedlogic.domain.item;

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
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ims_item_color_hue", schema = "public")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="colorHue")
public class ColorHue implements java.io.Serializable {

	private static final long serialVersionUID = -7547545887L;
	
	private Integer id;
	private String colorHue;
	private Item item;
	private Integer version;
	
	public ColorHue() {
	}

	public ColorHue(Integer id) {
		this.id = id;
	}
	
	public ColorHue(String colorHue) {
		this.colorHue = colorHue;
	}

	public ColorHue(String colorHue, Item item) {
		super();
		this.colorHue = colorHue;
		this.item = item;
	}

	
	public ColorHue(Integer id, String colorHue, Item item) {
		super();
		this.id = id;
		this.colorHue = colorHue;
		this.item = item;
	}

	@JsonIgnore
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ims_item_color_hue_id_seq_gen")
	@SequenceGenerator(name="ims_item_color_hue_id_seq_gen", sequenceName="ims_item_color_hue_id_seq")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_code")
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "color_hue", length = 120)
	public String getColorHue() {
		return this.colorHue;
	}

	public void setColorHue(String colorHue) {
		this.colorHue = colorHue;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((colorHue == null) ? 0 : colorHue.hashCode());
		result = prime * result + ((item == null) ? 0 : item.getItemcode().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ColorHue))
			return false;
		ColorHue other = (ColorHue) obj;
		if (colorHue == null) {
			if (other.colorHue != null)
				return false;
		} else if (!colorHue.equals(other.colorHue))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.getItemcode().equals(other.item.getItemcode()))
			return false;
		return true;
	}

}
