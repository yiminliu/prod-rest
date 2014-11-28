<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">

<spring:url var="action" value="/ims/createItem_price" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
    <div style="color:GREEN"> <h3>Material Information</h3></div>
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
       </tr>
       <tr>    
         <td><label for="materialCategory">Material Category:</label>
            <form:select id="materialCategory" path="material.materialcategory" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="materialCategory" items="${materialCategoryList}" varStatus="status">
                 <form:option value="${materialCategory}">${materialCategory.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>   
       </tr>
       <tr>  
         <td><label for="materialClass">Material Class:</label>
            <form:select id="materialClass" path="material.materialclass" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="materialClass" items="${materialClassList}" varStatus="status">
                 <form:option value="${materialClass}">${materialClass.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>
       </tr>
       <tr>   
         <td><label for="materialStyle">Material Style:</label>
            <form:select id="materialStyle" path="material.materialstyle" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="materialStyle" items="${materialStyleList}" varStatus="status">
                 <form:option value="${materialStyle}">${materialStyle.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td> 
       </tr>
       <tr>    
         <td><label for="materialFeature">Material Feature:</label>
             <form:input path="material.materialfeature" cssStyle="width:175px;"></form:input>
         </td>
        </tr>
    </table> 
    <div style="color:GREEN"> <h3>Dimension</h3></div>
    <table class="category">
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
        <td>Size Unit :
            <form:radiobutton path="dimensions.sizeunits" value="E" />E
            <form:radiobutton path="dimensions.sizeunits" value="M" />M
        </td>
         </tr>
      <tr>
        <td>Thickness Unit:
            <form:radiobutton path="dimensions.thicknessunit" value="E" />E
            <form:radiobutton path="dimensions.thicknessunit" value="M" />M
        </td>
      </tr>     
    </table>
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
