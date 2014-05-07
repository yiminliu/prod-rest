BedLogic
=============

Bedrosians Business Logic Service written in Java


## Dev Environment

* Mac OS X v10.7.5 or greater

* VirtualBox with Ubuntu 11.10 x64 Server

* Git


## Setup

* Install and setup VirtualBox with Ubuntu 11.10 x64 Server

https://github.com/beachsidecoders/beachside-dev-central/blob/master/virtualbox/vbx-osx-ubuntu.md

* Install and setup Git on Ubuntu with ssh key access 

http://vcs.bedrosians.com/bedrosians/bedrosians-dev-central/blob/master/vcs/git.md

* Install maven, jdk 6, and curl

```sh
sudo apt-get install -y maven2 openjdk-6-jdk curl

# Choose java-6 jre if it's not already selected
sudo update-alternatives --config java
sudo update-alternatives --config javac
sudo update-alternatives --config javaws
```

* Project Files

```sh
# clone repo
git clone ssh://git@vcs.bedrosians.com:2222/bedrosians/bedlogic.git

cd bedlogic
```

## Run
* Building

```sh
mvn clean package
```

* Running/Stopping

```sh
mvn jetty:run
# To stop, type Ctrl+c on the Jetty shell console
```

* Verify

```sh
# If the curl command is executed from a host other than the server, replace localhost by the dns name or ip of the server.
curl --get http://localhost:8080/bedlogic/rest/hello

# The accounts endpoint. Replace <customer-code> with the desired account. ie 411703
curl --get http://localhost:8080/bedlogic/rest/accounts/<customer-code>

Sample test cases and testing results

**************** Test case 1 **************
curl --basic -u "guest:" --get "http://localhost:8080/bedlogic/rest/products?itemcode=ASCATHASH2040"
{
    "items":[{
    "itemcode":"ASCATHASH2040",
    "itemcategory":"ATHENA",
    "countryorigin":"Italy",
    "inactivecode":"N",
    "itemdesc":{
    "fulldesc":"20x40 Athena Field Tile Ash(Gray)",
    "itemdesc1":"20x40 Athena Field Tile Ash",
    "itemdesc2":""
},
    "series":{
    "seriesname":"Athena",
    "seriescolor":"Ash"
},
    "material":{
    "materialtype":"Porcelain",
    "materialcategory":"Tile",
    "materialclass":"CTSRC",
    "materialstyle":"FW",
    "materialfeature":""
},
    "shadevariation":"V2",
    "colorhues":["GRAY"],
    "dimensions":{
    "nominallength":40.0,
    "nominalwidth":20.0,
    "sizeunits":"E",
    "thickness":"3/8",
    "thicknessunit":"E",
    "length":"39-1/8",
    "width":"19-1/2",
    "nominalthickness":0.0
},
    "price":{
    "listprice":7.2800,
    "sellprice":7.2800,
    "pricegroup":"",
    "priceunit":"S/F",
    "sellpricemarginpct":2.0,
    "sellpriceroundaccuracy":2,
    "listpricemarginpct":0.0,
    "minmarginpct":15.0,
    "futuresell":0.0000,
    "priorsellprice":9.1500,
    "tempprice":7.9800,
    "tempdatefrom":"2014-02-08",
    "tempdatethru":"2014-03-31",
    "priorlistprice":9.3100
},
    "usage":{
    "residential":"FR:WR:CR:SR:PR",
    "lightcommercial":"FL:WL:CL:SL:PL",
    "commercial":"FC:WC:CC:SC:PC"
},
    "testSpecification":{
    "waterabsorption":0.04,
    "scratchresistance":0.0,
    "frostresistance":"P",
    "chemicalresistance":"P",
    "peiabrasion":0.0,
    "scofwet":0.6,
    "scofdry":0.6,
    "breakingstrength":0,
    "scratchstandard":"",
    "breakingstandard":"",
    "restricted":"N",
    "warpage":" ",
    "wedging":" ",
    "dcof":0.56,
    "thermalshock":" ",
    "bondstrength":"",
    "greenfriendly":"N",
    "moh":0.0,
    "leadpoint":"N",
    "preconsummer":0.0,
    "posconsummer":0.0
},
    "purchasers":{
    "purchaser":"ALICIAB",
    "purchaser2":"GFIL"
},
    "packaginginfo":{
    "stdunit":"S/F",
    "stdratio":0.19,
    "ordunit":"S/M",
    "ordratio":2.0,
    "baseunit":"PCS",
    "baseisstdsell":"N",
    "baseisstdord":"N",
    "baseisfractqty":"N",
    "baseispackunit":"Y",
    "baseupc":0,
    "baseupcdesc":"",
    "basevolperunit":0.0000,
    "basewgtperunit":29.6000,
    "unit1unit":"CTN",
    "unit1ratio":3.0,
    "unit1isstdsell":"N",
    "unit1isstdord":"N",
    "unit1isfractqty":"N",
    "unit1ispackunit":"Y",
    "unit1upc":0,
    "unit1upcdesc":"",
    "unit1wgtperunit":89.4000,
    "unit2unit":"PLT",
    "unit2ratio":81.0,
    "unit2isstdsell":"N",
    "unit2isstdord":"N",
    "unit2isfractqty":"N",
    "unit2ispackunit":"Y",
    "unit2upc":0,
    "unit2upcdesc":"",
    "unit2wgtperunit":2430.0000,
    "unit3unit":"S/M",
    "unit3ratio":2.0,
    "unit3isstdsell":"N",
    "unit3isstdord":"Y",
    "unit3isfractqty":"Y",
    "unit3ispackunit":"N",
    "unit3upc":0,
    "unit3upcdesc":"",
    "unit3wgtperunit":0.0000,
    "unit4unit":"S/F",
    "unit4ratio":0.19,
    "unit4isstdsell":"Y",
    "unit4isstdord":"N",
    "unit4isfractqty":"Y",
    "unit4ispackunit":"N",
    "unit4upc":0,
    "unit4upcdesc":"",
    "unit4wgtperunit":0.0000
},
    "notes":{
    "ponotes":"",
    "notes1":"PRICING FOR FIRST 8 CONTAINERS",
    "notes2":"AFTER THAT COSTI UP TO 18.00",
    "notes3":""
},
    "newNoteSystem":[{
    "noteType":"buyer",
    "note":"PRICING FOR FIRST 8 CONTAINERS",
    "createdDate":null,
    "lastModifiedDate":null
},
    {
    "noteType":"additional",
    "note":"AFTER THAT COSTI UP TO 18.00",
    "createdDate":null,
    "lastModifiedDate":null
}],
    "productline":"",
    "iconsystem":"NNNNNNNNNNNNNNNNNNNN",
    "newIconSystem":null,
    "relateditemcodes":null,
    "showonwebsite":"Y",
    "itemtypecd":"#",
    "abccd":"C",
    "itemcd2":"",
    "inventoryItemcd":"",
    "showonalysedwards":"N",
    "offshade":"N",
    "printlabel":" ",
    "taxclass":"T",
    "lottype":"",
    "updatecd":"CERA-CRD",
    "directship":" ",
    "dropdate":null,
    "itemgroupnbr":0,
    "priorlastupdated":"2014-03-31",
    "imsNewFeature":null,
    "newVendorSystem":[{
    "vendorOrder":1,
    "vendorName":null,
    "vendorName2":null,
    "vendorXrefId":"AT140R",
    "vendorListPrice":18.0000,
    "vendorNetPrice":18.0000,
    "vendorPriceUnit":"S/M",
    "vendorFob":"",
    "vendorDiscountPct":0.0,
    "vendorPriceRoundAccuracy":2,
    "vendorMarkupPct":0.0,
    "vendorFreightRateCwt":0.0,
    "vendorLandedBaseCost":null,
    "leadTime":60,
    "dutyPct":0.0,
    "vendorId":134585
}],
    "vendors":{
    "vendornbr":0,
    "vendornbr1":134585,
    "vendornbr2":0,
    "vendornbr3":0,
    "vendorxrefcd":"AT140R",
    "vendorlistprice":18.0000,
    "vendorpriceunit":"S/M",
    "vendorfob":"",
    "vendordiscpct":0.0,
    "vendorroundaccuracy":2,
    "vendornetprice":18.0000,
    "vendormarkuppct":0.0,
    "vendorfreightratecwt":0.0,
    "dutypct":0.0,
    "leadtime":60,
    "vendorLandedBaseCost":9.0045,
    "vendordiscpct2":0.0,
    "vendordiscpct3":0.0
},
    "cost":{
    "cost1":0.0000,
    "priorcost":0.0000,
    "priorcost1":0.0000,
    "futurecost":0.0000,
    "futurecost1":0.0000,
    "poincludeinvendorcost":"Y",
    "nonstockcostpct":0.0,
    "costrangepct":0.0
},
    "priorVendor":null
}]
}

*********** Test case 2 **********
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?colorhues=White&size=12X9"
{
    "items":[{
    "itemcode":"ONXMANCRMCANE",
    "itemcategory":"TRIMONX",
    "countryorigin":"Turkey",
    "inactivecode":"N",
    "itemdesc":{
    "fulldesc":"Manisa Cream Onyx 3/4x9/16x12 Cane",
    "itemdesc1":"Manisa Cream 3/4x9/16x12 Cane",
    "itemdesc2":""
},
    "series":{
    "seriesname":"Manisa Cream",
    "seriescolor":"Manisa Cream"
},
    "material":{
    "materialtype":"Decorative",
    "materialcategory":"Trim",
    "materialclass":"STNTL",
    "materialstyle":"CN",
    "materialfeature":""
},
    "shadevariation":"V2",
    "colorhues":["WHITE"],
    "dimensions":{
    "nominallength":12.0,
    "nominalwidth":9.0,
    "sizeunits":"E",
    "thickness":"3/4",
    "thicknessunit":"E",
    "length":"12",
    "width":"9/16",
    "nominalthickness":0.0
},
    "price":{
    "listprice":9.6800,
    "sellprice":9.6800,
    "pricegroup":"",
    "priceunit":"PCS",
    "sellpricemarginpct":2.0,
    "sellpriceroundaccuracy":2,
    "listpricemarginpct":0.0,
    "minmarginpct":15.0,
    "futuresell":0.0000,
    "priorsellprice":9.6500,
    "tempprice":0.0000,
    "tempdatefrom":null,
    "tempdatethru":null,
    "priorlistprice":0.0000
},
    "usage":{
    "residential":"FR:WR:CR:SR",
    "lightcommercial":"FL:WL:CL:SL",
    "commercial":"FC:WC:CC:SC"
},
    "testSpecification":{
    "waterabsorption":0.0,
    "scratchresistance":0.0,
    "frostresistance":"",
    "chemicalresistance":"",
    "peiabrasion":0.0,
    "scofwet":0.0,
    "scofdry":0.0,
    "breakingstrength":0,
    "scratchstandard":"",
    "breakingstandard":"",
    "restricted":"N",
    "warpage":" ",
    "wedging":" ",
    "dcof":0.0,
    "thermalshock":" ",
    "bondstrength":"",
    "greenfriendly":"N",
    "moh":0.0,
    "leadpoint":"N",
    "preconsummer":0.0,
    "posconsummer":0.0
},
    "purchasers":{
    "purchaser":"LANAT",
    "purchaser2":"LANAT"
},
    "packaginginfo":{
    "stdunit":"PCS",
    "stdratio":1.0,
    "ordunit":"PCS",
    "ordratio":1.0,
    "baseunit":"PCS",
    "baseisstdsell":"Y",
    "baseisstdord":"Y",
    "baseisfractqty":"N",
    "baseispackunit":"Y",
    "baseupc":0,
    "baseupcdesc":"",
    "basevolperunit":0.0000,
    "basewgtperunit":0.4000,
    "unit1unit":"",
    "unit1ratio":0.0,
    "unit1isstdsell":"N",
    "unit1isstdord":"N",
    "unit1isfractqty":"N",
    "unit1ispackunit":"Y",
    "unit1upc":0,
    "unit1upcdesc":"",
    "unit1wgtperunit":0.0000,
    "unit2unit":"",
    "unit2ratio":0.0,
    "unit2isstdsell":"N",
    "unit2isstdord":"N",
    "unit2isfractqty":"N",
    "unit2ispackunit":"N",
    "unit2upc":0,
    "unit2upcdesc":"",
    "unit2wgtperunit":0.0000,
    "unit3unit":"",
    "unit3ratio":0.0,
    "unit3isstdsell":"N",
    "unit3isstdord":"N",
    "unit3isfractqty":"N",
    "unit3ispackunit":"N",
    "unit3upc":0,
    "unit3upcdesc":"",
    "unit3wgtperunit":0.0000,
    "unit4unit":"",
    "unit4ratio":0.0,
    "unit4isstdsell":"N",
    "unit4isstdord":"N",
    "unit4isfractqty":"N",
    "unit4ispackunit":"N",
    "unit4upc":0,
    "unit4upcdesc":"",
    "unit4wgtperunit":0.0000
},
    "notes":null,
    "newNoteSystem":[],
    "productline":"",
    "iconsystem":"NNNNNNNNNNNNNNNNNNNN",
    "newIconSystem":null,
    "relateditemcodes":null,
    "showonwebsite":"Y",
    "itemtypecd":"#",
    "abccd":"B",
    "itemcd2":"ONXMANCRMCANE",
    "inventoryItemcd":"",
    "showonalysedwards":"N",
    "offshade":"N",
    "printlabel":" ",
    "taxclass":"T",
    "lottype":"",
    "updatecd":"STON-MAR",
    "directship":" ",
    "dropdate":null,
    "itemgroupnbr":0,
    "priorlastupdated":"2014-03-31",
    "imsNewFeature":null,
    "newVendorSystem":[],
    "vendors":{
    "vendornbr":0,
    "vendornbr1":0,
    "vendornbr2":0,
    "vendornbr3":0,
    "vendorxrefcd":"",
    "vendorlistprice":2.9500,
    "vendorpriceunit":"PCS",
    "vendorfob":"FACTORY",
    "vendordiscpct":0.0,
    "vendorroundaccuracy":2,
    "vendornetprice":2.9500,
    "vendormarkuppct":0.0,
    "vendorfreightratecwt":0.0,
    "dutypct":0.0,
    "leadtime":0,
    "vendorLandedBaseCost":2.9500,
    "vendordiscpct2":0.0,
    "vendordiscpct3":0.0
},
    "cost":{
    "cost1":0.0000,
    "priorcost":0.0000,
    "priorcost1":0.0000,
    "futurecost":0.0000,
    "futurecost1":0.0000,
    "poincludeinvendorcost":"Y",
    "nonstockcostpct":0.0,
    "costrangepct":0.0
},
    "priorVendor":null
}]
}

More test cases:
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?itemcode=CRDBARBRU440"
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?category=BRECCIA"
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?matcategory=Tool"
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?origin=USA"
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?color=Beige&origin=China&category=BRECCIA"
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?lengthmax=2&widthmax=2"
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?lengthmax=2&lengthmin=1"
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?size=12X9,10X8,4X8"
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?color=red"
curl --basic -u "keymark:password" --get "http://localhost:8080/bedlogic/rest/products?status=best"

curl --basic -u "keymark:password" --put "http://localhost:8080/bedlogic/rest/products?itemcode=CRDBARBRU440,color=Red"

curl -H "Accept: application/json" -H "Content-type: application/json" -i --user keymark:password -X POST -d '{"itemcd":"CRDBARBRU422", "color":"Red","description":"test description","origin":"USA","price":"22.5"}' http://localhost:8080/bedlogic/rest/products



## Acknowledgements
BedLogic makes use of the following third-party open source libraries:

* Jetty (http://www.eclipse.org/jetty) - HTTP server
* Jersey (https://jersey.java.net) - JAX-RS REST implementation
* Spring Framework (http://projects.spring.io/spring-framework) - Application features: Dependency Injection, IOC, Data Access via ODBC, and spring-test module for integration testing
* JUnit (http://junit.org) - Testing framework
* Mockito (http://code.google.com/p/mockito/) - Mocking framework
* SLF4J (http://www.slf4j.org) and Logback (http://logback.qos.ch) - Logging api via SLF4J, and implementation via Logback.
