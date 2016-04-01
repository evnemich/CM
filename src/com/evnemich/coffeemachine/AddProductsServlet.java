package com.evnemich.coffeemachine;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.evnemich.coffeemachine.models.User;

/**
 * Servlet implementation class AddProducts
 */
@WebServlet("/AddProducts")
public class AddProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductsServlet() {
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
	User user;
	Object o = session.getAttribute("currentSessionUser");

	if (o == null) {
	    response.sendRedirect("failed.jsp");
	    return;
	} else {
	    user = (User) o;
	}

	Enumeration<String> products = request.getParameterNames();
	do {
	    name = products.nextElement();
	    try {
		if (!CoffeeMachine.addNewProduct(user, name, Boolean.getBoolean(request.getParameter(name)))) {
		    response.sendRedirect("failed.jsp");
		    return;
		}
	    } catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} while (products.hasMoreElements());
	response.sendRedirect("done.jsp");

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
