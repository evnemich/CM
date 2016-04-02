<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.evnemich.coffeemachine.models.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Navigation bar</title>
</head>
<body>
	<%
		boolean guest = false;
		boolean admin = false;
		String name;
		double balance = 0;
		Object o = session.getAttribute("currentSessionUser");
		
		if (o == null) {
			guest = true;
			name = "guest";
		} else {
			admin = ((User) o).admin;
			name = (String) session.getAttribute("currentSessionUserName");
			o = session.getAttribute("balance");
			if (o != null)
				balance = (double) o;
		}

		pageContext.setAttribute("guest", guest);
		pageContext.setAttribute("admin", admin);
	%>
	<table border="1">
		<tr>
			<td>User: <%=name%>
			</td>
			<c:if test="${!guest}">
			<td>Balance: <%=balance%>
			</td>
			</c:if>
			<c:if test="${guest}">
				<td><a href="login.jsp">Login</a></td>
				<td><a href="register.jsp">Register</a></td>
			</c:if>
			<c:if test="${!guest}">
				<td><a href="LogOut">Logout</a></td>
				<td><a href="addMoney.jsp">Add money</a></td>
				<td><a href="drink.jsp">Buy drinks</a></td>
				<c:if test="${admin}">
					<td><a href="addProduct.jsp">Add product</a></td>
					<td><a href="refill.jsp">Refill machine</a></td>
					<td><a href="setPrices.jsp">Set prices</a></td>
					<td><a href="removeProducts.jsp">Remove products</a></td>
				</c:if>
			</c:if>
		</tr>
	</table>
	<br>
</body>
</html>