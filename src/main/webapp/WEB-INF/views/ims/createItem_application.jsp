<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">
<spring:url var="action" value="/ims/createItem_packageUnits" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
     <div style="color:Green"> <h3>Usage</h3></div>
     <table class="category">     
         <tr>
              <td><label for="residentialOptions">Residential:</label>
                 <c:forEach var="usage" items="${usageList}" varStatus="status">
                   <form:checkbox path="applications.residential" value="${usage}R" />${usage.getDescription()}
             </c:forEach>
              </td>
         </tr>
         <tr>
              <td><label for="lightcommercialOptions">Light Commercial:</label>
                   <c:forEach var="usage" items="${usageList}" varStatus="status">
                      <form:checkbox path="applications.lightcommercial" value="${usage}L" />${usage.getDescription()}
                   </c:forEach>
              </td>
         </tr>
         <tr>
            <td><label for="commercialOptions">Commercial            :</label>
                <c:forEach var="usage" items="${usageList}" varStatus="status">
                   <form:checkbox path="applications.commercial" value="${usage}C" />${usage.getDescription()}
                </c:forEach>
            </td>
         </tr>
    </table>
    
    <div style="color:Green"> <h3>Application Information</h3></div>
    <table class="category">
       <tr>
          <td><label for="edgeOptions">Edge: </label>
              <c:forEach var="edge" items="${edgeList}" varStatus="status">
                 <form:radiobutton path="newFeature.edge" value="${edge}"/>${edge.getDescription()}
              </c:forEach>
         </td>                  
       </tr>
       <tr>
         <td><label for="bodyOptions">Body: </label>
              <c:forEach var="body" items="${bodyList}" varStatus="status">
                 <form:radiobutton path="newFeature.body" value="${body}"/>${body.getDescription()}
              </c:forEach>
         </td>
       </tr>
       <tr>
         <td><label for="designLookOptions">Design Look: </label>
              <c:forEach var="designLook" items="${designLookList}" varStatus="status">
                 <form:radiobutton path="newFeature.designLook" value="${designLook}" />${designLook.getDescription()}
              </c:forEach>
          </td>  
       </tr>
       <tr>
        <td><label for="designStyleOptions">Design Styles: </label>
              <c:forEach var="designStyle" items="${designStyleList}" varStatus="status">
                 <form:radiobutton  path="newFeature.designStyle" value="${designStyle}"/>${designStyle.getDescription()}
              </c:forEach>
         </td>  
      </tr>
      <tr>
        <td><label for="surfaceTypeOptions">Surface Type: </label>
              <c:forEach var="surfaceType" items="${surfaceTypeList}" varStatus="status">
                 <form:radiobutton path="newFeature.surfaceType" value="${surfaceType}"/>${surfaceType.getDescription()}
              </c:forEach>
         </td>  
       </tr>
       <tr>
          <td><label for="surfaceFinishOptions">Surface Finish: </label>
              <c:forEach var="surfaceFinish" items="${surfaceFinishList}" varStatus="status">
                 <form:radiobutton path="newFeature.surfaceFinish" value="${surfaceFinish}"/>${surfaceFinish.getDescription()}
              </c:forEach>
          </td>         
       </tr>
       <tr>
         <td><label for="surfaceApplicationOptions">Surface Application: </label>
              <c:forEach var="surfaceApplication" items="${surfaceApplicationList}" varStatus="status">
                 <form:radiobutton path="newFeature.surfaceApplication" value="${surfaceApplication}" />${surfaceApplication.getDescription()}
              </c:forEach>
         </td>
       </tr>
       <tr>
         <td><label for="recommendedGroutJointMinOptions">Recommended Grout Joint Min: </label>
               <form:radiobutton path="newFeature.recommendedGroutJointMin" value="1" />1
               <form:radiobutton path="newFeature.recommendedGroutJointMin" value="2" />2
               <form:radiobutton path="newFeature.recommendedGroutJointMin" value="3" />3
         </td> 
       </tr>
       <tr>
         <td><label for="recommendedGroutJointMaxOptions">Recommended Grout Joint Max: </label>
               <form:radiobutton path="newFeature.recommendedGroutJointMax" value="1" />1
               <form:radiobutton path="newFeature.recommendedGroutJointMax" value="2" />2
               <form:radiobutton path="newFeature.recommendedGroutJointMax" value="3" />3
         </td>
       </tr>
     </table>
     <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
            <input type="submit" value="Continue"/>
            <!--<a id="createItemPage2" href="<spring:url value="/ims/createItemPage2"/>"><span>Next</span></a>-->
        </td>
      </tr>
    </table> 
</form:form>

</div><!-- container -->
</body>
</html>
