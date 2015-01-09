<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>Item Management System -- Update Item</title>
</head>
<body>
  <%@ include file="/WEB-INF/includes/header.jsp"%>
   <div class="home_container">
   <div class="page_title">Update An Item</div>
   <form:form method="GET" action="updateItem_begin" modelAttribute="item">
     <table class="table_center">
        <tr>
           <td><form:label path="itemcode">Enter Item Code:</form:label></td>
           <td><form:input path="itemcode" /></td>
        </tr>
        <tr>
           <td><form:errors path="itemcode" cssClass="error" /></td>               
        </tr>
     </table>
     <table class="element_center">    
        <tr>
           <td>
               <input name="submit" type="submit" value="Submit"/>
           </td>
        </tr>
     </table>  
   </form:form>
   <%@ include file="/WEB-INF/includes/footer.jsp"%>
</div><!-- container -->
</body>
</html>
