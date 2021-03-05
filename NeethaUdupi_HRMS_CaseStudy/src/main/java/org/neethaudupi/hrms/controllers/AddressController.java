package org.neethaudupi.hrms.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.neethaudupi.hrms.models.Address;
import org.neethaudupi.hrms.models.EmployeePersonal;
import org.neethaudupi.hrms.repository.AddressRepository;
import org.neethaudupi.hrms.repository.EmployeePersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * Controller for employee_self page address details
 */
@Controller
public class AddressController {

	AddressRepository addressRepository;
	EmployeePersonalRepository employeePersonalRepository;
	
	private static final String BINDINGRESULT_ADD = "org.springframework.validation.BindingResult.address";

	@Autowired
	public AddressController(AddressRepository addressRepository,
			EmployeePersonalRepository employeePersonalRepository) {

		this.addressRepository = addressRepository;
		this.employeePersonalRepository = employeePersonalRepository;
	}

	// Personal Information Page - Address Details submission
	@PostMapping("/addressInfo")
	public String updateAddress(@Valid @ModelAttribute("address") Address address, BindingResult result,
			HttpSession session, RedirectAttributes attr) {

		// Check for binding errors
		if (result.hasErrors()) {
			attr.addFlashAttribute(BINDINGRESULT_ADD, result);
			attr.addFlashAttribute("address", address);

			return "redirect:/employeeSelf";
		}
		
		/*
		 * Check if an address is already associated with the employee. If yes, update it. 
		 * Otherwise add a new entry for address and associate with the employee 
		 */
		EmployeePersonal empPers = employeePersonalRepository.findByEmpId((int) session.getAttribute("empId"));
		if (empPers.getAddress() != null)
			address.setAddressId(empPers.getAddress().getAddressId());
		Address savedAdd = addressRepository.saveAndFlush(address);
		empPers.setAddress(savedAdd);

		EmployeePersonal empPersSvd = employeePersonalRepository.save(empPers);
		if (empPers.getEmpId() == empPersSvd.getEmpId())
			attr.addFlashAttribute("message_add", "Successfully Updated");
		else
			attr.addFlashAttribute("message_add", "Update Failed");
		
		return "redirect:/employeeSelf";
	}

}
