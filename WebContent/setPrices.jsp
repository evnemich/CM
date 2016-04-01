<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.evnemich.coffeemachine.CoffeeMachine"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setting prices</title>
</head>
<body>
	<%@ include file="navBar.jsp"%>
	<%
		pageContext.setAttribute("price", CoffeeMachine.price);
		pageContext.setAttribute("products", CoffeeMachine.products);
	%>
	Set prices for products (if u put 0, previous price will not changed)
	<form action="SetPrices">
		<table border="1">
			<tr>
				<td>Name</td>
				<td>Previous price</td>
				<td>New price</td>
			</tr>
			<c:forEach items="${products}" var="current">
				<tr>
					<td><c:out value="${current}" /></td>
					<td><c:out value="${price.get(current)}" /></td>
					<td><input type="text" name="${current}" value="0" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="submit" value="Set prices"></td>
			</tr>
		</table>
	</form>
</body>
</html>