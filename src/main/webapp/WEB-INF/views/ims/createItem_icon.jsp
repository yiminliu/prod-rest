<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<title>Item Management System -- Create Icons</title>
</head>
<body>

<div class="container">
<div id="main-content">
<div class="span-18 colborder">

<spring:url var="action" value="/ims/createItem_test" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
   <div class="container" style="color:Green"> <h3>Enter Icons Information </h3></div>
   <table class="category">
      <tr>
          <td><label for="madeInCountryOptions">Country: </label>
            <form:select id="madeInCountry" path="iconDescription.madeInCountry" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="madeInCountry" items="${countryList}" varStatus="status">
                 <form:option value="${madeInCountry}">${madeInCountry.getDescription()}</form:option>
              </c:forEach>
            </form:select>
          </td>
      </tr>      
      <tr>    
          <td><label for="exteriorProduct">Exterior Product: </label>
             <form:radiobutton path="iconDescription.exteriorProduct" value="false" />No
             <form:radiobutton path="iconDescription.exteriorProduct" value="true" />Yes
          </td>
      </tr>      
      <tr>     
           <td><label for="adaAccessibility">Ada Accessibility: </label>
               <form:select path="iconDescription.adaAccessibility" id="adaAccessibility">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>  
           <td><label for="throughColor">Through Color: </label>
               <form:select path="iconDescription.throughColor" id="throughColor">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>      
           <td><label for="colorBody">Color Body: </label>
               <form:select path="iconDescription.colorBody" id="colorBody">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>       
           <td><label for="inkJet">Ink Jet: </label>
               <form:select path="iconDescription.inkJet" id="inkJet">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>
           <td><label for="glazed">Glazed: </label>
               <form:select path="iconDescription.glazed" id="glazed">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>
           <td><label for="unglazed">Unglazed: </label>
               <form:select path="iconDescription.unglazed" id="unglazed">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>
            <td><label for="chiseledEdge">ChiseledEdge: </label>
               <form:select path="iconDescription.chiseledEdge" id="chiseledEdge">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
      </tr>      
      <tr>   
            <td><label for="rectifiedEdge">RectifiedEdge: </label>
               <form:select path="iconDescription.rectifiedEdge" id="rectifiedEdge">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
      </tr>      
      <tr>
            <td><label for="versaillesPattern">versaillesPattern: </label>
               <form:select path="iconDescription.versaillesPattern" id="versaillesPattern">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>
           <td><label for="recycled">Recycled: </label>
               <form:select path="iconDescription.recycled" id="recycled">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>
            <td><label for="postRecycled">PostRecycled: </label>
               <form:select path="iconDescription.postRecycled" id="postRecycled">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>
           <td><label for="preRecycled">PreRecycled: </label>
               <form:select path="iconDescription.preRecycled" id="preRecycled">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
           </td>
      </tr>      
      <tr>
           <td><label for="leadPointIcon">LeadPointIcon: </label>
               <form:select path="iconDescription.leadPointIcon" id="leadPointIcon">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
          </td>
      </tr>      
      <tr>
          <td><label for="greenFriendlyIcon">greenFriendlyIcon: </label>
               <form:select path="iconDescription.greenFriendlyIcon" id="greenFriendlyIcon">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
          </td>
      </tr>      
      <tr>
          <td><label for="coefficientOfFriction">coefficientOfFriction: </label>
               <form:select path="iconDescription.coefficientOfFriction" id="coefficientOfFriction">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
          </td>
      </tr>
    </table> 
    
    <table></table>
    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
             <input type="submit" value="Continue"/>
        </td>
      </tr>
    </table>  

</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
