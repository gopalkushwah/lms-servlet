<%@page import="javax.xml.crypto.Data"%>
<%@page import="in.pandit.dao.UserDao"%>
<%@page import="in.pandit.dao.LeadDao"%>
<%@page import="in.pandit.helper.CookiesHelper"%>
<%@page import="in.pandit.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.List" %>
<%@page import = "java.text.SimpleDateFormat" %>
<%@page import = "java.util.Date" %>
<%
	User userCookie = CookiesHelper.getUserCookies(request, "user"); 
	int totalLeadCount = LeadDao.getLeadsCountUsingUserId(userCookie.getUserId());
	int totalLeadCountByFacebookSource = LeadDao.getLeadsCountUsingUserIdAndSource(userCookie.getUserId(), "facebook");
	int totalLeadCountByStatusFinished = LeadDao.getLeadsCountUsingUserIdAndStatus(userCookie.getUserId(), "Finished");
	
	List<User> listUser = UserDao.getAllEmployeeByCompanyId(userCookie.getCompanyId());
%>
<html>
	<head>
		<title>User Dashboard</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
body{
	margin:0px;
	padding: 0px;
	background-color:#1b203d;
	font-family: system-ui;
}
:root{
	--primary : #272c4a;
}
.profile-image{
	width: 40px;
	height: 40px;
}
.status-card-cantainer{
	margin: 4px;
	display: grid;
	grid-template-columns:1fr 1fr 1fr 1fr;
}
.status-card{
	background-color: var(--primary);
	margin: 4px;
	height: 100px;
	border-radius: .4rem;
	padding: 4px;
}
.nav-heading{
	font-size:30px;
	cursor:pointer;
	color: white;
}
.box-heading{
	background-color: var(--primary);
	border-radius: .4rem;
	padding: 4px;
}
.divide{
	height: 2px;
	background-color: white;
	opacity: 100%;
	margin: 10px 10px;
	box-shadow: 0px 0px 2px white;
}

.main-container{
	background-color: var(--primary);
	margin:10px 10px;
	border-radius: .4rem;
	
}
.submit-btn{
	background-color: var(--primary);
	border: 2px solid var(--primary);
	outline: none;
	border-radius: .4rem;
	color:white;
	box-shadow:0px 0px 5px white;
}
.submit-btn:hover{
	background-color: white;
	border: 2px solid white;
	color: var(--primary);
	transition: all .3s ease-in-out;
}

.logo{
	margin: 0px;
	margin-left: 80px;
    font-weight: bold;
    color: white;
    margin-bottom: 30px;
}
.logo span{
	color: #f7403b;
}
.sidenav {
  height: 100%;
  width: 300px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #272c4a;
  overflow: hidden;
  transition: 0.5s;
  padding-top: 30px;
}

.sidenav a {
  padding: 15px 8px 15px 32px;
  text-decoration: none;
  font-size: 20px;
  color: #818181;
  display: block;
  transition: 0.3s;
}
.sidenav a:hover {
  color: #f1f1f1;
  background-color:#1b203d;
}
.sidenav{
  position: fixed;
  top: 0;
  right: 25px;
  font-size: 36px;
}
#main {
  transition: margin-left .5s;
  padding: 16px;
  margin-left: 300px;
}

.box-icon{
	font-size: 40px!important;
    float: right;
    margin-top: 35px!important;
    color: #818181;
    padding-right: 10px;
}

.nav2{
	display: none;
}

.logBtn{
	width: 100%;
	height: 40px;
	background-color: transparent;
	color: gray;
	border: none;
	font-size: 20px;
	color: #818181;
  	display: block;
  	transition: 0.3s;
  	text-align: left;
  	margin-left: -5px;
}
.logBtn:hover {
  color: #f1f1f1;
  background-color:#1b203d;
}

