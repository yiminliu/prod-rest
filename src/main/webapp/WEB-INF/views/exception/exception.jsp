<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
     <title>Item Management System - Page Not Found</title>
  </head>
  <body>
      <div class="container">
         <h1 class="error">Error!</h1>
         
         <c:if test="${not empty errorCode}">
		    <h1>Error Code: ${errorCode}</h1>
	     </c:if>
       	 <c:if test="${not empty errorMessage}">
		    <h4>Error Message:</h4> ${errorMessage}
	     </c:if>
         <table>
           <!--<tr>
              <c:if test="${not empty url}">
                 <td><h4>Failed URL:</h4> ${url}</td>
              </c:if>    
           </tr>
           <c:if test="${not empty error.getCause()}">
              <tr>   
                 <td><h4>Root Cause:</h4>  ${error.getCause().getMessage()}</td>
              </tr>
           </c:if>-->   
              <c:if test="${not empty error}">
              <tr>
                 <td><h4>Stack Trace:</h4>
                    <c:forEach items="${error.stackTrace}" var="ste">   
                      ${ste} 
                    </c:forEach>
                  </td>
             </tr>  
            </c:if>    
           </table>
           <table>
             <tr>
               <td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span>Home</span></a></td>
             </tr>
           </table>
      </div><!-- Close container -->
 </body>
</html>
