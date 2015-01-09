<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>
<html>
<head>
<title>Login Page</title>
</head>
<body onload='document.loginForm.username.focus();'>
    <c:url var="homeUrl" value="/home.html" />
    <c:url var="loginUrl" value="/spring_security_login" />
    <c:url var="logoutUrl" value="/j_spring_security_logout" /> 

	<div class="page_title">Item Management System</div>
 
	<div id="login-box" class="container">
 
		<h3>Login with Username and Password</h3>
 
        <c:if test="${param.failed == true}">
             <div class="error">Your login attempt failed. Please try again.</div><br/>
        </c:if>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="msg">${message}</div>
		</c:if>
 
		<form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
  	       <table>
			  <tr>
				<td>User Name:</td>
				<td><input type='text' name='username' value=''></td>
			  </tr>
			  <tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			  </tr>
			</table>
			<table class="element_center">  
			  <tr>
			    <td> 
			       <input name="submit" type="submit" value="Login" />
			    </td>
			    <td> 
			       <input name="reset" type="reset" value="Reset" />
			    </td>
			  </tr>
		   </table>
 
		   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
 
</body>
</html>