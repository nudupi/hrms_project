package org.neethaudupi.hrms.controllers;

import javax.validation.Valid;

import org.neethaudupi.hrms.models.EmployeeProfessional;
import org.neethaudupi.hrms.models.User;
import org.neethaudupi.hrms.repository.EmployeeProfessionalRepository;
import org.neethaudupi.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * Controller for registering user to login to the application
 */
@Controller
public class UserController {
	
	private EmployeeProfessionalRepository employeeProfessionalRepository;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	private static final String BINDINGRESULT_USER = "org.springframework.validation.BindingResult.user";
	
	@Autowired
	public UserController(EmployeeProfessionalRepository employeeProfessionalRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		
		this.employeeProfessionalRepository = employeeProfessionalRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	/*
	 * Called when saving user details on register_employee page, register user tab
	 */
	@PostMapping("/register")
	public String registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam(name="empId") int empId,
			 RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute(BINDINGRESULT_USER, result);
			attr.addFlashAttribute("user", user);

			return "redirect:/register";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		/*
		 * Register user and connect it to an employee
		 */
		EmployeeProfessional empProf = employeeProfessionalRepository.findByEmpId(empId);
		user.setEmployeeProfessional(empProf);
		User userdb = userRepository.saveAndFlush(user);
		empProf.setUser(userdb);
		EmployeeProfessional empProfnew = employeeProfessionalRepository.save(empProf);
		attr.addFlashAttribute("message_user","Successfully updated");
		
		return "redirect:/register";
	}
}
