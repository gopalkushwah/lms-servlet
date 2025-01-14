<%@page import="in.pandit.dao.LeadDao"%>
<%@page import="in.pandit.helper.CookiesHelper"%>
<%@page import="in.pandit.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import = "in.pandit.persistance.DatabaseConnection" %>
<%@page import = "java.text.SimpleDateFormat" %>
<%@page import = "java.util.Date" %>
<%
User userCookie = CookiesHelper.getUserCookies(request, "user");
int totalLeadCount = LeadDao.getTotalLeadsCount();
int totalLeadCountBySource = LeadDao.getLeadsCountUsingSourceFacebookOrGoogle();
int totalLeadCountNewLeads = LeadDao.getLeadsCountNewLeads();

int totalLeadCountByStatusFinished = LeadDao.getLeadsCountUsingUserIdAndStatus("Already Enrolled");
Connection connect = DatabaseConnection.getConnection();

%>
<!Doctype HTML>
	<html>
	<head>
		<title></title>
		<link rel="stylesheet" href="css/style.css" type="text/css"/>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<!-- cdn for table with pagination -->
		
<style>
body{
	margin:0px;
	padding: 0px;
	background-color:#1b203d;
	/* overflow: hidden; */
	font-family: system-ui;
}
.clearfix{
	clear: both;
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
.head{
	padding:20px;
}
.col-div-6{
	width: 50%;
	float: left;
}
.profile{
	display: inline-block;
	float: right;
	width: 160px;
}
.pro-img{
	float: left;
	width: 40px;
	margin-top: 5px;
	margin-left: -80px;
	border-radius: 50px;
}
.profile p{
	color: white;
	font-weight: 500;
	margin-left: -70px;
	margin-top: 4px;
	font-size: 13.5px;
}
.profile p span{
	font-weight: 400;
    font-size: 12px;
    display: block;
    color: #8e8b8b;
}
.col-div-3{
	width: 25%;
	float: left;
}
.box{
	width: 85%;
	height: 100px;
	background-color: #272c4a;
	margin-left: 10px;
	padding:10px;
}
.box p{
	 font-size: 35px;
    color: white;
    font-weight: bold;
    line-height: 30px;
    padding-left: 10px;
    margin-top: 20px;
    display: inline-block;
}
.box p span{
	font-size: 20px;
	font-weight: 400;
	color: #818181;
}
.box-icon{
	font-size: 40px!important;
    float: right;
    margin-top: 35px!important;
    color: #818181;
    padding-right: 10px;
}
.col-div-8{
	width: 70%;
	float: left;
}
.col-div-4{
	width: 30%;
	float: left;
}
.content-box{
	padding: 20px;
}
.content-box p{
	margin: 0px;
    font-size: 20px;
    color: #f7403b;
}
.content-box p span{
	float: right;
    background-color: #ddd;
    padding: 3px 10px;
    font-size: 15px;
}
.box-8, .box-4{
	width: 95%;
	background-color: #272c4a;
	height: 330px;
	
}
.nav2{
	display: none;
}

.box-8{
	margin-left: 10px;
}
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
  
}
td, th {
  text-align: left;
  padding:15px;
  color: #ddd;
  border-bottom: 1px solid #81818140;
}
.circle-wrap {
  margin: 50px auto;
  width: 150px;
  height: 150px;
  background: #e6e2e7;
  border-radius: 50%;
}
.circle-wrap .circle .mask,
.circle-wrap .circle .fill {
  width: 150px;
  height: 150px;
  position: absolute;
  border-radius: 50%;
}
.circle-wrap .circle .mask {
  clip: rect(0px, 150px, 150px, 75px);
}

.circle-wrap .circle .mask .fill {
  clip: rect(0px, 75px, 150px, 0px);
  background-color: #f7403b;
}
.circle-wrap .circle .mask.full,
.circle-wrap .circle .fill {
  animation: fill ease-in-out 3s;
  transform: rotate(126deg);
}

@keyframes fill {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(126deg);
  }
}
.circle-wrap .inside-circle {
  width: 130px;
  height: 130px;
  border-radius: 50%;
  background: #fff;
  line-height: 130px;
  text-align: center;
  margin-top: 10px;
  margin-left: 10px;
  position: absolute;
  z-index: 100;
  font-weight: 700;
  font-size: 2em;
}

/* Css for add lead form */
.boxheading{
	width: 96%;
	background-color: #272c4a;
	margin-left: 10px;
	padding:10px;
}
.boxheading p{
	 font-size: 25px;
    color: white;
    line-height: 30px;
    padding-left: 10px;
    margin-top: 7px;
    display: inline-block;
}
/* Table Desing */


table th , table td{
    text-align: center;
}

table tr:nth-child(even){
    background-color: #1b203d;
}

.pagination li:hover{
    cursor: pointer;
}
		table tbody tr {
			display: none;
		}

/* Profile Image Styling */
.profile-img{
	float: left;
	width: 60px;
	margin-top: 5px;
	border-radius: 50px;
}

