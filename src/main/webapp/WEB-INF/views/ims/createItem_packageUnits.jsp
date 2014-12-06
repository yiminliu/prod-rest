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
    <!--<table>
       <tr>
          <td><label for="stdunitOptions">Standard Unit: </label>
              <c:forEach var="stdunit" items="${packageUnitList}" varStatus="status">
                 <form:radiobutton path="units.stdunit" value="${stdunit}"/>${stdunit}
              </c:forEach>
          </td>
       </tr>
       <tr>
          <td><label for="ordunitOptions">Order Unit: </label>
              <c:forEach var="ordunit" items="${packageUnitList}" varStatus="status">
                 <form:radiobutton path="units.ordunit" value="${ordunit}"/>${ordunit}
              </c:forEach>
           </td>
       </tr>
       <tr>
          <td>Starndard Ratio: <form:input path="units.stdratio"></form:input></td>
       </tr>
       <tr>
          <td>Order Ratio: <form:input path="units.ordratio"></form:input></td>
       </tr>
     </table>-->  
     
     <div style="color:66CCCC"> <h3>Base Unit</h3></div>
     <table>
       <tr>   
          <td><label_same_row for="baseunitOptions">Base Unit: </label>
              <c:forEach var="baseunit" items="${packageUnitList}" varStatus="status">
                 <form:radiobutton cssStyle="input" path="units.baseunit" value="${baseunit}"/>${baseunit}
              </c:forEach>
          </td> 
       </tr>
       <tr>          
          <td><label_same_row for="baseisstdsellOptons">Base Is Standard Sell?: </label>
              <form:checkbox path="units.baseisstdsell" value="Y" />Yes
          </td>
         </tr>
       <tr>     
            <td><label_same_row for="baseisstdordOptions">Basei Is Standard Order: </label>
              <form:checkbox path="units.baseisstdord" value="Y" />Yes
            </td>
        </tr>
       <tr>       
            <td><label_same_row for="baseisfractqtyOptions">Base Is Fractq Qty: </label>
              <form:checkbox path="units.baseisfractqty" value="Y" />Yes
            </td>
       </tr>
       <tr>      
            <td><label_same_row for="baseispackunitOptions">Base Is Pack Unit: </label>
              <form:checkbox path="units.baseispackunit" value="Y" />Yes
            </td>
       </tr>
       <tr>  
           <td>Base UPC: <form:input path="units.baseupc"></form:input></td>
	    </tr>
       <tr>  
         <td>Baseu UPC Desc: <form:input path="units.baseupcdesc"></form:input></td>   
        </tr>
       <tr>  
          <td>Base Volumn: <form:input path="units.basevolperunit"></form:input></td>
	    </tr>
       <tr>  
          <td>Base Weight: <form:input path="units.basewgtperunit"></form:input></td>
	   </tr>
	</table>   
          
    <div style="color:66CCCC"> <h3>Unit1</h3></div>       
    <table>
       <tr>  
          <td><label_same_row for="unit1unitOptions">Unit1 Unit: </label>
              <c:forEach var="unit1unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit1unit" value="${unit1unit}"/>${unit1unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td>Unit1 Ratio: <form:input path="units.unit1ratio"></form:input></td>
       </tr>
       <tr>  
           <td><label_same_row for="unit1isstdsellOptions">Unit1 Is Standard Sell?: </label>
              <form:checkbox path="units.unit1isstdsell" value="Y" />Yes
           </td>
        </tr>
       <tr>  
            <td><label_same_row for="unit1isstdordOptions">unit1 Is Standard Order?: </label>
               <form:checkbox path="units.unit1isstdord" value="Y" />Yes
            </td>
        </tr>
       <tr>  
            <td><label_same_row for="unit1isfractqtyOptions">Unit1 Is Fract Qty?: </label>
               <form:checkbox path="units.unit1isfractqty" value="Y" />Yes
            </td>
        </tr>
       <tr>  
           <td><label_same_row for="unit1ispackunitOptions">Unit1 Is Pack Unit?: </label>
               <form:checkbox path="units.unit1ispackunit" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td>Unit1 UPC: <form:input path="units.unit1upc"></form:input></td>
	    </tr>
       <tr>  
          <td>Unit1 UPC Desc: <form:input path="units.unit1upcdesc"></form:input></td>
	    </tr>
        <tr>     
	      <td>Unit1 Weight: <form:input path="units.unit1wgtperunit"></form:input></td>
	     </tr>
	 </table>    
	<div style="color:66CCCC"> <h3>Unit2</h3></div>       
    <table>
       <tr>  
          <td><label_same_row for="unit2unitOptions">Unit2 Unit: </label>
              <c:forEach var="unit2unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit2unit" value="${unit2unit}"/>${unit2unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td>Unit2 Ratio: <form:input path="units.unit2ratio"></form:input></td>
       </tr>
       <tr>  
           <td><label_same_row for="unit2isstdsellOptions">Unit2 Is Standard Sell?: </label>
              <form:radiobutton path="units.unit2isstdsell" value="Y" />Yes
              <form:radiobutton path="units.unit2isstdsell" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label_same_row for="unit2isstdordOptions">unit2 Is Standard Order?: </label>
               <form:checkbox path="units.unit2isstdord" value="Y" />Yes
            </td>
        </tr>
       <tr>  
            <td><label_same_row for="unit2isfractqtyOptions">Unit2 Is Fract Qty?: </label>
               <form:checkbox path="units.unit2isfractqty" value="Y" />Yes
            </td>
        </tr>
       <tr>  
           <td><label_same_row for="unit2ispackunitOptions">Unit2 Is Pack Unit?: </label>
               <form:checkbox path="units.unit2ispackunit" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td>Unit2 UPC: <form:input path="units.unit2upc"></form:input></td>
	    </tr>
       <tr>  
          <td>Unit2 UPC Desc: <form:input path="units.unit2upcdesc"></form:input></td>
	    </tr>
        <tr>     
	      <td>Unit2 Weight: <form:input path="units.unit2wgtperunit"></form:input></td>
	     </tr>
	 </table>
	 <div style="color:66CCCC"> <h3>Unit3</h3></div>       
    <table>
       <tr>  
          <td><label_same_row for="unit3unitOptions">Unit3 Unit: </label>
              <c:forEach var="unit3unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit1unit" value="${unit3unit}"/>${unit3unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td>Unit3 Ratio: <form:input path="units.unit3ratio"></form:input></td>
       </tr>
       <tr>  
           <td><label_same_row for="unit3isstdsellOptions">Unit3 Is Standard Sell?: </label>
              <form:checkbox path="units.unit3isstdsell" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td><label_same_row for="unit3isstdordOptions">unit3 Is Standard Order?: </label>
               <form:checkbox path="units.unit3isstdord" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td><label_same_row for="unit3isfractqtyOptions">Unit3 Is Fract Qty?: </label>
               <form:checkbox path="units.unit3isfractqty" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td><label_same_row for="unit3ispackunitOptions">Unit3 Is Pack Unit?: </label>
               <form:checkbox path="units.unit3ispackunit" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td>Unit3 UPC: <form:input path="units.unit3upc"></form:input></td>
	    </tr>
       <tr>  
          <td>Unit3 UPC Desc: <form:input path="units.unit3upcdesc"></form:input></td>
	    </tr>
        <tr>     
	      <td>Unit3 Weight: <form:input path="units.unit3wgtperunit"></form:input></td>
	     </tr>
	 </table>
	 <div style="color:66CCCC"> <h3>Unit4</h3></div>       
    <table>
       <tr>  
          <td><label_same_row for="unit4unitOptions">Unit4 Unit: </label>
              <c:forEach var="unit4unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit4unit" value="${unit4unit}"/>${unit4unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td>Unit4 Ratio: <form:input path="units.unit4ratio"></form:input></td>
       </tr>
       <tr>  
           <td><label_same_row for="unit4isstdsellOptions">Unit4 Is Standard Sell?: </label>
              <form:checkbox path="units.unit4isstdsell" value="Y" />Yes
           </td>
        </tr>
       <tr>  
            <td><label_same_row for="unit4isstdordOptions">Unit4 Is Standard Order?: </label>
               <form:checkbox path="units.unit4isstdord" value="Y" />Yes
            </td>
        </tr>
       <tr>  
            <td><label_same_row for="unit4isfractqtyOptions">Unit4 Is Fract Qty?: </label>
               <form:checkbox path="units.unit4isfractqty" value="Y" />Yes
            </td>
        </tr>
       <tr>  
           <td><label_same_row for="unit4ispackunitOptions">Unit4 Is Pack Unit?: </label>
               <form:checkbox path="units.unit4ispackunit" value="Y" />Yes
           </td>
        </tr>
       <tr>  
           <td>Unit4 UPC: <form:input path="units.unit4upc"></form:input></td>
	    </tr>
       <tr>  
          <td>Unit4 UPC Desc: <form:input path="units.unit4upcdesc"></form:input></td>
	    </tr>
        <tr>     
	      <td>Unit4 Weight: <form:input path="units.unit4wgtperunit"></form:input></td>
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
