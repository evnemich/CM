<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Navigation bar</title>
</head>
<body>
	<%
		String name;
		double balance = 0;
		Object o = session.getAttribute("status");
		if (o == null) {
			session.setAttribute("status", "Guest");
			o = "Guest";
		}

		if (session.isNew() || ((String) o).equals("Guest"))
			name = "guest";
		else {
			o = session.getAttribute("currentSessionUserName");
			if (o != null)
				name = (String) o;
			else
				name = "guest";
			o = session.getAttribute("balance");
			if (o != null)
				balance = (double) o;
		}
	%>
	<table border="1">
		<tr>
			<td>User: <%=name%>
			</td>
			<td>Balance: <%=balance%>
			</td>
			<td><a href="login.jsp">Login</a></td>
			<td><a href="register.jsp">Register</a></td>
			<td><a href="LogOut">Logout</a></td>
			<td><a href="addMoney.jsp">Add money</a></td>
			<td><a href="drink.jsp">Buy drinks</a></td>
			<td><a href="addProduct.jsp">Add products</a></td>
			<td><a href="setPrices.jsp">Set prices</a></td>
			<td><a href="removeProducts.jsp">Remove products</a></td>
		</tr>
	</table>
	<br>
</body>
</html>