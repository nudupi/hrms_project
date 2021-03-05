<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PaySlip</title>

<link rel="stylesheet" type="text/css" href="/css/payslip.css">
<link rel="stylesheet" type="text/css" href="/css/nav_bar.css">
<link rel="stylesheet" type="text/css" href="/css/name_designation.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="/scripts/nav_bar.js"></script>

</head>
<body>

	<%@ include file="nav_bar.html"%>

	<div class="main_container">
		<div class="info_container">

			<div class="emp_pay">
				<!--  Logo and company address -->
				<div class="comp_info">
					<h1 id="logo">
						<i class="fa fa-globe fa-2x"></i>HRMS
					</h1>
					<label> 123, HR Dr.</label> <label> Charlotte, NC-28034</label>
				</div>
				
				<!-- Employee Details -->
				<div class="emp_info">
					<table id="emp_info_table">
						<tr>
							<td><b>Employee Name:</b></td>
							<td>${empPers.firstName}${empPers.lastName}</td>
						</tr>
						<tr>
							<td><b>Employee #:</b></td>
							<td>${empId}</td>
						</tr>
						<tr>
							<td><b>Department:</b></td>
							<td>${empProf.department.deptName}</td>
						</tr>
						<tr>
							<td><b>Job Title:</b></td>
							<td>${empProf.jobTitle }</td>
						</tr>
					</table>
				</div>
				
				<!-- Pay details -->
				<div class="pay_info">
					<table id="pay_info_table">
						<tr>
							<td><b>Pay Date:</b></td>
							<td>02/28/2021</td>
						</tr>
						<tr>
							<td><b>Pay Period:</b></td>
							<td>02/14/2021-02/28/2021</td>
						</tr>
						<tr>
							<td><b>Federal Filing Status:</b></td>
							<td>Single</td>
						</tr>
						<tr>
							<td><b>State Filing Status:</b></td>
							<td>NC</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		<!-- Table with salary details -->
		<div class="payment_details">
			<table id="payment_details_table">
				<thead>
					<tr>
						<th></th>
						<th>Current Period</th>
						<th>YTD</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Earnings</th>
						<th>${calcDet.earnings}</th>
						<th>${calcDet.annEarn}</th>
					</tr>
					<tr>
						<td>Basic Pay</td>
						<td>${payment.basicSalary}</td>
						<td>${calcDet.annSal}</td>
					</tr>
					<tr>
						<td>Bonus</td>
						<td>0.00</td>
						<td>${payment.bonus}</td>
					</tr>
					<tr>
						<td>Allowance</td>
						<td>${payment.allowance}</td>
						<td>${calcDet.annAllow}</td>
					</tr>
					<tr>
						<th>Pre-Tax Deductions</th>
						<th>${calcDet.deductions}</th>
						<th>${calcDet.annTotDeduct}</th>
					</tr>
					<tr>
						<td>401 k</td>
						<td>${calcDet.fr1k}</td>
						<td>${calcDet.annFr1k}</td>
					</tr>
					<tr>
						<td>FICA</td>
						<td>${calcDet.fica}</td>
						<td>${calcDet.annFica}</td>
					</tr>
					<tr>
						<td>Misc. Deduction</td>
						<td>${payment.deductions}</td>
						<td>${calcDet.annDeduct}</td>
					</tr>
					<tr>
						<th>Tax Deductions</th>
						<th>${calcDet.tdeduct}</th>
						<th>${calcDet.annTdeduct }</th>
					</tr>
					<tr>
						<td>Federal</td>
						<td>${calcDet.federal}</td>
						<td>${calcDet.annFederal}</td>
					</tr>
					<tr>
						<td>State</td>
						<td>${calcDet.state}</td>
						<td>${calcDet.annState}</td>
					</tr>
					<tr>
						<th>Net Pay</th>
						<th>${calcDet.netPay}</th>
						<th>${calcDet.annNetPay}</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>