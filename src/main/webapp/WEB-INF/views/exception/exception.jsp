<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
     <title>Item Management System - Page Not Found</title>
  </head>
  <body>
      <div class="container">
         <h1 class="error">Error!</h1>
         <p>Application has encountered an error. Please contact support on ...</p>
         Failed URL: ${url}
         Exception:  ${exception.message}
            <c:forEach items="${exception.stackTrace}" var="ste">   
               ${ste} 
            </c:forEach>
    
      </div><!-- Close container -->
 </body>
</html>