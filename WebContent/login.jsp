<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Coffee-machine</title>
</head>
<body>
	<%@ include file="navBar.jsp"%>
	<form action="LogIn">
		<table>
			<tr>
				<td>Login</td>
				<td><input type="text" name="login" /><br></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /><br></td>
			</tr>
			<tr>
				<td><input type="submit" value="login"></td>
				<td><button formaction="register.jsp">register</button></td>
			</tr>
		</table>
	</form>
</body>
</html>