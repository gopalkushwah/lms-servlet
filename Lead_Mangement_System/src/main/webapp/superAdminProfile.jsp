<%@page import="java.util.List"%>
<%@page import="in.pandit.dao.LeadDao"%>
<%@page import="in.pandit.dao.UserDao"%>
<%@page import="in.pandit.helper.CookiesHelper"%>
<%@page import="in.pandit.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.text.SimpleDateFormat" %>
<%@page import = "java.util.Date" %>
<%
	User userCookie = CookiesHelper.getUserCookies(request, "user");

	//                                   isadmin,issuperadmin
	int userCount = UserDao.getUserCount("false","false");
	int adminCount = UserDao.getUserCount("true","false");
	int totalLeadCountByStatusFinished = LeadDao.getLeadsCountUsingUserIdAndStatus(userCookie.getUserId(), "Finished");
	int totalLeadCount = LeadDao.getTotalLeadsCount();
%>
<!Doctype HTML>
	<html>
	<head>
		<title>Profile</title>
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
.profile-field-container{
	display: grid;
	grid-template-columns: 1fr 1fr;
}
.profile-img-update{
	width: 100px;
	height: 100px;
	border-radius: 14rem;
	border:2px solid white;
	box-shadow: 0px 0px 3px white;
	margin: 10px;
	cursor: pointer;
}
.profile-img-update:hover{
	transform: scale(1.05);
	transition: all .3s ease-in-out;
}
.user-info-container{
	margin: 4px;
	display: grid;
	grid-template-columns:1fr 1fr;
}
.info-container{
	background-color: var(--primary);
	margin: 4px;
	height: 100px;
	border-radius: .4rem;
	padding: 4px;
	border:2px solid white;
	cursor:pointer;
	box-shadow: 0px 0px 3px white;
}
.info-container:hover{
	transform: scale(1.05);
	transition: all .3s ease-in-out;
}
</style>
</head>
	<body>
		
		<% 
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if(session.getAttribute("email") == null)
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('You are no longer logged in');");
			out.println("</script>");
			response.sendRedirect("index.jsp");
		}
	%>
		
	  <div id="mySidenav" class="sidenav">
	  <p class="logo"><span>L </span>M S</p>
	  <a href="superadmin.jsp" class="icon-a"><i class="fa fa-dashboard icons"></i>   Dashboard</a>
	  <a href="allLeadsSuperAdmin.jsp"class="icon-a"><i class="fa fa-line-chart icons"></i>   All Leads</a>
	  <a href="allAdmin.jsp"class="icon-a"><i class="fa fa-user-circle icons"></i>   All Admin</a>
	  <a href="allUsersSuperAdmin.jsp"class="icon-a"><i class="fa fa-users icons"></i>   All Users</a>
	  <a href="superAdminProfile.jsp"class="icon-a"><i class="fa fa-user icons"></i>   Profile</a>
	  <a href="superAdminHelp.jsp"class="icon-a"><i class="fa fa-question-circle icons"></i>   Help</a>
	  <form action = "logout" method = "post"><a href="#"class="icon-a"><button type = "submit" class = "logBtn"><i class="fa fa-sign-out icons"></i> Logout</button></a></form> 
		 <div class = "timeDate">
	  	<p style = "margin-top: 330px; margin-left: 5px; font-size: 18px; color: gray;">Date and Time</p>
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
					<span  class="fs-2 fw-bold"><%= userCount %></span>
					<span>All Users</span>
				</div>
				<i class="fa fa-users box-icon"></i>
			</div>
			<div class="d-flex text-white align-items-center justify-content-around status-card">
				<div class="d-flex flex-column">
					<span  class="fs-2 fw-bold"><%= adminCount %></span>
					<span>All Admins</span>
				</div>
					<i class="fa fa-user-circle box-icon"></i>
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
			<p class="fs-2 text-white box-heading">Profile</p>
		</div>
		<hr class="divide">
		<div class="main-container pb-1">
			<div class="d-flex w-100 align-items-center justify-content-center">
				<img class="profile-img-update" src="image/profile image.png"  />	
			</div>
			<div class="user-info-container p-4 gap-2"> 	
				<% User user = UserDao.getCompanyInfo(userCookie.getUserId()); %>
				<div class="d-flex flex-column text-white info-container">
					<h3>Name</h3>
					<h5 style = "color: white;"><%=user.getName() %></h5>
				</div>
				<div class="d-flex flex-column text-white info-container">
					<h3>Email</h3>
					<h5 style = "color: white;"><%= user.getEmail() %></h5>
				</div>
				<div class="d-flex flex-column text-white info-container">
					<h3>Mobile</h3>
					<h5 style = "color: white;"><%= user.getMobile()%></h5>
				</div>
				<div class="d-flex flex-column text-white info-container">
					<h3>User Id</h3>
					<h5 style = "color: white;"><%= user.getUserId()%></h5>
				</div>
			</div>
			<form action = "updateSuperAdminProfile.jsp" method = "post" class="w-100 d-flex align-items-center justify-content-center mb-5">
				<button type= "submit" class ="submit-btn w-50">Update Profile</button>
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