<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <title>Ims Menu</title>
  <style type="text/css"> 
    table.category { 
        border-bottom: dotted 1px  blue;
     }
	td.narrow 
     { 
        width:10px;
     }
  </style>
</head>
<body>
   <div class="container">
      <c:choose>
         <c:when test="${empty item}">
             <div class="container">No Item Found</div>
         </c:when>
         <c:otherwise>
             <div class="container" style="text-align: middle;"><h2>Item Information for Item Code: <span style="color : red">${item.itemcode}</span></h2></div>
         </c:otherwise>
      </c:choose>
      <br/>
      <p></p>
      
      <div class="container">
         <div id="main-content">
             <div class="span-18 colborder">
                 <c:if test="${!empty item}">
                    <div class="container" style="color:GREEN"><h3>General Information</h3></div>
                    <table border="1">
                       <tr>
                         <!--<th>Item Code</th>-->
                         <th>Item Description</th>
                         <th>Category</th>
                         <th>Serious Name</th>
                         <th>Active Status</th>
                         <th>Origin</th>
                         <th>Shade Variation</th>
                         <th>MPS</th>
                         <th>Grade</th>
                         <th>Status</th>
                         <th>Warranty</th>
                       </tr>
                       <tr>
                          <!--<td style="color : red">${item.itemcode}</td>-->
                          <td>${item.itemdesc.itemdesc1}</td>
                          <td>${item.itemcategory}</td>
                          <td>${item.series.seriesname}</td>
                          <c:choose>
                             <c:when test="${item.inactivecode == 'N'}">
                                <td><c:out value="Active"/></td>
                             </c:when>
                             <c:otherwise>
                                <td><c:out value="Inative"/></td>
                             </c:otherwise>
                         </c:choose>
                         <td>${item.countryorigin}</td>
                         <td>${item.shadevariation}</td>
                         <td>${item.newFeature.mpsCode}</td>
                         <td>${item.newFeature.grade}</td>
                         <td>${item.newFeature.status}</td>
                         <td>${item.newFeature.warranty}</td>
                       </tr>
                    </table>
                    <div class="container" style="color:GREEN"> <h3>Material Information</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>Materialtype</th>
                         <th>Materialcategory</th>
                         <th>Materialclass</th>
                         <th>Materialstyle</th>
                         <th>Materialfeature</th>
                       </tr>
                       <tr>
                          <td >${item.material.materialtype}</td>
                          <td >${item.material.materialcategory}</td>
                          <td >${item.material.materialclass}</td>
                          <td >${item.material.materialstyle}</td>
                          <td >${item.material.materialfeature}</td>
                       </tr>
                    </table>
                    <div class="container" style="color:GREEN"> <h3>Color Hues</h3></div>
                    <table border="1" cellspacing="2">
                      <tr>
                        <c:forEach var="color" items="${item.colorhues}" varStatus="status">
                          <td>${color.colorHue}</td>
                       </c:forEach>
                       </tr>
                    </table>
                    <div class="container" style="color:GREEN"> <h3>Color Category</h3></div>
                    <table border="1" cellspacing="2">
                        <tr>
                          <th>Color Category</th>
                        </tr>
                        <tr>
                          <td>${item.colorcategory}</td>
                        </tr>  
                    </table>
                    <div class="container" style="color:Green"> <h3>Applications</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>Edge</th>
                         <th>Body</th>
                         <th>Design Look</th>
                         <th>Design Styles</th>
                         <th>Surface Type</th>
                         <th>Surface finish</th>
                         <th>Surface Application</th>
                         <th>Recommended Grout Joint Min</th>
                         <th>Recommended Grout Joint Max</th>
                       </tr>
                       <tr>
                         <td>${item.newFeature.edge}</td>
                         <td>${item.newFeature.body}</td>
                         <td>${item.newFeature.designLook}</td>
                         <td>${item.newFeature.designStyle}</td>
                         <td>${item.newFeature.surfaceType}</td>
                         <td>${item.newFeature.surfaceFinish}</td>
                         <td>${item.newFeature.surfaceApplication}</td>
                         <td>${item.newFeature.recommendedGroutJointMin}</td>
                         <td>${item.newFeature.recommendedGroutJointMax}</td>
                       </tr>
                    </table>
                   
                    <div class="container" style="color:Green"> <h3>Web</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>Show on Bedrosians</th>
                         <th>Show on AlysEdwards</th>
                         <th>Show on both</th>
                       </tr>
                       <tr>
                         <td>${item.showonwebsite}</td>
                         <td>${item.showonalysedwards}</td>
                         <td>
                            <c:choose>
                              <c:when test="${item.showonwebsite == 'Y' && item.showonalysedwards == 'Y'}">
                                <c:out value = 'Y'/>
                                </c:when>
                              <c:otherwise>
                                <c:out value = 'N'/>
                              </c:otherwise> 
                            </c:choose>  
                         </td>                 
                       </tr>
                    </table>
                    
                    <div class="container" style="color:GREEN"> <h3>Dimensions</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>Length</th>
                         <th>Width</th>
                         <th>Thickness</th>
                         <th>Nominal Length</th>
                         <th>Nominal Width</th>
                         <th>Nominal Thickness</th>
                         <th>Size Units</th>
                         <th>Thickness Unit</th>
                       </tr>
                       <tr>
                          <td>${item.dimensions.length}</td>
                          <td>${item.dimensions.width}</td>
                          <td>${item.dimensions.thickness}</td>
                          <td>${item.dimensions.nominallength}</td>
                          <td>${item.dimensions.nominalwidth}</td>
                          <td>${item.dimensions.nominalthickness}</td>
                          <td>${item.dimensions.sizeunits}</td>
                          <td>${item.dimensions.thicknessunit}</td>
                        </tr>
                    </table>
                   
                    <div class="container" style="color:GREEN"> <h3>Pricing Information</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>MSRP</th>
                         <c:if test="${item.price.tempprice > 0.0000}">
                            <th>Special Price Unit</th>
                            <th>Special Price Rate</th>
                            <th>Special Price Start Date</th>
                            <th>Special Price End Date</th>
                            <th>Type</th>
                         </c:if>   
                       </tr>
                       <tr>
                          <td>${item.price.sellprice}</td>
                          <c:if test="${item.price.tempprice > 0.0000}">
                             <td></td>
                             <td>${item.price.tempprice}</td>
                             <td>${item.price.tempdatefrom}</td>
                             <td>${item.price.tempdatethru}</td>
                           </c:if>   
                       </tr>
                    </table>
                     <div class="container" style="color:Green"> <h3>Buyers</h3></div>
                     <table border="1" cellspacing="2">
                       <tr>
                         <th>Buyer1</th>
                         <th>Buyer2</th>
                       </tr>
                       <tr>
                         <td>${item.purchasers.purchaser}</td>
                         <td>${item.purchasers.purchaser2}</td>
                       </tr>
                    </table>
                    
                    <div class="container" style="color:GREEN"> <h3>Notes</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>PO Notes</th>
                         <th>Buyer Notes</th>
                         <th>Invoice Notes</th>
                         <th>Internal Notes</th>
                       </tr>
                       <tr>
                          <td>${item.notes.ponotes}</td>
                          <td>${item.notes.buyernotes}</td>
                          <td>${item.notes.invoicenotes}</td>
                          <td>${item.notes.internalnotes}</td>
                        </tr>
                    </table>
    
                    <div class="container" style="color:Green"> <h3>Usage</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>Residential</th>
                         <th>Light Commercial</th>
                         <th>Commercial</th>
                       </tr>
                       <tr>
                         <td>${item.applications.residential}</td>
                         <td>${item.applications.lightcommercial}</td>
                         <td>${item.applications.commercial}</td>
                       </tr>
                    </table>
                    
                    
	                <div class="container" style="color:Green"> <h3>Test Specifications</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>waterabsorption</th>
                         <th>scratchresistance</th>
                         <th>frostresistance</th>
                         <th>chemicalresistance</th>
                         <th>peiabrasion</th>
                         <th>scofwet</th>
                         <th>scofdry</th>
                         <th>breakingstrength</th>
                         <th>scratchstandard</th>
                         <th>breakingstandard</th>
                         <th>warpage</th>
                         <th>wedging</th>
                         <th>dcof</th>
                         <th>thermalshock</th>
                         <th>bondstrength</th>
                         <th>greenfriendly</th>
                         <th>moh</th>
                         <th>leadpoint</th>
                         <th>preconsummer</th>
                         <th>posconsummer</th>
                         </tr>
                       <tr>
                         <td>${item.testSpecification.waterabsorption}</td>
                         <td>${item.testSpecification.scratchresistance}</td>
                         <td>${item.testSpecification.frostresistance}</td>
                         <td>${item.testSpecification.chemicalresistance}</td>
                         <td>${item.testSpecification.peiabrasion}</td>
                         <td>${item.testSpecification.scofwet}</td>
                         <td>${item.testSpecification.scofdry}</td>
                         <td>${item.testSpecification.breakingstrength}</td>
                         <td>${item.testSpecification.scratchstandard}</td>
                         <td>${item.testSpecification.breakingstandard}</td>
                         <td>${item.testSpecification.warpage}</td>
                         <td>${item.testSpecification.wedging}</td>
                         <td>${item.testSpecification.dcof}</td>
                         <td>${item.testSpecification.thermalshock}</td>
                         <td>${item.testSpecification.bondstrength}</td>
                         <td>${item.testSpecification.greenfriendly}</td>
                         <td>${item.testSpecification.moh}</td>
                         <td>${item.testSpecification.leadpoint}</td>
                         <td>${item.testSpecification.preconsummer}</td>
                         <td>${item.testSpecification.posconsummer}</td>
                       </tr>
                    </table>
                    <div class="container" style="color:Green"> <h3>Vendors</h3></div>
                    <table border="1" cellspacing="2">
                      <tr>
                         <th>#</th>
                         <th>Vendor Number</th>
                         <th>Vendor Name</th>
                         <th>Vendor Name2</th>
                         <th>Vendor XrefId</th>
                         <th>List Price</th>
                         <th>Net Price</th>
                         <th>Price Unit</th>
                         <th>Price Round Accuracy</th>
                         <th>Markup Pct</th>
                         <th>Freight Rate Cwt</th>
                         <th>Lead Time</th>
                         <th>Duty Pct</th>
                      </tr>
                      <c:forEach var="vendor" items="${item.newVendorSystem}">
                      <tr>
                         <td>${vendor.vendorOrder}</td>
                         <td>${vendor.vendorId.id}</td>
                         <td>${vendor.vendorName}</td>
                         <td>${vendor.vendorName2}</td>
                         <td>${vendor.vendorXrefId}</td>
                         <td>${vendor.vendorListPrice}</td>
                         <td>${vendor.vendorNetPrice}</td>
                         <td>${vendor.vendorPriceUnit}</td>
                         <td>${vendor.vendorPriceRoundAccuracy}</td>
                         <td>${vendor.vendorMarkupPct}</td>
                         <td>${vendor.vendorFreightRateCwt}</td>
                         <td>${vendor.leadTime}</td>
                         <td>${vendor.dutyPct}</td>                         
                       </tr>
                    </c:forEach>   
                    </table>
	
                    <div class="container" style="color:Green"> <h3>Icons</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>madeInCountry</th>
                         <th>exteriorProduct</th>
                         <th>adaAccessibility</th>
                         <th>throughColor</th>
                         <th>colorBody</th>
                         <th>inkJet</th>
                         <th>glazed</th>
                         <th>unglazed</th>
                         <th>rectifiedEdge</th>
                         <th>chiseledEdge</th>
                         <th>versaillesPattern</th>
                         <th>recycled</th>
                         <th>postRecycled</th>
                         <th>preRecycled</th>
                         <th>leadPointIcon</th>
                         <th>greenFriendlyIcon</th>
                         <th>coefficientOfFriction</th>
                         </tr>
                       <tr>
                          <td>${item.iconDescription.madeInCountry}</td>
                         <td>${item.iconDescription.exteriorProduct}</td>
                         <td>${item.iconDescription.adaAccessibility}</td>
                         <td>${item.iconDescription.throughColor}</td>
                         <td>${item.iconDescription.colorBody}</td>
                         <td>${item.iconDescription.inkJet}</td>
                         <td>${item.iconDescription.glazed}</td>
                         <td>${item.iconDescription.unglazed}</td>
                         <td>${item.iconDescription.rectifiedEdge}</td>
                         <td>${item.iconDescription.chiseledEdge}</td>
                         <td>${item.iconDescription.versaillesPattern}</td>
                         <td>${item.iconDescription.recycled}</td>
                         <td>${item.iconDescription.postRecycled}</td>
                         <td>${item.iconDescription.preRecycled}</td>
                         <td>${item.iconDescription.leadPointIcon}</td>
                         <td>${item.iconDescription.greenFriendlyIcon}</td>
                         <td>${item.iconDescription.coefficientOfFriction}</td>
                       </tr>
                    </table>
                    <div class="container" style="color:GREEN"> <h3>Units</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>Standard Unit</th>
                         <th>Order Unit</th>
                         <th>Standard Ratio</th>
                         <th>Order Ratio</th>
                       </tr>
                       <tr>
                          <td>${item.units.stdunit}</td>
                          <td>${item.units.ordunit}</td>
                          <td>${item.units.stdratio}</td>
                          <td>${item.units.ordratio}</td>
                        </tr>
                    </table>
  
                    <div class="container" style="color:GREEN"> <h3>Packaging Information</h3></div>
                    <table border="1" cellspacing="2">
                       <tr>
                         <th>boxPieces</th>
                         <th>boxSF</th>
                         <th>boxWeight</th>
                         <th>palletBox</th>
                         <th>palletSF</th>
                         <th>palletWeight</th>
                         </tr>
                       <tr>
                          <td>${item.packaginginfo.boxPieces}</td>
                          <td>${item.packaginginfo.boxSF}</td>
                          <td>${item.packaginginfo.boxWeight}</td>
                          <td>${item.packaginginfo.palletBox}</td>
                          <td>${item.packaginginfo.palletSF}</td>
                          <td>${item.packaginginfo.palletWeight}</td>
                       </tr>
                    </table>
                   
                </c:if>
<table cellspacing="10">
<tr>
<td>
<a id="imsHome" href="<spring:url value="/ims/index" />" class="button action-m"><span>Back To Ims Management Home</span></a>
</td>
</tr>
</table>
</div> <!-- close main-content -->
</div> <!-- Close container -->
</body>
</html>