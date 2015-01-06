<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
     <title>Item Management System -- Error Page </title>
  </head>
  <body>
      <div class="container">
              <h3 style="margin-bottom: 10px; padding-bottom: 0px; border-bottom: 1px #ccc dotted;">There was a problem</h3>
              <p style="font-size: 1.3em;">Your request could not be completed at this time.</a></p>
     <hr />
     <%
       try {
        // The Servlet spec guarantees this attribute will be available
           Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
           if (exception != null) {
              if (exception instanceof ServletException) {
              // It's a ServletException: we should extract the root cause
                  ServletException sx = (ServletException) exception;
                  Throwable rootCause = sx.getRootCause();
                  if (rootCause == null)
                      rootCause = sx;
                  out.println("** Root cause is: " + rootCause.getMessage());
                  rootCause.printStackTrace(new java.io.PrintWriter(out));
               } 
               else {
                 // It's not a ServletException, so we'll just show it
                   exception.printStackTrace(new java.io.PrintWriter(out));
               }
            }  
            else {
                 out.println("No error information available");
          }
          // Display cookies
          out.println("\nCookies:\n");
          Cookie[] cookies = request.getCookies();
          if (cookies != null) {
              for (int i = 0; i < cookies.length; i++) {
                   out.println(cookies[i].getName() + "=[" + cookies[i].getValue() + "]");
              }
          }
     } 
     catch (Exception ex) {
        ex.printStackTrace(new java.io.PrintWriter(out));
     }
     %>
      <table>
          <tr><td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span>IMS Home</span></a></td><tr>
      </table>
       
</div>
<!-- Close container -->
</body>
</html>