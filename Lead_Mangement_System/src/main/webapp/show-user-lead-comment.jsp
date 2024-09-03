<%@page import="in.pandit.helper.CookiesHelper"%>
<%@page import="in.pandit.model.User"%>
<%@page import="in.pandit.dao.UserDao"%>
<%@page import="in.pandit.dao.CommentDao"%>
<%@page import="in.pandit.model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="in.pandit.model.Lead"%>
<%@page import="in.pandit.dao.LeadDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.text.SimpleDateFormat" %>
<%@page import = "java.util.Date" %>
<%
	String leadId = request.getParameter("leadid");
	Lead leadByLeadId = LeadDao.getLeadsByLeadId(leadId);
	List<Comment> listComment = CommentDao.getAllCommentByLeadId(leadId);
	User userCookie = CookiesHelper.getUserCookies(request, "user");
	int totalLeadCount = LeadDao.getLeadsCountUsingUserId(userCookie.getUserId());
	int totalLeadCountByFacebookSource = LeadDao.getLeadsCountUsingUserIdAndSource(userCookie.getUserId(), "facebook");
	int totalLeadCountByStatusFinished = LeadDao.getLeadsCountUsingUserIdAndStatus(userCookie.getUserId(), "Finished");
	
%>
<!Doctype HTML>
	<html>
	<head>
		<title>All Leads</title>
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
.comment-inner-conatiner{
	background-color: var(--primary);
	display: grid;
	grid-template-columns:7fr 1fr;
	border-radius: .4rem;
	color:white;
	box-shadow: 0px 0px 5px white;
}
.comment-inner-conatiner:hover{
	background-color: white;
	color:var(--primary);
	transition:all .3s ease-in-out;
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
	  <a href="dashboard.jsp" class="icon-a"><i class="fa fa-dashboard icons"></i>   Dashboard</a>
	  <a href="user-all-leads.jsp"class="icon-a"><i class="fa fa-line-chart icons"></i>   All Leads</a>
	  <a href="currentLead.jsp"class="icon-a"><i class="fa fa-bar-chart icons"></i>   Current Leads</a>
	  <a href="profile.jsp"class="icon-a"><i class="fa fa-user-circle icons"></i>   Profile</a>
	  <a href="help.jsp"class="icon-a"><i class="fa fa-question-circle icons"></i>   Help</a>
	  <form action = "logout" method = "post"><a href="#"class="icon-a"><button type = "submit" class = "logBtn"><i class="fa fa-sign-out icons"></i> Logout</button></a></form> 
		 <div class = "timeDate">
	  	<p style = "margin-top: 320px; margin-left: 5px; font-size: 18px; color: gray;">Date and Time</p>
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
				<span class="nav nav-heading"  > All Comment</span>
				<span class="nav2 nav-heading"  > All Comment</span>
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
		<hr class="divide">
		<div class="main-container">
			<div class="overflow-x-scroll p-2">
				<table class="table table-dark table-striped table-hover">
  					<thead>
						<tr>
							<th>L.Id</th>
							<th>Name</th>
							<th>Email</th>
							<th>Source</th>
							<th>Date</th>
							<th>Owner</th>
						</tr>
  					</thead>
	  				<tbody>
						<tr>
							<td><%= leadByLeadId.getId() %></td>
							<td><%= leadByLeadId.getName() %></td>
							<td><%= leadByLeadId.getEmail() %></td>
							<td><%= leadByLeadId.getSource() %></td>
							<td><%= leadByLeadId.getDate() %></td>
							<td><%= leadByLeadId.getOwner() %></td>
						</tr>
	   				</tbody>
				</table>
				<div class="comment-container">
				<%for(Comment comment : listComment){
					User userByUserId = UserDao.getUserByUserId(comment.getUserId());
				%>
				<div class="comment-inner-conatiner p-2 m-2">
					<div class="comment-content">
			            <h5>Posted By : <%= userByUserId.getName() %></h5>
			            <h5>Comment : </h5>
			            <p><%= comment.getComment() %></p>
			            <h5>Posted on : <%= comment.getCreationDate() %></h5>
			        </div>
			        	
			        <%
			        if(comment.getUserId().equals(userCookie.getUserId())) {
			        %>
				        <div class="d-flex align-items-center justify-content-center">
							<form method="post" action="update-user-comment.jsp">
								<input name="comment-id" value="<%= comment.getId() %>" hidden="true">
								<input name="user-id" value="<%= comment.getUserId() %>" hidden="true">
								<input name="comment" value="<%= comment.getComment() %>" hidden="true">
				            	<button type="submit" class="submit-btn">Update</button>
							</form>
				        </div>
			        <%} %>
		        	</div>
				<%} %>
				</div>
			</div>
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