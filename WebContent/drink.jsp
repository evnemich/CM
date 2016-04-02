<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.evnemich.coffeemachine.CoffeeMachine"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose drink to buy</title>
</head>
<body>
	<%@ include file="navBar.jsp"%>
	<%
		pageContext.setAttribute("drinks", CoffeeMachine.drinks);
		pageContext.setAttribute("price", CoffeeMachine.price);
	%>
	Check drinks you want to buy
	<form action="BuyDrink">
		<table border="1">
			<tr>
				<td>Name</td>
				<td>Price</td>
				<td>Buy</td>
			</tr>
			<c:forEach items="${drinks}" var="current">
				<tr>
					<td><c:out value="${current}" /></td>
					<td><c:out value="${price.get(current)}" /></td>
					<td><input type="checkbox" name="${current}" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="submit" value="Choose ingredients"></td>
			</tr>
		</table>
	</form>
</body>
</html>