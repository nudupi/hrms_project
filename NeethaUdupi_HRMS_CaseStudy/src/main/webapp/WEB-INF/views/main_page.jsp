<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main</title>

	<link rel="stylesheet" type="text/css" href="/css/main_page.css">
	<link rel="stylesheet" type="text/css" href="/css/nav_bar.css">
	<link rel="stylesheet" type="text/css" href="/css/name_designation.css">
	
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<script src="/scripts/nav_bar.js"></script>

</head>
<body>

	<%@ include file="nav_bar.html"%>
	<%@ include file="name_designation.html"%>
	
	<div class="main_container">
	
		<div class="card_main_container">

			<div class="card_container">

				<div class="card" onClick="window.location='./employeeSelf'">
					<h4>Personal Details</h4>
					<div class="card_icon">
						<i class="fa fa-user fa-3x"></i>
					</div>
				</div>

				<div class="card" onClick="window.location='./employeeProf'">
					<h4>Professional Details</h4>
					<div class="card_icon">
						<i class="fa fa-address-card fa-3x"></i>
					</div>
				</div>

				<div class="card" onClick="window.location='./payslip'">
					<h4>Payslip</h4>
					<div class="card_icon">
						<i class="fa fa-money fa-3x"></i>
					</div>
				</div>

			</div>

			<sec:authorize access="hasRole('ADMIN')">
			
				<div class="card_container">
					<div class="card_new1" onClick="window.location='./register'">
						<h4>Register Employee</h4>
						<div class="card_icon">
							<i class="fa fa-user-plus fa-3x"></i>
						</div>
					</div>

					<div class="card_new2" onClick="window.location='./manageEmployee'">
						<h4>Manage Employee</h4>
						<div class="card_icon">
							<i class="fa fa-users fa-3x"></i>
						</div>
					</div>

				</div>
			</sec:authorize>

		</div>
	</div>
</body>
</html>