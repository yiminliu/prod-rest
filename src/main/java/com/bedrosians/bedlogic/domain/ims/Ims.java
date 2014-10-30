
package com.bedrosians.bedlogic.domain.ims;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyze;
//import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.bedrosians.bedlogic.domain.ims.embeddable.Applications;
import com.bedrosians.bedlogic.domain.ims.embeddable.Cost;
import com.bedrosians.bedlogic.domain.ims.embeddable.Description;
import com.bedrosians.bedlogic.domain.ims.embeddable.Dimensions;
import com.bedrosians.bedlogic.domain.ims.embeddable.Material;
import com.bedrosians.bedlogic.domain.ims.embeddable.Notes;
import com.bedrosians.bedlogic.domain.ims.embeddable.PackagingInfo;
import com.bedrosians.bedlogic.domain.ims.embeddable.Price;
import com.bedrosians.bedlogic.domain.ims.embeddable.Purchasers;
import com.bedrosians.bedlogic.domain.ims.embeddable.Series;
import com.bedrosians.bedlogic.domain.ims.embeddable.SimilarItemCode;
import com.bedrosians.bedlogic.domain.ims.embeddable.TestSpecification;
import com.bedrosians.bedlogic.domain.ims.embeddable.Units;
import com.bedrosians.bedlogic.domain.ims.embeddable.VendorInfo;
import com.bedrosians.bedlogic.util.ims.ImsDataUtil;


