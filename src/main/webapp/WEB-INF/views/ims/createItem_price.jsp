<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">
<spring:url var="action" value="/ims/createItem_application" />
<form:form method="POST" action="${action}" modelAttribute="aItem" enctype="application/x-www-form-urlencoded">
    <div style="color:GREEN"> <h3>Pricing</h3></div>
    <table class="category">
       <tr>
          <td><label> MSRP($)<span style="color:red;">*</span>: </label><form:input path="price.sellprice"></form:input></td> 
       </tr>
       <tr>
          <td><form:errors path="price.sellprice" cssClass="error" /></td>
       </tr>
       <tr>
          <td><label> Special Price($): </label><form:input path="price.tempprice"></form:input></td>
          <td><form:errors path="price.tempprice" cssClass="error" /></td>
       </tr>
       <tr>
          <td><label> Special Price Start Date (mm/dd/yyyy): </label>
              <form:input path="price.tempdatefrom" type="date"></form:input></td>
          <td><form:errors path="price.tempdatefrom" cssClass="error" /></td>
       </tr>
       <tr>
          <td><label> Special Price End Date (mm/dd/yyyy): </label>
              <form:input path="price.tempdatethru" type="date"></form:input></td>
          <td><form:errors path="price.tempdatethru" cssClass="error" /></td>
       </tr>     
    </table>  

    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
             <input type="submit" value="Continue"/>
        </td>
      </tr>
    </table> 
</form:form>

</div><!-- container -->
</body>
</html>
