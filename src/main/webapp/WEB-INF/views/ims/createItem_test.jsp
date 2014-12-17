<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>

<div class="container">
<spring:url var="action" value="/ims/createItem_note" />
<form:form method="POST" action="${action}" modelAttribute="aItem">
   <div style="color:Green"> <h3>Test specifications</h3></div>
      <table>
          <tr>
              <td><span style="color:black;">Warpage:</span>
                  <form:checkbox path="testSpecification.warpage" value="P" />Passed
               </td>
          </tr>
          <tr>           
              <td><span style="color:black;">Wedging: </span>
                  <form:checkbox path="testSpecification.wedging" value="P" />Passed
              </td>
           </tr>
           <tr>
              <td><span style="color:black;">Thermal Shock:</span>
                 <form:checkbox path="testSpecification.thermalshock" value="P" />Passed
              </td>
          </tr>
          <tr>
              <td><span style="color:black;">Frost Resistance:</span>
                   <form:checkbox path="testSpecification.frostresistance" value="P" />Passed
              </td>
          </tr>
          <tr>
              <td><span style="color:black;">Chemical Resistance:</span> 
                  <form:checkbox path="testSpecification.chemicalresistance" value="P" />Passed
              </td>
          </tr>
          <tr>
              <td><span style="color:black;">Green Friendly:</span>
                  <form:checkbox path="testSpecification.greenfriendly" value="" />Yes
                  <form:input path="testSpecification.greenfriendly" type="hidden" value="N" />
                </td>
          </tr>
          <tr>
              <td><label> Pei Abrasion: </label>
                <form:radiobutton path="testSpecification.peiabrasion" value="1"/>1
                <form:radiobutton path="testSpecification.peiabrasion" value="2"/>2
                <form:radiobutton path="testSpecification.peiabrasion" value="3"/>3
                <form:radiobutton path="testSpecification.peiabrasion" value="4"/>4
                <form:radiobutton path="testSpecification.peiabrasion" value="5"/>5
              </td>
          </tr>
          <tr>
              <td><label> MOH:</label>
                  <form:radiobutton  path="testSpecification.moh" value="1"/>1
                  <form:radiobutton  path="testSpecification.moh" value="2"/>2
                  <form:radiobutton  path="testSpecification.moh" value="3"/>3
                  <form:radiobutton  path="testSpecification.moh" value="4"/>4
                  <form:radiobutton  path="testSpecification.moh" value="5"/>5
                  <form:radiobutton  path="testSpecification.moh" value="6"/>6
                  <form:radiobutton  path="testSpecification.moh" value="7"/>7
                  <form:radiobutton  path="testSpecification.moh" value="8"/>8
                  <form:radiobutton  path="testSpecification.moh" value="9"/>9
                  <form:radiobutton  path="testSpecification.moh" value="10"/>10
               </td> 
          </tr>
          <tr>
              <td><label>Water Absorption (<=.5):</label> <form:input path="testSpecification.waterabsorption"></form:input></td>
          </tr>
          <tr>
              <td><label>Scratch Resistance:</label> <form:input path="testSpecification.scratchresistance"></form:input></td>
          </tr>
          
          <tr>
              <td><label>Scof Wet (>=0.6): </label><form:input path="testSpecification.scofwet"></form:input></td>
          </tr>
          <tr>
              <td><label>Scof Dry (>=0.6): </label><form:input path="testSpecification.scofdry"></form:input></td>
          </tr>
          <tr>
              <td><label>Breaking Strength (>=250):</label> <form:input path="testSpecification.breakingstrength"></form:input></td>
          </tr>
          <tr>
              <td><label>Scratch Standard: </label><form:input path="testSpecification.scratchstandard"></form:input></td>
          </tr>
          <tr>
              <td><label>Breaking Standard:</label> <form:input path="testSpecification.breakingstandard"></form:input></td>
          </tr>
          <tr>
              <td><label>DCOF (<1.0): </label><form:input path="testSpecification.dcof"></form:input></td>
          </tr>
          <tr>
              <td><label>Bond Strength: </label><form:input path="testSpecification.bondstrength"></form:input></td>
          </tr>
          <tr>
              <td><label>Lead Point: </label><form:input path="testSpecification.leadpoint"></form:input></td>
          </tr>
          <tr>
              <td><label>Pre-Consummer: </label><form:input path="testSpecification.preconsummer"></form:input></td>
          </tr>
          <tr>
              <td><label>Pos-Consummer: </label><form:input path="testSpecification.posconsummer"></form:input></td>
          </tr>
           <tr>
              <td><label>Pos-Consummer: </label><form:input path="testSpecification.posconsummer"></form:input></td>
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
