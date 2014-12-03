<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Ims Menu</title>
 </head>
<body>
<div class="home-container">
   <div id="main-content">
          <div class="page_title">Item Management System</div>
          <br/>
          <div class="center">
            <ul class="menue"> 
               <li><a id="searchItem" href="<spring:url value="/ims/getItem"/>"><span>Search Item</span></a></li>
             
               <li><a id="createItem" href="<spring:url value="/ims/createItem_begin"/>"><span>Create Item</span></a></li>
          
               <li><a id="updateItem" href="<spring:url value="/ims/updateItemInitForm"/>"><span>Update Item</span></a></li>
           
               <li><a id="deleteItem" href="<spring:url value="/ims/deleteItem"/>"><span>Delete Item</span></a></li>
          
               <li><a id="deactivateItem" href="<spring:url value="/ims/deactivateItem"/>"><span>Deactivate Item</span></a></li>
            </ul>
         </div>
  </div><!-- close main-content -->
</div> <!-- Close container -->
</body>
</html>