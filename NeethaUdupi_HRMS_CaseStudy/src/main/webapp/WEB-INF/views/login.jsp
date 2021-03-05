<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link rel="stylesheet" type="text/css" href="./css/login.css">

</head>
<body>
	<div class="main_container">

		<form action="login" method="post">

			<div class="form_container">

				<div class="header_image">
				
					<div class="login_header">
						<h2>Login</h2>
					</div>
					
					<div class="image_container">
						<img src="/images/person.jpg" />
					</div>
					
				</div>

				<div class="login_details">
				
					<div class="login_cred">
						<label>Username</label> 
						<input id="login_uname" name="username" 
							type="text" placeholder="Username" required>
					</div>
					
					<div class="login_cred">
						<label> Password</label> 
						<input id="login_pwd" name="password"
							type="password" placeholder="Password" required>
					</div>

					<div class="login_btn">
						<input id="submit_button" type="submit" value="Login">
					</div>

					<div class="login_forgot">
						<a href="#">Forgot Password</a>
					</div>
					
				</div>

			</div>

		</form>

	</div>

</body>
</html>