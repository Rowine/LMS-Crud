package com.jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SRemoveAdmin
 */
public class SRemoveAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SRemoveAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		 PrintWriter out=response.getWriter();
		 HttpSession session = request.getSession();
		 
		 String sid=request.getParameter("userId");
		 Integer loggedInUser = (Integer)session.getAttribute("userId");
		 
		 Integer id=Integer.parseInt(sid);  
		 int status = JUsersDAO.removeAdmin(id);
		 if(status > 0) {
			 if(id.equals(loggedInUser)) {
				 response.sendRedirect("SSignOut");  
			 }else {
				 request.getRequestDispatcher("userList.jsp").include(request, response); 
			 }
			 out.close();
		 }else {
	        session.setAttribute("error", "Sorry! Removing admin unsucessful.");
	        request.getRequestDispatcher("userList.jsp").include(request, response); 
	        out.close();
		 }
		 out.close();
	}


}
