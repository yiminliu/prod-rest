Ims Service Requirements
==============================

Overview
--------
The ims Rest Web Service manages Bedrosians item information. The service will allow creation, retrieval, update and deletion of item resources.

                        Service wadl
-----------------------------------------------------------------------

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<application xmlns="http://wadl.dev.java.net/2009/02">
    <doc xmlns:jersey="http://jersey.java.net/" jersey:generatedBy="Jersey: 1.18.3 12/01/2014 08:23 AM"/>
    <grammars/>
    <resources base="http://localhost:8080/bedlogic/v2/">
        <resource path="/ims">
            <method id="get" name="GET">
                <response>
                    <representation mediaType="application/json"/>
                </response>
            </method>
            <method id="create" name="POST">
                <request>
                    <representation mediaType="application/json"/>
                </request>
                <response>
                    <representation mediaType="*/*"/>
                </response>
            </method>
            <method id="update" name="PUT">
                <request>
                    <representation mediaType="application/json"/>
                </request>
                <response>
                    <representation mediaType="application/json"/>
                </response>
            </method>
            <resource path="{itemcode}">
                <param xmlns:xs="http://www.w3.org/2001/XMLSchema" name="itemcode" style="template" type="xs:string"/>
                <method id="delete" name="DELETE">
                    <response>
                        <representation mediaType="*/*"/>
                    </response>
                </method>
                <method id="getByItemCode" name="GET">
                    <response>
                        <representation mediaType="application/json"/>
                    </response>
                </method>
            </resource>
        </resource>
        <resource path="/hello">
            <method id="getHello" name="GET">
                <response>
                    <representation mediaType="application/json"/>
                </response>
            </method>
            <resource path="/index">
                <method id="createInitialLuceneIndex" name="GET">
                    <response>
                        <representation mediaType="*/*"/>
                    </response>
                </method>
            </resource>
        </resource>
    </resources>
</application>

* **wadl location: http://host:8080/bedlogic/v2/application.wadl**

 
                        Service Types and Specifications
-----------------------------------------------------------------------

1. Create a new item
* **required:**  item code(itemcode), item category(category), description(itemdesc.itemdesc1)

* **note:** 
	Item code should contain only letters, numbers or dashes. No special characters. The maximal length of item code is 18. 

2. Look up items with search parameter

* ** Resource URL **  
		http://hostname:port/bedrosians/bedlogic/v2/ims?{parameters} 
* ** Return data**  
        return a list of items matched the given search criteria	
* ** Search parameters** 

     *** itemcode *** (optional)  
		example value: AECBUB218NR
		
	 *** itemcategory *** (optional)  
		example value: BRECCIA
		
	 *** fulldesc *** (optional)  
		example value: Diamonds Are a Girls Best Friend
		
	*** itemdesc1 *** (optional)  
		example value: 13X13 Breccia Beige
	    
	*** inactivecode *** (optional)  
		possible values: N, Y, D
	
	*** showonwebsite *** (optional)  
		possible values: N, Y
		    
	*** seriesname *** (optional)  
		example value: builder
			
	*** materialCategory *** (optional)  
		example value: Deco
		
	*** materialclass *** (optional)  
		example value: FRT
		
	*** materialstyle *** (optional)  
		example value: SFCR
		
	*** materialfeature *** (optional)  
		example value: LE
		
	*** materialtype *** (optional)  
		example value: Slate
		
	*** colorcategory *** (optional)  
		example value: YELLOW
		
	*** colorhues *** (optional)  
		example value: YELLOW
		
	*** color *** (optional)  
		example value: White
		
	*** length *** (optional)  
		example value: 12
		
	*** width *** (optional)  
		example value: 12
		
	*** lengthmax *** (optional)  
		example value: 12
		
	*** lengthMin *** (optional)  
		example value: 2
		
	*** widthmax *** (optional)  
		example value: 12
		
	*** widthMin *** (optional)  
		example value: 2	
		
	*** size *** (optional)  
		example value: 10X8
		
	*** countryorigin *** (optional)  
		example value: USA
													
	*** purchaser *** (optional)  
		example value: Tom		
											
		
3. Look up an item with item code

* ** Resource URL **  
		http://hostname:port/bedrosians/bedlogic/v2/ims/{itemcode} 
* ** Path Value** 
    *** itemcode *** (required)  
		example value: AECBUB218NR
* ** Return data**  
        return exact one item with the item code which matches the given item code, or NotFoundException if no item with the given item code found.	
		

4. Update an item
* **required:**  item code(itemcode)
		
		 
                        Example Requests
