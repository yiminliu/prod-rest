
package com.bedrosians.bedlogic.domain.item;

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

import org.apache.solr.analysis.LetterTokenizerFactory;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonRootName;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.springframework.stereotype.Component;

import com.bedrosians.bedlogic.domain.item.embeddable.Description;
import com.bedrosians.bedlogic.domain.item.embeddable.Dimensions;
import com.bedrosians.bedlogic.domain.item.embeddable.Material;
import com.bedrosians.bedlogic.domain.item.embeddable.Notes;
import com.bedrosians.bedlogic.domain.item.embeddable.PackagingInfo;
import com.bedrosians.bedlogic.domain.item.embeddable.Series;
import com.bedrosians.bedlogic.domain.item.embeddable.Applications;
import com.bedrosians.bedlogic.domain.item.embeddable.Cost;
import com.bedrosians.bedlogic.domain.item.embeddable.Price;
import com.bedrosians.bedlogic.domain.item.embeddable.PriorVendor;
import com.bedrosians.bedlogic.domain.item.embeddable.Purchasers;
import com.bedrosians.bedlogic.domain.item.embeddable.SimilarItemCode;
import com.bedrosians.bedlogic.domain.item.embeddable.TestSpecification;
import com.bedrosians.bedlogic.domain.item.embeddable.Units;
import com.bedrosians.bedlogic.domain.item.embeddable.VendorInfo;
import com.bedrosians.bedlogic.util.ImsDataUtil;


