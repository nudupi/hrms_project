package org.neethaudupi.hrms.repository;

import java.util.Iterator;
import java.util.List;


import org.neethaudupi.hrms.models.EmployeeProfessional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployeeProfessionalRepository extends JpaRepository<EmployeeProfessional, Integer> {

	public EmployeeProfessional findByEmpId(int srch_emp);

	// Find all employees reporting to a manager
	public List<EmployeeProfessional> findByManager_EmpId(int attribute);

	// Find all employees by job title
	public List<EmployeeProfessional> findAllByJobTitle(String string);

	// Find all employees in a department
	public List<EmployeeProfessional> findByDepartment_DeptId(int parseInt);



}
