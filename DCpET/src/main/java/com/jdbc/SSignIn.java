package com.jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SSignIn
 */
public class SSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SSignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
        String email=request.getParameter("email");  
        String password=request.getParameter("password");
        
        JUsersDTO user = JUsersDAO.signInUser(email, password);
        
        if(user.getUserId() != 0) {
        	HttpSession session = request.getSession();
        	session.setAttribute("name", user.getName());
        	session.setAttribute("userId", user.getUserId());
        	session.setAttribute("isAdmin", user.getIsAdmin());
        	String error = (String)session.getAttribute("error");
        	if(error != null) {
        		session.setAttribute("error", null);
        	}
        	request.getRequestDispatcher("/home.jsp").include(request, response);
        	
        	out.close();
        }else {
        	HttpSession session = request.getSession();
        	session.setAttribute("error", "Sorry! Unable to login");
        	response.sendRedirect("index.jsp");
        }
        out.close();
	}

}
