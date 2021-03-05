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
<title>Manage Employee</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/manage_employee.css">
<link rel="stylesheet" type="text/css" href="/css/nav_bar.css">
<link rel="stylesheet" type="text/css" href="/css/name_designation.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="/scripts/nav_bar.js"></script>
<body>
	<%@ include file="nav_bar.html"%>
	<%@ include file="name_designation.html"%>
	<div class="main_container">


		<div id="form_container">
			<div class="contentClass">
				<h4>Search Employee Options:</h4>
			</div>

			<div id="SearchEmployee" class="contentClass">


				<form:form action="/searchEmployee" method="post">
					<div class="srch_area_id">

						<label> Employee Id</label> <input type="hidden" name="srch_by"
							value="Employee Id" /> <input id="srch_emp" name="srch_emp"
							type="text" pattern="[0-9]+" /> <input id="srch_btn" name="srch_btn"
							type="submit" value=" " />


					</div>
				</form:form>
			</div>
			<div id="SearchEmployee" class="contentClass">


				<form:form action="/searchEmployee" method="post">
					<div class="srch_area_dept">
						<input type="hidden" name="srch_by" value="Department" /> <label>
							Department</label> <select id="deptList" name="srch_emp">
							<c:forEach items="${deptList}" var="dept">

								<option value="${dept.deptId}">${dept.deptName}</option>

							</c:forEach>
						</select> <input id="srch_btn" name="srch_btn" type="submit" value=" " />

					</div>
				</form:form>

			</div>
			<div id="SearchEmployee" class="contentClass">
				<form:form action="/searchEmployee" method="post">
					<div class="srch_area">

						<label>Employee Name</label> <select id="srch_by" name="srch_by">
							<option>First Name</option>
							<option>Last Name</option>
							<option>Full Name</option>
						</select> <input id="srch_emp" name="srch_emp" type="text" /> <input
							id="srch_btn" name="srch_btn" type="submit" value=" " />
					</div>
				</form:form>

			</div>
			<div id="TeamInfo" class="contentClass">

				<h4>Employees</h4>
				<table>
					<thead>
						<tr>
							<th>Employee Id</th>
							<th>Employee First Name</th>
							<th>Employee Last Name</th>
							<th>Designation</th>
							<th>Department</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allEmpProf}" var="empProfrow">
							<tr>
								<td>${empProfrow.empId }</td>
								<td>${empProfrow.employeePersonal.firstName}</td>
								<td>${empProfrow.employeePersonal.lastName }</td>
								<td>${empProfrow.jobTitle }</td>
								<td>${empProfrow.department.deptName}</td>
								<td><a href="updateEmployee?empId=${empProfrow.empId}">Edit</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>


		</div>
	</div>
</body>
</html>