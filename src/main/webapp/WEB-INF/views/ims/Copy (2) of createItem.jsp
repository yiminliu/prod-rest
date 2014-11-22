<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>

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
<div id="main-content">
<div class="span-18 colborder">
<h3 style="margin-bottom: 10px; padding-bottom: 0px; color: BLUE">Enter Item Information</h3>
<form:form method="POST" modelAttribute="item">
<table>
   <div class="container" style="color:GREEN"> <h3>General Information</h3></div>
    <table class="category">
    <tr>
        <td>Item Code: <form:input path="itemcode"></form:input></td>
        <td>Item Description: <form:textarea path="itemdesc.itemdesc1"></form:textarea></td>
        <td>Category: <form:input path="itemcategory"></form:input></td>
        <td>Series Name: <form:input path="series.seriesname"></form:input></td>
        <td>Series Color: <form:input path="series.seriescolor"></form:input></td>
        <td><label for="inactivecodeOptions">Active Status: </label>
           <form:select path="inactivecode" id="inactivecodeOptions">
              <form:option value="">Select One</form:option>
              <form:option value="N">Active</form:option>
              <form:option value="Y">Inactive</form:option>
           </form:select>
        </td>
        <td><label for="countryOptions">Origin: </label>
            <form:select id="countries" path="countryorigin" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="countryName" items="${countryList}" varStatus="status">
                 <form:option value="${countryName}">${countryName}</form:option>
              </c:forEach>
           </form:select>
       </td>    
       <td><label for="shadevariationOptions">Shade Variation: </label>
           <form:select id="shadevariation" path="shadevariation" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
              <option value="0" selected="selected">Select one</option>
              <c:forEach var="shade" items="${shadeVariationList}" varStatus="status">
                 <form:option value="${shade}">${shade}</form:option>
              </c:forEach>
           </form:select>
        </td>    
        <td><label for="MPSOptions">MPS: </label>
            <form:select id="mps" path="newFeature.mpsCode" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="mps" items="${mpsList}" varStatus="status">
                 <form:option value="${mps}">${mps}</form:option>
              </c:forEach>
           </form:select>
       </td> 
       <td calss="narrow"><label for="itemStatusOptions">Status: </label>
            <form:select id="itemStatus" path="newFeature.status" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="itemStatus" items="${statusList}" varStatus="status">
                 <form:option value="${itemStatus}">${itemStatus}</form:option>
              </c:forEach>
           </form:select>
       </td>
       <td><label for="gradeOptions">Grade: </label>
            <form:select id="grade" path="newFeature.grade" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="grade" items="${gradeList}" varStatus="status">
                 <form:option value="${grade}">${grade}</form:option>
              </c:forEach>
           </form:select>
       </td>  
       <td><label for="warrantyOptions">Warranty: </label>
            <form:select id="warranty" path="newFeature.warranty" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <form:option value="1">1 year</form:option>
               <form:option value="3">3 year</form:option>
               <form:option value="5">5 year</form:option>
               <form:option value="10">10 year</form:option>
            </form:select>
         </td>
      </tr>
    </table>
  
    <div class="container" style="color:GREEN"> <h3>Material Information</h3></div>
    <table class="category">
      <tr>  
         <td><label for="materialType">Material Type:</label>
            <form:select id="materialType" path="material.materialtype" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="materialType" items="${materialTypeList}" varStatus="status">
                 <form:option value="${materialType}">${materialType.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>   
         <td><label for="materialCategory">Material Category:</label>
            <form:select id="materialCategory" path="material.materialcategory" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="materialCategory" items="${materialCategoryList}" varStatus="status">
                 <form:option value="${materialCategory}">${materialCategory.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>   
         <td><label for="materialClass">Material Class:</label>
            <form:select id="materialClass" path="material.materialclass" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="materialClass" items="${materialClassList}" varStatus="status">
                 <form:option value="${materialClass}">${materialClass.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td> 
         <td><label for="materialStyle">Material Style:</label>
            <form:select id="materialStyle" path="material.materialstyle" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="materialStyle" items="${materialStyleList}" varStatus="status">
                 <form:option value="${materialStyle}">${materialStyle.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>   
         <td>Material Feature: <form:input path="material.materialfeature"></form:input></td>
        </tr>
    </table> 
  
    <div class="container" style="color:GREEN"> <h3>Dimemsions</h3></div>
    <table class="category">
      <tr>
        <td>length: <form:input path="dimensions.length"></form:input></td>
        <td>width: <form:input path="dimensions.width"></form:input></td>
        <td>thickness: <form:input path="dimensions.thickness"></form:input></td>
        <td>nominallength: <form:input path="dimensions.nominallength"></form:input></td>
        <td>nominalwidth: <form:input path="dimensions.nominalwidth"></form:input></td>
        <td>nominalthickness: <form:input path="dimensions.nominalthickness"></form:input></td>
         <td><label for="sizeunitsOptions">Size Unit : </label>
           <form:select path="dimensions.sizeunits" id="sizeunitsOptions">
              <form:option value="">Select One</form:option>
              <form:option value="E">E</form:option>
              <form:option value="M">M</form:option>
           </form:select>
        </td>
        <td><label for="thicknessunitOptions">Thickness Unit: </label>
           <form:select path="dimensions.thicknessunit" id="thicknessunitOptions">
              <form:option value="">Select One</form:option>
              <form:option value="E">E</form:option>
              <form:option value="M">M</form:option>
           </form:select>
        </td>
      </tr>     
    </table>
     
     <div class="container" style="color:Green"> <h3>Applications</h3></div>
     <table class="category">
        <tr>
          <td calss="narrow"><label for="edgeOptions">Edge: </label>
            <form:select id="edge" path="newFeature.edge" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="edge" items="${edgeList}" varStatus="status">
                 <form:option value="${edge}">${edge.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>                  
         <td><label for="bodyOptions">Body: </label>
            <form:select id="body" path="newFeature.body" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="body" items="${bodyList}" varStatus="status">
                 <form:option value="${body}">${body.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>
         <td><label for="designLookOptions">Design Look: </label>
            <form:select id="designLook" path="newFeature.designLook" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="designLook" items="${designLookList}" varStatus="status">
                 <form:option value="${designLook}">${designLook.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>  
         <td><label for="designStyleOptions">Design Styles: </label>
            <form:select id="designStyle" path="newFeature.designStyle" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="designStyle" items="${designStyleList}" varStatus="status">
                 <form:option value="${designStyle}">${designStyle.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>  
         <td><label for="surfaceTypeOptions">Surface Type: </label>
            <form:select id="surfaceType" path="newFeature.surfaceType" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="surfaceType" items="${surfaceTypeList}" varStatus="status">
                 <form:option value="${surfaceType}">${surfaceType.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>  
         <td><label for="surfaceFinishOptions">Surface Finish: </label>
            <form:select id="surfaceFinish" path="newFeature.surfaceFinish" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">>
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="surfaceFinish" items="${surfaceFinishList}" varStatus="status">
                 <form:option value="${surfaceFinish}">${surfaceFinish.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>         
         <td><label for="surfaceApplicationOptions">Surface Application: </label>
            <form:select id="surfaceApplication" path="newFeature.surfaceApplication" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="surfaceApplication" items="${surfaceApplicationList}" varStatus="status">
                 <form:option value="${surfaceApplication}">${surfaceApplication.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>
         <td><label for="recommendedGroutJointMinOptions">Recommended Grout Joint Min: </label>
            <form:select id="recommendedGroutJointMin" path="newFeature.recommendedGroutJointMin" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <form:option value="1">1</form:option>
               <form:option value="2">2</form:option>
               <form:option value="3">3</form:option>
            </form:select>
         </td> 
         <td><label for="recommendedGroutJointMaxOptions">Recommended Grout Joint Max: </label>
            <form:select id="recommendedGroutJointMax" path="newFeature.recommendedGroutJointMax" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <form:option value="1">1</form:option>
               <form:option value="2">2</form:option>
               <form:option value="3">3</form:option>
            </form:select>
         </td>
        </tr>
    </table>
        
    <div class="container" style="color:GREEN"> <h3>Pricing</h3></div>
    <table class="category">
     <tr>
        <td>MSRP: <form:input path="price.sellprice"></form:input></td>
        <td>Special Price: <form:input path="price.tempprice"></form:input></td>
        <td>Special Price Start Date (mm/dd/yyyy): <form:input path="price.tempdatefrom"></form:input></td>
        <td>Special Price End Date (mm/dd/yyyy): <form:input path="price.tempdatethru"></form:input></td>
     </tr>     
    </table>  
   
    <table class="category">  
   <tr>
    <td style="padding-right: 200px;"> 
    <div class="container" style="color:GREEN"> <h3>Color Category</h3></div>
    <table>
     <tr>  
       <td><label for="colorOptions">Select Colors: </label>
            <form:select multiple="true" id="colorHue" path="colorhues" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;">
               <form:option value="0" selected="selected">Select colors</form:option>
               <c:forEach var="colorHue" items="${colorList}" varStatus="status">
                 <form:option value="${colorHue}">${colorHue}</form:option>
              </c:forEach>
           </form:select>
       </td> 
     </tr>
    </table>
    </td>
   
    <td>   
    <div class="container" style="color:Green"> <h3>Notes</h3></div>
       <table>
          <tr>
              <td>PO Notes: <form:textarea path="notes.ponotes"></form:textarea></td>
              <td>Buyer Notes: <form:textarea path="notes.buyernotes"></form:textarea></td>
              <td>Invoice Notes: <form:textarea path="notes.invoicenotes"></form:textarea></td>
              <td>Internal Notes: <form:textarea path="notes.internalnotes"></form:textarea></td>
          </tr>
    </table>   
    </td></tr>
    </table>
         
    <table class="category">  
    <tr>
    <td  style="padding-right: 150px;">
    <div class="container" style="color:GREEN"> <h3>Buyers</h3></div>
    <table>
     <tr>
        <td>Product Manager: <form:input path="purchasers.purchaser"></form:input></td>
        <td>Buyer: <form:input path="purchasers.purchaser2"></form:input></td>
     </tr>     
    </table>
    </td>
    <td style="padding-right: 150px;"> 
    <div class="container" style="color:Green"> <h3>Usage Information</h3></div>
    <table>
         <tr>
              <td><label for="residentialOptions">Residential: </label>
                 <form:select path="applications.residential" id="residentialOptions">
                    <form:option value="">Select One</form:option>
                    <form:option value="Y">Yes</form:option>
                    <form:option value="N">No</form:option>
                 </form:select>
              </td>
              <td><label for="lightcommercialOptions">Lightcommercial: </label>
                 <form:select path="applications.lightcommercial" id="lightcommercialOptions">
                    <form:option value="">Select One</form:option>
                    <form:option value="Y">Yes</form:option>
                    <form:option value="N">No</form:option>
                 </form:select>
              </td>
              <td><label for="commercialOptions">Commercial: </label>
                 <form:select path="applications.commercial" id="commercialOptions">
                    <form:option value="">Select One</form:option>
                    <form:option value="Y">Yes</form:option>
                    <form:option value="N">No</form:option>
                 </form:select>
              </td>
         </tr>
     </table>
     </td>
     <td>
     <div class="container" style="color:Green"> <h3>Web</h3></div>
    <table>
        <tr>
           <td><label for="showonwebsiteOptions">Show on Bedrosians: </label>
               <form:select path="showonwebsite" id="residentialOptions">
                  <form:option value="Y" selected="selected">Yes</form:option>
                  <form:option value="N">No</form:option>
               </form:select>
           </td>
           <td><label for="showonalysedwardsOptions">Show on AlysEdwards: </label>
               <form:select path="showonalysedwards" id="showonalysedwardsOptions">
                  <form:option value="Y" selected="selected">Yes</form:option>
                  <form:option value="N">No</form:option>
               </form:select>
           </td>
         </tr>
    </table>
    </td>
    </tr>
    </table>
     
    <div class="container" style="color:Green"> <h3>Vendors</h3></div>
    <table class="category">
     <c:forEach items="${newVendorSystem}" varStatus="loop">
       <tr>
          <!--<td style="width:20">Order#: <form:input path="newVendorSystem[${loop.index}].vendorOrder"></form:input></td>-->  
          <td style="width:20"><label for="vendorOrder">Order#: </label>
            <form:select id="vendorOrder" path="newVendorSystem[${loop.index}].vendorOrder" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <form:option value="1">1</form:option>
               <form:option value="2">2</form:option>
               <form:option value="3">3</form:option>
            </form:select>   
          <td>Vendor Number: <form:input path="newVendorSystem[${loop.index}].vendorId.id"></form:input></td>
          <td>Vendor Name: <form:input path="newVendorSystem[${loop.index}].vendorName"></form:input></td>
          <td>Vendor Name2: <form:input path="newVendorSystem[${loop.index}].vendorName2"></form:input></td>
          <td>Vendor XrefId: <form:input path="newVendorSystem[${loop.index}].vendorXrefId"></form:input></td>
          <td>List Price: <form:input path="newVendorSystem[${loop.index}].vendorListPrice" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
          <td>Net Price: <form:input path="newVendorSystem[${loop.index}].vendorNetPrice" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
          <td><label for="stdunitOptions">Price Unit: </label>
            <form:select id="vendorPriceUnit" path="newVendorSystem[${loop.index}].vendorPriceUnit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="vendorPriceUnit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${vendorPriceUnit}">${vendorPriceUnit}</form:option>
              </c:forEach>
            </form:select>
          </td>
          <td>Price Round Accuracy: <form:input path="newVendorSystem[${loop.index}].vendorPriceRoundAccuracy" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:160px;"></form:input></td>
          <td>Freight Rate Cwt: <form:input path="newVendorSystem[${loop.index}].vendorFreightRateCwt" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
          <td>Lead Time: <form:input path="newVendorSystem[${loop.index}].leadTime" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
          <td>Duty Pct: <form:input path="newVendorSystem[${loop.index}].dutyPct" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
       </tr>
     </c:forEach>  
     </table>
   
   <table class="category">
   <div class="container" style="color:Green"> <h3>Icons </h3></div>
   <tr>
    <td>
    
    <table>
       <tr>
          <td><label for="madeInCountryOptions">Country: </label>
            <form:select id="madeInCountry" path="iconDescription.madeInCountry" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="madeInCountry" items="${countryList}" varStatus="status">
                 <form:option value="${madeInCountry}">${madeInCountry.getDescription()}</form:option>
              </c:forEach>
            </form:select>
          </td>
          <td><label for="exteriorProduct">Exterior Product: </label>
               <form:select path="iconDescription.exteriorProduct" id="exteriorProduct">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
           <td><label for="adaAccessibility">Ada Accessibility: </label>
               <form:select path="iconDescription.adaAccessibility" id="adaAccessibility">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
           <td><label for="throughColor">Through Color: </label>
               <form:select path="iconDescription.throughColor" id="throughColor">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
           <td><label for="colorBody">Color Body: </label>
               <form:select path="iconDescription.colorBody" id="colorBody">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
           <td><label for="inkJet">Ink Jet: </label>
               <form:select path="iconDescription.inkJet" id="inkJet">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
           <td><label for="glazed">Glazed: </label>
               <form:select path="iconDescription.glazed" id="glazed">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
           <td><label for="unglazed">Unglazed: </label>
               <form:select path="iconDescription.unglazed" id="unglazed">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
            <td><label for="chiseledEdge">ChiseledEdge: </label>
               <form:select path="iconDescription.chiseledEdge" id="chiseledEdge">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
         </tr>
         </table>
         </td>
         </tr>
         <tr>
         <td>
         <table>
         <tr>   
            <td><label for="rectifiedEdge">RectifiedEdge: </label>
               <form:select path="iconDescription.rectifiedEdge" id="rectifiedEdge">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="versaillesPattern">versaillesPattern: </label>
               <form:select path="iconDescription.versaillesPattern" id="versaillesPattern">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
           <td><label for="recycled">Recycled: </label>
               <form:select path="iconDescription.recycled" id="recycled">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
            <td><label for="postRecycled">PostRecycled: </label>
               <form:select path="iconDescription.postRecycled" id="postRecycled">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
            <td><label for="preRecycled">PreRecycled: </label>
               <form:select path="iconDescription.preRecycled" id="preRecycled">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
            <td><label for="leadPointIcon">LeadPointIcon: </label>
               <form:select path="iconDescription.leadPointIcon" id="leadPointIcon">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
            <td><label for="greenFriendlyIcon">greenFriendlyIcon: </label>
               <form:select path="iconDescription.greenFriendlyIcon" id="greenFriendlyIcon">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
            <td><label for="coefficientOfFriction">coefficientOfFriction: </label>
               <form:select path="iconDescription.coefficientOfFriction" id="coefficientOfFriction">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
        </tr>
    </table> 
    </td></tr></table>
    <table class="category">
    <tr>
     <td>
     <div class="container" style="color:Green"> <h3>Test specifications</h3></div>
       <table>
          <tr>
              <td>Water Absorption (<=.5): <form:input path="testSpecification.waterabsorption"></form:input></td>
              <td>Scratch Resistance: <form:input path="testSpecification.scratchresistance"></form:input></td>
              <td>Frost Resistance: <form:input path="testSpecification.frostresistance"></form:input></td>
              <td>Chemical Resistance: <form:input path="testSpecification.chemicalresistance"></form:input></td>
              <td><label for="peiabrasion">Pei Abrasion: </label>
               <form:select id="peiabrasion" path="testSpecification.peiabrasion" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <form:option value="1">1</form:option>
               <form:option value="2">2</form:option>
               <form:option value="3">3</form:option>
               <form:option value="4">4</form:option>
               <form:option value="5">5</form:option>
               </form:select>
               </td>
              <td>Scof Wet (>=0.6): <form:input path="testSpecification.scofwet"></form:input></td>
              <td>Scof Dry (>=0.6): <form:input path="testSpecification.scofdry"></form:input></td>
              <td>Breaking Strength (>=250): <form:input path="testSpecification.breakingstrength"></form:input></td>
              <td>Scratch Standard: <form:input path="testSpecification.scratchstandard"></form:input></td>
              <td>Breaking Standard: <form:input path="testSpecification.breakingstandard"></form:input></td>
              </tr>
            </table> 
            </td>
            </tr>
            <tr>
            <td>
            <table>
              <td>Warpage: <form:input path="testSpecification.warpage"></form:input></td>
            
              <td>Wedging: <form:input path="testSpecification.wedging"></form:input></td>
              <td>DCOF: <form:input path="testSpecification.dcof"></form:input></td>
              <td>Thermal Shock: <form:input path="testSpecification.thermalshock"></form:input></td>
              <td>Bond Strength: <form:input path="testSpecification.bondstrength"></form:input></td>
              <td>Green Friendly: <form:input path="testSpecification.greenfriendly"></form:input></td>
              <td><label for="moh">MOH: </label>
               <form:select id="moh" path="testSpecification.moh" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <form:option value="1">1</form:option>
               <form:option value="2">2</form:option>
               <form:option value="3">3</form:option>
               <form:option value="4">4</form:option>
               <form:option value="5">5</form:option>
               <form:option value="6">6</form:option>
               <form:option value="7">7</form:option>
               <form:option value="8">8</form:option>
               <form:option value="9">9</form:option>
               <form:option value="10">10</form:option>
               </form:select>
              </td> 
              <td>Lead Point: <form:input path="testSpecification.leadpoint"></form:input></td>
              <td>Pre-Consummer: <form:input path="testSpecification.preconsummer"></form:input></td>
              <td>Pos-Consummer: <form:input path="testSpecification.posconsummer"></form:input></td>
         </tr>
    </table>
     </td>
     </tr>
     </table>
    <table></table>
    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
            <input type="submit" value="Submit"/>
            <!--<a id="createItemPage2" href="<spring:url value="/ims/createItemPage2"/>"><span>Next</span></a>-->
        </td>
      </tr>
    </table> 
</table>  
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
