package org.neethaudupi.hrms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/*
 * This entity is used to hold username and password to enter the application.
 */
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@NotEmpty(message="username is required")
	private String username;
	
	@NotEmpty(message="password is required")
	private String password;
	
	@NotEmpty(message="userrole is required")
	private String userRole;
	
	@OneToOne(mappedBy = "user")
	private EmployeeProfessional employeeProfessional;
	
	public User() {
		super();
	}
	
	public User(Integer userId, @NotEmpty(message = "username is required") String username,
			@NotEmpty(message = "password is required") String password,
			@NotEmpty(message = "userrole is required") String userRole, EmployeeProfessional employeeProfessional) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.employeeProfessional = employeeProfessional;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public EmployeeProfessional getEmployeeProfessional() {
		return employeeProfessional;
	}

	public void setEmployeeProfessional(EmployeeProfessional employeeProfessional) {
		this.employeeProfessional = employeeProfessional;
	}

	
	/*	
	public User() {
	//	super();
		this.userRole = "ROLE_USER";
		
	}

	
	public User(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String userRole) {
		
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	*/
	
}
