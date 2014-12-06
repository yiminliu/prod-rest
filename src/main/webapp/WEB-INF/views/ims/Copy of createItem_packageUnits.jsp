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
    <!--<table>
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
     </table>-->  
     
     <div style="color:Green"> <h3>Base Unit</h3></div>
     <table>
       <tr>   
          <td><label for="baseunitOptions">Base Unit: </label>
              <c:forEach var="baseunit" items="${packageUnitList}" varStatus="status">
                 <form:radiobutton  path="units.baseunit" value="${baseunit}"/>${baseunit}
              </c:forEach>
          </td> 
       </tr>
       <tr>          
          <td><label for="baseisstdsellOptons">Base Is Standard Sell?: </label>
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
          <td><label for="unit1unitOptions">unit1unit: </label>
              <c:forEach var="unit1unit" items="${packageUnitList}" varStatus="status">
                <form:radiobutton  path="units.unit1unit" value="${unit1unit}"/>${unit1unit}
              </c:forEach>
          </td>
       </tr>
       <tr>  
          <td>unit1ratio: <form:input path="units.unit1ratio"></form:input></td>
       </tr>
       <tr>  
           <td><label for="unit1isstdsellOptions">unit1isstdsell: </label>
              <form:radiobutton path="units.unit1isstdsell" value="Y" />Yes
              <form:radiobutton path="units.unit1isstdsell" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit1isstdordOptions">unit1isstdord: </label>
               <form:radiobutton path="units.unit1isstdord" value="Y" />Yes
               <form:radiobutton path="units.unit1isstdord" value="N" />No
            </td>
        </tr>
       <tr>  
            <td><label for="unit1isfractqtyOptions">unit1isfractqty: </label>
               <form:radiobutton path="units.unit1isfractqty" value="Y" />Yes
               <form:radiobutton path="units.unit1isfractqty" value="N" />No
          </td>
        </tr>
       <tr>  
           <td><label for="unit1ispackunitOptions">unit1ispackunit: </label>
               <form:radiobutton path="units.unit1ispackunit" value="Y" />Yes
               <form:radiobutton path="units.unit1ispackunit" value="N" />No
    
          </td>
        </tr>
       <tr>  
           <td>unit1upc: <form:input path="units.unit1upc"></form:input></td>
	    </tr>
       <tr>  
          <td>unit1upcdesc: <form:input path="units.unit1upcdesc"></form:input></td>
	    </tr>
        <tr>     
	      <td>unit1wgtperunit: <form:input path="units.unit1wgtperunit"></form:input></td>
	     </tr>
	 </table>    
	      <div style="color:Green"> <h3>Unit2</h3></div> 
       <tr>    
          <td><label for="unit2unitOptions">unit2unit: </label>
            <form:select id="unit2unit" path="units.unit2unit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="unit2unit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${unit2unit}">${unit2unit}</form:option>
              </c:forEach>
            </form:select>
          </td>
        </tr>
       <tr>     
          <td><label for="unit2isstdsellOptions">unit2isstdsell: </label>
               <form:select path="units.unit2isstdsell" id="unit2isstdsell">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
        </tr>
       <tr>       
          <td><label for="unit2isstdordOptions">unit2isstdord: </label>
               <form:select path="units.unit2isstdord" id="unit2isstdord">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
        </tr>
       <tr>  
          <td>unit2ratio: <form:input path="units.unit2ratio"></form:input></td>
        </tr>
       <tr>    
            <td><label for="unit2isfractqtyOptions">unit2isfractqty: </label>
               <form:select path="units.unit2isfractqty" id="unit2isfractqty">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
         </tr>
       <tr>  
             <td><label for="unit2ispackunitOptions">unit2ispackunit: </label>
               <form:select path="units.unit2ispackunit" id="unit2ispackunit">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
         </tr>
       <tr>  
          <td>unit2upc: <form:input path="units.unit2upc"></form:input></td>
	    </tr>
       <tr>  
          <td>unit2upcdesc: <form:input path="units.unit2upcdesc"></form:input></td>
	 </tr>
       <tr>  
       	   <td>unit2wgtperunit: <form:input path="units.unit2wgtperunit"></form:input></td>
	  </tr>
	   <div style="color:Green"> <h3>Unit1</h3></div> 
       <tr>  
	       <td><label for="unit3unitOptions">unit3unit: </label>
            <form:select id="unit3unit" path="units.unit3unit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="unit3unit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${unit3unit}">${unit3unit}</form:option>
              </c:forEach>
            </form:select>
          </td>
        </tr>
       <tr>  
          <td><label for="unit3isstdsellOptions">unit3isstdsell: </label>
               <form:select path="units.unit3isstdsell" id="unit3isstdsell">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
       </tr>
       <tr>       
             <td><label for="unit3isstdordOptions">unit3isstdord: </label>
               <form:select path="units.unit3isstdord" id="unit3isstdord">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
       </tr>
       <tr>  
         <td>unit3ratio: <form:input path="units.unit3ratio"></form:input></td>
         </tr>
       <tr>    
            <td><label for="unit3isfractqtyOptions">unit3isfractqty: </label>
               <form:select path="units.unit3isfractqty" id="unit3isfractqty">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
         </tr>
       <tr>      
            <td><label for="unit3ispackunitOptions">unit3ispackunit: </label>
               <form:select path="units.unit3ispackunit" id="unit3ispackunit">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
          </tr>
       <tr>   
	        <td>unit3upc: <form:input path="units.unit3upc"></form:input></td>
	    </tr>
       <tr>  
         <td>unit3upcdesc: <form:input path="units.unit3upcdesc"></form:input></td>
        </tr>
         <div style="color:Green"> <h3>Unit1</h3></div> 
       <tr>     <td><label for="unit4unitOptions">unit4unit: </label>
            <form:select id="unit4unit" path="units.unit4unit" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:280px;">
               <form:option value="0" selected="selected">Select one</form:option>
               <c:forEach var="unit4unit" items="${packageUnitList}" varStatus="status">
                 <form:option value="${unit4unit}">${unit4unit}</form:option>
              </c:forEach>
            </form:select>
          </td>
         </tr>
       <tr>     
	    </tr>
       <tr>  
           <td>unit3wgtperunit: <form:input path="units.unit3wgtperunit"></form:input></td>
	
            <td><label for="unit4isstdsellOptions">unit4isstdsell: </label>
               <form:select path="units.unit4isstdsell" id="unit4isstdsell">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
         </tr>
       <tr>      
           <td><label for="unit4isstdordOptions">unit4isstdord: </label>
               <form:select path="units.unit4isstdord" id="unit4isstdord">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
       </tr>
       <tr>        
          <td>unit4ratio: <form:input path="units.unit4ratio"></form:input></td>
      </tr>
       <tr>        
	     <td>unit4upc: <form:input path="units.unit4upc"></form:input></td>
	   </tr>
       <tr>  
             <td>unit4upcdesc: <form:input path="units.unit4upcdesc"></form:input></td>
	    </tr>
       <tr>    
            <td><label for="unit4isfractqtyOptions">unit4isfractqty: </label>
               <form:select path="units.unit4isfractqty" id="unit4isfractqty">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
     </tr>
       <tr>  
            <td><label for="unit4ispackunitOptions">unit4ispackunit: </label>
               <form:select path="units.unit4ispackunit" id="unit4ispackunit">
                  <form:option value="false" selected="selected">No</form:option>
                  <form:option value="true">Yes</form:option>
               </form:select>
            </td>
	   </tr>
       <tr>  
          <td>unit4wgtperunit: <form:input path="units.unit4wgtperunit"></form:input></td>
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
</table>  
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
