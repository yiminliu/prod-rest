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
      <div><h2>Item Detail</h2></div>
      <form:form method="POST" modelAttribute="item">
        <div id="main-content">
             <div class="span-18 colborder" style="color:GREEN"><h3>General Information</h3></div>
               
                 <div class="row">
                    <form:label path="${item.itemcode}">Item Code:</form:label>
                    <c:out value="${item.itemcode}"/>
                    <form:input path="itemcode" type="hidden"/>
                 </div>
                  <div class="row">
                    <form:label path="${item.itemdesc.itemdesc1}">Item Description</form:label>
                    <c:out value="${item.itemdesc.itemdesc1}"/>
                    <form:input path="itemdesc.itemdesc1" type="hidden"/>
                 </div>
                  <div class="row">
                    <form:label path="${item.itemcategory}">Category</form:label>
                    <c:out value="${item.itemcategory}"/>
                    <form:input path="itemcategory" type="hidden"/>
                 </div>
                  <div class="row">
                    <form:label path="${item.series.seriesname}">Serious Name</form:label>
                    <c:out value="${item.series.seriesname}"/>
                    <form:input path="series.seriesname" type="hidden"/>
                 </div>
                  <div class="row">
                    <form:label path="${item.series.seriescolor}">Series Color</form:label>
                    <c:out value="${item.series.seriescolor}"/>
                    <form:input path="series.seriescolor" type="hidden"/>
                 </div>
                 <div class="row">
                    <form:label path="${item.inactivecode}">Active Status</form:label>
                    <c:out value="${item.inactivecode}"/>
                    <form:input path="inactivecode" type="hidden"/>
                    <c:choose>
                             <c:when test="${item.inactivecode == 'N'}">
                                <td><c:out value="Active"/></td>
                             </c:when>
                             <c:otherwise>
                                <td><c:out value="Inative"/></td>
                             </c:otherwise>
                         </c:choose>
                 </div>
             
<table cellspacing="10">
<tr>
<td>
<a id="imsHome" href="<spring:url value="/ims/overview" />" class="button action-m"><span>Back To Ims Management Home</span></a>
</td>
</tr>
</table>
</div> <!-- close main-content -->
</form:form>
</div> <!-- Close container -->
</body>
</html>