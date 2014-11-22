<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Ims Menu</title>
<style type="text/css"> 
 
    .button-m {
       display: block;
       width: 130px;
       height: 25px;
       background: #4E9CAF;
       padding: 10px;
       text-align: center;
       text-decoration: none;
       border-radius: 5px;
       color: white;
       font-weight: bold;
    }
 </style>
</head>
<body>
<div class="container">
<c:choose>
<c:when test="${empty item}">
<div class="container">Error occured when create item</div>
</c:when>
<c:otherwise>
<h2>Item Code: <span style="color:RED">${item.itemcode} </span>Has Been Created Successfully!</h2>
</c:otherwise>
</c:choose>
</div>

</td>
</tr>
</table>
<table style="border:1px">
  <tr>
     <td><a id="viewItem" href="<spring:url value="/ims/getItemDetail/${item.itemcode}" />" class="button-m"><span>View The Item</span></a><td>
     <td><a id="modifyItem" href="<spring:url value="/ims/updateItem/${item.itemcode}" />" class="button-m"><span>Modify The Item</span></a></td>
     <td><a id="deleteItem" href="<spring:url value="/ims/deleteItem/${item.itemcode}" />" class="button-m"><span>Delete The Item</span></a></td>
     <td><a id="CreateItem" href="<spring:url value="/ims/createItem" />" class="button-m"><span>Create Another Item</span></a></td>
     <td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span>Home</span></a></td>
  </tr>
  </table>
</div> <!-- close main-content -->
</div> <!-- Close container -->
</body>
</html>
