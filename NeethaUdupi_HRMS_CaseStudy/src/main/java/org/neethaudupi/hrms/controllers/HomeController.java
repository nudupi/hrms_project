package org.neethaudupi.hrms.controllers;



import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.neethaudupi.hrms.models.EmployeePersonal;
import org.neethaudupi.hrms.models.EmployeeProfessional;
import org.neethaudupi.hrms.models.User;
import org.neethaudupi.hrms.repository.EmployeePersonalRepository;
import org.neethaudupi.hrms.repository.EmployeeProfessionalRepository;
import org.neethaudupi.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * Home Controller for the main_page which provides option to navigate to other pages
 */
@Controller
public class HomeController {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	HomeController(UserRepository userRepository, PasswordEncoder passwordEncoder ) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	/* Takes to the main page after login. */
	@GetMapping("/")
	public String showPage(Model model, Principal principal, HttpSession session) {
	
		User user = userRepository.findByUsername(principal.getName());
		
		/*
		 * Set session attribute to the employee id connected to the user
		 */
		session.setAttribute("empId",user.getEmployeeProfessional().getEmpId());
		model.addAttribute("empProf",user.getEmployeeProfessional());
		return "main_page";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/")
	public String showMainPage(@Valid Model model) {
		return "main_page";
	}
	
	@GetMapping("/mainPage")
	public String showMainPageMenu(Model model, HttpSession session, Principal principal) {
		
		User user = userRepository.findByUsername(principal.getName());
		model.addAttribute("empProf",user.getEmployeeProfessional());
		return "main_page";
	}
	
	@GetMapping("/logoutSuccess")
	public String showLogoutPage() {
		return "logout";
	}
	
	
	
}
