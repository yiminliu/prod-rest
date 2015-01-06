<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System -- Create An Item -Packing Units</title>
</head>
<body>
<%@ include file="/WEB-INF/includes/header.jsp"%>
<div class="container">

<spring:url var="action" value="/ims/createItem_vendor" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
  <div style="color:GREEN"> <h3>Package Units</h3></div>
     <table class="input_table_cells">
       <tr>   
          <td>
            <fieldset>
              <legend style="color:66CCCC"> Base Unit</legend>
              <span style="color:black;">Base Unit<span style="color:red;">*</span>: </span>
              <c:forEach var="baseunit" items="${packageUnitList}" varStatus="status">
                <span style="font-size: 75%;">
                   <form:radiobutton class="input_value" path="units.baseunit" value="${baseunit.description}"/>${baseunit.description}
                </span>
              </c:forEach>
              <form:errors path="units.baseunit" cssClass="error" />
              <div><span style="color:black;">Base Is Standard Sell?: </span>
                 <form:checkbox path="units.baseisstdsell" value="Y" cssStyle="input_value"/>Yes
              </div>
              <div><span style="color:black;">Base Is Standard Order: </span>
                <form:checkbox path="units.baseisstdord" value="Y" />Yes
              </div>
              <div><span style="color:black;">Base Is Fractq Qty: </span>
                <form:checkbox path="units.baseisfractqty" value="Y" />Yes
              </div>
              <div><span style="color:black;">Base Is Pack Unit: </span>
                <form:checkbox path="units.baseispackunit" value="Y" />Yes
              </div>
              <div style="color:black;">Base UPC: <form:input path="units.baseupc"></form:input></div>
	          <div style="color:black;">Base UPC Desc: <form:input path="units.baseupcdesc"></form:input></div>   
              <div style="color:black;">Base Volume: <form:input path="units.basevolperunit"></form:input></div>
	          <div style="color:black;">Base Weight<span style="color:red;">*</span>: <form:input path="units.basewgtperunit"></form:input>
                <form:errors path="units.basewgtperunit" cssClass="error" />
              </div>
           </fieldset>
         </td>  
	   </tr>
       <tr>
         <td>  
           <fieldset>
              <legend> Unit1</legend>
              <span style="color:black;">Unit1 Unit: </span>
              <c:forEach var="unit1unit" items="${packageUnitList}" varStatus="status">
                 <span style="font-size: 75%;">
                   <form:radiobutton path="units.unit1unit" value="${unit1unit.description}"/>${unit1unit.description}
                 </span>
              </c:forEach>
              <div style="color:black;">Unit1 Ratio: 
                <form:input class="input_value" path="units.unit1ratio"></form:input>
                <form:errors path="units.unit1ratio" cssClass="error" />
              </div>
              <div><span style="color:black;">Unit1 Is Standard Sell?: </span>
                <form:checkbox path="units.unit1isstdsell" value="Y" />Yes
              </div>
              <div><span style="color:black;">unit1 Is Standard Order?: </span>
                <form:checkbox path="units.unit1isstdord" value="Y" />Yes
              </div>
              <div><span style="color:black;">Unit1 Is Fract Qty?: </span>
                 <form:checkbox path="units.unit1isfractqty" value="Y" />Yes
              </div>
              <div><span style="color:black;">Unit1 Is Pack Unit?: </span>
                 <form:checkbox path="units.unit1ispackunit" value="Y" />Yes
              </div>
              <div style="color:black;">Unit1 UPC: <form:input class="input_value" path="units.unit1upc"></form:input></div>
	          <div style="color:black;">Unit1 UPC Desc: <form:input class="input_value" path="units.unit1upcdesc"></form:input></div>
	          <div style="color:black;">Unit1 Weight: <form:input class="input_value" path="units.unit1wgtperunit"></form:input></div>
	        </fieldset>
	     </td>
	      <td>  
           <fieldset>
              <legend> Unit2</legend>
              <span style="color:black;">Unit2 Unit: </span>
              <c:forEach var="unit2unit" items="${packageUnitList}" varStatus="status">
                 <span style="font-size: 75%;">
                   <form:radiobutton path="units.unit2unit" value="${unit2unit.description}"/>${unit2unit.description}
                 </span>
              </c:forEach>
              <div style="color:black;">Unit2 Ratio: 
                <form:input class="input_value" path="units.unit2ratio"></form:input>
                <form:errors path="units.unit2ratio" cssClass="error" />
              </div>
              <div><span style="color:black;">Unit2 Is Standard Sell?: </span>
                <form:checkbox path="units.unit2isstdsell" value="Y" />Yes
              </div>
              <div><span style="color:black;">unit2 Is Standard Order?: </span>
                <form:checkbox path="units.unit2isstdord" value="Y" />Yes
              </div>
              <div><span style="color:black;">Unit2 Is Fract Qty?: </span>
                 <form:checkbox path="units.unit2isfractqty" value="Y" />Yes
              </div>
              <div><span style="color:black;">Unit2 Is Pack Unit?: </span>
                 <form:checkbox path="units.unit2ispackunit" value="Y" />Yes
              </div>
              <div style="color:black;">Unit2 UPC: <form:input class="input_value" path="units.unit2upc"></form:input></div>
	          <div style="color:black;">Unit2 UPC Desc: <form:input class="input_value" path="units.unit2upcdesc"></form:input></div>
	          <div style="color:black;">Unit2 Weight: <form:input class="input_value" path="units.unit2wgtperunit"></form:input></div>
	       </fieldset>
	     </td>
	   </tr>  
	   <tr>
         <td>  
           <fieldset>
              <legend> Unit3</legend>
              <span style="color:black;">Unit3 Unit: </span>
              <c:forEach var="unit3unit" items="${packageUnitList}" varStatus="status">
                 <span style="font-size: 75%;">
                   <form:radiobutton path="units.unit3unit" value="${unit3unit.description}"/>${unit3unit.description}
                 </span>
              </c:forEach>
              <div style="color:black;">Unit3 Ratio: 
                <form:input class="input_value" path="units.unit3ratio"></form:input>
                <form:errors path="units.unit3ratio" cssClass="error" />
              </div>
              <div><span style="color:black;">Unit3 Is Standard Sell?: </span>
                <form:checkbox path="units.unit3isstdsell" value="Y" />Yes
              </div>
              <div><span style="color:black;">unit3 Is Standard Order?: </span>
                <form:checkbox path="units.unit3isstdord" value="Y" />Yes
              </div>
              <div><span style="color:black;">Unit3 Is Fract Qty?: </span>
                 <form:checkbox path="units.unit3isfractqty" value="Y" />Yes
              </div>
              <div><span style="color:black;">Unit3 Is Pack Unit?: </span>
                 <form:checkbox path="units.unit3ispackunit" value="Y" />Yes
              </div>
              <div style="color:black;">Unit3 UPC: <form:input class="input_value" path="units.unit3upc"></form:input></div>
	          <div style="color:black;">Unit3 UPC Desc: <form:input class="input_value" path="units.unit3upcdesc"></form:input></div>
	          <div style="color:black;">Unit3 Weight: <form:input class="input_value" path="units.unit3wgtperunit"></form:input></div>
	        </fieldset>
	     </td>
	      <td>  
           <fieldset>
              <legend> Unit4</legend>
              <span style="color:black;">Unit4 Unit: </span>
              <c:forEach var="unit4unit" items="${packageUnitList}" varStatus="status">
                 <span style="font-size: 75%;">
                   <form:radiobutton path="units.unit4unit" value="${unit4unit.description}"/>${unit4unit.description}
                 </span>
              </c:forEach>
              <div style="color:black;">Unit4 Ratio: 
                <form:input class="input_value" path="units.unit4ratio"></form:input>
                <form:errors path="units.unit4ratio" cssClass="error" />
              </div>
              <div><span style="color:black;">Unit4 Is Standard Sell?: </span>
                <form:checkbox path="units.unit4isstdsell" value="Y" />Yes
              </div>
              <div><span style="color:black;">unit4 Is Standard Order?: </span>
                <form:checkbox path="units.unit4isstdord" value="Y" />Yes
              </div>
              <div><span style="color:black;">Unit4 Is Fract Qty?: </span>
                 <form:checkbox path="units.unit4isfractqty" value="Y" />Yes
              </div>
              <div><span style="color:black;">Unit4 Is Pack Unit?: </span>
                 <form:checkbox path="units.unit4ispackunit" value="Y" />Yes
              </div>
              <div style="color:black;">Unit4 UPC: <form:input class="input_value" path="units.unit4upc"></form:input></div>
	          <div style="color:black;">Unit4 UPC Desc: <form:input class="input_value" path="units.unit4upcdesc"></form:input></div>
	          <div style="color:black;">Unit4 Weight: <form:input class="input_value" path="units.unit4wgtperunit"></form:input></div>
	        </fieldset>  
	     </td>
	   </tr> 
	 </table>    
	
    <table> 
      <tr> 
        <td>
             <input type="submit" value="Continue >>"/>
        </td>
      </tr>
    </table> 
</form:form>
<%@ include file="/WEB-INF/includes/footer.jsp"%>
</div><!-- container -->
</body>
</html>
