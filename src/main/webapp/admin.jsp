<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="in.pandit.dao.UserDao"%>
<%@page import="in.pandit.dao.LeadDao"%>
<%@page import="in.pandit.helper.CookiesHelper"%>
<%@page import="in.pandit.model.User"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import = "in.pandit.persistance.DatabaseConnection" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.ArrayList" %>
<%@page import = "java.text.SimpleDateFormat" %>
<%@page import = "java.util.Date" %>
<%
	User userCookie = CookiesHelper.getUserCookies(request, "user"); 
	int totalLeadCount = LeadDao.getLeadsCountUsingUserId(userCookie.getUserId());
	int totalLeadCountByFacebookSource = LeadDao.getLeadsCountUsingCompanyIdAndSource(userCookie.getUserId(), "facebook");
	int totalLeadCountByGoogleSource = LeadDao.getLeadsCountUsingCompanyIdAndSource(userCookie.getUserId(), "google");
	int employeeCount = UserDao.getEmployeeCountUsingCompanyId(userCookie.getUserId());
	
	List<User> listUser = UserDao.getAllEmployeeByCompanyId(userCookie.getUserId());
%>
<html>
<head>
	<title>Admin Dashboard</title>
	<link rel="stylesheet" href="css/style.css" type="text/css"/>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="./css/dashboard.css" type="text/css"/>
	<!-- This cdn is for sweet alert -->
	<script src=
	"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.0/sweetalert.min.js">
  	</script>
  	<script src="https://common.olemiss.edu/_js/sweet-alert/sweet-alert.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://common.olemiss.edu/_js/sweet-alert/sweet-alert.css">
</head>

