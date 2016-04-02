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
 * Servlet implementation class AddProducts
 */
@WebServlet("/AddProduct")
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
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
	User user;
	boolean drink;
	Object o = session.getAttribute("currentSessionUser");

	if (o == null) {
	    response.sendRedirect("failed.jsp");
	    return;
	} else {
	    user = (User) o;
	}

	o = request.getParameter("drink");
	if (o == null)
	    drink = false;
	else
	    drink = true;

	if (!CoffeeMachine.addNewProduct(user, request.getParameter("name"), drink)) {
	    response.sendRedirect("failed.jsp");
	    return;
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
