<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
  <head>
     <title>Item Management System - Error Page</title>
  </head>
  <body>
      <div class="container">
         <h1 class="error">Error!</h1>
             <c:if test="${not empty errorType}">
		    <p><span class="error_title">Error Type: </span>${errorType}</p>
	     </c:if>
       	 <c:if test="${not empty errorMessage}">
		    <p><span class="error_title">Error Message:</span> ${errorMessage}</p>
	     </c:if>
         <c:if test="${not empty rootErrorMessage}">
            <p><span class="error_title">Root Cause:</span>  ${rootErrorMessage}</p>
         </c:if>  
         <c:if test="${not empty rootError}">
            <span class="error_title">Stack Trace:</span>>
               <c:forEach items="${rootError.stackTrace}" var="ste">   
                  <span style="font-siz: 75%;">${ste}</span>
               </c:forEach>
         </c:if>    
         <table>
             <tr>
               <td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span>IMS Home</span></a></td>
             </tr>
         </table>
         <%@ include file="/WEB-INF/includes/footer.jsp"%>
      </div><!-- Close container -->
 </body>
</html>
