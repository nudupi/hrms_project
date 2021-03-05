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
<title>Employee Professional</title>

<link rel="stylesheet" type="text/css" href="/css/employee_self.css">
<link rel="stylesheet" type="text/css" href="/css/nav_bar.css">
<link rel="stylesheet" type="text/css" href="/css/name_designation.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="/scripts/tabs2.js"></script>
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
					onclick="displayContent(event,'Professional')" id="defaultOpen">
					<b>Professional Details</b>
				</button>

				<button class="tablink active"
					onclick="displayContent(event,'Employment')">
					<b>Employee Transactions</b>
				</button>

				<button class="tablink active"
					onclick="displayContent(event,'Salary')">
					<b>Salary Details</b>
				</button>

			</div>

			<!-- Professional Details Tab -->
			<div id="Professional" class="contentClass">

				<div class="info_container">
					<table>
						<tr>
							<th>Employee Id :</th>
							<td>${empId}</td>
							<th>SSN :</th>
							<td>${empProf.ssnNumber}</td>
						</tr>
						<tr>
							<th>Job Title :</th>
							<td>${empProf.jobTitle}</td>
							<th>Department :</th>
							<td>${empProf.department.deptName}</td>
						</tr>
						<tr>
							<th>Manager :</th>
							<td>${managerProf.employeePersonal.firstName}</td>
							<th>Employee Type :</th>
							<td>${empProf.empType}</td>
						</tr>
						<tr>
							<th>Date of Joining :</th>
							<td>${empProf.dateOfJoining}</td>
							<th>Dept Date of joining :</th>
							<td>${empProf.deptDateOfJoining}</td>
						</tr>
					</table>
				</div>

			</div>

			<!--  Employee Transactions Tab -->
			<div id="Employment" class="contentClass">
				<div class="info_container">
					<table id="trans_table">
						<thead>
							<tr>
								<th>Action Code</th>
								<th>Action Date</th>
								<th>Description</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${empTrans}" var="empTrans">
								<tr>
									<td>${empTrans.action}
									<td><input id="tranDate" type="date"
										value="${empTrans.transDate}" disabled /></td>
									<td>${empTrans.actionDesc}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>

			<!--  Salary Details Tab -->
			<div id="Salary" class="contentClass">
				<div class="info_container">
					<table>

						<tr>
							<th>BasicSalary :</th>
							<td>${salary.basicSalary}</td>
							<th>Bonus :</th>
							<td>${salary.bonus}</td>
						</tr>
						<tr>
							<th>Allowance :</th>
							<td>${salary.allowance}</td>
							<th>Deductions :</th>
							<td>${salary.deductions}</td>
						</tr>
						<tr>
							<th>Bank Acc # :</th>
							<td>${salary.accountNumber}</td>

						</tr>


					</table>
				</div>

			</div>

		</div>
	</div>


</body>
</html>