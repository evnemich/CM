package com.evnemich.coffeemachine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.evnemich.coffeemachine.models.User;

/**
 * Servlet implementation class AddMoney
 */
@WebServlet("/AddMoney")
public class AddMoneyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMoneyServlet() {
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
	if (o == null) {
	    response.sendRedirect("failed.jsp");
	    System.out.println("NO USER");
	    return;
	}
	User user = (User) o;

	double amount = (double) Double.parseDouble((String) request.getParameter("addCash"));
	if (user.addMoney(amount)) {
	    response.sendRedirect("done.jsp");
	} else {
	    response.sendRedirect("failed.jsp");
	    return;
	}
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
