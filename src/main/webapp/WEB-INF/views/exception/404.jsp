<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
     <title>Item Management System - Page Not Found</title>
  </head>
  <body>
    <div> <h2>HTTP Status 404 -- Page Not Found</h2></div>
    <div class="container">
            <h3 style="margin-bottom: 10px; padding-bottom: 0px; border-bottom: 1px #ccc dotted;">There was a problem</h3>
            <p>The page that you requested could not be found. Please check the link or URL and try again.</p>
    </div>
    <table>
             <tr>
               <td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span>IMS Home</span></a></td>
             </tr>
    </table>
    <%@ include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>