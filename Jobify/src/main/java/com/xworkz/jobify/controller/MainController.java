package com.xworkz.jobify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.jobify.dto.JobifyDTO;
import com.xworkz.jobify.dto.JobifyEntity;
import com.xworkz.jobify.repository.JobifyRepository;
import com.xworkz.jobify.service.JobifyServiceImpl;

@ComponentScan("com.xworkz")
@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private JobifyServiceImpl service;
	@Autowired
	private JobifyRepository repo;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(JobifyDTO dto, Model model) {
		System.out.println(dto);
		boolean saved = service.validateAndSave(dto, model);
		if (saved) {
			model.addAttribute("save", "Registered Succesfully");
			return "Registers";
		}

		System.out.println(dto);
		return "Registers";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password, Model model, String forget) {
		JobifyEntity entity = service.login(email, password, model);
		if (forget.equalsIgnoreCase("forgetPassword")) {

			return "Forgot";
		}

		if (forget.equalsIgnoreCase("login")) {
			if (entity != null) {
				System.out.println("Entity is not null and going to fetch account data");

				System.out.println("jobseeker accountype  is being fetched " + entity.getAccountType());
				if (entity.getAccountType().equals("JobSeeker")) {

					return "JobSeeker";
				} else if (entity.getAccountType().equals("jobprovider")) {
					System.out.println("jobprovider accountype is being fetched");
					return "JobProvider";
				}

			}

			System.out.println("Not fetching data ");
			return "SignIn";
		}
		return "SignIn";

	}

	@PostMapping("/forgotPassword")
	public String forgotPassword( String email, @RequestParam String resetOtp, Model model, String reset) {
		System.out.println(email);

		if (reset.equalsIgnoreCase("otp")) {
			JobifyEntity entity = service.forgotPassword(email, model);

			if (entity != null) {

				model.addAttribute("success", "OTP sent Successfully..");
				model.addAttribute("email", email);
				return "Forgot"; // Replace with the appropriate view name
			} else {
				model.addAttribute("email", email);
				model.addAttribute("error", "OTP Not Sent..");
				return "Forgot"; // Replace with the appropriate view name
			}
		}
		if (reset.equalsIgnoreCase("reset")) {
			System.out.println("Running reset otp" + email);
			boolean flag = service.verifyOtp(email, resetOtp);
			if (flag == true) {
				return "ResetPassword";
			}

		}
		model.addAttribute("email", email);
		model.addAttribute("error", "You have entered wrong OTP...! ");
		return "Forgot";
	}
	
	@RequestMapping(value = "/reset",method =RequestMethod.POST)
	public String resetPassword(@RequestParam String email,@RequestParam String password,Model model) {
		boolean resetSuccessfull = service.resetAccount(email, password);
		if(resetSuccessfull ) {
			model.addAttribute("resetSuccess", "Password reset succesfull, You can login using new Password");
			return "SignIn";
		}else {
			model.addAttribute("resetError", "Passowrd reset failed ,Please try agian...! ");
			return "Forgot";
		}
		
		
	}

}