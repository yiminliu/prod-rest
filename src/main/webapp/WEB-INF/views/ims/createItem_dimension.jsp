<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">
<spring:url var="action" value="/ims/createItem_price" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
    <div style="color:GREEN"> <h3>Dimension</h3></div>
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
    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
            <input type="submit" value="Continue"/>
            <!--<a id="createItemPage2" href="<spring:url value="/ims/createItemPage2"/>"><span>Next</span></a>-->
        </td>
      </tr>
    </table> 
</form:form>

</div><!-- container -->
</body>
</html>
