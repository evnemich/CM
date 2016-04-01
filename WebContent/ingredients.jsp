<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.evnemich.coffeemachine.CoffeeMachine"%>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose ingredients to your drink</title>
</head>
<body>
	<%@ include file="navBar.jsp"%>
	<%
		pageContext.setAttribute("ingr", CoffeeMachine.ingredients);
		pageContext.setAttribute("price", CoffeeMachine.price);
	%>
	Check ingredients you want to add
	<form action="BuyIngredients">
		<table border="1">
			<tr>
				<td>Name</td>
				<td>Price</td>
				<td>Amount</td>
			</tr>
			<c:forEach items="${ingr}" var="current">
				<tr>
					<td><c:out value="${current}" /></td>
					<td><c:out value="${price.get(current)}" /></td>
					<td><input type="text" name="${current}" value="0" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="submit" value="Buy"></td>
			</tr>
		</table>
	</form>
</body>
</body>
</html>