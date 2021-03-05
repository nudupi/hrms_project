package org.neethaudupi.hrms.repository;

import org.neethaudupi.hrms.models.EmployeePersonal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePersonalRepository extends JpaRepository<EmployeePersonal, Integer> {

	
	EmployeePersonal findByEmpId(int parseInt);
	
	EmployeePersonal findByFirstNameAndLastName(String firstName,String lastName);

	EmployeePersonal findByFirstName(String attribute);

	EmployeePersonal findByLastName(String attribute);

}
