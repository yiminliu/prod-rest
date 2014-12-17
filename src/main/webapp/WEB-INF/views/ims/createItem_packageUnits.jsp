<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">

<spring:url var="action" value="/ims/createItem_vendor" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
  <div style="color:GREEN"> <h3>Package Units</h3></div>
     <div style="color:66CCCC"> <h3>Base Unit</h3></div>
     <table>
       <tr>   
          <td><span style="color:black;">Base Unit<span style="color:red;">*</span>: </span>
              <c:forEach var="baseunit" items="${packageUnitList}" varStatus="status">
                 <form:radiobutton class="input_value" path="units.baseunit" value="${baseunit}"/>${baseunit}
              </c:forEach>
              <form:errors path="units.baseunit" cssClass="error" />
          </td> 
       </tr>
       <tr>          
          <td><span style="color:black;">Base Is Standard Sell?: </span>
              <form:checkbox path="units.baseisstdsell" value="Y" cssStyle="input_value"/>Yes
          </td>
         </tr>
       <tr>     
            <td><span style="color:black;">Base Is Standard Order: </span>
              <form:checkbox path="units.baseisstdord" value="Y" />Yes
            </td>
        </tr>
       <tr>       
            <td><span style="color:black;">Base Is Fractq Qty: </span>
              <form:checkbox path="units.baseisfractqty" value="Y" />Yes
            </td>
       </tr>
       <tr>      
            <td><span style="color:black;">Base Is Pack Unit: </span>
              <form:checkbox path="units.baseispackunit" value="Y" />Yes
            </td>
       </tr>
       <tr>  
           <td style="color:black;">Base UPC: <form:input path="units.baseupc"></form:input></td>
	    </tr>
       <tr>  
         <td style="color:black;">Baseu UPC Desc: <form:input path="units.baseupcdesc"></form:input></td>   
        </tr>
       <tr>  
          <td style="color:black;">Base Volumn: <form:input path="units.basevolperunit"></form:input></td>
	    </tr>
       <tr>  
          <td style="color:black;">Base Weight<span style="color:red;">*</span>: <form:input path="units.basewgtperunit"></form:input>
             <form:errors path="units.basewgtperunit" cssClass="error" />
          </td>
	   </tr>
	</table>   
          
    <div style="color:66CCCC"> <h3>Unit1</h3></div>       
    <table>
       <tr>  
          <td><span style="color:black;">Unit1 Unit: </span>
              <c:forEach var="unit1unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit1unit" value="${unit1unit}"/>${unit1unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td style="color:black;">Unit1 Ratio: 
              <form:input path="units.unit1ratio"></form:input>
              <form:errors path="units.unit1ratio" cssClass="error" />
          </td>
       </tr>
       <tr>  
           <td><span style="color:black;">Unit1 Is Standard Sell?: </span>
              <form:checkbox path="units.unit1isstdsell" value="Y" />Yes
           </td>
        </tr>
       <tr>  
            <td><span style="color:black;">unit1 Is Standard Order?: </span>
               <form:checkbox path="units.unit1isstdord" value="Y" />Yes
            </td>
        </tr>
       <tr>  
            <td><span style="color:black;">Unit1 Is Fract Qty?: </span>
               <form:checkbox path="units.unit1isfractqty" value="Y" />Yes
            </td>
        </tr>
       <tr>  
           <td><span style="color:black;">Unit1 Is Pack Unit?: </span>
               <form:checkbox path="units.unit1ispackunit" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td style="color:black;">Unit1 UPC: <form:input path="units.unit1upc"></form:input></td>
	    </tr>
       <tr>  
          <td style="color:black;">Unit1 UPC Desc: <form:input path="units.unit1upcdesc"></form:input></td>
	    </tr>
        <tr>     
	      <td style="color:black;">Unit1 Weight: <form:input path="units.unit1wgtperunit"></form:input></td>
	     </tr>
	 </table>    
	<div style="color:66CCCC"> <h3>Unit2</h3></div>       
    <table>
       <tr>  
          <td><span style="color:black;">Unit2 Unit: </span>
              <c:forEach var="unit2unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit2unit" value="${unit2unit}"/>${unit2unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td style="color:black;">Unit2 Ratio: 
             <form:input path="units.unit2ratio"></form:input>
             <form:errors path="units.unit2ratio" cssClass="error" />
          </td>
       </tr>
       <tr>  
           <td><span style="color:black;">Unit2 Is Standard Sell?: </span>
              <form:checkbox path="units.unit2isstdsell" value="Y" />Yes
           </td>
        </tr>
       <tr>  
            <td><span style="color:black;">unit2 Is Standard Order?: </span>
               <form:checkbox path="units.unit2isstdord" value="Y" />Yes
            </td>
        </tr>
       <tr>  
            <td><span style="color:black;">Unit2 Is Fract Qty?: </span>
               <form:checkbox path="units.unit2isfractqty" value="Y" />Yes
            </td>
        </tr>
       <tr>  
           <td><span style="color:black;">Unit2 Is Pack Unit?: </span>
               <form:checkbox path="units.unit2ispackunit" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td style="color:black;">Unit2 UPC: <form:input path="units.unit2upc"></form:input></td>
	    </tr>
       <tr>  
          <td style="color:black;">Unit2 UPC Desc: <form:input path="units.unit2upcdesc"></form:input></td>
	    </tr>
        <tr>     
	      <td style="color:black;">Unit2 Weight: <form:input path="units.unit2wgtperunit"></form:input></td>
	     </tr>
	 </table>
	 <div style="color:66CCCC"> <h3>Unit3</h3></div>       
    <table>
       <tr>  
          <td><span style="color:black;">Unit3 Unit: </span>
              <c:forEach var="unit3unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit3unit" value="${unit3unit}"/>${unit3unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td style="color:black;">Unit3 Ratio: 
             <form:input path="units.unit3ratio"></form:input>
             <form:errors path="units.unit3ratio" cssClass="error" />
          </td>
       </tr>
       <tr>  
           <td><span style="color:black;">Unit3 Is Standard Sell?: </span>
              <form:checkbox path="units.unit3isstdsell" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td><span style="color:black;">unit3 Is Standard Order?: </span>
               <form:checkbox path="units.unit3isstdord" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td><span style="color:black;">Unit3 Is Fract Qty?: </span>
               <form:checkbox path="units.unit3isfractqty" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td><span style="color:black;">Unit3 Is Pack Unit?: </span>
               <form:checkbox path="units.unit3ispackunit" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td style="color:black;">Unit3 UPC: <form:input path="units.unit3upc"></form:input></td>
	    </tr>
       <tr>  
          <td style="color:black;">Unit3 UPC Desc: <form:input path="units.unit3upcdesc"></form:input></td>
	    </tr>
        <tr>     
	      <td style="color:black;">Unit3 Weight: <form:input path="units.unit3wgtperunit"></form:input></td>
	     </tr>
	 </table>
	 <div style="color:66CCCC"> <h3>Unit4</h3></div>       
    <table>
       <tr>  
          <td><span style="color:black;">Unit4 Unit: </span>
              <c:forEach var="unit4unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit4unit" value="${unit4unit}"/>${unit4unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td style="color:black;">Unit4 Ratio: 
             <form:input path="units.unit4ratio"></form:input>
             <form:errors path="units.unit4ratio" cssClass="error" />
          </td>
       </tr>
       <tr>  
           <td><span style="color:black;">Unit4 Is Standard Sell?: </span>
              <form:checkbox path="units.unit4isstdsell" value="Y" />Yes
           </td>
        </tr>
       <tr>  
            <td><span style="color:black;">Unit4 Is Standard Order?: </span>
               <form:checkbox path="units.unit4isstdord" value="Y" />Yes
            </td>
        </tr>
       <tr>  
            <td><span style="color:black;">Unit4 Is Fract Qty?: </span>
               <form:checkbox path="units.unit4isfractqty" value="Y" />Yes
            </td>
        </tr>
       <tr>  
           <td><span style="color:black;">Unit4 Is Pack Unit?: </span>
               <form:checkbox path="units.unit4ispackunit" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td style="color:black;">Unit4 UPC: <form:input path="units.unit4upc"></form:input></td>
	    </tr>
       <tr>  
          <td style="color:black;">Unit4 UPC Desc: <form:input path="units.unit4upcdesc"></form:input></td>
	    </tr>
        <tr>     
	      <td style="color:black;">Unit4 Weight: <form:input path="units.unit4wgtperunit"></form:input></td>
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
