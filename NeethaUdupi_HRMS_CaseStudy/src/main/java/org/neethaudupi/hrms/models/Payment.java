package org.neethaudupi.hrms.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/*
 * This entity is used to hold salary details
 */
@Entity
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int payId;
	
	
	private double basicSalary;
	
	private double bonus;
	private double allowance;
	private double deductions;
	
	
	private long accountNumber;

	/*
	@OneToOne(mappedBy = "payment")
	private EmployeeProfessional employeeProfessional;
	*/
	
	public Payment() {
		super();
		
	}

	public Payment(int payId, double basicSalary, double bonus, double allowance, double deductions,
			long accountNumber) {
		super();
		this.payId = payId;
		this.basicSalary = basicSalary;
		this.bonus = bonus;
		this.allowance = allowance;
		this.deductions = deductions;
		this.accountNumber = accountNumber;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	

	

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	
	
	
}
