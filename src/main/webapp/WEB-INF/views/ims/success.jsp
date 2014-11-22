<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Delete Item</title>
<style type="text/css"> 
 
    .button-m {
       display: block;
       width: 115px;
       height: 25px;
       background: #4E9CAF;
       padding: 10px;
       text-align: center;
       border-radius: 5px;
       color: white;
       font-weight: bold;
    }
 </style>
</head>
<body>
   <div class="container">
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
           <td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span>Item Management System Home</span></a></td>
        </tr>
     </table>
   </div> <!-- Close container -->
</body>
</html>
