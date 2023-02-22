package com.jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SRegisterUser
 */
public class SRegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SRegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		 PrintWriter out=response.getWriter();  
		 
		 	String name=request.getParameter("name");  
	        String email=request.getParameter("email");  
	        String password=request.getParameter("password");
	        
	        JUsersDTO userDTO= new JUsersDTO();
	        userDTO.setName(name);
	        userDTO.setEmail(email);
	        userDTO.setPassword(password);
	        
	        int status = JUsersDAO.save(userDTO);
	        if(status>0) {
	        	out.println("<p>Registered successfully!</p>");
	        	request.getRequestDispatcher("register.html").include(request, response); 
	        }else {
	        	out.println("Sorry! unable to register"); 
	        }
	        
	        out.close();
	}

}
