package com.ptf.wp.projekat.dogadjaji_175.contollers;

import com.ptf.wp.projekat.dogadjaji_175.services.UserService;
import com.ptf.wp.projekat.dogadjaji_175.viewmodels.UserRegistration;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
	private final UserService userService;

	public AccountController(UserService userService) {
		super();
		this.userService = userService;
	}


	@GetMapping("/prijava")
	public String showLoginForm() {
		return "account/login";
	}
	
	@GetMapping("/registracija")
	public String showRegistrationForm(Model model) {
		UserRegistration registration = new UserRegistration();
		model.addAttribute("user", registration);
		
		return "account/registration";
	}
	
	@PostMapping("registracija")
	public String RegisterUser(@Valid @ModelAttribute("user") UserRegistration registration,
			 BindingResult result,
             Model model) {
		
		
	    boolean existing = userService.userExist(registration.getEmail());
	    if (existing) {
	        result.rejectValue("email", null, "Već postoji račun registriran sa tom e-poštom");
	    }
	    if (result.hasErrors()) {
	        model.addAttribute("user", registration);
	        return "account/registration";
	    }
	    
	    userService.save(registration);
	
	    return "redirect:/prijava";
	}
}
