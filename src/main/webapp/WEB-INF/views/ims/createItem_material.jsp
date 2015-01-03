<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">

<spring:url var="action" value="/ims/createItem_dimensions" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
    <div style="color:GREEN"> <h3>Material Information</h3></div>
    <table class="category">
       <tr>    
         <td><label for="materialCategory">Material Category<span style="color:red;">*</span>:</label>
            <form:select id="materialCategory" path="material.materialcategory" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;color:#0076BF;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="materialCategory" items="${materialCategoryList}" varStatus="status">
                 <form:option value="${materialCategory}">${materialCategory.description}</form:option>
              </c:forEach>
            </form:select>
         </td>   
       </tr>
       <tr>
         <td><form:errors path="material.materialcategory" cssClass="error" /></td>               
       </tr>
       <tr>  
         <td><label for="materialType">Material Type<span style="color:red;">*</span>:</label>
            <form:select id="materialType" path="material.materialtype" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;color:#0076BF;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="materialType" items="${materialTypeList}" varStatus="status">
                 <form:option value="${materialType}">${materialType.description}</form:option>
              </c:forEach>
            </form:select>
         </td> 
       </tr>
       <tr>
         <td><form:errors path="material.materialtype" cssClass="error" /></td>               
       </tr>
       <tr>  
         <td><label for="materialClass">Material Class<span style="color:red;">*</span>:</label>
            <form:select id="materialClass" path="material.materialclass" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;color:#0076BF;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="materialClass" items="${materialClassList}" varStatus="status">
                 <form:option value="${materialClass}">${materialClass.description}</form:option>
              </c:forEach>
            </form:select>
         </td>
       </tr>
       <tr>
         <td><form:errors path="material.materialclass" cssClass="error" /></td>               
       </tr>
       <tr>   
         <td><label for="materialStyle">Material Style:</label>
            <form:select id="materialStyle" path="material.materialstyle" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:180px;color:#0076BF;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="materialStyle" items="${materialStyleList}" varStatus="status">
                 <form:option value="${materialStyle}">${materialStyle.description}</form:option>
              </c:forEach>
            </form:select>
         </td> 
       </tr>
        <tr>
         <td><form:errors path="material.materialstyle" cssClass="error" /></td>               
       </tr>
       <tr>    
         <td><label for="materialFeature">Material Feature:</label>
             <form:input path="material.materialfeature" cssStyle="width:175px; color:#0076BF;"></form:input>
         </td>
        </tr>
    </table> 
    
    <table> 
      <tr> 
        <td>
            <input type="submit" value="Continue >>"/>
        </td>
      </tr>
    </table>  
</form:form>

</div><!-- container -->
</body>
</html>
