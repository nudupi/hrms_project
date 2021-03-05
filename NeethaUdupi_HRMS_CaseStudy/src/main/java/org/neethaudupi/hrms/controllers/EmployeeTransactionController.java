package org.neethaudupi.hrms.controllers;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.neethaudupi.hrms.models.Department;
import org.neethaudupi.hrms.models.EmployeeProfessional;
import org.neethaudupi.hrms.models.EmployeeTransaction;
import org.neethaudupi.hrms.models.Payment;
import org.neethaudupi.hrms.models.User;
import org.neethaudupi.hrms.repository.DepartmentRepository;
import org.neethaudupi.hrms.repository.EmployeePersonalRepository;
import org.neethaudupi.hrms.repository.EmployeeProfessionalRepository;
import org.neethaudupi.hrms.repository.EmployeeTransactionRepository;
import org.neethaudupi.hrms.repository.UserRepository;
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
 * Controller which tracks the employee transactions - employee_update page
 */
@Controller
public class EmployeeTransactionController {
	
	EmployeeProfessionalRepository empProfRepos;
	EmployeeTransactionRepository empTransRepos;
	EmployeePersonalRepository empPersRepos;
	DepartmentRepository deptRepos;
	UserRepository userRepos;

	@Autowired
	public EmployeeTransactionController(EmployeeProfessionalRepository empProfRepos,
			EmployeeTransactionRepository empTransRepos, EmployeePersonalRepository empPersRepos, DepartmentRepository deptRepos) {
		super();
		this.empProfRepos = empProfRepos;
		this.empTransRepos = empTransRepos;
		this.empPersRepos = empPersRepos;
		this.deptRepos = deptRepos;
	}


	/*
	 * Executed when an employee is selected to be updated from the manage employee page, transfers to employee update page.
	 */
	@GetMapping("/updateEmployee")
	public String showUpdateEmployee(Model model, @RequestParam(name = "empId") int empId) {
		
		/* Populate employee details */
		EmployeeProfessional empProfUpd = empProfRepos.findByEmpId(empId);
		model.addAttribute("empProfUpd", empProfUpd);
		
		/* Populate all the departments for the department list */
		model.addAttribute("deptList", deptRepos.findAll());
		
		/* Populate all the manager details for the manager list */
		model.addAttribute("managerList",empProfRepos.findAllByJobTitle("Manager"));
		
		if (empProfUpd.getPayment() != null)
			model.addAttribute("salary",empProfUpd.getPayment());
		
		return "employee_update";
	}

	/*
	 * Executed when the control returns to the update employee page after updating the employee details
	 */
	@GetMapping("/updateEmployeeDetail")
	public String showUpdateEmployeeDetail(Model model) {
	
		if (model.containsAttribute("empProfUpd"))
			model.addAttribute("empProfUpd", model.getAttribute("empProfUpd"));
		
		EmployeeProfessional empProfUpd = (EmployeeProfessional)model.getAttribute("empProfUpd");
		
		model.addAttribute("deptList", deptRepos.findAll());
		
		model.addAttribute("managerList",empProfRepos.findAllByJobTitle("Manager"));
		
		if (empProfUpd.getPayment() != null)
		model.addAttribute("salary",empProfUpd.getPayment());
		
		return "employee_update";
	}
	
	/*
	 * Executed when employee details have been submitted for updation.
	 */
	@PostMapping("/updateEmployee")
	public String saveUpdateEmployee(@Valid @ModelAttribute("empProfUpd") EmployeeProfessional empProfUpd,
			@RequestParam(name = "updType") String update, RedirectAttributes attr){
		
		EmployeeProfessional empProf = empProfRepos.findByEmpId(empProfUpd.getEmpId());
		EmployeeTransaction empTrans = new EmployeeTransaction();
		
		
		/* Update the employee details as well as add entry to the transaction table about the updation */
		if (update.equals("jobTitle")) {
		
			empTrans.setAction("PRO");
			empTrans.setActionDesc("Promotion");
			empProf.setJobTitle(empProfUpd.getJobTitle());
		}
		
		if (update.equals("manager")) {
			empTrans.setAction("MAN");
			empTrans.setActionDesc("Manager changed");
			empProf.setManager(empProfUpd.getManager());
		}
		
		if (update.equals("empType")) {
			empTrans.setAction("TYP");
			empTrans.setActionDesc("Employee Type changed" );
			empProf.setEmpType(empProfUpd.getEmpType());
		}
		/*
		if (update.equals("basicSalary")) {
			empTrans.setAction("SAL");
			empTrans.setActionDesc("Basic Salary changed ");
			empProf.getPayment().setBasicSalary(empProfUpd.getPayment().getBasicSalary() );
		}
		*/
		if (update.equals("department")) {
			empTrans.setAction("DEPT");
			empTrans.setActionDesc("Department change");
			empProf.setDepartment(empProfUpd.getDepartment());
		}
		
			
		User user = empProfRepos.findByEmpId(empProfUpd.getEmpId()).getUser();
		empProf.setUser(user);
		attr.addFlashAttribute("empProfUpd",empProf);
		
		EmployeeProfessional emptest = empProfRepos.save(empProf);
		
		if (empProfUpd.getEmpId() == emptest.getEmpId()) {
		Date date = new Date(System.currentTimeMillis());
		empTrans.setTransDate(date);
		empTrans.setEmployeeProfessional(emptest);
		EmployeeTransaction savedTrans = empTransRepos.save(empTrans);
		
		}
		
		return "redirect:/updateEmployeeDetail";
	}
	

	/*
	 * Executed when an employee is being terminated from update employee page
	 */
	@PostMapping("/terminateEmployee")
	public String terminateEmployee(@Valid @ModelAttribute("empProfUpd") EmployeeProfessional empProfUpd) {
		
		EmployeeProfessional empProf = empProfRepos.findByEmpId(empProfUpd.getEmpId());
		User user = empProfRepos.findByEmpId(empProf.getEmpId()).getUser();
	//	empProf.setUser(user);
		
		empProf.setEmployeePersonal(null);
		empProf.setDepartment(null);
		empProf.setManager(null);
		empProf.setPayment(null);
		empProf.setUser(null);
		
		
		//	userRepos.delete(user);
		
		List<EmployeeTransaction> empTrans = empTransRepos
				.findByEmployeeProfessional_EmpId(empProf.getEmpId());
		empTransRepos.deleteAll(empTrans);
		
		empProfRepos.delete(empProf);
	
		return "redirect:/manageEmployee";
	}
}
