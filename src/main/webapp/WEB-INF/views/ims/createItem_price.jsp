<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
  <title>Item Management System -- pricing</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
    $(function() {
    	 $( "#from" ).datepicker({
    		 defaultDate: "+1w",
    		 changeMonth: true,
    		 numberOfMonths: 1,
    		 onClose: function( selectedDate ) {
    		 $( "#to" ).datepicker( "option", "minDate", selectedDate );
    		 }
    		 });
    		 $( "#to" ).datepicker({
    		 defaultDate: "+1w",
    		 changeMonth: true,
    		 numberOfMonths: 1,
    		 onClose: function( selectedDate ) {
    		 $( "#from" ).datepicker( "option", "maxDate", selectedDate );
    		 }
    		 });
    		 });
  </script>
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
          <td><label> Special Price Start Date: </label>
              <form:input id="from" path="price.tempdatefrom" type="text"></form:input></td>
          <td><form:errors path="price.tempdatefrom" cssClass="error" /></td>
       </tr>
       <tr>
          <td><label> Special Price End Date: </label>
              <form:input id="to" path="price.tempdatethru" type="text"></form:input></td>
          <td><form:errors path="price.tempdatethru" cssClass="error" /></td>
       </tr>     
    </table>  

    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
             <input type="submit" value="Continue >>"/>
        </td>
      </tr>
    </table> 
</form:form>

</div><!-- container -->
</body>
</html>
