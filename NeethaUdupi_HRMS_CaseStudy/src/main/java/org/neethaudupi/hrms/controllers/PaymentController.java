package org.neethaudupi.hrms.controllers;

import java.sql.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.neethaudupi.hrms.models.EmployeeProfessional;
import org.neethaudupi.hrms.models.EmployeeTransaction;
import org.neethaudupi.hrms.models.Payment;
import org.neethaudupi.hrms.repository.EmployeePersonalRepository;
import org.neethaudupi.hrms.repository.EmployeeProfessionalRepository;
import org.neethaudupi.hrms.repository.EmployeeTransactionRepository;
import org.neethaudupi.hrms.repository.PaymentRepository;
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
 * Controller to handle salary and payslip details.
 */
@Controller
public class PaymentController {
	PaymentRepository paymentRepos;
	EmployeeProfessionalRepository empProfRepos;
	EmployeePersonalRepository empPersRepos;
	EmployeeTransactionRepository empTransRepos;
	
	private static final String BINDINGRESULT_SAL = "org.springframework.validation.BindingResult.salInfo";
	
	@Autowired
	public PaymentController(PaymentRepository paymentRepos, EmployeeProfessionalRepository empProfRepos,
			EmployeePersonalRepository empPersRepos,EmployeeTransactionRepository empTransRepos ) {
		super();
		this.paymentRepos = paymentRepos;
		this.empProfRepos = empProfRepos;
		this.empPersRepos = empPersRepos;
		this.empTransRepos = empTransRepos;
	}
	
	/*
	 * Called when salary details are added on register_employee page, Salary details tab
	 */
	@PostMapping("/salary")
	public String updateSalInfo(@Valid @ModelAttribute("salInfo") Payment salInfo, BindingResult result,
			@RequestParam(name = "empId") int empId, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute(BINDINGRESULT_SAL, result);
			attr.addFlashAttribute("salInfo", salInfo);

			return "redirect:/register";
		}
		
		/*
		 * Save the salary details for the employee
		 */
		EmployeeProfessional empProf = empProfRepos.findByEmpId(empId);
		Payment newPayment = paymentRepos.saveAndFlush(salInfo);
		empProf.setPayment(newPayment);
		empProfRepos.save(empProf);

		/*
		 * Add an entry in the transactions table for the employee
		 */
		EmployeeTransaction empTrans = new EmployeeTransaction();

		Date date = new Date(System.currentTimeMillis());
		empTrans.setAction("SAL_UPD");
		empTrans.setTransDate(date);
		empTrans.setActionDesc("Salary Updated");

		empTrans.setEmployeeProfessional(empProf);
		EmployeeTransaction savedTrans = empTransRepos.save(empTrans);
		attr.addFlashAttribute("message_sal","Successfully updated");

		return "redirect:/register";
	}
	
	/*
	 * Called when payslip page is displayed, performs calculations based on payment table
	 */
	@GetMapping("/payslip")
	public String showPayslip(Model model, HttpSession session) {
		EmployeeProfessional empProf = empProfRepos.findByEmpId((int) session.getAttribute("empId"));
		model.addAttribute("empProf", empProf);
		model.addAttribute("empPers",empProf.getEmployeePersonal());
		
		Payment payment = empProf.getPayment();
		if (empProf.getPayment() != null) {
		model.addAttribute("payment", payment);

		HashMap<String, Double> calcDet = new HashMap<String, Double>();
		
		double basic = Math.round(payment.getBasicSalary() * 100.0)/100.0;
        
		

		double annSal = Math.round((basic * 12.0) * 100.0)/100.0;
		calcDet.put("annSal", annSal);
		double annAllow = Math.round((payment.getAllowance() * 12.0)*100.0)/100.0;
		
		calcDet.put("annAllow", annAllow);
		double earnings = Math.round((payment.getBasicSalary() + payment.getAllowance())*100.0)/100.0;
		
		calcDet.put("earnings", earnings);
		
		double annEarn = Math.round((earnings * 12.0)*100.0)/100.0;
		calcDet.put("annEarn", annEarn);
		
		double fr1k = Math.round((0.05 * basic)*100.0)/100.0;
		calcDet.put("fr1k", fr1k);
		calcDet.put("annFr1k", Math.round((12.0*fr1k)*100.0)/100.0);
		
		double fica = Math.round((0.06 * basic) *100.0)/100.0;
		calcDet.put("fica",fica);
		calcDet.put("annFica", Math.round((fica*12.0)*100.0)/100.0);;
		
		double annDeduct = Math.round((payment.getDeductions() *12.0)*100.0)/100.0;
		
		calcDet.put("annDeduct",annDeduct);

		double deductions = fr1k + fica + payment.getDeductions();
		
		calcDet.put("deductions", Math.round(deductions * 100.0)/100.0);
		
		double annTotDeduct = Math.round((deductions * 12.0)*100.0)/100.0;
		calcDet.put("annTotDeduct", annTotDeduct);

		double federal = Math.round((basic * 0.12)*100.00)/100.00;
		calcDet.put("federal",federal);
		calcDet.put("annFederal",Math.round((federal*12.0)*100.0)/100.0);
		
		double state = Math.round((basic *  0.08)*100.00)/100.00;
		calcDet.put("state",state);
		calcDet.put("annState", state*12);
		
		double tdeduct = Math.round((federal + state)*100.0)/100.0;
		calcDet.put("tdeduct", tdeduct);
		
		double annTdeduct = Math.round((tdeduct * 12.0)*100.0)/100.0;
		calcDet.put("annTdeduct", annTdeduct);
		
		double netPay = earnings - deductions - tdeduct;
		double annNetPay = 12 * netPay;
		calcDet.put("netPay", Math.round(netPay*100.0)/100.0);
		calcDet.put("annNetPay", Math.round(annNetPay*100.0)/100.0);
		
		model.addAttribute("calcDet", calcDet);
		}
		return "payslip";
	}
}
