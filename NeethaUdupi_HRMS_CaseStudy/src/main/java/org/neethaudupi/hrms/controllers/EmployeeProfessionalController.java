package org.neethaudupi.hrms.controllers;


import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.neethaudupi.hrms.models.Department;
import org.neethaudupi.hrms.models.EmployeePersonal;
import org.neethaudupi.hrms.models.EmployeeProfessional;
import org.neethaudupi.hrms.models.EmployeeTransaction;
import org.neethaudupi.hrms.models.Payment;
import org.neethaudupi.hrms.models.User;
import org.neethaudupi.hrms.repository.DepartmentRepository;
import org.neethaudupi.hrms.repository.EmployeePersonalRepository;
import org.neethaudupi.hrms.repository.EmployeeProfessionalRepository;
import org.neethaudupi.hrms.repository.EmployeeTransactionRepository;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * Controller for displaying employee professional details, registering a new employee and viewing all
 *  employees reporting to the manager and search by id, name, deptartment
 */
@Controller
public class EmployeeProfessionalController {

	private EmployeeProfessionalRepository employeeProfessionalRepository;
	private EmployeePersonalRepository employeePersonalRepository;
	private DepartmentRepository departmentRepository;	
	private EmployeeTransactionRepository employeeTransactionRepository;
	
	private static final String BINDINGRESULT_NEWEMP = "org.springframework.validation.BindingResult.newEmp";
	private static final String BINDINGRESULT_USER = "org.springframework.validation.BindingResult.user";
	private static final String BINDINGRESULT_SAL = "org.springframework.validation.BindingResult.salInfo";
	
	@Autowired
	public EmployeeProfessionalController(EmployeeProfessionalRepository employeeProfessionalRepository,
			EmployeePersonalRepository employeePersonalRepository, DepartmentRepository departmentRepository,
			 EmployeeTransactionRepository employeeTransactionRepository) {
		this.employeeProfessionalRepository = employeeProfessionalRepository;
		this.employeePersonalRepository = employeePersonalRepository;
		this.departmentRepository = departmentRepository;
		this.employeeTransactionRepository = employeeTransactionRepository;
	}

	/*
	 * Executed when navigating to the employee professional details page. Populates the employee professional, 
	 * salary and transaction details.
	 */
	@GetMapping("/employeeProf")
	public String showEmployeeProf(Model model, HttpSession session) {

		EmployeeProfessional empProf = employeeProfessionalRepository.findByEmpId((int) session.getAttribute("empId"));
		model.addAttribute("empProf", empProf);
		model.addAttribute("managerProf", empProf.getManager());
		List<EmployeeTransaction> empTrans = employeeTransactionRepository
				.findByEmployeeProfessional_EmpId(empProf.getEmpId());
		if (!empTrans.isEmpty()) {
			model.addAttribute("empTrans", empTrans);
		}

		if (empProf.getPayment() != null)
			model.addAttribute("salary", empProf.getPayment());
		return "employee_professional";
	}

