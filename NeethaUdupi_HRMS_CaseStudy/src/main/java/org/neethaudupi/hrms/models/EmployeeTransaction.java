package org.neethaudupi.hrms.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * This entity is used to hold the employee transactions.
 */
@Entity
@Table(name = "EmployeeTransaction")
public class EmployeeTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transId;
	
	@NotEmpty(message="Action code is required")
	private String action;
	
	@NotNull(message="Transaction Date is required")
	private Date transDate;
	
	@NotEmpty(message="Transaction Desc is required")
	private String actionDesc;
	
	@ManyToOne
	private EmployeeProfessional employeeProfessional;

	public EmployeeTransaction() {
		super();
	}

	public EmployeeTransaction(int transId, @NotEmpty(message = "Action code is required") String action,
			@NotNull(message = "Transaction Date is required") Date transDate,
			@NotEmpty(message = "Transaction Desc is required") String actionDesc,
			EmployeeProfessional employeeProfessional) {
		super();
		this.transId = transId;
		this.action = action;
		this.transDate = transDate;
		this.actionDesc = actionDesc;
		this.employeeProfessional = employeeProfessional;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public EmployeeProfessional getEmployeeProfessional() {
		return employeeProfessional;
	}

	public void setEmployeeProfessional(EmployeeProfessional employeeProfessional) {
		this.employeeProfessional = employeeProfessional;
	}
	
}
