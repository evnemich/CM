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
 * Servlet implementation class SetPrices
 */
@WebServlet("/SetPrices")
public class SetPrices extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetPrices() {
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
	User user = (User) session.getAttribute("currentSessionUser");

	Enumeration<String> products = request.getAttributeNames();
	do {
	    name = products.nextElement();
	    try {
		int i = Integer.parseInt((String) request.getAttribute(name));
		CoffeeMachine.setPrice(user, name, i);
	    } catch (NumberFormatException e) {
		e.printStackTrace();
	    }
	} while (products.hasMoreElements());

	response.sendRedirect("ingredientsAdded.jsp");
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
