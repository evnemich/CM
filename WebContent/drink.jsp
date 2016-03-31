<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose drink to buy</title>
</head>
<body>
	<%@ include file="navBar.jsp"%>
	U can buy drinks here
	<form action="BuyDrink">
		<table>
			<tr>
				<td>Drinkname</td>
				<td><input type="checkbox" name="drink1" /><br></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Choose ingredients"></td>
			</tr>
		</table>
	</form>
</body>
</html>