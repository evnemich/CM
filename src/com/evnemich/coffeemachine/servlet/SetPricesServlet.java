package com.evnemich.coffeemachine.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.evnemich.coffeemachine.CoffeeMachine;
import com.evnemich.coffeemachine.models.User;

/**
 * Servlet implementation class SetPrices
 */
@WebServlet("/SetPrices")
public class SetPricesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetPricesServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession(true);
	String name;
	Object o = session.getAttribute("currentSessionUser");
	User user;
	int i;
	if (o == null) {
	    response.sendRedirect("failed.jsp");
	    return;
	}
	user = (User) o;

	Enumeration<String> products = request.getParameterNames();
	while (products.hasMoreElements()) {
	    name = products.nextElement();
	    try {
		i = Integer.parseInt((String) request.getParameter(name));
	    } catch (NumberFormatException e) {
		response.sendRedirect("failed.jsp");
		return;
	    }
	    if (i != 0)
		if (!CoffeeMachine.setPrice(user, name, i)) {
		    response.sendRedirect("failed.jsp");
		    return;
		}
	}
	response.sendRedirect("done.jsp");
	CoffeeMachine.updateData(user);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}