/* Css for from */
.boxform{
	width: 96%;
	/* height: 550px; */
	background-color: #272c4a;
	margin-left: 10px;
	padding:10px;
}

.btn{
	width: 250px;
	height: 35px;
	background-color: rgba(12, 188, 6, 0.8);
	font-size: 15px;
	color: white;
	border: none;
	border-radius: 5px;
}
.btn:hover{
	box-shadow: 0 19px 38px rgba(0,0,0,0.30), 0 15px 12px rgba(0,0,0,0.22);
	transition: 0.5s ease;
	background-color: rgba(7, 217, 0, 0.8);
}

.getInTouch{
  	margin: auto;
  	width: 60%;
  	padding: 25px;
  	margin-top: 20px;
  	box-shadow: 0 19px 38px rgba(0,0,0,0.30), 0 15px 12px rgba(0,0,0,0.22);
  	display: flex;
  	flex-direction: column;
  	gap: 1rem;
  	
}
.getInTouch div{
  	display: flex;
  	flex-direction: column;  	
}
.getInTouch div label{
	color: white;
	font-size: 1.2rem;
}
.getInTouch div input{
	outline:none;
	border:none;
	font-size: 1rem;
	padding: 5px;
	margin-top:5px;
}
.getInTouch div textarea{
	outline:none;
	border:none;
	font-size: 1rem;
	padding: 5px;
	margin-top:5px;
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
	  <a href="allUserLeads.jsp"class="icon-a"><i class="fa fa-line-chart icons"></i>   All Leads</a>
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

		<div class="head">
			<div class="col-div-6">
	<span style="font-size:30px;cursor:pointer; color: white;" class="nav"  > Help</span>
	<span style="font-size:30px;cursor:pointer; color: white;" class="nav2"  > Help</span>
	</div>
		<div class="col-div-6">
		<div class="profile">
			<img src="image/xyz.jfif" class="pro-img" />
			<%
			String namedb = null;
			String emaildb = null;
			try{
				String email = (String)session.getAttribute("email");
				PreparedStatement pstmt = connect.prepareStatement("select name, email from users where email = ?");			
				pstmt.setString(1, email);
				ResultSet rst = pstmt.executeQuery();

				while(rst.next()) {
					namedb = rst.getString(1);
					emaildb = rst.getString(2);
				}
				%>
					<p style="font-size:20px; text-align: center;"><% out.print(namedb); %> <span  style="font-size:12px; text-align: center;"><% out.print(emaildb); %></span></p>
				<%									
				}catch(Exception e){
					System.out.println(e);
				}
			%>
		</div>
	</div>
		<div class="clearfix"></div>
	</div>
		<div class="clearfix"></div>
		<br/>
		
		<div class="col-div-3">
			<div class="box">
				<p> <%= totalLeadCount %> 
	<br/><span>Total Leads</span></p>
				<i class="fa fa-line-chart box-icon"></i>
			</div>
		</div>
		<div class="col-div-3">
			<div class="box">
				<p> <%= totalLeadCountNewLeads %> <br/><span>New Leads</span></p>
				<i class="fa fa-cart-plus box-icon"></i>
			</div>
		</div>
		<div class="col-div-3">
			<div class="box">
				<p> <%= totalLeadCountBySource %> <br/><span>Social Media</span></p>
				<i class="fa fa-globe box-icon"></i>
			</div>
		</div>
		<div class="col-div-3">
			<div class="box">
				<p> <%= totalLeadCountByStatusFinished %> <br/><span>Enrolled</span></p>
				<i class="fa fa-tasks box-icon"></i>
			</div>
		</div>
		<div class="clearfix"></div>
		<br/>
		<!-- Adding new leads form -->
		<div class="clearfix"></div>
		<br/>
		<div class="col-12">
			<div class="boxheading">
				<p style = "margin-top: 20px;">Get In Touch</p>
				<i class="fa fa-address-book box-icon flaot-left"></i>	
				<hr style="height:2px;border-width:0;color:gray;background-color:gray">
			
				<form action="help" method="post">
					<div  class="getInTouch">
						<div>
							<label>Name</label>
							<input type = "text" name = "name" placeholder = "Your name" value="<%= userCookie.getName()%>"/>
						</div>
						<div>
							<label>Email</label>
							<input type = "email" name = "email" placeholder = "Your email" value="<%= userCookie.getEmail()%>"/>
						</div>
						<div>
							<label>Mobile</label>
							<input type = "tel" name = "mobile" placeholder = "Your mobile" maxlength = "10" value="<%= userCookie.getMobile()%>"/>
						</div>
						<div>
							<label>Subject</label>
							<input type = "text" name = "subject" placeholder = "Enter Subject"/>
						</div>
						<div>
							<label>Comments</label>
							<textarea name = "comments" rows="5" cols="35" placeholder = "Comments"></textarea>
						</div>
						<button type = "submit" class = "btn">Send</button>
					</div>
				</form>		
		</div>	
		</div>
		
		
		<br><br><br>
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


	</html>