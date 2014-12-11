<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">
<div id="main-content">
<div class="span-18 colborder">
<h3 style="margin-bottom: 10px; padding-bottom: 0px;">Enter The Base Item Code</h3>

<form:form method="GET" action="cloneItem" modelAttribute="item">
   <table>
    <tr>
        <td><form:label path="itemcode">Item Code:</form:label></td>
        <td><form:input path="itemcode" /></td>
    </tr>
    <tr>
        <td><form:errors path="itemcode" cssClass="error" /></td> 
    </tr>             
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
