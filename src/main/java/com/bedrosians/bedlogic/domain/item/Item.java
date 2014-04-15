
package com.bedrosians.bedlogic.domain.item;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.bedrosians.bedlogic.domain.item.embeddable.Applications;
import com.bedrosians.bedlogic.domain.item.embeddable.Cost;
import com.bedrosians.bedlogic.domain.item.embeddable.Price;
import com.bedrosians.bedlogic.domain.item.embeddable.PriorVendor;
import com.bedrosians.bedlogic.domain.item.embeddable.Buyer;
import com.bedrosians.bedlogic.domain.item.embeddable.SimilarItemCode;
import com.bedrosians.bedlogic.domain.item.embeddable.Test;
import com.bedrosians.bedlogic.domain.item.embeddable.Unit;
import com.bedrosians.bedlogic.domain.item.enums.TaxClass;
import com.bedrosians.bedlogic.util.ImsResultUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

@Component
@Entity
@Table(name = "ims", schema = "public")
public class Item implements java.io.Serializable {

	private static final long serialVersionUID = -3213582221787L;
	
    //------basic info------//   		
	private String itemCode;
	private String description;//itemdesc1;
	private String category;
	private String seriesName;
	private String type;
	private String origin;
	private String shadeVariation;
	private String offShade;
	private String showOnWebsite;
	private String showOnAlysedwards;
	private String inventoryItemCode;
	
	//----- color ------//
	private String color;
	private String colorCategory;
	
	//----- dimension ------//
	private String length;
	private String width;
	private String thickness;
	private Float nominalLength;
	private Float nominalWidth;
	private Float nominalThickness;
	private String sizeUnits;
	private String thicknessUnit;
	
	//----- material info -------//
	private String materialType;
	private String materialCategory;
	private String materialStyle;
	private String materialFeature;
	private String materialClassCode;

	//-----legacy data ------//
	private String itemtypecd;
	private String inactivecd;
	private String printlabel;
    private String itemcd2;
	private TaxClass taxClass;
	private String lottype;
	private String updatecd;
	private String icons;
	private String abccd;
	private String itemdesc2;
	private String fulldesc;
    private String subtype;
	private String productline;
	private Character directship;
	private Date dropdate;
	private Integer itemgroupnbr;
	private Date priorlastupdated;
	
	//------- new featureas --------//	
	@JsonInclude
	private ImsNewFeature imsNewFeature;
	//------- icon (new inplementation/table) --------//	
	@JsonInclude
	private Icon icon;	
	//------- vendor info --------//	
	@JsonInclude
	private List<Vendor> vendors=  new ArrayList<>();
	//------- note (new inplementation/table) --------//
	@JsonInclude
	private List<Note> notes=  new ArrayList<>();	
	//------- price info as embedded component--------//	
    private Price price = new Price();
  //------- cost info as embedded component--------//	
    private Cost cost = new Cost();
    //------- unit info as embedded component--------//	
    Unit unit = new Unit();
    //------- test info as embedded component--------//	
  	Test test = new Test();
    //------- applications as embedded component--------//	
  	Applications applications = new Applications(); 
  	//------- buyer as embedded component--------//	
  	Buyer buyer = new Buyer();
    //------- prior vendor info as embedded component -------//
  	private PriorVendor priorVendor = new PriorVendor();
    //----- similar items as embedded component -----//
  	private SimilarItemCode similarItemCode = new SimilarItemCode();
  	
  	
  	
  	public Item() {
	}

