Ims Service Requirements
==============================

---

Overview
--------
The ims service manages Bedrosians item information. The service will be used to search and manage the item information.
The ims service provides item search, creation, update and deletion services.

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
   
 {
  "ims":[{
    "item":{
    "itemcode":"ENQQNT154730",
    "itemcategory":"SLAB3QNT",
    "countryorigin":"India",
    "inactivecode":"N",
    "shadevariation":"V1",
    "colorcategory":"BEIGE",
    "showonwebsite":"Y",
    "iconsystem":"NNNNNNNNNNNNNNNNNNNN",
    "itemtypecode":"#",
    "abccode":"D",
    "itemcode2":"QNTHAZELNUT3",
    "inventoryitemcode":"",
    "showonalysedwards":"N",
    "offshade":"N",
    "printlabel":"N",
    "taxclass":"T",
    "lottype":"S",
    "updatecode":"STON-SLAB",
    "directship":"N",
    "dropdate":null,
    "productline":"STON",
    "itemgroupnbr":1,
    "priorlastupdated":"2014-04-14",
    "itemdesc":{
        "fulldesc":"Quantra Hazelnut 3cm Slab",
        "itemdesc1":"QUANTRA 3CM # 1547 HAZELNUT"
    },
    "series":{
        "seriesname":"Builder",
        "seriescolor":"Hazelnut"
    },
    "material":{
        "materialtype":"Engineered Quartz",
        "materialcategory":"Slab",
        "materialclass":"SLABS",
        "materialstyle":"SL3",
        "materialfeature":"PL"
    },
    "dimensions":{
        "nominallength":120.0,
        "nominalwidth":55.0,
        "sizeunits":"E",
        "thickness":"3",
        "thicknessunit":"M",
        "length":"120",
        "width":"55",
        "nominalthickness":0.0
    },
    "price":{
        "listprice":25.5800,
        "sellprice":25.5800,
        "pricegroup":"2",
        "priceunit":"S/F",
        "sellpricemarginpct":2.0,
        "sellpriceroundaccuracy":2,
        "listpricemarginpct":0.0,
        "minmarginpct":15.0,
        "futuresell":27.7800,
        "tempprice":0.0000,
        "tempdatefrom":null,
        "tempdatethru":null,
        "priorlistprice":32.3000,
        "priorsellprice":25.8500
     },
    "testSpecification":{
        "waterabsorption":0.3,
        "scratchresistance":139.0,
        "frostresistance":null,
        "chemicalresistance":null,
        "peiabrasion":0.0,
        "scofwet":0.0,
        "scofdry":0.0,
        "breakingstrength":0,
        "scratchstandard":"EN 14617-4",
        "breakingstandard":"",
        "restricted":"N",
        "warpage":null,
        "wedging":null,
        "dcof":0.0,
        "thermalshock":null,
        "bondstrength":"",
        "greenfriendly":"N",
        "moh":7.0,
        "leadpoint":"N",
        "preconsummer":0.0,
        "posconsummer":0.0
     },
    "purchasers":{
        "purchaser":"PATA",
        "purchaser2":""
    },
    "packaginginfo":{
        "boxPieces":0.0,
        "boxSF":0.0,
        "boxWeight":0.0,
        "palletBox":0.0,
        "palletSF":367.696,
        "palletWeight":5774.997
     },
    "notes":{
        "ponotes":"ATTN VENDOR: PLEASE SEND 50-100 EA 4X6",
        "buyernotes":"",
        "invoicenotes":"FOR WARRANTY REGISTRATION GO TO  HTTP://WWW.QUANTRA.IN/LIFE-WITH-QUANTRA/WARRANTY-AND-REGISTRATION/",
        "internalnotes":""
    },
    "applications":{
        "residential":"FR:WR:CR:SR",
        "lightcommercial":"FL:WL:CL:SL",
        "commercial":"FC:WC:CC:SC",
        "residentialList":null,
        "lightcommercialList":null,
        "commercialList":null
    },
    "usage":["FR","WR","CR","SR","FL","WL","CL","SL","FC","WC","CC","SC"],
    "units":{
    "stdunit":"S/F",
    "stdratio":1.0,
    "ordunit":"S/M",
    "ordratio":10.76,
    "baseunit":"S/F",
    "baseisstdsell":"Y",
    "baseisstdord":"N",
    "baseisfractqty":"Y",
    "baseispackunit":"Y",
    "baseupc":0,
    "baseupcdesc":"",
    "basevolperunit":0.0000,
    "basewgtperunit":15.7059,
    "unit1unit":"",
    "unit1ratio":0.0,
    "unit1isstdsell":"N",
    "unit1isstdord":"N",
    "unit1isfractqty":"N",
    "unit1ispackunit":"N",
    "unit1upc":0,
    "unit1upcdesc":"",
    "unit1wgtperunit":0.0000,
    "unit2unit":"PLT",
    "unit2ratio":367.696,
    "unit2isstdsell":"N",
    "unit2isstdord":"N",
    "unit2isfractqty":"N",
    "unit2ispackunit":"Y",
    "unit2upc":0,
    "unit2upcdesc":"",
    "unit2wgtperunit":0.0000,
    "unit3unit":"S/M",
    "unit3ratio":10.76,
    "unit3isstdsell":"N",
    "unit3isstdord":"Y",
    "unit3isfractqty":"Y",
    "unit3ispackunit":"N",
    "unit3upc":0,
    "unit3upcdesc":"",
    "unit3wgtperunit":0.0000,
    "unit4unit":"S/F",
    "unit4ratio":1.0,
    "unit4isstdsell":"N",
    "unit4isstdord":"N",
    "unit4isfractqty":"Y",
    "unit4ispackunit":"N",
    "unit4upc":0,
    "unit4upcdesc":"",
    "unit4wgtperunit":0.0000
    },
        "cost":{
        "cost1":0.0000,
        "priorcost":0.0000,
        "futurecost":0.0000,
        "poincludeinvendorcost":"Y",
        "nonstockcostpct":0.0,
        "costrangepct":0.0
    },
    "relateditemcodes":{
        "similaritemcd1":"",
        "similaritemcd2":"",
        "similaritemcd3":"",
        "similaritemcd4":"",
        "similaritemcd5":"",
        "similaritemcd6":"",
        "similaritemcd7":"                  "
    },
    "vendors":{
        "vendornbr":0,
        "vendornbr1":0,
        "vendornbr2":0,
        "vendorxrefcd":"",
        "vendorlistprice":96.8400,
        "vendorpriceunit":"S/M",
        "vendorfob":"FACTORY",
        "vendordiscpct":0.0,
        "vendorroundaccuracy":2,
        "vendornetprice":96.8400,
        "vendormarkuppct":0.0,
        "vendorfreightratecwt":0.0,
        "dutypct":0.0,
        "leadtime":0,
        "vendorlandedbasecost":9.0000,
        "vendordiscpct2":0.0,
        "vendordiscpct3":0.0
    },
    "newVendorSystem":[{
        "vendorId":{
             "id":0
        },
        "vendorOrder":1,
        "vendorName":null,
        "vendorName2":null,
        "vendorXrefId":"",
        "vendorListPrice":96.8400,
        "vendorNetPrice":96.8400,
        "vendorPriceUnit":"S/M",
        "vendorFob":"FACTORY",
        "vendorDiscountPct":0.0,
        "vendorPriceRoundAccuracy":2,
        "vendorMarkupPct":0.0,
        "vendorFreightRateCwt":0.0,
        "vendorLandedBaseCost":9.0000,
        "leadTime":0,
        "dutyPct":0.0
     }],
    "newFeature":null,
    "iconDescription":null,
    "colorhues":[{
        "colorHue":"BEIGE"
        }]
    }
}]
}

