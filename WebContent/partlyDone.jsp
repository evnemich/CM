<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>You bought only part of your cart</title>
</head>
<body>
	<%@ include file="navBar.jsp"%>
	<%
	pageContext.setAttribute("bought", session.getAttributeNames());
	%>
	We have not bought all items
	<c:forEach items="${bought}" var="current">
	<c:if test="${!current.equals('balance') and !current.equals('currentSessionUserName') and !current.equals('currentSessionUser')}">
		<tr>
			<td><c:out value="${current}" /></td>
			<td><c:out value="${session.getAttribute(current)}" /><br></td>
			<td><c:out value="${session.removeAttribute(current)}" /></td>
		</tr>
		</c:if>
	</c:forEach>
</body>
</html>