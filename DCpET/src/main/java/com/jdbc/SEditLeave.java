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
 * Servlet implementation class SEditLeave
 */
public class SEditLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SEditLeave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        String sApplicationId=request.getParameter("applicationId"); 
        int applicationId = Integer.parseInt(sApplicationId);  
        String sid= request.getParameter("userId");
        int id=Integer.parseInt(sid); 
        
       if(applicationId != 0) {
    	   HttpSession session = request.getSession();
       		session.setAttribute("applicationId",applicationId);
       		session.setAttribute("userId", id);
       		RequestDispatcher rd=request.getRequestDispatcher("edit.jsp");  
       		rd.forward(request, response);
       	
       		out.close();
       }  
        out.close();  
	}
}
