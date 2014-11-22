<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>

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
<div id="main-content">
<div class="span-18 colborder">
<spring:url var="action" value="/ims/createItem_application" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
   
    <div class="container" style="color:GREEN"> <h3>Pricing</h3></div>
    <table class="category">
       <tr>
          <td>MSRP: <form:input path="price.sellprice"></form:input></td>
       </tr>
       <tr>
         <td><form:errors path="price.sellprice" cssClass="error" /></td>               
      </tr>
       <tr>
          <td>Special Price: <form:input path="price.tempprice"></form:input></td>
       </tr>
       <tr>
          <td>Special Price Start Date (mm/dd/yyyy): <form:input path="price.tempdatefrom" value=""></form:input></td>
       </tr>
       </tr>
          <td>Special Price End Date (mm/dd/yyyy): <form:input path="price.tempdatethru" value=""></form:input></td>
       </tr>     
    </table>  
    <table></table>
    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
             <input type="submit" value="Continue"/>
        </td>
      </tr>
    </table> 
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
