package com.evnemich.coffeemachine;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.evnemich.coffeemachine.models.User;

/**
 * Servlet implementation class LogIn
 */
@WebServlet(urlPatterns = { "/LogIn" })
public class LogInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    User user = CoffeeMachine.logIn(request.getParameter("login"), request.getParameter("password"));

	    if (user.isValid()) {
		System.out.println("UPD");
		CoffeeMachine.updateData(user);
		System.out.println("UPD");
		HttpSession session = request.getSession(true);
		session.setAttribute("currentSessionUser", user);
		session.setAttribute("balance", user.getMoney());
		session.setAttribute("currentSessionUserName", request.getParameter("login"));
		response.sendRedirect("done.jsp");
	    } else
		response.sendRedirect("loginFailed.jsp");
	}

	catch (Throwable theException) {
	}
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
