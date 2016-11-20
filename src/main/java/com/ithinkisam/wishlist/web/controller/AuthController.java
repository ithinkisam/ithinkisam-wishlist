package com.ithinkisam.wishlist.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.service.provider.UserProvider;

@Controller
public class AuthController {

	@Autowired
	private UserProvider userProvider;
	
	@RequestMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(HttpServletRequest request) {
		if (request.isUserInRole("USER")) {
			return "redirect:/";
		}
		return "registration";
	}
	
	@PostMapping("register")
	public String registerNewUser(
			@ModelAttribute User user,
			BindingResult bindingResult,
			@RequestParam("passwordConfirmation") String passwordConfirmation) {
		userProvider.addNewUser(user);
		return "redirect:/login?registered";
	}
	
}
