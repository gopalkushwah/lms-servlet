<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign up</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />

    <link rel="stylesheet" href="login.css" />
    <link rel="stylesheet" href="./css/signup.css" />
    

<!-- This cdn is for sweet alert -->
<script src=
"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.0/sweetalert.min.js">
  </script>
  
  <script src="https://common.olemiss.edu/_js/sweet-alert/sweet-alert.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://common.olemiss.edu/_js/sweet-alert/sweet-alert.css">
</head>
<body>

	<%
	if (request.getAttribute("failed") != null) {
		out.print("<script>sweetAlert('Oops...', 'Its look like you are not a valid users!', 'error');</script>");
	}
	%>

	<form action = "RegisterUsers" method="post">
    <div class="login_form_container">
      <div class="login_form">
        <h2>Sign Up</h2>
       
        <div class="login_inner_container">
        	<div class="input_group">
	          <i class="fa fa-user"></i>
	          <input
	          	name = "name"
	            type="text"
	            placeholder="Full Name"
	            class="input_text"
	            autocomplete="off"
	            required
	          />
	        </div>
	        
	        <div class="input_group">
	          <i class="fa fa-envelope"></i>
	          <input
	          	name = "email"
	            type="email"
	            placeholder="Email"
	            class="input_text"
	            autocomplete="off"
	            required
	          />
	        </div>
	        
	        <div class="input_group">
	          <i class="fa fa-phone"></i>
	          <input
	          	name = "mobile"
	            type="tel"
	            placeholder="Mobile"
	            class="input_text"
	            autocomplete="off"
	            maxlength = "10"
	            required
	          />
	        </div>
	                <div class="input_group">
	          <i class="fa fa-user"></i>
	          <input
	          	name = "company_id"
	            type="text"
	            placeholder="Company Id"
	            class="input_text"
	            autocomplete="off"
	            required
	          />
	        </div>
	        
	        <div class="input_group">
	          <i class="fa fa-envelope"></i>
	          <input
	          	name = "company_email"
	            type="email"
	            placeholder="Company Email"
	            class="input_text"
	            autocomplete="off"
	            required
	          />
	        </div>
	        
	        
	               
	        <div class="input_group">
	          <i class="fa fa-unlock-alt"></i>
	          <input
	          	name = "password"
	            type="password"
	            placeholder="Password"
	            class="input_text"
	            autocomplete="off"
	            required
	          />
	        </div>
	        <div class="input_group">
	          <i class="fa fa-user-circle"></i>
	          <input type="radio" name="gender" class="input_radio" value="male">  Male
			  <input type="radio" name="gender" class="input_radio" value="female">  Female
	        
	        </div>
        </div>
        
        <div class="button_group" id="login_button">
          <button type = "submit">Register</button>>
        </div>
        <div class="fotter">
          <a href = "index.jsp">Back to Login</a>
        </div>
      </div>
      
    </div>
    </form>
</body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="login.js"></script>
  <script>
  $(".input_text").focus(function(){
	    $(this).prev('.fa').addclass('glowIcon')
	})
	$(".input_text").focusout(function(){
	    $(this).prev('.fa').removeclass('glowIcon')
	})

  </script>
</html>