</style>
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
	  <a href="dashboard.jsp" class="icon-a"><i class="fa fa-dashboard icons"></i>   Dashboard</a>
	  <a href="user-all-leads.jsp"class="icon-a"><i class="fa fa-line-chart icons"></i>   All Leads</a>
	  <a href="currentLead.jsp"class="icon-a"><i class="fa fa-bar-chart icons"></i>   Current Leads</a>
	  <a href="profile.jsp"class="icon-a"><i class="fa fa-user-circle icons"></i>   Profile</a>
	  <a href="help.jsp"class="icon-a"><i class="fa fa-question-circle icons"></i>   Help</a>
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
		<div class="row d-flex mt-3">
			<div class="col-9">
				<span class="nav nav-heading"  > Dashboard</span>
				<span class="nav2 nav-heading"  > Dashboard</span>
			</div>
			<div class="col-3">
				<div class="d-flex text-white gap-2 align-items-center">
					<img src="image/xyz.jfif" class="profile-image"/>
					<div class="d-flex flex-column align-items-start ">
						<span class="fs-4"><%= userCookie.getName() %> </span>
						<span><%= userCookie.getEmail() %></span>
					</div>
				</div>
			</div>
		</div>
		<div class="mt-3 mb-3 status-card-cantainer">
			<div class="d-flex text-white align-items-center justify-content-around status-card">
				<div class="d-flex flex-column">
					<span class="fs-2 fw-bold"><%= totalLeadCount %> </span>
					<span>Total Leads</span>
				</div>
				<i class="fa fa-line-chart box-icon"></i>
			</div>
			<div class="d-flex text-white align-items-center justify-content-around status-card">
				<div class="d-flex flex-column">
					<span  class="fs-2 fw-bold">1</span>
					<span>New Leads</span>
				</div>
				<i class="fa fa-cart-plus box-icon"></i>
			</div>
			<div class="d-flex text-white align-items-center justify-content-around status-card">
				<div class="d-flex flex-column">
					<span  class="fs-2 fw-bold"><%= totalLeadCountByFacebookSource %></span>
					<span>Facebook Leads</span>
				</div>
					<i class="fa fa-facebook-square box-icon"></i>
			</div>
			<div class="d-flex text-white align-items-center justify-content-around status-card">
				<div class="d-flex flex-column">
					<span  class="fs-2 fw-bold"><%= totalLeadCountByStatusFinished %></span>
					<span>Enrolled</span>
				</div>
				<i class="fa fa-tasks box-icon"></i>
			</div>
		</div>
		<div class="pe-2 ps-2">
			<p class="fs-2 text-white box-heading">Add New Lead</p>
		</div>
		<hr class="divide">
		<div class="main-container">
			<form action = "addNewLead" method = "post">
				<div class="row p-3">
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Name</label>
						<input class="form-control" type = "text" name = "name" required/>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Email</label>
						<input class="form-control" type = "email" name = "email" required/>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Address</label>
						<input class="form-control" type = "text" name = "address" required/>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Mobile</label>
						<input class="form-control" type = "tel" name = "mobile" maxlength= "10" required/>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Source</label>
						<input class="form-control" type = "text" name = "source" required list="source"/>
						<datalist id="datalistOptions">
						  <option value="google">
						  <option value="facebook">
						  <option value="instagram">
						  <option value="linkedin">
						  <option value="snapchat">
						  <option value="direct mail">
						  <option value="banner">
						</datalist>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Date</label>
						<input class="form-control" type = "date" name = "date" required/>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Time</label>
						<input class="form-control" type = "time" name = "time" required/>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Owner Email</label>
						<input class="form-control" type = "email" name = "owner"  value = "<%= userCookie.getEmail()%>" readonly>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Current Owner</label>
						<select class="form-control" name = "currentOwner" required>
							<option selected disabled > --Select--</option>
							<%for(User x : listUser){%>
								<option ><%= x.getEmail() %></option>
							<% }%>
						</select>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Priority</label>
						<select class="form-control" name="priority" required>
							<option selected disabled > --Select--</option>
							<option>Low</option>
							<option>Medium</option>
							<option>High</option>
						</select>
					</div>
					<div class="col-6 d-flex flex-column text-white">
						<label class="fs-5 mb-2 mt-3">Status</label>
						<select class="form-control" name="status" required>
							<option selected disabled > --Select--</option>
							<option>New</option>
							<option>Progress</option>
							<option>Finished</option>
						</select>
					</div>
					<div class="col-12 mt-2 w-100 d-flex justify-content-center">
						<button type="submit" class="submit-btn w-50 fw-bold">Add Lead</button>
					</div>
				</div>
			</form>
		</div>
	</div>	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
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
</html>