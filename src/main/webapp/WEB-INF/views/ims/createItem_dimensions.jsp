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
   
    <div style="color:GREEN"> <h3>Dimensions</h3></div>
    <table class="category">
      <tr>
        <td><label for="length">Length: </label>
            <form:input path="dimensions.length" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;color:#0076BF;"></form:input>
        </td>
      </tr>
      <tr>
        <td><label for="nominallength">Nominal Length: </label> 
            <form:input path="dimensions.nominallength" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;color:#0076BF;"></form:input>
        </td>
      </tr>
      <tr>
        <td><label for="width">Width: </label>
            <form:input path="dimensions.width" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;color:#0076BF;"></form:input>
        </td>
      </tr>
      <tr>
         <td><label for="nominalwidth">Nominal Width: </label>
            <form:input path="dimensions.nominalwidth" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;color:#0076BF;"></form:input>
         </td>
      </tr>
      <tr>
        <td><label for="thickness">Thickness: </label>
            <form:input path="dimensions.thickness" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;color:#0076BF;"></form:input>
        </td>
      </tr>
      <tr>
        <td><label for="nominalthickness">Nominal Thickness: </label>
            <form:input path="dimensions.nominalthickness" cssErrorClass="span-8 validationFailed" cssStyle="width:175px;color:#0076BF;"></form:input>
        </td>
      </tr>
      <tr>
        <td><span style="color:black;">Size Unit :</span>
            <form:radiobutton path="dimensions.sizeunits" value="E" />E
            <form:radiobutton path="dimensions.sizeunits" value="M" />M
        </td>
         </tr>
      <tr>
        <td><span style="color:black;">Thickness Unit:</span>
            <form:radiobutton path="dimensions.thicknessunit" value="E" />E
            <form:radiobutton path="dimensions.thicknessunit" value="M" />M
        </td>
      </tr>     
    </table>
    <table> 
      <tr style="float:middle;"> 
        <td>
            <input type="submit" value="Continue >>"/>
        </td>
      </tr>
    </table> 
</form:form>

</div><!-- container -->
</body>
</html>