@JsonRootName(value = "item")
@Component
@Entity
@Table(name = "ims", schema = "public")
@DynamicUpdate
@DynamicInsert
@Indexed
@AnalyzerDef(name ="colorCategoryAnalyzer", tokenizer = @org.hibernate.search.annotations.TokenizerDef(factory=LetterTokenizerFactory.class))
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Item implements java.io.Serializable {

	private static final long serialVersionUID = -8321358221787L;
	
    //------Basic Info------//   		
	private String itemcode;
	private String itemcategory;
	private String countryorigin;
	private String inactivecode;
	private String shadevariation;
	private String colorcategory;
	private List<String> colorhues =  new ArrayList<>();
	private Character showonwebsite;
  	private String iconsystem;
	private Character itemtypecd;
	private String abccd;
	private String itemcd2;
	private String inventoryitemcd;
	private Character showonalysedwards;
	private Character offshade;
	private Character printlabel;
	private Character taxclass;
	private String lottype;
	private String updatecd;
	private Character directship;
	private Date dropdate;
	private String productline;
	private Integer itemgroupnbr;
	private Date priorlastupdated;	
	
	//----- Embedded Components ---------//
	private Description itemdesc;
	private Series series;
	private Material material;
	private Dimensions dimensions;
	//this default aims to meet ims_check5
	private Price price = new Price();
	private TestSpecification testSpecification;
 	private SimilarItemCode relateditemcodes;
	private Purchasers purchasers;
	private PackagingInfo packaginginfo = new PackagingInfo();
	private Notes notes;
	private Applications applications;
	private List<String> usage;
  	private Units units;
  	private Cost cost;
  	private VendorInfo vendors = new VendorInfo();
  	//private PriorVendor priorVendor;
  	
	//------- Associations --------//
  	private List<ItemVendor> newVendorSystem = new ArrayList<>();
  	private ItemNewFeature itemNewFeature;
    private IconCollection iconDescription;
	//private List<ColorHue> colorhues =  new ArrayList<>();
  	//private List<Note> newNoteSystem = new ArrayList<>();
     	
  	public Item() {
	}

	public Item(String itemcode) {
		this.itemcode = itemcode;
	}
	
	@Id
	@DocumentId
	@Column(name = "itemcd", unique = true, nullable = false, length = 18)
	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
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
	@IndexedEmbedded
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
    public String getAbccd() {
		return this.abccd;
	}

	public void setAbccd(String abccd) {
		this.abccd = abccd;
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
	public Character getDirectship() {
		return this.directship;
	}

	public void setDirectship(Character directship) {
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
	public Character getItemtypecd() {
		return this.itemtypecd;
	}
	
	public void setItemtypecd(Character itemtypecd) {
		this.itemtypecd = itemtypecd;
	}

	@Column(name = "lottype", length = 1)
	public String getLottype() {
		return this.lottype;
	}

	public void setLottype(String lottype) {
		this.lottype = lottype;
	}

	@Column(name = "printlabel", length = 1)
	public Character getPrintlabel() {
		return this.printlabel;
	}

	public void setPrintlabel(Character printlabel) {
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
	public String getUpdatecd() {
		return this.updatecd;
	}

	public void setUpdatecd(String updatecd) {
		this.updatecd = updatecd;
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
	public Character getShowonwebsite() {
		return this.showonwebsite;
	}

	public void setShowonwebsite(Character showonwebsite) {
		this.showonwebsite = showonwebsite;
	}

	//@JsonIgnore
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	@Analyzer(definition="colorCategoryAnalyzer")
	@Boost(2.0f)
    @Column(name = "colorcategory", length = 30)
	public String getColorcategory() {
		return this.colorcategory;
	}

	public void setColorcategory(String colorcategory) {
		this.colorcategory = colorcategory;
	}
	
	@Transient
	public List<String> getColorhues() {
		if(colorhues == null || colorhues.isEmpty())
		   colorhues = ImsDataUtil.convertColorCategoryToStringList(colorcategory);
		return colorhues;
	}
		
	public void setColorhues(List<String> colorhues) {
		this.colorhues = colorhues;
	}
	
	@Column(name = "showonalysedwards", length = 1)
	public Character getShowonalysedwards() {
		return this.showonalysedwards;
	}

	public void setShowonalysedwards(Character showonalysedwards) {
		this.showonalysedwards = showonalysedwards;
	}

	@Column(name = "offshade", length = 1)
	public Character getOffshade() {
		return this.offshade;
	}

	public void setOffshade(Character offshade) {
		this.offshade = offshade;
	}

	@Column(name = "itemcd2", length = 18)
	public String getItemcd2() {
		return this.itemcd2;
	}

	public void setItemcd2(String itemcd2) {
		this.itemcd2 = itemcd2;
	}

	@Column(name = "icons", length = 20)
	public String getIconsystem() {
		return this.iconsystem;
	}

	public void setIconsystem(String iconsystem) {
		this.iconsystem = iconsystem;
	}

	@Column(name = "inventory_itemcd", length = 18)
	public String getInventoryitemcd() {
		return this.inventoryitemcd;
	}

	public void setInventoryitemcd(String inventoryitemcd) {
		this.inventoryitemcd = inventoryitemcd;
	}

	@Column(name = "itemtaxclass")
	public Character getTaxclass() {
		return this.taxclass;
	}

	public void setTaxclass(Character taxclass) {
		this.taxclass = taxclass;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@IndexedEmbedded
	public ItemNewFeature getImsNewFeature() {	
	    return this.itemNewFeature;
	}

	public void setImsNewFeature(ItemNewFeature itemNewFeature) {
		this.itemNewFeature = itemNewFeature;
	}
	
	public void addImsNewFeature(ItemNewFeature itemNewFeature ){
		if(this.getImsNewFeature() != null)
			setImsNewFeature(null);
		itemNewFeature.setItem(this);
		this.itemNewFeature = itemNewFeature;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public IconCollection getIconDescription() {	
	    return iconDescription;// != null//? iconCollection : ImsDataUtil.parseIcons(getIconsystem());
	}

	public void setIconDescription(IconCollection iconDescription) {
		this.iconDescription = iconDescription;
	}
	
	public void addIconDescription(IconCollection iconDescription){
		iconDescription.setItem(this);
		this.iconDescription = iconDescription;
	}

	//@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@IndexedEmbedded(depth=2)
	public List<ItemVendor> getNewVendorSystem() {
		return this.newVendorSystem;
	}

	public void setNewVendorSystem(List<ItemVendor> newVendorSystem) {
		this.newVendorSystem = newVendorSystem;
	}

	public void addNewVendorSystem(ItemVendor vendor){
		if(vendor.getItemVendorId().getItemCode() == null)
		   vendor.getItemVendorId().setItemCode(this.getItemcode());	
		vendor.setItem(this);
		if(getNewVendorSystem() == null)
		   setNewVendorSystem(new ArrayList<ItemVendor>());	
		vendor.setVendorOrder(getNewVendorSystem().size() +1);
		getNewVendorSystem().add(vendor);
	}
	
	public void initVendors(int numberOfVendors){
		for(int i = 0; i < numberOfVendors; i++) {
			addNewVendorSystem(new ItemVendor());
		}
	}

	/*	
	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL)
	//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	//@Fetch(FetchMode.SUBSELECT)
	public List<ColorHue> getColorhues() {
		return this.colorhues;
	}

	public void setColorhues(List<ColorHue> colorhues) {
		this.colorhues = colorhues;
	}
   
	public void addColorhue(ColorHue colorhue){
		colorhue.setItem(this);
		getColorhues().add(colorhue);
	}

	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL)
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
		Item other = (Item) obj;
		if (itemcode == null) {
			if (other.itemcode != null)
				return false;
		} else if (!itemcode.trim().equalsIgnoreCase(other.itemcode.trim()))
			return false;
		return true;
	}

	public Item(String itemcode, String itemcategory, String countryorigin,
			String inactivecode, String shadevariation, String colorcategory,
			Character showonwebsite, String iconsystem, String type,
			Character itemtypecd, String abccd, String itemcd2,
			String inventoryitemcd, Character showonalysedwards,
			Character offshade, Character printlabel, Character taxclass,
			String lottype, String updatecd, String subtype,
			Character directship, Date dropdate, String productline,
			Integer itemgroupnbr, Date priorlastupdated, Description itemdesc,
			Series series, Material material, Dimensions dimensions,
			Price price, VendorInfo vendors, List<String> usage,
			TestSpecification testSpecification,
			SimilarItemCode relateditemcodes, Purchasers purchasers,
			PackagingInfo packaginginfo, Notes notes,
			Applications applications, Units units, Cost cost,
			PriorVendor priorVendor, ItemNewFeature itemNewFeature,
			IconCollection iconDescription){
			//, List<ColorHue> colorhues){
			//Set<ItemVendor> newVendorSystem, List<Note> newNoteSystem) {
		super();
		this.itemcode = itemcode;
		this.itemcategory = itemcategory;
		this.countryorigin = countryorigin;
		this.inactivecode = inactivecode;
		this.shadevariation = shadevariation;
		this.colorcategory = colorcategory;
		this.showonwebsite = showonwebsite;
		this.iconsystem = iconsystem;
		//this.type = type;
		this.itemtypecd = itemtypecd;
		this.abccd = abccd;
		this.itemcd2 = itemcd2;
		this.inventoryitemcd = inventoryitemcd;
		this.showonalysedwards = showonalysedwards;
		this.offshade = offshade;
		this.printlabel = printlabel;
		this.taxclass = taxclass;
		this.lottype = lottype;
		this.updatecd = updatecd;
		//this.subtype = subtype;
		this.directship = directship;
		this.dropdate = dropdate;
		this.productline = productline;
		this.itemgroupnbr = itemgroupnbr;
		this.priorlastupdated = priorlastupdated;
		this.itemdesc = itemdesc;
		this.series = series;
		this.material = material;
		this.dimensions = dimensions;
		this.price = price;
		this.vendors = vendors;
		this.usage = usage;
		this.testSpecification = testSpecification;
		this.relateditemcodes = relateditemcodes;
		this.purchasers = purchasers;
		this.packaginginfo = packaginginfo;
		this.notes = notes;
		this.applications = applications;
		this.units = units;
		this.cost = cost;
		//this.priorVendor = priorVendor;
		this.itemNewFeature = itemNewFeature;
		this.iconDescription = iconDescription;
		this.newVendorSystem = newVendorSystem;
		//this.colorhues = colorhues;
		//this.newNoteSystem = newNoteSystem;
	}

	@Override
	public String toString() {
		return "Item "
				+ "[itemcd=" + itemcode 
				+ ", itemNewFeature=" + itemNewFeature 
				+ ", abccd=" + abccd 
				+ ", category=" + itemcategory 
				+ ", inactivecd=" + inactivecode
				+ ", itemgroupnbr=" + itemgroupnbr 
				+ ", itemtypecd=" + itemtypecd 
				+ ", printlabel=" + printlabel 
				+ ", productline=" + productline 
				+ ", updatecd=" + updatecd 
				+ ", origin=" + countryorigin
				+ ", shadevariation=" + shadevariation 
				+ ", showonwebsite=" + showonwebsite
				+ ", colorcategory=" + colorcategory 
				+ ", showonalysedwards=" + showonalysedwards 
				+ ", offshade=" + offshade 
				+ ", itemcd2=" + itemcd2 
				+ ", icons=" + iconsystem 
				+ ", inventoryItemcd=" + inventoryitemcd 
				+ "]";
	}

}