	/*
	 * Executed when navigating to manage employee page
	 */
	@GetMapping("/manageEmployee")
	public String showManageEmployee(Model model, HttpSession session) {
		
		List<EmployeeProfessional> allEmpProf = new ArrayList<EmployeeProfessional>();

		model.addAttribute("empProf", employeeProfessionalRepository.findByEmpId((int) session.getAttribute("empId")));

		if (model.containsAttribute("srch_by")) {
			
			/* Search for employee with given employee id */
			if (model.getAttribute("srch_by").equals("Employee Id") && (model.getAttribute("srch_emp") != null)) {
				
				EmployeeProfessional empProfsrch = employeeProfessionalRepository
						.findByEmpId(Integer.parseInt((String) model.getAttribute("srch_emp")));
				allEmpProf.add(empProfsrch);
				model.addAttribute("allEmpProf", allEmpProf);

			} else if (model.getAttribute("srch_by").equals("Full Name") && (model.getAttribute("srch_emp") != null)) {
				/*Search for employee by fullname */
				String str[] = ((String) model.getAttribute("srch_emp")).split("\s");

				EmployeePersonal empPers = employeePersonalRepository.findByFirstNameAndLastName(str[0], str[1]);
				if (empPers != null) {
					allEmpProf.add(employeeProfessionalRepository.findByEmpId(empPers.getEmpId()));
					model.addAttribute("allEmpProf", allEmpProf);

				}
			} else if (model.getAttribute("srch_by").equals("First Name") && (model.getAttribute("srch_emp") != null)) {
				/* Serach by first name */
				EmployeePersonal empPers = employeePersonalRepository.findByFirstName((String) model.getAttribute("srch_emp"));
				if (empPers != null) {
					allEmpProf.add(employeeProfessionalRepository.findByEmpId(empPers.getEmpId()));
					model.addAttribute("allEmpProf", allEmpProf);

				}
			} else if (model.getAttribute("srch_by").equals("Last Name") && (model.getAttribute("srch_emp") != null)) {
				/* Search by last name */
				EmployeePersonal empPers = employeePersonalRepository.findByLastName((String) model.getAttribute("srch_emp"));
				if (empPers != null) {
					allEmpProf.add(employeeProfessionalRepository.findByEmpId(empPers.getEmpId()));
					model.addAttribute("allEmpProf", allEmpProf);

				}
			} else if (model.getAttribute("srch_by").equals("Department") && (model.getAttribute("srch_emp") != null)) {
				/* Search by department */
				allEmpProf = employeeProfessionalRepository.findByDepartment_DeptId(Integer.parseInt((String)model.getAttribute("srch_emp")));
				
				
					model.addAttribute("allEmpProf", allEmpProf);

				
		} } else {
			/* All employees reporting to the manager */
			allEmpProf = employeeProfessionalRepository.findByManager_EmpId((int) session.getAttribute("empId"));

			model.addAttribute("allEmpProf", allEmpProf);
		}
			
			model.addAttribute("deptList", departmentRepository.findAll());
			
			return "manage_employee";
	}

	/*
	 * Executed when a new employee is being added
	 */
	@PostMapping("/addEmployee")
	public String showResultAddEmployee(@Valid @ModelAttribute("newEmp") EmployeeProfessional newEmp, BindingResult result,
			@RequestParam(name = "firstName") String fname, @RequestParam(name = "lastName") String lname,
			RedirectAttributes attr) {
		// Check for binding errors
		
				if (result.hasErrors()) {
					attr.addFlashAttribute(BINDINGRESULT_NEWEMP, result);
					attr.addFlashAttribute("newEmp", newEmp);
					
					return "redirect:/register";
				}
			
				
		EmployeePersonal empPers = new EmployeePersonal();

		empPers.setFirstName(fname);
		empPers.setLastName(lname);

		/* Save the employee professional details */
		EmployeeProfessional empProfRes = employeeProfessionalRepository.saveAndFlush(newEmp);

		empPers.setEmployeeProfessional(empProfRes);

		/* Save employee personal details */
		employeePersonalRepository.save(empPers);
		attr.addFlashAttribute("newEmpId", empProfRes.getEmpId());

		/* Update the employee transaction table */
		EmployeeTransaction empTransHire = new EmployeeTransaction();
		empTransHire.setAction("HIRE");

		empTransHire.setTransDate(empProfRes.getDateOfJoining());
		empTransHire.setActionDesc("New Employee Hired");

		empTransHire.setEmployeeProfessional(empProfRes);
		EmployeeTransaction savedTransHire = employeeTransactionRepository.save(empTransHire);

		EmployeeTransaction empTransDept = new EmployeeTransaction();
		empTransDept.setTransDate(empProfRes.getDeptDateOfJoining());
		empTransDept.setAction("DEPT_JOIN");
		empTransDept.setActionDesc("Joined Department");
		empTransDept.setEmployeeProfessional(empProfRes);
		EmployeeTransaction savedTransDept = employeeTransactionRepository.save(empTransDept);

		return "redirect:/register";
	}


