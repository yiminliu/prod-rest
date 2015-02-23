<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
<title>Item Management System -- Create An Item Icons</title>
</head>
<body>
<%@ include file="/WEB-INF/includes/header.jsp"%>
<div class="container">
<spring:url var="action" value="/ims/createItem_test" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
   <div style="color:Green"> <h3>Icons</h3></div>
   <table class="category">
      <tr>       
           <td class="label_same_row">Ink Jet:
               <form:checkbox path="iconDescription.inkJet" value="Y" />Yes
           </td>
      </tr>      
      <tr>
           <td class="label_same_row">Glazed:
               <form:checkbox path="iconDescription.glazed" value="Y" />Yes
           </td>
      </tr>      
      <tr>
           <td class="label_same_row">Unglazed:
               <form:checkbox path="iconDescription.unglazed" value="Y" />Yes
           </td>
      </tr> 
      <tr>
           <td class="label_same_row">Recycled:
              <form:checkbox path="iconDescription.recycled" value="Y" />Yes
           </td>
      </tr>      
      <tr>
            <td class="label_same_row">Post-Recycled:
               <form:checkbox path="iconDescription.postRecycled" value="Y" />Yes
           </td>
      </tr>      
      <tr>
           <td class="label_same_row">Pre-Recycled:
              <form:checkbox path="iconDescription.preRecycled" value="Y" />Yes
           </td>
      </tr>  
      <tr>
           <td class="label_same_row">Lead Point:
             <form:checkbox path="iconDescription.leadPoint" value="Y" />Yes
          </td>
      </tr> 
       <tr>      
           <td class="label_same_row">Color Body:
               <form:checkbox path="iconDescription.colorBody" value="Y" />Yes
           </td>
      </tr>         
      <tr>  
           <td class="label_same_row">Through Color:
               <form:checkbox path="iconDescription.throughColor" value="Y" />Yes
           </td>
      </tr>      
      <tr>
            <td class="label_same_row">Chiseled Edge:
               <form:checkbox path="iconDescription.chiseledEdge" value="Y" />Yes
            </td>
      </tr>      
      <tr>   
            <td class="label_same_row">Rectified Edge:
               <form:checkbox path="iconDescription.rectifiedEdge" value="Y" />Yes
            </td>
      </tr> 
      <tr>
          <td class="label_same_row">Green Friendly:
             <form:checkbox path="iconDescription.greenFriendly" value="Y" />Yes
          </td>
      </tr> 
      <tr>    
          <td class="label_same_row">Exterior Product:
              <form:checkbox path="iconDescription.exteriorProduct" value="Y" />Yes
          </td>
      </tr> 
      <tr>
            <td class="label_same_row">Versailles Pattern:
               <form:checkbox path="iconDescription.versaillesPattern" value="Y" />Yes
           </td>
      </tr>
           
      <tr>     
           <td class="label_same_row">Ada Accessibility: 
               <form:checkbox path="iconDescription.adaAccessibility" value="Y" />Yes
           </td>
      </tr>      
      <tr>
          <td class="label_same_row">Coefficient of Friction:
             <form:checkbox path="iconDescription.coefficientOfFriction" value="Y" />Yes
          </td>
      </tr>
      <tr>
          <td><label for="madeInCountryOptions">Country: </label>
              <c:forEach var="madeInCountry" items="${countryList}" varStatus="status">
                 <form:radiobutton path="iconDescription.madeInCountry" value="${madeInCountry}"/>${madeInCountry}
              </c:forEach>
          </td>
      </tr>
    </table> 
    <table> 
      <tr> 
        <td>
             <input type="submit" value="Next >"/>
        </td>
      </tr>
    </table>  
</form:form>
<%@ include file="/WEB-INF/includes/footer.jsp"%>
</div><!-- container -->
</body>
</html>
