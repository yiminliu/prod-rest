<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<title>Item Management System -- Create Icons</title>
</head>
<body>

<div class="container">
<spring:url var="action" value="/ims/createItem_test" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
   <div style="color:Green"> <h3>Icons</h3></div>
   <table class="category">
      <tr>       
           <td>Ink Jet:
               <form:checkbox path="iconDescription.inkJet" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
           <td>Glazed:
               <form:checkbox path="iconDescription.glazed" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
           <td>Unglazed:
               <form:checkbox path="iconDescription.unglazed" value="Yes" />Yes
           </td>
      </tr> 
      <tr>
           <td>Recycled:
              <form:checkbox path="iconDescription.recycled" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
            <td>Post-Recycled:
               <form:checkbox path="iconDescription.postRecycled" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
           <td>Pre-Recycled:
              <form:checkbox path="iconDescription.preRecycled" value="Yes" />Yes
           </td>
      </tr>  
      <tr>
           <td>Lead Point:
             <form:checkbox path="iconDescription.leadPointIcon" value="Yes" />Yes
          </td>
      </tr>       
      <tr>  
           <td>Through Color:
               <form:checkbox path="iconDescription.throughColor" value="Yes" />Yes
           </td>
      </tr>      
      <tr>      
           <td>Color Body:
               <form:checkbox path="iconDescription.colorBody" value="Yes" />Yes
           </td>
      </tr>      
          
      <tr>
            <td>Chiseled Edge:
               <form:checkbox path="iconDescription.chiseledEdge" value="Yes" />Yes
            </td>
      </tr>      
      <tr>   
            <td>Rectified Edge:
               <form:checkbox path="iconDescription.rectifiedEdge" value="Yes" />Yes
            </td>
      </tr> 
      
      <tr>    
          <td>Exterior Product:
              <form:checkbox path="iconDescription.exteriorProduct" value="Yes" />Yes
          </td>
      </tr> 
      
      <tr>
          <td>Green Friendly:
             <form:checkbox path="iconDescription.greenFriendlyIcon" value="Yes" />Yes
          </td>
      </tr> 
           
      <tr>
            <td>Versailles Pattern:
               <form:checkbox path="iconDescription.versaillesPattern" value="Yes" />Yes
           </td>
      </tr>
           
      <tr>     
           <td>Ada Accessibility: 
               <form:checkbox path="iconDescription.adaAccessibility" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
          <td>Coefficient of Friction:
             <form:checkbox path="iconDescription.coefficientOfFriction" value="Yes" />Yes
          </td>
      </tr>
      <tr>
          <td><label for="madeInCountryOptions">Country: </label>
              <c:forEach var="madeInCountry" items="${countryList}" varStatus="status">
                 <form:radiobutton path="iconDescription.madeInCountry" value="${madeInCountry}"/>${madeInCountry.getDescription()}
              </c:forEach>
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
</div><!-- container -->
</body>
</html>
