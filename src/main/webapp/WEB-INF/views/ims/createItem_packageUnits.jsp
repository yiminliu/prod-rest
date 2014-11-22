<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">
<div id="main-content">
<div class="span-18 colborder">
<spring:url var="action" value="/ims/createItem_vendor" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
<table>
  <div class="container" style="color:BLUE"> <h3>Enter Package Units Information</h3></div>
    <table>
       <tr>
          <td><label for="stdunitOptions">stdunit: </label>
              <c:forEach var="stdunit" items="${packageUnitList}" varStatus="status">
                 <form:radiobutton path="units.stdunit" value="${stdunit}"/>${stdunit}
              </c:forEach>
          </td>
       </tr>
       <tr>
          <td><label for="ordunitOptions">ordunit: </label>
              <c:forEach var="ordunit" items="${packageUnitList}" varStatus="status">
                 <form:radiobutton path="units.ordunit" value="${ordunit}"/>${ordunit}
              </c:forEach>
           </td>
       </tr>
       <tr>
          <td>stdratio: <form:input path="units.stdratio"></form:input></td>
       </tr>
       <tr>
          <td>ordratio: <form:input path="units.ordratio"></form:input></td>
       </tr>
     </table>  
     
     <div style="color:Green"> <h3>Base Unit</h3></div>
     <table>
       <tr>   
          <td><label for="baseunitOptions">baseunit: </label>
              <c:forEach var="baseunit" items="${packageUnitList}" varStatus="status">
                 <form:radiobutton  path="units.baseunit" value="${baseunit}"/>${baseunit}
              </c:forEach>
          </td> 
       </tr>
       <tr>          
          <td><label for="baseisstdsellOptons">baseisstdsell: </label>
              <form:radiobutton path="units.baseisstdsell" value="Y" />Yes
              <form:radiobutton path="units.baseisstdsell" value="N" />No
           </td>
         </tr>
       <tr>     
            <td><label for="baseisstdordOptions">baseisstdord: </label>
              <form:radiobutton path="units.baseisstdord" value="Y" />Yes
              <form:radiobutton path="units.baseisstdord" value="N" />No
            </td>
        </tr>
       <tr>       
            <td><label for="baseisfractqtyOptions">baseisfractqty: </label>
              <form:radiobutton path="units.baseisfractqty" value="Y" />Yes
              <form:radiobutton path="units.baseisfractqty" value="N" />No
            </td>
       </tr>
       <tr>      
            <td><label for="baseispackunitOptions">baseispackunit: </label>
              <form:radiobutton path="units.baseispackunit" value="Y" />Yes
              <form:radiobutton path="units.baseispackunit" value="N" />No
           </td>
       </tr>
       <tr>  
           <td>baseupc: <form:input path="units.baseupc"></form:input></td>
	    </tr>
       <tr>  
         <td>baseupcdesc: <form:input path="units.baseupcdesc"></form:input></td>   
        </tr>
       <tr>  
          <td>basevolperunit: <form:input path="units.basevolperunit"></form:input></td>
	    </tr>
       <tr>  
          <td>basewgtperunit: <form:input path="units.basewgtperunit"></form:input></td>
	   </tr>
	</table>   
          
    <div style="color:Green"> <h3>Unit1</h3></div>       
    <table>
       <tr>  
          <td><label for="unit1unitOptions">Unit1 Unit: </label>
              <c:forEach var="unit1unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit1unit" value="${unit1unit}"/>${unit1unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td>Unit1 Ratio: <form:input path="units.unit1ratio"></form:input></td>
       </tr>
       <tr>  
           <td><label for="unit1isstdsellOptions">Unit1 Is Standard Sell?: </label>
              <form:radiobutton path="units.unit1isstdsell" value="Y" />Yes
              <form:radiobutton path="units.unit1isstdsell" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit1isstdordOptions">unit1 Is Standard Order?: </label>
               <form:radiobutton path="units.unit1isstdord" value="Y" />Yes
               <form:radiobutton path="units.unit1isstdord" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit1isfractqtyOptions">Unit1 Is Fract Qty?: </label>
               <form:radiobutton path="units.unit1isfractqty" value="Y" />Yes
               <form:radiobutton path="units.unit1isfractqty" value="N" />No
          </td>
        </tr>
       <tr>  
           <td><label for="unit1ispackunitOptions">Unit1 Is Pack Unit?: </label>
               <form:radiobutton path="units.unit1ispackunit" value="Y" />Yes
               <form:radiobutton path="units.unit1ispackunit" value="N" />No
    
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
	<div style="color:Green"> <h3>Unit2</h3></div>       
    <table>
       <tr>  
          <td><label for="unit2unitOptions">Unit2 Unit: </label>
              <c:forEach var="unit2unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit2unit" value="${unit2unit}"/>${unit2unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td>Unit2 Ratio: <form:input path="units.unit2ratio"></form:input></td>
       </tr>
       <tr>  
           <td><label for="unit2isstdsellOptions">Unit2 Is Standard Sell?: </label>
              <form:radiobutton path="units.unit2isstdsell" value="Y" />Yes
              <form:radiobutton path="units.unit2isstdsell" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit2isstdordOptions">unit2 Is Standard Order?: </label>
               <form:radiobutton path="units.unit2isstdord" value="Y" />Yes
               <form:radiobutton path="units.unit2isstdord" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit2isfractqtyOptions">Unit2 Is Fract Qty?: </label>
               <form:radiobutton path="units.unit2isfractqty" value="Y" />Yes
               <form:radiobutton path="units.unit2isfractqty" value="N" />No
          </td>
        </tr>
       <tr>  
           <td><label for="unit2ispackunitOptions">Unit2 Is Pack Unit?: </label>
               <form:radiobutton path="units.unit2ispackunit" value="Y" />Yes
               <form:radiobutton path="units.unit2ispackunit" value="N" />No
    
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
	 <div style="color:Green"> <h3>Unit3</h3></div>       
    <table>
       <tr>  
          <td><label for="unit3unitOptions">Unit3 Unit: </label>
              <c:forEach var="unit3unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit1unit" value="${unit3unit}"/>${unit3unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td>Unit3 Ratio: <form:input path="units.unit3ratio"></form:input></td>
       </tr>
       <tr>  
           <td><label for="unit3isstdsellOptions">Unit3 Is Standard Sell?: </label>
              <form:radiobutton path="units.unit3isstdsell" value="Y" />Yes
              <form:radiobutton path="units.unit3isstdsell" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit3isstdordOptions">unit3 Is Standard Order?: </label>
               <form:radiobutton path="units.unit3isstdord" value="Y" />Yes
               <form:radiobutton path="units.unit3isstdord" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit3isfractqtyOptions">Unit3 Is Fract Qty?: </label>
               <form:radiobutton path="units.unit3isfractqty" value="Y" />Yes
               <form:radiobutton path="units.unit3isfractqty" value="N" />No
          </td>
        </tr>
       <tr>  
           <td><label for="unit3ispackunitOptions">Unit3 Is Pack Unit?: </label>
               <form:radiobutton path="units.unit3ispackunit" value="Y" />Yes
               <form:radiobutton path="units.unit3ispackunit" value="N" />No
    
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
	 <div style="color:Green"> <h3>Unit4</h3></div>       
    <table>
       <tr>  
          <td><label for="unit4unitOptions">Unit4 Unit: </label>
              <c:forEach var="unit4unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit4unit" value="${unit4unit}"/>${unit1unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td>Unit4 Ratio: <form:input path="units.unit4ratio"></form:input></td>
       </tr>
       <tr>  
           <td><label for="unit4isstdsellOptions">Unit4 Is Standard Sell?: </label>
              <form:radiobutton path="units.unit4isstdsell" value="Y" />Yes
              <form:radiobutton path="units.unit4isstdsell" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit4isstdordOptions">Unit4 Is Standard Order?: </label>
               <form:radiobutton path="units.unit4isstdord" value="Y" />Yes
               <form:radiobutton path="units.unit1isstdord" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit4isfractqtyOptions">Unit4 Is Fract Qty?: </label>
               <form:radiobutton path="units.unit4isfractqty" value="Y" />Yes
               <form:radiobutton path="units.unit4isfractqty" value="N" />No
          </td>
        </tr>
       <tr>  
           <td><label for="unit4ispackunitOptions">Unit4 Is Pack Unit?: </label>
               <form:radiobutton path="units.unit4ispackunit" value="Y" />Yes
               <form:radiobutton path="units.unit4ispackunit" value="N" />No
    
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
</table>  
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
