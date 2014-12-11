<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <title>Ims Menu</title>
</head>
<body>
   <div class="container">
      <c:choose>
         <c:when test="${empty itemList}">
             <div class="page_title">No Item Found!</div>
         </c:when>
         <c:otherwise>
           <div class="page_title">Search Result(${fn:length(itemList)} items)</div>
          </c:otherwise>
      </c:choose>
      <br/>
      <p></p>
      <div id="main-content">
             <div class="span-18 colborder">
                 <c:if test="${!empty itemList}">
                    <table border="1">
                       <tr style="color: BLACK">
                         <th>Item Code</th>
                         <th>Item Description</th>
                         <th>Category</th>
                         <th>Serious Name</th>
                         <th>Active Status</th>
                         <th>Color Category</th>
                         <th>Color</th>
                         <th>Origin</th>
                         <th>MPS</th>
                         <th>Grade</th>
                         <th>Status</th>
                         <th>Inventory Item Code</th>
                         <th>Edit</th>
                       </tr>
                       <c:forEach var="item" items="${itemList}" varStatus="status">  
                       <tr>
                          <td style="color : red"><a id="itemDetail" href="<spring:url value="/ims/getItemDetail/${item.itemcode}" />">${item.itemcode}</a></td>
                          <td>${item.itemdesc.itemdesc1}</td>
                          <td>${item.itemcategory}</td>
                          <td>${item.series.seriesname}</td>
                          <c:choose>
                             <c:when test="${item.inactivecode == 'N'}">
                                <td><c:out value="Active"/></td>
                             </c:when>
                             <c:otherwise>
                                <td><c:out value="Inative"/></td>
                             </c:otherwise>
                          </c:choose>
                          <td>${item.getColorHuesAsStrng()}</td>
                          <td>${item.series.seriescolor}</td>
                          <td>${item.countryorigin}</td>
                          <td>${item.newFeature.mpsCode}</td>
                          <td>${item.newFeature.grade}</td>
                          <td>${item.newFeature.status}</td>
                          <td>${item.inventoryitemcode}</td>
                          <td><a id="modifyItem" href="<spring:url value="/ims/updateItem/${item.itemcode}" />"><span>Edit</span></a></td>
                          <!-- <td><a id="deleteItem" href="<spring:url value="/ims/deleteItem/${item.itemcode}" />" class="button-m"><span>Delete</span></a></td>-->
                       </tr>
                     </c:forEach> 
                   </table> 
                </c:if>
             </div>
               
             <table  class="center" style="margin: 0 auto; cellspacing: 30px; cellpadding: 30px; border-spacing: 50px;">
                <tr>
                   <td style="cellspacing: 30px; cellpadding: 30px;">
                      <a id="imsSearch" href="<spring:url value="/ims/getItem" />" class="button-mn"><span style="cellspacing: 30px; cellpadding: 30px;">Item Search Page</span></a>
                      <a id="imsHome" href="<spring:url value="/ims/index" />" class="button-mn"><span style="cellspacing: 30px; cellpadding: 30px;">Ims Management Home</span></a>
                   </td>
                </tr>
             </table>
      </div> <!-- close main-content -->
   </div> <!-- Close container -->
</body>
</html>