<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <title>Ims Menu</title>
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
      <c:choose>
         <c:when test="${empty kVendor}">
             <div class="container">No Item Found</div>
         </c:when>
         <c:otherwise>
             <div class="container" style="text-align: middle;"><h2>Vendor Information for Vendor Number: <span style="color : red">${kVendor.vendorNumber}</span></h2></div>
         </c:otherwise>
      </c:choose>
      <br/>
      <p></p>
      <div class="container">
         <div id="main-content">
             <div class="span-18 colborder">
                 <c:if test="${!empty kVendor}">
                      <table border="1">
                       <tr>
                         <!--<th>Item Code</th>-->
                         <th>Name</th>
                         <th>Dba Name</th>
                         <th>Addr1</th>
                         <th>Addr2</th>
                         <th>City</th>
                         <th>States</th>
                         <th>Zip</th>
                         <th>Country</th>
                         <th>Email</th>
                         <th>Phone</th>
                         <th>Fax</th>
                         <th>Fob Code</th>
                         <th>Gla Account</th>
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
                          <td>${kVendor.phone}</td>
                          <td>${kVendor.fax}</td>
                          <td>${kVendor.fobcd}</td>
                          <td>${kVendor.glacct}</td>
                                </tr>
                    </table>
                 </c:if>
<table cellspacing="10">
<tr>
<td>
<a id="imsHome" href="<spring:url value="/ims/index" />" class="button action-m"><span>Back To Ims Management Home</span></a>
</td>
</tr>
</table>
</div> <!-- close main-content -->
</div> <!-- Close container -->
</body>
</html>