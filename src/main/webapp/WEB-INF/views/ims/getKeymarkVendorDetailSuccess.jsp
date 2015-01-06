<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <title>Vendor Detail</title>
</head>
<body>
   <%@ include file="/WEB-INF/includes/header.jsp"%>
   <div class="container">
      <c:choose>
         <c:when test="${empty kVendor}">
             <div class="container">No Item Found</div>
         </c:when>
         <c:otherwise>
             <div class="page_title">Vendor Information for Vendor Number: <span style="color : red">${kVendor.vendorNumber}</span></div>
         </c:otherwise>
      </c:choose>
      <br/>
      <p></p>
      <div class="container">
                  <c:if test="${!empty kVendor}">
                      <table border="1">
                       <tr>
                         <!--<th>Item Code</th>-->
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
                       </tr>  
                       <tr>
                          <!--<td style="color : red">${item.itemcode}</td>-->
                          <td>${kVendor.name}</td>
                          <td>${kVendor.dbaname}</td>
                          <td>${kVendor.addr1}</td>
                          <td>${kVendor.addr2}</td>
                          <td>${kVendor.city}</td>
                          <td>${kVendor.statecd}</td>
                          <td>${kVendor.zip}</td>
                          <td>${kVendor.countrycd}</td>
                          <td>${kVendor.email}</td>
                          <td>${kVendor.poemail}</td>
                          <td>${kVendor.phone}</td>
                          <td>${kVendor.fax}</td>
                          <td>${kVendor.fobcd}</td>
                          <td>${kVendor.glacct}</td>
                          <td>${kVendor.alwaysonhold}</td>
                       </tr>
                    </table>
                 </c:if>
       <table>
          <tr>
             <td>
                <a id="imsHome" href="<spring:url value="/ims/index" />" class="button action-m"><span>Back To IMS Home</span></a>
             </td>
          </tr>
       </table>
       <%@ include file="/WEB-INF/includes/footer.jsp"%>
</div> <!-- Close container -->
</body>
</html>