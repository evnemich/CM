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
public class BuyIngredientsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyIngredientsServlet() {
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
	Object o;
	boolean del = false;
	HttpSession session = request.getSession(true);
	User user;
	String username;
	o = session.getAttribute("currentSessionUser");
	if (o == null) {
	    response.sendRedirect("failed.jsp");
	    return;
	}
	user = (User) o;
	o = session.getAttribute("currentSessionUserName");
	if (o == null) {
	    response.sendRedirect("failed.jsp");
	    return;
	}
	username = (String) o;
	o = session.getAttribute("balance");
	session.removeAttribute("currentSessionUser");
	session.removeAttribute("currentSessionUserName");
	session.removeAttribute("balance");
	session.removeAttribute("status");

	Enumeration<String> drinks = session.getAttributeNames();

	while (drinks.hasMoreElements()) {
	    name = drinks.nextElement();
	    try {
		if (!CoffeeMachine.buy(user, name, 1))
		    del = true;
		else
		    session.removeAttribute(name);
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("EX");
		e.printStackTrace();
	    }
	    if (del)
		session.removeAttribute(name);

	}

	Enumeration<String> ingredients = request.getParameterNames();
	while (!del && ingredients.hasMoreElements()) {
	    name = ingredients.nextElement();
	    try {
		int i = Integer.parseInt((String) request.getParameter(name));
		if (i != 0)
		    if (CoffeeMachine.buy(user, name, i)) {
			session.setAttribute(name, i);
		    } else
			del = true;
	    } catch (NumberFormatException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	if (del)
	    response.sendRedirect("partlyDone.jsp");
	else {
	    drinks = session.getAttributeNames();
	    while (drinks.hasMoreElements()) {
		name = drinks.nextElement();
		session.removeAttribute(name);
	    }
	    response.sendRedirect("done.jsp");
	}
	session.setAttribute("currentSessionUser", user);
	session.setAttribute("currentSessionUserName", username);
	session.setAttribute("status", "Logged in");
	session.setAttribute("balance", user.getMoney());
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
