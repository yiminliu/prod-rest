<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">
<spring:url var="action" value="/ims/cloneItem" />
<form:form method="POST" action="${action}" modelAttribute="item">
  <div style="color:Green"> <h3>Edit Pertaining Information to Create a New Item</h3></div>
   <table class="category">
      <tr>
         <td><label>New Item Code<span style="color:red;">*</span>: </label>  <form:input path="itemcode" style="text-transform:uppercase;"></form:input><small>(maximum 18 characters)</small></td>
      </tr>
      <tr>
         <td><form:errors path="itemcode" cssClass="error" /></td>               
      </tr>
      <tr>  
         <td><label>Series Color<span style="color:red;">*</span>: </label>  <form:input path="series.seriescolor"></form:input><small>(maximum 30 characters)</small></td>
      </tr>
      <tr>
         <td><form:errors path="series.seriescolor" cssClass="error" /></td>               
      </tr>
      <tr>    
         <td><label>Item Description<span style="color:red;">*</span>: </label><form:input path="itemdesc.itemdesc1" size="50"></form:input><small>(maximum 35 characters)</small></td>
      </tr>
       <tr>
         <td><form:errors path="itemdesc.itemdesc1" cssClass="error" /></td>               
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
       <td>
           <label for="ColorHuesOptions">Color Hues: </label>
           <c:forEach var="colorHue" items="${colorList}" varStatus="status">
              <span style="padding-left: 5px;padding-bottom:3px; font-size: 12px;">
                <form:checkbox path="colorhues" value="${colorHue}" />${colorHue}
              </span>  
           </c:forEach>
       </td> 
     </tr>
      <tr>
        <td><label for="length">Length: </label>
            <form:input path="dimensions.length" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;"></form:input>
        </td>
      </tr>
      <tr>
        <td><label for="nominallength">Nominal Length: </label> 
            <form:input path="dimensions.nominallength" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;"></form:input>
        </td>
      </tr>
      <tr>
        <td><label for="width">Width: </label>
            <form:input path="dimensions.width" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;"></form:input>
        </td>
      </tr>
      <tr>
         <td><label for="nominalwidth">Nominal Width: </label>
            <form:input path="dimensions.nominalwidth" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;"></form:input>
         </td>
      </tr>
      <tr>
        <td><label for="thickness">Thickness: </label>
            <form:input path="dimensions.thickness" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;"></form:input>
        </td>
      </tr>
      <tr>
        <td><label for="nominalthickness">Nominal Thickness: </label>
            <form:input path="dimensions.nominalthickness" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;"></form:input>
        </td>
      </tr>
      <tr>
        <td><span style="color:black;">Size Unit :</span>
            <form:radiobutton path="dimensions.sizeunits" value="E" />E
            <form:radiobutton path="dimensions.sizeunits" value="M" />M
        </td>
         </tr>
      <tr>
        <td><span style="color:black;">Thickness Unit:</span>
            <form:radiobutton path="dimensions.thicknessunit" value="E" />E
            <form:radiobutton path="dimensions.thicknessunit" value="M" />M
        </td>
      </tr>     
    </table>
    </table>
  
   <br/>
   <table> 
      <tr > 
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
      </tr>
   </table> 
</form:form>
</div><!-- container -->
</body>
</html>
