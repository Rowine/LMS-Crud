<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="java.util.ArrayList"%>    
<%@ page import="com.jdbc.JApplicationDTO"%> 
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
    <link rel="stylesheet" href="styles.css" />
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
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"
      integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    ></script>
  </head>
<body>
	<%
	int userId = (int)session.getAttribute("userId");
	String sUserId = Integer.toString(userId);
	
	String name = (String)session.getAttribute("name");
	
	Boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
	
	String error = (String)session.getAttribute("error");
	
	 List<JApplicationDTO> list = JApplicationDAO.getAllApplicationById(sUserId);

	%>  
	
	<!-- Navbar -->
    <div class="bg-light">
    <%
    	if(error != null){%>
    		<div
      class="bg-danger d-flex align-items-center justify-content-between"
      role="alert"
      id="alert"
    >
      <div></div>
      <div class="text-light text-center py-3"><%=error %></div>
      <button
        type="button"
        class="btn-close btn-lg me-3 my-2"
        aria-label="Close"
      ></button>
    </div>
    		
    	<% }%>
   
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
                <a class="nav-link active" aria-current="page" href="home.jsp"
                  >Leave Applications</a
                >
              </li>
              <%if(isAdmin){ %>
              	<li class="nav-item">
                <a class="nav-link " aria-current="page" href="admin.jsp"
                  >Approve Applications</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link " aria-current="page" href="userList.jsp"
                  >Users</a
                >
              </li>
              <%} %>
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
      <div class="d-flex justify-content-between">
        <div>
          <h1>Leave Applications</h1>
        </div>
        <div class="align-self-end">
        	<form action="SCreateLeave" method="post">
	        	<input type="hidden" name="userId" value=<%=sUserId %> />
	        	<button type="submit" class="btn btn-success">  <i class="fa-solid fa-plus"></i> Create Leave</button>
	        </form>
        </div>
      </div>
      <div class="table-responsive-md">
      <table class="table align-middle bg-white m-3 table-hover">
        <thead class="table-light">
          <tr>
          	<th>Application ID</th>
            <th>Reason</th>
            <th>Leave Start</th>
            <th>Leave End</th>
            <th>Status</th>
            <th>Actions</th>
           </tr>
          </thead>
         <tbody>
         	<%
		for(JApplicationDTO app:list){
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM d, yyyy");
	        Date leaveStartDate = inputFormat.parse(app.getLeaveStart());
	        Date leaveEndDate = inputFormat.parse(app.getLeaveEnd());
	        String formattedLeaveStart = outputFormat.format(leaveStartDate);
	        String formattedLeaveEnd = outputFormat.format(leaveEndDate);
		%>
		<tr>
			<td>
				<p class="fw-bold mb-1 text-muted"><%= app.getApplicationId() %></p></td>
			<td>
				<p class="fw-normal mb-1"><%= app.getReason()%></p>
			</td>
		<td>
			<p class="fw-normal mb-1"><%=formattedLeaveStart%></p>
		</td>
		<td>
			<p class="fw-normal mb-1"><%=formattedLeaveEnd%></p>
		</td>
		<td>
		<% if(app.getStatus().equals("Approved")){ %>
			<span class="badge bg-success rounded-pill d-inline">
			<%= app.getStatus()%>
		 	</span>
		<%}else if(app.getStatus().equals("Pending")){%>
			 <span class="badge bg-warning rounded-pill d-inline">Pending</span>
		<% }else {%>
			<span class="badge bg-danger rounded-pill d-inline">Declined</span>
		<% }%>
		</td>
		 <td>
              <a href='SEditLeave?userId=<%= sUserId%>&applicationId=<%= app.getApplicationId()%>' <%if(app.getStatus().equals("Pending")){%>
              class="btn btn-link btn-sm btn-rounded"
              <%}else{ %>
              class="btn btn-link btn-sm disabled" 
              <%} %>
              >
                <i class="fa-solid fa-pen-to-square"></i>
              </a>
              <a href='SDeleteLeave?userId=<%= sUserId%>&applicationId=<%= app.getApplicationId()%>' <%if(app.getStatus().equals("Pending")){%>
              class="btn btn-link btn-sm btn-rounded"
              <%}else{ %>
              class="btn btn-link btn-sm disabled" 
              <%} %>
              >
                <i class="fa-solid fa-trash"></i>
              </a>
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
    <script>
      $('body').on('click', '.btn-close', function (e) {
        $('#alert').remove()
      })
    </script>
</body>
</html>