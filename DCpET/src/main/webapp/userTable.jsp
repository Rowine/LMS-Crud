<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>    
<%@ page import="com.jdbc.JUsersDTO"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
</head>
<body>
	<h1>Users</h1>
	<%
	ArrayList<JUsersDTO> userDTOList= new ArrayList<JUsersDTO>();

	userDTOList= (ArrayList<JUsersDTO>)request.getAttribute("userDTOList");
	%>  <table border="3">
       <tr>
        <th>User ID</th>
        <th>Name</th>
        <th>Email</th>
       </tr>
   
   <% for(int x=0;x<userDTOList.size();x++){
    %>    
    <tr>
    <td><%= userDTOList.get(x).getUserId()%></td>
    <td><%=    userDTOList.get(x).getName()%></td>
    <td><%=    userDTOList.get(x).getEmail()%></td>
    </tr>
     <%
    }%>
</table>
</body>
</html>