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

<spring:url var="action" value="/ims/createItem_dimension" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
  <table>
    <div class="container" style="color:GREEN"> <h3>Enter Material Information</h3></div>
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
         <td>Material Feature: <form:input path="material.materialfeature"></form:input></td>
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
</table>  
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
