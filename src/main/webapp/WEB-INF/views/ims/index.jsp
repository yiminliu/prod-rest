<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Ims Menu</title>
  <style type="text/css"> 
     table.category { 
        border-bottom: dotted 1px blue;
     }
     .container {
        color:#0076BF;
        margin: 30px auto; 
        padding: 30px;
        border-spacing: 10px;
        empty-cells:show;
        width:90%;
        text-align:left;
      } 
    </style>  
 </head>
<body>
<div class="container">
   <div id="main-content">
         <H2>Item Management System</H2>
         <table>
            <tr style="float:middle;"> 
               <td><a id="searchItem" href="<spring:url value="/ims/getItem"/>"><span>Search Item</span></a></td>
            </tr>
            <tr style="float:middle;">   
                <td><a id="createItem" href="<spring:url value="/ims/createItem_begin"/>"><span>Create Item</span></a></td>
            </tr>
            <tr style="float:middle;"> 
                <td><a id="updateItem" href="<spring:url value="/ims/updateItemInitForm"/>"><span>Update Item</span></a></td>
            </tr>  
            <tr style="float:middle;"> 
                 <td><a id="updateItem" href="<spring:url value="/ims/deleteItem"/>"><span>Delete Item</span></a></td>
            </tr>
            <tr style="float:middle;"> 
                <td><a id="updateItem" href="<spring:url value="/ims/deactivateItem"/>"><span>Deactivate Item</span></a></td>
            </tr>
         </table>
  </div><!-- close main-content -->
</div> <!-- Close container -->
</body>
</html>