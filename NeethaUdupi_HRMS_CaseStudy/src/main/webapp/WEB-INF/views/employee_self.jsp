<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Personal</title>

<link rel="stylesheet" type="text/css" href="/css/employee_self.css">
<link rel="stylesheet" type="text/css" href="/css/nav_bar.css">
<link rel="stylesheet" type="text/css" href="/css/name_designation.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="/scripts/tabs.js"></script>
<script src="/scripts/nav_bar.js"></script>

</head>

<body>

	<%@ include file="nav_bar.html"%>
	<%@ include file="name_designation.html"%>

	<div class="main_container">

		<div id="tabsDiv">

			<!-- Tab Bar  with buttons-->
			<div id="ulDiv">

				<button class="tablink active"
					onclick="displayContent(event,'Personal')" id="defaultOpen">
					<b>Personal Details</b>
				</button>

				<button class="tablink" onclick="displayContent(event,'Address')">
					<b>Address Details</b>
				</button>

			</div>

			<!-- Personal Details Tab -->
			<div id="Personal" class="contentClass">

				<div>
					<h4>Personal Details</h4>
				</div>
				<form:form action="personalInfo" method="post"
					modelAttribute="empPers">

					<div class="info_container">

						<div class="info_sub">

							<label>First Name</label>
							<form:input path="firstName" type="text"
								value="${empPers.firstName}" required="true"/>
							<form:errors path="firstName" />

							<label>Last Name</label>
							<form:input path="lastName" type="text"
								value="${empPers.lastName}" required="true"/>
							<form:errors path="lastName" />

						</div>

						<div class="info_sub">

							<label>Cell #</label>
							<form:input path="phoneNumber" type="text"
								value="${empPers.phoneNumber}" required="true"/>
							<form:errors path="phoneNumber" />

							<label>Email</label>
							<form:input path="email" type="email" value="${empPers.email}" required="true"/>
							<form:errors path="email" />
						</div>

						<div class="info_sub">

							<label>Date of Birth</label>
							<form:input path="dateOfBirth" type="date"
								value="${empPers.dateOfBirth}" required="true" />
							<form:errors path="dateOfBirth" />

							<label>Gender</label>
							<div class="gender">
								<div>
									<form:radiobutton path="gender" value="M"
										checked="${empPers.gender == 'M'?'checked':''}" />
									<label>M</label>
									<form:errors path="gender" />
								</div>
								<div>
									<form:radiobutton path="gender" value="F"
										checked="${empPers.gender == 'F'?'checked':''}" />
									<label>F</label>
									<form:errors path="gender" />
								</div>
							</div>

						</div>
					</div>

					<div class="save_button">
						<input type="submit" value="Save" />
					</div>

				</form:form>
				<div>
					<label class="msg" >${message}</label>
				</div>
			</div>


			<!--  Address Details Tab -->
			<div id="Address" class="contentClass">
				<div>
					<h4>Address Details</h4>
				</div>

				<form:form action="addressInfo" method="post"
					modelAttribute="address">
					<div class="info_container">

						<div class="info_sub">

							<label>Address Line</label> 
							<form:input  type="text"
								path="addressLine1" value="${address.addressLine1}"  />
							<form:errors path="addressLine1" />
							
							<label>Address Line</label> 
							<form:input type="text"
								path="addressLine2" value="${address.addressLine2}" />
							<form:errors path="addressLine2" />	
							
						</div>
						
						<div class="info_sub">
						
							<label>City</label> 
							<form:input  type="text" path="city"
								value="${address.city}" /> 
							<form:errors path="city" />
								
							<label>State</label> 
							<form:input type="text" path="userState"
								value="${address.userState}"  />
							<form:errors path="userState" />
						</div>
						
						<div class="info_sub">
						
							<label>Country</label> 
							<form:input type="text"
								path="country" value="${address.country}"  /> 
							<form:errors path="country" />	
								
							<label>Zipcode</label>
							<form:input type="text" path="zipcode"
								value="${address.zipcode}" />
							<form:errors path="zipcode" />
						</div>
					</div>
					
					<div class="save_button">
						<input type="submit" value="Save" />
					</div>
				</form:form>
				<div>
					<label class="msg">${message_add}</label>
				</div>
			</div>

		</div>
	</div>

</body>
</html>