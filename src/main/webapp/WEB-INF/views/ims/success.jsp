<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Success Page</title>
</head>
<body>
   <%@ include file="/WEB-INF/includes/header.jsp"%>
   <div class="home_container">
      <c:choose>
         <c:when test="${empty itemCode}">
           <div class="container">Error occurred</div>
         </c:when>
         <c:otherwise>
            <h2>Item: <span style="color:RED">${itemCode} </span>Has Been ${operation} Successfully!</h2>
         </c:otherwise>
      </c:choose>
    
     <table style="border:1px">
        <tr>
           <td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span>IMS Home</span></a></td>
        </tr>
     </table>
     <%@ include file="/WEB-INF/includes/footer.jsp"%>
   </div> <!-- Close container -->
</body>
</html>
