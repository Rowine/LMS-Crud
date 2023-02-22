package com.jdbc;
import java.util.*;  
import java.sql.*;

public class JApplicationDAO {
	public static Connection getConnection(){  
        Connection con=null;  
        try{  
        	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        	//Get a connection
        	con=DriverManager.getConnection("jdbc:derby:C:\\Users\\Rowine\\MyDB;create=true");  
        }catch(Exception e){System.out.println(e);}  
        return con;  
    }  
	
	
    public static int create(JApplicationDTO application){  
        int status=0;  
        
        try{  
            Connection con=JUsersDAO.getConnection();  
         PreparedStatement ps=con.prepareStatement("INSERT INTO application (reason, leave_start, leave_end, user_id) VALUES ( ?, ?, ?, (SELECT user_id from users where user_id = ?))");
            
            ps.setString(1,application.getReason());  
            ps.setString(2,application.getLeaveStart());  
            ps.setString(3,application.getLeaveEnd());  
            ps.setString(4, application.getUserId());
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    
    public static List<JApplicationDTO> getAllApplicationById(String id){  
        List<JApplicationDTO> list=new ArrayList<JApplicationDTO>();  
          
        try{  
            Connection con=JApplicationDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from application where user_id=" + id);  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	JApplicationDTO application=new JApplicationDTO();  
            	application.setApplicationId(rs.getInt(1));  
            	application.setUserId(rs.getString(2));
            	application.setReason(rs.getString(3));  
                application.setStatus(rs.getString(4));  
                application.setLeaveStart(rs.getString(5));  
                application.setLeaveEnd(rs.getString(6));  
                list.add(application);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }  
    
    public static List<JApplicationDTO> getAllApplication(){  
        List<JApplicationDTO> list=new ArrayList<JApplicationDTO>(); 
        try{  
            Connection con=JApplicationDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from application");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	JApplicationDTO application=new JApplicationDTO();  
                application.setApplicationId(rs.getInt(1));  
                application.setUserId(rs.getString(2));  
                application.setReason(rs.getString(3));  
                application.setStatus(rs.getString(4));  
                application.setLeaveStart(rs.getString(5));  
                application.setLeaveEnd(rs.getString(6)); 
                list.add(application);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }
    
    public static int checkDateApplicationDuplicate(String leaveStart, int id){  
    	int status=0; 
          
        try{  
            Connection con=JApplicationDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from application where leave_start=? and user_id=?" );  
            ps.setString(1,leaveStart);  
            ps.setInt(2,id);  
            ResultSet rs = ps.executeQuery();  
            if(rs.next()) {
            	status=1;
            }
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    
    public static JApplicationDTO getApplicationById(int id){  
    	JApplicationDTO application= new JApplicationDTO();  
        
        try{  
            Connection con=JApplicationDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from application where application_id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                application.setUserId(rs.getString(2));  
                application.setReason(rs.getString(3));  
                application.setLeaveStart(rs.getString(5));  
                application.setLeaveEnd(rs.getString(6));  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return application;   
    }
    
    public static JUsersDTO getUserByApplicationId(int id){  
    	JUsersDTO user= new JUsersDTO();  
        
        try{  
            Connection con=JApplicationDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("select user_id from application where application_id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                user = JUsersDAO.getUserById(rs.getInt(1));  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return user;   
    }
    
    public static int updateLeave(JApplicationDTO application){  
        int status=0;  
        try{  
            Connection con=JApplicationDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "update application set reason=?,leave_start=?,leave_end=? where application_id=?");  
            ps.setString(1,application.getReason());  
            ps.setString(2,application.getLeaveStart());  
            ps.setString(3,application.getLeaveEnd());  
            ps.setInt(4,application.getApplicationId());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }
    
    public static int deleteApplication(int id){  
        int status=0;  
        try{  
            Connection con=JApplicationDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from Application where application_id=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    
    public static int approveApplication(int id){  
        int status=0;  
        try{  
            Connection con=JApplicationDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("update application set status='Approved' where application_id=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    

    public static int declineApplication(int id){  
        int status=0;  
        try{  
            Connection con=JApplicationDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("update application set status='Declined' where application_id=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    } 
    

    
}