-----------------------------------------------------------------------		 
	* ** Search items with query parameters **  
   curl --basic -u "guest:" --get "http://localhost:8080/bedlogic/v2/ims?inactivecode=N&showonwebsite=Y&seriesname=builder"
   
   Result:
   
 [ {
  "itemcode" : "MET10148",
  "itemcategory" : "METROQUA",
  "countryorigin" : "",
  "inactivecode" : "N",
  "shadevariation" : "V1",
  "colorcategory" : "BEIGE:GREEN",
  "showonwebsite" : "Y",
  "iconsystem" : "NYYNNNNYNNYNNNNNNNNN",
  "itemtypecode" : "#",
  "abccode" : "SO",
  "itemcode2" : "METBASICS48",
  "inventoryitemcode" : "",
  "showonalysedwards" : "N",
  "offshade" : "N",
  "printlabel" : "N",
  "taxclass" : "T",
  "lottype" : "",
  "updatecode" : "CERA-MET",
  "directship" : "N",
  "dropdate" : null,
  "productline" : "CERA",
  "itemgroupnbr" : 1,
  "priorlastupdated" : "2014-03-31",
  "itemdesc" : {
    "fulldesc" : "QUARRY -  OYSTER BAY FIELD 4X",
    "itemdesc1" : "QUARRY -  OYSTER BAY FIELD 4X8"
  },
  "series" : {
    "seriesname" : "Quarrybasics",
    "seriescolor" : "Oyster Bay"
  },
  "material" : {
    "materialtype" : "Quarry",
    "materialcategory" : "Tile",
    "materialclass" : "CTSRC",
    "materialstyle" : "FW",
    "materialfeature" : ""
  },
  "dimensions" : {
    "nominallength" : 4.0,
    "nominalwidth" : 8.0,
    "sizeunits" : "E",
    "thickness" : "1/2",
    "thicknessunit" : "E",
    "length" : "8",
    "width" : "4",
    "nominalthickness" : 0.0
  },
  "price" : {
    "listprice" : 4.1800,
    "sellprice" : 4.1800,
    "pricegroup" : "",
    "priceunit" : "S/F",
    "sellpricemarginpct" : 0.0,
    "sellpriceroundaccuracy" : 0,
    "listpricemarginpct" : 0.0,
    "minmarginpct" : 15.0,
    "futuresell" : 4.3800,
    "tempprice" : 0.0000,
    "tempdatefrom" : null,
    "tempdatethru" : null,
    "priorlistprice" : 5.0600,
    "priorsellprice" : 4.1500
  },
  "testSpecification" : {
    "waterabsorption" : null,
    "scratchresistance" : null,
    "frostresistance" : null,
    "chemicalresistance" : null,
    "peiabrasion" : null,
    "scofwet" : null,
    "scofdry" : null,
    "breakingstrength" : null,
    "scratchstandard" : "",
    "breakingstandard" : "",
    "restricted" : "N",
    "warpage" : null,
    "wedging" : null,
    "dcof" : null,
    "thermalshock" : null,
    "bondstrength" : "",
    "greenfriendly" : "N",
    "moh" : 0.0,
    "leadpoint" : "N",
    "preconsummer" : 0.0,
    "posconsummer" : 0.0
  },
  "purchasers" : {
    "purchaser" : "ALICIAB",
    "purchaser2" : ""
  },
  "packaginginfo" : {
    "boxPieces" : 32.0,
    "boxSF" : 7.111111,
    "boxWeight" : 35.52,
    "palletBox" : 0.0,
    "palletSF" : 0.0,
    "palletWeight" : 0.0
  },
  "notes" : {
    "ponotes" : "",
    "buyernotes" : "371000",
    "invoicenotes" : "",
    "internalnotes" : ""
  },
  "applications" : {
    "residential" : "FR:WR:CR:SR",
    "lightcommercial" : "FL:WL:CL:SL",
    "commercial" : "FC:WC:CC:SC",
    "residentialList" : null,
    "lightcommercialList" : null,
    "commercialList" : null
  },
  "usage" : [ "FR", "WR", "CR", "SR", "FL", "WL", "CL", "SL", "FC", "WC", "CC", "SC" ],
  "units" : {
    "stdunit" : "S/F",
    "stdratio" : 4.5,
    "ordunit" : "S/F",
    "ordratio" : 4.5,
    "baseunit" : "PCS",
    "baseisstdsell" : "N",
    "baseisstdord" : "N",
    "baseisfractqty" : "N",
    "baseispackunit" : "Y",
    "baseupc" : 0,
    "baseupcdesc" : "",
    "basevolperunit" : 0.0000,
    "basewgtperunit" : 1.1100,
    "unit1unit" : "CTN",
    "unit1ratio" : 32.0,
    "unit1isstdsell" : "N",
    "unit1isstdord" : "N",
    "unit1isfractqty" : "N",
    "unit1ispackunit" : "Y",
    "unit1upc" : 0,
    "unit1upcdesc" : "",
    "unit1wgtperunit" : 0.0000,
    "unit2unit" : "",
    "unit2ratio" : 0.0,
    "unit2isstdsell" : "N",
    "unit2isstdord" : "N",
    "unit2isfractqty" : "N",
    "unit2ispackunit" : "N",
    "unit2upc" : 0,
    "unit2upcdesc" : "",
    "unit2wgtperunit" : 0.0000,
    "unit3unit" : "",
    "unit3ratio" : 0.0,
    "unit3isstdsell" : "N",
    "unit3isstdord" : "N",
    "unit3isfractqty" : "N",
    "unit3ispackunit" : "N",
    "unit3upc" : 0,
    "unit3upcdesc" : "",
    "unit3wgtperunit" : 0.0000,
    "unit4unit" : "S/F",
    "unit4ratio" : 4.5,
    "unit4isstdsell" : "Y",
    "unit4isstdord" : "Y",
    "unit4isfractqty" : "Y",
    "unit4ispackunit" : "N",
    "unit4upc" : 0,
    "unit4upcdesc" : "",
    "unit4wgtperunit" : 0.0000
  },
  "cost" : {
    "cost1" : 0.0000,
    "priorcost" : 0.0000,
    "futurecost" : 1.3000,
    "poincludeinvendorcost" : "Y",
    "nonstockcostpct" : 0.0,
    "costrangepct" : 20.0
  },
  "relateditemcodes" : null,
  "vendors" : {
    "vendornbr" : 0,
    "vendornbr1" : 371000,
    "vendornbr2" : 0,
    "vendorxrefcd" : "",
    "vendorlistprice" : 1.3000,
    "vendorpriceunit" : "S/F",
    "vendorfob" : "FACTORY",
    "vendordiscpct" : 0.0,
    "vendorroundaccuracy" : 2,
    "vendornetprice" : 1.3000,
    "vendormarkuppct" : 0.0,
    "vendorfreightratecwt" : 0.0,
    "dutypct" : 0.0,
    "leadtime" : 0,
    "vendorlandedbasecost" : 0.2889,
    "vendordiscpct2" : 0.0,
    "vendordiscpct3" : 0.0
  },
  "newVendorSystem" : [ {
    "vendorId" : {
      "id" : 371000
    },
    "vendorOrder" : 1,
    "vendorName" : null,
    "vendorName2" : null,
    "vendorXrefId" : "",
    "vendorListPrice" : 1.3000,
    "vendorNetPrice" : 1.3000,
    "vendorPriceUnit" : "S/F",
    "vendorFob" : "FACTORY",
    "vendorDiscountPct" : 0.0,
    "vendorPriceRoundAccuracy" : 2,
    "vendorMarkupPct" : 0.0,
    "vendorFreightRateCwt" : 0.0,
    "vendorLandedBaseCost" : 0.2889,
    "leadTime" : 0,
    "dutyPct" : 0.0
  } ],
  "newFeature" : null,
  "iconDescription" : {
    "madeInCountry" : null,
    "exteriorProduct" : "Yes",
    "adaAccessibility" : "No",
    "throughColor" : "No",
    "colorBody" : "Yes",
    "inkJet" : "No",
    "glazed" : "No",
    "unglazed" : "Yes",
    "rectifiedEdge" : "No",
    "chiseledEdge" : "No",
    "versaillesPattern" : "No",
    "recycled" : "No",
    "postRecycled" : "No",
    "preRecycled" : "No",
    "leadPoint" : "No",
    "greenFriendly" : "No",
    "coefficientOfFriction" : "No"
  },
  "colorhues" : [ {
    "colorHue" : "BEIGE"
  }, {
    "colorHue" : "GREEN"
  } ]
}, {
  "itemcode" : "MET101Q3485",
  "itemcategory" : "METROQUA",
  "countryorigin" : "",
  "inactivecode" : "N",
  "shadevariation" : "V1",
  "colorcategory" : "BEIGE:GREEN",
  "showonwebsite" : "Y",
  "iconsystem" : "NYYNNNNYNNYNNNNNNNNN",
  "itemtypecode" : "#",
  "abccode" : "SO",
  "itemcode2" : "METBASICSQ3485",
  "inventoryitemcode" : "",
  "showonalysedwards" : "N",
  "offshade" : "N",
  "printlabel" : "N",
  "taxclass" : "T",
  "lottype" : "",
  "updatecode" : "CERA-MET",
  "directship" : "N",
  "dropdate" : null,
  "productline" : "CERA",
  "itemgroupnbr" : 1,
  "priorlastupdated" : "2014-03-31",
  "itemdesc" : {
    "fulldesc" : "",
    "itemdesc1" : "QUARRY-OYSTERBAY COVE BASE 4X8"
  },
  "series" : {
    "seriesname" : "Quarrybasics",
    "seriescolor" : "Oyster Bay"
  },
  "material" : {
    "materialtype" : "Quarry",
    "materialcategory" : "Trim",
    "materialclass" : "CTSRC",
    "materialstyle" : "CB",
    "materialfeature" : ""
  },
  "dimensions" : {
    "nominallength" : 4.0,
    "nominalwidth" : 8.0,
    "sizeunits" : "E",
    "thickness" : "5/8",
    "thicknessunit" : "E",
    "length" : "4",
    "width" : "8",
    "nominalthickness" : 0.0
  },
  "price" : {
    "listprice" : 2.9800,
    "sellprice" : 2.9800,
    "pricegroup" : "",
    "priceunit" : "PCS",
    "sellpricemarginpct" : 0.0,
    "sellpriceroundaccuracy" : 0,
    "listpricemarginpct" : 0.0,
    "minmarginpct" : 15.0,
    "futuresell" : 0.0000,
    "tempprice" : 0.0000,
    "tempdatefrom" : null,
    "tempdatethru" : null,
    "priorlistprice" : 3.6900,
    "priorsellprice" : 2.9500
  },
  "testSpecification" : {
    "waterabsorption" : null,
    "scratchresistance" : null,
    "frostresistance" : null,
    "chemicalresistance" : null,
    "peiabrasion" : null,
    "scofwet" : null,
    "scofdry" : null,
    "breakingstrength" : null,
    "scratchstandard" : "",
    "breakingstandard" : "",
    "restricted" : "N",
    "warpage" : null,
    "wedging" : null,
    "dcof" : null,
    "thermalshock" : null,
    "bondstrength" : "",
    "greenfriendly" : "N",
    "moh" : 0.0,
    "leadpoint" : "N",
    "preconsummer" : 0.0,
    "posconsummer" : 0.0
  },
  "purchasers" : {
    "purchaser" : "ALICIAB",
    "purchaser2" : ""
  },
  "packaginginfo" : {
    "boxPieces" : 12.0,
    "boxSF" : 0.0,
    "boxWeight" : 14.400001,
    "palletBox" : 0.0,
    "palletSF" : 0.0,
    "palletWeight" : 0.0
  },
  "notes" : {
    "ponotes" : "",
    "buyernotes" : "371000",
    "invoicenotes" : "",
    "internalnotes" : ""
  },
  "applications" : {
    "residential" : "FR:WR:CR:SR",
    "lightcommercial" : "FL:WL:CL:SL",
    "commercial" : "FC:WC:CC:SC",
    "residentialList" : null,
    "lightcommercialList" : null,
    "commercialList" : null
  },
  "usage" : [ "FR", "WR", "CR", "SR", "FL", "WL", "CL", "SL", "FC", "WC", "CC", "SC" ],
  "units" : {
    "stdunit" : "PCS",
    "stdratio" : 1.0,
    "ordunit" : "PCS",
    "ordratio" : 1.0,
    "baseunit" : "PCS",
    "baseisstdsell" : "Y",
    "baseisstdord" : "Y",
    "baseisfractqty" : "N",
    "baseispackunit" : "Y",
    "baseupc" : 0,
    "baseupcdesc" : "",
    "basevolperunit" : 0.0000,
    "basewgtperunit" : 1.2000,
    "unit1unit" : "CTN",
    "unit1ratio" : 12.0,
    "unit1isstdsell" : "N",
    "unit1isstdord" : "N",
    "unit1isfractqty" : "N",
    "unit1ispackunit" : "Y",
    "unit1upc" : 0,
    "unit1upcdesc" : "",
    "unit1wgtperunit" : 0.0000,
    "unit2unit" : "",
    "unit2ratio" : 0.0,
    "unit2isstdsell" : "N",
    "unit2isstdord" : "N",
    "unit2isfractqty" : "N",
    "unit2ispackunit" : "N",
    "unit2upc" : 0,
    "unit2upcdesc" : "",
    "unit2wgtperunit" : 0.0000,
    "unit3unit" : "",
    "unit3ratio" : 0.0,
    "unit3isstdsell" : "N",
    "unit3isstdord" : "N",
    "unit3isfractqty" : "N",
    "unit3ispackunit" : "N",
    "unit3upc" : 0,
    "unit3upcdesc" : "",
    "unit3wgtperunit" : 0.0000,
    "unit4unit" : "",
    "unit4ratio" : 0.0,
    "unit4isstdsell" : "N",
    "unit4isstdord" : "N",
    "unit4isfractqty" : "N",
    "unit4ispackunit" : "N",
    "unit4upc" : 0,
    "unit4upcdesc" : "",
    "unit4wgtperunit" : 0.0000
  },
  "cost" : {
    "cost1" : 0.0000,
    "priorcost" : 0.0000,
    "futurecost" : 0.9200,
    "poincludeinvendorcost" : "Y",
    "nonstockcostpct" : 0.0,
    "costrangepct" : 20.0
  },
  "relateditemcodes" : null,
  "vendors" : {
    "vendornbr" : 0,
    "vendornbr1" : 371000,
    "vendornbr2" : 0,
    "vendorxrefcd" : "",
    "vendorlistprice" : 0.9200,
    "vendorpriceunit" : "PCS",
    "vendorfob" : "FACTORY",
    "vendordiscpct" : 0.0,
    "vendorroundaccuracy" : 2,
    "vendornetprice" : 0.9200,
    "vendormarkuppct" : 0.0,
    "vendorfreightratecwt" : 0.0,
    "dutypct" : 0.0,
    "leadtime" : 0,
    "vendorlandedbasecost" : 0.9200,
    "vendordiscpct2" : 0.0,
    "vendordiscpct3" : 0.0
  },
  "newVendorSystem" : [ {
    "vendorId" : {
      "id" : 371000
    },
    "vendorOrder" : 1,
    "vendorName" : null,
    "vendorName2" : null,
    "vendorXrefId" : "",
    "vendorListPrice" : 0.9200,
    "vendorNetPrice" : 0.9200,
    "vendorPriceUnit" : "PCS",
    "vendorFob" : "FACTORY",
    "vendorDiscountPct" : 0.0,
    "vendorPriceRoundAccuracy" : 2,
    "vendorMarkupPct" : 0.0,
    "vendorFreightRateCwt" : 0.0,
    "vendorLandedBaseCost" : 0.9200,
    "leadTime" : 0,
    "dutyPct" : 0.0
  } ],
  "newFeature" : null,
  "iconDescription" : {
    "madeInCountry" : null,
    "exteriorProduct" : "Yes",
    "adaAccessibility" : "No",
    "throughColor" : "No",
    "colorBody" : "Yes",
    "inkJet" : "No",
    "glazed" : "No",
    "unglazed" : "Yes",
    "rectifiedEdge" : "No",
    "chiseledEdge" : "No",
    "versaillesPattern" : "No",
    "recycled" : "No",
    "postRecycled" : "No",
    "preRecycled" : "No",
    "leadPoint" : "No",
    "greenFriendly" : "No",
    "coefficientOfFriction" : "No"
  },
  "colorhues" : [ {
    "colorHue" : "BEIGE"
  }, {
    "colorHue" : "GREEN"
  } ]
}, {
  "itemcode" : "MET45848",
  "itemcategory" : "METROSOM",
  "countryorigin" : "",
  "inactivecode" : "N",
  "shadevariation" : "V3",
  "colorcategory" : "GREEN:BROWN",
  "showonwebsite" : "Y",
  "iconsystem" : "NYYNNNNYNNYNNNNNNNNN",
  "itemtypecode" : "#",
  "abccode" : "SO",
  "itemcode2" : "METSOMERSET48",
  "inventoryitemcode" : "",
  "showonalysedwards" : "Y",
  "offshade" : "N",
  "printlabel" : "N",
  "taxclass" : "T",
  "lottype" : "",
  "updatecode" : "CERA-MET",
  "directship" : "N",
  "dropdate" : null,
  "productline" : "CERA",
  "itemgroupnbr" : 1,
  "priorlastupdated" : "2014-03-31",
  "itemdesc" : {
    "fulldesc" : "",
    "itemdesc1" : "4X8-FLOOR METRO SOMERSET-CORDOBA"
  },
  "series" : {
    "seriesname" : "Somerset",
    "seriescolor" : "Cordoba"
  },
  "material" : {
    "materialtype" : "Quarry",
    "materialcategory" : "Tile",
    "materialclass" : "CTSRC",
    "materialstyle" : "FW",
    "materialfeature" : ""
  },
  "dimensions" : {
    "nominallength" : 4.0,
    "nominalwidth" : 8.0,
    "sizeunits" : "E",
    "thickness" : "1/2",
    "thicknessunit" : "E",
    "length" : "4",
    "width" : "8",
    "nominalthickness" : 0.0
  },
  "price" : {
    "listprice" : 4.5800,
    "sellprice" : 4.5800,
    "pricegroup" : "",
    "priceunit" : "S/F",
    "sellpricemarginpct" : 0.0,
    "sellpriceroundaccuracy" : 0,
    "listpricemarginpct" : 0.0,
    "minmarginpct" : 15.0,
    "futuresell" : 4.7800,
    "tempprice" : 0.0000,
    "tempdatefrom" : null,
    "tempdatethru" : null,
    "priorlistprice" : 5.5000,
    "priorsellprice" : 4.5000
  },
  "testSpecification" : {
    "waterabsorption" : null,
    "scratchresistance" : null,
    "frostresistance" : null,
    "chemicalresistance" : null,
    "peiabrasion" : null,
    "scofwet" : null,
    "scofdry" : null,
    "breakingstrength" : null,
    "scratchstandard" : "",
    "breakingstandard" : "",
    "restricted" : "N",
    "warpage" : null,
    "wedging" : null,
    "dcof" : null,
    "thermalshock" : null,
    "bondstrength" : "",
    "greenfriendly" : "N",
    "moh" : 0.0,
    "leadpoint" : "N",
    "preconsummer" : 0.0,
    "posconsummer" : 0.0
  },
  "purchasers" : {
    "purchaser" : "ALICIAB",
    "purchaser2" : ""
  },
  "packaginginfo" : {
    "boxPieces" : 32.0,
    "boxSF" : 7.111111,
    "boxWeight" : 35.0016,
    "palletBox" : 0.0,
    "palletSF" : 0.0,
    "palletWeight" : 0.0
  },
  "notes" : {
    "ponotes" : "",
    "buyernotes" : "371000",
    "invoicenotes" : "",
    "internalnotes" : "SPECIAL ORDER"
  },
  "applications" : {
    "residential" : "FR:WR:CR:SR",
    "lightcommercial" : "FL:WL:CL:SL",
    "commercial" : "FC:WC:CC:SC",
    "residentialList" : null,
    "lightcommercialList" : null,
    "commercialList" : null
  },
  "usage" : [ "FR", "WR", "CR", "SR", "FL", "WL", "CL", "SL", "FC", "WC", "CC", "SC" ],
  "units" : {
    "stdunit" : "S/F",
    "stdratio" : 4.5,
    "ordunit" : "S/F",
    "ordratio" : 4.5,
    "baseunit" : "PCS",
    "baseisstdsell" : "N",
    "baseisstdord" : "N",
    "baseisfractqty" : "N",
    "baseispackunit" : "Y",
    "baseupc" : 0,
    "baseupcdesc" : "",
    "basevolperunit" : 0.0000,
    "basewgtperunit" : 1.0938,
    "unit1unit" : "CTN",
    "unit1ratio" : 32.0,
    "unit1isstdsell" : "N",
    "unit1isstdord" : "N",
    "unit1isfractqty" : "N",
    "unit1ispackunit" : "Y",
    "unit1upc" : 0,
    "unit1upcdesc" : "",
    "unit1wgtperunit" : 0.0000,
    "unit2unit" : "",
    "unit2ratio" : 0.0,
    "unit2isstdsell" : "N",
    "unit2isstdord" : "N",
    "unit2isfractqty" : "N",
    "unit2ispackunit" : "N",
    "unit2upc" : 0,
    "unit2upcdesc" : "",
    "unit2wgtperunit" : 0.0000,
    "unit3unit" : "",
    "unit3ratio" : 0.0,
    "unit3isstdsell" : "N",
    "unit3isstdord" : "N",
    "unit3isfractqty" : "N",
    "unit3ispackunit" : "N",
    "unit3upc" : 0,
    "unit3upcdesc" : "",
    "unit3wgtperunit" : 0.0000,
    "unit4unit" : "S/F",
    "unit4ratio" : 4.5,
    "unit4isstdsell" : "Y",
    "unit4isstdord" : "Y",
    "unit4isfractqty" : "Y",
    "unit4ispackunit" : "N",
    "unit4upc" : 0,
    "unit4upcdesc" : "",
    "unit4wgtperunit" : 0.0000
  },
  "cost" : {
    "cost1" : 0.0000,
    "priorcost" : 0.0000,
    "futurecost" : 0.0000,
    "poincludeinvendorcost" : "Y",
    "nonstockcostpct" : 0.0,
    "costrangepct" : 0.0
  },
  "relateditemcodes" : null,
  "vendors" : {
    "vendornbr" : 0,
    "vendornbr1" : 371000,
    "vendornbr2" : 0,
    "vendorxrefcd" : "",
    "vendorlistprice" : 1.4500,
    "vendorpriceunit" : "S/F",
    "vendorfob" : "FACTORY",
    "vendordiscpct" : 0.0,
    "vendorroundaccuracy" : 2,
    "vendornetprice" : 1.4500,
    "vendormarkuppct" : 0.0,
    "vendorfreightratecwt" : 0.0,
    "dutypct" : 0.0,
    "leadtime" : 0,
    "vendorlandedbasecost" : 0.3222,
    "vendordiscpct2" : 0.0,
    "vendordiscpct3" : 0.0
  },
  "newVendorSystem" : [ {
    "vendorId" : {
      "id" : 371000
    },
    "vendorOrder" : 1,
    "vendorName" : null,
    "vendorName2" : null,
    "vendorXrefId" : "",
    "vendorListPrice" : 1.4500,
    "vendorNetPrice" : 1.4500,
    "vendorPriceUnit" : "S/F",
    "vendorFob" : "FACTORY",
    "vendorDiscountPct" : 0.0,
    "vendorPriceRoundAccuracy" : 2,
    "vendorMarkupPct" : 0.0,
    "vendorFreightRateCwt" : 0.0,
    "vendorLandedBaseCost" : 0.3222,
    "leadTime" : 0,
    "dutyPct" : 0.0
  } ],
  "newFeature" : null,
  "iconDescription" : {
    "madeInCountry" : null,
    "exteriorProduct" : "Yes",
    "adaAccessibility" : "No",
    "throughColor" : "No",
    "colorBody" : "Yes",
    "inkJet" : "No",
    "glazed" : "No",
    "unglazed" : "Yes",
    "rectifiedEdge" : "No",
    "chiseledEdge" : "No",
    "versaillesPattern" : "No",
    "recycled" : "No",
    "postRecycled" : "No",
    "preRecycled" : "No",
    "leadPoint" : "No",
    "greenFriendly" : "No",
    "coefficientOfFriction" : "No"
  },
  "colorhues" : [ {
    "colorHue" : "GREEN"
  }, {
    "colorHue" : "BROWN"
  } ]
}, {
  "itemcode" : "MET101QCR1485",
  "itemcategory" : "METROQUA",
  "countryorigin" : "",
  "inactivecode" : "N",
  "shadevariation" : "V1",
  "colorcategory" : "BEIGE:GREEN",
  "showonwebsite" : "Y",
  "iconsystem" : "NYYNNNNYNNYNNNNNNNNN",
  "itemtypecode" : "#",
  "abccode" : "SO",
  "itemcode2" : "METBASICSQCR1485",
  "inventoryitemcode" : "",
  "showonalysedwards" : "N",
  "offshade" : "N",
  "printlabel" : "N",
  "taxclass" : "T",
  "lottype" : "",
  "updatecode" : "CERA-MET",
  "directship" : "N",
  "dropdate" : null,
  "productline" : "CERA",
  "itemgroupnbr" : 1,
  "priorlastupdated" : "2014-03-31",
  "itemdesc" : {
    "fulldesc" : "",
    "itemdesc1" : "QUARRY-OYSTERBAY SBN CNR 4X8"
  },
  "series" : {
    "seriesname" : "Quarrybasics",
    "seriescolor" : "Oyster Bay"
  },
  "material" : {
    "materialtype" : "Quarry",
    "materialcategory" : "Trim",
    "materialclass" : "CTSRC",
    "materialstyle" : "SFCR",
    "materialfeature" : ""
  },
  "dimensions" : {
    "nominallength" : 4.0,
    "nominalwidth" : 8.0,
    "sizeunits" : "E",
    "thickness" : "5/8",
    "thicknessunit" : "E",
    "length" : "4",
    "width" : "8",
    "nominalthickness" : 0.0
  },
  "price" : {
    "listprice" : 4.2800,
    "sellprice" : 4.2800,
    "pricegroup" : "",
    "priceunit" : "PCS",
    "sellpricemarginpct" : 0.0,
    "sellpriceroundaccuracy" : 0,
    "listpricemarginpct" : 0.0,
    "minmarginpct" : 15.0,
    "futuresell" : 0.0000,
    "tempprice" : 0.0000,
    "tempdatefrom" : null,
    "tempdatethru" : null,
    "priorlistprice" : 5.3800,
    "priorsellprice" : 4.3000
  },
  "testSpecification" : {
    "waterabsorption" : null,
    "scratchresistance" : null,
    "frostresistance" : null,
    "chemicalresistance" : null,
    "peiabrasion" : null,
    "scofwet" : null,
    "scofdry" : null,
    "breakingstrength" : null,
    "scratchstandard" : "",
    "breakingstandard" : "",
    "restricted" : "N",
    "warpage" : null,
    "wedging" : null,
    "dcof" : null,
    "thermalshock" : null,
    "bondstrength" : "",
    "greenfriendly" : "N",
    "moh" : 0.0,
    "leadpoint" : "N",
    "preconsummer" : 0.0,
    "posconsummer" : 0.0
  },
  "purchasers" : {
    "purchaser" : "ALICIAB",
    "purchaser2" : ""
  },
  "packaginginfo" : {
    "boxPieces" : 12.0,
    "boxSF" : 0.0,
    "boxWeight" : 12.960001,
    "palletBox" : 0.0,
    "palletSF" : 0.0,
    "palletWeight" : 0.0
  },
  "notes" : {
    "ponotes" : "",
    "buyernotes" : "371000",
    "invoicenotes" : "",
    "internalnotes" : ""
  },
  "applications" : {
    "residential" : "FR:WR:CR:SR",
    "lightcommercial" : "FL:WL:CL:SL",
    "commercial" : "FC:WC:CC:SC",
    "residentialList" : null,
    "lightcommercialList" : null,
    "commercialList" : null
  },
  "usage" : [ "FR", "WR", "CR", "SR", "FL", "WL", "CL", "SL", "FC", "WC", "CC", "SC" ],
  "units" : {
    "stdunit" : "PCS",
    "stdratio" : 1.0,
    "ordunit" : "PCS",
    "ordratio" : 1.0,
    "baseunit" : "PCS",
    "baseisstdsell" : "Y",
    "baseisstdord" : "Y",
    "baseisfractqty" : "N",
    "baseispackunit" : "Y",
    "baseupc" : 0,
    "baseupcdesc" : "",
    "basevolperunit" : 0.0000,
    "basewgtperunit" : 1.0800,
    "unit1unit" : "CTN",
    "unit1ratio" : 12.0,
    "unit1isstdsell" : "N",
    "unit1isstdord" : "N",
    "unit1isfractqty" : "N",
    "unit1ispackunit" : "Y",
    "unit1upc" : 0,
    "unit1upcdesc" : "",
    "unit1wgtperunit" : 0.0000,
    "unit2unit" : "",
    "unit2ratio" : 0.0,
    "unit2isstdsell" : "N",
    "unit2isstdord" : "N",
    "unit2isfractqty" : "N",
    "unit2ispackunit" : "N",
    "unit2upc" : 0,
    "unit2upcdesc" : "",
    "unit2wgtperunit" : 0.0000,
    "unit3unit" : "",
    "unit3ratio" : 0.0,
    "unit3isstdsell" : "N",
    "unit3isstdord" : "N",
    "unit3isfractqty" : "N",
    "unit3ispackunit" : "N",
    "unit3upc" : 0,
    "unit3upcdesc" : "",
    "unit3wgtperunit" : 0.0000,
    "unit4unit" : "",
    "unit4ratio" : 0.0,
    "unit4isstdsell" : "N",
    "unit4isstdord" : "N",
    "unit4isfractqty" : "N",
    "unit4ispackunit" : "N",
    "unit4upc" : 0,
    "unit4upcdesc" : "",
    "unit4wgtperunit" : 0.0000
  },
  "cost" : {
    "cost1" : 0.0000,
    "priorcost" : 0.0000,
    "futurecost" : 1.4000,
    "poincludeinvendorcost" : "Y",
    "nonstockcostpct" : 0.0,
    "costrangepct" : 20.0
  },
  "relateditemcodes" : null,
  "vendors" : {
    "vendornbr" : 0,
    "vendornbr1" : 371000,
    "vendornbr2" : 0,
    "vendorxrefcd" : "",
    "vendorlistprice" : 1.4000,
    "vendorpriceunit" : "PCS",
    "vendorfob" : "FACTORY",
    "vendordiscpct" : 0.0,
    "vendorroundaccuracy" : 2,
    "vendornetprice" : 1.4000,
    "vendormarkuppct" : 0.0,
    "vendorfreightratecwt" : 0.0,
    "dutypct" : 0.0,
    "leadtime" : 0,
    "vendorlandedbasecost" : 1.4000,
    "vendordiscpct2" : 0.0,
    "vendordiscpct3" : 0.0
  },
  "newVendorSystem" : [ {
    "vendorId" : {
      "id" : 371000
    },
    "vendorOrder" : 1,
    "vendorName" : null,
    "vendorName2" : null,
    "vendorXrefId" : "",
    "vendorListPrice" : 1.4000,
    "vendorNetPrice" : 1.4000,
    "vendorPriceUnit" : "PCS",
    "vendorFob" : "FACTORY",
    "vendorDiscountPct" : 0.0,
    "vendorPriceRoundAccuracy" : 2,
    "vendorMarkupPct" : 0.0,
    "vendorFreightRateCwt" : 0.0,
    "vendorLandedBaseCost" : 1.4000,
    "leadTime" : 0,
    "dutyPct" : 0.0
  } ],
  "newFeature" : null,
  "iconDescription" : {
    "madeInCountry" : null,
    "exteriorProduct" : "Yes",
    "adaAccessibility" : "No",
    "throughColor" : "No",
    "colorBody" : "Yes",
    "inkJet" : "No",
    "glazed" : "No",
    "unglazed" : "Yes",
    "rectifiedEdge" : "No",
    "chiseledEdge" : "No",
    "versaillesPattern" : "No",
    "recycled" : "No",
    "postRecycled" : "No",
    "preRecycled" : "No",
    "leadPoint" : "No",
    "greenFriendly" : "No",
    "coefficientOfFriction" : "No"
  },
  "colorhues" : [ {
    "colorHue" : "BEIGE"
  }, {
    "colorHue" : "GREEN"
  } ]
  
  
* ** Create a new item **  
   curl -H "Accept: application/json" -H "Content-type: application/json" -i --user keymark:JBED -X POST http://localhost:8080/bedlogic/v2/ims -d '{"itemcode":"CRDBARBTEST12","itemcategory2":"ATHENA","itemdesc":{"itemdesc1":"2x2 Athena Mosaic on 12x12 SHT Ash"}}' 
   
* ** Update an item **  	
   curl -H "Accept: application/json" -H "Content-type: application/json" -i --user keymark:JBED -X PUT http://localhost:8080/bedlogic/v2/ims -d '{"itemcode":"CRDBARBTEST","itemcategory":"AT","itemdesc":{"itemdesc1":"update"}}' 
 