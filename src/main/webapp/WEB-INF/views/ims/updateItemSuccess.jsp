<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Ims Menu</title>
</head>
<body>
<div class="container">
<c:choose>
<c:when test="${empty item}">
<div class="container">Error occured while updatig item</div>
</c:when>
<c:otherwise>
<h3>Item Updated Successfully!</h3>
</c:otherwise>
</c:choose>
</div>
<a id="imsHome" href="<spring:url value="/ims/overview" />" class="button action-m"><span>Back To Ims Management Home</span></a>
</td>
</tr>
</table>
</div> <!-- close main-content -->
</div> <!-- Close container -->
</body>
</html>
