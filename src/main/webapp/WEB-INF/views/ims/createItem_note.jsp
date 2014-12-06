<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">
<spring:url var="action" value="/ims/createItem_final" />
<form:form method="POST" action="${action}" modelAttribute="aItem">

    <div style="color:Green"> <h3>Web</h3></div>
    <table class="category"> 
        <tr>
           <td style="width: 100%; clear: both;">Show on Bedrosians:
              <form:radiobutton path="showonwebsite" value="Y" />Yes
              <form:radiobutton path="showonwebsite" value="N" />No
           </td>
        </tr>
        <tr>
           <td style="width: 100%; clear: both;"><form:errors path="showonwebsite" cssClass="error" /></td>               
      </tr>
        <tr>   
           <td>Show on AlysEdwards:
              <form:radiobutton path="showonalysedwards" value="Y" />Yes
              <form:radiobutton path="showonalysedwards" value="N" />No
           </td>
        </tr>
   </table>
   <div style="color:GREEN"> <h3>Buyers</h3></div>
   <table class="category"> 
     <tr>
        <td><label>Product Manager*: </label>
            <form:input path="purchasers.purchaser" cssStyle="width:155px;"></form:input>
        </td>
     </tr>
     <tr>
        <td><label>Buyer: </label>
        <form:input path="purchasers.purchaser2" cssStyle="width:155px;"></form:input></td>
     </tr>     
   </table>
    <div style="color:Green"> <h3>Notes</h3></div>
    <table>
          <tr>
              <td><label>PO Notes: </label>     <form:textarea path="notes.ponotes"></form:textarea></td>
          </tr>
          <tr>
              <td><label>Buyer's Notes: </label> <form:textarea path="notes.buyernotes"></form:textarea></td>
          </tr>
          <tr>
              <td><label>Invoice Notes: </label> <form:textarea path="notes.invoicenotes"></form:textarea></td>
          </tr>
          <tr>
              <td><label>Internal Notes: </label> <form:textarea path="notes.internalnotes"></form:textarea></td>
          </tr>
     </table>   
     <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
            <input type="submit" value="Create Item"/>
            <!--<a id="createItemPage2" href="<spring:url value="/ims/createItemPage2"/>"><span>Next</span></a>-->
        </td>
      </tr>
    </table> 
</form:form>

</div><!-- container -->
</body>
</html>
