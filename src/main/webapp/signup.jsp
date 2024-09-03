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

<style>
	*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    letter-spacing: 1px;
    background-color: #0c1022;
}
.login_form_container{
    position: relative;
    width: 400px;
    height: 640px;
    max-width: 400px;
    max-height: 640px;
    background: #040717;
    border-radius: 50px 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
    margin-top: 70px;
}
.login_form_container::before{
    
    position: absolute;
    width: 170%;
    height: 170%;
    content: '';
    background-image: conic-gradient(transparent, transparent, transparent, #ee00ff);
    animation: rotate_border 6s linear infinite;

}
.login_form_container::after{
    
    position: absolute;
    width: 170%;
    height: 170%;
    content: '';
    background-image: conic-gradient(transparent, transparent, transparent, #00ccff);
    animation: rotate_border 6s linear infinite;
    animation-delay: -3s;
}

.login_form{
    position: absolute;
    content: '';
    background-color: #0c1022;
    border-radius: 50px 5px;
    inset: 5px;
    padding: 50px 40px;
    z-index: 10;
    color: #00ccff;

} 
h2{
    font-size: 40px;
    font-weight: 600;
    text-align: center;
}
.input_group{
    margin-top: 40px;
    position:relative;
    display:flex;
    align-items: center;
    justify-content: start;
}
.input_text{
    width: 95%;
    height: 30px;
    background: transparent;
    border: none;
    outline: none;
    border-bottom: 1px solid #00ccff;
    font-size: 20px;
    padding-left: 10px;
    color: #00ccff;

}
.input_radio{
    width: 10%;
    height: 15px;
    background: transparent;
    border: none;
    outline: none;
    border-bottom: 1px solid #00ccff;
    font-size: 20px;
    padding-left: 5px;
    color: #00ccff;

}
::placeholder{
    font-size: 15px;
    color: #00ccff52;
    letter-spacing: 1px;

}
.fa{
    font-size: 20px;

}
#login_button{
    position: relative;
    width: 300px;
    height: 40px;
    transition: 1s;
    margin-top: 70px;


}
#login_button button{
    position: absolute;
    width: 100%;
    height: 100%;
    text-decoration: none;
    z-index: 10;
    cursor: pointer;
    font-size: 22px;
    letter-spacing: 2px;
    border: 1px solid #00ccff;
    border-radius: 50px;
    background-color: #0c1022;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #00ccff;
}
.fotter {
    margin-top: 30px;
    display: flex;
    justify-content: space-between;

}
.fotter a{
    text-decoration: none;
    cursor: pointer;
    font-size: 18px;
    color: #00ccff;
    text-align: center;
}
.glowIcon {
    text-shadow: 0 0 10px #00ccff;

}
</style>

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

	<form action = "RegisterUsers" method = "post">
    <div class="login_form_container">
      <div class="login_form">
        <h2>Sign Up</h2>
       
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
          <i class="fa fa-user-circle"></i>
          <input type="radio" name="gender" class="input_radio" value="male">  Male
		  <input type="radio" name="gender" class="input_radio" value="female">  Female
        
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