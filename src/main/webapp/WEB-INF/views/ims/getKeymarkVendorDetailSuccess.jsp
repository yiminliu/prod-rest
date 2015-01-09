<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="en">
<head>
  <title>Vendor Detail</title>
</head>
<body>
   <%@ include file="/WEB-INF/includes/header.jsp"%>
   <div class="home-container">
      <c:choose>
         <c:when test="${empty kVendor}">
             <div class="container">No Item Found</div>
         </c:when>
         <c:otherwise>
             <div class="page_title">Vendor Information for Vendor Number: <span style="color : red">${kVendor.vendorNumber}</span></div>
         </c:otherwise>
      </c:choose>
      <p></p>
      <div>
            <c:if test="${!empty kVendor}">
                 <table border="1">
                     <tr>
                         <th>Name</th>
                         <th>Dba Name</th>
                         <th>Address1</th>
                         <th>Address2</th>
                         <th>City</th>
                         <th>States</th>
                         <th>Zip</th>
                         <th>Country</th>
                         <th>Email</th>
                         <th>poEmail</th>
                         <th>Phone</th>
                         <th>Fax</th>
                         <th>Fob Code</th>
                         <th>Gla Account</th>
                         <th>Always On-Hold</th>
                         <th>Discount %</th>
                         <th>Freight Rate Cwt</th>
                    </tr>  
                    <tr>
                          <td>${kVendor.name}</td>
                          <td>${kVendor.dbaname}</td>
                          <td>${kVendor.addr1}</td>
                          <td>${kVendor.addr2}</td>
                          <td>${kVendor.city}</td>
                          <td>${kVendor.statecd}</td>
                          <td>${kVendor.zip}</td>
                          <td>${kVendor.countrycd}</td>
                          <td>${kVendor.email}</td>
                          <td>${kVendor.poEmail}</td>
                          <td>${kVendor.phone}</td>
                          <td>${kVendor.fax}</td>
                          <td>${kVendor.fobcd}</td>
                          <td>${kVendor.glacct}</td>
                          <td>${kVendor.alwaysonhold}</td>
                          <td>${kVendor.discountpct}</td>
                          <td>${kVendor.vendorfreightratecwt}</td>
                    </tr>
                 </table>
           </c:if>
     </div>
     <table class="table_center">
              <tr>
                 <td><a id="viewItem" href="<spring:url value="/ims/getItemDetail/${item.itemcode}" />" class="button-l"><span>Back to The Item Detail</span></a></td>
                 <td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span>IMS Home</span></a></td>
              </tr>
     </table>
     <%@ include file="/WEB-INF/includes/footer.jsp"%>
   </div> <!-- Close container -->
</body>
</html>