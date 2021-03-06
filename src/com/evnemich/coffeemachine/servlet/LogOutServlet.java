package com.evnemich.coffeemachine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.evnemich.coffeemachine.CoffeeMachine;
import com.evnemich.coffeemachine.models.User;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOut")
public class LogOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutServlet() {
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
	Object o = session.getAttribute("currentSessionUser");

	if (o != null) {
	    User user = (User) o;

	    CoffeeMachine.logOut(user);

	    session.removeAttribute("currentSessionUser");
	    session.removeAttribute("currentSessionUserName");
	    session.removeAttribute("balance");
	}

	response.sendRedirect("logout.jsp");
	session.invalidate();
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
