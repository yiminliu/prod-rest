<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
     <title>Item Management System - Access Denied</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/includes/header.jsp"%>
    <div> <h2>HTTP Status 403 -- Access Is Denied</h2></div>
    <div class="container">
            <h3 style="margin-bottom: 10px; padding-bottom: 0px; border-bottom: 1px #ccc dotted;">There was a problem</h3>
            <p>Your request to the page is denied.</p>
    </div>
    <table>
             <tr>
               <td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span>IMS Home</span></a></td>
             </tr>
    <%@ include file="/WEB-INF/includes/footer.jsp"%>         
    </table>
</body>
</html>