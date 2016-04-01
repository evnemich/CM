<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding products</title>
</head>
<body>
<%@ include file="navBar.jsp"%>
    <form action="AddProduct">
        <table>
            <tr>
                <td>Product name</td>
                <td><input type="text" name="name" /><br></td>
            </tr>

            <tr>
                <td>Drink</td>
                <td><input type="checkbox" name="drink" /><br></td>
            </tr>
            <tr>
                <td> </td>
                <td><input type="submit" value="submit"></td>
            </tr>
        </table>
    </form>
</body>
</html>