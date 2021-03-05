package org.neethaudupi.hrms.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * This entity is used to store professional details of an employee.
 */
@Entity
@Table(name = "EmployeeProfessional")
public class EmployeeProfessional {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int empId;
	
	private String ssnNumber;
	
	private String jobTitle;
	
	private Date dateOfJoining;
	
	private Date deptDateOfJoining;
	
	private String empType;
	
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private EmployeePersonal employeePersonal;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	private EmployeeProfessional manager;
	
	@ManyToOne
	@JoinColumn(name = "deptId")
	private Department department;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payId")
	private Payment payment;
	
	public EmployeeProfessional() {
		super();
	}
	

	public EmployeeProfessional(int empId, String ssnNumber, String jobTitle, Date dateOfJoining,
			Date deptDateOfJoining, String empType, EmployeePersonal employeePersonal, User user,
			EmployeeProfessional manager, Department department, Payment payment) {
		super();
		this.empId = empId;
		this.ssnNumber = ssnNumber;
		this.jobTitle = jobTitle;
		this.dateOfJoining = dateOfJoining;
		this.deptDateOfJoining = deptDateOfJoining;
		this.empType = empType;
		this.employeePersonal = employeePersonal;
		this.user = user;
		this.manager = manager;
		this.department = department;
		this.payment = payment;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getSsnNumber() {
		return ssnNumber;
	}


	public void setSsnNumber(String ssnNumber) {
		this.ssnNumber = ssnNumber;
	}


	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getDeptDateOfJoining() {
		return deptDateOfJoining;
	}

	public void setDeptDateOfJoining(Date deptDateOfJoining) {
		this.deptDateOfJoining = deptDateOfJoining;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	

	public EmployeePersonal getEmployeePersonal() {
		return employeePersonal;
	}


	public void setEmployeePersonal(EmployeePersonal employeePersonal) {
		this.employeePersonal = employeePersonal;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EmployeeProfessional getManager() {
		return manager;
	}

	public void setManager(EmployeeProfessional manager) {
		this.manager = manager;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
