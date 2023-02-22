<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page session="true" %>
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
	int userId = (int)session.getAttribute("userId");
%>
<a href="/DCpET/home.jsp" class="btn btn-primary m-5">Go back</a>
<div
      class="container-sm position-absolute top-50 start-50 translate-middle bg-light w-50 rounded-4"
    >
      <div class="m-auto">
        <h1 class="text-center p-3 fw-bold">Leave Application</h1>
      </div>
      <form class="m-auto p-3" action='SCreateLeave2' method='post'>
      <tr><td></td><td><input type='hidden' name='userId' value=<%= userId %>></td></tr>
        <div class="mb-3">
        <label for="reason" class="col-form-label">Reason:</label>
          <select class="form-select"  id="reason" name='reason'>
				<option >Sick leave</option>
				<option >Casual leave</option>
				<option>Public holiday</option>
				<option >Religious holidays</option>
				<option >Maternity leave</option> 
				<option >Paternity leave</option>
				<option>Bereavement leave</option>
				<option >Compensatory leave</option>
				<option>Sabbatical leave</option>
				<option>Unpaid leave</option>
			</select>
        </div>
        <div class="d-flex justify-content-between mt-4">
          <div class="col-5">
            <label for="date" class="col-form-label">Leave Start:</label>
            <div class="input-group date" id="leaveStart">
              <input type="date" class="form-control date_picker" id="leaveStart" name="leaveStart"/>

            </div>
          </div>
          <div class="col-5">
            <label for="date" class="col-form-label">Leave End:</label>
            <div class="input-group" id="leaveEnd" >
              <input type="date" class="form-control date_picker" id="leaveEnd" name="leaveEnd"/>
            </div>
          </div>
        </div>
        <div class="mt-5 d-grid gap-2">
          <button type="submit" class="btn btn-primary">Submit</button>
        </div>
      </form>
    </div>

</body>
<script type="text/javascript">
    var today = new Date()
    var dd = String(today.getDate()).padStart(2, '0')
    var mm = String(today.getMonth() + 1).padStart(2, '0')
    var yyyy = today.getFullYear()

    today = yyyy + '-' + mm + '-' + dd
    $('.date_picker').attr('min', today)
  </script>
</html>