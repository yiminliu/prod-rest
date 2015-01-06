<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Ims Index</title>
 </head>
<body>
   <%@ include file="/WEB-INF/includes/header.jsp"%>
   <div id="menu" style="margin-top:50px;">
          <div class="page_title">Item Management System</div>
          <ul>
            <li><a id="searchItem" href="<spring:url value="/ims/getItem"/>">Search Item</a></li>
            <li>Create Item
                <ul>
                    <li style="margin-left:100px;"><a id="createItem" href="<spring:url value="/ims/createItem_begin"/>">Create a Brand New Item</a></li>
                    <li style="margin-left:80px;"><a id="cloneItem" href="<spring:url value="/ims/cloneItemForm"/>">    Clone an Existing Item</a></li>  
                </ul>
            </li>
            <li><a id="updateItem" href="<spring:url value="/ims/updateItemForm"/>">Update Item</a></li>  
            <!--<li><a id="deleteItem" href="<spring:url value="/ims/deleteItem"/>"><span>Delete Item</span></a></li>
            <li><a id="deactivateItem" href="<spring:url value="/ims/deactivateItem"/>"><span>Deactivate Item</span></a></li>-->
        </ul>
        <%@ include file="/WEB-INF/includes/footer.jsp"%>
   </div> 
</body>
</html>