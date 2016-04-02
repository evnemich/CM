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
 * Servlet implementation class RemoveProducts
 */
@WebServlet("/RemoveProducts")
public class RemoveProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveProductsServlet() {
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
	if (o == null) {
	    response.sendRedirect("failed.jsp");
	    return;
	}
	user = (User) o;

	Enumeration<String> products = request.getParameterNames();
	while (products.hasMoreElements()) {
	    name = products.nextElement();
	    try {
		if (request.getParameter(name) != null)
		    if (!CoffeeMachine.removeProduct(user, name)) {
			response.sendRedirect("failed.jsp");
			return;
		    }
	    } catch (NumberFormatException e) {
		e.printStackTrace();
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