<body>
	<% 
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // Preventing from back after logout.
		if(session.getAttribute("email") == null)
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('You are no longer logged in');");
			out.println("</script>");
			response.sendRedirect("index.jsp");
		}
	%>
	
	<%
	if (request.getAttribute("messages") != null) {
		out.print("<script>swal('Thank You!', 'We will get in touch soon!', 'success')</script>");
	}
	%>
	
	<div id="mySidenav" class="sidenav">
		<p class="logo"><span>L </span>M S</p>
		<a href="admin.jsp" class="icon-a"><i class="fa fa-dashboard icons"></i>   Dashboard</a>
		<a href="allLeads.jsp"class="icon-a"><i class="fa fa-line-chart icons"></i>   All Leads</a>
		<a href="allUsers.jsp"class="icon-a"><i class="fa fa-user-plus icons"></i>   All Users</a>
		<a href="adminProfile.jsp"class="icon-a"><i class="fa fa-user-circle icons"></i>   Profile</a>
		<a href="adminHelp.jsp"class="icon-a"><i class="fa fa-question-circle icons"></i>   Help</a>
	  <form action = "logout" method = "post"><a href="#"class="icon-a"><button type = "submit" class = "logBtn"><i class="fa fa-sign-out icons"></i> Logout</button></a></form> 
	  <div class = "timeDate">
	  	<p style = "margin-top: 390px; margin-left: 5px; font-size: 18px; color: gray;">Date and Time</p>
	  	<%
	  		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			Date date = new Date();
	    	out.println("<p style = 'margin-top: -20px; margin-left: 5px; font-size: 16px; color: lightgray;''>"+formatter.format(date)+"</p>");
	  	%>
	  </div>
	</div>
	
	<div id="main">

		<div class="head">
			<div class="col-div-6">
	<span style="font-size:30px;cursor:pointer; color: white;" class="nav"  > Dashboard</span>
	<span style="font-size:30px;cursor:pointer; color: white;" class="nav2"  > Dashboard</span>
	</div>
		
		<div class="col-div-6">
		<div class="profile">

			<img src="image/xyz.jfif" class="pro-img" />
			<p style="font-size:20px; text-align: center;"><%= userCookie.getName() %> <span  style="font-size:12px; text-align: center;"><%= userCookie.getEmail() %></span></p>
		
		</div>
	</div>
		<div class="clearfix"></div>
	</div>

		<div class="clearfix"></div>
		<br/>
		
		<div class="col-div-3">
			<div class="box">
				<p>
					<%= totalLeadCount %> 
				<br/><span>Total Leads</span></p>
				<i class="fa fa-line-chart box-icon"></i>
			</div>
		</div>
		<div class="col-div-3">
			<div class="box">
				<p>
					<%= employeeCount %>
				<br/><span>All Users</span></p>
				<i class="fa fa-users box-icon"></i>
			</div>
		</div>
		<div class="col-div-3">
			<div class="box">
				<p>
					<%= totalLeadCountByFacebookSource %>
				<br/><span>Facebook Leads</span></p>
				<i class="fa fa-facebook-square box-icon"></i>
			</div>
		</div>
		<div class="col-div-3">
			<div class="box">
				<p>
					<%= totalLeadCountByGoogleSource %>
				<br/><span>Google Leads</span></p>
				<i class="fa fa-google-plus box-icon"></i>
			</div>
		</div>
		
		
		<div class="clearfix"></div>
		<br/>
		
		
		<!-- Adding new leads form -->
		<div class="clearfix"></div>
		<br/>
		
		<div class="col-12">
			<div class="boxheading">
				<p>Add New Lead</p>
				<%
					
				%>
			</div>
		</div>
		<hr style="width:97%;text-align:left;margin-left:10px">
		<div class="col-12">
			<div class="boxform">
				<form action = "AddLeadsAdmin" method = "post">
					<table>
						<tr>
							<td>Name </td>
							<td><input type = "text" name = "name" required/></td>
							<td>Email </td>
							<td><input type = "email" name = "email" required/></td>
						</tr>
						<tr>
							<td>Address </td>
							<td><input type = "text" name = "address" required/></td>
							<td>Mobile </td>
							<td><input type = "tel" name = "mobile" maxlength= "10" required/></td>
						</tr>
						<tr>
							<td>Source </td>
							<td><input type = "text" name = "source" required/></td>
							<td>Date </td>
							<td><input type = "date" name = "date" required/></td>
						</tr>
						<tr>
							<td>Time </td>
							<td><input type = "time" name = "time" required/></td>
							<td>Owner Email</td>
							
							
							<td>
								<input type = "email" name = "owner"  value = "<%= userCookie.getEmail()%>" readonly>
						
							</td>
							
						</tr>
						<tr>
							<td>Current Owner </td>
							<td>
							<select name = "currentOwner" required>
								<option selected disabled > --Select--</option>
								<%for(User x : listUser){%>
									<option ><%= x.getEmail() %></option>
								<% }%>
							</select>
							</td>
							
							<td>Status </td>
							<td>
							<select name="status" required>
								<option selected disabled > --Select--</option>
								<option>New</option>
								<option>Progress</option>
								<option>Finished</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>Priority </td>
							<td>
							<select name="priority" required>
								<option selected disabled > --Select--</option>
								<option>Low</option>
								<option>Medium</option>
								<option>High</option>
							</select>
							</td>
							<td></td>
							<td><button type="submit" class="btn">Add Lead</button></td>
						</tr>		
					</table>
				</form>
			</div>
		</div>	
		
		
			<br><br><br><br><br><br>
		
		</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>

	  $(".nav").click(function(){
	    $("#mySidenav").css('width','70px');
	    $("#main").css('margin-left','70px');
	    $(".logo").css('visibility', 'hidden');
	    $(".logo span").css('visibility', 'visible');
	     $(".logo span").css('margin-left', '-10px');
	     $(".icon-a").css('visibility', 'hidden');
	     $(".icons").css('visibility', 'visible');
	     $(".icons").css('margin-left', '-8px');
	      $(".nav").css('display','none');
	      $(".nav2").css('display','block');
	  });

	$(".nav2").click(function(){
	    $("#mySidenav").css('width','300px');
	    $("#main").css('margin-left','300px');
	    $(".logo").css('visibility', 'visible');
	     $(".icon-a").css('visibility', 'visible');
	     $(".icons").css('visibility', 'visible');
	     $(".nav").css('display','block');
	      $(".nav2").css('display','none');
	 });

	</script>
	
	</body>

</body>
</html>