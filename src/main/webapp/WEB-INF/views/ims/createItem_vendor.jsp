<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System -- Create An Item</title>
</head>
<body>
<%@ include file="/WEB-INF/includes/header.jsp"%>
<div class="container">
<spring:url var="action" value="/ims/createItem_icon" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
    <div style="color:Green"> <h3>Vendors</h3></div>
    <table class="category">
     <c:forEach items="${newVendorSystem}" varStatus="loop">
       <tr>
          <!--<td style="width:20">Order#: <form:input path="newVendorSystem[${loop.index + 1}].vendorOrder"></form:input></td>-->  
          <td style="width:20"><label for="vendorOrder"> </label>
                 <form:hidden path="newVendorSystem[${loop.index}].vendorOrder" value="${loop.index + 1}"/>${loop.index + 1}. 
          </td> 
          <td>
            <c:choose>
             <c:when test="${loop.index == 0}">       
                <label>Vendor Number<span style="color:red;">*</span>:</label> <form:input path="newVendorSystem[${loop.index}].vendorId.id"></form:input>
             </c:when>
             <c:otherwise>
                 <label>Vendor Number:</label> <form:input path="newVendorSystem[${loop.index}].vendorId.id"></form:input>
             </c:otherwise> 
            </c:choose>    
            <form:errors path="newVendorSystem[${loop.index}].vendorId.id" cssClass="error" />  
          </td>    
          <!-- <td>Vendor Name: <form:input path="newVendorSystem[${loop.index}].vendorName"></form:input></td>
          <td>Vendor Name2: <form:input path="newVendorSystem[${loop.index}].vendorName2"></form:input></td>-->
          <td> <label>Vendor XrefId:</label> <form:input path="newVendorSystem[${loop.index}].vendorXrefId"></form:input></td>
          <td><label>List Price:</label> <form:input path="newVendorSystem[${loop.index}].vendorListPrice" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input>
                <form:errors path="newVendorSystem[${loop.index}].vendorListPrice" cssClass="error" />
          </td>
          <td><label for="stdunitOptions">Price Unit: </label>
            <form:select id="vendorPriceUnit" path="newVendorSystem[${loop.index}].vendorPriceUnit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="vendorPriceUnit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${vendorPriceUnit}">${vendorPriceUnit}</form:option>
              </c:forEach>
            </form:select>
            <form:errors path="newVendorSystem[${loop.index}].vendorPriceUnit" cssClass="error" />
          </td>
          <td><label>Discount Pct:</label> <form:input path="newVendorSystem[${loop.index}].vendorDiscountPct" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:160px;"></form:input>
             <form:errors path="newVendorSystem[${loop.index}].vendorDiscountPct" cssClass="error" />
          </td>
          <td><label>Price Round Accuracy:</label> <form:input path="newVendorSystem[${loop.index}].vendorPriceRoundAccuracy" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:160px;"></form:input>
             <form:errors path="newVendorSystem[${loop.index}].vendorPriceRoundAccuracy" cssClass="error" />
          </td>
          <td><label>Markup Pct:</label> <form:input path="newVendorSystem[${loop.index}].vendorMarkupPct" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input>
              <form:errors path="newVendorSystem[${loop.index}].vendorMarkupPct" cssClass="error" />
          </td>
          <td><label>Freight Rate Cwt:</label> <form:input path="newVendorSystem[${loop.index}].vendorFreightRateCwt" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:160px;"></form:input>
             <form:errors path="newVendorSystem[${loop.index}].vendorFreightRateCwt" cssClass="error" />
          </td>
          <td><label>Lead Time:</label> <form:input path="newVendorSystem[${loop.index}].leadTime" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
          <td><label>Duty Pct:</label> <form:input path="newVendorSystem[${loop.index}].dutyPct" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:80px;"></form:input></td>
       </tr>
     </c:forEach>  
     </table>
    <table> 
      <tr style="float:middle;"> 
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
