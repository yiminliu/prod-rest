<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">
<div id="main-content">
<div class="span-18 colborder">
<h3 style="margin-bottom: 10px; padding-bottom: 0px;">Enter Item Information</h3>

<form:form method="POST" modelAttribute="item">
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
<table>
  <div class="container" style="color:Green"> <h3>Units </h3></div>
    <table>
       <tr>
          <td><label for="stdunitOptions">stdunit: </label>
            <form:select id="stdunit" path="units.stdunit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="stdunit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${stdunit}">${stdunit}</form:option>
              </c:forEach>
            </form:select>
          </td>
          <td><label for="ordunitOptions">ordunit: </label>
            <form:select id="ordunit" path="units.ordunit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="ordunit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${ordunit}">${ordunit}</form:option>
              </c:forEach>
            </form:select>
          </td>
          <td><label for="baseunitOptions">baseunit: </label>
            <form:select id="baseunit" path="units.baseunit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="baseunit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${baseunit}">${baseunit}</form:option>
              </c:forEach>
            </form:select>
          </td>
          
          <td><label for="unit1unitOptions">unit1unit: </label>
            <form:select id="unit1unit" path="units.unit1unit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="unit1unit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${unit1unit}">${unit1unit}</form:option>
              </c:forEach>
            </form:select>
          </td>
          <td><label for="unit2unitOptions">unit2unit: </label>
            <form:select id="unit2unit" path="units.unit2unit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="unit2unit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${unit2unit}">${unit2unit}</form:option>
              </c:forEach>
            </form:select>
          </td>
          <td><label for="unit3unitOptions">unit3unit: </label>
            <form:select id="unit3unit" path="units.unit3unit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="unit3unit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${unit3unit}">${unit3unit}</form:option>
              </c:forEach>
            </form:select>
          </td>
          <td><label for="unit4unitOptions">unit4unit: </label>
            <form:select id="unit4unit" path="units.unit4unit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="unit4unit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${unit4unit}">${unit4unit}</form:option>
              </c:forEach>
            </form:select>
          </td>
          <td>stdratio: <form:input path="units.stdratio"></form:input></td>
          <td>ordratio: <form:input path="units.ordratio"></form:input></td>
          
          <td>unit1ratio: <form:input path="units.unit1ratio"></form:input></td>
          <td>unit2ratio: <form:input path="units.unit2ratio"></form:input></td>
          <td>unit3ratio: <form:input path="units.unit3ratio"></form:input></td>
          <td>unit4ratio: <form:input path="units.unit4ratio"></form:input></td>
          
          <td>baseupcdesc: <form:input path="units.baseupcdesc"></form:input></td>
          <td>unit1upcdesc: <form:input path="units.unit1upcdesc"></form:input></td>
          <td>unit2upcdesc: <form:input path="units.unit2upcdesc"></form:input></td>
          <td>unit3upcdesc: <form:input path="units.unit3upcdesc"></form:input></td>
          <td>unit4upcdesc: <form:input path="units.unit4upcdesc"></form:input></td>
	
	     <td>baseupc: <form:input path="units.baseupc"></form:input></td>
	     <td>unit1upc: <form:input path="units.unit1upc"></form:input></td>
	     <td>unit2upc: <form:input path="units.unit2upc"></form:input></td>
	     <td>unit3upc: <form:input path="units.unit3upc"></form:input></td>
	     <td>unit4upc: <form:input path="units.unit4upc"></form:input></td>
	     
	     <td><label for="baseisstdsellOptons">baseisstdsell: </label>
               <form:select path="units.baseisstdsell" id="baseisstdsell">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            
	      <td><label for="unit1isstdsellOptions">unit1isstdsell: </label>
               <form:select path="units.unit1isstdsell" id="unit1isstdsell">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit2isstdsellOptions">unit2isstdsell: </label>
               <form:select path="units.unit2isstdsell" id="unit2isstdsell">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit3isstdsellOptions">unit3isstdsell: </label>
               <form:select path="units.unit3isstdsell" id="unit3isstdsell">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit4isstdsellOptions">unit4isstdsell: </label>
               <form:select path="units.unit4isstdsell" id="unit4isstdsell">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            
            <td><label for="baseisstdordOptions">baseisstdord: </label>
               <form:select path="units.baseisstdord" id="baseisstdord">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
	      <td><label for="unit1isstdordOptions">unit1isstdord: </label>
               <form:select path="units.unit1isstdord" id="unit1isstdord">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
             <td><label for="unit2isstdordOptions">unit2isstdord: </label>
               <form:select path="units.unit2isstdord" id="unit2isstdord">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
             <td><label for="unit3isstdordOptions">unit3isstdord: </label>
               <form:select path="units.unit3isstdord" id="unit3isstdord">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
             <td><label for="unit4isstdordOptions">unit4isstdord: </label>
               <form:select path="units.unit4isstdord" id="unit4isstdord">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            
            <td><label for="baseisfractqtyOptions">baseisfractqty: </label>
               <form:select path="units.baseisfractqty" id="baseisfractqty">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            
            <td><label for="unit1isfractqtyOptions">unit1isfractqty: </label>
               <form:select path="units.unit1isfractqty" id="unit1isfractqty">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit2isfractqtyOptions">unit2isfractqty: </label>
               <form:select path="units.unit2isfractqty" id="unit2isfractqty">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit3isfractqtyOptions">unit3isfractqty: </label>
               <form:select path="units.unit3isfractqty" id="unit3isfractqty">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit4isfractqtyOptions">unit4isfractqty: </label>
               <form:select path="units.unit4isfractqty" id="unit4isfractqty">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
  
            <td><label for="baseispackunitOptions">baseispackunit: </label>
               <form:select path="units.baseispackunit" id="baseispackunit">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit1ispackunitOptions">unit1ispackunit: </label>
               <form:select path="units.unit1ispackunit" id="unit1ispackunit">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit2ispackunitOptions">unit2ispackunit: </label>
               <form:select path="units.unit2ispackunit" id="unit2ispackunit">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit3ispackunitOptions">unit3ispackunit: </label>
               <form:select path="units.unit3ispackunit" id="unit3ispackunit">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
            <td><label for="unit4ispackunitOptions">unit4ispackunit: </label>
               <form:select path="units.unit4ispackunit" id="unit4ispackunit">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
	   <td>basevolperunit: <form:input path="units.basevolperunit"></form:input></td>
	   <td>basewgtperunit: <form:input path="units.basewgtperunit"></form:input></td>
	   
	   <td>basewgtperunit: <form:input path="units.unit1wgtperunit"></form:input></td>
	   <td>basewgtperunit: <form:input path="units.unit2wgtperunit"></form:input></td>
	   <td>basewgtperunit: <form:input path="units.unit3wgtperunit"></form:input></td>
	   <td>basewgtperunit: <form:input path="units.unit4wgtperunit"></form:input></td>
	  </tr>
    </table> 
    <table></table>
    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
            <input type="submit" value="Submit"/>
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
