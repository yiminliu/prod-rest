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
<spring:url var="action" value="/ims/createItem_price" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
<table>
   
    <div class="container" style="color:GREEN"> <h3>Enter Dimension Information</h3></div>
    <table class="category">
      <tr>
        <td>length: <form:input path="dimensions.length"></form:input></td>
        <td>nominallength: <form:input path="dimensions.nominallength"></form:input></td>
      </tr>
      <tr>
        <td>width: <form:input path="dimensions.width"></form:input></td>
        <td>nominalwidth: <form:input path="dimensions.nominalwidth"></form:input></td>
      </tr>
      <tr>
        <td>thickness: <form:input path="dimensions.thickness"></form:input></td>
        <td>nominalthickness: <form:input path="dimensions.nominalthickness"></form:input></td>
      </tr>
      <tr>
        <td><label for="sizeunitsOptions">Size Unit : </label>
            <form:radiobutton path="dimensions.sizeunits" value="E" />E
            <form:radiobutton path="dimensions.sizeunits" value="M" />M
        </td>
        <td><label for="thicknessunitOptions">Thickness Unit: </label>
            <form:radiobutton path="dimensions.thicknessunit" value="E" />E
            <form:radiobutton path="dimensions.thicknessunit" value="M" />M
        </td>
      </tr>     
    </table>
   
    <table></table>
    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
            <input type="submit" value="Continue"/>
            <!--<a id="createItemPage2" href="<spring:url value="/ims/createItemPage2"/>"><span>Next</span></a>-->
        </td>
      </tr>
    </table> 
  </table>  
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
