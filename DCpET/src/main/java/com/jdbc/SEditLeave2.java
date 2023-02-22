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
 * Servlet implementation class SEditLeave2
 */
public class SEditLeave2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SEditLeave2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();  
	        
	        String userId=request.getParameter("userId");
		 	int IuserId = Integer.parseInt(userId);
	        String sApplicationId=request.getParameter("applicationId"); 
	        int applicationId = Integer.parseInt(sApplicationId);
	        String reason=request.getParameter("reason");  
	        String leaveStart=request.getParameter("leaveStart");  
	        String leaveEnd=request.getParameter("leaveEnd");   
	          
	        JApplicationDTO application=new JApplicationDTO();
	        application.setApplicationId(applicationId);
	        application.setReason(reason);  
	        application.setLeaveStart(leaveStart);  
	        application.setLeaveEnd(leaveEnd);  
	        
	        int duplicate = JApplicationDAO.checkDateApplicationDuplicate(leaveStart, IuserId);
	        if (duplicate == 0) {
	        	if(leaveStart.compareTo(leaveEnd) < 0) {
	        		int status = JApplicationDAO.updateLeave(application);
		 	        JUsersDTO user = JUsersDAO.getUserById(IuserId); 
		 	        if(status>0) {
		 	        	HttpSession session = request.getSession();
		 	        	session.setAttribute("name", user.getName());
		 	        	session.setAttribute("userId", user.getUserId());
		 	        	String error = (String)session.getAttribute("error");
		 	        	if(error != null) {
		 	        		session.setAttribute("error", null);
		 	        	}
		 	        	RequestDispatcher rd=request.getRequestDispatcher("home.jsp");  
		 	        	rd.forward(request, response);	
		 	        }else {
		 	        	HttpSession session = request.getSession();
		 	        	session.setAttribute("error", "Sorry! Unable to create.");
			        	request.getRequestDispatcher("/home.jsp").include(request, response); 
		 	        }
	        	}else {
	        		HttpSession session = request.getSession();
	 	        	session.setAttribute("error", "Sorry! There is a problem with your Date.");
		        	request.getRequestDispatcher("/home.jsp").include(request, response); 
	        	}
	        	 
	        }else {
	        	HttpSession session = request.getSession();
 	        	session.setAttribute("error", "Sorry! Dates have duplicates.");
	        	request.getRequestDispatcher("/home.jsp").include(request, response); 
	        }
	        
//	        JUsersDTO user = JApplicationDAO.getUserByApplicationId(applicationId);
//	        int status=JApplicationDAO.updateLeave(application);  
//	        if(status>0){
//	        	HttpSession session = request.getSession();
//	        	session.setAttribute("name", user.getName());
//	        	session.setAttribute("userId", user.getUserId());
//	        	RequestDispatcher rd=request.getRequestDispatcher("home.jsp");  
//	        	rd.forward(request, response);
//	        	
//	        	out.close();
//	        }else{  
//	            out.println("Sorry! unable to update record");  
//	        }  
	          
	        out.close();  
	}

}
