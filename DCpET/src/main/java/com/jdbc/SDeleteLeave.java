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
 * Servlet implementation class SDeleteLeave
 */
public class SDeleteLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SDeleteLeave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		 PrintWriter out=response.getWriter();
		 
		 
		String userId = request.getParameter("userId");
		int IuserId = Integer.parseInt(userId);
		String sid=request.getParameter("applicationId"); 
        int id=Integer.parseInt(sid);  
        int status = JApplicationDAO.deleteApplication(id);
        
        JUsersDTO user = JUsersDAO.getUserById(IuserId);
        if(status > 0) {
        	HttpSession session = request.getSession();
        	session.setAttribute("name", user.getName());
        	session.setAttribute("userId", user.getUserId());
        	RequestDispatcher rd=request.getRequestDispatcher("home.jsp");  
        	rd.forward(request, response);
        	
        	out.close();
        }else {
        	HttpSession session = request.getSession();
	        session.setAttribute("error", "Sorry! Deletion unsucessful.");
        	request.getRequestDispatcher("/home.jsp").include(request, response); 
        }
    }  

}
