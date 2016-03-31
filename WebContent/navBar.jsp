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
	if (session.isNew() || ((String) session.getAttribute("status")).equals("Guest"))
		name = " ";
	else
		name = (String) session.getAttribute("currentSessionUserName");

%>
	<%=name%>
	<a href="login.jsp">Login</a>
	<a href="register.jsp">Register</a> 
    <a href="LogOut">Logout</a>
    <a href="drink.jsp">Buy drinks</a>
    <a href="addProduct.jsp">Add products</a>
    <a href="setPrices.jsp">Set prices</a>
    <a href="removeProducts.jsp">Remove products</a>
    
	<br>
</body>
</html>