	@GetMapping("/searchEmployee")
	public String showSearchEmployee(Model model) {

		if (model.getAttribute("srch_by").equals("Employee Id")) {

			EmployeeProfessional empProfsrch = employeeProfessionalRepository
					.findByEmpId(Integer.parseInt((String) model.getAttribute("srch_emp")));
			if (empProfsrch != null)
				model.addAttribute("empProfsrch", empProfsrch);

		}

		if (model.getAttribute("srch_by").equals("Employee Name")) {

			String str[] = ((String) model.getAttribute("srch_emp")).split("\s");

			if ((str[0] != null) && (str[1] != null)) {
				EmployeePersonal empPers = employeePersonalRepository.findByFirstNameAndLastName(str[0], str[1]);
				if (empPers != null) {

					model.addAttribute("empProfsrch", employeeProfessionalRepository.findByEmpId(empPers.getEmpId()));
				}
			}

		}

		return "manage_employee";
	}

	@PostMapping("/searchEmployee")
	public String showSearchResultEmployee(@RequestParam(name = "srch_by") String srch_by,
			@RequestParam(name = "srch_emp") String srch_emp, RedirectAttributes attr) {
		if (!srch_emp.equals("")) {
			attr.addFlashAttribute("srch_by", srch_by);
			attr.addFlashAttribute("srch_emp", srch_emp);
		}
		return "redirect:/manageEmployee";
	}

	/*
	 * Executed when navigating to register employee page.
	 */
	@GetMapping("/register")
	public String showRegistrationPage(Model model, HttpSession session) {

		
		model.addAttribute("empProf", employeeProfessionalRepository.findByEmpId((int) session.getAttribute("empId")));

		if (!model.containsAttribute("newEmp")) {
		
			model.addAttribute("newEmp", new EmployeeProfessional());
		}
		else { 
		
			model.addAttribute("newEmp",model.getAttribute("newEmp"));
		}
		
		if (!model.containsAttribute("user"))
			model.addAttribute("user",new User());
		else
			model.addAttribute("user", model.getAttribute("user"));
		
		if (!model.containsAttribute("salInfo"))
			model.addAttribute("salInfo",new Payment());
		else
			model.addAttribute("salInfo", model.getAttribute("salInfo"));
		
		
		model.addAttribute("deptList", departmentRepository.findAll());

		model.addAttribute("managerList", employeeProfessionalRepository.findAllByJobTitle("Manager"));
		
		if (model.containsAttribute("newEmpId"))
			model.addAttribute("message", "Successfully added Employee number : " + model.getAttribute("newEmpId"));
		
		// Message holds status of previous update on this page
				if (model.containsAttribute("message"))
					model.addAttribute("message", model.getAttribute("message"));

				if (model.containsAttribute("message_user"))
					model.addAttribute("message_user", model.getAttribute("message_user"));
				
				if (model.containsAttribute("message_sal"))
					model.addAttribute("message_sal", model.getAttribute("message_sal"));
				
				// Binding error during post request
				if (model.containsAttribute("BINDINGRESULT_NEWEMP")) { 
					
					model.addAttribute(BINDINGRESULT_NEWEMP, model.getAttribute(BINDINGRESULT_NEWEMP));
				} 
				
				if (model.containsAttribute(BINDINGRESULT_USER)) {
					model.addAttribute(BINDINGRESULT_USER, model.getAttribute(BINDINGRESULT_USER));
				}
				
				if (model.containsAttribute(BINDINGRESULT_SAL)) {
					model.addAttribute(BINDINGRESULT_SAL, model.getAttribute(BINDINGRESULT_SAL));
				}

		return "register_employee";
	}

}
