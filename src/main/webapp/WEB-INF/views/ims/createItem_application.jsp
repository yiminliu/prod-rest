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
<spring:url var="action" value="/ims/createItem_packageUnits" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
    <div class="container" style="color:Green"> <h3>Enter Application Information</h3></div>
    <table class="category">
       <tr>
          <td calss="narrow"><label for="edgeOptions">Edge: </label>
            <form:select id="edge" path="newFeature.edge" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="edge" items="${edgeList}" varStatus="status">
                 <form:option value="${edge}">${edge.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>                  
       </tr>
       <tr>
         <td><label for="bodyOptions">Body: </label>
            <form:select id="body" path="newFeature.body" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="body" items="${bodyList}" varStatus="status">
                 <form:option value="${body}">${body.getDescription()}</form:option>
              </c:forEach>
            </form:select>
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
            <form:select id="designStyle" path="newFeature.designStyle" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="designStyle" items="${designStyleList}" varStatus="status">
                 <form:option value="${designStyle}">${designStyle.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>  
      </tr>
      <tr>
        <td><label for="surfaceTypeOptions">Surface Type: </label>
            <form:select id="surfaceType" path="newFeature.surfaceType" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="surfaceType" items="${surfaceTypeList}" varStatus="status">
                 <form:option value="${surfaceType}">${surfaceType.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>  
       </tr>
       <tr>
          <td><label for="surfaceFinishOptions">Surface Finish: </label>
            <form:select id="surfaceFinish" path="newFeature.surfaceFinish" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:100px;">>
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="surfaceFinish" items="${surfaceFinishList}" varStatus="status">
                 <form:option value="${surfaceFinish}">${surfaceFinish.getDescription()}</form:option>
              </c:forEach>
            </form:select>
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
    <table></table>
    <table> 
      <tr style="float:middle;"> 
        <td colspan="2">
            <input type="submit" value="Continue"/>
            <!--<a id="createItemPage2" href="<spring:url value="/ims/createItemPage2"/>"><span>Next</span></a>-->
        </td>
      </tr>
    </table> 
 
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
