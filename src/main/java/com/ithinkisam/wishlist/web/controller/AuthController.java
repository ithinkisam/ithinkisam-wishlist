package com.ithinkisam.wishlist.web.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.ithinkisam.wishlist.domain.NewUser;
import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.security.VerificationToken;
import com.ithinkisam.wishlist.service.UserProvider;
import com.ithinkisam.wishlist.service.exception.EmailExistsException;
import com.ithinkisam.wishlist.service.exception.UsernameExistsException;
import com.ithinkisam.wishlist.service.validation.NewUserValidator;
import com.ithinkisam.wishlist.web.event.OnRegistrationCompleteEvent;

@Controller
public class AuthController {

	/**
	 * Providers
	 */
	@Autowired
	private UserProvider userProvider;
	
	/**
	 * Validators
	 */
	private NewUserValidator newUserValidator = new NewUserValidator();
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@RequestMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(HttpServletRequest request,
			@ModelAttribute NewUser newUser) {
		if (request.isUserInRole("USER")) {
			return "redirect:/";
		}
		return "registration";
	}
	
	@PostMapping("register")
	public String registerNewUser(
			@ModelAttribute NewUser newUser,
			BindingResult bindingResult,
			WebRequest request) {
		
		newUserValidator.validate(newUser, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		
		User user = createUserAccount(newUser, bindingResult);
		if (user == null) {
			bindingResult.reject("auth.registrationError");
			return "registration";
		}
		
		try {
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
		} catch (Exception e) {
			bindingResult.reject("auth.registrationError");
			return "registration";
		}
		
		return "redirect:/login?registered";
	}
	
	@GetMapping("/registrationConfirm")
	public String confirmRegistration(
			@RequestParam("token") String token) {
		
		VerificationToken verificationToken = userProvider.getVerificationToken(token);
		if (verificationToken == null) {
			return "redirect:/login?tokenNotFound";
		}
		if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			return "redirect:/login?expired";
		}
		
		userProvider.enableNewUser(verificationToken.getUser().getUsername());
		return "redirect:/login?registrationConfirmed";
	}
	
	private User createUserAccount(NewUser newUser, BindingResult bindingResult) {
		User user = null;
		try {
			user = userProvider.registerNewUserAccount(newUser);
		} catch (EmailExistsException e) {
			LOGGER.error("Unable to register new user; Email already exists: " + newUser.getEmail());
			bindingResult.reject("email", "validation.newuser.email.exists");
		} catch (UsernameExistsException e) {
			LOGGER.error("Unable to register new user; Username already exists: " + newUser.getUsername());
			bindingResult.reject("username", "validation.newuser.username.exists");
		}
		return user;
	}
	
}
