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
 * Servlet implementation class SCreateLeave
 */
public class SCreateLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SCreateLeave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        String sid= request.getParameter("userId");
        int id=Integer.parseInt(sid); 
          
        JUsersDTO user=JUsersDAO.getUserById(id);  
        
        if(user.getUserId() != 0) {
        	HttpSession session = request.getSession();
        	session.setAttribute("userId", user.getUserId());
        	RequestDispatcher rd=request.getRequestDispatcher("create.jsp");  
        	rd.forward(request, response);
        	
        	out.close();
        }
          
        out.close();  
	}
}
