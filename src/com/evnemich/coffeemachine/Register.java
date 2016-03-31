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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
	try {

	    User user = DataBase.register(request.getParameter("login"), request.getParameter("password"));

	    if (user.getId() != 0) {

		HttpSession session = request.getSession(true);
		session.setAttribute("currentSessionUser", user);
		session.setAttribute("currentSessionUserName", request.getParameter("login"));
		response.sendRedirect("loginSuccessful.jsp"); // logged-in page
	    }

	    else
		response.sendRedirect("userAlreadyExist.jsp"); // error page
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
