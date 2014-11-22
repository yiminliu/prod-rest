<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>

  <style type="text/css"> 
     table.category { 
        border-bottom: dotted 1px  blue;
     }
     .container {
        color:#0076BF;
        margin: -10px 0px -10px 0px;
        border-spacing: 10px;
        empty-cells:show;
        width:90%;
        text-align:left;
      } 

      .table tr {
         height:15px;
         white-space:nowrap;
         text-align:left;
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

<form:form method="POST" modelAttribute="item">
   <div class="container" style="color:GREEN"> <h3>Deactivate An Item</h3></div>
   <table class="category">
      <tr>
         <td>Item Code: <form:input path="itemcode"></form:input></td>
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
