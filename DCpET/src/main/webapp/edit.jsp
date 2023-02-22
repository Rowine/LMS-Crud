<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="com.jdbc.JApplicationDTO"%> 
<%@ page import="com.jdbc.JApplicationDAO"%>  
<%@ page import="java.util.*"%> 
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
      src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"
      integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    ></script>
  </head>
<body class="position-relative w-75 vh-100 m-auto">
<%

	List<String> optionList = new ArrayList<String>();
	optionList.add("Sick leave");
	optionList.add("Casual leave");
	optionList.add("Public leave");
	optionList.add("Religious leave");
	optionList.add("Maternity leave");
	optionList.add("Paternity leave");
	optionList.add("Bereavement leave");
	optionList.add("Compensatory leave");
	optionList.add("Sabbatical leave");
	optionList.add("Unpaid leave");
	
	int applicationId = (int)session.getAttribute("applicationId");
	
	int userId = (int)session.getAttribute("userId");

	JApplicationDTO appDetails = JApplicationDAO.getApplicationById(applicationId);
	
%>
 <a href="/DCpET/home.jsp" class="btn btn-primary m-5">Go back</a>
<div
      class="container-sm position-absolute top-50 start-50 translate-middle bg-light w-50 rounded-4"
    >
   
      <div class="m-auto">
        <h1 class="text-center p-3 fw-bold">Edit Leave Application</h1>
      </div>
      <form class="m-auto p-3" action='SEditLeave2' method='post'>
      <tr><td></td><td><input type='hidden' name='applicationId' value="<%= applicationId %>"/></td></tr>
      <tr><td></td><td><input type='hidden' name='userId' value="<%= userId %>"/></td></tr>
        <div class="mb-3">
        <label for="reason" class="col-form-label">Reason:</label>
          <select class="form-select"  id="reason" name='reason'>
				<% for (String i : optionList) {%>
		<%if(i.equals(appDetails.getReason())){ %>
			<option selected><%= i %> </option>
		<%} else{ %>
			<option><%=i %></option>
		<%}
		} %>
			</select>
        </div>
        <div class="d-flex justify-content-between mt-4">
          <div class="col-5">
            <label for="date" class="col-form-label">Leave Start:</label>
            <div class="input-group date" id="leaveStart">
              <input type="date" class="form-control date_picker" id="leaveStart" name="leaveStart" value="<%= appDetails.getLeaveStart()%>"/>

            </div>
          </div>
          <div class="col-5">
            <label for="date" class="col-form-label">Leave End:</label>
            <div class="input-group" id="leaveEnd" >
              <input type="date" class="form-control  date_picker" id="leaveEnd" name="leaveEnd" value="<%= appDetails.getLeaveEnd()%>"/>
            </div>
          </div>
        </div>
        <div class="mt-5 d-grid gap-2">
          <button type="submit" class="btn btn-primary">Submit</button>
        </div>
      </form>
    </div>
    
    <script type="text/javascript">
    var today = new Date()
    var dd = String(today.getDate()).padStart(2, '0')
    var mm = String(today.getMonth() + 1).padStart(2, '0')
    var yyyy = today.getFullYear()

    today = yyyy + '-' + mm + '-' + dd
    $('.date_picker').attr('min', today)
  </script>
</body>
</html>