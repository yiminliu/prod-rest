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

<form:form method="POST" modelAttribute="item">
   <div class="container" style="color:GREEN"> <h3>Delete An Item</h3></div>
   <table class="category">
      <tr>
         <td>Item Code: <form:input path="itemcode"></form:input></td>
      </tr>
      <tr>
         <td><form:errors path="itemcode" cssClass="error" /></td>               
       </tr>
   </table>
   <table></table>
   <table> 
      <tr style="float:middle;"> 
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
