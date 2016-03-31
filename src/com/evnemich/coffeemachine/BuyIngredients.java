package com.evnemich.coffeemachine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.evnemich.coffeemachine.models.User;

/**
 * Servlet implementation class BuyIngredients
 */
@WebServlet("/BuyIngredients")
public class BuyIngredients extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyIngredients() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	String name;
	boolean del = false;
	HttpSession session = request.getSession(true);
	User user = (User) session.getAttribute("currentSessionUser");
	String username = (String) session.getAttribute("currentSessionUser");
	session.removeAttribute("currentSessionUser");
	session.removeAttribute("currentSessionUsername");
	session.removeAttribute("status");

	Enumeration<String> drinks = session.getAttributeNames();

	do {
	    name = drinks.nextElement();
	    try {
		if (!CoffeeMachine.buy(user, name, 1))
		    del = true;
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    if (del)
		session.removeAttribute(name);

	} while (drinks.hasMoreElements());

	session.setAttribute("currentSessionUser", user);
	session.setAttribute("currentSessionUserName", username);
	session.setAttribute("status", "Logged in");

	if (del) {
	    response.sendRedirect("ingredients.jsp");
	    return;
	}

	Enumeration<String> ingredients = request.getAttributeNames();
	do {
	    name = ingredients.nextElement();
	    try {
		int i = Integer.parseInt((String) request.getAttribute(name));
		if (CoffeeMachine.buy(user, name, i))
		    session.setAttribute(name, i);
	    } catch (NumberFormatException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} while (ingredients.hasMoreElements());

	response.sendRedirect("ingredients.jsp");
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
