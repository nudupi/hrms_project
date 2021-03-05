package org.neethaudupi.hrms.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/*
 * This entity holds personal details of an employee.
 */
@Entity
@Table(name="employeePersonal")
public class EmployeePersonal {
	
	@Id
	private int empId;
	
	@NotEmpty(message="First Name required")
	private String firstName;
	
	@NotEmpty(message="Last Name required")
	private String lastName;
	
	private String phoneNumber;
	
	private String email;
		
	private Date dateOfBirth;
	
	private String gender;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "empId")
	private EmployeeProfessional employeeProfessional;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId")
	private Address address;

	public EmployeePersonal() {
		super();
	}


	public EmployeePersonal(int empId, @NotEmpty(message = "First Name required") String firstName,
			@NotEmpty(message = "Last Name required") String lastName, String phoneNumber, String email,
			Date dateOfBirth, String gender, EmployeeProfessional employeeProfessional, Address address) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.employeeProfessional = employeeProfessional;
		this.address = address;
	}



	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public EmployeeProfessional getEmployeeProfessional() {
		return employeeProfessional;
	}

	public void setEmployeeProfessional(EmployeeProfessional employeeProfessional) {
		this.employeeProfessional = employeeProfessional;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
