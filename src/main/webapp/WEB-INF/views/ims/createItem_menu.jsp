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
          <br/><br/>
          <table class="center" style="cellspacing: 35px;">
           <tr style="float:middle;">   
                <td><a id="createItem" href="<spring:url value="/ims/createItem_begin"/>"><span>Create a Brand New Item</span></a></td>
            </tr>
            <tr style="float:middle;"> 
                <td><a id="cloneItem" href="<spring:url value="/ims/cloneItemForm"/>"><span>Clone an Item</span></a></td>
            </tr>  
          </table>
  </div><!-- close main-content -->
</div> <!-- Close container -->
</body>
</html>