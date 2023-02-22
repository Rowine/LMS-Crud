package com.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class SRetrieveUsers
 */
public class SRetrieveUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private static String dbURL = "jdbc:derby:C:\\Users\\Rowine\\MyDB;create=true";
	
	// jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SRetrieveUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Set the connection driver
				try
		        {
		            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		            //Get a connection
		            conn = DriverManager.getConnection(dbURL);
		        }
		        catch (Exception except)
		        {
		        	System.out.println("Error");
		            except.printStackTrace();
		        }
				
				// connect to the database and retrieval
				
				 JUsersDTO userDTO= new JUsersDTO();    
			       
			        ArrayList<JUsersDTO> userDTOList= new ArrayList<JUsersDTO>();
			       
			       
			        try
			            {
			                stmt = conn.createStatement();
			                ResultSet rs = stmt.executeQuery("select * from USERS");
			             
			                   
			                    while (rs.next()) {
			                        System.out.println(
			                          rs.getString("email") + " - " + rs.getString("name"));
			                        userDTO= new JUsersDTO();
			                       
			                        userDTO.setUserId(rs.getInt("user_id"));
			                        userDTO.setName(rs.getString("name"));
			                        userDTO.setEmail(rs.getString("email"));
			                       
			                        userDTOList.add(userDTO);
			                      }
			                    request.setAttribute("userDTOList", userDTOList);
			                rs.close();
			                stmt.close();
			            }
			            catch (SQLException sqlExcept)
			            {
			            	System.out.println("Error");
			                sqlExcept.printStackTrace();
			            }
				
				// set the value to the request object
				
				// call the JSP
				RequestDispatcher rd = request.getRequestDispatcher("userTable.jsp");
				rd.forward(	request, response);
						
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