	public Item(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@Id
	@Column(name = "itemcd", unique = true, nullable = false, length = 18)
	public String getItemCode() {
		//return this.itemCode);
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Embedded
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Embedded
	public Applications getApplications() {
		return applications;
	}

	public void setApplications(Applications applications) {
		this.applications = applications;
	}
    
	@Embedded
	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	@Embedded
	public PriorVendor getPriorVendor() {
		return priorVendor;
	}

	public void setPriorVendor(PriorVendor priorVendor) {
		this.priorVendor = priorVendor;
	}	

	@Embedded
	public SimilarItemCode getSimilarItemCode() {
		return similarItemCode;
	}

	public void setSimilarItemCode(SimilarItemCode similarItemCode) {
		this.similarItemCode = similarItemCode;
	}

	//------ vendors ------//
	//Used to update the existing items in ims table
    private Integer vendornbr1;
	private String vendorxrefcd;
	private BigDecimal vendorlistprice = BigDecimal.valueOf(0.00);
	private String vendorpriceunit = "PCS";
	private String vendorfob;
	private Float vendordiscpct = 0F;
	private Integer vendorroundaccuracy = 1;
	private BigDecimal vendornetprice = new BigDecimal(0.00);
	private Float vendormarkuppct = 0F;
	private Float vendorfreightratecwt = 0F;
	private Float dutypct;
	private Integer leadtime;
	//To meet ims_check5 constraint
	private BigDecimal vendorLandedBaseCost = new BigDecimal(0.00);
	private Float listpricemarginpct = 0F;
	private Float vendordiscpct2 = 0F;
	private Float vendordiscpct3 = 0F;

	private Integer vendornbr;
	private Integer vendornbr2;
    private Integer vendornbr3;
    
	//------ notes -------//
	private String poNotes;
	private String notes1;
	private String notes2;
	private String notes3;

	@Column(name = "itemdesc1", length = 35)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "itemdesc2", length = 35)
	public String getItemdesc2() {
		return this.itemdesc2;
	}

	public void setItemdesc2(String itemdesc2) {
		this.itemdesc2 = itemdesc2;
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

	@Column(name = "category", length = 8)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "color", length = 30)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
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

	@Column(name = "inactivecd", length = 3)
	public String getInactivecd() {
		return this.inactivecd;
	}

	public void setInactivecd(String inactivecd) {
		this.inactivecd = inactivecd;
	}

	@Column(name = "itemgroupnbr", precision = 2, scale = 0)
	public Integer getItemgroupnbr() {
		return this.itemgroupnbr;
	}

	public void setItemgroupnbr(Integer itemgroupnbr) {
		this.itemgroupnbr = itemgroupnbr;
	}

	@Column(name = "itemtypecd", length = 1)
	public String getItemtypecd() {
		return this.itemtypecd;
	}
	
	public void setItemtypecd(String itemtypecd) {
		this.itemtypecd = itemtypecd;
	}

	@JsonIgnore
	@Column(name = "leadtime", precision = 4, scale = 0)
	public Integer getLeadtime() {
		return this.leadtime;
	}

	public void setLeadtime(Integer leadtime) {
		this.leadtime = leadtime;
	}

	@Column(name = "lottype", length = 1)
	public String getLottype() {
		return this.lottype;
	}

	public void setLottype(String lottype) {
		this.lottype = lottype;
	}

	

	@JsonIgnore
	@Column(name = "notes1", length = 35)
	public String getNotes1() {
		return this.notes1;
	}

	public void setNotes1(String notes1) {
		this.notes1 = notes1;
	}

	@JsonIgnore
	@Column(name = "notes2", length = 35)
	public String getNotes2() {
		return this.notes2;
	}

	public void setNotes2(String notes2) {
		this.notes2 = notes2;
	}

	@JsonIgnore
	@Column(name = "notes3", length = 120)
	public String getNotes3() {
		return this.notes3;
	}

	public void setNotes3(String notes3) {
		this.notes3 = notes3;
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

	@Column(name = "seriesname", length = 40)
	public String getSeriesName() {
		return this.seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	@JsonIgnore
	@Column(name = "updatecd", length = 10)
	public String getUpdatecd() {
		return this.updatecd;
	}

	public void setUpdatecd(String updatecd) {
		this.updatecd = updatecd;
	}
    
	@JsonIgnore
	@Column(name = "vendorxrefcd", length = 30)
	public String getVendorxrefcd() {
		return this.vendorxrefcd;
	}

	public void setVendorxrefcd(String vendorxrefcd) {
		this.vendorxrefcd = vendorxrefcd;
	}

	@JsonIgnore
	@Column(name = "vendornbr", precision = 6, scale = 0)
	public Integer getVendornbr() {
		return this.vendornbr;
	}

	public void setVendornbr(Integer vendornbr) {
		this.vendornbr = vendornbr;
	}

	@JsonIgnore
	@Column(name = "listpricemarginpct", precision = 5)
	public Float getListpricemarginpct() {
		return this.listpricemarginpct;
	}

	public void setListpricemarginpct(Float listpricemarginpct) {
		this.listpricemarginpct = listpricemarginpct;
	}

	@JsonIgnore
	@Column(name = "vendorpriceunit", length = 4)
	public String getVendorpriceunit() {
		return this.vendorpriceunit;
	}

	public void setVendorpriceunit(String vendorpriceunit) {
		this.vendorpriceunit = vendorpriceunit;
	}

	@JsonIgnore
	@Column(name = "vendorfob", length = 10)
	public String getVendorfob() {
		return this.vendorfob;
	}

	public void setVendorfob(String vendorfob) {
		this.vendorfob = vendorfob;
	}
   
	@JsonIgnore
	@Column(name = "vendorlistprice", precision = 9, scale = 4)
	public BigDecimal getVendorlistprice() {
		return this.vendorlistprice;
	}

	public void setVendorlistprice(BigDecimal vendorlistprice) {
		this.vendorlistprice = vendorlistprice;
	}

	@JsonIgnore
	@Column(name = "vendordiscpct1", precision = 5)
	public Float getVendordiscpct() {
		return this.vendordiscpct;
	}

	public void setVendordiscpct(Float vendordiscpct) {
		this.vendordiscpct = vendordiscpct;
	}

	@JsonIgnore
	@Column(name = "vendordiscpct2", precision = 5)
	public Float getVendordiscpct2() {
		return this.vendordiscpct2;
	}

	public void setVendordiscpct2(Float vendordiscpct2) {
		this.vendordiscpct2 = vendordiscpct2;
	}

	@JsonIgnore
	@Column(name = "vendordiscpct3", precision = 5)
	public Float getVendordiscpct3() {
		return this.vendordiscpct3;
	}

	public void setVendordiscpct3(Float vendordiscpct3) {
		this.vendordiscpct3 = vendordiscpct3;
	}

	@JsonIgnore
	@Column(name = "vendorroundaccuracy", precision = 1, scale = 0)
	public Integer getVendorroundaccuracy() {
		return this.vendorroundaccuracy;
	}

	public void setVendorroundaccuracy(Integer vendorroundaccuracy) {
		this.vendorroundaccuracy = vendorroundaccuracy;
	}

	@JsonIgnore
	@Column(name = "vendornetprice", precision = 9, scale = 4)
	public BigDecimal getVendornetprice() {
		return this.vendornetprice;
	}

	public void setVendornetprice(BigDecimal vendornetprice) {
		this.vendornetprice = vendornetprice;
	}

	@JsonIgnore
	@Column(name = "vendormarkuppct", precision = 4, scale = 1)
	public Float getVendormarkuppct() {
		return this.vendormarkuppct;
	}

	public void setVendormarkuppct(Float vendormarkuppct) {
		this.vendormarkuppct = vendormarkuppct;
	}

	@JsonIgnore
	@Column(name = "vendorfreightratecwt", precision = 9, scale = 4)
	public Float getVendorfreightratecwt() {
		return this.vendorfreightratecwt;
	}

	public void setVendorfreightratecwt(Float vendorfreightratecwt) {
		this.vendorfreightratecwt = vendorfreightratecwt;
	}

	@PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER')") 
	@Column(name = "vendorlandedbasecost", precision = 13, scale = 6)
	public BigDecimal getVendorLandedBaseCost() {
		return this.vendorLandedBaseCost;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER')") 
	public void setVendorLandedBaseCost(BigDecimal vendorLandedBaseCost) {
		this.vendorLandedBaseCost = vendorLandedBaseCost;
	}

	@JsonIgnore
	@Column(name = "vendornbr1", precision = 6, scale = 0)
	public Integer getVendornbr1() {
		return this.vendornbr1;
	}

	public void setVendornbr1(Integer vendornbr1) {
		this.vendornbr1 = vendornbr1;
	}

	@JsonIgnore
	@Column(name = "vendornbr2", precision = 6, scale = 0)
	public Integer getVendornbr2() {
		return this.vendornbr2;
	}

	public void setVendornbr2(Integer vendornbr2) {
		this.vendornbr2 = vendornbr2;
	}

	@JsonIgnore
	@Column(name = "vendornbr3", precision = 6, scale = 0)
	public Integer getVendornbr3() {
		return this.vendornbr3;
	}

	public void setVendornbr3(Integer vendornbr3) {
		this.vendornbr3 = vendornbr3;
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

	@Column(name = "mattype", length = 24)
	public String getMaterialType() {
		return this.materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	@Column(name = "thickness", length = 12)
	public String getThickness() {
		return this.thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	@Column(name = "sizeunits", length = 3)
	public String getSizeUnits() {
		return this.sizeUnits;
	}

	public void setSizeUnits(String sizeUnits) {
		this.sizeUnits = sizeUnits;
	}

	@Column(name = "fulldesc", length = 70)
	public String getFulldesc() {
		return this.fulldesc;
	}

	public void setFulldesc(String fulldesc) {
		this.fulldesc = fulldesc;
	}

	@Column(name = "origin", length = 18)
	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "shadevariation", length = 2)
	public String getShadeVariation() {
		return this.shadeVariation;
	}

	public void setShadeVariation(String shadeVariation) {
		this.shadeVariation = shadeVariation;
	}

	//@Column(name = "application", length = 20)
    //public String getApplication() {
	//	return this.application;
	//}

	//public void setApplication(String application) {
	//	this.application = application;
	//}

	@Column(name = "showonwebsite", length = 1)
	public String getShowOnWebsite() {
		return this.showOnWebsite;
	}

	public void setShowOnWebsite(String showOnWebsite) {
		this.showOnWebsite = showOnWebsite;
	}

	@Column(name = "colorcategory", length = 30)
	public String getColorCategory() {
		return this.colorCategory;
	}

	public void setColorCategory(String colorCategory) {
		this.colorCategory = colorCategory;
	}

	@Column(name = "showonalysedwards", length = 1)
	public String getShowOnAlysedwards() {
		return this.showOnAlysedwards;
	}

	public void setShowOnAlysedwards(String showOnAlysedwards) {
		this.showOnAlysedwards = showOnAlysedwards;
	}

	@Column(name = "offshade", length = 1)
	public String getOffShade() {
		return this.offShade;
	}

	public void setOffShade(String offShade) {
		this.offShade = offShade;
	}

	@Column(name = "itemcd2", length = 18)
	public String getItemcd2() {
		return this.itemcd2;
	}

	public void setItemcd2(String itemcd2) {
		this.itemcd2 = itemcd2;
	}

	
	@Column(name = "materialclass_cd", length = 5)
	public String getMaterialClassCode() {
		return this.materialClassCode;
	}

	public void setMaterialClassCode(String materialClassCode) {
		this.materialClassCode = materialClassCode;
	}

	
	@Column(name = "matcategory", length = 10)
	public String getMaterialCategory() {
		return this.materialCategory;
	}

	public void setMaterialCategory(String materialCategory) {
		this.materialCategory = materialCategory;
	}

	@Column(name = "matstyle", length = 7)
	public String getMaterialStyle() {
		return this.materialStyle;
	}

	public void setMaterialStyle(String materialStyle) {
		this.materialStyle = materialStyle;
	}

	@Column(name = "mfeature", length = 15)
	public String getMaterialFeature() {
		return this.materialFeature;
	}

	public void setMaterialFeature(String materialFeature) {
		this.materialFeature = materialFeature;
	}

	@JsonIgnore
	@Column(name = "type", length = 16)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	@Column(name = "subtype", length = 32)
	public String getSubtype() {
		return this.subtype;
	}

    public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	@JsonIgnore
	@Column(name = "icons", length = 20)
	public String getIcons() {
		return this.icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	@JsonIgnore
	@Column(name = "po_notes", length = 120)
	public String getPoNotes() {
		return this.poNotes;
	}

	public void setPoNotes(String poNotes) {
		this.poNotes = poNotes;
	}

	@Column(name = "thicknessunit", length = 3)
	public String getThicknessUnit() {
		return this.thicknessUnit;
	}

	public void setThicknessUnit(String thicknessUnit) {
		this.thicknessUnit = thicknessUnit;
	}

	@Column(name = "inventory_itemcd", length = 18)
	public String getInventoryItemCode() {
		return this.inventoryItemCode;
	}

	public void setInventoryItemCode(String inventoryItemCode) {
		this.inventoryItemCode = inventoryItemCode;
	}

	@Column(name = "nm_length", precision = 5)
	public Float getNominalLength() {
		return this.nominalLength;
	}

	public void setNominalLength(Float nominalLength) {
		this.nominalLength = nominalLength;
	}

	@Column(name = "nm_width", precision = 5)
	public Float getNominalWidth() {
		return this.nominalWidth;
	}

	public void setNominalWidth(Float nominalWidth) {
		this.nominalWidth = nominalWidth;
	}

	@Column(name = "nm_thickness", precision = 5)
	public Float getNominalThickness() {
		return this.nominalThickness;
	}

	public void setNominalThickness(Float nominalThickness) {
		this.nominalThickness = nominalThickness;
	}

	

	
    @JsonIgnore
	@Column(name = "dutypct", precision = 7, scale = 4)
	public Float getDutypct() {
		return this.dutypct;
	}

	public void setDutypct(Float dutypct) {
		this.dutypct = dutypct;
	}

		@Column(name = "itemtaxclass")
	@Enumerated(EnumType.STRING)
	public TaxClass getTaxClass() {
		return this.taxClass;
	}

	public void setTaxClass(TaxClass taxClass) {
		this.taxClass = taxClass;
	}
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL)
	public ImsNewFeature getImsNewFeature() {	
	    return this.imsNewFeature;
	}

	public void setImsNewFeature(ImsNewFeature imsNewFeature) {
		this.imsNewFeature = imsNewFeature;
	}
	
	public void addImsNewFeature(ImsNewFeature imsNewFeature ){
		imsNewFeature.setItem(this);
		this.imsNewFeature = imsNewFeature;
	}
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL)
	public Icon getIcon() {	
	    return icon != null? icon : ImsResultUtil.parseIcons(getIcons());
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}
	
	public void addIcon(Icon icon ){
		icon.setItem(this);
		this.icon = icon;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(/*fetch = FetchType.EAGER,*/ mappedBy = "item", cascade = CascadeType.ALL)
	public List<Vendor> getVendors() {
		return this.vendors;
	}

	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	public void addVendor(Vendor vendor){
		vendor.setItem(this);
		getVendors().add(vendor);
	}
	
	public void initVendors(int numberOfVendors){
		for(int i = 0; i < numberOfVendors; i++) {
			addVendor(new Vendor());
		}
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(/*fetch = FetchType.EAGER,*/ mappedBy = "item", cascade = CascadeType.ALL)
	public List<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}


	public void addNote(Note note){
		note.setItem(this);
		String noteType = note.getType();
		switch(noteType){
		   case "po": case "po_note":
			   getNotes().add(0, note);
			   break;
		   case "buyer": case "buyer_note":
			   getNotes().add(1, note);
			   break;	
		   case "invoice": case "invoice_note":
			   getNotes().add(2, note);
			   break;
		   case "internal": case "internal_note":
			   getNotes().add(3, note);
			   break;	
		}
	}

	public void initNotes(int numberOfNotes){
		for(int i = 0; i < numberOfNotes; i++) {
			addNote(new Note());
		}
	}
	
	@JsonIgnore
	@Transient
	public String getStandardSellUnit(){
		return ImsResultUtil.getStandardSellUnit(this);
	}
	
	@JsonIgnore
	@Transient
	public String getStandardOrderUnit(){
		return ImsResultUtil.getStandardSellUnit(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemCode == null) ? 0 : itemCode.hashCode());
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
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Item "
				+ "[itemcd=" + itemCode 
				+ ", description=" + description
				+ ", itemdesc2=" + itemdesc2 
				//+ ",  mpsCode=" +  mpsCode
				//+ ",  grade=" +  grade
				//+ ",  status=" +  status
				+ ", imsNewFeature=" + imsNewFeature
			    + ", vendors=" + vendors 
				//+ ", baseunit=" + baseunit
				/*+ ", baseisstdsell=" + baseisstdsell 
				+ ", baseisstdord=" + baseisstdord 
				+ ", baseisfractqty=" + baseisfractqty
				+ ", baseispackunit=" + baseispackunit 
				+ ", baseupc=" + baseupc 
				+ ", baseupcdesc=" + baseupcdesc 
				+ ", basevolperunit=" + basevolperunit 
				+ ", basewgtperunit=" + basewgtperunit
				+ ", unit1unit=" + unit1unit 
				+ ", unit1ratio=" + unit1ratio
				+ ", unit1isstdsell=" + unit1isstdsell 
				+ ", unit1isstdord=" + unit1isstdord 
				+ ", unit1isfractqty=" + unit1isfractqty
				+ ", unit1ispackunit=" + unit1ispackunit 
				+ ", unit1upc=" + unit1upc 
				+ ", unit1upcdesc=" + unit1upcdesc 
				+ ", unit2unit=" + unit2unit 
				+ ", unit2ratio=" + unit2ratio
				+ ", unit2isstdsell=" + unit2isstdsell 
				+ ", unit2isstdord=" + unit2isstdord 
				+ ", unit2isfractqty=" + unit2isfractqty
				+ ", unit2ispackunit=" + unit2ispackunit 
				+ ", unit2upc=" + unit2upc 
				+ ", unit2upcdesc=" + unit2upcdesc 
				+ ", unit3unit=" + unit3unit 
				+ ", unit3ratio=" + unit3ratio
				+ ", unit3isstdsell=" + unit3isstdsell 
				+ ", unit3isstdord=" + unit3isstdord 
				+ ", unit3isfractqty=" + unit3isfractqty
				+ ", unit3ispackunit=" + unit3ispackunit
				+ ", unit3upc=" + unit3upc 
				+ ", unit3upcdesc=" + unit3upcdesc 
				+ ", unit4unit=" + unit4unit 
				+ ", unit4ratio=" + unit4ratio
				+ ", unit4isstdsell=" + unit4isstdsell 
				+ ", unit4isstdord=" + unit4isstdord 
				+ ", unit4isfractqty=" + unit4isfractqty
				+ ", unit4ispackunit=" + unit4ispackunit 
				+ ", unit4upc=" + unit4upc 
				+ ", unit4upcdesc=" + unit4upcdesc 
					+ ", unit1wgtperunit=" + unit1wgtperunit 
				+ ", unit2wgtperunit=" + unit2wgtperunit
				+ ", unit3wgtperunit=" + unit3wgtperunit 
				+ ", unit4wgtperunit=" + unit4wgtperunit 
				*///+ ", listprice=" + listprice 
				//+ ", price=" + price
				//+ ", priorlastupdated=" + priorlastupdated
				//+ ", priorlistprice=" + priorlistprice 
				//+ ", priorPrice=" + priorPrice 
				//+ ", abccd=" + abccd 
				+ ", category=" + category 
				+ ", color=" + color 
				//+ ", directship=" + directship
				//+ ", dropdate=" + dropdate 
				+ ", inactivecd=" + inactivecd
				+ ", itemgroupnbr=" + itemgroupnbr 
				//+ ", itemtaxclass=" + itemtaxclass 
				+ ", itemtypecd=" + itemtypecd 
				+ ", leadtime=" + leadtime 
				//+ ", lottype=" + lottype 
				//+ ", minmarginpct=" + minmarginpct 
				//+ ", nonstockcostpct=" + nonstockcostpct
				+ ", notes1=" + notes1 
				+ ", notes2=" + notes2 
				+ ", notes3=" + notes3 
				+ ", printlabel=" + printlabel 
				+ ", productline=" + productline 
				+ ", seriesname=" + seriesName
				//+ ", specialhandlecd1=" + specialhandlecd1
				//+ ", specialhandlecd2=" + specialhandlecd2
				//+ ", specialhandlecd3=" + specialhandlecd3 
				//+ ", tempdatefrom="	+ tempdatefrom 
				//+ ", tempdatethru=" + tempdatethru
				//+ ", tempPrice=" + tempPrice 
				//+ ", typealf=" + typealf
				+ ", updatecd=" + updatecd 
				+ ", vendorxrefcd=" + vendorxrefcd
				+ ", vendornbr=" + vendornbr 
				//+ ", listpricemarginpct=" + listpricemarginpct 
				//+ ", sellpricemarginpct=" + sellpricemarginpct 
				//+ ", sellpriceroundaccuracy=" + priceRoundAccuracy 
				+ ", vendorpriceunit=" + vendorpriceunit 
				+ ", vendorfob=" + vendorfob
				+ ", vendorlistprice=" + vendorlistprice 
				//+ ", vendordiscpct1=" + vendordiscpct1 + 
				+ ", vendordiscpct2=" + vendordiscpct2
				+ ", vendordiscpct3=" + vendordiscpct3
				+ ", vendorroundaccuracy=" + vendorroundaccuracy
				+ ", vendornetprice=" + vendornetprice 
				+ ", vendormarkuppct=" + vendormarkuppct 
				+ ", vendorfreightratecwt=" + vendorfreightratecwt 
				+ ", vendorlandedbasecost=" + vendorLandedBaseCost 
				//+ ", priorvendorpriceunit=" + priorvendorpriceunit 
				//+ ", priorvendorfob=" + priorvendorfob
				//+ ", priorvendorlistprice=" + priorvendorlistprice
				//+ ", priorvendordiscpct1=" + priorvendordiscpct1
				//+ ", priorvendordiscpct2=" + priorvendordiscpct2
				//+ ", priorvendordiscpct3=" + priorvendordiscpct3
				//+ ", priorvendorroundaccuracy=" + priorvendorroundaccuracy
				//+ ", priorvendornetprice=" + priorvendornetprice
				//+ ", priorvendormarkuppct=" + priorvendormarkuppct
				//+ ", priorvendorfreightratecwt=" + priorvendorfreightratecwt
				//+ ", priorvendorlandedbasecost=" + priorvendorlandedbasecost
				//+ ", calcsellprice=" + calcsellprice 
				//+ ", calclistprice=" + calclistprice 
				//+ ", costrangepct=" + costrangepct
				//+ ", poincludeinvendorcost=" + poincludeinvendorcost
				//+ ", listprice1=" + listprice1 + ", listprice2=" + listprice2
			    //	+ ", listprice3=" + listprice3 + ", listprice4=" + listprice4
				//+ ", listprice5=" + listprice5 + ", sellprice1=" + sellprice1
				//+ ", sellprice2=" + sellprice2 + ", sellprice3=" + sellprice3
				//+ ", sellprice4=" + sellprice4 + ", sellprice5=" + sellprice5
				//+ ", priorlistprice1=" + priorlistprice1 + ", priorlistprice2="
				//+ priorlistprice2 + ", priorlistprice3=" + priorlistprice3
				//+ ", priorlistprice4=" + priorlistprice4 + ", priorlistprice5="
				//+ priorlistprice5 + ", priorsellprice1=" + priorsellprice1
				//+ ", priorsellprice2=" + priorsellprice2 + ", priorsellprice3="
				//+ priorsellprice3 + ", priorsellprice4=" + priorsellprice4
				//+ ", priorsellprice5=" + priorsellprice5 
				//+ ", futurelist=" + futurelist 
				//+ ", futurePrice=" + futurePrice
				//+ ", futurelist1=" + futurelist1 
				//+ ", futuresell1=" + futuresell1 
				//+ ", cost1=" + cost
				//+ ", priorcost=" + priorcost 
				//+ ", priorcost1=" + priorcost1 
				//+ ", futurecost=" + futurecost 
				//+ ", futurecost1=" + futurecost1 
				+ ", vendornbr1=" + vendornbr1 
				+ ", vendornbr2=" + vendornbr2 
				+ ", vendornbr3=" + vendornbr3 
				+ ", length=" + length 
				+ ", width=" + width 
				+ ", mattype=" + materialType
				+ ", thickness=" + thickness 
				+ ", sizeunits=" + sizeUnits
				+ ", fulldesc=" + fulldesc 
				+ ", origin=" + origin
				+ ", shadevariation=" + shadeVariation 
				+ ", showonwebsite=" + showOnWebsite
				+ ", colorcategory=" + colorCategory 
				+ ", showonalysedwards=" + showOnAlysedwards 
				+ ", offshade=" + offShade 
				+ ", itemcd2=" + itemcd2 
				//+ ", waterAbsorption=" + waterAbsorption
				//+ ", scratchResistance=" + scratchResistance
				//+ ", frostResistance=" + frostResistance
				//+ ", chemicalResistance=" + chemicalResistance
				//+ ", peiAbrasion=" + peiAbrasion 
				//+ ", scofWet=" + scofWet
				//+ ", scofDry=" + scofDry 
				//+ ", breakingStrength=" + breakingStrength 
				//+ ", samplenbr=" + samplenbr
				//+ ", waterAbsorptionSign=" + waterAbsorptionSign
				//+ ", scofWetSign=" + scofWetSign 
				//+ ", scofDrySign=" + scofDrySign 
				//+ ", materialclassCd=" + materialclassCd
				//+ ", stdunit=" + stdunit + 
				//", stdratio=" + stdratio
				//+ ", ordunit=" + ordunit
				//+ ", ordratio=" + ordratio
				+ ", matcategory=" + materialCategory 
				+ ", matstyle=" + materialStyle
				//+ ", moh=" + moh + 
				//", mfeature=" + mfeature 
				//+ ", price=" + price 
				//+ ", qualitychoice=" + qualitychoice
				//+ ", greenfriendly=" + greenfriendly 
				+ ", type=" + type
				+ ", subtype=" + subtype 
				+ ", icons=" + icons 
				+ ", poNotes=" + poNotes
				//+ ", pricegroup=" + pricegroup 
				//+ ", srStandard=" + srStandard
				+ ", thicknessunit=" + thicknessUnit
				//+ ", bkStandard=" + bkStandard 
				+ ", inventoryItemcd=" + inventoryItemCode
				+ ", nmLength=" + nominalLength 
				+ ", nmWidth=" + nominalWidth 
				+ ", nmThickness=" + nominalThickness 
				//+ ", residential=" + residential 
				//+ ", lightcommercial=" + lightcommercial
				//+ ", commercial=" + commercial 
				//+ ", preConsummer=" + preConsummer 
				//+ ", posConsummer=" + posConsummer
				//+ ", leadPoint=" + leadPoint 
				//+ ", purchaser=" + purchaser
				//+ ", purchaser2=" + purchaser2 
				+ ", dutypct=" + dutypct
				//+ ", knownAliases1=" + knownAliases1 
				//+ ", knownAliases2=" + knownAliases2 
				//+ ", knownAliases3=" + knownAliases3
				//+ ", knownAliases4=" + knownAliases4 
				//+ ", knownAliases5=" + knownAliases5 
				//+ ", knownAliases6=" + knownAliases6
				//+ ", knownAliases7=" + knownAliases7 
				//+ ", similarItemcd1=" + similarItemcd1 
				//+ ", similarItemcd2=" + similarItemcd2
				//+ ", similarItemcd3=" + similarItemcd3 
				//+ ", similarItemcd4=" + similarItemcd4 
				//+ ", similarItemcd5=" + similarItemcd5
				//+ ", similarItemcd6=" + similarItemcd6 
				//+ ", similarItemcd7=" + similarItemcd7 
				//+ ", restricted=" + restricted 
				//+ ", warpage=" + warpage 
				//+ ", wedging=" + wedging 
				//+ ", dcof=" + dcof
				//+ ", thermalShock=" + thermalShock 
				//+ ", bondStrength=" + bondStrength 
				+ "]";
	}

	//----- not used fields -----//
		/*

	    //private String application;
		//private String specialhandlecd1;
		//private String specialhandlecd2;
		//private String specialhandlecd3;
		//private String typealf;
		
		//private Integer samplenbr;
		//private String waterAbsorptionSign;
		//private String scofWetSign;
		//private String scofDrySign;
	    //private String qualitychoice;

		private Character price;
		private BigDecimal calcsellprice;
		private BigDecimal calclistprice;
		private BigDecimal listprice1;
		private BigDecimal listprice2;
		private BigDecimal listprice3;
		private BigDecimal listprice4;
		private BigDecimal listprice5;
		private BigDecimal sellprice1;
		private BigDecimal sellprice2;
		private BigDecimal sellprice3;
		private BigDecimal sellprice4;
		private BigDecimal sellprice5;
		private BigDecimal priorlistprice1;
		private BigDecimal priorlistprice2;
		private BigDecimal priorlistprice3;
		private BigDecimal priorlistprice4;
		private BigDecimal priorlistprice5;
		private BigDecimal priorsellprice1;
		private BigDecimal priorsellprice2;
		private BigDecimal priorsellprice3;
		private BigDecimal priorsellprice4;
		private BigDecimal priorsellprice5;
		private BigDecimal futurelist;
		private BigDecimal futurelist1;
		private BigDecimal futuresell1;
		private Float priorvendordiscpct2;
		private Float priorvendordiscpct3;
		private String knownAliases1;
		private String knownAliases2;
		private String knownAliases3;
		private String knownAliases4;
		private String knownAliases5;
		private String knownAliases6;
		private String knownAliases7;
		*/


	//@Column(name = "samplenbr", precision = 8, scale = 0)
	//public Integer getSamplenbr() {
	//	return this.samplenbr;
	//}

	//public void setSamplenbr(Integer samplenbr) {
	//	this.samplenbr = samplenbr;
	//}

	//@Column(name = "water_absorption_sign", length = 2)
	//public String getWaterAbsorptionSign() {
	//	return this.waterAbsorptionSign;
	//}

	//public void setWaterAbsorptionSign(String waterAbsorptionSign) {
	//	this.waterAbsorptionSign = waterAbsorptionSign;
	//}

	//@Column(name = "scof_wet_sign", length = 2)
	//public String getScofWetSign() {
	//	return this.scofWetSign;
	//}

	//public void setScofWetSign(String scofWetSign) {
	//	this.scofWetSign = scofWetSign;
	//}

	//@Column(name = "scof_dry_sign", length = 2)
	//public String getScofDrySign() {
	//	return this.scofDrySign;
	//}

	//public void setScofDrySign(String scofDrySign) {
	//	this.scofDrySign = scofDrySign;
	//}

	//@Column(name = "itemtaxclass", length = 1)
		//public Character getItemtaxclass() {
		//	return this.itemtaxclass;
		//}

		//public void setItemtaxclass(Character itemtaxclass) {
		//	this.itemtaxclass = itemtaxclass;
		//}
	//@Column(name = "specialhandlecd1", length = 10)
		//public String getSpecialhandlecd1() {
		//	return this.specialhandlecd1;
		//}

		//public void setSpecialhandlecd1(String specialhandlecd1) {
		//	this.specialhandlecd1 = specialhandlecd1;
		//}

		//@Column(name = "specialhandlecd2", length = 10)
		//public String getSpecialhandlecd2() {
		//	return this.specialhandlecd2;
		//}

		//public void setSpecialhandlecd2(String specialhandlecd2) {
		//	this.specialhandlecd2 = specialhandlecd2;
		//}

		//@Column(name = "specialhandlecd3", length = 10)
	    //public String getSpecialhandlecd3() {
		//	return this.specialhandlecd3;
		//}

		//public void setSpecialhandlecd3(String specialhandlecd3) {
		//	this.specialhandlecd3 = specialhandlecd3;
		//}

		
		//@Column(name = "typealf", length = 8)
		//public String getTypealf() {
		//	return this.typealf;
		//}

		//public void setTypealf(String typealf) {
		//	this.typealf = typealf;
		//}
	
	//@Column(name = "price", length = 1)
		//public Character getPrice() {
		//	return this.price;
		//}

		//public void setPrice(Character price) {
		//	this.price = price;
		//}

		//@Column(name = "qualitychoice", length = 12)
		//public String getQualitychoice() {
		//	return this.qualitychoice;
		//}

		//public void setQualitychoice(String qualitychoice) {
		//	this.qualitychoice = qualitychoice;
		//}
	
	//@Column(name = "calcsellprice", precision = 9, scale = 4)
		//public BigDecimal getCalcsellprice() {
		//	return this.calcsellprice;
		//}
	//@Column(name = "listprice1", precision = 9, scale = 4)
		//public BigDecimal getListprice1() {
		//	return this.listprice1;
		//}

		//public void setListprice1(BigDecimal listprice1) {
		//	this.listprice1 = listprice1;
		//}

		//@Column(name = "listprice2", precision = 9, scale = 4)
		//public BigDecimal getListprice2() {
		//	return this.listprice2;
		//}

		//public void setListprice2(BigDecimal listprice2) {
		//	this.listprice2 = listprice2;
		//}

		//@Column(name = "listprice3", precision = 9, scale = 4)
		//public BigDecimal getListprice3() {
		//	return this.listprice3;
		//}

		//public void setListprice3(BigDecimal listprice3) {
		//	this.listprice3 = listprice3;
		//}

		//@Column(name = "listprice4", precision = 9, scale = 4)
		//public BigDecimal getListprice4() {
		//	return this.listprice4;
		//}

		//public void setListprice4(BigDecimal listprice4) {
		//	this.listprice4 = listprice4;
		//}

		//@Column(name = "listprice5", precision = 9, scale = 4)
		//public BigDecimal getListprice5() {
		//	return this.listprice5;
		//}

		//public void setListprice5(BigDecimal listprice5) {
		//	this.listprice5 = listprice5;
		//}

		//@Column(name = "sellprice1", precision = 9, scale = 4)
		//public BigDecimal getSellprice1() {
		//	return this.sellprice1;
		//}

		//public void setSellprice1(BigDecimal sellprice1) {
		//	this.sellprice1 = sellprice1;
		//}

		//@Column(name = "sellprice2", precision = 9, scale = 4)
		//public BigDecimal getSellprice2() {
		//	return this.sellprice2;
		//}

		//public void setSellprice2(BigDecimal sellprice2) {
		//	this.sellprice2 = sellprice2;
		//}

		//@Column(name = "sellprice3", precision = 9, scale = 4)
		//public BigDecimal getSellprice3() {
		//	return this.sellprice3;
		//}

		//public void setSellprice3(BigDecimal sellprice3) {
		//	this.sellprice3 = sellprice3;
		//}

		//@Column(name = "sellprice4", precision = 9, scale = 4)
		//public BigDecimal getSellprice4() {
		//	return this.sellprice4;
		//}

		//public void setSellprice4(BigDecimal sellprice4) {
		//	this.sellprice4 = sellprice4;
		//}

		///@Column(name = "sellprice5", precision = 9, scale = 4)
		//public BigDecimal getSellprice5() {
		//	return this.sellprice5;
		//}

		//public void setSellprice5(BigDecimal sellprice5) {
		//	this.sellprice5 = sellprice5;
		//}

		//@Column(name = "priorlistprice1", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice1() {
		//	return this.priorlistprice1;
		//}

		//public void setPriorlistprice1(BigDecimal priorlistprice1) {
//			this.priorlistprice1 = priorlistprice1;
		//}

		//@Column(name = "priorlistprice2", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice2() {
		//	return this.priorlistprice2;
		//}

		//public void setPriorlistprice2(BigDecimal priorlistprice2) {
		//	this.priorlistprice2 = priorlistprice2;
		//}

		//@Column(name = "priorlistprice3", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice3() {
		//	return this.priorlistprice3;
		//}

		//public void setPriorlistprice3(BigDecimal priorlistprice3) {
		//	this.priorlistprice3 = priorlistprice3;
		//}

		//@Column(name = "priorlistprice4", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice4() {
		//	return this.priorlistprice4;
		//}

		//public void setPriorlistprice4(BigDecimal priorlistprice4) {
		//	this.priorlistprice4 = priorlistprice4;
		//}

		//@Column(name = "priorlistprice5", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice5() {
		//	return this.priorlistprice5;
		//}

		//public void setPriorlistprice5(BigDecimal priorlistprice5) {
		//	this.priorlistprice5 = priorlistprice5;
		//}

		//@Column(name = "priorsellprice1", precision = 9, scale = 4)
		//public BigDecimal getPriorsellprice1() {
		//	return this.priorsellprice1;
		//}

		//public void setPriorsellprice1(BigDecimal priorsellprice1) {
		//	this.priorsellprice1 = priorsellprice1;
		//}

		//@Column(name = "priorsellprice2", precision = 9, scale = 4)
		//public BigDecimal getPriorsellprice2() {
		//	return this.priorsellprice2;
		//}

		//public void setPriorsellprice2(BigDecimal priorsellprice2) {
		//	this.priorsellprice2 = priorsellprice2;
		//}

		//@Column(name = "priorsellprice3", precision = 9, scale = 4)
		//public BigDecimal getPriorsellprice3() {
		//	return this.priorsellprice3;
		//}

		//public void setPriorsellprice3(BigDecimal priorsellprice3) {
		//	this.priorsellprice3 = priorsellprice3;
		//}

		//@Column(name = "priorsellprice4", precision = 9, scale = 4)
		//public BigDecimal getPriorsellprice4() {
		//	return this.priorsellprice4;
		//}

		//public void setPriorsellprice4(BigDecimal priorsellprice4) {
		//	this.priorsellprice4 = priorsellprice4;
		//}

		//@Column(name = "priorsellprice5", precision = 9, scale = 4)
		///public BigDecimal getPriorsellprice5() {
		//	return this.priorsellprice5;
		//}

		//public void setPriorsellprice5(BigDecimal priorsellprice5) {
		//	this.priorsellprice5 = priorsellprice5;
		//}

		//@Column(name = "futurelist", precision = 9, scale = 4)
		//public BigDecimal getFuturelist() {
		//	return this.futurelist;
		//}

		//public void setFuturelist(BigDecimal futurelist) {
//			this.futurelist = futurelist;
		//}

		//@Column(name = "futurelist1", precision = 9, scale = 4)
		//public BigDecimal getFuturelist1() {
		//	return this.futurelist1;
		//}

		//public void setFuturelist1(BigDecimal futurelist1) {
		//	this.futurelist1 = futurelist1;
		//}

		//@Column(name = "futuresell1", precision = 9, scale = 4)
		//public BigDecimal getFuturesell1() {
		//	return this.futuresell1;
		//}

		//public void setFuturesell1(BigDecimal futuresell1) {
		//	this.futuresell1 = futuresell1;
		//}
		//public void setCalcsellprice(BigDecimal calcsellprice) {
		//	this.calcsellprice = calcsellprice;
		//}

		//@Column(name = "calclistprice", precision = 9, scale = 4)
		//public BigDecimal getCalclistprice() {
		//	return this.calclistprice;
		//}

		//public void setCalclistprice(BigDecimal calclistprice) {
		//	this.calclistprice = calclistprice;
		//}

		
	//@Column(name = "known_aliases1", length = 30)
		//public String getKnownAliases1() {
		//	return this.knownAliases1;
		//}

		//public void setKnownAliases1(String knownAliases1) {
		//	this.knownAliases1 = knownAliases1;
		//}

		//@Column(name = "known_aliases2", length = 30)
		//public String getKnownAliases2() {
		//	return this.knownAliases2;
		//}

		//public void setKnownAliases2(String knownAliases2) {
		//	this.knownAliases2 = knownAliases2;
		//}

		//@Column(name = "known_aliases3", length = 30)
		//public String getKnownAliases3() {
		//	return this.knownAliases3;
		//}

		//public void setKnownAliases3(String knownAliases3) {
		//	this.knownAliases3 = knownAliases3;
		//}

		//@Column(name = "known_aliases4", length = 30)
		//public String getKnownAliases4() {
		//	return this.knownAliases4;
		//}

		//public void setKnownAliases4(String knownAliases4) {
		//	this.knownAliases4 = knownAliases4;
		//}

		//@Column(name = "known_aliases5", length = 30)
		//public String getKnownAliases5() {
		//	return this.knownAliases5;
		//}

		//public void setKnownAliases5(String knownAliases5) {
		//	this.knownAliases5 = knownAliases5;
		//}

		//@Column(name = "known_aliases6", length = 30)
		//public String getKnownAliases6() {
		//	return this.knownAliases6;
		//}

		//public void setKnownAliases6(String knownAliases6) {
		//	this.knownAliases6 = knownAliases6;
		//}

		//@Column(name = "known_aliases7", length = 30)
		//public String getKnownAliases7() {
		//	return this.knownAliases7;
		//}

		//public void setKnownAliases7(String knownAliases7) {
		//	this.knownAliases7 = knownAliases7;
		//}
			
	    
}
