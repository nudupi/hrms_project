package org.neethaudupi.hrms.repository;

import java.util.List;

import org.neethaudupi.hrms.models.EmployeeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeTransactionRepository extends JpaRepository<EmployeeTransaction, Integer> {
	
	// Find all transactions pertaining to an employee
	List<EmployeeTransaction> findByEmployeeProfessional_EmpId(int empId);

}
