package com.jdbc;
import java.util.*;  
import java.sql.*;

public class JUsersDAO {
	
	public static Connection getConnection(){  
        Connection con=null;  
        try{  
        	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        	//Get a connection
        	con=DriverManager.getConnection("jdbc:derby:C:\\Users\\Rowine\\MyDB;create=true");  
        }catch(Exception e){System.out.println(e);}  
        return con;  
    }  
	
	
    public static int save(JUsersDTO user){  
        int status=0;  
        try{  
            Connection con=JUsersDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "insert into users (name,email,password) values (?,?,?)");  
            ps.setString(1,user.getName());  
            ps.setString(2,user.getEmail());  
            ps.setString(3,user.getPassword());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    
    
    public static int update(JUsersDTO user){  
        int status=0;  
        try{  
            Connection con=JUsersDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "update users set name=?,email=?,password=? where id=?");  
            ps.setString(1,user.getName());  
            ps.setString(2,user.getEmail());  
            ps.setString(3,user.getPassword());  
            ps.setInt(4,user.getUserId());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    
    public static int addAdmin(int id){  
        int status=0;  
        try{  
            Connection con=JUsersDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "update users set is_admin='True' where user_id=?");   
            ps.setInt(1,id);  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    } 
    
    public static int removeAdmin(int id){  
        int status=0;  
        try{  
            Connection con=JUsersDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "update users set is_admin='False' where user_id=?");   
            ps.setInt(1,id);  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    } 
    
    
    public static int delete(int id){  
        int status=0;  
        try{  
            Connection con=JUsersDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from users where id=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    
    
    public static JUsersDTO getUserById(int id){  
    	JUsersDTO user=new JUsersDTO();  
          
        try{  
            Connection con=JUsersDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from users where user_id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                user.setUserId(rs.getInt(1));  
                user.setName(rs.getString(2));  
                user.setEmail(rs.getString(3));  
                user.setPassword(rs.getString(5));  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return user;  
    }  
    
    public static JUsersDTO signInUser(String email, String password ){  
    	JUsersDTO user=new JUsersDTO();  
          
        try{  
            Connection con=JUsersDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("SELECT * FROM USERS WHERE email=? AND password=?");  
            ps.setString(1,email);
            ps.setString(2, password);
            
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){
                user.setUserId(rs.getInt(1));  
                user.setName(rs.getString(2));  
                user.setEmail(rs.getString(3));  
                user.setIsAdmin(Boolean.parseBoolean(rs.getString(4)));
                user.setPassword(rs.getString(5)); 
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return user;  
    } 
    public static List<JUsersDTO> getAllEmployees(){  
        List<JUsersDTO> list=new ArrayList<JUsersDTO>(); 
        try{  
            Connection con=JUsersDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from users");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	JUsersDTO user=new JUsersDTO();  
                user.setUserId(rs.getInt(1));  
                user.setName(rs.getString(2));  
                user.setEmail(rs.getString(3));  
                user.setIsAdmin(Boolean.parseBoolean(rs.getString(4)));
                user.setPassword(rs.getString(5));  
                list.add(user);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }  
}  
