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
<title>Employee Update</title>

<link rel="stylesheet" type="text/css" href="/css/employee_update.css">
<link rel="stylesheet" type="text/css" href="/css/nav_bar.css">
<link rel="stylesheet" type="text/css" href="/css/name_designation.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="/scripts/nav_bar.js"></script>

</head>
<body>
	<%@ include file="nav_bar.html"%>
	<div class="main_container">
		<div class="contentClass">

			<div class="info_container">
				<h3>Current Information</h3>
				<div>
				<!--  	<div class="info_container"> -->
						<table>
							<tr>
								<th>Emp. Id</th>
								<td>${empProfUpd.empId}</td>
								<th>SSN</th>
								<td>${empProfUpd.ssnNumber}</td>
							</tr>
							<tr>
								<th>Job Title</th>
								<td>${empProfUpd.jobTitle}</td>
								<th>Department</th>
								<td>${empProfUpd.department.deptName}</td>
							</tr>
							<tr>
								<th>Manager</th>
								<td>${empProfUpd.manager.employeePersonal.firstName}</td>
								<th>Emp.Type</th>
								<td>${empProfUpd.empType}</td>
							</tr>
							<tr>
								<th>Date of Joining</th>
								<td>${empProfUpd.dateOfJoining}</td>
								<th>Dept Date of joining</th>
								<td>${empProfUpd.deptDateOfJoining}</td>
							</tr>
							<!--  
							<tr>
								<th>Basic Salary</th>
								<td>${empProfUpd.payment.basicSalary}</td>
								<th>Bonus</th>
								<td>${salary.bonus}</td>
							</tr>
							-->
						</table>
				<!--  	</div> -->
				</div>
			</div>

			<div class="contentClass_inner">
				<div class="info_container_add">
					<form:form action="updateEmployee" method="post"
						modelAttribute="empProfUpd">
						<input type="hidden" name="empId" value="${empProfUpd.empId}" />
						<input type="hidden" name="updType" value="jobTitle" />
						<h3>Promote Employee</h3>
						<div class="info_sub">
							<label>New Designation</label> <input id="design" name="jobTitle"
								type="text" value="${empProfUpd.jobTitle}" />
						</div>
						<div class="save_btn">
							<input type="submit" value="Save" />
						</div>

					</form:form>
				</div>

				<div class="info_container_add">
					<form:form action="updateEmployee" method="post"
						modelAttribute="empProfUpd">

						<h3>Change Manager</h3>
						<div class="info_sub">
						<input type="hidden" name="empId" value="${empProfUpd.empId}" />
						<input type="hidden" name="updType" value="manager" />
							<label>New Manager</label> 
								<select class="deptList" name="manager">
								<c:forEach items="${managerList}" var="manager">

									<option value="${manager.empId}">${manager.employeePersonal.firstName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="save_btn">
							<input type="submit" value="Save" />
						</div>

					</form:form>
				</div>
			</div>

			<div class="contentClass_inner">
				<div class="info_container_add">
					<form:form action="updateEmployee" method="post"
						modelAttribute="empProfUpd">

						<h3>Update Employee Type</h3>
						<div class="info_sub">
						<input type="hidden" name="empId" value="${empProfUpd.empId}" />
						<input type="hidden" name="updType" value="empType" />
							<label>New Employee Type</label> 
								<select class="deptList" name="empType">
								<option value="FULL-TIME">FULL-TIME</option>
								<option value="PART-TIME">PART-TIME</option>
								<option value="CONTRACT">CONTRACT</option>
							</select>
						</div>
						<div class="save_btn">
							<input type="submit" value="Save" />
						</div>

					</form:form>
				</div>
				<div class="info_container_add">
					<form:form action="updateEmployee" method="post"
						modelAttribute="empProfUpd">

						<h3>Transfer Employee</h3>
						<div class="info_sub">
						<input type="hidden" name="empId" value="${empProfUpd.empId}" />
						<input type="hidden" name="updType" value="department" />
							<label>New Department</label> 
								<select class="deptList"
								name="department">
								<c:forEach items="${deptList}" var="dept">

									<option value="${dept.deptId}">${dept.deptName}</option>

								</c:forEach>
							</select>
							
						</div>
						<div class="save_btn">
							<input type="submit" value="Save" />
						</div>

					</form:form>
				</div>
<!--  
				<div class="info_container_add">
					<form:form action="updateEmployee" method="post"
						modelAttribute="salary">

						<h3>Update Salary</h3>
						<div class="info_sub">
						<input type="hidden" name="empId" value="${empProfUpd.empId}" />
						<input type="hidden" name="updType" value="basicSalary" />
							<label>New Salary</label> <input id="sal" type="text" name="basicSalary"
								value="${salary.basicSalary}" />
						</div>
						<div class="save_btn">
							<input type="submit" value="Save" />
						</div>

					</form:form>
				</div>
				-->
			</div>

			<div class="contentClass_inner">
			<!--
				<div class="info_container_add">
					<form:form action="updateEmployee" method="post"
						modelAttribute="empProfUpd">

						<h3>Transfer Employee</h3>
						<div class="info_sub">
						<input type="hidden" name="empId" value="${empProfUpd.empId}" />
						<input type="hidden" name="updType" value="department" />
							<label>New Department</label> 
								<select class="deptList"
								name="department">
								<c:forEach items="${deptList}" var="dept">

									<option value="${dept.deptId}">${dept.deptName}</option>

								</c:forEach>
							</select>
							
						</div>
						<div class="save_btn">
							<input type="submit" value="Save" />
						</div>

					</form:form>
				</div> -->
				<!--  
				<div class="info_container_add">
					<form:form action="updateEmployee" method="post"
						modelAttribute="salary">

						<h3>Update Salary</h3>
						<div class="info_sub">
						<input type="hidden" name="empId" value="${empProfUpd.empId}" />
						<input type="hidden" name="updType" value="basicSalary" />
							<label>New Salary</label> <input id="sal" type="text" name="basicSalary"
								value="${salary.basicSalary}" />
						</div>
						<div class="save_btn">
							<input type="submit" value="Save" />
						</div>

					</form:form>
				</div>
				-->
				<div class="info_container_add">
					<form:form action="terminateEmployee" method="post"
						modelAttribute="empProfUpd">

						<h3>Terminate Employee</h3>
						<div class="info_sub">
							<input type="hidden" name="empId" value="${empProfUpd.empId}" />
							<p> Are you sure you want to terminate the employee?</p>
						</div>
							<div class="save_btn">
								<input type="submit" value="Save" />
							</div>
						
					</form:form>
				</div>
			</div>

			
		</div>
	</div>
</body>
</html>