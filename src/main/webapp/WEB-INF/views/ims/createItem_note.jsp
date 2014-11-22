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
<spring:url var="action" value="/ims/createItem_final" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
<table>
   
    <div class="container" style="color:Green"> <h3>Enter Notes</h3></div>
       <table>
          <tr>
              <td>PO Notes:      <form:textarea path="notes.ponotes"></form:textarea></td>
          </tr>
          <tr>
              <td>Buyer's Notes: <form:textarea path="notes.buyernotes"></form:textarea></td>
          </tr>
          <tr>
              <td>Invoice Notes: <form:textarea path="notes.invoicenotes"></form:textarea></td>
          </tr>
          <tr>
              <td>Internal Notes: <form:textarea path="notes.internalnotes"></form:textarea></td>
          </tr>
    </table>   
    </td></tr>
    </table>
    
      </table>
    <table></table>
    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
            <input type="submit" value="Create Item"/>
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
