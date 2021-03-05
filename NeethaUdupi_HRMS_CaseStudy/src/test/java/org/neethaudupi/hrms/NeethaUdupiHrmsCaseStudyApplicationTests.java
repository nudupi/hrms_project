package org.neethaudupi.hrms;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.neethaudupi.hrms.models.Address;
import org.neethaudupi.hrms.models.EmployeePersonal;
import org.neethaudupi.hrms.models.EmployeeProfessional;
import org.neethaudupi.hrms.models.EmployeeTransaction;
import org.neethaudupi.hrms.models.User;
import org.neethaudupi.hrms.repository.EmployeePersonalRepository;
import org.neethaudupi.hrms.repository.EmployeeProfessionalRepository;
import org.neethaudupi.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NeethaUdupiHrmsCaseStudyApplicationTests {
	@Autowired
	EmployeePersonalRepository empPersRepos;
	
	@Autowired
	EmployeeProfessionalRepository empProfRepos;
	
	@Autowired
	UserRepository userRepos;
	
	@Test
	void contextLoads() {
	}
	
	/*
	 * Tests findByEmpId (Find Employee Personal Details given an employee Id)
	 */
	@Test
	void findByEmpIdTest() {
		
		EmployeePersonal empPers = empPersRepos.findByEmpId(11);
				
		assertEquals(empPers.getFirstName(),"May");
		assertEquals(empPers.getLastName(),"Parker");
		assertEquals(empPers.getPhoneNumber(),"12345678");
		assertEquals(empPers.getEmail(),"test@email.com");
		assertEquals(empPers.getDateOfBirth(),Date.valueOf("1996-05-04"));
		assertEquals(empPers.getGender(),"F");
	}
	
	/* 
	 * Tests findByFirstNameAndLastName (Find Employee Personal details given the first and last name)
	 */
	@Test
	void findByFirstNameAndLastNameTest() {
		EmployeePersonal empPers = empPersRepos.findByFirstNameAndLastName("Alan","Mills");
		assertEquals(empPers.getEmpId(), 12);
		assertEquals(empPers.getPhoneNumber(),"4782346709");
		assertEquals(empPers.getEmail(),"almills@test.com");
		assertEquals(empPers.getDateOfBirth(),Date.valueOf("2000-07-12"));
		assertEquals(empPers.getGender(),"M");
	}
	
	/* Tests findAllByJobTitle - Find all employees given the job title */
	@Test
	void findAllByJobTitleTest() {
		List<EmployeeProfessional> empProf = empProfRepos.findAllByJobTitle("TESTER");
		assertEquals(empProf.get(0).getEmpId(),14);
		assertEquals(empProf.get(0).getDateOfJoining(),Date.valueOf("2016-06-12"));
		assertEquals(empProf.get(0).getDeptDateOfJoining(),Date.valueOf("2016-07-04"));
		assertEquals(empProf.get(0).getEmpType(),"FULL-TIME");
		assertEquals(empProf.get(0).getEmployeePersonal().getFirstName(),"Sarah");
		assertEquals(empProf.get(0).getEmployeePersonal().getLastName(),"Williams");
		
		assertEquals(empProf.get(1).getEmpId(),16);
		assertEquals(empProf.get(1).getDateOfJoining(),Date.valueOf("2009-03-04"));
		assertEquals(empProf.get(1).getDeptDateOfJoining(),Date.valueOf("2009-09-04"));
		assertEquals(empProf.get(1).getEmpType(),"PART-TIME");
		assertEquals(empProf.get(1).getEmployeePersonal().getFirstName(),"Venus");
		assertEquals(empProf.get(1).getEmployeePersonal().getLastName(),"James");
		
	}

	/*
	 * Parametrized test for findByUserName
	 */
	@ParameterizedTest
	@CsvSource({"mparker,14,ROLE_ADMIN","swilliam,17,ROLE_USER","almills,15,ROLE_USER"})
	void findByUsernameTest(String userName, Integer userId, String role) {
		User userActual = userRepos.findByUsername(userName);
		assertEquals(userActual.getUserId(),userId);
		assertEquals(userActual.getUserRole(),role);
		
	}
}