@JsonRootName(value = "ims")
@Component
@Entity
@Table(name = "ims", schema = "public")
@DynamicUpdate
@DynamicInsert
//@Indexed
//@Analyzer(impl = StandardAnalyzer.class)
//@AnalyzerDef(name ="colorCategoryAnalyzer", tokenizer = @org.hibernate.search.annotations.TokenizerDef(factory=LetterTokenizerFactory.class))
//@Cacheable
//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ims implements java.io.Serializable {

	private static final long serialVersionUID = -8321358221787L;
	
    //------Basic Info------//   		
	private String itemcode;
	private String itemcategory;
	private String countryorigin;
	private String inactivecode;
	private String shadevariation;
	private String colorcategory;
	//private List<String> colorhues =  new ArrayList<>();
	private String showonwebsite;
  	private String iconsystem;
	private String itemtypecode;
	private String abccode;
	private String itemcode2;
	private String inventoryitemcode;
	private String showonalysedwards;
	private String offshade;
	private String printlabel;
	private String taxclass;
	private String lottype;
	private String updatecode;
	private String directship;
	private Date dropdate;
	private String productline;
	private Integer itemgroupnbr;
	private Date priorlastupdated;	
	private Long version;
	
	//----- Embedded Components ---------//
	private Description itemdesc;
	private Series series;
	private Material material;
	private Dimensions dimensions;
	//the following default values aim to meet ims_check5 constrains defined in keymark ims table
	private Price price = new Price();
	private TestSpecification testSpecification;
	private Purchasers purchasers;
	private PackagingInfo packaginginfo = new PackagingInfo();
	private Notes notes;
	private Applications applications;
	private List<String> usage;
  	private Units units;
  	private Cost cost;
  	private SimilarItemCode relateditemcodes;
  	private VendorInfo vendors = new VendorInfo();
  	//private PriorVendor priorVendor;
  	
	//------- Associations --------//
  	private List<Vendor> newVendorSystem = new ArrayList<>();
  	private ImsNewFeature newFeature;
    private IconCollection iconDescription;
	private List<ColorHue> colorhues =  new ArrayList<>();
  	//private List<Note> newNoteSystem = new ArrayList<>();
     	
	@Id
	@DocumentId
	@Column(name = "itemcd", unique = true, nullable = false, length = 18)
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	
	@JsonIgnore
	@Version
	@Column(name="version")
	public Long getVersion() {
		return version;
	}
	private void setVersion(Long version) {
		this.version = version;
	}

	@Embedded
	@IndexedEmbedded
	public Description getItemdesc() {
		return itemdesc;
	}
    public void setItemdesc(Description itemdesc) {
		this.itemdesc = itemdesc;
	}

	@Embedded
    @IndexedEmbedded
	public Series getSeries() {
		return series;
	}
    public void setSeries(Series series) {
		this.series = series;
	}
	
	@Embedded
	@IndexedEmbedded
	public Dimensions getDimensions() {
		return dimensions;
	}
	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}
	
	@Embedded
	@IndexedEmbedded
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	@Embedded
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	
	@Embedded
	public Cost getCost() {
		return cost;
	}
	public void setCost(Cost cost) {
		this.cost = cost;
	}

	@Embedded	
	public Units getUnits() {
		return units;
	}
	public void setUnits(Units units) {
		this.units = units;
	}

	@Embedded
	public TestSpecification getTestSpecification() {
		return testSpecification;
	}
	public void setTestSpecification(TestSpecification testSpecification) {
		this.testSpecification = testSpecification;
	}

	@Embedded
	public Applications getApplications() {
		return applications;
	}
	public void setApplications(Applications applications) {
		this.applications = applications;
	}
    
	@Embedded
	public Purchasers getPurchasers() {
		return purchasers;
	}
	public void setPurchasers(Purchasers purchasers) {
		this.purchasers = purchasers;
	}
	
	@Embedded
	@IndexedEmbedded(depth=1, includePaths = { "vendornbr1" })
	public VendorInfo getVendors() {
		return vendors;
	}
	public void setVendors(VendorInfo vendors) {
		this.vendors = vendors;
	}
	
	@Embedded
	public Notes getNotes() {
		return notes;
	}
	public void setNotes(Notes notes) {
		this.notes = notes;
	}

	@Embedded
	public SimilarItemCode getRelateditemcodes() {
		return relateditemcodes;
	}
	public void setRelateditemcodes(SimilarItemCode relateditemcodes) {
		this.relateditemcodes = relateditemcodes;
	}
	
	@Transient
	public PackagingInfo getPackaginginfo() {
		return packaginginfo;
	}
	public void setPackaginginfo(PackagingInfo packaginginfo) {
		this.packaginginfo = packaginginfo;
	}
	
	@Transient
	public List<String> getUsage() {
			return this.usage;
	}
	public void setUsage(List<String> usage) {
		this.usage = usage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "priorlastupdated", length = 13)
	public Date getPriorlastupdated() {
		return this.priorlastupdated;
	}
	public void setPriorlastupdated(Date priorlastupdated) {
		this.priorlastupdated = priorlastupdated;
	}

	@Column(name = "abccd", length = 4)
    public String getAbccode() {
		return this.abccode;
	}
	public void setAbccode(String abccode) {
		this.abccode = abccode;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Column(name = "category", length = 8)
	@Boost(1.5f)
	public String getItemcategory() {
		return this.itemcategory;
	}
	public void setItemcategory(String itemcategory) {
		this.itemcategory = itemcategory;
	}

	@Column(name = "directship", length = 1)
	public String getDirectship() {
		return this.directship;
	}
	public void setDirectship(String directship) {
		this.directship = directship;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dropdate", length = 13)
	public Date getDropdate() {
		return this.dropdate;
	}
	public void setDropdate(Date dropdate) {
		this.dropdate = dropdate;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(2.0f)
	@Column(name = "inactivecd", length = 3)
	public String getInactivecode() {
		return this.inactivecode;
	}
	public void setInactivecode(String inactivecode) {
		this.inactivecode = inactivecode;
	}

	@Column(name = "itemgroupnbr", precision = 2, scale = 0)
	public Integer getItemgroupnbr() {
		return this.itemgroupnbr;
	}
	public void setItemgroupnbr(Integer itemgroupnbr) {
		this.itemgroupnbr = itemgroupnbr;
	}

	@Column(name = "itemtypecd", length = 1)
	public String getItemtypecode() {
		return this.itemtypecode;
	}
	public void setItemtypecode(String itemtypecode) {
		this.itemtypecode = itemtypecode;
	}

	@Column(name = "lottype", length = 1)
	public String getLottype() {
		return this.lottype;
	}
	public void setLottype(String lottype) {
		this.lottype = lottype;
	}

	@Column(name = "printlabel", length = 1)
	public String getPrintlabel() {
		return this.printlabel;
	}
	public void setPrintlabel(String printlabel) {
		this.printlabel = printlabel;
	}

	@Column(name = "productline", length = 8)
	public String getProductline() {
		return this.productline;
	}
	public void setProductline(String productline) {
		this.productline = productline;
	}

	@Column(name = "updatecd", length = 10)
	public String getUpdatecode() {
		return this.updatecode;
	}
	public void setUpdatecode(String updatecode) {
		this.updatecode = updatecode;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(1.8f)
	@Column(name = "origin", length = 18)
	public String getCountryorigin() {
		return this.countryorigin;
	}
	public void setCountryorigin(String countryorigin) {
		this.countryorigin = countryorigin;
	}

	@Column(name = "shadevariation", length = 2)
	public String getShadevariation() {
		return this.shadevariation;
	}
	public void setShadevariation(String shadevariation) {
		this.shadevariation = shadevariation;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(2.0f)
	@Column(name = "showonwebsite", length = 1)
	public String getShowonwebsite() {
		return this.showonwebsite;
	}
	public void setShowonwebsite(String showonwebsite) {
		this.showonwebsite = showonwebsite;
	}

	//@JsonIgnore
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	//@Analyzer(definition="colorCategoryAnalyzer")
	@Boost(2.0f)
    @Column(name = "colorcategory", length = 30)
	public String getColorcategory() {
		return this.colorcategory;
	}
	public void setColorcategory(String colorcategory) {
		this.colorcategory = colorcategory;
	}
	
	/*
	@Transient
	public List<String> getColorhues() {
		if(colorhues == null || colorhues.isEmpty())
		   colorhues = ImsDataUtil.convertColorCategoryToStringList(colorcategory);
		return colorhues;
	}	
	public void setColorhues(List<String> colorhues) {
		this.colorhues = colorhues;
	}
	*/
	
	@Column(name = "showonalysedwards", length = 1)
	public String getShowonalysedwards() {
		return this.showonalysedwards;
	}
	public void setShowonalysedwards(String showonalysedwards) {
		this.showonalysedwards = showonalysedwards;
	}

	@Column(name = "offshade", length = 1)
	public String getOffshade() {
		return this.offshade;
	}
	public void setOffshade(String offshade) {
		this.offshade = offshade;
	}

	@Column(name = "itemcd2", length = 18)
	public String getItemcode2() {
		return this.itemcode2;
	}
	public void setItemcode2(String itemcode2) {
		this.itemcode2 = itemcode2;
	}

	@Column(name = "icons", length = 20)
	public String getIconsystem() {
		return this.iconsystem;
	}
	public void setIconsystem(String iconsystem) {
		this.iconsystem = iconsystem;
	}

	@Column(name = "inventory_itemcd", length = 18)
	public String getInventoryitemcode() {
		return this.inventoryitemcode;
	}
	public void setInventoryitemcode(String inventoryitemcode) {
		this.inventoryitemcode = inventoryitemcode;
	}

	@Column(name = "itemtaxclass")
	public String getTaxclass() {
		return this.taxclass;
	}
	public void setTaxclass(String taxclass) {
		this.taxclass = taxclass;
	}
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "ims", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@IndexedEmbedded
	public ImsNewFeature getNewFeature() {	
	    return this.newFeature;
	}
	public void setNewFeature(ImsNewFeature newFeature) {
		this.newFeature = newFeature;
	}
	
	public void addNewFeature(ImsNewFeature newFeature ){
		if(this.getNewFeature() != null)
			setNewFeature(null);
		newFeature.setIms(this);
		this.newFeature = newFeature;
	}
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "ims", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public IconCollection getIconDescription() {	
	    return iconDescription;// != null//? iconCollection : ImsDataUtil.parseIcons(getIconsystem());
	}
	public void setIconDescription(IconCollection iconDescription) {
		this.iconDescription = iconDescription;
	}
	
	public void addIconDescription(IconCollection iconDescription){
		iconDescription.setIms(this);
		this.iconDescription = iconDescription;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ims", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@IndexedEmbedded(depth=2)
	public List<Vendor> getNewVendorSystem() {
		return this.newVendorSystem;
	}
	public void setNewVendorSystem(List<Vendor> newVendorSystem) {
		this.newVendorSystem = newVendorSystem;
	}

	public void addNewVendorSystem(Vendor vendor){
		if(vendor.getVendorId().getItemCode() == null)
		   vendor.getVendorId().setItemCode(this.getItemcode());	
		vendor.setIms(this);
		if(getNewVendorSystem() == null)
		   setNewVendorSystem(new ArrayList<Vendor>());	
		vendor.setVendorOrder(getNewVendorSystem().size() +1);
		getNewVendorSystem().add(vendor);
	}
	
	public void initVendors(int numberOfVendors){
		for(int i = 0; i < numberOfVendors; i++) {
			addNewVendorSystem(new Vendor());
		}
	}

	
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ims", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	public List<ColorHue> getColorhues() {
		return this.colorhues;
	}

	public void setColorhues(List<ColorHue> colorhues) {
		this.colorhues = colorhues;
	}
   
	public void addColorhue(ColorHue colorhue){
		colorhue.setIms(this);
		if(colorhues == null)
			colorhues = new ArrayList<ColorHue>();
		colorhues.add(colorhue);
	}
   
	/*
	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ims", cascade = CascadeType.ALL)
	public List<Note> getNewNoteSystem() {
		return this.newNoteSystem;
	}

	public void setNewNoteSystem(List<Note> newNoteSystem) {
		this.newNoteSystem = newNoteSystem;
	}

	public void addNote(Note note){
		if(getNewNoteSystem() != null && getNewNoteSystem().size() < 5)
			initNotes(5);
		note.setItem(this);
		String noteType = note.getNoteType();
		switch(noteType){
		   case "po": case "po_note":
			   getNewNoteSystem().set(0, note);
			   break;
		   case "buyer": case "buyer_note":
	    	   getNewNoteSystem().set(1, note);
			   break;	
		   case "invoice": case "invoice_note":
			   getNewNoteSystem().set(2, note);
			   break;
		   case "internal": case "internal_note":
			   getNewNoteSystem().set(4, note);
			   break;	
		   case "additional": case "additional_note":
			   getNewNoteSystem().set(3, note);
			   break;	   
		}
	}

	public void initNotes(int numberOfNotes){
		newNoteSystem = Arrays.asList(new Note[numberOfNotes]);
		for(int i = 0; i < numberOfNotes; i++) {
			Note note = new Note();
			note.setItem(this);
			newNoteSystem.set(i, note);
		}
	}	
	*/
    @JsonIgnore
    @Transient
	public String getStandardSellUnit(){
    	return ImsDataUtil.getStandardSellUnit(this);
	}
	@JsonIgnore
	@Transient
	public String getStandardOrderUnit(){
		return ImsDataUtil.getStandardPurchaseUnit(this);
	}

	public Ims() {
	}

	public Ims(String itemcode) {
		this.itemcode = itemcode;
	}
	
	public Ims(String itemcode, String itemcategory, String countryorigin,
			String inactivecode, String shadevariation, String colorcategory,
			//List<String> colorhues, 
			String showonwebsite, String iconsystem,
			String itemtypecode, String abccode, String itemcode2,
			String inventoryitemcode, String showonalysedwards,
			String offshade, String printlabel, String taxclass,
			String lottype, String updatecode, String directship,
			Date dropdate, String productline, Integer itemgroupnumber,
			Date priorlastupdated, Long version, Description itemdesc,
			Series series, Material material, Dimensions dimensions,
			Price price, TestSpecification testSpecification,
			Purchasers purchasers, PackagingInfo packaginginfo, Notes notes,
			Applications applications, List<String> usage, Units units,
			Cost cost, VendorInfo vendors, SimilarItemCode relateditemcodes,
			List<Vendor> newVendorSystem, ImsNewFeature imsNewFeature,
			IconCollection iconDescription) {
		super();
		this.itemcode = itemcode;
		this.itemcategory = itemcategory;
		this.countryorigin = countryorigin;
		this.inactivecode = inactivecode;
		this.shadevariation = shadevariation;
		this.colorcategory = colorcategory;
		//this.colorhues = colorhues;
		this.showonwebsite = showonwebsite;
		this.iconsystem = iconsystem;
		this.itemtypecode = itemtypecode;
		this.abccode = abccode;
		this.itemcode2 = itemcode2;
		this.inventoryitemcode = inventoryitemcode;
		this.showonalysedwards = showonalysedwards;
		this.offshade = offshade;
		this.printlabel = printlabel;
		this.taxclass = taxclass;
		this.lottype = lottype;
		this.updatecode = updatecode;
		this.directship = directship;
		this.dropdate = dropdate;
		this.productline = productline;
		this.itemgroupnbr = itemgroupnumber;
		this.priorlastupdated = priorlastupdated;
		this.version = version;
		this.itemdesc = itemdesc;
		this.series = series;
		this.material = material;
		this.dimensions = dimensions;
		this.price = price;
		this.testSpecification = testSpecification;
		this.purchasers = purchasers;
		this.packaginginfo = packaginginfo;
		this.notes = notes;
		this.applications = applications;
		this.usage = usage;
		this.units = units;
		this.cost = cost;
		this.vendors = vendors;
		this.relateditemcodes = relateditemcodes;
		this.newVendorSystem = newVendorSystem;
		this.newFeature = newFeature;
		this.iconDescription = iconDescription;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemcode == null) ? 0 : itemcode.trim().hashCode());
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
		Ims other = (Ims) obj;
		if (itemcode == null) {
			if (other.itemcode != null)
				return false;
		} else if (!itemcode.trim().equalsIgnoreCase(other.itemcode.trim()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product "
				+ "[itemcd=" + itemcode 
				+ ", productNewFeature=" + newFeature 
				+ ", abccode=" + abccode 
				+ ", category=" + itemcategory 
				+ ", inactivecd=" + inactivecode
				+ ", itemgroupnbr=" + itemgroupnbr 
				+ ", itemtypecode=" + itemtypecode 
				+ ", printlabel=" + printlabel 
				+ ", productline=" + productline 
				+ ", updatecode=" + updatecode 
				+ ", origin=" + countryorigin
				+ ", shadevariation=" + shadevariation 
				+ ", showonwebsite=" + showonwebsite
				+ ", colorcategory=" + colorcategory 
				+ ", showonalysedwards=" + showonalysedwards 
				+ ", offshade=" + offshade 
				+ ", itemcd2=" + itemcode2 
				+ ", icons=" + iconsystem 
				+ ", inventoryItemcd=" + inventoryitemcode 
				+ "]";
	}

}
