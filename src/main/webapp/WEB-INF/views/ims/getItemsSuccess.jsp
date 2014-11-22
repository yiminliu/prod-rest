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
         <c:when test="${empty itemList}">
             <div class="container">No Item Found</div>
         </c:when>
         <c:otherwise>
             <div class="container" style="color:GREEN"><h3>Items</h3></div>
         </c:otherwise>
      </c:choose>
      <br/>
      <p></p>
      <div id="main-content">
             <div class="span-18 colborder">
                 <c:if test="${!empty itemList}">
                    <table border="1">
                       <tr>
                         <th>Item Code</th>
                         <th>Item Description</th>
                         <th>Category</th>
                         <th>Serious Name</th>
                         <th>Active Status</th>
                         <th>Color Category</th>
                         <th>Origin</th>
                         <th>MPS</th>
                         <th>Grade</th>
                         <th>Status</th>
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
                           <td>${item.countryorigin}</td>
                          <td>${item.newFeature.mpsCode}</td>
                          <td>${item.newFeature.grade}</td>
                          <td>${item.newFeature.status}</td>
                       </tr>
                     </c:forEach> 
                   </table> 
                </c:if>
             </div>
               
             <table cellspacing="40"; border-spacing: 10px>
                <tr>
                   <td padding-right: 20px;>
                      <a id="imsSearch" href="<spring:url value="/ims/getItem" />" class="button action-m"><span>Item Search Page</span></a>
                      <a id="imsHome" href="<spring:url value="/ims/index" />" class="button action-m"><span>Ims Management Home</span></a>
                   </td>
                </tr>
             </table>
      </div> <!-- close main-content -->
   </div> <!-- Close container -->
</body>
</html>