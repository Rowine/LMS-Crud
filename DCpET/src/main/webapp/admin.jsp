<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>    
<%@ page import="com.jdbc.JApplicationDTO"%> 
<%@ page import="com.jdbc.JUsersDTO"%> 
<%@ page import="com.jdbc.JApplicationDAO"%>     
<%@ page import="java.util.*"%> 
<%@ page import="java.text.SimpleDateFormat"%> 
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
	
	 List<JApplicationDTO> list = JApplicationDAO.getAllApplication();
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
                <a class="nav-link " aria-current="page" href="home.jsp"
                  >Leave Applications</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="admin.jsp"
                  >Approve Applications</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="userList.jsp">Users</a>
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
        <h1>Approve Applications</h1>
      </div>
      <div class="table-responsive-md">
      <table class="table align-middle mb-0 bg-white table-hover">
        <thead class="table-light">
          <tr>
            <th>Application ID</th>
            <th>Name</th>
            <th>Reasons</th>
            <th>Status</th>
            <th>Leave Start</th>
            <th>Leave End</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
        <%
		for(JApplicationDTO app:list){
			JUsersDTO user = JApplicationDAO.getUserByApplicationId(app.getApplicationId());
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM d, yyyy");
	        Date leaveStartDate = inputFormat.parse(app.getLeaveStart());
	        Date leaveEndDate = inputFormat.parse(app.getLeaveEnd());
	        String formattedLeaveStart = outputFormat.format(leaveStartDate);
	        String formattedLeaveEnd = outputFormat.format(leaveEndDate);
		%>
          <tr>
            <td>
              <p class="text-muted mb-0 fw-bold"><%= app.getApplicationId() %></p>
            </td>
            <td>
              <div class="d-flex align-items-center">
                <div class="">
                  <p class="fw-bold mb-1"><%= user.getName() %></p>
                  <p class="text-muted mb-0"><%= user.getEmail() %></p>
                </div>
              </div>
            </td>
            <td>
              <p class="fw-normal mb-1"><%=app.getReason() %></p>
            </td>
            <td>
            <% 
            String status = app.getStatus();
            if(status.equals("Approved")){
            	%>
            	<span class="badge bg-success rounded-pill d-inline"
                ><%=status %></span>
            <% }else if(status.equals("Pending")){%>
				
              <span class="badge bg-warning rounded-pill d-inline"
                ><%=status %></span>
              <%}else{ %>
              	<span class="badge bg-danger rounded-pill d-inline"
                ><%=status %></span>
                <%} %>
            </td>
            <td><%=formattedLeaveStart %></td>
            <td><%=formattedLeaveEnd %></td>
            <td>
              <div class="d-flex align-items-center">
                <div class="d-flex align-items-center">
                  <a
                  	href='SApproveLeave?userId=<%= user.getUserId()%>&applicationId=<%= app.getApplicationId()%>'
                    class="btn btn-sm btn-outline-success me-2"
                    type="button"
                  >
                    <i class="fas fa-check"></i>
                  </a>
                  <a
                  	href='SDeclineLeave?userId=<%= user.getUserId()%>&applicationId=<%= app.getApplicationId()%>'
                    class="btn btn-sm btn-outline-danger me-2"
                    type="button"
                  >
                    <i class="fas fa-times"></i>
                  </a>
                </div>
              </div>
            </td>
          </tr>
        <%} %>
        </tbody>
      </table>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
