package org.neethaudupi.hrms.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.neethaudupi.hrms.models.Address;
import org.neethaudupi.hrms.models.EmployeePersonal;
import org.neethaudupi.hrms.models.EmployeeProfessional;

import org.neethaudupi.hrms.repository.EmployeePersonalRepository;
import org.neethaudupi.hrms.repository.EmployeeProfessionalRepository;
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
 * Controller for Employee Personal Details - employee_self page, personal details tab
 */
@Controller
public class EmployeePersonalController {

	EmployeePersonalRepository employeePersonalRepository;
	EmployeeProfessionalRepository employeeProfessionalRepository;
	
	private static final String BINDINGRESULT_EMPSELF = "org.springframework.validation.BindingResult.empPers";
	private static final String BINDINGRESULT_ADD = "org.springframework.validation.BindingResult.address";

	@Autowired
	EmployeePersonalController(EmployeePersonalRepository employeePersonalRepository,
			EmployeeProfessionalRepository employeeProfessionalRepository) {
		this.employeePersonalRepository = employeePersonalRepository;
		this.employeeProfessionalRepository = employeeProfessionalRepository;
	}

	/*
	 * Called when Personal Details Page is displayed.
	 */
	@GetMapping("/employeeSelf")
	public String showEmployeeSelf(Model model, HttpSession session) {

		EmployeePersonal empPers = employeePersonalRepository.findByEmpId((int) session.getAttribute("empId"));
		EmployeeProfessional empProf = employeeProfessionalRepository.findByEmpId((int) session.getAttribute("empId"));

		// Load Employee Professional and Personal information along with address
		if (!model.containsAttribute("empProf"))
			model.addAttribute("empProf", empProf);

		if (!model.containsAttribute("empPers"))
			model.addAttribute("empPers", empPers);

		if (empPers.getAddress() != null)
			model.addAttribute("address", empPers.getAddress());
		else if (!model.containsAttribute("address"))
			model.addAttribute("address", new Address());

		// Message holds status of previous update on this page
		if (model.containsAttribute("message"))
			model.addAttribute("message", model.getAttribute("message"));

		if (model.containsAttribute("message_add"))
			model.addAttribute("message_add", model.getAttribute("message_add"));
		
		// Binding error during post request
		if (model.containsAttribute("BINDINGRESULT_EMPSELF")) {
			model.addAttribute(BINDINGRESULT_EMPSELF, model.getAttribute(BINDINGRESULT_EMPSELF));
		}
		
		if (model.containsAttribute("BINDINGRESULT_ADD")) {
			model.addAttribute(BINDINGRESULT_ADD, model.getAttribute(BINDINGRESULT_ADD));
		}

		return "employee_self";
	}

	// Personal Details Tab Submission
	@PostMapping("/personalInfo")
	public String showPersonalInfo(@Valid @ModelAttribute("empPers") EmployeePersonal empPers, BindingResult result,
			@RequestParam(name = "gender") String gender, HttpSession session, RedirectAttributes attr) {

		// Check for binding errors
		if (result.hasErrors()) {
			attr.addFlashAttribute(BINDINGRESULT_EMPSELF, result);
			attr.addFlashAttribute("empPers", empPers);

			return "redirect:/employeeSelf";
		}

		// Set the employee Id and gender, and save
		int employeeId = (int) session.getAttribute("empId");
		empPers.setEmpId(employeeId);
		empPers.setGender(gender);
		EmployeePersonal empPerSvd = employeePersonalRepository.saveAndFlush(empPers);

		if (empPerSvd.getEmpId() == employeeId)
			attr.addFlashAttribute("message", "Successfully Updated");
		else
			attr.addFlashAttribute("message", "Update Failure");

		return "redirect:/employeeSelf";
	}

}
