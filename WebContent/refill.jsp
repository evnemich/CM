<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.evnemich.coffeemachine.CoffeeMachine"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Write how much products to add</title>
</head>
<body>
<%@ include file="navBar.jsp"%>
    <%
        pageContext.setAttribute("products", CoffeeMachine.products);
    %>
    Check drinks you want to buy
    <form action="Refill">
        <table>
            <c:forEach items="${products}" var="current">
                <tr>
                    <td><c:out value="${current}" /></td>
                    <td><input type="text" name="${current}" value="0" /></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td><input type="submit" value="Refill machine"></td>
            </tr>
        </table>
    </form>
</body>
</html>