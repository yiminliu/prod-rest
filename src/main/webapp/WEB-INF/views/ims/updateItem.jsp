<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>

  <style type="text/css"> 
     table.category { 
        border-bottom: dotted 1px blue;
     }
     .container {
        color:#0076BF;
        margin: -20px auto; 
        padding: 30px;
        border-spacing: 10px;
        empty-cells:show;
        width:90%;
        text-align:left;
      } 

      .table tr {
         height:20px;
         white-space:nowrap;
         text-align:left;
      }
	  td.narrow 
      { 
        width:10px;
      } 
      .error {
       color: red;
       } 
  </style>
</head>
<body>

<div class="container">
<div id="main-content">
<div class="span-18 colborder">
<spring:url var="action" value="/ims/createItem_material" />
<form:form method="POST" action="${action}" modelAttribute="item">
   <div style="color:Green"> <h3>General Information</h3></div>
   <table>
   <table class="category">
      <tr>
         <td>Item Code:   <form:input path="itemcode" size="18"></form:input><small>maximum 18 characters</small></td>
      </tr>
      <tr>
         <td><form:errors path="itemcode" cssClass="error" /></td>               
      </tr>
      <tr>    
         <td>Item Description: <form:input path="itemdesc.itemdesc1" size="50"></form:input><small>maximum 35 characters</small></td>
      </tr>
       <tr>
         <td><form:errors path="itemdesc.itemdesc1" cssClass="error" /></td>               
      </tr>
      <tr>   
         <td>Category:         <form:input path="itemcategory"></form:input></td>
      </tr>
      <tr>
         <td><form:errors path="itemcategory" cssClass="error" /></td>               
      </tr>
      <tr>  
         <td>Series Name:      <form:input path="series.seriesname"></form:input></td>
      </tr>
      <tr>  
         <td>Series Color:     <form:input path="series.seriescolor"></form:input></td>
      </tr>
      <tr>  
         <td><label for="inactivecodeOptions">Active Status: </label>
             <form:radiobutton path="inactivecode" value="N" />Active
             <form:radiobutton path="inactivecode" value="Y" />Inactive
         </td>
      </tr>
      <tr>
         <td><form:errors path="inactivecode" cssClass="error" /></td>               
      </tr>
      <tr> 
        <td calss="narrow"><label for="itemStatusOptions">Status: </label>
              <c:forEach var="itemStatus" items="${statusList}" varStatus="status">
                 <form:radiobutton path="newFeature.status" value="${itemStatus}" />${itemStatus}
              </c:forEach>
        </td>
      </tr>
       <tr>
         <td><form:errors path="newFeature.status" cssClass="error" /></td>               
      </tr>
      <tr>
         <td><label for="gradeOptions">Grade: </label>
             <c:forEach var="grade" items="${gradeList}" varStatus="status">
                <form:radiobutton path="newFeature.grade" value="${grade}" />${grade}
             </c:forEach>
         </td> 
      </tr>
       <tr>
         <td><form:errors path="newFeature.grade" cssClass="error" /></td>               
      </tr>
      <tr>   
        <td><label for="shadevariationOptions">Shade Variation: </label>
            <c:forEach var="shade" items="${shadeVariationList}" varStatus="status">
                 <form:radiobutton path="shadevariation" value="${shade}" />${shade}
            </c:forEach>
        </td> 
      </tr>
     
      <tr> 
         <td><label for="warrantyOptions">Warranty: </label>
            <form:radiobutton path="newFeature.warranty" value="1" />1 year
            <form:radiobutton path="newFeature.warranty" value="3" />3 year
            <form:radiobutton path="newFeature.warranty" value="5" />5 year
            <form:radiobutton path="newFeature.warranty" value="10" />10 year
         </td>
      </tr>
       <tr>  
        <td><label for="countryOptions">Origin: </label>
            <c:forEach var="countryName" items="${countryList}" varStatus="status">
                 <form:radiobutton path="countryorigin" value="${countryName}" />${countryName}
              </c:forEach>
        </td> 
      </tr>
      <tr>
         <td><form:errors path="countryorigin" cssClass="error" /></td>               
      </tr>
      <tr>   
        <td><label for="MPSOptions">MPS: </label>
               <c:forEach var="mps" items="${mpsList}" varStatus="status">
                  <form:radiobutton path="newFeature.mpsCode" value="${mps}" />${mps}
               </c:forEach>
        </td>
      </tr>
      <tr>
         <td><form:errors path="newFeature.mpsCode" cssClass="error" /></td>               
      </tr>
    </table>
   
    <div style="color:GREEN"> <h3>Color Category</h3></div>
    <table  class="category"> 
     <tr>  
       <td style="padding-left: 5px;padding-bottom:3px; font-size: 12px;">
           <c:forEach var="colorHue" items="${colorList}" varStatus="status">
                <form:checkbox path="colorhues" value="${colorHue}" />${colorHue}
           </c:forEach>
       </td> 
     </tr>
    </table>
  
    <div style="color:Green"> <h3>Web</h3></div>
    <table class="category"> 
        <tr>
           <td><label for="showonwebsiteOptions">Show on Bedrosians: </label>
              <form:radiobutton path="showonwebsite" value="Y" />Yes
              <form:radiobutton path="showonwebsite" value="N" />No
           </td>
        </tr>
          <tr>
         <td><form:errors path="showonwebsite" cssClass="error" /></td>               
      </tr>
        <tr>   
           <td><label for="showonalysedwardsOptions">Show on AlysEdwards: </label>
              <form:radiobutton path="showonalysedwards" value="Y" />Yes
              <form:radiobutton path="showonalysedwards" value="N" />No
           </td>
        </tr>
     </table>
     <div style="color:Green"> <h3>Usage</h3></div>
     <table class="category">     
         <tr>
              <td><label for="residentialOptions">Residential      :</label>
                 <form:radiobutton path="applications.residential" value="Y" />Yes
                 <form:radiobutton path="applications.residential" value="N" />No
              </td>
         </tr>
         <tr>
              <td><label for="lightcommercialOptions">Lightcommercial:</label>
                 <form:radiobutton path="applications.lightcommercial" value="Y" />Yes
                 <form:radiobutton path="applications.lightcommercial" value="N" />No
              </td>
         </tr>
         <tr>
            <td><label for="commercialOptions">Commercial            :</label>
                 <form:radiobutton path="applications.commercial" value="Y" />Yes
                 <form:radiobutton path="applications.commercial" value="N" />No
            </td>
         </tr>
   </table>
   <div style="color:GREEN"> <h3>Buyers</h3></div>
    <table class="category"> 
     <tr>
        <td>Product Manager: <form:input path="purchasers.purchaser"></form:input></td>
        <td>Buyer: <form:input path="purchasers.purchaser2"></form:input></td>
     </tr>     
    </table>
   <br/>
   <table> 
      <tr > 
        <td colspan="2">
            <input type="submit" value="Continue"/>
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
