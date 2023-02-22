<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.jdbc.JUsersDTO"%> 
<%@ page import="com.jdbc.JUsersDAO"%> 
<%@ page import="java.util.*"%> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
      integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  </head>
  <body>
  <%
	int userId = (int)session.getAttribute("userId");
	String sUserId = Integer.toString(userId);
	
	String name = (String)session.getAttribute("name");
	
	String error = (String)session.getAttribute("error");
	
	
	String deleteAdminId = (String)session.getAttribute("deleteAdminId");
	
	List<JUsersDTO> list = JUsersDAO.getAllEmployees();
	
	%> 
    <div class="bg-light">

      <nav class="navbar navbar-expand-lg navbar-light container-sm">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">L.M.S</a>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarText"
            aria-controls="navbarText"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="home.jsp"
                  >Leave Applications</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" aria-current="page" href="admin.jsp"
                  >Approve Applications</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="userList.jsp">Users</a>
              </li>
            </ul>
            <div class="text-end py-2 navbar-text">
              <form action="SSignOut" method="post">
	        		<button type="submit" class="btn btn-primary">
	        		<i class="fas fa-sign-out-alt"></i>
	                Logout as <strong><%= name %></strong>
	                </button>
	        	</form>
            </div>
          </div>
        </div>
      </nav>
    </div>

    <div class="container-sm">
      <div>
        <h1>Users</h1>
      </div>
      <table class="table align-middle mb-0 bg-white">
        <thead class="bg-light">
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Admin</th>
            <th>Make Admin</th>
          </tr>
        </thead>
        <tbody>
        <%
		for(JUsersDTO user:list){
		%>
          <tr>
            <td>
              <div class="d-flex align-items-center">
                <div>
                  <p class="fw-bold mb-1"><%=user.getName() %></p>
                </div>
              </div>
            </td>
            <td>
              <p class="fw-normal mb-1"><%= user.getEmail()%></p>
            </td>
            <td>
            <% if(user.getIsAdmin()){ %>
              <span class="badge bg-success rounded-pill d-inline"
                >Admin</span>
             <%}else{ %>
             	<span class="badge bg-danger rounded-pill d-inline"
                >Not Admin</span>
             <%} %>
            </td>
            <td>
              <div class="d-flex align-items-center">
                <div class="d-flex align-items-center">
                <% if(user.getIsAdmin()){ %>
                  <a
                  	href='SRemoveAdmin?userId=<%= user.getUserId()%>'
                    class="btn btn-sm btn-outline-danger ms-4"
                    type="button"
                  >
                    <i class="fas fa-times"></i>
                  </a>
                 <%}else{ %>
                 <a
                 	href='SAddAdmin?userId=<%= user.getUserId()%>'
                    class="btn btn-sm btn-outline-success ms-4"
                    type="button"
                  >
                    <i class="fas fa-check"></i>
                  </a>
                  <%} %>
                </div>
              </div>
            </td>
          </tr>
         <%} %>
        </tbody>
      </table>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
