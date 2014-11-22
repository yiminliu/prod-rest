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
<spring:url var="action" value="/ims/createItem_icon" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
<table>
   
    <div class="container" style="color:Green"> <h3>Enter Vendors Information</h3></div>
    <table class="category">
     <c:forEach items="${newVendorSystem}" varStatus="loop">
       <tr>
          <!--<td style="width:20">Order#: <form:input path="newVendorSystem[${loop.index}].vendorOrder"></form:input></td>-->  
          <td style="width:20"><label for="vendorOrder">Order#: </label>
            <form:select id="vendorOrder" path="newVendorSystem[${loop.index}].vendorOrder" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <form:option value="1">1</form:option>
               <form:option value="2">2</form:option>
               <form:option value="3">3</form:option>
            </form:select>   
          <td>Vendor Number: <form:input path="newVendorSystem[${loop.index}].vendorId.id"></form:input></td>
          <td>Vendor Name: <form:input path="newVendorSystem[${loop.index}].vendorName"></form:input></td>
          <td>Vendor Name2: <form:input path="newVendorSystem[${loop.index}].vendorName2"></form:input></td>
          <td>Vendor XrefId: <form:input path="newVendorSystem[${loop.index}].vendorXrefId"></form:input></td>
          <td>List Price: <form:input path="newVendorSystem[${loop.index}].vendorListPrice" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
          <td>Net Price: <form:input path="newVendorSystem[${loop.index}].vendorNetPrice" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
          <td><label for="stdunitOptions">Price Unit: </label>
            <form:select id="vendorPriceUnit" path="newVendorSystem[${loop.index}].vendorPriceUnit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="vendorPriceUnit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${vendorPriceUnit}">${vendorPriceUnit}</form:option>
              </c:forEach>
            </form:select>
          </td>
          <td>Price Round Accuracy: <form:input path="newVendorSystem[${loop.index}].vendorPriceRoundAccuracy" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:160px;"></form:input></td>
          <td>Freight Rate Cwt: <form:input path="newVendorSystem[${loop.index}].vendorFreightRateCwt" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
          <td>Lead Time: <form:input path="newVendorSystem[${loop.index}].leadTime" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
          <td>Duty Pct: <form:input path="newVendorSystem[${loop.index}].dutyPct" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
       </tr>
     </c:forEach>  
     </table>
  
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
