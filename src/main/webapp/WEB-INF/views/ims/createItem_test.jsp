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
<spring:url var="action" value="/ims/createItem_note" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
   <div class="container" style="color:Green"> <h3>Test specifications</h3></div>
      <table>
          <tr>
              <td>Water Absorption (<=.5): <form:input path="testSpecification.waterabsorption"></form:input></td>
          </tr>
          <tr>
              <td>Scratch Resistance: <form:input path="testSpecification.scratchresistance"></form:input></td>
          </tr>
          <tr>
              <td>Frost Resistance: <form:input path="testSpecification.frostresistance"></form:input></td>
          </tr>
          <tr>
              <td>Chemical Resistance: <form:input path="testSpecification.chemicalresistance"></form:input></td>
          </tr>
          <tr>
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
          </tr>
          <tr>
              <td>Scof Wet (>=0.6): <form:input path="testSpecification.scofwet"></form:input></td>
          </tr>
          <tr>
              <td>Scof Dry (>=0.6): <form:input path="testSpecification.scofdry"></form:input></td>
          </tr>
          <tr>
              <td>Breaking Strength (>=250): <form:input path="testSpecification.breakingstrength"></form:input></td>
          </tr>
          <tr>
              <td>Scratch Standard: <form:input path="testSpecification.scratchstandard"></form:input></td>
          </tr>
          <tr>
              <td>Breaking Standard: <form:input path="testSpecification.breakingstandard"></form:input></td>
          </tr>
          <tr>
              <td>Warpage: <form:input path="testSpecification.warpage"></form:input></td>
          </tr>
          <tr>           
              <td>Wedging: <form:input path="testSpecification.wedging"></form:input></td>
          </tr>
          <tr>
              <td>DCOF: <form:input path="testSpecification.dcof"></form:input></td>
          </tr>
          <tr>
              <td>Thermal Shock: <form:input path="testSpecification.thermalshock"></form:input></td>
          </tr>
          <tr>
              <td>Bond Strength: <form:input path="testSpecification.bondstrength"></form:input></td>
          </tr>
          <tr>
              <td>Green Friendly: <form:input path="testSpecification.greenfriendly"></form:input></td>
          </tr>
          <tr>
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
          </tr>
          <tr>
              <td>Lead Point: <form:input path="testSpecification.leadpoint"></form:input></td>
          </tr>
          <tr>
              <td>Pre-Consummer: <form:input path="testSpecification.preconsummer"></form:input></td>
          </tr>
          <tr>
              <td>Pos-Consummer: <form:input path="testSpecification.posconsummer"></form:input></td>
          </tr>
    </table>
    <table></table>
    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
            <input type="submit" value="Continue"/>
            <!--<a id="createItemPage2" href="<spring:url value="/ims/createItemPage2"/>"><span>Next</span></a>-->
        </td>
      </tr>
    </table> 
 
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
