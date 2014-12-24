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
           <td><label_same_row>Ink Jet:</label_same_row>
               <form:checkbox path="iconDescription.inkJet" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
           <td><label_same_row>Glazed:</label_same_row>
               <form:checkbox path="iconDescription.glazed" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
           <td><label_same_row>Unglazed:</label_same_row>
               <form:checkbox path="iconDescription.unglazed" value="Yes" />Yes
           </td>
      </tr> 
      <tr>
           <td><label_same_row>Recycled:</label_same_row>
              <form:checkbox path="iconDescription.recycled" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
            <td><label_same_row>Post-Recycled:</label_same_row>
               <form:checkbox path="iconDescription.postRecycled" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
           <td><label_same_row>Pre-Recycled:</label_same_row>
              <form:checkbox path="iconDescription.preRecycled" value="Yes" />Yes
           </td>
      </tr>  
      <tr>
           <td><label_same_row>Lead Point:</label_same_row>
             <form:checkbox path="iconDescription.leadPoint" value="Yes" />Yes
          </td>
      </tr> 
       <tr>      
           <td><label_same_row>Color Body:</label_same_row>
               <form:checkbox path="iconDescription.colorBody" value="Yes" />Yes
           </td>
      </tr>         
      <tr>  
           <td><label_same_row>Through Color:</label_same_row>
               <form:checkbox path="iconDescription.throughColor" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
            <td><label_same_row>Chiseled Edge:</label_same_row>
               <form:checkbox path="iconDescription.chiseledEdge" value="Yes" />Yes
            </td>
      </tr>      
      <tr>   
            <td><label_same_row>Rectified Edge:</label_same_row>
               <form:checkbox path="iconDescription.rectifiedEdge" value="Yes" />Yes
            </td>
      </tr> 
      <tr>
          <td><label_same_row>Green Friendly:</label_same_row>
             <form:checkbox path="iconDescription.greenFriendly" value="Yes" />Yes
          </td>
      </tr> 
      <tr>    
          <td><label_same_row>Exterior Product:</label_same_row>
              <form:checkbox path="iconDescription.exteriorProduct" value="Yes" />Yes
          </td>
      </tr> 
      <tr>
            <td><label_same_row>Versailles Pattern:</label_same_row>
               <form:checkbox path="iconDescription.versaillesPattern" value="Yes" />Yes
           </td>
      </tr>
           
      <tr>     
           <td><label_same_row>Ada Accessibility</label_same_row>: 
               <form:checkbox path="iconDescription.adaAccessibility" value="Yes" />Yes
           </td>
      </tr>      
      <tr>
          <td><label_same_row>Coefficient of Friction:</label_same_row>
             <form:checkbox path="iconDescription.coefficientOfFriction" value="Yes" />Yes
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
