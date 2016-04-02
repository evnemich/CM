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
 * Servlet implementation class Refill
 */
@WebServlet("/Refill")
public class RefillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefillServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String name;
	Object o;
	boolean error = false;
	HttpSession session = request.getSession(true);
	User user;
	String username;
	o = session.getAttribute("currentSessionUser");
	if (o == null) {
	    response.sendRedirect("failed.jsp");
	    return;
	}
	user = (User) o;
	username = (String) session.getAttribute("currentSessionUserName");
	session.removeAttribute("currentSessionUser");
	session.removeAttribute("currentSessionUserName");
	session.removeAttribute("balance");

	Enumeration<String> products = request.getParameterNames();

	while (products.hasMoreElements()) {
	    name = products.nextElement();
	    if (!CoffeeMachine.addProduct(user, name, Integer.parseInt(request.getParameter(name))))
		error = true;
	}

	if (error)
	    response.sendRedirect("failed.jsp");
	else
	    response.sendRedirect("done.jsp");
	session.setAttribute("currentSessionUser", user);
	session.setAttribute("currentSessionUserName", username);
	session.setAttribute("balance", user.getMoney());
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
