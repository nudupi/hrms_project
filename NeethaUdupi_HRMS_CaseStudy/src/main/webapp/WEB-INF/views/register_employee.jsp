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
<title>Register</title>
<link rel="stylesheet" type="text/css" href="/css/register_employee.css">
<link rel="stylesheet" type="text/css" href="/css/nav_bar.css">
<link rel="stylesheet" type="text/css" href="/css/name_designation.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<script src="/scripts/tabs3.js"></script>
<script src="/scripts/nav_bar.js"></script>

</head>
<body>

	<%@ include file="nav_bar.html"%>
	<%@ include file="name_designation.html"%>

	<div class="main_container">

		<div id="tabsDiv">

			<!--  Tab bar with buttons -->
			<div id="ulDiv">

				<button class="tablink active"
					onclick="displayContent(event,'AddEmployee')" id="defaultOpen">
					Add a new Employee</button>

				<button class="tablink active"
					onclick="displayContent(event,'RegisterEmployee')">Add
					username and password</button>

				<button class="tablink active"
					onclick="displayContent(event,'Salary')">Salary Details</button>

			</div>

			<!-- Register a new employee -->
			<div id="AddEmployee" class="contentClass">

				<form:form action="addEmployee" method="post"
					modelAttribute="newEmp">
					<div class="info_container">
						<div>
							<label class="msg">${message}</label>
						</div>

						<div class="info_sub">
							<label>First Name</label> 
							<input name="firstName" id="firstname" type="text" value="${newEmp.employeePersonal.firstName}" required/>
							
							
							 <label>Last Name</label> 
							 <input name="lastName" id="lastname" type="text" value="${newEmp.employeePersonal.lastName}" required/>
							 
						</div>
						
						<div class="info_sub">
							<label>Job Title</label> 
							<form:input path="jobTitle" type="text" value="${newEmp.jobTitle}" required="true"/> 
							<form:errors path="jobTitle" />
							
							<label>Department</label>
							<form:select id="deptList" path="department" value="${newEmp.department}" >
								<c:forEach items="${deptList}" var="dept">

									<option value="${dept.deptId}">${dept.deptName}</option>

								</c:forEach>
							</form:select>
							<form:errors path="department" />
						</div>
						
						<div class="info_sub">
							<label>Manager</label> 
							<form:select id="deptList" path="manager" value="${newEmp.manager}" >
								<c:forEach items="${managerList}" var="manager">

									<option value="${manager.empId}">${manager.employeePersonal.firstName}</option>
								</c:forEach>
							</form:select>
							<form:errors path="manager" /> 
							
							<label>Employee Type</label> 
							<form:select id="deptList" path="empType" value="${newEmp.empType}">
								<option value="FULL-TIME">FULL-TIME</option>
								<option value="PART-TIME">PART-TIME</option>
								<option value="CONTRACT">CONTRACT</option>
							</form:select>
							<form:errors path="empType" />
						</div>
						
						<div class="info_sub">
							<label> Date of Joining</label> 
							<form:input path="dateOfJoining" type="date" value="${newEmp.dateOfJoining}" required="true" /> 
							<form:errors path="dateOfJoining" />
							
							<label>Department Joining Date</label> 
							<form:input path="deptDateOfJoining" type="date" value="${newEmp.deptDateOfJoining}" required="true"/>
							<form:errors path="deptDateOfJoining" />
						</div>

						<div class="info_sub">
							<label>SSN #</label> 
							<form:input path="ssnNumber" type="text" value="${newEmp.ssnNumber}" required="true"/>
							<form:errors path="ssnNumber" />
						</div>
						<div class="save_button">
							<input type="submit" value="Save" />
						</div>

					</div>


				</form:form>

			</div>

			<!-- User registration for login -->
			<div id="RegisterEmployee" class="contentClass">
				<form:form action="register" method="post" modelAttribute="user">
					<div class="info_container">
						<div>
							<label class="msg">${message_user}</label>
						</div>
						<div class="info_sub">
							<label>Employee Id</label> 
							<input name="empId" type="text" required/> 
						
							
							<label>Username</label>
							<form:input path="username" type="text" />
							<form:errors path="username" />
						</div>

						<div class="info_sub">
							<label>Password</label> 
							<form:input path="password"	type="text" />
							<form:errors path="password" />
							
							 <label>Role</label> 
							 <form:select id="deptList" path="userRole">
								<option value="ROLE_USER">EMPLOYEE</option>
								<option value="ROLE_ADMIN">MANAGER</option>
							</form:select>
							<form:errors path="userRole" />
						</div>

						<div class="save_button">
							<input type="submit" value="Save" />
						</div>
					</div>


				</form:form>
				
			</div>

			<!-- Salary Details Tab -->
			<div id="Salary" class="contentClass">
			
				<form:form action="salary" post="method" modelAttribute="salInfo">

					<div class="info_container">
					
						<div>
							<label class="msg">${message_sal}</label>
						</div>
						
						<div class="info_sub">
							<label>Employee Id</label>
							<input id="empId" name="empId" type="text" required/> 
							
							
							<label>Bank account #</label> 
							<form:input path="accountNumber" type="text" value="${salInfo.accountNumber}" required="true"  />
							<form:errors path="accountNumber" />
						</div>

						<div class="info_sub">

							<label>Basic Salary</label> 
							<form:input path="basicSalary" type="text" value="${salInfo.basicSalary}" required="true"  />
							<form:errors path="basicSalary" />
							
							 <label>Bonus</label> 
							 <form:input path="bonus" type="text" required="true"/>
							 <form:errors path="bonus" />
						</div>

						<div class="info_sub">
							<label>Allowance</label> 
							<form:input path="allowance" type="text" required="true"/> 
							<form:errors path="allowance" />
							
							<label>Deductions</label>
							<form:input path="deductions" type="text" required="true"/>
							<form:errors path="deductions" />
						</div>

						<div class="save_button">
							<input type="submit" value="Save" />
						</div>
					</div>

				</form:form>
				
			</div>

		</div>
	</div>
</body>
</html>