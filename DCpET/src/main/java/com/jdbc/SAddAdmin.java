package com.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SAddAdmin
 */
public class SAddAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SAddAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		 PrintWriter out=response.getWriter();
		 
		 
		String sid=request.getParameter("userId"); 
		int id=Integer.parseInt(sid);  
		int status = JUsersDAO.addAdmin(id);
      
      if(status > 0) {
      	RequestDispatcher rd=request.getRequestDispatcher("userList.jsp");  
      	rd.forward(request, response);
      	
      	out.close();
      }else {
      		HttpSession session = request.getSession();
	        session.setAttribute("error", "Sorry! Adding admin unsucessful.");
      	request.getRequestDispatcher("/userList.jsp").include(request, response); 
      }
	}

}