* ** Search ims with item code **  
   curl --basic -u "guest:" --get "http://localhost:8080/bedlogic/v2/ims/AECBUB218NR"

   Result
   
 {
  "ims":{
    "item":{
    "itemcode":"AECBUB218NR",
    "itemcategory":"BUBBLI",
    "countryorigin":"China",
    "inactivecode":"N",
    "shadevariation":"V2",
    "colorcategory":"ALMOND:BEIGE:GOLD:GRAY:RUST",
    "colorhues":["ALMOND",
    "BEIGE",
    "GOLD",
    "GRAY",
    "RUST"],
    "showonwebsite":"N",
    "iconsystem":"NNNNNNNNNNNNNNNNNNNN",
    "itemtypecd":"#",
    "abccd":"B",
    "itemcd2":"AECBUBNR",
    "inventoryitemcd":"",
    "showonalysedwards":"Y",
    "offshade":"N",
    "printlabel":"N",
    "taxclass":"T",
    "lottype":"",
    "updatecd":"CERA-TCR",
    "directship":"N",
    "dropdate":null,
    "productline":"CERA",
    "itemgroupnbr":1,
    "priorlastupdated":"2014-06-10",
    "itemdesc":{
    "fulldesc":"Non Iridescent- Silky Negliee",
    "itemdesc1":"BUBBLICIOUS NON IRIDESCENT-SILKY"
},
    "series":{
    "seriesname":"Bubblicious",
    "seriescolor":"Silky Negligee"
},
    "material":{
    "materialtype":"Glass",
    "materialcategory":"Tile",
    "materialclass":"DECOR",
    "materialstyle":"WL",
    "materialfeature":""
},
    "dimensions":{
    "nominallength":12.0,
    "nominalwidth":12.0,
    "sizeunits":"E",
    "thickness":"1/8",
    "thicknessunit":"E",
    "length":"12",
    "width":"12",
    "nominalthickness":0.0
},
    "price":{
    "listprice":85.2900,
    "sellprice":85.2900,
    "pricegroup":"",
    "priceunit":"S/F",
    "sellpricemarginpct":2.0,
    "sellpriceroundaccuracy":2,
    "listpricemarginpct":0.0,
    "minmarginpct":15.0,
    "futuresell":85.2900,
    "tempprice":0.0000,
    "tempdatefrom":null,
    "tempdatethru":null,
    "priorlistprice":85.3100,
    "priorsellprice":68.2500
},
    "testSpecification":{
    "waterabsorption":0.17,
    "scratchresistance":null,
    "frostresistance":null,
    "chemicalresistance":"P",
    "peiabrasion":null,
    "scofwet":null,
    "scofdry":null,
    "breakingstrength":null,
    "scratchstandard":"",
    "breakingstandard":"",
    "restricted":"N",
    "warpage":null,
    "wedging":null,
    "dcof":null,
    "thermalshock":null,
    "bondstrength":"",
    "greenfriendly":"N",
    "moh":0.0,
    "leadpoint":"N",
    "preconsummer":0.0,
    "posconsummer":0.0
},
    "relateditemcodes":null,
    "purchasers":{
    "purchaser":"FELICIA",
    "purchaser2":"MELISSAM"
},
    "packaginginfo":{
    "boxPieces":20.0,
    "boxSF":20.0,
    "boxWeight":24.0,
    "palletBox":0.0,
    "palletSF":0.0,
    "palletWeight":0.0
},
    "notes":null,
    "applications":{
    "residential":"WR:SR:PR",
    "lightcommercial":"WL:SL",
    "commercial":"WC:SC"
},
    "usage":["WR",
    "SR",
    "PR",
    "WL",
    "SL",
    "WC",
    "SC"],
    "units":{
    "stdunit":"S/F",
    "stdratio":1.0,
    "ordunit":"S/F",
    "ordratio":1.0,
    "baseunit":"PCS",
    "baseisstdsell":"N",
    "baseisstdord":"N",
    "baseisfractqty":"N",
    "baseispackunit":"Y",
    "baseupc":0,
    "baseupcdesc":"",
    "basevolperunit":0.000000,
    "basewgtperunit":1.200000,
    "unit1unit":"CTN",
    "unit1ratio":20.0,
    "unit1isstdsell":"N",
    "unit1isstdord":"N",
    "unit1isfractqty":"N",
    "unit1ispackunit":"Y",
    "unit1upc":0,
    "unit1upcdesc":"",
    "unit1wgtperunit":23.800000,
    "unit2unit":"",
    "unit2ratio":0.0,
    "unit2isstdsell":"N",
    "unit2isstdord":"N",
    "unit2isfractqty":"N",
    "unit2ispackunit":"N",
    "unit2upc":0,
    "unit2upcdesc":"",
    "unit2wgtperunit":0.000000,
    "unit3unit":"",
    "unit3ratio":0.0,
    "unit3isstdsell":"N",
    "unit3isstdord":"N",
    "unit3isfractqty":"N",
    "unit3ispackunit":"N",
    "unit3upc":0,
    "unit3upcdesc":"",
    "unit3wgtperunit":0.000000,
    "unit4unit":"S/F",
    "unit4ratio":1.0,
    "unit4isstdsell":"Y",
    "unit4isstdord":"Y",
    "unit4isfractqty":"N",
    "unit4ispackunit":"N",
    "unit4upc":0,
    "unit4upcdesc":"",
    "unit4wgtperunit":0.000000,
    "default":false
},
    "cost":{
    "cost1":0.0000,
    "priorcost":0.0000,
    "futurecost":0.0000,
    "poincludeinvendorcost":"Y",
    "nonstockcostpct":0.0,
    "costrangepct":0.0
},
    "vendors":{
    "vendornbr":0,
    "vendornbr1":324495,
    "vendornbr2":0,
    "vendorxrefcd":"",
    "vendorlistprice":5.0850,
    "vendorpriceunit":"S/F",
    "vendorfob":"FACTORY",
    "vendordiscpct":0.0,
    "vendorroundaccuracy":2,
    "vendornetprice":5.0900,
    "vendormarkuppct":0.0,
    "vendorfreightratecwt":0.0,
    "dutypct":0.0,
    "leadtime":0,
    "vendorlandedbasecost":5.090000,
    "vendordiscpct2":0.0,
    "vendordiscpct3":0.0,
    "default":false
},
    "newVendorSystem":[{
    "vendorOrder":1,
    "vendorName":null,
    "vendorName2":null,
    "vendorXrefId":"",
    "vendorListPrice":5.0850,
    "vendorNetPrice":5.0900,
    "vendorPriceUnit":"S/F",
    "vendorFob":"FACTORY",
    "vendorDiscountPct":0.0,
    "vendorPriceRoundAccuracy":2,
    "vendorMarkupPct":0.0,
    "vendorFreightRateCwt":0.0,
    "vendorLandedBaseCost":5.090000,
    "leadTime":0,
    "dutyPct":0.0,
    "vendorId":324495
}],
    "iconDescription":null,
    "imsNewFeature":null
}
}

* ** Create a new item **  
   curl -H "Accept: application/json" -H "Content-type: application/json" -i --user keymark:JBED -X POST http://localhost:8080/bedlogic/v2/ims -d '{"itemcode":"CRDBARBTEST12","itemcategory2":"ATHENA","itemdesc":{"itemdesc1":"2x2 Athena Mosaic on 12x12 SHT Ash"}}' 
   
* ** Update an item **  	
   curl -H "Accept: application/json" -H "Content-type: application/json" -i --user keymark:JBED -X PUT http://localhost:8080/bedlogic/v2/ims -d '{"itemcode":"CRDBARBTEST","itemcategory":"AT","itemdesc":{"itemdesc1":"update"}}' 
 